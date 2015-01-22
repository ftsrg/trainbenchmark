package hu.bme.mit.trainbenchmark.benchmark.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonSerializer {
	
	public static void serialize(BenchmarkResult bmr){
		ObjectMapper mapper = new ObjectMapper();
		// to enable standard indentation ("pretty-printing"):
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		// to allow serialization of "empty" POJOs (no properties to serialize)
		// (without this setting, an exception is thrown in those cases)
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

		String scenario = bmr.getScenario();
		String query = bmr.getQuery();
		String tool = bmr.getTool();
		int size = bmr.getArtifactSize();
		int series = bmr.getRunIndex();
		String fileName = tool + "-" + scenario + "-" + query + "-Size" + size + "-Index" + series;
		try {
			mapper.writeValue(new File("results/" + fileName + ".json"), bmr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Create JSON file: results/" + fileName);
	}
}
