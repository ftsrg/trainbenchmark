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

import hu.bme.mit.trainbenchmark.benchmark.matches.SemaphoreNeighborMatch;

public class TinkerGraphSemaphoreNeighborMatch extends TinkerGraphMatch implements SemaphoreNeighborMatch {

	protected final Vertex semaphore;
	protected final Vertex route1;
	protected final Vertex route2;
	protected final Vertex sensor1;
	protected final Vertex sensor2;
	protected final Vertex te1;
	protected final Vertex te2;

	public TinkerGraphSemaphoreNeighborMatch(final Vertex semaphore, final Vertex route1, final Vertex route2, final Vertex sensor1, final Vertex sensor2,
			final Vertex te1, final Vertex te2) {
		super();
		this.semaphore = semaphore;
		this.route1 = route1;
		this.route2 = route2;
		this.sensor1 = sensor1;
		this.sensor2 = sensor2;
		this.te1 = te1;
		this.te2 = te2;
	}

	@Override
	public Vertex getSemaphore() {
		return semaphore;
	}

	@Override
	public Vertex getRoute1() {
		return route1;
	}

	@Override
	public Vertex getRoute2() {
		return route2;
	}

	@Override
	public Vertex getSensor1() {
		return sensor1;
	}

	@Override
	public Vertex getSensor2() {
		return sensor2;
	}

	@Override
	public Vertex getTe1() {
		return te1;
	}

	@Override
	public Vertex getTe2() {
		return te2;
	}

}
