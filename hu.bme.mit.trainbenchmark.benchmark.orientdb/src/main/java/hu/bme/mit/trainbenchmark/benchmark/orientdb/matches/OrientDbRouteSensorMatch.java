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

import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_ROUTE;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SENSOR;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SW;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SWP;
import hu.bme.mit.trainbenchmark.benchmark.matches.railway.RouteSensorMatch;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.pipes.util.structures.Row;

public class OrientDbRouteSensorMatch extends OrientDbMatch implements RouteSensorMatch {

	public OrientDbRouteSensorMatch(final Row match) {
		super(match);
	}

	@Override
	public Vertex getRoute() {
		return (Vertex) match.getColumn(VAR_ROUTE);
	}

	@Override
	public Vertex getSensor() {
		return (Vertex) match.getColumn(VAR_SENSOR);
	}

	@Override
	public Vertex getSwP() {
		return (Vertex) match.getColumn(VAR_SWP.toLowerCase());
	}

	@Override
	public Vertex getSw() {
		return (Vertex) match.getColumn(VAR_SW);
	}

	@Override
	public Vertex[] toArray() {
		return new Vertex[] { getRoute(), getSensor(), getSwP(), getSw() };
	}
	
}
