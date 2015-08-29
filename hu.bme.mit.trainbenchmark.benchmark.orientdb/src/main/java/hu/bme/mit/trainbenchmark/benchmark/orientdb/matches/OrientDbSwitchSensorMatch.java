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

import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SW;
import hu.bme.mit.trainbenchmark.benchmark.matches.railway.SwitchSensorMatch;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.pipes.util.structures.Row;

public class OrientDbSwitchSensorMatch extends OrientDbMatch implements SwitchSensorMatch {


	public OrientDbSwitchSensorMatch(final Row match) {
		super(match);
	}

	@Override
	public Vertex getSw() {
		return (Vertex) match.getColumn(VAR_SW);
	}

	@Override
	public Vertex[] toArray() {
		return new Vertex[] { getSw() };
	}
	
}
