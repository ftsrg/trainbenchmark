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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.matches;

import hu.bme.mit.trainbenchmark.emf.RailwayElementComparator;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

import java.util.Comparator;

import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;

public class EMFIncQueryMatchComparator implements Comparator<BasePatternMatch> {

	protected RailwayElementComparator rec = new RailwayElementComparator();

	@Override
	public int compare(final BasePatternMatch o1, final BasePatternMatch o2) {
		final Object[] m1 = o1.toArray();
		final Object[] m2 = o2.toArray();
		for (int i = 0; i < m1.length; i++) {
			final RailwayElement t1 = (RailwayElement) m1[i];
			final RailwayElement t2 = (RailwayElement) m2[i];

			final int comparison = rec.compare(t1, t2);
			if (comparison != 0) {
				return comparison;
			}
		}

		return 0;
	}

}
