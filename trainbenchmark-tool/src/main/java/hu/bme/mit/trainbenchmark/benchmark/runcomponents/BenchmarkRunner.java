package hu.bme.mit.trainbenchmark.benchmark.runcomponents;

import com.google.common.base.Joiner;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.config.ExecutionConfig;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.File;
import java.io.IOException;

public class BenchmarkRunner {

	public static int runPerformanceBenchmark(final BenchmarkConfig bc, final ExecutionConfig ec)
			throws IOException, InterruptedException {
		final Joiner joiner = Joiner.on(", ");
		System.out.println("Running benchmark.");
		System.out.println("Workload: " + bc.getConfigBase().getWorkload());
		System.out.println("Tool: " + bc.getToolName());
		System.out.println("Model: " + bc.getConfigBase().getModelPath());
		System.out.println("Description: " + bc.getDescription());
		System.out.println("Operations: [" + joiner.join(bc.getConfigBase().getOperations()) + "]");
		System.out.println("Execution configuration: " + ec);
		System.out.println("Runs: " + bc.getConfigBase().getRuns());

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
			if (watchdog.killedProcess()) {
				System.out.println("Process timed out.");
			} else {
				e.printStackTrace(System.out);
			}
			return e.getExitValue();
		}
	}

}
