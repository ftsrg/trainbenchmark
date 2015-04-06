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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;

import java.util.Collection;
import java.util.HashSet;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

public class Neo4jPosLength extends Neo4jJavaBenchmarkCase {

	@Override
	public Collection<Node> checkJava() {
		matches = new HashSet<>();

		try (Transaction tx = graphDb.beginTx()) {
			// Segment
			final ResourceIterable<Node> segments = GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(labelSegment);
			for (final Node segment : segments) {
				final Integer length = (Integer) segment.getProperty(LENGTH);
				// Segment.length <= 0
				if (length <= 0) {
					matches.add(segment);
				}
			}
		}

		return matches;
	}

}
