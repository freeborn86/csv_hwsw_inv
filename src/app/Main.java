package app;

import java.io.IOException;

import config.Config;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		OBSOLETE_ReadWrite.summarizeToCsvLists(Config.inputFileFolder, Config.outputFileFolder, Config.outputFileName);
	}

}
