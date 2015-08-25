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
package hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.match.JenaSemaphoreNeighborMatch;

import java.io.IOException;
import java.util.Collection;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class JenaTransformationRepairSemaphoreNeighbor extends JenaTransformationRepair<JenaSemaphoreNeighborMatch> {

	public JenaTransformationRepairSemaphoreNeighbor(final JenaDriver jenaDriver) {
		super(jenaDriver);
	}

	@Override
	public void rhs(final Collection<JenaSemaphoreNeighborMatch> matches) throws IOException {
		final Model model = jenaDriver.getModel();

		final Property entry = model.getProperty(BASE_PREFIX + ENTRY);
		for (final JenaSemaphoreNeighborMatch match : matches) {
			final Resource route2 = match.getRoute2();
			final Resource semaphore = match.getSemaphore();

			model.add(model.createStatement(route2, entry, semaphore));
		}
	}

}
