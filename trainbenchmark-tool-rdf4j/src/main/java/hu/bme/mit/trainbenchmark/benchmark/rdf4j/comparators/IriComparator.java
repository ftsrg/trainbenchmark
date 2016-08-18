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

import java.util.Comparator;

import org.eclipse.rdf4j.model.IRI;

public class IriComparator implements Comparator<IRI> {

	@Override
	public int compare(final IRI iri1, final IRI iri2) {
		final long id1 = Long.parseLong(iri1.getLocalName().substring(1));
		final long id2 = Long.parseLong(iri2.getLocalName().substring(1));
		return Long.compare(id1, id2);
	}

}
