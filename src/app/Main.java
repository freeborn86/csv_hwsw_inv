package app;

import java.io.IOException;
import java.text.ParseException;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {
		AidaOutputReader.readAidaOutputFiles();
		AidaReportFilter.filterReports();
		AidaReportWriter.writeReports();
	}
}
