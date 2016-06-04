package hu.bme.mit.trainbenchmark.benchmark.emfapi.test;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Ordering;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;

public class QueryShuffleTransform<TPatternMatch, TDriver extends Driver<?>> {

	protected final ModelOperation<TPatternMatch, TDriver> operation;
	protected Collection<TPatternMatch> matches;
	protected Comparator<TPatternMatch> comparator;
	
	public QueryShuffleTransform(final ModelOperation<TPatternMatch, TDriver> operation) {
		this.operation = operation;
	}
	
	public Collection<? extends TPatternMatch> query() throws Exception {
		matches = operation.evaluateQuery();
		return matches;
	}
	
	public List<TPatternMatch> work() {
		final Ordering<TPatternMatch> ordering = Ordering.from(comparator);
		final List<TPatternMatch> sortedMatches = ordering.sortedCopy(matches);
		return sortedMatches;
	}
	
}
