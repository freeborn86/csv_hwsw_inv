package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import aida_mappings.ComputerAttributeMap;
import aida_mappings.DisplayAttributeMap;
import aida_mappings.LicenceAttributeMap;
import enrty_types.ComputerReportEntry;
import enrty_types.DisplayReportEntry;
import enrty_types.LicenceReportEntry;

public class AidaOutputReader {

	private static LinkedList<ComputerReportEntry> computers;
	private static LinkedList<DisplayReportEntry> displays;
	private static LinkedList<LicenceReportEntry> licences;

	public static void readAidaOutputFiles()
			throws IOException, InterruptedException {
		Path inputFolder = Paths.get(config.Config.outputFileFolder);
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(inputFolder)) {
			for (Path entry : stream) {
				app.AidaOutputReader.readCsv(entry.toString());
			}
		}
	}

	private static void readCsv(String csvFilePath) throws InterruptedException {

		ComputerReportEntry currentComputer = new ComputerReportEntry();
		DisplayReportEntry currentDisplay = null;

		BufferedReader br = null;
		String line = "";

		try {

			br = new BufferedReader(new FileReader(csvFilePath));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(config.Config.inputSeparator);

				ComputerAttributeMap.checkAndInsertData(data, currentComputer, computers);
				currentDisplay = DisplayAttributeMap.checkLineAndInsertData(data, currentComputer, currentDisplay,
						displays);
				LicenceAttributeMap.checkLineAndInsertData(data, currentComputer, licences);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static LinkedList<ComputerReportEntry> getComputers() {
		return computers;
	}

	public static LinkedList<DisplayReportEntry> getDisplays() {
		return displays;
	}

	public static LinkedList<LicenceReportEntry> getLicences() {
		return licences;
	}
}
