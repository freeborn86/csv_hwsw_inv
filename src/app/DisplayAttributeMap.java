package app;

import java.util.LinkedList;

public class DisplayAttributeMap {

	// TODO function should be void? Function should be decomposed to smaller
	// functions
	static void checkLineAndInsertData(String[] data, Display display, LinkedList<Display> displays) {
		if (data.length < 6)
			return;

		if (!data[0].toLowerCase().equals("monitor"))
			return;

		String displayAttributeId = data[4];
		if (isManufacturingDate(displayAttributeId))
			display.manufacturingDate = getLastElementWithoutSemiColons(data);
		if (isModel(displayAttributeId))
			display.model = getLastElementWithoutSemiColons(data);

		// Since the Aida report is sequential having a monitor name signifies a
		// new display
		if (isName(displayAttributeId)) {
			
			display.name = getLastElementWithoutSemiColons(data);
		}
		if (isResolution(displayAttributeId))
			display.resolution = getLastElementWithoutSemiColons(data);
		if (isSerialNumber(displayAttributeId))
			display.serialNumber = getLastElementWithoutSemiColons(data);
		if (isSizeInInches(displayAttributeId))
			display.sizeInInches = getLastElementWithoutSemiColons(data);
		if (isType(displayAttributeId))
			display.type = getLastElementWithoutSemiColons(data);
		if (isSupportedModes(displayAttributeId))
			displays.add(display);
			return;
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