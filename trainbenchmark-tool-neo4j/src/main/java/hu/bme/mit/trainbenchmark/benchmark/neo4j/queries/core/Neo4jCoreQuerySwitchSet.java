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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core;

import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.labelRoute;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.labelSemaphore;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.labelSwitch;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.labelSwitchPosition;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeEntry;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeFollows;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.util.Neo4jUtil;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.Signal;

public class Neo4jCoreQuerySwitchSet extends Neo4jCoreQuery<Neo4jSwitchSetMatch> {

	public Neo4jCoreQuerySwitchSet(final Neo4jDriver driver) {
		super(RailwayQuery.SWITCHSET, driver);
	}

	@Override
	public Collection<Neo4jSwitchSetMatch> evaluate() {
		final Collection<Neo4jSwitchSetMatch> matches = new ArrayList<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (Transaction tx = graphDb.beginTx()) {
			// (route:Route)
			final Iterable<Node> routes = () -> graphDb.findNodes(labelRoute);
			for (final Node route : routes) {
				// (route:Route)-[:entry]->(semaphore:Semaphore)
				final Iterable<Node> semaphores = Neo4jUtil.getAdjacentNodes(route, relationshipTypeEntry, Direction.OUTGOING,
						labelSemaphore);
				for (final Node semaphore : semaphores) {

					// semaphore.signal = "GO"
					final Object signal = semaphore.getProperty(SIGNAL);
					if (!Signal.GO.toString().equals(signal)) {
						continue;
					}

					// (route:Route)-[:follows]->(swP:SwitchPosition)
					final Iterable<Node> swPs = Neo4jUtil.getAdjacentNodes(route, relationshipTypeFollows, Direction.OUTGOING,
							labelSwitchPosition);
					for (final Node swP : swPs) {
						// (swP:SwitchPosition)-[:target]->(sw:Switch)
						final Iterable<Node> sws = Neo4jUtil.getAdjacentNodes(swP, Neo4jConstants.relationshipTypeTarget,
								Direction.OUTGOING, labelSwitch);

						for (final Node sw : sws) {
							final Object currentPosition = sw.getProperty(ModelConstants.CURRENTPOSITION);
							final Object position = swP.getProperty(ModelConstants.POSITION);

							if (!currentPosition.equals(position)) {
								final Map<String, Object> match = new HashMap<>();
								match.put(QueryConstants.VAR_SEMAPHORE, semaphore);
								match.put(QueryConstants.VAR_ROUTE, route);
								match.put(QueryConstants.VAR_SWP, swP);
								match.put(QueryConstants.VAR_SW, sw);
								match.put(QueryConstants.VAR_CURRENTPOSITION, currentPosition);
								match.put(QueryConstants.VAR_POSITION, position);
								matches.add(new Neo4jSwitchSetMatch(match));
							}
						}
					}
				}
			}
		}

		return matches;
	}
}
