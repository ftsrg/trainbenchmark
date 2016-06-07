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

import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;

public class EmfConnectedSegmentsMatch extends EmfMatch implements ConnectedSegmentsMatch {

	public EmfConnectedSegmentsMatch(final Sensor sensor, final Segment segment1, final Segment segment2,
			final Segment segment3, final Segment segment4, final Segment segment5, final Segment segment6) {
		super();
		match = new RailwayElement[] { sensor, segment1, segment2, segment3, segment4, segment5, segment6 };
	}

	@Override
	public Sensor getSensor() {
		return (Sensor) match[0];
	}

	@Override
	public Segment getSegment1() {
		return (Segment) match[1];
	}

	@Override
	public Segment getSegment2() {
		return (Segment) match[2];
	}

	@Override
	public Segment getSegment3() {
		return (Segment) match[3];
	}

	@Override
	public Segment getSegment4() {
		return (Segment) match[4];
	}

	@Override
	public Segment getSegment5() {
		return (Segment) match[5];
	}

	@Override
	public Segment getSegment6() {
		return (Segment) match[6];
	}

}
