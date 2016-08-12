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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches;

import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.matches.RouteSensorMatch;

public class TinkerGraphRouteSensorMatch extends TinkerGraphMatch implements RouteSensorMatch {

	protected final Vertex route;
	protected final Vertex sensor;
	protected final Vertex swP;
	protected final Vertex sw;

	public TinkerGraphRouteSensorMatch(final Vertex route, final Vertex sensor, final Vertex swP, final Vertex sw) {
		super();
		this.route = route;
		this.sensor = sensor;
		this.swP = swP;
		this.sw = sw;
	}

	@Override
	public Vertex getRoute() {
		return route;
	}

	@Override
	public Vertex getSensor() {
		return sensor;
	}

	@Override
	public Vertex getSwP() {
		return swP;
	}

	@Override
	public Vertex getSw() {
		return sw;
	}

}
