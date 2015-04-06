package hu.bme.mit.trainbenchmark.benchmark.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ResultSerializer {

	public static void serializeToJSON(final BenchmarkResult bmr) throws IOException {
		final ObjectMapper mapper = new ObjectMapper();
		// to enable standard indentation ("pretty-printing"):
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		// to allow serialization of "empty" POJOs (no properties to serialize)
		// (without this setting, an exception is thrown in those cases)
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mapper.setVisibilityChecker(mapper.getVisibilityChecker().with(JsonAutoDetect.Visibility.NONE));
		
		final String scenario = bmr.getScenario();
		final String query = bmr.getQuery();
		final String tool = bmr.getTool();
		final int size = bmr.getSize();
		final int runIndex = bmr.getRunIndex();
		final String relativeFilePath = "results/" + tool + "-" + scenario + "-" + query + "-Size" + size + "-Index" + runIndex + ".json";
		mapper.writeValue(new File(bmr.getBenchmarkConfig().getWorkspacePath() + relativeFilePath), bmr);

		System.out.println("Create JSON file: " + relativeFilePath);
	}
}
