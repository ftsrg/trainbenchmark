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
package hu.bme.mit.trainbenchmark.benchmark.orientdb.matches;

import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SEGMENT1;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SEGMENT2;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SEGMENT3;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SEGMENT4;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SEGMENT5;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SEGMENT6;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SENSOR;
import hu.bme.mit.trainbenchmark.benchmark.matches.railway.ConnectedSegmentsMatch;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.pipes.util.structures.Row;

public class OrientDbConnectedSegmentsMatch extends OrientDbMatch implements ConnectedSegmentsMatch {

	public OrientDbConnectedSegmentsMatch(final Row match) {
		super(match);
	}

	@Override
	public Vertex getSensor() {
		return (Vertex) match.getColumn(VAR_SENSOR);
	}

	@Override
	public Vertex getSegment1() {
		return (Vertex) match.getColumn(VAR_SEGMENT1);
	}

	@Override
	public Vertex getSegment2() {
		return (Vertex) match.getColumn(VAR_SEGMENT2);
	}

	@Override
	public Vertex getSegment3() {
		return (Vertex) match.getColumn(VAR_SEGMENT3);
	}

	@Override
	public Vertex getSegment4() {
		return (Vertex) match.getColumn(VAR_SEGMENT4);
	}

	@Override
	public Vertex getSegment5() {
		return (Vertex) match.getColumn(VAR_SEGMENT5);
	}

	@Override
	public Vertex getSegment6() {
		return (Vertex) match.getColumn(VAR_SEGMENT6);
	}

	@Override
	public Vertex[] toArray() {
		return new Vertex[] { getSensor(), getSegment1(), getSegment2(), getSegment3(), getSegment4(), getSegment5(), getSegment6() };
	}

}
