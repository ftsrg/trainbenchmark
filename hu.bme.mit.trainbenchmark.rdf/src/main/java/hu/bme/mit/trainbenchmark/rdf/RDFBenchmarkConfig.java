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

package hu.bme.mit.trainbenchmark.rdf;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.config.ModificationMethod;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

import org.apache.commons.cli.ParseException;

public class RDFBenchmarkConfig extends BenchmarkConfig {

	protected boolean inferencing;

	public RDFBenchmarkConfig(final String[] args, final String tool) throws ParseException {
		super(args, tool);
	}

	public RDFBenchmarkConfig(final Scenario scenario, final int size, final String tool, final int runIndex, final Query query,
			final int iterationCount, final ModificationMethod modificationMethod, final long modificationConstant) {
		super(scenario, size, tool, runIndex, query, iterationCount, modificationMethod, modificationConstant);
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption("inferencing", true, "RDF: type of inference");
	}

	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		inferencing = cmd.hasOption("inferencing");
	}

	public boolean isInferencing() {
		return inferencing;
	}
}
