package hu.bme.mit.trainbenchmark.benchmark.phases;

import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkBundle;

public class TransformationPhase extends Phase {
	
	public TransformationPhase(final BenchmarkBundle<?, ?, ?> executor) {
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
