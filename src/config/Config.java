package config;

public class Config {
	
	//test commit to master
	public static final String appVersion = "0.73b";
	public static final String outputFileName = "ARS";
	public static final String inputFileFolder = "aida_logs_input";
	public static final String outputFileFolder = "aida_logs_output";
	public static final String inputSeparator = ",";
	public static String outputSeparator;

	//BALU: system loc info is better
	public static String sList = WindowsRegistry.readRegistry("HKEY_CURRENT_USER\\Control Panel\\International",
			"sList");
	
	static {
		outputSeparator = (sList != null && !sList.equals("")) ? sList : ",";
	}

	public static boolean listDisplays = true;
	public static boolean listLicences = true;
}
