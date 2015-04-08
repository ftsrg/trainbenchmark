package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;

import java.io.IOException;
import java.util.List;

import com.google.common.collect.Ordering;

public abstract class RepairTransformation<M, T> extends Transformation<M, T, M> {

	@Override
	protected void lhs() throws IOException {
		candidatesToModify = currentMatches;
	}

	@Override
	protected List<M> copyAndSort() {
		final Ordering<M> ordering = Ordering.from(driver.getMatchComparator());
		final List<M> sortedMatches = ordering.sortedCopy(candidatesToModify);
		return sortedMatches;
	}

}
