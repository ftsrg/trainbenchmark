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
import java.util.List;
import java.util.stream.Collectors;

import org.apache.jena.rdf.model.Resource;

import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.JenaTransformation;

public class JenaTransformationInjectSemaphoreNeighbor extends JenaTransformation<JenaSemaphoreNeighborInjectMatch> {

	public JenaTransformationInjectSemaphoreNeighbor(final JenaDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<JenaSemaphoreNeighborInjectMatch> matches) throws IOException {
		final List<Resource> routes = matches.stream().map(it -> it.getRoute()).collect(Collectors.toList());
		driver.deleteSingleOutgoingEdge(routes, ROUTE, ENTRY);
	}

}
