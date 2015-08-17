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

package hu.bme.mit.trainbenchmark.generator.rdf.config;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public class RDFGeneratorConfig extends GeneratorConfig {

	protected static final String METAMODEL = "metamodel";
	protected boolean metamodel;

	public RDFGeneratorConfig(final String[] args) throws ParseException {
		super(args);
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption(METAMODEL, false, "generate metamodel and container object and relations for RDF TBox");
	}

	@Override
	protected void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		metamodel = cmd.hasOption(METAMODEL);
	}

	public boolean isMetamodel() {
		return metamodel;
	}

}
