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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.config;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;
import hu.bme.mit.trainbenchmark.constants.TransformationStrategy;

public class EMFIncQueryBenchmarkConfig extends BenchmarkConfig {

	protected static final String EMFINCQUERY = "EMF-IncQuery";
	protected static final String BACKEND = "backend";

	protected EMFIncQueryBackend backend;

	public EMFIncQueryBenchmarkConfig(final String[] args) throws ParseException {
		super(EMFINCQUERY, args);
	}

	// for Hawk
	protected EMFIncQueryBenchmarkConfig(final String[] args, final String toolName) throws ParseException {
		super(toolName, args);
	}

	public EMFIncQueryBenchmarkConfig(final ScenarioEnum scenario, final int size, final int runIndex, final RailwayQuery query,
			final int iterationCount, final TransformationStrategy transformationStrategy,
			final long transformationConstant, final EMFIncQueryBackend backend) {
		super(EMFINCQUERY, scenario, size, runIndex, query, iterationCount, transformationStrategy,
				transformationConstant);
		this.backend = backend;
	}

	// for Hawk
	protected EMFIncQueryBenchmarkConfig(final String className, final ScenarioEnum scenario, final int size,
			final int runIndex, final RailwayQuery query, final int iterationCount,
			final TransformationStrategy transformationStrategy, final long transformationConstant,
			final boolean localSearch) {
		super(className, scenario, size, runIndex, query, iterationCount, transformationStrategy,
				transformationConstant);
	}

	@Override
	protected void initOptions() {
		super.initOptions();
		
		options.addOption(BACKEND, true, "set the IncQuery backend: incremental or local search");
		Option backendOption = options.getOption(BACKEND);
		backendOption.setRequired(true);
		options.addOption(backendOption);
	}

	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		backend = EMFIncQueryBackend.valueOf(cmd.getOptionValue(BACKEND).toUpperCase());
	}

	public EMFIncQueryBackend getBackend() {
		return backend;
	}

	@Override
	public String getToolName() {
		return super.getToolName() + "_(" + backend.toString() + ")";
	}

}
