package hu.bme.mit.trainbenchmark.benchmark.phases;

import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkBundle;

public class CleanupPhase extends Phase {
	
	public CleanupPhase(final BenchmarkBundle<?, ?, ?> executor) {
		super(executor);
	}

	@Override
	public void initialize() throws Exception {
		executor.cleanup();
	}
	
	@Override
	public void run() throws Exception {
	}
	
}
