package hu.bme.mit.trainbenchmark.benchmark.phases;

import java.io.IOException;

import token.TrainBenchmarkDataToken;
import eu.mondo.sam.core.DataToken;
import eu.mondo.sam.core.phases.AtomicPhase;
import eu.mondo.sam.core.results.PhaseResult;

public class CheckPhase extends AtomicPhase {

	public CheckPhase(String phaseName) {
		super(phaseName);
	}

	@Override
	public void execute(DataToken token, PhaseResult phaseResult) {
		TrainBenchmarkDataToken trainToken = ((TrainBenchmarkDataToken) token);
		try {
			trainToken.getBenchmarkCase().benchmarkCheck(
					phaseResult);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

}
