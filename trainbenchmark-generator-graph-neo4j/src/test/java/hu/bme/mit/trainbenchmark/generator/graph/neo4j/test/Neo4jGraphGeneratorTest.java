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

package hu.bme.mit.trainbenchmark.generator.graph.neo4j.test;

import java.util.List;

import com.google.common.collect.ImmutableList;

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.generator.graph.neo4j.Neo4jGraphSerializer;
import hu.bme.mit.trainbenchmark.generator.graph.neo4j.config.Neo4jGraphGeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.graph.neo4j.config.Neo4jGraphGeneratorConfigBuilder;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorTest;
import hu.bme.mit.trainbenchmark.neo4j.config.Neo4jGraphFormat;

public class Neo4jGraphGeneratorTest extends GeneratorTest {

	protected final List<Neo4jGraphFormat> graphFormats = ImmutableList.of(
			Neo4jGraphFormat.BINARY, //
			Neo4jGraphFormat.CSV, //
			Neo4jGraphFormat.GRAPHML);

	@Override
	public void generate(final GeneratorConfigBase gcb) throws Exception {
		for (Neo4jGraphFormat graphFormat : graphFormats) {
			final Neo4jGraphGeneratorConfig gc = new Neo4jGraphGeneratorConfigBuilder().setConfigBase(gcb)
					.setGraphFormat(graphFormat).createConfig();
			final Neo4jGraphSerializer serializer = new Neo4jGraphSerializer(gc);
			final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gc);
			generator.generateModel();
		}
	}

}