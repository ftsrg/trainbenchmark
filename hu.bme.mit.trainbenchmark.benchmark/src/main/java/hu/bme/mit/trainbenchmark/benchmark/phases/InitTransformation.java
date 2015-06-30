package hu.bme.mit.trainbenchmark.benchmark.phases;

import token.TrainBenchmarkDataToken;
import eu.mondo.sam.core.DataToken;
import eu.mondo.sam.core.phases.AtomicPhase;
import eu.mondo.sam.core.results.PhaseResult;

public class InitTransformation extends AtomicPhase {

	public InitTransformation(String phaseName) {
		super(phaseName);
	}

	@Override
	public void execute(DataToken token, PhaseResult phaseResult) {
		TrainBenchmarkDataToken trainToken = ((TrainBenchmarkDataToken) token);
		trainToken.getBenchmarkCase().benchmarkInitTransformation();
	}

}
