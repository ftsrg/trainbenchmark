package hu.bme.mit.trainbenchmark.benchmark.runcomponents;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Ordering;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;

public class QueryShuffleTransformation<TPatternMatch, TDriver extends Driver> {

	protected final ModelOperation<TPatternMatch, TDriver> operation;
	protected final Comparator<? super TPatternMatch> comparator;
	protected final Random random;
	protected Collection<TPatternMatch> matches;
	protected List<TPatternMatch> sortedMatches;
	protected List<TPatternMatch> candidates;

	public QueryShuffleTransformation(final ModelOperation<TPatternMatch, TDriver> operation, final Comparator<? super TPatternMatch> comparator,
			final Random random) {
		this.operation = operation;
		this.comparator = comparator;
		this.random = random;
	}

	public static <TPatternMatch, TDriver extends Driver> QueryShuffleTransformation<TPatternMatch, TDriver> of(
			final ModelOperation<TPatternMatch, TDriver> operation, final Comparator<? super TPatternMatch> comparator, final Random random) {
		return new QueryShuffleTransformation<>(operation, comparator, random);
	}

	public Collection<? extends TPatternMatch> evaluateQuery() throws Exception {
		matches = operation.getQuery().evaluate();
		return matches;
	}

	public List<TPatternMatch> shuffle(int nMatchesToModify) {
		final Ordering<? super TPatternMatch> ordering = Ordering.from(comparator);
		sortedMatches = ordering.sortedCopy(matches);

		final int size = sortedMatches.size();
		if (size < nMatchesToModify) {
			nMatchesToModify = size;
		}
		Collections.shuffle(sortedMatches, random);
		candidates = new ArrayList<>(nMatchesToModify);
		for (int i = 0; i < nMatchesToModify; i++) {
			final TPatternMatch candidate = sortedMatches.get(i);
			candidates.add(candidate);
		}

		return candidates;
	}

	public void transform() throws Exception {
		operation.getTransformation().get().activateTransformation(candidates);
	}

	public boolean isTransformation() {
		return operation.getTransformation().isPresent();
	}

	public ModelQuery<TPatternMatch, TDriver> getQuery() {
		return operation.getQuery();
	}
	
	public Collection<TPatternMatch> getMatches() {
		return matches;
	}
	
	public List<TPatternMatch> getSortedMatches() {
		return sortedMatches;
	}
	
	public List<TPatternMatch> getCandidates() {
		return candidates;
	}

}
