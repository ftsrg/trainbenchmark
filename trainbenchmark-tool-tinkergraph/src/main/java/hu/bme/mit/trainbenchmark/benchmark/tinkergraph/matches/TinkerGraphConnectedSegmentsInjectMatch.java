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

import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsInjectMatch;

public class TinkerGraphConnectedSegmentsInjectMatch extends TinkerGraphMatch implements ConnectedSegmentsInjectMatch {

	protected final Vertex sensor;
	protected final Vertex segment1;
	protected final Vertex segment3;

	public TinkerGraphConnectedSegmentsInjectMatch(final Vertex sensor, final Vertex segment1, final Vertex segment3) {
		super();
		this.sensor = sensor;
		this.segment1 = segment1;
		this.segment3 = segment3;
	}

	@Override
	public Vertex getSensor() {
		return sensor;
	}

	@Override
	public Vertex getSegment1() {
		return segment1;
	}

	@Override
	public Vertex getSegment3() {
		return segment3;
	}

}
