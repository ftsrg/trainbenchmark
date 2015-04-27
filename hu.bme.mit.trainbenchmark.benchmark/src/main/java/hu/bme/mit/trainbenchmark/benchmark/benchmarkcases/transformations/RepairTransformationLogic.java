package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Ordering;

public class RepairTransformationLogic<M, T> extends TransformationLogic<M, T, M> {

	protected RepairTransformationLogic(final Comparator comparator) {
		super(comparator);
	}

	@Override
	protected void lhs(final Collection<M> currentMatches) throws IOException {
		candidatesToModify = currentMatches;
	}

	@Override
	protected List<M> copyAndSort() {
		final Ordering<M> ordering = Ordering.from(comparator);
		final List<M> sortedMatches = ordering.sortedCopy(candidatesToModify);
		return sortedMatches;
	}
}
