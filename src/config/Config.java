package config;

public class Config {

	private static boolean automaticSeparator = false;

	private static String sList = null;
	{
		// BALU: system loc info is better
		if (automaticSeparator == true) {
			sList = WindowsRegistry.readRegistry("HKEY_CURRENT_USER\\Control Panel\\International", "sList");
		}
	}

	public static String outputSeparator = (sList != null && !sList.equals("")) ? sList : ",";

	private static final String appVersion = "0.74";
	public static final String outputFilePrefix = "ARS_" + appVersion + "_";

	public static final String inputFileFolder = "aida_logs_input";
	public static final String outputFileFolder = "aida_logs_output";

	public static final String inputSeparator = ",";
	public static String commonHeader = "sep=" + outputSeparator + "\r\n";
}
