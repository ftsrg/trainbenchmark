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
package hu.bme.mit.trainbenchmark.benchmark.hawk.config;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.constants.TransformationStrategy;

public class HawkBenchmarkConfig extends EMFIncQueryBenchmarkConfig {

	protected static final String HAWK = "Hawk";
	protected static final String USE_HAWK_SCOPE = "useHawkScope";
	
	protected boolean useHawkScope;

	public HawkBenchmarkConfig(final String[] args) throws ParseException {
		super(args, HAWK);
	}

	public HawkBenchmarkConfig(final Scenario scenario, final int size, final int runIndex, final Query query, final int iterationCount,
			final TransformationStrategy transformationStrategy, final long transformationConstant) {
		super(HAWK, scenario, size, runIndex, query, iterationCount, transformationStrategy, transformationConstant, false);
	}

	@Override
	protected void initOptions() {
		super.initOptions();
		
		options.addOption(USE_HAWK_SCOPE, false, "Use the HawkScope (should be faster)");
	}

	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);
		
		useHawkScope = options.hasOption(USE_HAWK_SCOPE);
	}
	
	public boolean isUseHawkScope() {
		return useHawkScope;
	}

}
