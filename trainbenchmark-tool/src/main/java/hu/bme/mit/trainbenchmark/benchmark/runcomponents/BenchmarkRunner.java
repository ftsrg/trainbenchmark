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

public class BenchmarkRunner {

	public static int run(final BenchmarkConfig bc) throws IOException, InterruptedException {
		System.out.println("Running benchmark: " + bc.getToolName());
		System.out.println("Model: " + bc.getConfigBase().getModelPath());
		System.out.println("Description: " + bc.getDescription());

		final File configFile = File.createTempFile("trainbenchmark-benchmark-", ".conf");
		final String configPath = configFile.getAbsolutePath();
		bc.saveToFile(configPath);

		final String projectName = String.format("trainbenchmark-tool-%s", bc.getProjectName());
		final String jarPath = String.format("../%s/build/libs/%s-1.0.0-SNAPSHOT-fat.jar %s", projectName, projectName, configPath);

		final String javaCommand = String.format("java -Xms%s -Xmx%s -server -jar %s %s", bc.getConfigBase().getXms(), bc.getConfigBase().getXmx(),
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

}
