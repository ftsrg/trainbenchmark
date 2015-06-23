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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.ID_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameSwitchSensorMatch;

import java.io.IOException;
import java.util.Collection;

import org.openrdf.model.Resource;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

public class SesameTransformationRepairSwitchSensor extends SesameTransformationRepair<SesameSwitchSensorMatch> {

	public SesameTransformationRepairSwitchSensor(final SesameDriver sesameDriver) {
		super(sesameDriver);
	}

	@Override
	public void rhs(final Collection<SesameSwitchSensorMatch> matches) throws IOException {
		final RepositoryConnection con = sesameDriver.getConnection();
		final ValueFactory vf = sesameDriver.getValueFactory();

		final URI sensorEdgeType = vf.createURI(BASE_PREFIX + SENSOR_EDGE);
		final URI sensorType = vf.createURI(BASE_PREFIX + SENSOR);

		try {
			for (final SesameSwitchSensorMatch match : matches) {
				final Resource sw = match.getSw();

				final URI sensor = vf.createURI(BASE_PREFIX + ID_PREFIX + sesameDriver.getNewVertexId());

				// set vertex type
				con.add(sensor, RDF.TYPE, sensorType);
				// insert edge
				con.add(sw, sensorEdgeType, sensor);
			}
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

}
