package app;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import enrty_types.ComputerReportEntry;
import enrty_types.DisplayReportEntry;
import enrty_types.LicenceReportEntry;

public class AidaReportFilter {

	static LinkedHashMap<String, ComputerReportEntry> computersToKeep = new LinkedHashMap<>();

	private static LinkedList<ComputerReportEntry> filteredComputers = new LinkedList<>();
	private static LinkedList<DisplayReportEntry> filteredDisplays = new LinkedList<>();
	private static LinkedList<LicenceReportEntry> filteredLicences = new LinkedList<>();

	public void filterComputers() throws ParseException {
		populateComputersToKeep();
		deepCopyReports();
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
		deepCopyLists(AidaOutputReader.getComputers(), filteredComputers);
		deepCopyLists(AidaOutputReader.getDisplays(), filteredDisplays);
		deepCopyLists(AidaOutputReader.getLicences(), filteredLicences);
	}

	private static <E> void deepCopyLists(LinkedList<E> source, LinkedList<E> target) {
		if (!target.isEmpty()) {
			return;
		}
		for (E element : source) {
			target.add(element);
		}
	}

}
