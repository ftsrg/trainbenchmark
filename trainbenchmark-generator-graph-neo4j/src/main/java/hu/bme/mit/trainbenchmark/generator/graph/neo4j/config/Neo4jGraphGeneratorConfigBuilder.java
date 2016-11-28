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

import com.google.common.base.Preconditions;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBuilder;
import hu.bme.mit.trainbenchmark.neo4j.config.Neo4jGraphFormat;

public class Neo4jGraphGeneratorConfigBuilder extends GeneratorConfigBuilder<Neo4jGraphGeneratorConfig, Neo4jGraphGeneratorConfigBuilder> {

	protected Neo4jGraphFormat graphFormat;

	public Neo4jGraphGeneratorConfigBuilder setGraphFormat(Neo4jGraphFormat graphFormat) {
		this.graphFormat = graphFormat;
		return this;
	}

	@Override
	public Neo4jGraphGeneratorConfig createConfig() {
		return new Neo4jGraphGeneratorConfig(configBase, graphFormat);
	}

	@Override
	public void checkNotNulls() {
		super.checkNotNulls();
		Preconditions.checkNotNull(graphFormat);
	}

}
