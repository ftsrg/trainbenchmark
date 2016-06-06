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

package hu.bme.mit.trainbenchmark.benchmark.rdf;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;
import hu.bme.mit.trainbenchmark.constants.TransformationStrategy;

public class RdfBenchmarkConfig extends BenchmarkConfig {

	protected static final String INFERENCING = "inferencing";
	protected boolean inferencing;

	public RdfBenchmarkConfig(final String toolName, final String[] args) throws ParseException {
		super();
	}

	public RdfBenchmarkConfig(final String className, final ScenarioEnum scenario, final int size, final int runIndex, final RailwayQuery query,
			final int iterationCount, final TransformationStrategy transformationStrategy, final long transformationConstant,
			final boolean inferencing) {
		super(className, scenario, size, runIndex, query, iterationCount, transformationStrategy, transformationConstant);
		this.inferencing = inferencing;
	}

	@Override
	protected void initOptions() {
		super.initOptions();

//		options.addOption(INFERENCING, false, "Use type inferencing");
	}

	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		inferencing = cmd.hasOption(INFERENCING);
	}

	public boolean isInferencing() {
		return inferencing;
	}

	@Override
	public String getToolName() {
		return super.getToolName() + (isInferencing() ? "_(Inferencing)" : "_(No_Inferencing)");
	}
}
