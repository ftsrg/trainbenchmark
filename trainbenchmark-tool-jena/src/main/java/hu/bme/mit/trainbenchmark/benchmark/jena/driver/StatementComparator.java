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
package hu.bme.mit.trainbenchmark.benchmark.jena.driver;

import java.util.Comparator;

import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

import hu.bme.mit.trainbenchmark.benchmark.jena.comparators.ResourceComparator;

public class StatementComparator implements Comparator<Statement> {

	protected final ResourceComparator resourceComparator = new ResourceComparator();

	@Override
	public int compare(final Statement o1, final Statement o2) {
		final Resource r1 = o1.getObject().asResource();
		final Resource r2 = o2.getObject().asResource();

		return resourceComparator.compare(r1, r2);
	}
}
