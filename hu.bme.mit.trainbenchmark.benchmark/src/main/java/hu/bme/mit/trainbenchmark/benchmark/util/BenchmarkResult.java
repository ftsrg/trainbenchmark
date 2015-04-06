/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
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
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Stopwatch;

public class BenchmarkResult {
	
	protected String tool;
	protected String query;
	protected BenchmarkConfig bc;
	protected Stopwatch stopwatch;
	protected Random random;

	@JsonProperty("ModifiedElements")
	protected List<Long> modifiedElementsSizes = new ArrayList<>();
	
	@JsonProperty("Memory")
	protected List<Long> memoryUsages = new ArrayList<>();
	
	@JsonProperty("Results")
	protected List<Integer> resultSizes = new ArrayList<>();

	// phase 1
	@JsonProperty("ReadTime")
	protected Long readTime;
	
	// phase 2
	@JsonProperty("CheckTimes")
	protected List<Long> checkTimes = new ArrayList<>();
	// phase 3
	@JsonProperty("LHSTimes")
	protected List<Long> lhsTimes = new ArrayList<>();
	
	@JsonProperty("RHSTimes")
	protected List<Long> rhsTimes = new ArrayList<>();

	public BenchmarkResult(final String tool, final String query) {
		this.tool = tool;
		this.query = query;
		this.random = new UniqRandom(TrainBenchmarkConstants.RANDOM_SEED);
	}
	
	// JSON properties
	@JsonProperty("Size")
	public int getSize(){
		return bc.getSize();
	}
	
	@JsonProperty("Scenario")
	public String getScenario(){
		return bc.getScenarioName();
	}
	
	@JsonProperty("RunIndex")
	public int getRunIndex(){
		return bc.getRunIndex();
	}
	
	@JsonProperty("Tool")
	public String getTool() {
		return tool;
	}
	
	@JsonProperty("Query")
	public String getQuery() {
		return query;
	}
	// benchmarkconfig

	public BenchmarkConfig getBenchmarkConfig() {
		return bc;
	}

	public void setBenchmarkConfig(final BenchmarkConfig bc) {
		this.bc = bc;
	}

	// timing

	public void restartClock() {
		stopwatch = Stopwatch.createStarted();
	}

	public long stopClock() {
		stopwatch.stop();
		final long nanos = stopwatch.elapsed(TimeUnit.NANOSECONDS);
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
	public void addLhsTime() {
		lhsTimes.add(stopClock());
	}

	public void addRhsTime() {
		rhsTimes.add(stopClock());
	}

	// random

	public void setRandom(final Random random) {
		this.random = random;
	}

	public Random getRandom() {
		return random;
	}

	// memory usage

	public void addMemoryUsage(final long memoryUsage) {
		memoryUsages.add(memoryUsage);
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
		// @formatter:off
		return
			"Benchmark results\n" +
			"-----------------\n" +
			"Result sizes: " + resultSizes + "\n" +
			"Read time: " + readTime + "\n" + 	
			"Check time: " + checkTimes + "\n" + 	
			"LHS times: " + lhsTimes + "\n" + 	
			"RHS times: " + rhsTimes + "\n" + 	
			"Modified elements: " + modifiedElementsSizes 
			;
		// @formatter:on
	}

	public void publish() throws IOException{
		ResultSerializer.serializeToJSON(this);
	}
	
}
