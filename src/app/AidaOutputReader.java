package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import config.Config;

public class AidaOutputReader {

	private LinkedList<Computer> computers;
	private LinkedList<Display> displays;
	private LinkedList<Licence> licences;

	public void readAidaOutputFiles(String inputFileFolder, String outputFileFolder, String outputFileName)
			throws IOException, InterruptedException {
		Path inputFolder = Paths.get(inputFileFolder);
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(inputFolder)) {
			for (Path entry : stream) {
				app.AidaOutputReader.readCsvs(entry.toString());
			}
		}
	}

	public static void readCsvs(String csvFilePath) throws InterruptedException {

		Computer currentComputer = new Computer();
		/*
		Display currentDisplay = new Display();
		boolean newDisp = false;
		String currentDate = "N/A";
		 */
		
		BufferedReader br = null;
		String line = "";

		try {

			br = new BufferedReader(new FileReader(csvFilePath));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(config.Config.inputSeparator);
				
				ComputerAttributeMap.checkAndInsertData(data, currentComputer);

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

	void displayAndLicenceHostBinding(String[] data, Computer currentComputer, Display currentDisplay,
			FileWriter licnsWriter, boolean newDisp) throws IOException {
		if (data[0].toLowerCase().equals("monitor")) {

			if (data[4].toLowerCase().equals("monitor name")) {
				currentDisplay = new Display();
				newDisp = true;
				currentDisplay.name = data[data.length - 1].replaceAll(";", "");
				currentDisplay.host = currentComputer.hostName;
				currentComputer.externalDisplayCount++;
			}

		}
		if (licnsWriter != null && (data[4].toLowerCase().equals("product key"))) {

			if (data[0].toLowerCase().equals("licenses")) {
				if (data[1].toLowerCase().contains("microsoft internet explorer")) {
					// ignore Internet Explorer
				} else {
					licnsWriter.append(currentComputer.hostName + Config.outputSeparator + data[1].replaceAll(";", "")
							+ Config.outputSeparator + data[data.length - 1].replaceAll(";", "") + "\r\n");
				}

			}
		}
	}

}
