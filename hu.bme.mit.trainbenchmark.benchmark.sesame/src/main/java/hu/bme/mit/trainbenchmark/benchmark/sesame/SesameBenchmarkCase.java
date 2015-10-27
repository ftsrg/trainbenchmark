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

package hu.bme.mit.trainbenchmark.benchmark.sesame;

import java.io.IOException;
import java.util.Comparator;

import org.openrdf.model.URI;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sesame.checkers.SesameChecker;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.SesameTransformation;
import hu.bme.mit.trainbenchmark.constants.Query;

public class SesameBenchmarkCase extends AbstractBenchmarkCase<SesameMatch, URI, SesameDriver<RDFBenchmarkConfig>, RDFBenchmarkConfig, SesameChecker> {

	@Override
	public SesameDriver createDriver(final RDFBenchmarkConfig benchmarkConfig) throws Exception {
		return new SesameDriver(benchmarkConfig);
	}

	@Override
	public SesameChecker createChecker(final RDFBenchmarkConfig benchmarkConfig, final SesameDriver driver, final Query query) throws Exception {
		return new SesameChecker(driver, benchmarkConfig, query);
	}

	@Override
	public Comparator<?> createMatchComparator() {
		return new SesameMatchComparator();
	}

	@Override
	public Transformation<?, ?> createTransformation(final RDFBenchmarkConfig benchmarkConfig, final SesameDriver driver, final Query query) throws IOException {
		return SesameTransformation.newInstance(driver, query, benchmarkConfig.getScenario());
	}
}
