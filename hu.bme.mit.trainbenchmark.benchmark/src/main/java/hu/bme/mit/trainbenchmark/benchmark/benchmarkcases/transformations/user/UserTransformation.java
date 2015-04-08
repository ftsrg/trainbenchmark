package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;

import java.util.List;

import com.google.common.collect.Ordering;

public abstract class UserTransformation<M, T> extends Transformation<M, T, T> {

	@Override
	protected List<T> copyAndSort() {
		final Ordering<T> ordering = Ordering.from(driver.getElementComparator());
		final List<T> sortedMatches = ordering.sortedCopy(candidatesToModify);
		return sortedMatches;
	}

}
