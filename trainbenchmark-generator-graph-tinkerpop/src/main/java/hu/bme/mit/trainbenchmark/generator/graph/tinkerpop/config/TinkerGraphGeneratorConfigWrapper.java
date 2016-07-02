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

public class TinkerGraphGeneratorConfigWrapper extends GeneratorConfigWrapper {

	protected TinkerGraphFormat graphFormat;
	
	protected TinkerGraphGeneratorConfigWrapper() {
	}
	
	public TinkerGraphGeneratorConfigWrapper(final GeneratorConfig generatorConfig, final TinkerGraphFormat graphFormat) {
		super(generatorConfig);
		this.graphFormat = graphFormat;
	}
	
	public TinkerGraphFormat getGraphFormat() {
		return graphFormat;
	}

	@Override
	public String getProjectName() {
		return "graph-tinkerpop";
	}

}
