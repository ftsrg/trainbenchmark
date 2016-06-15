package hu.bme.mit.trainbenchmark.benchmark.executor;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;

import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class BenchmarkResult {

	protected final ListMultimap<RailwayQuery, Integer> allMatches = LinkedListMultimap.create();
	
	protected Long readTime;
	protected final ListMultimap<RailwayQuery, Long> queryTimes = LinkedListMultimap.create();
	protected final ListMultimap<RailwayQuery, Long> transformationTimes = LinkedListMultimap.create();
	
	public void registerMatches(final RailwayQuery query, final int matches) {
		allMatches.put(query, matches);
	}
	
	public void registerReadTime(final Long readTime) {
		this.readTime = readTime;
	}
	
	public void registerQueryTime(final RailwayQuery query, final Long time) {
		queryTimes.put(query, time);
	}
	
	public void registerTransformationTime(final RailwayQuery query, final Long time) {
		transformationTimes.put(query, time);
	}
	
	public ListMultimap<RailwayQuery, Integer> getAllMatches() {
		return allMatches;
	}
	
	public Long getReadTime() {
		return readTime;
	}
	
	public ListMultimap<RailwayQuery, Long> getQueryTimes() {
		return queryTimes;
	}
	
	public ListMultimap<RailwayQuery, Long> getTransformationTimes() {
		return transformationTimes;
	}

	@Override
	public String toString() {
		String s = "";
		s += "Benchmark results.\n";
		s += "Matches: " + allMatches.toString() + "\n";
		s += "Read time: " + readTime + "\n";
		s += "Query times: " + queryTimes + "\n";
		s += "Transformation times: " + transformationTimes + "\n";
		return s;
	}
	
}
