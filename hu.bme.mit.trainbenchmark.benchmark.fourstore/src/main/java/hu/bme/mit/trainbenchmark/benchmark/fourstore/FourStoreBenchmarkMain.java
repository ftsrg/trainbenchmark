package hu.bme.mit.trainbenchmark.benchmark.fourstore;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

public class FourStoreBenchmarkMain {

	public static void main(String[] args) throws IOException, ParseException {
		FourStoreBenchmarkLogic benchmarkLogic = new FourStoreBenchmarkLogic(args);
		benchmarkLogic.runBenchmark();
	}

}
