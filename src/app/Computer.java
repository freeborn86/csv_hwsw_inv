package app;

public class Computer {
	// The below fields of computer store hardware attributes of a certain
	// computer
	String hostName;
	String chassisType;
	String model;
	String cpuType;
	String ramSize;
	String motherboardType;
	String serialNumber;
	String primaryDisplay;

	String microsoftOsType;
	String microsoftOsProductKey;

	Integer externalDisplayCount;

	public Computer(String hostName, String chassisType, String model, String cpuType, String ramSize,
			String motherboardType, String serialNumber, String primaryDisplay, String microsoftOsType,
			String microsoftOsProductKey, Integer externalDisplayCount) {
		this.hostName = hostName;
		this.chassisType = chassisType;
		this.model = model;
		this.cpuType = cpuType;
		this.ramSize = ramSize;
		this.motherboardType = motherboardType;
		this.serialNumber = serialNumber;
		this.primaryDisplay = primaryDisplay;
		this.microsoftOsType = microsoftOsType;
		this.microsoftOsProductKey = microsoftOsProductKey;
		this.externalDisplayCount = externalDisplayCount;
	}

	public Computer() {
		this("N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", 0);
	}

	public String toString() {
		return toString(",");
	}

	public String toString(String separator) {
		return this.hostName + separator + this.chassisType + separator + this.model + separator
				+ this.ramSize.replaceAll(" MB", "") + separator + this.cpuType + separator + this.motherboardType
				+ separator + this.serialNumber + separator + this.microsoftOsType + separator
				+ this.microsoftOsProductKey + separator + this.primaryDisplay + separator + this.externalDisplayCount;
	}
}
