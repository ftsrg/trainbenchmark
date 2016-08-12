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

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSetMatch;

public class TinkerGraphSwitchSetMatch extends TinkerGraphMatch implements SwitchSetMatch {

	protected final Vertex semaphore;
	protected final Vertex route;
	protected final Vertex swP;
	protected final Vertex sw;
	protected final String position;
	protected final String currentPosition;

	public TinkerGraphSwitchSetMatch(final Vertex semaphore, final Vertex route, final Vertex swP, final Vertex sw, final String position,
			final String currentPosition) {
		super();
		this.semaphore = semaphore;
		this.route = route;
		this.swP = swP;
		this.sw = sw;
		this.position = position;
		this.currentPosition = currentPosition;
	}

	@Override
	public Vertex getSemaphore() {
		return semaphore;
	}

	@Override
	public Vertex getRoute() {
		return route;
	}

	@Override
	public Vertex getSwP() {
		return swP;
	}

	@Override
	public Vertex getSw() {
		return sw;
	}

	public String getPosition() {
		return position;
	}

	public String getCurrentPosition() {
		return currentPosition;
	}

}
