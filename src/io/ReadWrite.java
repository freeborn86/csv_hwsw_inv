package io;

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

import app.Config;

public class ReadWrite {

	private static String inputSeparator = Config.inputSeparator;
	private static String outputSeparator = Config.outputSeparator;

	public static void readCsvsWriteAttributes(String csvFilePath, FileWriter compsWriter,
			FileWriter dispsWriter, FileWriter licnsWriter) throws InterruptedException {

		Computer currentComputer = new Computer();
		Display currentDisplay = new Display();
		boolean newDisp = false;
		String currentDate = "N/A";

		BufferedReader br = null;
		String line = "";

		try {

			br = new BufferedReader(new FileReader(csvFilePath));
			while ((line = br.readLine()) != null) {

				String[] data = line.split(inputSeparator);

				if (data[3].equals("261")) {
					// computer name
					currentComputer.name = data[data.length - 1].replaceAll(";", "");
				}

				if (data[3].equals("264")) {
					currentDate = data[data.length - 1].replaceAll(";", "");
				}

				if (data[3].equals("513")) {
					// windows type
					currentComputer.winType = data[data.length - 1].replaceAll(";", "");
				}

				if (data[3].equals("517")) {
					// cpu
					currentComputer.cpu = data[data.length - 1].replaceAll(";", "");
				}

				if (data[3].equals("518")) {
					// motherboard
					currentComputer.mobo = data[data.length - 1].replaceAll(";", "");
				}

				if (data[3].equals("520")) {
					// memory amount
					currentComputer.ram = data[data.length - 1].replaceAll(";", "");
				}

				if (data[3].equals("525")) {
					// primary display
					currentComputer.display = data[data.length - 1].replaceAll(";", "");
				}

				if (data[3].equals("551")) {
					// comp type
					currentComputer.type = data[data.length - 1].replaceAll(";", "");
				}

				if (data[3].equals("553")) {
					// computer sn
					currentComputer.sn = data[data.length - 1].replaceAll(";", "");
				}
				
				if (data[3].equals("563")) {
					// computer chassis type
					currentComputer.chassisType = data[data.length - 1].replaceAll(";", "");
				}

				if (data[3].equals("3853")) {
					// win key
					currentComputer.winKey = data[data.length - 1].replaceAll(";", "");
				}

				if ((data[0].toLowerCase().matches("k.perny.") || data[0].toLowerCase().equals("monitor"))) {

					if ((data[4].toLowerCase().matches("k.perny. neve")
							|| data[4].toLowerCase().equals("monitor name"))) {
						currentDisplay = new Display();
						newDisp = true;
						currentDisplay.name = data[data.length - 1].replaceAll(";", "");
						currentDisplay.host = currentComputer.name;
						currentComputer.externalDisplayCount++;
					}

					if (data[4].toLowerCase().contains("model")) {

						currentDisplay.model = data[data.length - 1].replaceAll(";", "");
					}

					if ((data[4].toLowerCase().matches("k.perny. t.pusa")
							|| data[4].toLowerCase().contains("monitor typ"))) {
						currentDisplay.type = data[data.length - 1].replaceAll(";", "");
					}

					if (data[4].toLowerCase().matches("gy.rt.s ideje")
							|| data[4].toLowerCase().equals("herstellungsdatum")
							|| data[4].toLowerCase().equals("serial number")) {
						currentDisplay.manDate = data[data.length - 1].replaceAll(";", "");
					}

					if (data[4].toLowerCase().matches("sorozatsz.m")
							|| data[4].toLowerCase().equals("seriennummer")
							|| data[4].toLowerCase().equals("serial number")) {
						// TODO: implement same SN based duplicate display
						// filtering
						currentDisplay.sn = data[data.length - 1].replaceAll(";", "");
					}

					if (data[4].toLowerCase().matches("maxim.lis l.that. kijelz. m.ret")
							|| data[4].toLowerCase().contains("max. visible display size")
							|| data[4].toLowerCase().matches("maximale sichtbare bildschirmgr.ße")) {
						currentDisplay.size = data[data.length - 1].replaceAll(";", "");
					}

					if ((data[4].toLowerCase().matches("maxim.lis felbont.s")
							|| data[4].toLowerCase().contains("maximum resolution")
							|| data[4].toLowerCase().matches("maximale aufl.sung"))) {
						currentDisplay.res = data[data.length - 1].replaceAll(";", "");
					}

					if (data[2].toLowerCase().matches("t.mogatott megjelen.t.si m.dok")
							|| data[2].toLowerCase().contains("supported video modes")
							|| data[2].toLowerCase().matches("unterst.tzte videomodi")) {
						if (newDisp == true && Config.listDisplays == true) {
							dispsWriter.append(currentDisplay.toStringPbh(outputSeparator) + "\r\n");
							newDisp = false;
						}
					}
				}

				if (licnsWriter != null && (data[4].toLowerCase().matches("term.kkulcs")
						|| (data[4].toLowerCase().matches("produktschl.ssel"))
						|| data[4].toLowerCase().equals("product key"))) {

					if ((data[0].toLowerCase().contains("licen")
							|| data[0].toLowerCase().equals("lizenzen"))) {
						if (data[1].toLowerCase().contains("microsoft internet explorer")) {
							// ignore Internet Explorer
						} else {
							licnsWriter.append(currentComputer.name + outputSeparator
									+ data[1].replaceAll(";", "") + outputSeparator
									+ data[data.length - 1].replaceAll(";", "") + "\r\n");
						}

					}
				}
			}
			//current change
			compsWriter.append(
					currentDate + outputSeparator + currentComputer.toString(outputSeparator) + "\r\n");
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

	public static void summarizeToCsvLists(String inputFileFolder, String outputFileFolder,
			String outputFileName) throws IOException, InterruptedException {

		Path inputFolder = Paths.get(inputFileFolder);
		Path outputFolder = Paths.get(outputFileFolder);
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HHmm.ss").format(new Date());

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(inputFolder)) {

			FileWriter compsWriter = new FileWriter(outputFolder + "\\" + outputFileName + "_"
					+ Config.appVersion + "_" + timeStamp + "_COMPS.csv");
			compsWriter.append("Report Date" + outputSeparator + "Name" + outputSeparator + "Chassis Type" + outputSeparator + "Type"
					+ outputSeparator + "Memory" + outputSeparator + "CPU" + outputSeparator + "Motherboard"
					+ outputSeparator + "Serial" + outputSeparator + "Windows Type" + outputSeparator
					+ "Windows Product Key" + outputSeparator + "Primary Display" + outputSeparator
					+ "Displays\r\n");

			FileWriter dispsWriter = null;
			if (Config.listDisplays == true) {
				dispsWriter = new FileWriter(outputFolder + "\\" + outputFileName + "_" + Config.appVersion
						+ "_" + timeStamp + "_DISPS.csv");
				dispsWriter.append("Host" + outputSeparator + "Type" + outputSeparator + "Name"
						+ outputSeparator + "Resolution" + outputSeparator + "Serial Number" + outputSeparator
						+ "Manufactured" + outputSeparator + "Size" + "\r\n");
			}

			FileWriter licnsWriter = null;
			if (Config.listLicences == true) {

				licnsWriter = new FileWriter(outputFolder + "\\" + outputFileName + "_" + Config.appVersion
						+ "_" + timeStamp + "_LICNS.csv");
				licnsWriter.append("Used by (Host)" + outputSeparator + "Software Name" + outputSeparator
						+ "Product Key" /* + outputSeparator + "Use count" */ + "\r\n");

				// TODO: implement licence use count
			}

			for (Path entry : stream) {
				// compsWriter.append(entry + outputSeparator);
				readCsvsWriteAttributes(entry.toString(), compsWriter, dispsWriter, licnsWriter);
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
