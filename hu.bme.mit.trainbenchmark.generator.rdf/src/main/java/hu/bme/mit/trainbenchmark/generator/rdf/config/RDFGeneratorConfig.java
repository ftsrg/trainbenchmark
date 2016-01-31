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

import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.rdf.RDFFormat;

public class RDFGeneratorConfig extends GeneratorConfig {

	protected static final String INFERRED = "inferred";
	protected static final String FORMAT = "format";
	
	protected boolean inferred;
	protected RDFFormat format; 

	public RDFGeneratorConfig(final String[] args) throws ParseException {
		super(args);
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption(INFERRED, false, "insert the inferred tuples instead of inserting the metamodel");
		options.addOption(FORMAT, true, "specify the format: turtle (default), ntriples");
		Option formatOption = options.getOption(FORMAT);
		formatOption.setRequired(true);
		options.addOption(formatOption);
	}

	@Override
	protected void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		inferred = cmd.hasOption(INFERRED);
		format = RDFFormat.valueOf(cmd.getOptionValue(FORMAT).toUpperCase());
	}

	public boolean isInferred() {
		return inferred;
	}

	public RDFFormat getFormat() {
		return format;
	}
	
	public String getModelFlavor() {
		return isInferred() ? "-inferred" : "-metamodel";
	}

	public String getExtension() {
		return format.getExtension();
	}
	
}
