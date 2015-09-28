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
import hu.bme.mit.trainbenchmark.constants.TransformationStategy;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public class Neo4jBenchmarkConfig extends BenchmarkConfig {

	protected static final String NEO4J = "Neo4j";
	protected static final String COREAPI = "coreapi";

	protected boolean coreApi;

	public Neo4jBenchmarkConfig(final String[] args) throws ParseException {
		super(args, NEO4J);
	}

	public Neo4jBenchmarkConfig(final Scenario scenario, final int size, final int runIndex, final Query query, final int iterationCount,
			final TransformationStategy modificationMethod, final long modificationConstant, final boolean coreApi) {
		super(NEO4J, scenario, size, runIndex, query, iterationCount, modificationMethod, modificationConstant);
		this.coreApi = coreApi;
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption(COREAPI, false, "use the faster, low-level core API for querying");
	}

	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		coreApi = cmd.hasOption(COREAPI);
	}

	public boolean isCoreApi() {
		return coreApi;
	}

	@Override
	public String getTool() {
		return super.getTool() + (isCoreApi() ? "-CoreAPI" : "-Cypher");
	}
}
