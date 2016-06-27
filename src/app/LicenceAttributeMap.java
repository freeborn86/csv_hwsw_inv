package app;

import java.util.LinkedList;

public class LicenceAttributeMap {

	static void checkLineAndInsertData(String[] data, Computer computer, LinkedList<Licence> licences) {
		if (data.length < 6)
			return;

		String LicenceAttributeId = data[0];

		// Since the Aida report is sequential finding a monitor name signifies
		// a new display
		if (isLicence(LicenceAttributeId)) {
			Licence licence = new Licence(computer.hostName, getSoftwareName(data), getProductKey(data));
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
