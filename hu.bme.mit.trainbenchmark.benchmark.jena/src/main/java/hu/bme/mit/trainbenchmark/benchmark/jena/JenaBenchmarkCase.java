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

package hu.bme.mit.trainbenchmark.benchmark.jena;

import java.io.IOException;
import java.util.Comparator;

import org.apache.jena.rdf.model.Resource;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.jena.benchmarkcases.JenaChecker;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.match.JenaMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.match.JenaMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.JenaTransformation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RdfBenchmarkConfig;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class JenaBenchmarkCase
		extends AbstractBenchmarkCase<JenaMatch, Resource, JenaDriver, RdfBenchmarkConfig, JenaChecker> {

	@Override
	public JenaDriver createDriver(final RdfBenchmarkConfig benchmarkConfig) throws Exception {
		return new JenaDriver(benchmarkConfig.isInferencing());
	}

	@Override
	public JenaChecker createModelQuery(final RdfBenchmarkConfig benchmarkConfig, final JenaDriver driver, final RailwayQuery query)
			throws Exception {
		return new JenaChecker(driver, benchmarkConfig, query);
	}

	@Override
	public ModelTransformation<?, JenaDriver> createTransformation(final RdfBenchmarkConfig benchmarkConfig,
			final JenaDriver driver, final RailwayQuery query) throws IOException {
		return JenaTransformation.newInstance(driver, query, benchmarkConfig.getScenario());
	}

	@Override
	public Comparator<?> getMatchComparator() {
		return new JenaMatchComparator();
	}

}
