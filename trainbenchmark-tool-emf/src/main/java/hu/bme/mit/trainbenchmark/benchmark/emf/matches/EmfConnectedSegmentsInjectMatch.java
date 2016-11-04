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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((segment1 == null) ? 0 : segment1.hashCode());
		result = prime * result + ((segment3 == null) ? 0 : segment3.hashCode());
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
		final EmfConnectedSegmentsInjectMatch other = (EmfConnectedSegmentsInjectMatch) obj;
		if (segment1 == null) {
			if (other.segment1 != null)
				return false;
		} else if (!segment1.equals(other.segment1))
			return false;
		if (segment3 == null) {
			if (other.segment3 != null)
				return false;
		} else if (!segment3.equals(other.segment3))
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
