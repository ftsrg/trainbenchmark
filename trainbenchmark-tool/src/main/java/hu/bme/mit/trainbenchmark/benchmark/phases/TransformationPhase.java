package hu.bme.mit.trainbenchmark.benchmark.phases;

import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkBundle;

public class TransformationPhase extends Phase {

	public TransformationPhase(final BenchmarkBundle<?, ?, ?> bundle) {
		super(bundle);
	}

	@Override
	public void initialize() throws Exception {
		bundle.shuffle();
	}

	@Override
	public void run() throws Exception {
		bundle.transform();
	}

}
