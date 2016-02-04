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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.config;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.ScenarioEnum;
import hu.bme.mit.trainbenchmark.constants.TransformationStrategy;

public class Neo4jBenchmarkConfig extends BenchmarkConfig {

	protected static final String NEO4J = "Neo4j";
	protected static final String ENGINE = "engine";

	protected Neo4jEngine engine;

	public Neo4jBenchmarkConfig(final String[] args) throws ParseException {
		super(NEO4J, args);
	}

	public Neo4jBenchmarkConfig(final ScenarioEnum scenario, final int size, final int runIndex, final RailwayQuery query, final int iterationCount,
			final TransformationStrategy transformationStrategy, final long transformationConstant, final Neo4jEngine engine) {
		super(NEO4J, scenario, size, runIndex, query, iterationCount, transformationStrategy, transformationConstant);
		this.engine = engine;
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption(ENGINE, true, "query engine: coreapi (Core Java API), cypher (Cypher query language)");
	}

	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		engine = Neo4jEngine.valueOf(cmd.getOptionValue(ENGINE).toUpperCase());
	}

	public Neo4jEngine getEngine() {
		return engine;
	}
	
	@Override
	public String getToolName() {
		return super.getToolName() + "_(" + engine.toString() + ")";
	}
}
