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

import static hu.bme.mit.trainbenchmark.benchmark.tinkergraph.constants.TinkerGraphConstants.labelRoute;
import static hu.bme.mit.trainbenchmark.benchmark.tinkergraph.constants.TinkerGraphConstants.labelSemaphore;
import static hu.bme.mit.trainbenchmark.benchmark.tinkergraph.constants.TinkerGraphConstants.labelSwitch;
import static hu.bme.mit.trainbenchmark.benchmark.tinkergraph.constants.TinkerGraphConstants.labelSwitchPosition;
import static hu.bme.mit.trainbenchmark.benchmark.tinkergraph.constants.TinkerGraphConstants.relationshipTypeEntry;
import static hu.bme.mit.trainbenchmark.benchmark.tinkergraph.constants.TinkerGraphConstants.relationshipTypeFollows;
import static hu.bme.mit.trainbenchmark.benchmark.tinkergraph.constants.TinkerGraphConstants.relationshipTypeSwitch;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL;

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
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSwitchSetMatch;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.Signal;

public class TinkerGraphCoreSwitchSetChecker extends TinkerGraphCoreChecker<TinkerGraphSwitchSetMatch> {

	public TinkerGraphCoreSwitchSetChecker(final TinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public Collection<TinkerGraphSwitchSetMatch> check() {
		final Collection<TinkerGraphSwitchSetMatch> matches = new ArrayList<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (Transaction tx = graphDb.beginTx()) {
			// (route:Route)
			final ResourceIterator<Node> routes = graphDb.findNodes(labelRoute);
			while (routes.hasNext()) {
				final Node route = routes.next();

				// (route:Route)-[:entry]->(semaphore:Semaphore)
				final Iterable<Relationship> entries = route.getRelationships(Direction.OUTGOING,
						relationshipTypeEntry);
				
				for (final Relationship entry : entries) {
					final Node semaphore = entry.getEndNode();
					if (!semaphore.hasLabel(labelSemaphore)) {
						continue;
					}

					// semaphore.signal = "GO"
					final Object signal = semaphore.getProperty(SIGNAL);
					if (!Signal.GO.toString().equals(signal)) {
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
							matches.add(new TinkerGraphSwitchSetMatch(match));
						}
					}
				}
			}
		}

		return matches;
	}
}
