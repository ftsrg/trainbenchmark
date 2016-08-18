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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.ID_PREFIX;

import java.util.Collection;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.repository.RepositoryConnection;

import hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver.Rdf4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.Rdf4jTransformation;

public class Rdf4jTransformationRepairSwitchMonitored extends Rdf4jTransformation<Rdf4jSwitchMonitoredMatch> {

	public Rdf4jTransformationRepairSwitchMonitored(final Rdf4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Rdf4jSwitchMonitoredMatch> matches) throws Exception {
		final RepositoryConnection con = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final IRI sensorEdgeType = vf.createIRI(BASE_PREFIX + MONITORED_BY);
		final IRI sensorType = vf.createIRI(BASE_PREFIX + SENSOR);
		final IRI trackElementType = vf.createIRI(BASE_PREFIX + TRACKELEMENT);

		for (final Rdf4jSwitchMonitoredMatch match : matches) {
			final Resource sw = match.getSw();

			final IRI sensor = vf.createIRI(BASE_PREFIX + ID_PREFIX + driver.getNewVertexId());

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
