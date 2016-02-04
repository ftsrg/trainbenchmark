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
package hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject;

import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.ID_PREFIX;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Selector;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.RDF;

import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class JenaTransformationInjectConnectedSegments extends JenaTransformationInject {

	public JenaTransformationInjectConnectedSegments(final JenaDriver driver) {
		super(driver);
	}

	@Override
	public void performRHS(final Collection<Resource> segments) throws Exception {
		final Model model = driver.getModel();
		final Property connectsToProperty = model.getProperty(BASE_PREFIX + ModelConstants.CONNECTS_TO);
		final Property monitoredByProperty = model.getProperty(BASE_PREFIX + ModelConstants.MONITORED_BY);
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

			// delete (segment1)-[:connectsTo]->(segment3) edge
			model.remove(connectsToEdge0);

			// create (segment2) node
			final Long newVertexId = driver.getNewVertexId();
			final Resource segment2 = model.createResource(BASE_PREFIX + ID_PREFIX + newVertexId);
			model.add(model.createStatement(segment2, RDF.type, segmentType));

			// get (sensor) nodes
			final Selector monitoredByEdgesSelector = new SimpleSelector(segment1, monitoredByProperty, (RDFNode) null);
			final StmtIterator monitoredByEdges = model.listStatements(monitoredByEdgesSelector);
			final List<RDFNode> sensors = new LinkedList<>();
			while (monitoredByEdges.hasNext()) {
				final Statement monitoredByEdge = monitoredByEdges.next();
				final RDFNode sensor = monitoredByEdge.getObject();
				sensors.add(sensor);
			}
			// (segment2)-[:monitoredBy]->(sensor)
			for (final RDFNode sensor : sensors) {
				model.add(segment2, monitoredByProperty, sensor);								
			}		
			// (segment1)-[:connectsTo]->(segment2)
			model.add(segment1, connectsToProperty, segment2);
			// (segment2)-[:connectsTo]->(segment3)
			model.add(segment2, connectsToProperty, segment3);
		}
	}

}
