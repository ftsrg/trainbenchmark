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
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.neo4j.config.Neo4jDeployment;
import hu.bme.mit.trainbenchmark.neo4j.config.Neo4jGraphFormat;

public class Neo4jBenchmarkConfig extends BenchmarkConfig {

	protected final Neo4jDeployment deployment;
	protected final Neo4jEngine engine;
	protected final Neo4jGraphFormat graphFormat;

	protected Neo4jBenchmarkConfig(final BenchmarkConfigBase configBase, final Neo4jDeployment deployment, final Neo4jEngine engine, final Neo4jGraphFormat graphFormat) {
		super(configBase);
		this.deployment = deployment;
		this.engine = engine;
		this.graphFormat = graphFormat;
	}

	public Neo4jDeployment getDeployment() {
		return deployment;
	}

	public Neo4jEngine getEngine() {
		return engine;
	}

	public Neo4jGraphFormat getGraphFormat() {
		return graphFormat;
	}

	@Override
	public String getToolName() {
		return String.format("Neo4j (%s-%s-%s)", getDeployment(), getEngine(), getGraphFormat());
	}

	@Override
	public String getProjectName() {
		return "neo4j";
	}

}
