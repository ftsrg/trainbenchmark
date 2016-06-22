package hu.bme.mit.trainbenchmark.benchmark.executor;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Iterables;

import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class BenchmarkResult {

	protected final String tool;
	protected final String workload;

	protected RunResult currentResult;
	protected List<RunResult> runResults = new LinkedList<>();

	public BenchmarkResult(final String tool, final String workload) {
		this.tool = tool;
		this.workload = workload;
	}

	public void nextRun() {
		currentResult = new RunResult();
		runResults.add(currentResult);
	}
	
	public RunResult getLastRunResult() {
		return Iterables.getLast(runResults);
		
	}

	public void registerMatches(final RailwayQuery query, final int numberOfMatches) {
		currentResult.getMatches().put(query, numberOfMatches);
	}

	public void registerReadTime(final Long readTime) {
		currentResult.setReadTime(readTime);
	}

	public void registerQueryTime(final Long time) {
		currentResult.getQueryTimes().add(time);
	}

	public void registerTransformationTime(final Long time) {
		currentResult.getTransformationTimes().add(time);
	}

	@Override
	public String toString() {
		String s = "";
		for (final RunResult runResult : runResults) {
			s += "Matches: " + runResult.getMatches().toString() + "\n";
			s += "Read time: " + runResult.getReadTime() + "\n";
			s += "Query times: " + runResult.getQueryTimes() + "\n";
			s += "Transformation times: " + runResult.getTransformationTimes() + "\n";
			s += "\n";
		}
		return s;
	}

	final String SEP = ",";
	final String NL = "\n";

	public String csvTimes() {
		final String recordBase = tool + SEP + workload + SEP;

		String csvTimes = "Tool" + SEP + "Workload" + SEP + "Run" + SEP + "Phase" + SEP + "Iteration" + SEP + "Value"
				+ NL;
		for (int run = 1; run <= runResults.size(); run++) {
			final RunResult runResult = runResults.get(run - 1);

			final String runBase = recordBase + run + SEP;
			
			// Read
			final String timeRecord = runBase + "Read" + SEP + SEP + runResult.getReadTime() + NL;
			csvTimes += timeRecord;

			// Check
			final String queryRecord = runBase + "Check" + SEP + SEP + runResult.getQueryTimes().get(0) + NL;
			csvTimes += queryRecord;

			// Transformation-Recheck
			final List<Long> transformationTimes = runResult.getTransformationTimes();			
			final List<Long> queryTimes = runResult.getQueryTimes();
			for (int iteration = 1; iteration <= transformationTimes.size(); iteration++) {
				final Long transformationTime = transformationTimes.get(iteration - 1);
				final Long recheckTime = queryTimes.get(iteration);
				
				final String recheckRecord = runBase + "Transformation" + SEP + iteration + SEP + recheckTime + NL;
				csvTimes += recheckRecord;				
				final String transformationRecord = runBase + "Recheck" + SEP + iteration + SEP + transformationTime + NL;
				csvTimes += transformationRecord;				
			}
		}

		return csvTimes;
	}
	
	public String csvMatches() {
		final String recordBase = tool + SEP + workload + SEP;
		String csvMatches = "Tool" + SEP + "Workload" + SEP + "Run" + SEP + "Query" + SEP + "Iteration" + SEP + "Value"
				+ NL;
		for (int run = 1; run <= runResults.size(); run++) {
			final RunResult runResult = runResults.get(run - 1);

			for (final RailwayQuery query : runResult.getMatches().keySet()) {
				final List<Integer> queryMatches = runResult.getMatches().get(query);
				for (int iteration = 1; iteration <= queryMatches.size(); iteration++) {
					final Integer matches = queryMatches.get(iteration - 1);

					String record = recordBase;
					record += run;
					record += SEP;
					record += query;
					record += SEP;
					record += iteration;
					record += SEP;
					record += matches;
					record += NL;

					csvMatches += record;
				}
			}
		}

		return csvMatches;
	}
	
}
