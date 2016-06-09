package hu.bme.mit.trainbenchmark.benchmark.phases;

import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkExecutor;

public class ReadPhase extends Phase {

	public ReadPhase(final BenchmarkExecutor executor) {
		super(executor);
	}

	@Override
	public void run() throws Exception {
		executor.read();
	}

}
