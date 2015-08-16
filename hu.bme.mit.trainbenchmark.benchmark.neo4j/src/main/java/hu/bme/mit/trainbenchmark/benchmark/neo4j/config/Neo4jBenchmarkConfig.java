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

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.config.ModificationMethod;
import hu.bme.mit.trainbenchmark.constants.ModelType;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.ScenarioConstants;

import org.apache.commons.cli.ParseException;

public class Neo4jBenchmarkConfig extends BenchmarkConfig {

	protected static final String NEO4J = "Neo4j";
	protected boolean javaApi;

	public Neo4jBenchmarkConfig(final String[] args) throws ParseException {
		super(args, NEO4J);
	}

	public Neo4jBenchmarkConfig(final ScenarioConstants scenario, final int size, final int runIndex,
			final Query query, final int iterationCount,
			final ModificationMethod modificationMethod, final long modificationConstant,
			final boolean javaApi, final ModelType modelType) {
		super(NEO4J, scenario, size, runIndex, query, iterationCount, modificationMethod,
				modificationConstant, false, modelType);
		this.javaApi = javaApi;
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption("javaapi", false, "use the faster, low-level Java API for querying");
	}

	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		javaApi = cmd.hasOption("javaapi");
	}

	public boolean isJavaApi() {
		return javaApi;
	}

}
