package app;

import java.util.LinkedList;

public class DisplayAttributeMap {

	// TODO function should be void? Function should be decomposed to smaller
	// functions
	static Display checkLineAndInsertData(String[] data, Computer computer, Display display,
			LinkedList<Display> displays) {
		if (data.length < 6)
			return null;

		if (!data[0].toLowerCase().equals("monitor"))
			return null;

		String displayAttributeId = data[4];

		// Since the Aida report is sequential finding a monitor name signifies
		// a new display
		if (isName(displayAttributeId)) {
			if (display == null) {
				display = new Display();
				display.name = getLastElementWithoutSemiColons(data);
				display.host = computer.hostName;
			}
		}
		
		if (isManufacturingDate(displayAttributeId))
			display.manufacturingDate = getLastElementWithoutSemiColons(data);
		if (isModel(displayAttributeId))
			display.model = getLastElementWithoutSemiColons(data);
		if (isResolution(displayAttributeId))
			display.resolution = getLastElementWithoutSemiColons(data);
		if (isSerialNumber(displayAttributeId))
			display.serialNumber = getLastElementWithoutSemiColons(data);
		if (isSizeInInches(displayAttributeId))
			display.sizeInInches = getLastElementWithoutSemiColons(data);
		if (isType(displayAttributeId))
			display.type = getLastElementWithoutSemiColons(data);
		
		//This attribute follows any important other, therefore adding fields ends at it
		if (isSupportedModes(displayAttributeId)) {
			displays.add(display);
			display = null;
		}
		return display;
	}

	static String getLastElementWithoutSemiColons(String[] data) {
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

	public static boolean isSupportedModes(String displayAttributeId) {
		return displayAttributeId.toLowerCase().equals("supported video modes");
	}

	public static boolean isResolution(String displayAttributeId) {
		return displayAttributeId.toLowerCase().equals("maximum resolution");
	}

}