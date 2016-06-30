package app;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		AidaOutputReader.readAidaOutputFiles();
		AidaReportWriter.writeReports();
	}

}
