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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.ID_PREFIX;

import java.util.Collection;

import org.openrdf.model.Resource;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.repository.RepositoryConnection;

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameSwitchMonitoredMatch;

public class SesameTransformationRepairSwitchMonitored extends SesameTransformationRepair<SesameSwitchSensorMatch> {

	public SesameTransformationRepairSwitchMonitored(final SesameDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<SesameSwitchMonitoredMatch> matches) throws Exception {
		final RepositoryConnection con = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final URI sensorEdgeType = vf.createURI(BASE_PREFIX + MONITORED_BY);
		final URI sensorType = vf.createURI(BASE_PREFIX + SENSOR);
		final URI trackElementType = vf.createURI(BASE_PREFIX + TRACKELEMENT);

		for (final SesameSwitchMonitoredMatch match : matches) {
			final Resource sw = match.getSw();

			final URI sensor = vf.createURI(BASE_PREFIX + ID_PREFIX + driver.getNewVertexId());

			// set vertex type
			con.add(sensor, RDF.TYPE, sensorType);

			// insert the supertype as well
			if (!driver.isInferencing()) {
				con.add(sensor, RDF.TYPE, trackElementType);
			}

			// insert edge
			con.add(sw, sensorEdgeType, sensor);
		}
	}

}
