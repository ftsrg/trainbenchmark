/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
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
import hu.bme.mit.trainbenchmark.constants.Scenario;

import org.apache.commons.cli.ParseException;

public class Neo4jBenchmarkConfig extends BenchmarkConfig {

	protected boolean javaApi;

	public Neo4jBenchmarkConfig(final String[] args, final String tool) throws ParseException {
		super(args, tool);
	}

	public Neo4jBenchmarkConfig(final Scenario scenario, final int size, final String tool, final int runIndex, final String query, final int iterationCount,
			final ModificationMethod modificationMethod, final long modificationConstant, final boolean javaApi) {
		super(scenario, size, tool, runIndex, query, iterationCount, modificationMethod, modificationConstant);
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
