package hu.bme.mit.trainbenchmark.benchmark.virtuoso;

import java.io.IOException;

import hu.bme.mit.trainbenchmark.benchmark.util.Util;

public class VirtuosoProcess {

	private static final String SCRIPT_DIRECTORY = "../hu.bme.mit.trainbenchmark.benchmark.virtuoso/scripts/";

	public static void clean() throws IOException, InterruptedException {
		final String commandStart = SCRIPT_DIRECTORY + "clean-virtuoso.sh";
		Util.executeCommand(commandStart, "Failed to clean Virtuoso database directory");
	}

	public static void startServer() throws IOException, InterruptedException {
		final String commandStart = SCRIPT_DIRECTORY + "start-virtuoso.sh";
		Util.executeCommand(commandStart, "Failed to start Virtuoso process");
	}

	public static void stopServer() throws IOException, InterruptedException {
		final String commandStart = SCRIPT_DIRECTORY + "stop-virtuoso.sh";
		Util.executeCommand(commandStart, "Failed to stop Virtuoso process");
	}

}
