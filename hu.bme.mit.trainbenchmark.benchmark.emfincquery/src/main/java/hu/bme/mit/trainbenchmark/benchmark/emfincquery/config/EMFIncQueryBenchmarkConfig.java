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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.config.ModificationMethod;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

import org.apache.commons.cli.ParseException;

public class EMFIncQueryBenchmarkConfig extends BenchmarkConfig {

	private static final String EMFINCQUERY = "EMFIncQuery";
	private static final String LOCALSEARCH = "localSearch";
	private boolean localSearch;

	public EMFIncQueryBenchmarkConfig(final String[] args) throws ParseException {
		super(args, EMFINCQUERY);
	}

	public EMFIncQueryBenchmarkConfig(final Scenario scenario, final int size, final int runIndex, final Query query,
			final int iterationCount, final ModificationMethod modificationMethod, final long modificationConstant, final boolean localSearch) {
		super(EMFINCQUERY, scenario, size, runIndex, query, iterationCount, modificationMethod, modificationConstant);
		this.localSearch = localSearch;
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption(LOCALSEARCH, false, "uses the local search strategy for pattern matching");
	}

	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);

		localSearch = cmd.hasOption(LOCALSEARCH);
	}

	public boolean isLocalSearch() {
		return localSearch;
	}

	@Override
	public String getTool() {
		if (isLocalSearch()) {
			return "EMFIncQuery-LocalSearch";
		} else {
			return "EMFIncQuery-Incremental";
		}
	}

}
