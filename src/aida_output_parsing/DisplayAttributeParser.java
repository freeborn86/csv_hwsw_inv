package aida_output_parsing;

import java.util.LinkedList;

import enrty_types.ComputerReportEntry;
import enrty_types.DisplayReportEntry;

public class DisplayAttributeParser {

	// TODO function should be void? Function should be decomposed to smaller
	// functions
	public static DisplayReportEntry checkLineAndInsertData(String[] data, ComputerReportEntry currentComputer,
			DisplayReportEntry currentDisplayEntry, LinkedList<DisplayReportEntry> displays) {

		if (data.length < 6)
			return null;

		if (!data[0].toLowerCase().equals("monitor"))
			return null;

		String displayAttributeId = data[4];

		// Since the Aida report is sequential finding a monitor name signifies
		// a new display
		if (isName(displayAttributeId)) {
			currentDisplayEntry = new DisplayReportEntry();
			currentDisplayEntry.setEnrtyAdded(false);
			currentDisplayEntry.setName(getLastElementWithoutSemicolons(data));
			currentDisplayEntry.setHost(currentComputer.getHostName());
			currentDisplayEntry.setHostSerial(currentComputer.getSerialNumber());
		}
		if (isManufacturingDate(displayAttributeId))
			currentDisplayEntry.setManufacturingDate(getLastElementWithoutSemicolons(data));
		if (isModel(displayAttributeId))
			currentDisplayEntry.setModel(getLastElementWithoutSemicolons(data));
		if (isResolution(displayAttributeId))
			currentDisplayEntry.setResolution(getLastElementWithoutSemicolons(data));
		if (isSerialNumber(displayAttributeId))
			currentDisplayEntry.setSerialNumber(getLastElementWithoutSemicolons(data));
		if (isSizeInInches(displayAttributeId))
			currentDisplayEntry.setSizeInInches(getLastElementWithoutSemicolons(data));
		if (isType(displayAttributeId))
			currentDisplayEntry.setType(getLastElementWithoutSemicolons(data));
		if (isSupportedModes(data)) {
			if (currentDisplayEntry.isEnrtyAdded())
				return currentDisplayEntry;
			if (currentComputer.isDisplayAdded(currentDisplayEntry)) {
				return currentDisplayEntry;
			}
			displays.add(currentDisplayEntry);
			currentComputer.addDisplay(currentDisplayEntry);
			currentComputer.setExternalDisplayCount(currentComputer.getExternalDisplayCount() + 1);
			currentDisplayEntry.setEnrtyAdded(true);
		}
		return currentDisplayEntry;
	}

	static String getLastElementWithoutSemicolons(String[] data) {
		return data[data.length - 1].replaceAll(";", "");
	}

	public static boolean isName(String displayAttributeId) {
		return displayAttributeId.toLowerCase().equals("monitor name");
	}

	public static boolean isModel(String displayAttributeId) {
		return displayAttributeId.toLowerCase().equals("model");
	}

	public static boolean isType(String displayAttributeId) {
		return displayAttributeId.toLowerCase().equals("monitor type");
	}

	public static boolean isManufacturingDate(String displayAttributeId) {
		return displayAttributeId.toLowerCase().equals("manufacture date");
	}

	public static boolean isSerialNumber(String displayAttributeId) {
		return displayAttributeId.toLowerCase().equals("serial number");
	}

	public static boolean isSizeInInches(String displayAttributeId) {
		return displayAttributeId.toLowerCase().equals("max. visible display size");
	}

	public static boolean isSupportedModes(String[] data) {
		return data[2].toLowerCase().equals("supported video modes");
	}

	public static boolean isResolution(String displayAttributeId) {
		return displayAttributeId.toLowerCase().equals("maximum resolution");
	}

}