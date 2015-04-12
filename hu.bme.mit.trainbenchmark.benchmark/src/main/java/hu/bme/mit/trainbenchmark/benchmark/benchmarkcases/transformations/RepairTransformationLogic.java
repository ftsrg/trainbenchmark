package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RepairTransformationLogic<M, T> extends TransformationLogic<M, T, M> {

	@Override
	protected void lhs(final Collection<M> currentMatches) throws IOException {
		candidatesToModify = currentMatches;
	}

	@Override
	protected List<M> copyAndSort() {
		// final Ordering<M> ordering = Ordering.from(driver.getMatchComparator());
		// final List<M> sortedMatches = ordering.sortedCopy(candidatesToModify);
		final List<M> sortedMatches = new ArrayList<>(candidatesToModify);
		return sortedMatches;
	}

}
