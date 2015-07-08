package hu.bme.mit.trainbenchmark.benchmark.phases;

import hu.bme.mit.trainbenchmark.benchmark.token.TrainBenchmarkDataToken;
import eu.mondo.sam.core.DataToken;
import eu.mondo.sam.core.phases.AtomicPhase;
import eu.mondo.sam.core.phases.OptionalPhase;
import eu.mondo.sam.core.results.PhaseResult;

public class CalculateModelMetricsPhase extends OptionalPhase {

	protected boolean analyze;

	public CalculateModelMetricsPhase() {
		analyze = false;
		phase = new ConcreteCalculationPhase("ModelMetrics");
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

	private class ConcreteCalculationPhase extends AtomicPhase {

		public ConcreteCalculationPhase(String phaseName) {
			super(phaseName);
		}

		@Override
		public void execute(DataToken token, PhaseResult phaseResult) {
			final TrainBenchmarkDataToken trainToken = ((TrainBenchmarkDataToken) token);
			trainToken.getBenchmarkCase().calculateModelMetrics(
					phaseResult);
		}

	}

}
