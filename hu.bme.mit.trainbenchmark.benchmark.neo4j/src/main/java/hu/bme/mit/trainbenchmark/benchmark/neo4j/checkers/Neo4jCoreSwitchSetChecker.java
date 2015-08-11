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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.checkers;

import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.labelRoute;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.labelSemaphore;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.labelSwitch;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.labelSwitchPosition;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeEntry;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeFollows;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeSwitch;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchSetMatch;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.Signal;

public class Neo4jCoreSwitchSetChecker extends Neo4jCoreChecker<Neo4jSwitchSetMatch> {

	public Neo4jCoreSwitchSetChecker(final Neo4jDriver neoDriver) {
		super(neoDriver);
	}

	@Override
	public Collection<Neo4jSwitchSetMatch> check() {
		final Collection<Neo4jSwitchSetMatch> matches = new HashSet<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (Transaction tx = graphDb.beginTx()) {
			final ResourceIterator<Node> semaphores = graphDb.findNodes(labelSemaphore);
			while (semaphores.hasNext()) {
				final Node semaphore = semaphores.next();

				// semaphore.signal = "GO"
				final Object signal = semaphore.getProperty(SIGNAL);
				if (!Signal.GO.toString().equals(signal)) {
					continue;
				}

				// (semaphore:Semaphore)<-[:entry]-(route:Route)
				final Iterable<Relationship> entries = semaphore.getRelationships(Direction.INCOMING,
						relationshipTypeEntry);
				for (final Relationship entry : entries) {
					final Node route = entry.getStartNode();
					if (!route.hasLabel(labelRoute)) {
						continue;
					}

					// (route:Route)-[:follows]->(swP:SwitchPosition)
					final Iterable<Relationship> followss = route.getRelationships(Direction.OUTGOING,
							relationshipTypeFollows);
					for (final Relationship follows : followss) {
						final Node swP = follows.getEndNode();
						if (!swP.hasLabel(labelSwitchPosition)) {
							continue;
						}

						// (swP:SwitchPosition)-[:switch]->(sw:Switch)
						final Iterable<Relationship> relationshipSwitches = swP.getRelationships(Direction.OUTGOING,
								relationshipTypeSwitch);

						if (!relationshipSwitches.iterator().hasNext()) {
							continue;
						}

						final Node sw = relationshipSwitches.iterator().next().getEndNode();
						if (!sw.hasLabel(labelSwitch)) {
							continue;
						}

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

		return matches;
	}
}
