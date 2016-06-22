package io;

//import java.util.LinkedHashSet;

public class Computer {
	// The below fields of computer store hardware attributes of a certain
	// computer
	String name;
	String chassisType;
	String type;
	String cpu;
	String ram;
	String mobo;
	String sn;
	String display;
	Integer externalDisplayCount;

	// The below two fields store the type of windows installed on the computer
	// and its licence
	String winType;
	String winKey;

	public Computer(String name, String chassisType, String type, String cpu, String ram, String mobo, String sn, String display, Integer externalDisplayCount,
			String winType, String winKey) {
		this.name = name;
		this.chassisType = chassisType;
		this.type = type;
		this.cpu = cpu;
		this.ram = ram;
		this.mobo = mobo;
		this.sn = sn;
		this.display = display;
		this.externalDisplayCount = externalDisplayCount;
		this.winType = winType;
		this.winKey = winKey;

	}

	public Computer() {
		this("N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", 0, "N/A", "N/A");
	}

	public String toString() {
		return toString(",");
	}

	public String toString(String separator) {
		return this.name + separator + this.chassisType + separator + this.type + separator + this.ram.replaceAll(" MB", "") + separator
				+ this.cpu + separator + this.mobo + separator + this.sn + separator + this.winType
				+ separator + this.winKey + separator + this.display + separator + this.externalDisplayCount;
	}
}
