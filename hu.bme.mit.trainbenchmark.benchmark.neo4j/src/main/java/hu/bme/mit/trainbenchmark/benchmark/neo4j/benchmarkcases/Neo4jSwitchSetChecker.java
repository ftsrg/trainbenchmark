/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases;

import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.labelRoute;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.labelSemaphore;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.labelSwitch;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.labelSwitchPosition;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeEntry;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeFollows;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeSwitch;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchSetMatch;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.Signal;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

public class Neo4jSwitchSetChecker extends Neo4jJavaChecker<Neo4jSwitchSetMatch> {

	public Neo4jSwitchSetChecker(final Neo4jDriver neoDriver) {
		super(neoDriver);
	}

	@Override
	public Collection<Neo4jSwitchSetMatch> check() {
		final Collection<Neo4jSwitchSetMatch> matches = new HashSet<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (Transaction tx = graphDb.beginTx()) {
			final ResourceIterable<Node> semaphores = GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(labelSemaphore);
			for (final Node semaphore : semaphores) {
				// semaphore.signal = "GO"
				final Object signal = semaphore.getProperty(SIGNAL);
				if (!Signal.GO.toString().equals(signal)) {
					continue;
				}

				// (semaphore:Semaphore)<-[:entry]-(route:Route)
				final Iterable<Relationship> entries = semaphore.getRelationships(Direction.INCOMING, relationshipTypeEntry);
				for (final Relationship entry : entries) {
					final Node route = entry.getStartNode();
					if (!route.hasLabel(labelRoute)) {
						continue;
					}

					// (route:Route)-[:follows]->(sP:SwitchPosition)
					final Iterable<Relationship> followss = route.getRelationships(Direction.OUTGOING, relationshipTypeFollows);
					for (final Relationship follows : followss) {
						final Node swP = follows.getEndNode();
						if (!swP.hasLabel(labelSwitchPosition)) {
							continue;
						}

						// (swP:SwitchPosition)-[:switch]->(sw:Switch)
						final Iterable<Relationship> relationshipSwitches = swP
								.getRelationships(Direction.OUTGOING, relationshipTypeSwitch);

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
							match.put(QueryConstants.VAR_SWP, sw);
							matches.add(new Neo4jSwitchSetMatch(match));
						}
					}
				}
			}
		}

		return matches;
	}
}
