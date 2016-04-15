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

package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;
import hu.bme.mit.trainbenchmark.constants.TransformationStrategy;

public class TinkerGraphBenchmarkConfig extends BenchmarkConfig {

	protected static final String TINKERGRAPH = "TinkerGraph";

	public TinkerGraphBenchmarkConfig(final String[] args) throws ParseException {
		super(TINKERGRAPH, args);
	}

	public TinkerGraphBenchmarkConfig(final ScenarioEnum scenario, final int size, final int runIndex, final RailwayQuery query, final int iterationCount,
			final TransformationStrategy transformationStrategy, final long transformationConstant, final TinkerGraphEngine engine) {
		super(TINKERGRAPH, scenario, size, runIndex, query, iterationCount, transformationStrategy, transformationConstant);
	}

	@Override
	protected void initOptions() {
		super.initOptions();
	}

	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);
	}
	
	@Override
	public String getToolName() {
		return super.getToolName();
	}
}
