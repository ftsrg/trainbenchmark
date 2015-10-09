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
package hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;

import java.io.IOException;
import java.util.Collection;

import org.apache.jena.rdf.model.Resource;

import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;

public class JenaTransformationInjectSemaphoreNeighbor extends JenaTransformationInject {

	public JenaTransformationInjectSemaphoreNeighbor(final JenaDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<Resource> routes) throws IOException {
		driver.deleteSingleOutgoingEdge(routes, ROUTE, ENTRY);
	}

}
