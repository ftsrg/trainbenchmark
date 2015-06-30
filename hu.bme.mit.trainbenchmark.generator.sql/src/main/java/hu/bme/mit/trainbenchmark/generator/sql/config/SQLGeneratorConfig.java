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
package hu.bme.mit.trainbenchmark.generator.sql.config;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

import org.apache.commons.cli.ParseException;

public class SQLGeneratorConfig extends GeneratorConfig {

	protected static final String MEMSQL = "memsql";
	
	protected boolean memSQL;
	
	public SQLGeneratorConfig(final String[] args) throws ParseException {
		super(args);
	}
	
	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption(MEMSQL, false, "generate model for MemSQL");
	}

	@Override
	protected void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		memSQL = cmd.hasOption(MEMSQL);
	}

	public boolean isMemSQL() {
		return memSQL;
	}

}
