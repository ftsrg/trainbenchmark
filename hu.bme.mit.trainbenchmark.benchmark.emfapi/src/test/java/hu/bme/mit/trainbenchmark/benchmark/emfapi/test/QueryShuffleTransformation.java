package hu.bme.mit.trainbenchmark.benchmark.emfapi.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Ordering;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;

public class QueryShuffleTransformation<TPatternMatch, TDriver extends Driver<?>> {

	protected final ModelOperation<TPatternMatch, TDriver> operation;
	protected final Random random;
	protected Collection<TPatternMatch> matches;
	protected List<TPatternMatch> sortedMatches;
	protected Comparator<TPatternMatch> comparator;
	
	public QueryShuffleTransformation(final ModelOperation<TPatternMatch, TDriver> operation, final Random random) {
		this.operation = operation;
		this.random = random;
	}
	
	public Collection<? extends TPatternMatch> evaluateQuery() throws Exception {
		matches = operation.evaluateQuery();
		return matches;
	}
	
	public List<TPatternMatch> createSortedList() {
		final Ordering<TPatternMatch> ordering = Ordering.from(comparator);
		sortedMatches = ordering.sortedCopy(matches);
		return sortedMatches;
	}
	
	public List<TPatternMatch> pickPatternMatches(long nMatchesToModify) {
		final int size = sortedMatches.size();
		if (size < nMatchesToModify) {
			nMatchesToModify = size;
		}
		Collections.shuffle(sortedMatches, random);
		final List<TPatternMatch> objects = new ArrayList<>();
		for (int i = 0; i < nMatchesToModify; i++) {
			final TPatternMatch object = sortedMatches.get(i);
			objects.add(object);
		}
		return objects;
	}
	
	public Collection<? extends TPatternMatch> transform() throws Exception {
		matches = operation.evaluateQuery();
		return matches;
	}

	
}
