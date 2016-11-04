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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((segment1 == null) ? 0 : segment1.hashCode());
		result = prime * result + ((segment2 == null) ? 0 : segment2.hashCode());
		result = prime * result + ((segment3 == null) ? 0 : segment3.hashCode());
		result = prime * result + ((segment4 == null) ? 0 : segment4.hashCode());
		result = prime * result + ((segment5 == null) ? 0 : segment5.hashCode());
		result = prime * result + ((segment6 == null) ? 0 : segment6.hashCode());
		result = prime * result + ((sensor == null) ? 0 : sensor.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final EmfConnectedSegmentsMatch other = (EmfConnectedSegmentsMatch) obj;
		if (segment1 == null) {
			if (other.segment1 != null)
				return false;
		} else if (!segment1.equals(other.segment1))
			return false;
		if (segment2 == null) {
			if (other.segment2 != null)
				return false;
		} else if (!segment2.equals(other.segment2))
			return false;
		if (segment3 == null) {
			if (other.segment3 != null)
				return false;
		} else if (!segment3.equals(other.segment3))
			return false;
		if (segment4 == null) {
			if (other.segment4 != null)
				return false;
		} else if (!segment4.equals(other.segment4))
			return false;
		if (segment5 == null) {
			if (other.segment5 != null)
				return false;
		} else if (!segment5.equals(other.segment5))
			return false;
		if (segment6 == null) {
			if (other.segment6 != null)
				return false;
		} else if (!segment6.equals(other.segment6))
			return false;
		if (sensor == null) {
			if (other.sensor != null)
				return false;
		} else if (!sensor.equals(other.sensor))
			return false;
		return true;
	}

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
