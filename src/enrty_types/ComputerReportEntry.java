package enrty_types;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class ComputerReportEntry implements HasHostIdForFiltering {
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

	LinkedList<DisplayReportEntry> displaysOfHost;

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
		this.displaysOfHost = new LinkedList<>();
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

	public boolean newer(ComputerReportEntry other) throws ParseException {
		return this.getReportDate().after(other.getReportDate());
	}

	public Date getReportDate() throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.parse(this.date + " " + this.time);
	}

	public LinkedList<DisplayReportEntry> getDisplaysOfHost() {
		return displaysOfHost;
	}

	public void addDisplay(DisplayReportEntry displayToAdd) {
		if (isDisplayAdded(displayToAdd)) {
			return;
		}
		displaysOfHost.add(displayToAdd);
	}

	public boolean isDisplayAdded(DisplayReportEntry display) {
		return isDisplayIdenticalToAlreadyAdded(this.displaysOfHost, display);
	}

	boolean isDisplayIdenticalToAlreadyAdded(LinkedList<DisplayReportEntry> displays,
			DisplayReportEntry displayEntyToCheck) {
		for (DisplayReportEntry displayEntryOfList : displays) {
			if (displayEntyToCheck.equals(displayEntryOfList))
				return true;
		}
		return false;
	}

	@Override
	public String getSerialNumberForFiltering() {
		return getSerialNumber();
	}

	@Override
	public String getHostNameForFiltering() {
		return getHostName();
	}

	public String getHostName() {
		return hostName;
	}

	public Integer getExternalDisplayCount() {
		return externalDisplayCount;
	}

	public String getSerialNumber() {
		return serialNumber;
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
