package hu.bme.mit.trainbenchmark.benchmark.allegro;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

public class AllegroBenchmarkMain {
	
	public static void main(String[] args) throws ParseException, IOException{
		AllegroBenchmarkLogic allegroBenchmarkLogic = new AllegroBenchmarkLogic(args);
		allegroBenchmarkLogic.runBenchmark();
	}
}
