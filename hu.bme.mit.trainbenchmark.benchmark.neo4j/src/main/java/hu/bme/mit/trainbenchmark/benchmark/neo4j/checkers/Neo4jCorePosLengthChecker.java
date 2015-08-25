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

import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.labelSegment;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SEGMENT;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jPosLengthMatch;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;

public class Neo4jCorePosLengthChecker extends Neo4jCoreChecker<Neo4jPosLengthMatch> {

	public Neo4jCorePosLengthChecker(final Neo4jDriver neoDriver) {
		super(neoDriver);
	}

	@Override
	public Collection<Neo4jPosLengthMatch> check() {
		final Collection<Neo4jPosLengthMatch> matches = new HashSet<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (Transaction tx = graphDb.beginTx()) {
			// Segment
			final ResourceIterator<Node> segments = graphDb.findNodes(labelSegment);
			while (segments.hasNext()) {
				final Node segment = segments.next();
				final Integer length = (Integer) segment.getProperty(LENGTH);
				// Segment.length <= 0
				if (length <= 0) {
					final Map<String, Object> match = new HashMap<>();
					match.put(VAR_SEGMENT, segment);
					matches.add(new Neo4jPosLengthMatch(match));
				}
			}
		}

		return matches;
	}
}
