package hu.bme.mit.trainbenchmark.benchmark.phases;

import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkBundle;

public class ReadPhase extends Phase {

	public ReadPhase(final BenchmarkBundle<?, ?, ?> executor) {
		super(executor);
	}

	@Override
	public void initialize() throws Exception {
		executor.initializeDriver();
	}
	
	@Override
	public void run() throws Exception {
		executor.read();
	}

}
