package hu.bme.mit.trainbenchmark.benchmark.phases;

import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkExecutor;

public class InitializeOperationsPhase extends Phase {

	public InitializeOperationsPhase(final BenchmarkExecutor<?, ?, ?> executor) {
		super(executor);
	}

	@Override
	public void run() throws Exception {
		executor.initializeOperations();		
	}

}
