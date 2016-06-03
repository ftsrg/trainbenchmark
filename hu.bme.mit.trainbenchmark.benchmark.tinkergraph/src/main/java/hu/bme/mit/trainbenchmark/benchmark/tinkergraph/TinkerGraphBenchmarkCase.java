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

package hu.bme.mit.trainbenchmark.benchmark.tinkergraph;

import java.util.Comparator;

import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config.TinkerGraphBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphMatchComparator;

public class TinkerGraphBenchmarkCase
		extends AbstractBenchmarkCase<TinkerGraphMatch, Vertex, TinkerGraphDriver, TinkerGraphBenchmarkConfig, ModelQuery<TinkerGraphMatch, TinkerGraphDriver>> {

	@Override
	public TinkerGraphDriver createDriver(final TinkerGraphBenchmarkConfig benchmarkConfig) throws Exception {
		return new TinkerGraphDriver();
	}

	@Override
	public Comparator<?> getMatchComparator() {
		return new TinkerGraphMatchComparator();
	}

}
