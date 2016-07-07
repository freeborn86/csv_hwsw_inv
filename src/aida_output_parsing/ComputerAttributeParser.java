package aida_output_parsing;

import java.util.LinkedList;

import enrty_types.ComputerReportEntry;

public class ComputerAttributeParser {
	// TODO function should be void?
	public static void checkAndInsertData(String[] data, ComputerReportEntry computer,
			LinkedList<ComputerReportEntry> computers) {
		// The AIDA report stores the value of the attribute on the 6th column
		// without it no meaningful data can be extracted from the report
		if (data.length < 6)
			return;
		Integer Id = -1;
		try {
			Id = Integer.parseInt(data[3]);
		} catch (NumberFormatException n) {
			return;
		}
		if (isHostName(Id))
			computer.setHostName(getLastElementWithoutSemicolons(data));
		if (isReportDate(Id))
			computer.setDate(getLastElementWithoutSemicolons(data));
		if (isReportTime(Id))
			computer.setTime(getLastElementWithoutSemicolons(data));
		if (isChassisType(Id))
			computer.setChassisType(getLastElementWithoutSemicolons(data));
		if (isCpuType(Id))
			computer.setCpuType(getLastElementWithoutSemicolons(data));
		if (isMicrosoftOsProductKey(Id))
			computer.setMicrosoftOsProductKey(getLastElementWithoutSemicolons(data));
		if (isModel(Id))
			computer.setModel(getLastElementWithoutSemicolons(data));
		if (isMotherboardType(Id))
			computer.setMotherboardType(getLastElementWithoutSemicolons(data));
		if (isRamSize(Id))
			computer.setRamSize(getLastElementWithoutSemicolons(data));
		if (isPrimaryDisplay(Id))
			computer.setPrimaryDisplay(getLastElementWithoutSemicolons(data));
		if (isSerialNumber(Id))
			computer.setSerialNumber(getLastElementWithoutSemicolons(data));
		// Last used attribute, adding the computer at it to the list
		if (isMicrosoftOsType(Id)) {
			computer.setMicrosoftOsType(getLastElementWithoutSemicolons(data));
			computers.add(computer);
		}
		return;
	}

	static String getLastElementWithoutSemicolons(String[] data) {
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