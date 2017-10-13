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

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;

public class Neo4JApiQuerySwitchMonitored extends Neo4jApiQuery<Neo4jSwitchMonitoredMatch> {

	public Neo4JApiQuerySwitchMonitored(final Neo4jDriver driver) {
		super(RailwayQuery.SWITCHMONITORED, driver);
	}

	@Override
	public Collection<Neo4jSwitchMonitoredMatch> evaluate() {
		final Collection<Neo4jSwitchMonitoredMatch> matches = new ArrayList<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (Transaction tx = graphDb.beginTx()) {
			final Iterable<Node> sws = () -> graphDb.findNodes(Neo4jConstants.labelSwitch);
			// (sw:Switch)
			for (final Node sw : sws) {
				// (sw)-[:sensor]->(Sensor) NAC
				final Iterable<Relationship> relationshipSensors = sw.getRelationships(Direction.OUTGOING, Neo4jConstants.relationshipTypeMonitoredBy);

				boolean hasSensor = false;
				for (final Relationship relationshipSensor : relationshipSensors) {
					final Node sensor = relationshipSensor.getEndNode();
					if (sensor.hasLabel(Neo4jConstants.labelSensor)) {
						hasSensor = true;
						break;
					}
				}

				if (!hasSensor) {
					final Map<String, Object> match = new HashMap<>();
					match.put(VAR_SW, sw);
					matches.add(new Neo4jSwitchMonitoredMatch(match));
				}
			}
		}

		return matches;
	}
}
