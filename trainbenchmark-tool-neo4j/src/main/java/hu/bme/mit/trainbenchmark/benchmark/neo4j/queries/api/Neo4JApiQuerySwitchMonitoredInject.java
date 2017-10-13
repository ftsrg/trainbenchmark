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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;

public class Neo4JApiQuerySwitchMonitoredInject extends Neo4jApiQuery<Neo4jSwitchMonitoredInjectMatch> {

	public Neo4JApiQuerySwitchMonitoredInject(final Neo4jDriver driver) {
		super(RailwayQuery.SWITCHMONITORED_INJECT, driver);
	}

	@Override
	public Collection<Neo4jSwitchMonitoredInjectMatch> evaluate() {
		final Collection<Neo4jSwitchMonitoredInjectMatch> matches = new ArrayList<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (final Transaction tx = graphDb.beginTx()) {
			// (sw:Switch)
			final Iterable<Node> sws = () -> graphDb.findNodes(Neo4jConstants.labelSwitch);
			for (final Node sw : sws) {
				final Map<String, Object> match = new HashMap<>();
				match.put(QueryConstants.VAR_SW, sw);
				matches.add(new Neo4jSwitchMonitoredInjectMatch(match));
			}
		}

		return matches;
	}
}
