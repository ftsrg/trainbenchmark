package hu.bme.mit.trainbenchmark.benchmark.executor;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;

import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class BenchmarkResults {

	protected final ListMultimap<RailwayQuery, Integer> allMatches = LinkedListMultimap.create();
	
	public void registerMatches(final RailwayQuery query, final int matches) {
		allMatches.put(query, matches);
	}
	
	@Override
	public String toString() {
		return allMatches.toString();
	}
	
}
