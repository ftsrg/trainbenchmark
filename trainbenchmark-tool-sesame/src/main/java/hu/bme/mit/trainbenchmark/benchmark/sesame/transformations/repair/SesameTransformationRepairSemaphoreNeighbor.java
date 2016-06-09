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
package hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameSemaphoreNeighborMatch;

import java.util.Collection;

import org.openrdf.model.Resource;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

public class SesameTransformationRepairSemaphoreNeighbor extends SesameTransformationRepair<SesameSemaphoreNeighborMatch> {

	public SesameTransformationRepairSemaphoreNeighbor(final SesameDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<SesameSemaphoreNeighborMatch> matches) throws RepositoryException {
		final RepositoryConnection con = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final URI entry = vf.createURI(BASE_PREFIX + ENTRY);

		for (final SesameSemaphoreNeighborMatch match : matches) {
			final Resource route2 = match.getRoute2();
			final Resource semaphore = match.getSemaphore();
			con.add(route2, entry, semaphore);
		}
	}

}
