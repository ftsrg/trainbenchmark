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

package hu.bme.mit.trainbenchmark.generator.graph.config;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

import org.apache.commons.cli.ParseException;

public class GraphGeneratorConfig extends GeneratorConfig {

	protected boolean orientdb;

	public GraphGeneratorConfig(String[] args) throws ParseException {
		super(args);
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption("orientdb", false, "generate graphml for orientdb");
	}

	@Override
	public void processArguments(String[] args) throws ParseException {
		super.processArguments(args);
		
		orientdb = cmd.hasOption("orientdb");
	}
	
	public boolean isOrientDb() {
		return orientdb;
	}

}
