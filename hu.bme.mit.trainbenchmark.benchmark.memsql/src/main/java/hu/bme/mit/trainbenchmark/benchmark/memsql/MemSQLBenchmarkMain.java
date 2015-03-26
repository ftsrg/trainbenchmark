package hu.bme.mit.trainbenchmark.benchmark.memsql;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

public class MemSQLBenchmarkMain {

    public static void main(String[] args) throws IOException, ParseException {
	MemSQLBenchmarkLogic benchmarkLogic = new MemSQLBenchmarkLogic(args);
	benchmarkLogic.runBenchmark();
    }
}
