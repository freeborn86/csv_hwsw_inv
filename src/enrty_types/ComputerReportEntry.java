package enrty_types;

public class ComputerReportEntry {
	// The below fields of computer store hardware attributes of a certain
	// computer
	String hostName;
	String date;
	String time;
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

	public ComputerReportEntry(String hostName, String date, String time, String chassisType, String model,
			String cpuType, String ramSize, String motherboardType, String serialNumber, String primaryDisplay,
			String microsoftOsType, String microsoftOsProductKey, Integer externalDisplayCount) {
		super();
		this.hostName = hostName;
		this.date = date;
		this.time = time;
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

	public ComputerReportEntry() {
		this("N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", 0);
	}

	public String toString() {
		return toString(config.Config.outputSeparator);
	}


	public String toString(String separator) {
		return this.date + separator + this.time + separator + this.hostName + separator + this.chassisType + separator
				+ this.model + separator + this.ramSize.replaceAll(" MB", "") + separator + this.cpuType + separator
				+ this.motherboardType + separator + this.serialNumber + separator + this.microsoftOsType + separator
				+ this.microsoftOsProductKey + separator + this.primaryDisplay + separator + this.externalDisplayCount
				+ "\r\n";
	}
	
	public String getHostName() {
		return hostName;
	}

	public Integer getExternalDisplayCount() {
		return externalDisplayCount;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setChassisType(String chassisType) {
		this.chassisType = chassisType;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setCpuType(String cpuType) {
		this.cpuType = cpuType;
	}

	public void setRamSize(String ramSize) {
		this.ramSize = ramSize;
	}

	public void setMotherboardType(String motherboardType) {
		this.motherboardType = motherboardType;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setPrimaryDisplay(String primaryDisplay) {
		this.primaryDisplay = primaryDisplay;
	}

	public void setMicrosoftOsType(String microsoftOsType) {
		this.microsoftOsType = microsoftOsType;
	}

	public void setMicrosoftOsProductKey(String microsoftOsProductKey) {
		this.microsoftOsProductKey = microsoftOsProductKey;
	}

	public void setExternalDisplayCount(Integer externalDisplayCount) {
		this.externalDisplayCount = externalDisplayCount;
	}

	
}
