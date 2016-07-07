package enrty_types;

public class LicenceReportEntry implements HasHostIdForFiltering {

	String hostName;
	String hostSerial;
	String softwareName;
	String productKey;

	public LicenceReportEntry(String hostName, String hostSerial, String softwareName, String productKey) {
		super();
		this.hostName = hostName;
		this.hostSerial = hostSerial;
		this.softwareName = softwareName;
		this.productKey = productKey;
	}

	public LicenceReportEntry() {
		this("N/A", "N/A", "N/A", "N/A");
	}

	public String toString() {
		return toString(config.Config.outputSeparator);
	}

	public String toString(String separator) {
		return this.hostName + separator + this.softwareName + separator + this.productKey + "\r\n";
	}

	@Override
	public String getSerialNumberForFiltering() {
		return getHostSerial();
	}

	public String getHostNameForFiltering() {
		return getHostName();
	}

	public String getHostName() {
		return hostName;
	}

	public String getHostSerial() {
		return hostSerial;
	}

	public void setHostSerial(String hostSerial) {
		this.hostSerial = hostSerial;
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
