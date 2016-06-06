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

package hu.bme.mit.trainbenchmark.benchmark.iqdcore;

import hu.bme.mit.incqueryds.WildcardInput;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.benchmarkcases.IQDCoreChecker;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.config.IQDCoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IQDCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.IQDCoreTransformation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;

public class IQDCoreBenchmarkCase extends AbstractBenchmarkCase<IQDCoreMatch, Long, IQDCoreDriver, IQDCoreBenchmarkConfig, IQDCoreChecker {

	protected final IQDCoreBenchmarkConfig iqdbc;
	protected WildcardInput iqdInput;
	protected RDFBenchmarkConfig rdfbc;
	protected IQDCoreChecker checker;

	public IQDCoreBenchmarkCase(IQDCoreBenchmarkConfig config) {
		iqdbc = config;
		setCPUAffinity();
	}

	public void setCPUAffinity() {
		if (iqdbc.isCPURestricted()) {
			String cpulist = iqdbc.getCpuList();
			try {
				int pid = Integer.parseInt(new File("/proc/self").getCanonicalFile().getName());
				Runtime.getRuntime().exec(String.format("taskset -a -p -c %s %d", cpulist, pid));
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}

	@Override
	public IQDCoreDriver createDriver(IQDCoreBenchmarkConfig benchmarkConfig) throws Exception {
        iqdInput = new WildcardInput(benchmarkConfig.getMessageSize());
		try {
			checker = new IQDCoreChecker(iqdInput, iqdbc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new IQDCoreDriver(rdfbc, iqdInput, checker);
	}

	@Override
	public IQDCoreChecker createChecker(IQDCoreBenchmarkConfig benchmarkConfig, IQDCoreDriver driver, RailwayQuery query) throws Exception {
		return checker;
	}

	@Override
	public ModelTransformation<?, ?> createTransformation(IQDCoreBenchmarkConfig benchmarkConfig, IQDCoreDriver driver, RailwayQuery query) throws IOException {
		return IQDCoreTransformation.newInstance(driver, benchmarkConfig.getQuery(), benchmarkConfig.getScenario());
	}

	@Override
	public Comparator<?> getMatchComparator() {
		return new IQDCoreMatchComparator();
	}
}