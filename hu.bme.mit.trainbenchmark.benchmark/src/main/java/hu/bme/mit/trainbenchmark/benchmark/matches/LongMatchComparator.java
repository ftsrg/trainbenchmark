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
package hu.bme.mit.trainbenchmark.benchmark.matches;

public class LongMatchComparator extends MatchComparator<LongMatch, Long> {

	protected LongComparator lc = new LongComparator();

	@Override
	public int compare(final LongMatch o1, final LongMatch o2) {
		final Long[] m1 = o1.getMatch();
		final Long[] m2 = o2.getMatch();
		return compareArrays(m1, m2, lc);
	}

}
