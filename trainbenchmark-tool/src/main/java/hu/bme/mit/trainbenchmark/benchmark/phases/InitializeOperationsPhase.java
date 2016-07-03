package hu.bme.mit.trainbenchmark.benchmark.phases;

import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkBundle;

public class InitializeOperationsPhase extends Phase {

	public InitializeOperationsPhase(final BenchmarkBundle<?, ?, ?> bundle) {
		super(bundle);
	}

	@Override
	public void run() throws Exception {
		bundle.initializeDriver();
		bundle.initializeOperations();
	}

}
