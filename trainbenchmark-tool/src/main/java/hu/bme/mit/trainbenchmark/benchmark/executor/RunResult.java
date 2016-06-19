package hu.bme.mit.trainbenchmark.benchmark.executor;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;

import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class RunResult {
	
	protected ListMultimap<RailwayQuery, Integer> matches = LinkedListMultimap.create();
	
	protected Long readTime;
	protected List<Long> queryTimes = new LinkedList<>();
	protected List<Long> transformationTimes = new LinkedList<>();

	public RunResult() {
	}
	
	public ListMultimap<RailwayQuery, Integer> getMatches() {
		return matches;
	}
	
	public Long getReadTime() {
		return readTime;
	}
	
	public void setReadTime(final Long readTime) {
		this.readTime = readTime;
	}
	
	public List<Long> getQueryTimes() {
		return queryTimes;
	}
	
	public List<Long> getTransformationTimes() {
		return transformationTimes;
	}
	
}