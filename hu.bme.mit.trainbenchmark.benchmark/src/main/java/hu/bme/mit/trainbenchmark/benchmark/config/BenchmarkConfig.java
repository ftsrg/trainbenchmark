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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableList;

import hu.bme.mit.trainbenchmark.config.AbstractConfig;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;
import hu.bme.mit.trainbenchmark.constants.TransformationStrategy;

public class BenchmarkConfig extends AbstractConfig {

	protected static final String RUNS = "runs";
	protected static final String ITERATION_COUNT = "iterationCount";
	protected static final String TRANSFORMATION_CONSTANT = "transformationConstant";
	protected static final String TRANSFORMATION_STRATEGY = "transformationStrategy";

	// this must be specified by the user
	protected int runs;

	protected String toolName;
	protected String modelPath;
	protected CommandLine cmd;

	// constants for the transformation
	protected TransformationStrategy transformationStrategy = TransformationStrategy.FIXED;
	protected long transformationConstant = 10;
	protected int iterationCount = 10;

	private Collection<RailwayOperation> railwayOperations;

	public BenchmarkConfig() {
	}

	public BenchmarkConfig(final String toolName, final ScenarioEnum scenario, final int size, final int runs, final RailwayQuery query,
			final int iterationCount, final TransformationStrategy transformationStrategy, final long transformationConstant) {
		super();
		this.toolName = toolName;
		this.runs = runs;
		this.operations = ImmutableList.of(query);
		this.iterationCount = iterationCount;
		this.transformationStrategy = transformationStrategy;
		this.transformationConstant = transformationConstant;
	}

	@Override
	protected void initOptions() {
		// super.initOptions();
		//
		// // the "size" and "queries" options are required for the BenchmarkConfig but not required for the GeneratorConfig
		// options.getOption(SIZE).setRequired(true);
		// final Option queryOption = options.getOption(OPERATIONS);
		// queryOption.setRequired(true);
		// options.addOption(queryOption);
		//
		// // runs
		// options.addOption(RUNS, true, "number of runs");
		// final Option runOption = options.getOption(RUNS);
		// runOption.setRequired(true);
		// options.addOption(runOption);
		//
		// // model operations
		// final Option operations = new Option(OPERATIONS, true, "specifies the model operations, e.g. RouteSensor, PosLengthInject,
		// SwitchSetRepair");
		// operations.setArgs(Option.UNLIMITED_VALUES);
		// options.addOption(operations);
		//
		// // constants for the transformation
		// options.addOption(ITERATION_COUNT, true, "number of transformation-recheck iterations");
		// options.addOption(TRANSFORMATION_STRATEGY, true,
		// "options: fixed -- modify a fixed number of elements, proportional -- modify a percentage of the elements based on the size of
		// the results set");
		// options.addOption(TRANSFORMATION_CONSTANT, true,
		// "transformation constant for the transformation method (number of elements for fixed strategy, percentage for proportional
		// strategy)");
	}

	public void processArguments(final String[] args) throws ParseException {
		// super.processArguments(args);
		//
		// runs = new Integer(cmd.getOptionValue(RUNS));
		//
		// if (cmd.hasOption(ITERATION_COUNT)) {
		// iterationCount = new Integer(cmd.getOptionValue(ITERATION_COUNT));
		// }
		//
		// if (cmd.hasOption(TRANSFORMATION_STRATEGY)) {
		// transformationStrategy = TransformationStrategy.valueOf(cmd.getOptionValue(TRANSFORMATION_STRATEGY).toUpperCase());
		// }
		//
		// if (cmd.hasOption(TRANSFORMATION_CONSTANT)) {
		// transformationConstant = new Long(cmd.getOptionValue(TRANSFORMATION_CONSTANT));
		// }
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

	public String getModelPath() {
		// return "../models/railway-repair-1.xmi";
		return modelPath;
	}

	public void setModelPath(final String modelPath) {
		this.modelPath = modelPath;
	}

	public Collection<RailwayOperation> getRailwayOperations() {
		return railwayOperations;
	}

	public void setRailwayOperations(Collection<RailwayOperation> railwayOperations) {
		this.railwayOperations = railwayOperations;
	}

	public void saveToFile(final String path) throws FileNotFoundException {
		final Kryo kryo = new Kryo();
		try (final Output output = new Output(new FileOutputStream(path))) {
			kryo.writeObject(output, this);
		}
	}

	public static BenchmarkConfig fromFile(final String path) throws FileNotFoundException {
		final Kryo kryo = new Kryo();
		try (final Input input = new Input(new FileInputStream(path))) {
			final BenchmarkConfig benchmarkConfig = kryo.readObject(input, BenchmarkConfig.class);
			return benchmarkConfig;
		}
	}

	// public String getCaseName() {
	// final String queries = getQueries().toString().replaceAll("[\\[\\]]", "").replaceAll(", ","-");
	// return queries;
	// }

}
