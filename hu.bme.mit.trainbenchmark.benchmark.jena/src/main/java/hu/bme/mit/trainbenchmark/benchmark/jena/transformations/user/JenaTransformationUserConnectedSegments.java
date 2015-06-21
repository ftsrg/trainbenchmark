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
package hu.bme.mit.trainbenchmark.benchmark.jena.transformations.user;

import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.ID_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.IOException;
import java.util.Collection;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;

public class JenaTransformationUserConnectedSegments extends JenaTransformationUser {

	public JenaTransformationUserConnectedSegments(final JenaDriver jenaDriver) {
		super(jenaDriver);
	}

	@Override
	public void rhs(final Collection<Resource> segments) throws IOException {
		final Model model = jenaDriver.getModel();
		final Property connectsToProperty = model.getProperty(BASE_PREFIX + ModelConstants.CONNECTSTO);
		final Property sensorEdgeProperty = model.getProperty(BASE_PREFIX + ModelConstants.SENSOR_EDGE);
		final Property segmentType = model.getProperty(BASE_PREFIX + ModelConstants.SEGMENT);

		for (final Resource segment1 : segments) {
			// get (segment3) node
			final Selector connectsToEdges0selector = new SimpleSelector(segment1, connectsToProperty, (RDFNode) null);
			final StmtIterator connectsToEdges0 = model.listStatements(connectsToEdges0selector);
			if (!connectsToEdges0.hasNext()) {
				continue;
			}
			final Statement connectsToEdge0 = connectsToEdges0.next();
			final RDFNode segment3 = connectsToEdge0.getObject();

			// get (sensor) node
			final Selector sensorEdgesSelector = new SimpleSelector(segment1, sensorEdgeProperty, (RDFNode) null);
			final StmtIterator sensorEdges = model.listStatements(sensorEdgesSelector);
			if (!sensorEdges.hasNext()) {
				continue;
			}
			final Statement sensorEdge = sensorEdges.next();
			final RDFNode sensor = sensorEdge.getObject();

			// delete (segment1)-[:connectsTo]->(segment3) edge
			model.remove(connectsToEdge0);

			// create (segment2) node
			final Long newVertexId = jenaDriver.getNewVertexId();
			final Resource segment2 = model.createResource(BASE_PREFIX + ID_PREFIX + newVertexId);

			model.add(model.createStatement(segment2, RDF.type, segmentType));

			// (segment1)-[:connectsTo]->(segment2)
			model.add(segment1, connectsToProperty, segment2);
			// (segment2)-[:connectsTo]->(segment3)
			model.add(segment2, connectsToProperty, segment3);
			// (segment1)-[:sensor]->(sensor)
			model.add(segment1, sensorEdgeProperty, sensor);
		}
	}

}
