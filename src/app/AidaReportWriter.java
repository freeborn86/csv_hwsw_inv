package app;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import config.Config;

public class AidaReportWriter {

	public static void writeReports() throws IOException {
		writeComputerReport();
		writeDisplayReport();
		writeLicenceReport();
		writeOutdatedReport();
	}

	private static void writeComputerReport() throws IOException {
		FileWriter computersWriter = new FileWriter(computerReportPath);
		computersWriter.append(computerReportHeader);
		writeComputerReportBody(computersWriter);
		safeClose(computersWriter);
	}

	private static void writeOutdatedReport() throws IOException {
		FileWriter outdatedReport = new FileWriter(outdatedReportPath);
		outdatedReport.append(computerReportHeader);
		writeOutDatedReportBody(outdatedReport);
		safeClose(outdatedReport);
	}

	private static void writeDisplayReport() throws IOException {
		FileWriter displaysWriter = new FileWriter(displayReportPath);
		displaysWriter.append(displayReportHeader);
		writeDisplayReportBody(displaysWriter);
		safeClose(displaysWriter);
	}

	private static void writeLicenceReport() throws IOException {
		FileWriter licencesWriter = new FileWriter(licenceReportPath);
		licencesWriter.append(licenceReportHeader);
		writeLicenceReportBody(licencesWriter);
		safeClose(licencesWriter);
	}

	private static String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HHmm.ss").format(new Date());
	private static String reportPath = Paths.get(Config.outputFileFolder) + "\\" + Paths.get(Config.outputFilePrefix)
			+ timeStamp;

	private static String computerReportPath = reportPath + "_COMPS.csv";
	private static String outdatedReportPath = reportPath + "_OUTDT.csv";
	private static String displayReportPath = reportPath + "_DISPS.csv";
	private static String licenceReportPath = reportPath + "_LICNS.csv";

	// TODO: smarter automatic generation / inclusion of fields from classes
	private static String computerReportHeader = config.Config.commonHeader + "Report Date"
			+ config.Config.outputSeparator + "Report Time" + config.Config.outputSeparator + "Name"
			+ config.Config.outputSeparator + "Chassis Type" + config.Config.outputSeparator + "Type"
			+ config.Config.outputSeparator + "Memory" + config.Config.outputSeparator + "CPU"
			+ config.Config.outputSeparator + "Motherboard" + config.Config.outputSeparator + "Serial"
			+ config.Config.outputSeparator + "Windows Type" + config.Config.outputSeparator + "Windows Product Key"
			+ config.Config.outputSeparator + "Primary Display" + config.Config.outputSeparator + "Displays\r\n";

	private static String displayReportHeader = config.Config.commonHeader + "Host" + config.Config.outputSeparator
			+ "Type" + config.Config.outputSeparator + "Name" + config.Config.outputSeparator + "Resolution"
			+ config.Config.outputSeparator + "Serial Number" + config.Config.outputSeparator + "Manufactured"
			+ config.Config.outputSeparator + "Size" + "\r\n";

	private static String licenceReportHeader = config.Config.commonHeader + "Used by (Host)"
			+ config.Config.outputSeparator + "Software Name" + config.Config.outputSeparator + "Product Key" + "\r\n";

	private static void writeComputerReportBody(FileWriter fw) throws IOException {
		writeReportBody(fw, AidaReportFilter.getFilteredComputers());
	}

	private static void writeDisplayReportBody(FileWriter fw) throws IOException {
		writeReportBody(fw, AidaReportFilter.getFilteredDisplays());
	}

	private static void writeLicenceReportBody(FileWriter fw) throws IOException {
		writeReportBody(fw, AidaReportFilter.getFilteredLicences());
	}

	private static void writeOutDatedReportBody(FileWriter fw) throws IOException {
		writeReportBody(fw, AidaReportFilter.getOutdatedComputerEntries());
	}

	private static <E> void writeReportBody(FileWriter fw, LinkedList<E> reportBody) throws IOException {
		for (E element : reportBody) {
			fw.append(element.toString());
		}
	}

	private static void safeClose(FileWriter fw) throws IOException {
		if (fw != null) {
			fw.flush();
			fw.close();
		}
	}
}
