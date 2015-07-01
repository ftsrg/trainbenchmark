/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.util;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;

import java.util.ArrayList;
import java.util.List;

public class BenchmarkResult {

	private static final String RSS = "rss";
	private static final String REPAIR = "repair";
	private static final String CHECK = "check";
	private static final String RECHECK = "recheck";
	private static final String READ = "read";
	private static final String TIME = "time";

	protected String tool;
	protected String query;
	protected BenchmarkConfig bc;
	protected long startTime;

	protected int runIndex;
	protected final String SEPARATOR = "\t";

	protected List<Long> modifiedElementsSizes = new ArrayList<>();
	protected List<Integer> resultSizes = new ArrayList<>();

	// phase 1
	protected Long readTime;
	// phase 2
	protected List<Long> checkTimes = new ArrayList<>();
	// phase 3
	protected List<Long> repairTimes = new ArrayList<>();

	public BenchmarkResult(final BenchmarkConfig bc) {
		this.bc = bc;
	}

	// benchmarkconfig

	public BenchmarkConfig getBenchmarkConfig() {
		return bc;
	}

	public static BenchmarkResult newInstance(final BenchmarkConfig bc) {
		return new BenchmarkResult(bc);
	}
	
	public void setBenchmarkConfig(final BenchmarkConfig bc) {
		this.bc = bc;
	}

	// timing

	public void restartClock() {
		startTime = System.nanoTime();
	}

	public long stopClock() {
		final long currentTime = System.nanoTime();
		final long nanos = currentTime - startTime;
		return nanos;
	}

	// phase 1
	public void setReadTime() {
		readTime = stopClock();
	}

	// phase 2
	public void addCheckTime() {
		checkTimes.add(stopClock());
	}

	// phase 3
	public void addTransformationTime() {
		repairTimes.add(stopClock());
	}

	// modification parameters

	public void addModifiedElementsSize(final long modifiedElementsSize) {
		modifiedElementsSizes.add(modifiedElementsSize);
	}

	// result sizes

	public void addResultSize(final int size) {
		resultSizes.add(size);
	}

	public List<Integer> getResultSizes() {
		return resultSizes;
	}

	public long getLastResultSize() {
		return resultSizes.get(resultSizes.size() - 1);
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();

		for (int i = 0; i < resultSizes.size(); i++) {
			String phase = (i == 0) ? phase = CHECK : RECHECK;
			generateRow(builder, phase, RSS, i, resultSizes.get(i));
		}

		// phases
		generateRow(builder, READ, TIME, 0, readTime);
		for (int i = 0; i < checkTimes.size(); i++) {
			String phase = (i == 0) ? phase = CHECK : RECHECK;
			generateRow(builder, phase, TIME, i, checkTimes.get(i));
		}
		for (int i = 0; i < repairTimes.size(); i++) {
			generateRow(builder, REPAIR, TIME, (i + 1), repairTimes.get(i));
		}

		return builder.toString();
	}

	protected void generateRow(final StringBuilder builder, final String phase, final String metricName, final int i, final long value) {
		// Scenario
		builder.append(bc.getScenarioName());
		builder.append(SEPARATOR);
		// RunIndex
		builder.append(runIndex);
		builder.append(SEPARATOR);
		// Tool
		builder.append(bc.getTool());
		builder.append(SEPARATOR);
		// Size
		builder.append(bc.getSize());
		builder.append(SEPARATOR);
		// Query
		builder.append(bc.getQuery());
		builder.append(SEPARATOR);
		// PhaseName
		builder.append(phase);
		builder.append(SEPARATOR);
		// Iteration
		builder.append(i);
		builder.append(SEPARATOR);
		// MetricName
		builder.append(metricName);
		builder.append(SEPARATOR);
		// MetricValue
		builder.append(value);

		// newline
		builder.append(System.lineSeparator());
	}

	public void publish(final boolean includeHeader) {
		if (includeHeader) {
			System.out.println("Scenario	RunIndex	Tool	Size	Query	PhaseName	Iteration	MetricName	MetricValue");
		}
		System.out.print(toString());
	}

}
