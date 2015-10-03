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
package hu.bme.mit.trainbenchmark.benchmark.iqdcore.config;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.constants.TransformationStrategy;

public class IQDCoreBenchmarkConfig extends RDFBenchmarkConfig {

	protected static final String IQDCORE = "IQDCore";
	protected static final String CPULIST = "cpulist";

	protected String cpulist;

	public IQDCoreBenchmarkConfig(final String[] args) throws ParseException {
		super(args, IQDCORE);
	}

	public IQDCoreBenchmarkConfig(final Scenario scenario, final int size, final int runIndex, final Query query,
			final int iterationCount, final TransformationStrategy transformationStrategy, final long transformationConstant,
			final String cpulist) {
		super(IQDCORE, scenario, size, runIndex, query, iterationCount, transformationStrategy, transformationConstant, false);
		this.cpulist = cpulist;
	}

	@Override
	protected void initOptions() {
		super.initOptions();

		options.addOption(CPULIST, true, "uses the passed cores only");
	}

	@Override
	public void processArguments(final String[] args) throws ParseException {
		super.processArguments(args);
		cpulist = cmd.getOptionValue(CPULIST);
	}

	public boolean isCPURestricted() {
		return cpulist != null;
	}
	
	public String getCpuList() {
		return cpulist;
	}

	@Override
	public String getTool() {
		if (isCPURestricted()) {
			int coreCount = cpulist.split(",").length;
			return String.format("IQDCore-%d", coreCount);
		} else {
			return "IQDCore";
		}
	}

}
