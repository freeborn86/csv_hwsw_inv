package app;

import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import enrty_types.ComputerReportEntry;
import enrty_types.DisplayReportEntry;
import enrty_types.HasHostIdForFiltering;
import enrty_types.LicenceReportEntry;

public class AidaReportFilter {

	static LinkedHashMap<String, ComputerReportEntry> computersToKeep = new LinkedHashMap<>();

	private static LinkedList<ComputerReportEntry> filteredComputerEntries = new LinkedList<>();
	private static LinkedList<DisplayReportEntry> filteredDisplayEntries = new LinkedList<>();
	private static LinkedList<LicenceReportEntry> filteredLicenceEntries = new LinkedList<>();

	private static LinkedList<ComputerReportEntry> outdatedComputerEntries = new LinkedList<>();

	public static void filterReports() throws ParseException {
		populateComputersToKeep();
		deepCopyReports();
		filterAllEntries();
		generateOutdatedComputerEntries();
	}

	private static void populateComputersToKeep() throws ParseException {
		for (ComputerReportEntry readerEntry : AidaOutputReader.getComputers()) {
			if (computersToKeep.containsKey(readerEntry.getSerialNumber())) {
				if (!computersToKeep.get(readerEntry.getSerialNumber()).newer(readerEntry)) {
					computersToKeep.put(readerEntry.getSerialNumber(), readerEntry);
				}
			} else {
				computersToKeep.put(readerEntry.getSerialNumber(), readerEntry);
			}
		}
	}

	private static void deepCopyReports() {
		deepCopyLists(AidaOutputReader.getComputers(), filteredComputerEntries);
		deepCopyLists(AidaOutputReader.getDisplays(), filteredDisplayEntries);
		deepCopyLists(AidaOutputReader.getLicences(), filteredLicenceEntries);
	}

	private static void generateOutdatedComputerEntries() {
		deepCopyLists(AidaOutputReader.getComputers(), outdatedComputerEntries);
		removeOperatingComputers();
	}

	private static <E> void deepCopyLists(LinkedList<E> source, LinkedList<E> target) {
		if (!target.isEmpty()) {
			return;
		}
		for (E element : source) {
			target.add(element);
		}
	}

	private static void removeOperatingComputers() {
		for (Iterator<ComputerReportEntry> iterator = outdatedComputerEntries.iterator(); iterator.hasNext();) {
			ComputerReportEntry entryToRemove = iterator.next();
			if (computersToKeep.get(entryToRemove.getSerialNumberForFiltering()).getHostName() == entryToRemove
					.getHostNameForFiltering()) {
				iterator.remove();
			}
		}
	}

	private static void filterAllEntries() {
		filterComputerEntries();
		filterDisplayEntries();
		filterLicenceEntries();
	}

	private static void filterComputerEntries() {
		filterReportEntries(filteredComputerEntries);
	}

	private static void filterDisplayEntries() {
		filterReportEntries(filteredDisplayEntries);
	}

	private static void filterLicenceEntries() {
		filterReportEntries(filteredLicenceEntries);
	}

	private static <E extends HasHostIdForFiltering> void filterReportEntries(LinkedList<E> reportEntries) {
		for (Iterator<E> iterator = reportEntries.iterator(); iterator.hasNext();) {
			E entryToRemove = iterator.next();
			if (computersToKeep.get(entryToRemove.getSerialNumberForFiltering()).getHostName() != entryToRemove
					.getHostNameForFiltering()) {
				iterator.remove();
			}
		}
	}

	public static LinkedList<ComputerReportEntry> getFilteredComputers() {
		return filteredComputerEntries;
	}

	public static LinkedList<DisplayReportEntry> getFilteredDisplays() {
		return filteredDisplayEntries;
	}

	public static LinkedList<LicenceReportEntry> getFilteredLicences() {
		return filteredLicenceEntries;
	}

	public static LinkedList<ComputerReportEntry> getOutdatedComputerEntries() {
		return outdatedComputerEntries;
	}
}
