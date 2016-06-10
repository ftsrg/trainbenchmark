package hu.bme.mit.trainbenchmark.generator.runner;

import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigWrapper;

public class GeneratorRunner {

	public static int runBenchmark(final GeneratorConfigWrapper gcw, final String generatorName) throws IOException {
		final File configFile = File.createTempFile("trainbenchmark-generator-", ".conf");
		final String configPath = configFile.getAbsolutePath();
		gcw.saveToFile(configPath);

		final String projectName = String.format("trainbenchmark-generator-%s", generatorName);
		final String scriptCommand = String.format("../%s/build/install/%s/bin/%s %s", projectName, projectName,
				projectName, configPath);

		System.out.println(scriptCommand);
		final CommandLine cmdLine = CommandLine.parse(scriptCommand);
		final DefaultExecutor executor = new DefaultExecutor();
		final int exitValue = executor.execute(cmdLine);

		return exitValue;
	}

}
