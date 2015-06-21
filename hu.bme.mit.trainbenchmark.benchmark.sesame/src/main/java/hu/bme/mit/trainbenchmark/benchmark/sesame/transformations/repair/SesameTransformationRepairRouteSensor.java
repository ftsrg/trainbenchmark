/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.DEFINED_BY;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameRouteSensorMatch;

import java.io.IOException;
import java.util.Collection;

import org.openrdf.model.Resource;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

public class SesameTransformationRepairRouteSensor extends SesameTransformationRepair<SesameRouteSensorMatch> {

	public SesameTransformationRepairRouteSensor(final SesameDriver sesameDriver) {
		super(sesameDriver);
	}

	@Override
	public void rhs(final Collection<SesameRouteSensorMatch> matches) throws IOException {
		final RepositoryConnection con = sesameDriver.getConnection();
		final ValueFactory vf = sesameDriver.getValueFactory();

		final URI definedBy = vf.createURI(BASE_PREFIX + DEFINED_BY);

		try {
			for (final SesameRouteSensorMatch match : matches) {
				final Resource route = match.getRoute();
				final Resource sensor = match.getSensor();

				con.add(route, definedBy, sensor);
			}
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

}
