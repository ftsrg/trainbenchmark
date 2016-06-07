package hu.bme.mit.trainbenchmark.benchmark.phases;

import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkExecutor;

public class TransformationPhase extends Phase {
	
	public TransformationPhase(final BenchmarkExecutor executor) {
		super(executor);
	}

	@Override
	public void initialize() throws Exception {
		executor.shuffle();
	}
	
	@Override
	public void run() throws Exception {
		executor.transform();		
	}
	
}
