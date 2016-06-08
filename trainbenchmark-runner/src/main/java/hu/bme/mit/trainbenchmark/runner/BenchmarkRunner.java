package hu.bme.mit.trainbenchmark.runner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;

public class BenchmarkRunner {

	public static void runBenchmark(final BenchmarkConfigWrapper bcw) throws IOException {
		final File configFile = File.createTempFile("trainbenchmark-", ".conf");
		final String configPath = configFile.getAbsolutePath();
		bcw.saveToFile(configPath);

		final String toolName = "emfapi";

		final String projectName = String.format("trainbenchmark-tool-%s", toolName);
		final String scriptCommand = String.format("../%s/build/install/%s/bin/%s %s", projectName, projectName, projectName, configPath);

		System.out.println(scriptCommand);
		final Process process = Runtime.getRuntime().exec(scriptCommand);

		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		while ((line = input.readLine()) != null) {
			System.out.println(line);
		}
		input.close();
	}

}
