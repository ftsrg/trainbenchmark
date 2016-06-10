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

package hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigWrapper;

public class TinkerPopGraphGeneratorConfigWrapper extends GeneratorConfigWrapper {

	protected GraphFormat graphFormat;
	
	protected TinkerPopGraphGeneratorConfigWrapper() {
	}
	
	public TinkerPopGraphGeneratorConfigWrapper(final GeneratorConfig generatorConfig, final GraphFormat graphFormat) {
		super(generatorConfig);
		this.graphFormat = graphFormat;
	}
	
	public GraphFormat getGraphFormat() {
		return graphFormat;
	}
	
	public static TinkerPopGraphGeneratorConfigWrapper fromFile(final String path) throws FileNotFoundException {
		final Kryo kryo = new Kryo();
		try (final Input input = new Input(new FileInputStream(path))) {
			final TinkerPopGraphGeneratorConfigWrapper benchmarkConfig = kryo.readObject(input, TinkerPopGraphGeneratorConfigWrapper.class);
			return benchmarkConfig;
		}
	}

}
