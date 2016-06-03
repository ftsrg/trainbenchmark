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

import hu.bme.mit.trainbenchmark.benchmark.matches.MatchComparator;
import hu.bme.mit.trainbenchmark.emf.RailwayElementComparator;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

public class EmfMatchComparator extends MatchComparator<EmfMatch, RailwayElement> {

	protected RailwayElementComparator rec = new RailwayElementComparator();

	@Override
	public int compare(final EmfMatch o1, final EmfMatch o2) {
		final RailwayElement[] m1 = o1.match;
		final RailwayElement[] m2 = o2.match;
		return compareArrays(m1, m2, rec);
	}

}
