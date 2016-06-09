package hu.bme.mit.trainbenchmark.benchmark.phases;

import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkExecutor;

public class QueryPhase extends Phase {

	public QueryPhase(BenchmarkExecutor executor) {
		super(executor);
	}

	@Override
	public void run() throws Exception {
		executor.query();
	}	
	
}
