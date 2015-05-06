package hu.bme.mit.trainbenchmark.benchmark.orientdb;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

public class OrientDbBenchmarkMain {

	public static void main(String[] args) throws IOException, ParseException {
		OrientDbBenchmarkLogic benchmarkLogic = new OrientDbBenchmarkLogic(args);
		benchmarkLogic.runBenchmark();
	}

}
