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

package hu.bme.mit.trainbenchmark.generator.rdf.config;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.rdf.RDFFormat;

import org.apache.commons.cli.ParseException;

public class RDFGeneratorConfig extends GeneratorConfig {

	protected boolean metamodel;
	protected RDFFormat rdfFormat;

	public RDFGeneratorConfig(String[] args) throws ParseException {
		super(args);
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption("metamodel", false, "generate metamodel and container object and relations for RDF TBox");
		options.addOption("format", true, "the format of the RDF output: turtle (default), rdfxml");
	}

	@Override
	protected void processArguments(String[] args) throws ParseException {
		super.processArguments(args);

		metamodel = cmd.hasOption("metamodel");

		if (cmd.hasOption("format")) {
			switch (cmd.getOptionValue("format")) {
			case "turtle":
				rdfFormat = RDFFormat.TURTLE;
				break;
			case "rdfxml":
				rdfFormat = RDFFormat.RDFXML;
				break;
			default:
				throw new ParseException("Unsupported format for RDF generator.");
			}
		} else {
			rdfFormat = RDFFormat.TURTLE;
		}
	}

	public boolean isMetamodel() {
		return metamodel;
	}

	public RDFFormat getRdfFormat() {
		return rdfFormat;
	}

}
