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
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;

public class EmfConnectedSegmentsMatch extends EmfMatch implements ConnectedSegmentsMatch {

	protected final Sensor sensor;
	protected final Segment segment1;
	protected final Segment segment2;
	protected final Segment segment3;
	protected final Segment segment4;
	protected final Segment segment5;
	protected final Segment segment6;

	public EmfConnectedSegmentsMatch(final Sensor sensor, final Segment segment1, final Segment segment2,
			final Segment segment3, final Segment segment4, final Segment segment5, final Segment segment6) {
		super();
		this.sensor = sensor;
		this.segment1 = segment1;
		this.segment2 = segment2;
		this.segment3 = segment3;
		this.segment4 = segment4;
		this.segment5 = segment5;
		this.segment6 = segment6;
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
	public Segment getSegment2() {
		return segment2;
	}

	@Override
	public Segment getSegment3() {
		return segment3;
	}

	@Override
	public Segment getSegment4() {
		return segment4;
	}

	@Override
	public Segment getSegment5() {
		return segment5;
	}

	@Override
	public Segment getSegment6() {
		return segment6;
	}

}
