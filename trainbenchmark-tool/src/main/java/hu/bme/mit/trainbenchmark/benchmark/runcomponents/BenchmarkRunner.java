package hu.bme.mit.trainbenchmark.benchmark.runcomponents;

import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;

public class BenchmarkRunner {

	public static int run(final BenchmarkConfigWrapper bcw) throws IOException, InterruptedException {
		System.out.println("Running benchmark: " + bcw.getToolName());
		System.out.println("Model: " + bcw.getBenchmarkConfig().getModelPath());
		System.out.println("Description: " + bcw.getDescription());

		final File configFile = File.createTempFile("trainbenchmark-benchmark-", ".conf");
		final String configPath = configFile.getAbsolutePath();
		bcw.saveToFile(configPath);

		final String projectName = String.format("trainbenchmark-tool-%s", bcw.getProjectName());
		final String jarPath = String.format("../%s/build/libs/%s-1.0.0-SNAPSHOT-fat.jar %s", projectName, projectName,
				configPath);

		final String javaCommand = String.format("java -Xms%s -Xmx%s -server -jar %s %s",
				bcw.getBenchmarkConfig().getXms(), bcw.getBenchmarkConfig().getXmx(), jarPath, configPath);
		final CommandLine cmdLine = CommandLine.parse(javaCommand);

		final long timeoutInSeconds = bcw.getBenchmarkConfig().getTimeout();
		final long timeoutInMilliseconds = timeoutInSeconds * 1000;
		final ExecuteWatchdog watchdog = new ExecuteWatchdog(timeoutInMilliseconds);
		final Executor bundle = new DefaultExecutor();
		bundle.setWatchdog(watchdog);

		try {
			final int exitValue = bundle.execute(cmdLine);
			System.out.println();
			return exitValue;
		} catch (ExecuteException e) {
			System.out.println("Process timed out.");
			return 143;
		}
	}

}
