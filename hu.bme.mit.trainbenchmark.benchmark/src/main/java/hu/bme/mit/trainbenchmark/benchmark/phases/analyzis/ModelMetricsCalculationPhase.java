package hu.bme.mit.trainbenchmark.benchmark.phases.analyzis;

import hu.bme.mit.trainbenchmark.benchmark.token.TrainBenchmarkDataToken;
import eu.mondo.sam.core.DataToken;
import eu.mondo.sam.core.phases.AtomicPhase;
import eu.mondo.sam.core.results.PhaseResult;

public class ModelMetricsCalculationPhase extends AtomicPhase {

	public ModelMetricsCalculationPhase(String phaseName) {
		super(phaseName);
	}

	@Override
	public void execute(DataToken token, PhaseResult phaseResult) {
		final TrainBenchmarkDataToken trainToken = ((TrainBenchmarkDataToken) token);
		trainToken.getBenchmarkCase()
				.calculateModelMetrics(phaseResult);
	}

}
