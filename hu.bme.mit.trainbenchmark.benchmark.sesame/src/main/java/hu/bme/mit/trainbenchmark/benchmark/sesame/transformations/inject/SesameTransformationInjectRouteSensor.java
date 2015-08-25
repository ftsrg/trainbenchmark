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

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants;

import java.util.Collection;

import org.openrdf.model.URI;
import org.openrdf.repository.RepositoryException;

public class SesameTransformationInjectRouteSensor extends SesameTransformationInject {

	public SesameTransformationInjectRouteSensor(final SesameDriver sesameDriver) {
		super(sesameDriver);
	}

	@Override
	public void rhs(final Collection<URI> routes) throws RepositoryException {
		sesameDriver.deleteOneOutgoingEdge(routes, RailwayModelConstants.ROUTE, RailwayModelConstants.DEFINED_BY);
	}

}
