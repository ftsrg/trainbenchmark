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

package hu.bme.mit.trainbenchmark.benchmark.rdf;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.TransformationStategy;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public class RDFBenchmarkConfig extends BenchmarkConfig {

	protected static final String INFERENCING = "inferencing";
	protected boolean inferencing;

	public RDFBenchmarkConfig(final String[] args, final String className) throws ParseException {
		super(args, className);
	}

	public RDFBenchmarkConfig(final String className, final Scenario scenario, final int size, final int runIndex, final Query query,
			final int iterationCount, final TransformationStategy modificationMethod, final long modificationConstant,
			final boolean inferencing) {
		super(className, scenario, size, runIndex, query, iterationCount, modificationMethod, modificationConstant);
		this.inferencing = inferencing;
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption(INFERENCING, false, "Use type inferencing");
	}

	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		inferencing = cmd.hasOption(INFERENCING);
	}

	public boolean isInferencing() {
		return inferencing;
	}

	@Override
	public String getTool() {
		return super.getTool() + (isInferencing() ? "-Inferencing" : "-NoInferencing");
	}
}
