package app;

import java.io.IOException;

import io.ReadWrite;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		ReadWrite.summarizeToCsvLists(Config.inputFileFolder, Config.outputFileFolder, Config.outputFileName);
	}

}
