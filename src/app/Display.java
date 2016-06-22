package io;

class Display {

	String name;
	String model;
	String type;
	String manDate;
	String sn;
	String size;
	String res;
	String host;

	public Display(String name, String model, String type, String manDate, String sn, String size, String res,
			String host) {
		this.name = name;
		this.model = model;
		this.type = type;
		this.manDate = manDate;
		this.sn = sn;
		this.size = size;
		this.res = res;
		this.host = host;
	}

	Display() {
		this("N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
	}

	public String toString() {
		return toString(", ");
	}

	public String toString(String separator) {
		return this.name + separator + this.model + separator + this.type + separator + this.manDate + separator
				+ this.sn + separator + this.size + separator + this.res + separator + this.host;
	}

	public String toStringPbh(String separator) {
		return this.host + separator + this.type + separator + this.name + separator + this.res + separator
				+ this.sn + separator + this.manDate + separator + this.size;
	}
}
