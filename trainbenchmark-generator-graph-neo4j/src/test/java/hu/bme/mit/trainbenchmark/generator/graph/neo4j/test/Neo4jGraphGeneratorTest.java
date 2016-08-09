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

import hu.bme.mit.trainbenchmark.generator.ModelGenerator;
import hu.bme.mit.trainbenchmark.generator.ScalableGeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.graph.neo4j.Neo4jGraphSerializer;
import hu.bme.mit.trainbenchmark.generator.graph.neo4j.config.Neo4jGraphGeneratorConfigWrapper;
import hu.bme.mit.trainbenchmark.generator.tests.GeneratorBaseTest;

public class Neo4jGraphGeneratorTest extends GeneratorBaseTest {

	@Override
	public void generate(final GeneratorConfig gc) throws Exception {
		final Neo4jGraphGeneratorConfigWrapper gcw = new Neo4jGraphGeneratorConfigWrapper(gc);
		final Neo4jGraphSerializer serializer = new Neo4jGraphSerializer(gcw);
		final ModelGenerator generator = ScalableGeneratorFactory.createGenerator(serializer, gcw);
		generator.generateModel();
	}

}