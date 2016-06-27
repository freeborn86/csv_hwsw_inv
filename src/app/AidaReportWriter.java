package app;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import config.Config;

public class AidaReportWriter {
	
	public static void summarizeToCsvLists(String inputFileFolder, String outputFileFolder, String outputFileName)
			throws IOException, InterruptedException {

		Path inputFolder = Paths.get(inputFileFolder);
		Path outputFolder = Paths.get(outputFileFolder);
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HHmm.ss").format(new Date());

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(inputFolder)) {

			FileWriter compsWriter = new FileWriter(
					outputFolder + "\\" + outputFileName + "_" + Config.appVersion + "_" + timeStamp + "_COMPS.csv");
			compsWriter.append("Report Date" + config.Config.outputSeparator + "Name" + config.Config.outputSeparator + "Chassis Type"
					+ config.Config.outputSeparator + "Type" + config.Config.outputSeparator + "Memory" + config.Config.outputSeparator + "CPU" + config.Config.outputSeparator
					+ "Motherboard" + config.Config.outputSeparator + "Serial" + config.Config.outputSeparator + "Windows Type" + config.Config.outputSeparator
					+ "Windows Product Key" + config.Config.outputSeparator + "Primary Display" + config.Config.outputSeparator + "Displays\r\n");

			FileWriter dispsWriter = null;
			if (Config.listDisplays == true) {
				dispsWriter = new FileWriter(outputFolder + "\\" + outputFileName + "_" + Config.appVersion + "_"
						+ timeStamp + "_DISPS.csv");
				dispsWriter.append("Host" + config.Config.outputSeparator + "Type" + config.Config.outputSeparator + "Name" + config.Config.outputSeparator
						+ "Resolution" + config.Config.outputSeparator + "Serial Number" + config.Config.outputSeparator + "Manufactured"
						+ config.Config.outputSeparator + "Size" + "\r\n");
			}

			FileWriter licnsWriter = null;
			if (Config.listLicences == true) {

				licnsWriter = new FileWriter(outputFolder + "\\" + outputFileName + "_" + Config.appVersion + "_"
						+ timeStamp + "_LICNS.csv");
				licnsWriter.append("Used by (Host)" + config.Config.outputSeparator + "Software Name" + config.Config.outputSeparator
						+ "Product Key" /* + outputSeparator + "Use count" */ + "\r\n");

				// TODO: implement licence use count
			}

			for (Path entry : stream) {
				// compsWriter.append(entry + outputSeparator);
				app.AidaOutputReader.readCsvsWriteAttributes(entry.toString(), compsWriter, dispsWriter, licnsWriter);
			}
			if (compsWriter != null) {
				compsWriter.flush();
				compsWriter.close();
			}
			if (dispsWriter != null) {
				dispsWriter.flush();
				dispsWriter.close();
			}

			if (licnsWriter != null) {
				licnsWriter.flush();
				licnsWriter.close();
			}

		}
	}


}
