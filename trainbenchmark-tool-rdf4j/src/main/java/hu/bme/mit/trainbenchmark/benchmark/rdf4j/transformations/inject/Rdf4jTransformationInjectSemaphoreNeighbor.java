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
package hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.inject;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.rdf4j.model.IRI;

import hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver.Rdf4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.Rdf4jTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class Rdf4jTransformationInjectSemaphoreNeighbor<TRdf4jDriver extends Rdf4jDriver> extends Rdf4jTransformation<Rdf4jSemaphoreNeighborInjectMatch, TRdf4jDriver> {

	public Rdf4jTransformationInjectSemaphoreNeighbor(final TRdf4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Rdf4jSemaphoreNeighborInjectMatch> matches) {
		final List<IRI> routes = matches.stream().map(it -> it.getRoute()).collect(Collectors.toList());
		driver.deleteSingleOutgoingEdge(routes, ModelConstants.ENTRY);
	}

}
