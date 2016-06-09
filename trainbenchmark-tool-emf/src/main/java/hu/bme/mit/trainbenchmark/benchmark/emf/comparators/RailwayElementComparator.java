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
package hu.bme.mit.trainbenchmark.benchmark.emf.comparators;

import java.util.Comparator;

import hu.bme.mit.trainbenchmark.railway.RailwayElement;

public class RailwayElementComparator implements Comparator<RailwayElement> {

	@Override
	public int compare(final RailwayElement e1, final RailwayElement e2) {
		final int id1 = e1.getId();
		final int id2 = e2.getId();
		return Integer.compare(id1, id2);
	}

}
