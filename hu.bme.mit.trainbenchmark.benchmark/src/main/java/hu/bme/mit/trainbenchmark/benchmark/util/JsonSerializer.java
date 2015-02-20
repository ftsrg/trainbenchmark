package hu.bme.mit.trainbenchmark.benchmark.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonSerializer {

	public static void serialize(final BenchmarkResult bmr) throws IOException {
		final ObjectMapper mapper = new ObjectMapper();
		// to enable standard indentation ("pretty-printing"):
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		// to allow serialization of "empty" POJOs (no properties to serialize)
		// (without this setting, an exception is thrown in those cases)
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

		final String scenario = bmr.getScenario();
		final String query = bmr.getQuery();
		final String tool = bmr.getTool();
		final int size = bmr.getArtifactSize();
		final int series = bmr.getRunIndex();
		final String fileName = tool + "-" + scenario + "-" + query + "-Size" + size + "-Index" + series;
		mapper.writeValue(new File(bmr.getBenchmarkConfig().getWorkspacePath() + "/results/" + fileName + ".json"), bmr);

		System.out.println("Create JSON file: results/" + fileName);
	}
}
