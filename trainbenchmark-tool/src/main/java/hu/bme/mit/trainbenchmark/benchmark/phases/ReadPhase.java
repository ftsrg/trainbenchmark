package hu.bme.mit.trainbenchmark.benchmark.phases;

import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkBundle;

public class ReadPhase extends Phase {

	public ReadPhase(final BenchmarkBundle<?, ?, ?> bundle) {
		super(bundle);
	}

	@Override
	public void run() throws Exception {
		bundle.read();
	}

}
