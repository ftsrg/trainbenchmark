package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations;

import java.io.IOException;

public abstract class RepairTransformation<M, T> extends Transformation<M, T, M> {

	@Override
	protected void lhs() throws IOException {
		candidatesToModify = currentMatches;
	}

}
