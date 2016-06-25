package app;

import config.Config;

public class ComputerAttributeMap {
	// TODO function should be void?
	static Computer checkLineAndInsertData(String line, Computer computer) {
		String[] data = line.split(Config.inputSeparator);
		// The AIDA report stores the value of the attribute on the 6th column
		// without it no meaningful data can be extracted from the report
		if (data.length < 6)
			return computer;
		Integer Id = Integer.parseInt(data[3]);
		if (isChassisType(Id))
			computer.chassisType = getLastElementWithoutSemiColons(data);
		if (isCpuType(Id))
			computer.cpuType = getLastElementWithoutSemiColons(data);
		if (isHostName(Id))
			computer.hostName = getLastElementWithoutSemiColons(data);
		if (isMicrosoftOsProductKey(Id))
			computer.microsoftOsProductKey = getLastElementWithoutSemiColons(data);
		if (isMicrosoftOsType(Id))
			computer.microsoftOsType = getLastElementWithoutSemiColons(data);
		if (isModel(Id))
			computer.model = getLastElementWithoutSemiColons(data);
		if (isMotherboardType(Id))
			computer.motherboardType = getLastElementWithoutSemiColons(data);
		if (isRamSize(Id))
			computer.ramSize = getLastElementWithoutSemiColons(data);
		if (isPrimaryDisplay(Id))
			computer.primaryDisplay = getLastElementWithoutSemiColons(data);
		;
		if (isSerialNumber(Id))
			computer.serialNumber = getLastElementWithoutSemiColons(data);
		return computer;
	}
	

	static String getLastElementWithoutSemiColons(String[] data) {
		return data[data.length - 1].replaceAll(";", "");
	}

	public static boolean isHostName(int aidaReportLineId) {
		return aidaReportLineId == 261;
	}

	public static boolean isReportDate(int aidaReportLineId) {
		return aidaReportLineId == 264;
	}

	public static boolean isReportTime(int aidaReportLineId) {
		return aidaReportLineId == 265;
	}

	public static boolean isMicrosoftOsType(int aidaReportLineId) {
		return aidaReportLineId == 513;
	}

	public static boolean isCpuType(int aidaReportLineId) {
		return aidaReportLineId == 517;
	}

	public static boolean isMotherboardType(int aidaReportLineId) {
		return aidaReportLineId == 518;
	}

	public static boolean isRamSize(int aidaReportLineId) {
		return aidaReportLineId == 520;
	}

	public static boolean isPrimaryDisplay(int aidaReportLineId) {
		return aidaReportLineId == 525;
	}

	public static boolean isModel(int aidaReportLineId) {
		return aidaReportLineId == 551;
	}

	public static boolean isSerialNumber(int aidaReportLineId) {
		return aidaReportLineId == 553;
	}

	public static boolean isChassisType(int aidaReportLineId) {
		return aidaReportLineId == 563;
	}

	public static boolean isMicrosoftOsProductKey(int aidaReportLineId) {
		return aidaReportLineId == 3853;
	}
}