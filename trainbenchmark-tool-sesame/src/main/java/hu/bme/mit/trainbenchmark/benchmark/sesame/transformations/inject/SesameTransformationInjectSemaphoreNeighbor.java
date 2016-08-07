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
package hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.inject;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.openrdf.model.URI;
import org.openrdf.repository.RepositoryException;

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameVertexMatch;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class SesameTransformationInjectSemaphoreNeighbor extends SesameTransformationInject {

	public SesameTransformationInjectSemaphoreNeighbor(final SesameDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<SesameVertexMatch> routeMatches) throws RepositoryException {
		final List<URI> routes = routeMatches.stream().map(it -> it.getVertex()).collect(Collectors.toList());
		driver.deleteSingleOutgoingEdge(routes, ModelConstants.ROUTE, ModelConstants.ENTRY);
	}

}
