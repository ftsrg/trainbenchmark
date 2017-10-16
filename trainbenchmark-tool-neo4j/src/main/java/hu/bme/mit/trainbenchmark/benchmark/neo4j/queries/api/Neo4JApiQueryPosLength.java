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

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jPosLengthMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jHelper;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_LENGTH;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT;

public class Neo4JApiQueryPosLength extends Neo4jApiQuery<Neo4jPosLengthMatch> {

	public Neo4JApiQueryPosLength(final Neo4jDriver driver) {
		super(RailwayQuery.POSLENGTH, driver);
	}

	@Override
	public Collection<Neo4jPosLengthMatch> evaluate() {
		final Collection<Neo4jPosLengthMatch> matches = new ArrayList<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (Transaction tx = graphDb.beginTx()) {
			// (segment:Segment)
			final Iterable<Node> segments = () -> graphDb.findNodes(Neo4jConstants.labelSegment);
			for (final Node segment : segments) {
				final Number lengthNumber = (Number) segment.getProperty(LENGTH);
				final int length = Neo4jHelper.numberToInt(lengthNumber);

				// segment.length <= 0
				if (length <= 0) {
					final Map<String, Object> match = new HashMap<>();
					match.put(VAR_SEGMENT, segment);
					match.put(VAR_LENGTH, length);
					matches.add(new Neo4jPosLengthMatch(match));
				}
			}
		}

		return matches;
	}
}
