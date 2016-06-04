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
package hu.bme.mit.trainbenchmark.benchmark.viatra.matches;

import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.benchmark.matches.MatchComparator;
import hu.bme.mit.trainbenchmark.emf.RailwayElementComparator;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

public class ViatraMatchComparator<TMatch extends BasePatternMatch> extends MatchComparator<TMatch, RailwayElement> {
 
	protected ViatraMatchComparator() {
		super(new RailwayElementComparator());
	}
	
	public static <TMatch extends BasePatternMatch> ViatraMatchComparator<TMatch> create() {
		return new ViatraMatchComparator<>();
	}
	
	@Override
	public int compare(final BasePatternMatch o1, final BasePatternMatch o2) {
		final Object[] m1 = o1.toArray();
		final Object[] m2 = o2.toArray();
		return compareArrays(m1, m2);
	}

}
