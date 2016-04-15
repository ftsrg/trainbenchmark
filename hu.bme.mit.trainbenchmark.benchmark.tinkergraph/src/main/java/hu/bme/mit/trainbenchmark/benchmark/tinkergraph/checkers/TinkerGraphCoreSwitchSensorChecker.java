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

package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.checkers;

import static hu.bme.mit.trainbenchmark.benchmark.tinkergraph.constants.TinkerGraphConstants.labelSensor;
import static hu.bme.mit.trainbenchmark.benchmark.tinkergraph.constants.TinkerGraphConstants.labelSwitch;
import static hu.bme.mit.trainbenchmark.benchmark.tinkergraph.constants.TinkerGraphConstants.relationshipTypeMonitoredBy;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSwitchSensorMatch;

public class TinkerGraphCoreSwitchSensorChecker extends TinkerGraphCoreChecker<TinkerGraphSwitchSensorMatch> {

	public TinkerGraphCoreSwitchSensorChecker(final TinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public Collection<TinkerGraphSwitchSensorMatch> check() {
		final Collection<TinkerGraphSwitchSensorMatch> matches = new ArrayList<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (Transaction tx = graphDb.beginTx()) {
			final ResourceIterator<Node> switches = graphDb.findNodes(labelSwitch);
			while (switches.hasNext()) {
				// (sw:Switch)
				final Node sw = switches.next();
				
				// (sw)-[:sensor]->(Sensor) NAC
				final Iterable<Relationship> relationshipSensors = sw.getRelationships(Direction.OUTGOING, relationshipTypeMonitoredBy);

				boolean hasSensor = false;
				for (final Relationship relationshipSensor : relationshipSensors) {
					final Node sensor = relationshipSensor.getEndNode();
					if (sensor.hasLabel(labelSensor)) {
						hasSensor = true;
						break;
					}
				}

				if (!hasSensor) {
					final Map<String, Object> match = new HashMap<>();
					match.put(VAR_SW, sw);
					matches.add(new TinkerGraphSwitchSensorMatch(match));
				}
			}
		}

		return matches;
	}
}
