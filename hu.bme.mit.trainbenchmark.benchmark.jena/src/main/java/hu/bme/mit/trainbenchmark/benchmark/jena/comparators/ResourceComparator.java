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

import java.util.Comparator;

import org.apache.jena.rdf.model.Resource;

import hu.bme.mit.trainbenchmark.rdf.RdfHelper;

public class ResourceComparator implements Comparator<Resource> {

	@Override
	public int compare(final Resource r1, final Resource r2) {
		final long id1 = RdfHelper.extractId(r1.getURI());
		final long id2 = RdfHelper.extractId(r2.getURI());

		return Long.compare(id1, id2);
	}

}
