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
package hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver.Rdf4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.Rdf4jTransformation;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.RepositoryConnection;

import java.util.Collection;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

public class Rdf4jTransformationRepairSemaphoreNeighbor extends Rdf4jTransformation<Rdf4jSemaphoreNeighborMatch> {

	public Rdf4jTransformationRepairSemaphoreNeighbor(final Rdf4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Rdf4jSemaphoreNeighborMatch> matches) {
		final RepositoryConnection con = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final IRI entry = vf.createIRI(BASE_PREFIX + ENTRY);

		for (final Rdf4jSemaphoreNeighborMatch match : matches) {
			final Resource route2 = match.getRoute2();
			final Resource semaphore = match.getSemaphore();
			con.add(route2, entry, semaphore);
		}
	}

}
