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
package hu.bme.mit.trainbenchmark.emf;

import hu.bme.mit.trainbenchmark.railway.RailwayElement;

import java.util.Comparator;

public class RailwayElementComparator implements Comparator<RailwayElement> {

	@Override
	public int compare(final RailwayElement e1, final RailwayElement e2) {
		final int id1 = e1.getId();
		final int id2 = e2.getId();
		return Integer.compare(id1, id2);
	}

}
