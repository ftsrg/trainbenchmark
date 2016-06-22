package hu.bme.mit.trainbenchmark.benchmark.runner;

import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;

public class BenchmarkRunner {

	public static int run(final BenchmarkConfigWrapper bcw) throws IOException, InterruptedException {
		System.out.println("Running benchmark: " + bcw.getToolName());
		System.out.println("Model: " + bcw.getBenchmarkConfig().getModelPath());
		System.out.println("Desc: " + bcw.getDescription());
		
		final File configFile = File.createTempFile("trainbenchmark-benchmark-", ".conf");
		final String configPath = configFile.getAbsolutePath();
		bcw.saveToFile(configPath);

		final String projectName = String.format("trainbenchmark-tool-%s", bcw.getProjectName());
		final String jarPath = String.format("../%s/build/libs/%s-1.0.0-SNAPSHOT-fat.jar %s", projectName,
				projectName, configPath);

		final String javaCommand = String.format("java -Xms%s -Xmx%s -server -jar %s %s",
				bcw.getBenchmarkConfig().getXms(), bcw.getBenchmarkConfig().getXmx(), jarPath, configPath);
		final CommandLine cmdLine = CommandLine.parse(javaCommand);
		final DefaultExecutor executor = new DefaultExecutor();
		final int exitValue = executor.execute(cmdLine);

		System.out.println();
		
		return exitValue;
	}

}
