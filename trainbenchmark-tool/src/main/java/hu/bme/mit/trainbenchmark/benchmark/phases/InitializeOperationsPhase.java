package hu.bme.mit.trainbenchmark.benchmark.phases;

import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkBundle;

public class InitializeOperationsPhase extends Phase {

	public InitializeOperationsPhase(final BenchmarkBundle<?, ?, ?> executor) {
		super(executor);
	}

	@Override
	public void run() throws Exception {
		executor.initializeOperations();
	}

}
