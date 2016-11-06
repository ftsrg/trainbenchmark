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
package hu.bme.mit.trainbenchmark.benchmark.emf.matches;

import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;

public class EmfConnectedSegmentsInjectMatch extends EmfMatch implements ConnectedSegmentsInjectMatch {

	protected final Sensor sensor;
	protected final Segment segment1;
	protected final Segment segment3;

	public EmfConnectedSegmentsInjectMatch(final Sensor sensor, final Segment segment1, final Segment segment3) {
		super();
		this.sensor = sensor;
		this.segment1 = segment1;
		this.segment3 = segment3;
	}

	@Override
	public Sensor getSensor() {
		return sensor;
	}

	@Override
	public Segment getSegment1() {
		return segment1;
	}

	@Override
	public Segment getSegment3() {
		return segment3;
	}

}
