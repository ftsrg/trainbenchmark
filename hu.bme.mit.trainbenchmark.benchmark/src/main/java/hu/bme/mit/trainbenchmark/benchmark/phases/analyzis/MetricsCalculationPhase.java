package hu.bme.mit.trainbenchmark.benchmark.phases.analyzis;

import eu.mondo.sam.core.phases.OptionalPhase;

public class MetricsCalculationPhase extends OptionalPhase {

	protected boolean analyze;

	public MetricsCalculationPhase() {
		analyze = false;
	}

	@Override
	public boolean condition() {
		return analyze;
	}

	public boolean isAnalyze() {
		return analyze;
	}

	public void setAnalyze(boolean analyze) {
		this.analyze = analyze;
	}

}
