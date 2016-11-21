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

import org.openrdf.model.URI;
import org.openrdf.repository.RepositoryException;

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.SesameTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RdfConstants;

public class SesameTransformationInjectSemaphoreNeighbor<TSesameDriver extends SesameDriver> extends SesameTransformation<SesameSemaphoreNeighborInjectMatch, TSesameDriver> {

	public SesameTransformationInjectSemaphoreNeighbor(final TSesameDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<SesameSemaphoreNeighborInjectMatch> matches) throws RepositoryException {
		final URI entry = driver.getValueFactory().createURI(RdfConstants.BASE_PREFIX + ModelConstants.ENTRY);

		for (SesameSemaphoreNeighborInjectMatch match : matches) {
			driver.getConnection().remove(match.getRoute(), entry, match.getSemaphore());
		}
	}

}
