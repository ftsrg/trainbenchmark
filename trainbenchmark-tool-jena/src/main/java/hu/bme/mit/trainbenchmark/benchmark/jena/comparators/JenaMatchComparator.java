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
package hu.bme.mit.trainbenchmark.benchmark.jena.comparators;

import org.apache.jena.rdf.model.Resource;

import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaMatch;
import hu.bme.mit.trainbenchmark.benchmark.matches.comparators.MatchComparator;

public class JenaMatchComparator extends MatchComparator<JenaMatch, Resource> {

	protected JenaMatchComparator() {
		super(new ResourceComparator());
	}
	
	public static JenaMatchComparator create() {
		return new JenaMatchComparator();
	}

	@Override
	public int compare(final JenaMatch o1, final JenaMatch o2) {
		final Resource[] m1 = (Resource[]) o1.toArray();
		final Resource[] m2 = (Resource[]) o2.toArray();
		return compareArrays(m1, m2);
	}

}
