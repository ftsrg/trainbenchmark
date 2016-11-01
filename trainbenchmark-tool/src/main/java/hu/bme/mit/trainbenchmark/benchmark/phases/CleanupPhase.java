package hu.bme.mit.trainbenchmark.benchmark.phases;

import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkBundle;

public class CleanupPhase extends Phase {

	public CleanupPhase(final BenchmarkBundle<?, ?, ?> bundle) {
		super(bundle);
	}

	@Override
	public void initialize() throws Exception {
		if (bundle != null) {
			bundle.cleanup();
		}
	}

	@Override
	public void run() throws Exception {
	}

}
