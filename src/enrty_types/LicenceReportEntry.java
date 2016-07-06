package enrty_types;

public class LicenceReportEntry {

	String hostName;
	String softwareName;
	String productKey;

	public LicenceReportEntry(String hostName, String softwareName, String productKey) {
		super();
		this.hostName = hostName;
		this.softwareName = softwareName;
		this.productKey = productKey;
	}

	public LicenceReportEntry() {
		this("N/A", "N/A", "N/A");
	}

	public String toString() {
		return toString(config.Config.outputSeparator);
	}

	public String toString(String separator) {
		return this.hostName + separator + this.softwareName + separator + this.productKey + "\r\n";
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

}
