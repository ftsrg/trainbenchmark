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

import hu.bme.mit.trainbenchmark.config.TrainBenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

import org.apache.commons.cli.ParseException;

public class BenchmarkConfig extends TrainBenchmarkConfig {

	// modification constants
	protected ModificationMethod modificationMethod;
	protected long modificationConstant;

	protected boolean benchmarkMode;
	protected int iterationCount;
	protected int runIndex;
	protected Query query;
	protected String className;
	protected boolean analyze;

	public int getRunIndex() {
		return runIndex;
	}

	public BenchmarkConfig(final String args[], final String className)
			throws ParseException {
		super(args);
		this.className = className;
	}

	public BenchmarkConfig(final String className, final Scenario scenario,
			final int size, final int runIndex, final Query query,
			final int iterationCount,
			final ModificationMethod modificationMethod,
			final long modificationConstant,
			final boolean calculateMetrics) {
		super(scenario, size);
		this.className = className;
		this.runIndex = runIndex;
		this.query = query;
		this.iterationCount = iterationCount;
		this.modificationMethod = modificationMethod;
		this.modificationConstant = modificationConstant;
		this.analyze = calculateMetrics;
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption(requiredOption("query",
				"the query to run, e.g. RouteSensor"));

		options.addOption(
				"benchmarkMode",
				true,
				"run benchmark specific (non-functional) procedures, like cleaning the OS cache");
		options.addOption("runIndex", true,
				"index of the run in the benchmark series");

		// modification constants
		options.addOption(
				"modificationMethod",
				true,
				"options: constant -- modify a fixed number of elements, resultSet -- modify based a number of elements based on the size of the results set");
		options.addOption("iterationCount", true,
				"number of modify-check iterations");
		options.addOption("modificationConstant", true,
				"modification constant for the modification method");
		options.addOption("analyze", false,
				"flag for calculating metrics");
	}

	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		// queries argument -> testCases list
		query = Query.valueOf(cmd.getOptionValue("query").toUpperCase());

		final String modificationMethodString = cmd
				.getOptionValue("modificationMethod");
		if (modificationMethodString != null) {
			switch (modificationMethodString) {
			case "constant":
				modificationMethod = ModificationMethod.CONSTANT;
				break;
			case "resultSet":
				modificationMethod = ModificationMethod.RESULT_SET;
				break;
			default:
				throw new ParseException(
						"Invalid modification method specified");
			}
		} else {
			modificationMethod = ModificationMethod.CONSTANT;
		}

		final String iterationCountString = cmd
				.getOptionValue("iterationCount");
		if (iterationCountString != null) {
			iterationCount = new Integer(iterationCountString);
		} else {
			iterationCount = 10;
		}

		final String runIndexString = cmd.getOptionValue("runIndex");
		if (runIndexString != null) {
			runIndex = new Integer(runIndexString);
		} else {
			runIndex = -1;
		}

		modificationConstant = 1;
		modificationConstant = determineModificationConstant("modificationConstant");

		final String benchmarkModeString = cmd
				.getOptionValue("benchmarkMode");
		if (benchmarkModeString != null) {
			benchmarkMode = new Boolean(benchmarkModeString);
		} else {
			benchmarkMode = false;
		}

		analyze = cmd.hasOption("analyze");
	}

	private long determineModificationConstant(final String optionName) {
		if (cmd.getOptionValue(optionName) != null) {
			return new Long(cmd.getOptionValue(optionName));
		} else {
			return modificationConstant;
		}
	}

	public ModificationMethod getModificationMethod() {
		return modificationMethod;
	}

	public long getModificationConstant() {
		return modificationConstant;
	}

	public boolean isBenchmarkMode() {
		return benchmarkMode;
	}

	public int getIterationCount() {
		return iterationCount;
	}

	public Query getQuery() {
		return query;
	}

	public String getClassName() {
		return className;
	}

	public String getTool() {
		return getClassName();
	}

	public boolean isAnalyze() {
		return analyze;
	}

	public void setAnalyze(boolean analyze) {
		this.analyze = analyze;
	}

}
