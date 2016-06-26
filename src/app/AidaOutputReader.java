package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import config.Config;

public class AidaOutputReader {

	private LinkedList<Computer> computers;
	private LinkedList<Display> displays;
	private LinkedList<Licence> licences;

	public static void readCsvsWriteAttributes(String csvFilePath, FileWriter compsWriter, FileWriter dispsWriter,
			FileWriter licnsWriter) throws InterruptedException {

		Computer currentComputer = new Computer();
		Display currentDisplay = new Display();
		boolean newDisp = false;
		String currentDate = "N/A";

		BufferedReader br = null;
		String line = "";

		try {

			br = new BufferedReader(new FileReader(csvFilePath));
			while ((line = br.readLine()) != null) {

				String[] data = line.split(config.Config.inputSeparator);

				if ((data[0].toLowerCase().matches("k.perny.") || data[0].toLowerCase().equals("monitor"))) {

					if ((data[4].toLowerCase().matches("k.perny. neve")
							|| data[4].toLowerCase().equals("monitor name"))) {
						currentDisplay = new Display();
						newDisp = true;
						currentDisplay.name = data[data.length - 1].replaceAll(";", "");
						currentDisplay.host = currentComputer.hostName;
						currentComputer.externalDisplayCount++;
					}

					
					}
				}

				if (licnsWriter != null && (data[4].toLowerCase().matches("term.kkulcs")
						|| (data[4].toLowerCase().matches("produktschl.ssel"))
						|| data[4].toLowerCase().equals("product key"))) {

					if ((data[0].toLowerCase().contains("licen") || data[0].toLowerCase().equals("lizenzen"))) {
						if (data[1].toLowerCase().contains("microsoft internet explorer")) {
							// ignore Internet Explorer
						} else {
							licnsWriter.append(currentComputer.hostName + Config.outputSeparator + data[1].replaceAll(";", "")
									+ Config.outputSeparator + data[data.length - 1].replaceAll(";", "") + "\r\n");
						}

					}
				}
			}
			// current change on comp-dupe-filter branch
			compsWriter.append(currentDate + Config.outputSeparator + currentComputer.toString(Config.outputSeparator) + "\r\n");
		}catch(

	FileNotFoundException e)
	{
		e.printStackTrace();
	}catch(
	IOException e)
	{
		e.printStackTrace();
	}finally
	{
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

}
