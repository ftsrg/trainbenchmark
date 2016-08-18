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
package hu.bme.mit.trainbenchmark.benchmark.rdf4j.comparators;

import org.eclipse.rdf4j.model.IRI;

import hu.bme.mit.trainbenchmark.benchmark.matches.comparators.BaseMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jMatch;

public class Rdf4jMatchComparator extends BaseMatchComparator<Rdf4jMatch, IRI> {

	protected Rdf4jMatchComparator() {
		super(new IriComparator());
	}
	
	public static Rdf4jMatchComparator create() {
		return new Rdf4jMatchComparator();
	}

	@Override
	public int compare(final Rdf4jMatch o1, final Rdf4jMatch o2) {
		final Object[] m1 = o1.toArray();
		final Object[] m2 = o2.toArray();
		return compareArrays(m1, m2);
	}

}
