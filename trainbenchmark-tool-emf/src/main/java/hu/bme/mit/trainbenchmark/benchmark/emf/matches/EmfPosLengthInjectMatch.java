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

import hu.bme.mit.trainbenchmark.benchmark.matches.PosLengthMatch;
import hu.bme.mit.trainbenchmark.railway.Segment;

public class EmfPosLengthInjectMatch extends EmfMatch implements PosLengthMatch {

	protected final Segment segment;

	public EmfPosLengthInjectMatch(final Segment segment) {
		super();
		this.segment = segment;
	}

	@Override
	public Segment getSegment() {
		return segment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((segment == null) ? 0 : segment.hashCode());
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
		final EmfPosLengthInjectMatch other = (EmfPosLengthInjectMatch) obj;
		if (segment == null) {
			if (other.segment != null)
				return false;
		} else if (!segment.equals(other.segment))
			return false;
		return true;
	}

}
