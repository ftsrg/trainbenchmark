/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.java.matches;

import hu.bme.mit.trainbenchmark.emf.RailwayElementComparator;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

import java.util.Comparator;

public class JavaMatchComparator implements Comparator<JavaMatch> {

	protected RailwayElementComparator rec = new RailwayElementComparator();

	@Override
	public int compare(final JavaMatch o1, final JavaMatch o2) {
		final RailwayElement[] m1 = o1.match;
		final RailwayElement[] m2 = o2.match;
		for (int i = 0; i < m1.length; i++) {
			final RailwayElement t1 = m1[i];
			final RailwayElement t2 = m2[i];

			final int comparison = rec.compare(t1, t2);
			if (comparison != 0) {
				return comparison;
			}
		}

		return 0;
	}

}
