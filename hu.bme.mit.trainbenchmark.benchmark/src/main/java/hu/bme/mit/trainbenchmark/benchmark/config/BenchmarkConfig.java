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

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.config.TrainBenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public class BenchmarkConfig extends TrainBenchmarkConfig {

	protected static final String RUNS = "runs";
	protected static final String MODIFICATION_CONSTANT = "modificationConstant";
	protected static final String ITERATION_COUNT = "iterationCount";
	protected static final String MODIFICATION_METHOD = "modificationMethod";

	// modification constants
	protected ModificationMethod modificationMethod;
	protected long modificationConstant;

	protected boolean benchmarkMode;
	protected int iterationCount;
	protected int runs;
	protected String className;

	public BenchmarkConfig(final String args[], final String className) throws ParseException {
		super(args);
		this.className = className;
	}

	public BenchmarkConfig(final String className, final Scenario scenario, final int size, final int runs, final Query query,
			final int iterationCount, final ModificationMethod modificationMethod, final long modificationConstant) {
		super(scenario, size);
		this.className = className;
		this.runs = runs;
		this.query = query;
		this.iterationCount = iterationCount;
		this.modificationMethod = modificationMethod;
		this.modificationConstant = modificationConstant;
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		// the "size" and "query" options are required for the BenchmarkConfig but not required for the GeneratorConfig
		options.getOption(SIZE).setRequired(true);
		options.getOption(QUERY).setRequired(true);

		options.addOption(RUNS, true, "number of runs");
		options.getOption(RUNS).setRequired(true);

		// modification constants
		options.addOption(MODIFICATION_METHOD, true,
				"options: constant -- modify a fixed number of elements, resultSet -- modify based a number of elements based on the size of the results set");
		options.addOption(ITERATION_COUNT, true, "number of modify-check iterations");
		options.addOption(MODIFICATION_CONSTANT, true, "modification constant for the modification method");
	}

	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		final String modificationMethodString = cmd.getOptionValue(MODIFICATION_METHOD);
		if (modificationMethodString != null) {
			switch (modificationMethodString) {
			case "constant":
				modificationMethod = ModificationMethod.CONSTANT;
				break;
			case "resultSet":
				modificationMethod = ModificationMethod.RESULT_SET;
				break;
			default:
				throw new ParseException("Invalid modification method specified");
			}
		} else {
			modificationMethod = ModificationMethod.CONSTANT;
		}

		runs = new Integer(cmd.getOptionValue(RUNS));

		final String iterationCountString = cmd.getOptionValue(ITERATION_COUNT);
		if (iterationCountString != null) {
			iterationCount = new Integer(iterationCountString);
		} else {
			iterationCount = 10;
		}

		modificationConstant = determineModificationConstant(MODIFICATION_CONSTANT);
	}

	private long determineModificationConstant(final String optionName) {
		if (cmd.getOptionValue(optionName) != null) {
			return new Long(cmd.getOptionValue(optionName));
		} else {
			return 1;
		}
	}

	public ModificationMethod getModificationMethod() {
		return modificationMethod;
	}

	public long getModificationConstant() {
		return modificationConstant;
	}

	public int getIterationCount() {
		return iterationCount;
	}

	public int getRuns() {
		return runs;
	}

	public String getClassName() {
		return className;
	}

	public String getTool() {
		return getClassName();
	}
}
