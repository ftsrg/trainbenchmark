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
package hu.bme.mit.trainbenchmark.benchmark.matches.comparators;

import java.util.Comparator;

import hu.bme.mit.trainbenchmark.benchmark.matches.Match;

public abstract class BaseMatchComparator<TMatch extends Match, TElement> extends MatchComparator<TMatch, TElement> {

	public BaseMatchComparator(final Comparator<TElement> elementComparator) {
		super(elementComparator);
	}

	@Override
	public int compare(final Match m1, final Match m2) {
		final Object[] o1 = m1.toArray();
		final Object[] o2 = m2.toArray();
		return compareArrays(o1, o2);
	}

}
