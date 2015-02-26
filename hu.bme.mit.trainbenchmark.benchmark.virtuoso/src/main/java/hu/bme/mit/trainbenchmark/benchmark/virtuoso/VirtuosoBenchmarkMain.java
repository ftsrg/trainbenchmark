package hu.bme.mit.trainbenchmark.benchmark.virtuoso;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

public class VirtuosoBenchmarkMain {

	public static void main(String[] args) throws ParseException, IOException {
		VirtuosoBenchmarkLogic benchmarkLogic = new VirtuosoBenchmarkLogic(args);
		benchmarkLogic.runBenchmark();
	}

}
