package enrty_types;

public class DisplayReportEntry {

	String name;
	String model;
	String type;
	String manufacturingDate;
	String serialNumber;
	String sizeInInches;
	String resolution;
	String host;

	public DisplayReportEntry(String name, String model, String type, String manDate, String sn, String size,
			String res, String host) {
		this.name = name;
		this.model = model;
		this.type = type;
		this.manufacturingDate = manDate;
		this.serialNumber = sn;
		this.sizeInInches = size;
		this.resolution = res;
		this.host = host;
	}

	public DisplayReportEntry() {
		this("N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
	}

	public String toString() {
		return toString(config.Config.outputSeparator);
	}

	public String toString(String separator) {
		return this.host + separator + this.type + separator + this.name + separator + this.resolution + separator
				+ this.serialNumber + separator + this.manufacturingDate + separator + this.sizeInInches + "\r\n";
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
}
