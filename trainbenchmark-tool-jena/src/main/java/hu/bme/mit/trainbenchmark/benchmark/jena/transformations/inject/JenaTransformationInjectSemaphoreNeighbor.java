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
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaVertexMatch;

public class JenaTransformationInjectSemaphoreNeighbor extends JenaTransformationInject<JenaVertexMatch> {

	public JenaTransformationInjectSemaphoreNeighbor(final JenaDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<JenaVertexMatch> routeMatches) throws IOException {
		final List<Resource> routes = routeMatches.stream().map(it -> it.getVertex()).collect(Collectors.toList());
		driver.deleteSingleOutgoingEdge(routes, ROUTE, ENTRY);
	}

}
