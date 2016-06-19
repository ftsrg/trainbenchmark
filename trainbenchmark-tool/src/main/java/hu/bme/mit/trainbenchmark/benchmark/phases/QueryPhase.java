package hu.bme.mit.trainbenchmark.benchmark.phases;

import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkBundle;

public class QueryPhase extends Phase {

	public QueryPhase(BenchmarkBundle<?, ?, ?> executor) {
		super(executor);
	}

	@Override
	public void run() throws Exception {
		executor.query();
	}	
	
}
