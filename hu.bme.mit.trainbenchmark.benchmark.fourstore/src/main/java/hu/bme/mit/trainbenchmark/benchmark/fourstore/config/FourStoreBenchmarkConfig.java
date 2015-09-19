/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.fourstore.config;

import hu.bme.mit.trainbenchmark.benchmark.config.ModificationMethod;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.ModelType;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.ScenarioConstants;

import org.apache.commons.cli.ParseException;

public class FourStoreBenchmarkConfig extends RDFBenchmarkConfig {

	protected static final String FOURSTORE = "FourStore";

	protected boolean cluster;
	protected boolean showCommandOutput;
	protected boolean showUpdateCommands;

	public FourStoreBenchmarkConfig(final String[] args, final String tool) throws ParseException {
		super(args, tool);
	}

	public FourStoreBenchmarkConfig(final ScenarioConstants scenario, final int size, final int runIndex,
			final Query query, final int iterationCount,
			final ModificationMethod modificationMethod, final long modificationConstant,
			final ModelType modelType) {
		super(FOURSTORE, scenario, size, runIndex, query, iterationCount, modificationMethod,
				modificationConstant, modelType);
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption("cluster", false, "run the benchmark in a cluster");
		options.addOption("showCommandOutput", false,
				"show the results of the command line applications (e.g. 4s-backend, 4s-import)");
		options.addOption("showUpdateCommands", false, "show 4s-update commands");
	}

	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		cluster = cmd.hasOption("cluster");
		showCommandOutput = cmd.hasOption("showCommandOutput");
		showUpdateCommands = cmd.hasOption("showUpdateCommands");
	}

	public boolean isCluster() {
		return cluster;
	}

	public boolean isShowCommandOutput() {
		return showCommandOutput;
	}

	public boolean isShowUpdateCommands() {
		return showUpdateCommands;
	}

}
