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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_LENGTH;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT;

import java.util.Map;

import org.neo4j.graphdb.Node;

import hu.bme.mit.trainbenchmark.benchmark.matches.PosLengthMatch;

public class Neo4jPosLengthMatch extends Neo4jMatch implements PosLengthMatch {

	public Neo4jPosLengthMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public Node getSegment() {
		return (Node) match.get(VAR_SEGMENT);
	}

	public Integer getLength() {
		return (Integer) match.get(VAR_LENGTH);
	}

	@Override
	public Node[] toArray() {
		return new Node[] { getSegment() };
	}

}
