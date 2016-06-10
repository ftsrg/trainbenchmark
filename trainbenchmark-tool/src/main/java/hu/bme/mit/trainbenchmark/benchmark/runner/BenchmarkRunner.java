package hu.bme.mit.trainbenchmark.benchmark.runner;

import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;

public class BenchmarkRunner {

	public static int run(final BenchmarkConfigWrapper bcw, final String toolName) throws IOException {
		final File configFile = File.createTempFile("trainbenchmark-benchmark-", ".conf");
		final String configPath = configFile.getAbsolutePath();
		bcw.saveToFile(configPath);

		final String projectName = String.format("trainbenchmark-tool-%s", toolName);
		final String scriptCommand = String.format("../%s/build/install/%s/bin/%s %s", projectName, projectName,
				projectName, configPath);

		System.out.println(scriptCommand);
		final CommandLine cmdLine = CommandLine.parse(scriptCommand);
		final DefaultExecutor executor = new DefaultExecutor();
		final int exitValue = executor.execute(cmdLine);

		return exitValue;
	}

}
