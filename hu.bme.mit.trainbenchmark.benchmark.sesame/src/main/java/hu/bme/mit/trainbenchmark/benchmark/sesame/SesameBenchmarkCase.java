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

import java.util.Comparator;

import org.openrdf.model.URI;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RdfBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatchComparator;

public class SesameBenchmarkCase extends AbstractBenchmarkCase<SesameMatch, URI, SesameDriver, RdfBenchmarkConfig> {

	@Override
	public SesameDriver createDriver(final RdfBenchmarkConfig benchmarkConfig) throws Exception {
		return new SesameDriver(benchmarkConfig.isInferencing());
	}

	@Override
	public Comparator<SesameMatch> getMatchComparator() {
		return new SesameMatchComparator();
	}

}
