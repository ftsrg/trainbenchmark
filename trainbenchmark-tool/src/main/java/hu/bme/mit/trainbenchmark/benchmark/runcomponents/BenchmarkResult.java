package hu.bme.mit.trainbenchmark.benchmark.runcomponents;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.result.AbstractResult;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class BenchmarkResult extends AbstractResult {

	protected final String PHASE = "Phase";
	protected final String ITERATION = "Iteration";
	protected final String TIME = "Time";

	protected final List<RunResult> runResults = new LinkedList<>();

	public BenchmarkResult(final BenchmarkConfig bc) {
		super(bc);
	}

	public void nextRun() {
		final RunResult result = new RunResult();
		runResults.add(result);
	}

	public RunResult getLastRunResult() {
		return Iterables.getLast(runResults);
	}

	public void registerMatches(final RailwayQuery query, final int numberOfMatches) {
		getCurrentResult().getMatches().put(query, numberOfMatches);
	}

	public void registerReadTime(final Long readTime) {
		getCurrentResult().setReadTime(readTime);
	}

	public void registerQueryTime(final Long time) {
		getCurrentResult().getQueryTimes().add(time);
	}

	public void registerTransformationTime(final Long time) {
		getCurrentResult().getTransformationTimes().add(time);
	}

	@Override
	public String toString() {
		String s = "";
		for (final RunResult runResult : runResults) {
			s += "Matches: " + runResult.getMatches().toString() + NL;
			s += "Read time: " + runResult.getReadTime() + NL;
			s += "Query times: " + runResult.getQueryTimes() + NL;
			s += "Transformation times: " + runResult.getTransformationTimes() + NL;
			s += NL;
		}
		return s;
	}

	public String csvTimes() {
		final List<String> headerAttributes = ImmutableList.of(TOOL, WORKLOAD, DESCRIPTION, MODEL, RUN, PHASE, ITERATION, TIME);
		final String header = separatorJoiner.join(headerAttributes);

		final List<String> rows = Lists.newArrayList(header);
		// in case of 5 runs, the run variable takes values of [1, 2, 3, 4, 5]
		for (Integer run = 1; run <= runResults.size(); run++) {
			final RunResult runResult = runResults.get(run - 1);

			// Read
			final List<String> timeRecordAttributes = ImmutableList.of(toolName, workload, description, model,
					run.toString(), "Read", NA, runResult.getReadTime().toString());
			final String timeRecord = separatorJoiner.join(timeRecordAttributes);
			rows.add(timeRecord);

			// Check
			final List<String> queryRecordAttributes = ImmutableList.of(toolName, workload, description, model,
					run.toString(), "Check", NA, runResult.getQueryTimes().get(0).toString());
			final String queryRecord = separatorJoiner.join(queryRecordAttributes);
			rows.add(queryRecord);

			// Transformation-Recheck
			final List<Long> transformationTimes = runResult.getTransformationTimes();
			final List<Long> queryTimes = runResult.getQueryTimes();
			for (Integer iteration = 1; iteration <= transformationTimes.size(); iteration++) {
				final Long transformationTime = transformationTimes.get(iteration - 1);
				final Long recheckTime = queryTimes.get(iteration);

				final List<String> recheckRecordAttributes = ImmutableList.of(toolName, workload, description, model,
						run.toString(), "Transformation", iteration.toString(), recheckTime.toString());
				final String recheckRecord = separatorJoiner.join(recheckRecordAttributes);
				rows.add(recheckRecord);

				final List<String> transformationRecordAttributes = ImmutableList.of(toolName, workload, description, model,
						run.toString(), "Recheck", iteration.toString(), transformationTime.toString());
				final String transformationRecord = separatorJoiner.join(transformationRecordAttributes);
				rows.add(transformationRecord);
			}
		}

		rows.add(LAST_LINE);
		final String csv = newlineJoiner.join(rows);
		return csv;
	}

	public String csvMatches() {
		final List<String> headerAttributes = ImmutableList.of("Tool", "Workload", "Description", "Model", "Run",
				"Query", "Iteration", "Matches");
		final String header = separatorJoiner.join(headerAttributes);

		final List<String> rows = Lists.newArrayList(header);
		for (Integer run = 1; run <= runResults.size(); run++) {
			final RunResult runResult = runResults.get(run - 1);

			for (final RailwayQuery query : runResult.getMatches().keySet()) {
				final List<Integer> queryMatches = runResult.getMatches().get(query);
				for (Integer iteration = 1; iteration <= queryMatches.size(); iteration++) {
					final Integer matches = queryMatches.get(iteration - 1);

					final List<String> recordAttributes = ImmutableList.of(toolName, workload, description, model,
							run.toString(), query.toString(), iteration.toString(), matches.toString());
					final String record = separatorJoiner.join(recordAttributes);
					rows.add(record);
				}
			}
		}

		rows.add(LAST_LINE);
		final String csv = newlineJoiner.join(rows);
		return csv;
	}

	public void serialize() throws IOException {
		serializeCsv(csvTimes(), "times");
		serializeCsv(csvMatches(), "matches");
	}

	protected RunResult getCurrentResult() {
		return Iterables.getLast(runResults);
	}

}
