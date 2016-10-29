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

package hu.bme.mit.trainbenchmark.generator.graph.neo4j.config;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public class Neo4jGraphGeneratorConfig extends GeneratorConfig {

	protected Neo4jGraphGeneratorConfig() {
	}
	
	public Neo4jGraphGeneratorConfig(final GeneratorConfigBase generatorConfig) {
		super(generatorConfig);
	}

	@Override
	public String getProjectName() {
		return "graph-neo4j";
	}
	
}