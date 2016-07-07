package enrty_types;

public class DisplayReportEntry implements HasHostIdForFiltering {

	String name;
	String model;
	String type;
	String manufacturingDate;
	String serialNumber;
	String sizeInInches;
	String resolution;
	String host;
	String hostSerial;
	boolean enrtyAdded;

	public DisplayReportEntry(String name, String model, String type, String manDate, String sn, String size,
			String res, String host, String hostSerial, boolean entryAdded) {
		this.name = name;
		this.model = model;
		this.type = type;
		this.manufacturingDate = manDate;
		this.serialNumber = sn;
		this.sizeInInches = size;
		this.resolution = res;
		this.host = host;
		this.hostSerial = hostSerial;
		this.enrtyAdded = entryAdded;
	}

	public DisplayReportEntry() {
		this("N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", false);
	}

	public String toString() {
		return toString(config.Config.outputSeparator);
	}

	public String toString(String separator) {
		return this.host + separator + this.type + separator + this.name + separator + this.resolution + separator
				+ this.serialNumber + separator + this.manufacturingDate + separator + this.sizeInInches + "\r\n";
	}

	public boolean equals(DisplayReportEntry other) {
		if (!other.host.equals(this.host))
			return false;
		if (!other.manufacturingDate.equals(this.manufacturingDate))
			return false;
		if (!other.model.equals(this.model))
			return false;
		if (!other.name.equals(this.name))
			return false;
		if (!other.resolution.equals(this.resolution))
			return false;
		if (!other.serialNumber.equals(this.serialNumber))
			return false;
		if (!other.sizeInInches.equals(this.sizeInInches))
			return false;
		if (!other.type.equals(this.type))
			return false;
		return true;
	}

	@Override
	public String getSerialNumberForFiltering() {
		return getHostSerial();
	}

	public String getHostNameForFiltering() {
		return getHost();
	}

	public String getHost() {
		return host;
	}

	public String getHostSerial() {
		return hostSerial;
	}

	public void setHostSerial(String hostSerial) {
		this.hostSerial = hostSerial;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setManufacturingDate(String manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setSizeInInches(String sizeInInches) {
		this.sizeInInches = sizeInInches;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setEnrtyAdded(boolean enrtyAdded) {
		this.enrtyAdded = enrtyAdded;
	}

	public boolean isEnrtyAdded() {
		return enrtyAdded;
	}

}
