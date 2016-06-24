package app;

public class Display {

	String name;
	String model;
	String type;
	String manufacturingDate;
	String serialNumber;
	String sizeInInches;
	String resolution;
	String host;

	public Display(String name, String model, String type, String manDate, String sn, String size, String res,
			String host) {
		this.name = name;
		this.model = model;
		this.type = type;
		this.manufacturingDate = manDate;
		this.serialNumber = sn;
		this.sizeInInches = size;
		this.resolution = res;
		this.host = host;
	}

	public Display() {
		this("N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
	}

	public String toString() {
		return toString(", ");
	}

	public String toString(String separator) {
		return this.name + separator + this.model + separator + this.type + separator + this.manufacturingDate + separator
				+ this.serialNumber + separator + this.sizeInInches + separator + this.resolution + separator + this.host;
	}

	public String toStringPbh(String separator) {
		return this.host + separator + this.type + separator + this.name + separator + this.resolution + separator
				+ this.serialNumber + separator + this.manufacturingDate + separator + this.sizeInInches;
	}
}
