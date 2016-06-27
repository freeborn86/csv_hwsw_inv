package app;

public class Licence {
	
	String hostName;
	String softwareName;
	String productKey;

	public Licence(String hostName, String softwareName, String productKey) {
		super();
		this.hostName = hostName;
		this.softwareName = softwareName;
		this.productKey = productKey;
	}

	public Licence() {
		this( "N/A", "N/A", "N/A");
	}

	public String toString() {
		return toString(", ");
	}

	public String toString(String separator) {
		return this.hostName + separator + this.softwareName + separator + this.productKey;
	}
}
