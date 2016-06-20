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
package hu.bme.mit.trainbenchmark.benchmark.comparators;

import hu.bme.mit.trainbenchmark.benchmark.matches.LongMatch;
import hu.bme.mit.trainbenchmark.benchmark.matches.comparators.MatchComparator;

public class LongMatchComparator<TMatch extends LongMatch> extends MatchComparator<TMatch, Long> {

	protected LongMatchComparator() {
		super(new LongComparator());
	}
	
	public static <TMatch extends LongMatch> LongMatchComparator<TMatch> create() {
		return new LongMatchComparator<>();
	}

	@Override
	public int compare(final LongMatch o1, final LongMatch o2) {
		final Long[] m1 = o1.getMatch();
		final Long[] m2 = o2.getMatch();
		return compareArrays(m1, m2);
	}

}
