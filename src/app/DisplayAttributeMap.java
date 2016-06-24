package app;

import config.Config;

public class DisplayAttributeMap {
	
	/**
	String name;
	String model;
	String type;
	String manufacturingDate;
	String serialNumber;
	String sizeInInches;
	String resolution;
	String host;
	 */

	static Display checkLineAndInsertData(String line, Display display) {
		String[] data = line.split(Config.inputSeparator);
		if (! data[0].toLowerCase().equals("monitor") )
			return display;
		
		String attribute = data[4]; 
		
		return display;
	}
	
	static String getLastElementWithoutSemiColons(String [] data){
		return data[data.length - 1].replaceAll(";", "");
	}
	
	public static boolean isName(String aidaDisplayAttriubte) {
		return aidaDisplayAttriubte.toLowerCase().equals("monitor name"); 
	}
	
	public static boolean isModel (String aidaDisplayAttriubte){
		return aidaDisplayAttriubte.toLowerCase().equals("model");
	}
	
	public static boolean is (String aidaDisplayAttriubte){
		return aidaDisplayAttriubte.toLowerCase()
	}
	
}