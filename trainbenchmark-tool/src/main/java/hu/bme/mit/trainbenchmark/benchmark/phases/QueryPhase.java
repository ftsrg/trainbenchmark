package hu.bme.mit.trainbenchmark.benchmark.phases;

import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkBundle;

public class QueryPhase extends Phase {

	public QueryPhase(BenchmarkBundle<?, ?, ?> bundle) {
		super(bundle);
	}

	@Override
	public void run() throws Exception {
		bundle.query();
	}

}
