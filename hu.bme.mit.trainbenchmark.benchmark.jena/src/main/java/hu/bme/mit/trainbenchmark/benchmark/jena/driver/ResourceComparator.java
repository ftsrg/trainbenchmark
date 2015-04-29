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
package hu.bme.mit.trainbenchmark.benchmark.jena.driver;

import hu.bme.mit.trainbenchmark.rdf.RDFHelper;

import java.util.Comparator;

import com.hp.hpl.jena.rdf.model.Resource;

public class ResourceComparator implements Comparator<Resource> {

	@Override
	public int compare(final Resource r1, final Resource r2) {
		final long id1 = RDFHelper.extractId(r1.getURI());
		final long id2 = RDFHelper.extractId(r2.getURI());

		return Long.compare(id1, id2);
	}

}
