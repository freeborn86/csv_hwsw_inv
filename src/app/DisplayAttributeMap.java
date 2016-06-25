package app;

import config.Config;

public class DisplayAttributeMap {

	/**
	 * String name; String model; String type; String manufacturingDate; String
	 * serialNumber; String sizeInInches; String resolution; String host;
	 */

	// TODO function should be void? Function should be decomposed to smaller
	// functions
	static Display checkLineAndInsertData(String line, Display display) {
		String[] data = line.split(Config.inputSeparator);
		if (data.length < 6)
			return display;

		if (!data[0].toLowerCase().equals("monitor"))
			return display;

		String displayAttributeId = data[4];
		if (isManufacturingDate(displayAttributeId))
			display.manufacturingDate = getLastElementWithoutSemiColons(data);
		if (isModel(displayAttributeId))
			display.model = getLastElementWithoutSemiColons(data);
		if (isName(displayAttributeId))
			display.name = getLastElementWithoutSemiColons(data);
		if (isResolution(displayAttributeId))
				display.resolution = getLastElementWithoutSemiColons(data);
		if (isSerialNumber(displayAttributeId))
				display.serialNumber = getLastElementWithoutSemiColons(data);
		if (isSizeInInches(displayAttributeId))
				display.sizeInInches = getLastElementWithoutSemiColons(data);
		if (isType(displayAttributeId))
				display.type = getLastElementWithoutSemiColons(data);

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

	public static boolean isResolution(String displayAttributeId) {
		return displayAttributeId.toLowerCase().equals("maximum resolution");
	}

}