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

package hu.bme.mit.trainbenchmark.generator.graph;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.generator.Generator;
import hu.bme.mit.trainbenchmark.generator.GeneratorFactory;
import hu.bme.mit.trainbenchmark.generator.graph.config.GraphGeneratorConfig;

public class GraphGeneratorFactory extends GeneratorFactory{

	protected GraphGeneratorConfig graphGeneratorConfig;
	
	public GraphGeneratorFactory(GraphGeneratorConfig generatorConfig) throws ParseException {
		super(generatorConfig);
		this.graphGeneratorConfig = generatorConfig;
	}

	@Override
	protected Generator getRailwayGenerator() {
		return new GraphRailwayGenerator(graphGeneratorConfig);
	}

	@Override
	protected Generator getScheduleGenerator() {
		return new GraphScheduleGenerator(graphGeneratorConfig, new GraphRailwayGenerator(graphGeneratorConfig));
	}

}
