package aida_output_parsing;

import java.util.LinkedList;

import enrty_types.ComputerReportEntry;
import enrty_types.LicenceReportEntry;

public class LicenceAttributeParser {

	public static void checkLineAndInsertData(String[] data, ComputerReportEntry computer,
			LinkedList<LicenceReportEntry> licences) {
		if (data.length < 6)
			return;

		String LicenceAttributeId = data[0];

		if (isLicence(LicenceAttributeId)) {
			if (getSoftwareName(data).toLowerCase().contains("microsoft internet explorer"))
				return;

			LicenceReportEntry licence = new LicenceReportEntry();
			licence.setHostName(computer.getHostName());
			licence.setHostSerial(computer.getSerialNumber());
			licence.setProductKey(getProductKey(data));
			licence.setSoftwareName(getSoftwareName(data));
			licences.add(licence);
		}
		return;
	}

	private static boolean isLicence(String LicenceAttributeId) {
		return LicenceAttributeId.toLowerCase().equals("licenses");
	}

	private static String getSoftwareName(String[] data) {
		return data[1];
	}

	private static String getProductKey(String[] data) {
		return data[5];
	}

}
