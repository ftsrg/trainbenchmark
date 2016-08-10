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
package hu.bme.mit.trainbenchmark.benchmark.iqdcore.match;

import hu.bme.mit.trainbenchmark.benchmark.comparators.LongComparator;
import hu.bme.mit.trainbenchmark.benchmark.matches.comparators.MatchComparator;

public class IqdCoreMatchComparator extends MatchComparator<IqdCoreMatch, Long> {

	protected IqdCoreMatchComparator() {
		super(new LongComparator());
	}

	public static IqdCoreMatchComparator create() {
		return new IqdCoreMatchComparator();
	}

	@Override
	public int compare(final IqdCoreMatch o1, final IqdCoreMatch o2) {
		final Long[] m1 = (Long[]) o1.toArray();
		final Long[] m2 = (Long[]) o2.toArray();
		return compareArrays(m1, m2);
	}
}
