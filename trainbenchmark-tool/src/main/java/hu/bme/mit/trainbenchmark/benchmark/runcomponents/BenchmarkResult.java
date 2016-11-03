package hu.bme.mit.trainbenchmark.benchmark.runcomponents;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class BenchmarkResult {

	protected final String SEP = ",";
	protected final String NL = "\n";
	protected final String NA = "";
	protected final String LAST_LINE = "";
	protected final Joiner separatorJoiner = Joiner.on(SEP);
	protected final Joiner newlineJoiner = Joiner.on(NL);

	protected final List<RunResult> runResults = new LinkedList<>();

	protected final String tool;
	protected final String workload;
	protected final String workspaceDir;
	protected final String model;
	protected final String description;
	protected final Integer memoryLimit;

	public BenchmarkResult(final String tool, final String workload, final String workspaceDir, final String model,
			final String description, final Integer memoryLimit) {
		this.tool = tool;
		this.workload = workload;
		this.workspaceDir = workspaceDir;
		this.model = model;
		this.description = description;
		this.memoryLimit = memoryLimit;
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

	public String csvMemory() {
		final List<String> headerAttributes = ImmutableList.of("Tool", "Workload", "Description", "Model", "Run", "Memory");
		final String header = separatorJoiner.join(headerAttributes);

		final List<String> rows = Lists.newArrayList(header);

		for (Integer run = 1; run <= runResults.size(); run++) {
			final List<String> memoryRecordAttributes = ImmutableList.of(tool, workload, description, model,
					run.toString(), memoryLimit.toString());
			final String timeRecord = separatorJoiner.join(memoryRecordAttributes);
			rows.add(timeRecord);
		}
		
		rows.add(LAST_LINE);
		final String csv = newlineJoiner.join(rows);
		return csv;
	}

	public String csvTimes() {
		final List<String> headerAttributes = ImmutableList.of("Tool", "Workload", "Description", "Model", "Run",
				"Phase", "Iteration", "Time");
		final String header = separatorJoiner.join(headerAttributes);

		final List<String> rows = Lists.newArrayList(header);
		// in case of 5 runs, the run variable takes values of [1, 2, 3, 4, 5]
		for (Integer run = 1; run <= runResults.size(); run++) {
			final RunResult runResult = runResults.get(run - 1);

			// Read
			final List<String> timeRecordAttributes = ImmutableList.of(tool, workload, description, model,
					run.toString(), "Read", NA, runResult.getReadTime().toString());
			final String timeRecord = separatorJoiner.join(timeRecordAttributes);
			rows.add(timeRecord);

			// Check
			final List<String> queryRecordAttributes = ImmutableList.of(tool, workload, description, model,
					run.toString(), "Check", NA, runResult.getQueryTimes().get(0).toString());
			final String queryRecord = separatorJoiner.join(queryRecordAttributes);
			rows.add(queryRecord);

			// Transformation-Recheck
			final List<Long> transformationTimes = runResult.getTransformationTimes();
			final List<Long> queryTimes = runResult.getQueryTimes();
			for (Integer iteration = 1; iteration <= transformationTimes.size(); iteration++) {
				final Long transformationTime = transformationTimes.get(iteration - 1);
				final Long recheckTime = queryTimes.get(iteration);

				final List<String> recheckRecordAttributes = ImmutableList.of(tool, workload, description, model,
						run.toString(), "Transformation", iteration.toString(), recheckTime.toString());
				final String recheckRecord = separatorJoiner.join(recheckRecordAttributes);
				rows.add(recheckRecord);

				final List<String> transformationRecordAttributes = ImmutableList.of(tool, workload, description, model,
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

					final List<String> recordAttributes = ImmutableList.of(tool, workload, description, model,
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
		final String timesCsvPath = String.format("%sresults/times-%s-%s-%s-[%s].csv", workspaceDir, tool, workload,
				model, description);
		// System.out.println("Saving execution times to: " + timesCsvPath);
		FileUtils.write(new File(timesCsvPath), csvTimes());

		final String matchesCsvPath = String.format("%sresults/matches-%s-%s-%s-[%s].csv", workspaceDir, tool, workload,
				model, description);
		// System.out.println("Saving results to: " + matchesCsvPath);
		FileUtils.write(new File(matchesCsvPath), csvMatches());

	}
	
	protected RunResult getCurrentResult() {
		return Iterables.getLast(runResults);
	}

}
