/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
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

import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

import java.util.Map;

import org.neo4j.graphdb.Node;

public class Neo4jConnectedSegmentsMatch extends Neo4jMatch implements ConnectedSegmentsMatch {

	public Neo4jConnectedSegmentsMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public Node getSensor() {
		return (Node) match.get(QueryConstants.VAR_SENSOR);
	}

	@Override
	public Node getSegment1() {
		return (Node) match.get(QueryConstants.VAR_SEGMENT1);
	}

	@Override
	public Node getSegment2() {
		return (Node) match.get(QueryConstants.VAR_SEGMENT2);
	}

	@Override
	public Node getSegment3() {
		return (Node) match.get(QueryConstants.VAR_SEGMENT3);
	}

	@Override
	public Node getSegment4() {
		return (Node) match.get(QueryConstants.VAR_SEGMENT4);
	}

	@Override
	public Node getSegment5() {
		return (Node) match.get(QueryConstants.VAR_SEGMENT5);
	}

	@Override
	public Node getSegment6() {
		return (Node) match.get(QueryConstants.VAR_SEGMENT6);
	}

	@Override
	public Node[] toArray() {
		return new Node[] { getSensor(), getSegment1(), getSegment2(), getSegment3(), getSegment4(), getSegment5(), getSegment6() };
	}

}
