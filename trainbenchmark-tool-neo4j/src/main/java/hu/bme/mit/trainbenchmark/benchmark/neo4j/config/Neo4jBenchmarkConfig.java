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

public class Neo4jBenchmarkConfig extends BenchmarkConfig {

	protected Neo4jEngine engine;

	protected Neo4jBenchmarkConfig(final BenchmarkConfigBase configBase, final Neo4jEngine engine) {
		super(configBase);
		this.engine = engine;
	}

	public Neo4jEngine getEngine() {
		return engine;
	}

	@Override
	public String getToolName() {
		return String.format("Neo4j (%s)", getEngine());
	}

	@Override
	public String getProjectName() {
		return "neo4j";
	}

}
