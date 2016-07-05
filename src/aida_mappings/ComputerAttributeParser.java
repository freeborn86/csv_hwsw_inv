package aida_mappings;

import java.util.LinkedList;

import enrty_types.ComputerReportEntry;

public class ComputerAttributeParser {
	// TODO function should be void?
	public static ComputerReportEntry checkAndInsertData(String[] data, ComputerReportEntry computer,
			LinkedList<ComputerReportEntry> computers) {
		// The AIDA report stores the value of the attribute on the 6th column
		// without it no meaningful data can be extracted from the report
		if (data.length < 6)
			return computer;
		Integer Id = -1;
		try {
			Id = Integer.parseInt(data[3]);
		} catch (NumberFormatException n) {
			return computer;
		}
		if (isHostName(Id))
			computer.setHostName(getLastElementWithoutSemiColons(data));
		if (isReportDate(Id))
			computer.setDate(getLastElementWithoutSemiColons(data));
		if (isReportTime(Id))
			computer.setTime(getLastElementWithoutSemiColons(data));
		if (isChassisType(Id))
			computer.setChassisType(getLastElementWithoutSemiColons(data));
		if (isCpuType(Id))
			computer.setCpuType(getLastElementWithoutSemiColons(data));
		if (isMicrosoftOsProductKey(Id))
			computer.setMicrosoftOsProductKey(getLastElementWithoutSemiColons(data));
		if (isModel(Id))
			computer.setModel(getLastElementWithoutSemiColons(data));
		if (isMotherboardType(Id))
			computer.setMotherboardType(getLastElementWithoutSemiColons(data));
		if (isRamSize(Id))
			computer.setRamSize(getLastElementWithoutSemiColons(data));
		if (isPrimaryDisplay(Id))
			computer.setPrimaryDisplay(getLastElementWithoutSemiColons(data));
		if (isSerialNumber(Id))
			computer.setSerialNumber(getLastElementWithoutSemiColons(data));
		// since this is the last attribute adding the computer at it to the
		// list
		if (isMicrosoftOsType(Id)) {
			computer.setMicrosoftOsType(getLastElementWithoutSemiColons(data));
			computers.add(computer);
		}
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