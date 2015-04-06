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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.Signal;

import java.util.Collection;
import java.util.HashSet;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

public class Neo4jSwitchSet extends Neo4jJavaBenchmarkCase {

	@Override
	public Collection<Node> checkJava() {
		results = new HashSet<>();

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
						final Node sP = follows.getEndNode();
						if (!sP.hasLabel(labelSwitchPosition)) {
							continue;
						}

						// (sP:SwitchPosition)-[:switch]->(sw:Switch)
						final Iterable<Relationship> relationshipSwitches = sP.getRelationships(Direction.OUTGOING,
								relationshipTypeSwitch);

						if (!relationshipSwitches.iterator().hasNext()) {
							continue;
						}

						final Node sw = relationshipSwitches.iterator().next().getEndNode();
						if (!sw.hasLabel(labelSwitch)) {
							continue;
						}

						final Object currentPosition = sw.getProperty(ModelConstants.CURRENTPOSITION);
						final Object position = sP.getProperty(ModelConstants.POSITION);

						if (!currentPosition.equals(position)) {
							results.add(sP);
						}
					}
				}
			}
		}

		return results;
	}

}
