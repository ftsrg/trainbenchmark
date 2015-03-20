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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.config.ModificationMethod;
import hu.bme.mit.trainbenchmark.constants.Scenario;

import org.apache.commons.cli.ParseException;

public class EMFIncQueryBenchmarkConfig extends BenchmarkConfig {

	protected String functionalDependencyOption;
	protected String stubTrimOption;
	protected boolean deltaMonitor;
	
	public EMFIncQueryBenchmarkConfig(final String[] args, final String tool) throws ParseException {
		super(args, tool);
	}

	public EMFIncQueryBenchmarkConfig(final Scenario scenario, final int size, final String tool, final int runIndex, final String query, final int iterationCount,
			final ModificationMethod modificationMethod, final long modificationConstant) {
		super(scenario, size, tool, runIndex, query, iterationCount, modificationMethod, modificationConstant);
	}
		
	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption("functionalDependencyOption", true, "FunctionalDependencyOption");
		options.addOption("stubTrimOption", true, "StubTrimOption");
		options.addOption("noDeltaMonitor", false, "do not use DeltaMonitor");
	}
	
	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		functionalDependencyOption = cmd.getOptionValue("functionalDependencyOption");
		stubTrimOption = cmd.getOptionValue("stubTrimOption");
		if (cmd.hasOption("noDeltaMonitor")) {
			deltaMonitor = false;
		} else {
			deltaMonitor = true;
		}
	}

	public String getFunctionalDependencyOption() {
		return functionalDependencyOption;
	}

	public String getStubTrimOption() {
		return stubTrimOption;
	}
	
	public boolean isDeltaMonitor() {
		return deltaMonitor;
	}
	
}
