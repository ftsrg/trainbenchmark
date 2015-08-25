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

import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SEGMENT1;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SEGMENT2;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SEGMENT3;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SEGMENT4;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SEGMENT5;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SEGMENT6;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SENSOR;
import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsMatch;

import java.util.Map;

import org.neo4j.graphdb.Node;

public class Neo4jConnectedSegmentsMatch extends Neo4jMatch implements ConnectedSegmentsMatch {

	public Neo4jConnectedSegmentsMatch(final Map<String, Object> match) {
		super(match);
	}

	@Override
	public Node getSensor() {
		return (Node) match.get(VAR_SENSOR);
	}

	@Override
	public Node getSegment1() {
		return (Node) match.get(VAR_SEGMENT1);
	}

	@Override
	public Node getSegment2() {
		return (Node) match.get(VAR_SEGMENT2);
	}

	@Override
	public Node getSegment3() {
		return (Node) match.get(VAR_SEGMENT3);
	}

	@Override
	public Node getSegment4() {
		return (Node) match.get(VAR_SEGMENT4);
	}

	@Override
	public Node getSegment5() {
		return (Node) match.get(VAR_SEGMENT5);
	}

	@Override
	public Node getSegment6() {
		return (Node) match.get(VAR_SEGMENT6);
	}

	@Override
	public Node[] toArray() {
		return new Node[] { getSensor(), getSegment1(), getSegment2(), getSegment3(), getSegment4(), getSegment5(), getSegment6() };
	}

}
