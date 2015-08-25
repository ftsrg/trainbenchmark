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

import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SENSOR_EDGE;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.ID_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.match.JenaSwitchSensorMatch;

import java.util.Collection;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class JenaTransformationRepairSwitchSensor extends JenaTransformationRepair<JenaSwitchSensorMatch> {

	public JenaTransformationRepairSwitchSensor(final JenaDriver jenaDriver) {
		super(jenaDriver);
	}

	@Override
	public void rhs(final Collection<JenaSwitchSensorMatch> matches) throws Exception {
		final Model model = jenaDriver.getModel();
		final Property sensorEdge = model.getProperty(BASE_PREFIX + SENSOR_EDGE);
		final Resource sensorType = model.getResource(BASE_PREFIX + SENSOR);

		for (final JenaSwitchSensorMatch match : matches) {
			final Resource sw = match.getSw();
			final Long newVertexId = jenaDriver.getNewVertexId();
			final Resource sensor = model.createResource(BASE_PREFIX + ID_PREFIX + newVertexId);

			model.add(model.createStatement(sw, sensorEdge, sensor));
			model.add(model.createStatement(sensor, RDF.type, sensorType));
		}

	}
}
