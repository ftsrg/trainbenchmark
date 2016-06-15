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

import org.apache.jena.rdf.model.Resource;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.jena.comparators.JenaMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaMatch;
import hu.bme.mit.trainbenchmark.benchmark.matches.comparators.MatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RdfBenchmarkConfigWrapper;

public class JenaBenchmarkCase extends AbstractBenchmarkCase<JenaMatch, Resource, JenaDriver, RdfBenchmarkConfigWrapper> {

	@Override
	public JenaDriver createDriver(final RdfBenchmarkConfigWrapper benchmarkConfigWrapper) throws Exception {
		return new JenaDriver(benchmarkConfigWrapper.isInferencing());
	}

	@Override
	public JenaMatchComparator getMatchComparator() {
		return JenaMatchComparator.create();
	}

}
