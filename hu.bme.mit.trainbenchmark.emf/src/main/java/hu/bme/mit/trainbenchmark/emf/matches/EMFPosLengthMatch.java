/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.emf.matches;

import hu.bme.mit.trainbenchmark.benchmark.matches.PosLengthMatch;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.Segment;

public class EMFPosLengthMatch extends EMFMatch implements PosLengthMatch {

	public EMFPosLengthMatch(final Segment segment) {
		super();
		match = new RailwayElement[] { segment };
	}

	public Segment getSegment() {
		return (Segment) match[0];
	}

}
