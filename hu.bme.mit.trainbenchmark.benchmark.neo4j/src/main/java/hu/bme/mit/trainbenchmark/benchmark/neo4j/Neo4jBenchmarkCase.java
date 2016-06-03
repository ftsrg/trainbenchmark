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

package hu.bme.mit.trainbenchmark.benchmark.neo4j;

import java.util.Comparator;

import org.neo4j.graphdb.Node;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatchComparator;

public class Neo4jBenchmarkCase<Neo4jChecker> extends AbstractBenchmarkCase<Neo4jMatch, Node, Neo4jDriver, Neo4jBenchmarkConfig> {

	@Override
	public Neo4jDriver createDriver(final Neo4jBenchmarkConfig benchmarkConfig) throws Exception {
		return new Neo4jDriver(benchmarkConfig.getWorkspacePath());
	}
	
	@Override
	public Comparator<Neo4jMatch> getMatchComparator() {
		return new Neo4jMatchComparator();
	}

}
