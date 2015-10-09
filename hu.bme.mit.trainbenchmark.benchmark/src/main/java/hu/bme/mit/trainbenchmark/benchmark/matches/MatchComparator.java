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
package hu.bme.mit.trainbenchmark.benchmark.matches;

import java.util.Comparator;

public abstract class MatchComparator<TMatch, TElement> implements Comparator<TMatch> {

	public int compareArrays(final Object[] m1, final Object[] m2, final Comparator<TElement> elementComparator) {
		for (int i = 0; i < m1.length; i++) {
			final TElement t1 = (TElement) m1[i];
			final TElement t2 = (TElement) m2[i];

			final int comparison = elementComparator.compare(t1, t2);
			if (comparison != 0) {
				return comparison;
			}
		}

		return 0;
	}

}
