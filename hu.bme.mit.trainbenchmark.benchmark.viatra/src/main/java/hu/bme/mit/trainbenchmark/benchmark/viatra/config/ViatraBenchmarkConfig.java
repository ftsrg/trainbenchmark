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
package hu.bme.mit.trainbenchmark.benchmark.viatra.config;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;
import hu.bme.mit.trainbenchmark.constants.TransformationStrategy;

public class ViatraBenchmarkConfig extends BenchmarkConfig {

	protected static final String VIATRA = "VIATRA";
	protected static final String BACKEND = "backend";

	protected ViatraBackend backend;

	public ViatraBenchmarkConfig(final String[] args) throws ParseException {
		super();
	}

	public ViatraBenchmarkConfig(final ScenarioEnum scenario, final int size, final int runIndex, final RailwayQuery query,
			final int iterationCount, final TransformationStrategy transformationStrategy,
			final long transformationConstant, final ViatraBackend backend) {
		super(VIATRA, scenario, size, runIndex, query, iterationCount, transformationStrategy,
				transformationConstant);
		this.backend = backend;
	}

	@Override
	protected void initOptions() {
		super.initOptions();
		
//		options.addOption(BACKEND, true, "set the backend for VIATRA Query. Options: incremental, localsearch.");
//		final Option backendOption = options.getOption(BACKEND);
//		backendOption.setRequired(true);
//		options.addOption(backendOption);
	}

	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		backend = ViatraBackend.valueOf(cmd.getOptionValue(BACKEND).toUpperCase());
	}

	public ViatraBackend getBackend() {
		return backend;
	}

	@Override
	public String getToolName() {
		return super.getToolName() + "_(" + backend.toString() + ")";
	}

}
