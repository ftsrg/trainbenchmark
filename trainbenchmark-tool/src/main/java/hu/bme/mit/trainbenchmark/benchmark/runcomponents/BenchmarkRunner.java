package hu.bme.mit.trainbenchmark.benchmark.runcomponents;

import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.config.ExecutionConfig;

public class BenchmarkRunner {

	public static int runPerformanceBenchmark(final BenchmarkConfig bc, final ExecutionConfig ec)
			throws IOException, InterruptedException {
		System.out.println("Running benchmark: " + bc.getToolName());
		System.out.println("Model: " + bc.getConfigBase().getModelPath());
		System.out.println("Description: " + bc.getDescription());
		System.out.println("Execution configuration: " + ec);

		final File configFile = File.createTempFile("trainbenchmark-benchmark-", ".conf");
		final String configPath = configFile.getAbsolutePath();
		bc.saveToFile(configPath);

		final String projectName = String.format("trainbenchmark-tool-%s", bc.getProjectName());
		final String jarPath = String.format("../%s/build/libs/%s-1.0.0-SNAPSHOT-fat.jar %s", projectName, projectName,
				configPath);

		final String javaCommand = String.format("java -Xms%s -Xmx%s -server -jar %s %s", ec.getXms(), ec.getXmx(),
				jarPath, configPath);
		final CommandLine cmdLine = CommandLine.parse(javaCommand);

		final long timeoutInSeconds = bc.getConfigBase().getTimeout();
		final long timeoutInMilliseconds = timeoutInSeconds * 1000;
		final ExecuteWatchdog watchdog = new ExecuteWatchdog(timeoutInMilliseconds);
		final Executor executor = new DefaultExecutor();
		executor.setWatchdog(watchdog);
		executor.setStreamHandler(new PumpStreamHandler());
		try {
			final int exitValue = executor.execute(cmdLine);
			System.out.println();
			return exitValue;
		} catch (final ExecuteException e) {
			e.printStackTrace();
			return 143;
		}
	}

	public static int runMemoryBenchmark(final BenchmarkConfig bc, final ExecutionConfig defaultEc,
			final int numberOfSteps) throws IOException, InterruptedException {
		// e.g. initalMaxMemory = 12800, we save this (as a final variable), so
		// that we will not exceed it
		//
		// the memoryQuantum is halved on the start of each loop, so this starts
		// from 12800 as well (and will go down to 6400)
		final int initialMaxMemory = defaultEc.getMaxMemory();
		int currentMaxMemory = initialMaxMemory;
		int memoryQuantum = initialMaxMemory;
		int step = 0;

		while (step < numberOfSteps && currentMaxMemory <= initialMaxMemory) {
			step++;
			memoryQuantum /= 2;

			final ExecutionConfig ec = new ExecutionConfig(currentMaxMemory, currentMaxMemory);
			if (runPerformanceBenchmark(bc, ec) == 0) {
				System.out.println("Execution finished, testing with less memory.");
				System.out.println();
				currentMaxMemory -= memoryQuantum;
			} else {
				System.out.println("Execution failed, testing with more memory.");
				System.out.println();
				currentMaxMemory += memoryQuantum;
			}
		}

		if (currentMaxMemory > initialMaxMemory) {
			System.out.println(
					"The first execution timed out or errored. Skipping larger sizes for this tool, scenario and query mix.");
			return -1;
		}
		System.out.println("Execution succeeded, estimated memory requirement: " + currentMaxMemory);
		System.out.println();

		return 0;
	}

}
