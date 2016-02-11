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

package hu.bme.mit.trainbenchmark.benchmark.config;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import com.google.common.collect.ImmutableList;

import hu.bme.mit.trainbenchmark.config.TrainBenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;
import hu.bme.mit.trainbenchmark.constants.TransformationStrategy;

public class BenchmarkConfig extends TrainBenchmarkConfig {

	protected static final String RUNS = "runs";
	protected static final String ITERATION_COUNT = "iterationCount";
	protected static final String TRANSFORMATION_CONSTANT = "transformationConstant";
	protected static final String TRANSFORMATION_STRATEGY = "transformationStrategy";

	// this must be specified by the user
	protected int runs;

	protected final String toolName;

	// constants for trhe transformation
	protected TransformationStrategy transformationStrategy = TransformationStrategy.FIXED;
	protected long transformationConstant = 10;
	protected int iterationCount = 10;

	protected String className;

	public BenchmarkConfig(final String toolName, final String args[]) throws ParseException {
		super(args);
		this.toolName = toolName;
	}

	public BenchmarkConfig(final String toolName, final ScenarioEnum scenario, final int size, final int runs, final RailwayQuery query,
			final int iterationCount, final TransformationStrategy transformationStrategy, final long transformationConstant) {
		super(scenario, size);
		this.toolName = toolName;
		this.runs = runs;
		this.queryMix = ImmutableList.of(query);
		this.iterationCount = iterationCount;
		this.transformationStrategy = transformationStrategy;
		this.transformationConstant = transformationConstant;
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		// the "size" and "queries" options are required for the BenchmarkConfig but not required for the GeneratorConfig
		options.getOption(SIZE).setRequired(true);
		final Option queryOption = options.getOption(QUERY_MIX);
		queryOption.setRequired(true);
		options.addOption(queryOption);

		// runs
		options.addOption(RUNS, true, "number of runs");
		final Option runOption = options.getOption(RUNS);
		runOption.setRequired(true);
		options.addOption(runOption);

		// constants for the transformation
		options.addOption(ITERATION_COUNT, true, "number of transformation-recheck iterations");
		options.addOption(TRANSFORMATION_STRATEGY, true,
				"options: fixed -- modify a fixed number of elements, proportional -- modify a percentage of the elements based on the size of the results set");
		options.addOption(TRANSFORMATION_CONSTANT, true,
				"transformation constant for the transformation method (number of elements for fixed strategy, percentage for proportional strategy)");
	}

	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		runs = new Integer(cmd.getOptionValue(RUNS));

		if (cmd.hasOption(ITERATION_COUNT)) {
			iterationCount = new Integer(cmd.getOptionValue(ITERATION_COUNT));
		}

		if (cmd.hasOption(TRANSFORMATION_STRATEGY)) {
			transformationStrategy = TransformationStrategy.valueOf(cmd.getOptionValue(TRANSFORMATION_STRATEGY).toUpperCase());
		}

		if (cmd.hasOption(TRANSFORMATION_CONSTANT)) {
			transformationConstant = new Long(cmd.getOptionValue(TRANSFORMATION_CONSTANT));
		}
	}

	public TransformationStrategy getTransformationStrategy() {
		return transformationStrategy;
	}

	public long getTransformationConstant() {
		return transformationConstant;
	}

	public int getIterationCount() {
		return iterationCount;
	}

	public int getRuns() {
		return runs;
	}

	public String getToolName() {
		return toolName;
	}

	public String getCaseName() {
		final String queries = getQueries().toString().replaceAll("[\\[\\]]", "").replaceAll(", ","-");
		return queries;
	}
	
}
