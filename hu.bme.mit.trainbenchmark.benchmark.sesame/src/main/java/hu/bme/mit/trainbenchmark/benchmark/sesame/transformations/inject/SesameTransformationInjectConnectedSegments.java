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
package hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.inject;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTSTO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.ID_PREFIX;

import java.util.Collection;

import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryResult;

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;

public class SesameTransformationInjectConnectedSegments extends SesameTransformationInject {

	public SesameTransformationInjectConnectedSegments(final SesameDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<URI> segments) throws Exception {
		final RepositoryConnection connection = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final URI connectsTo = vf.createURI(BASE_PREFIX + CONNECTSTO);
		final URI sensorEdgeType = vf.createURI(BASE_PREFIX + SENSOR_EDGE);

		final URI segmentType = vf.createURI(BASE_PREFIX + SEGMENT);

		for (final URI segment1 : segments) {
			// get (segment3) node
			final RepositoryResult<Statement> connectsToEdges0 = connection.getStatements(segment1, connectsTo, null, true);
			if (!connectsToEdges0.hasNext()) {
				continue;
			}
			final Statement connectsToEdge0 = connectsToEdges0.next();
			final Value segment3 = connectsToEdge0.getObject();

			// get (sensor) node
			final RepositoryResult<Statement> sensorEdges = connection.getStatements(segment1, sensorEdgeType, null, true);
			if (!sensorEdges.hasNext()) {
				continue;
			}
			final Statement sensorEdge = sensorEdges.next();
			final Value sensor = sensorEdge.getObject();

			// delete (segment1)-[:connectsTo]->(segment3) edge
			connection.remove(connectsToEdge0);

			// create (segment2) node
			final Long newVertexId = driver.getNewVertexId();
			final URI segment2 = vf.createURI(BASE_PREFIX + ID_PREFIX + newVertexId);
			connection.add(segment2, RDF.TYPE, segmentType);
			// (segment1)-[:connectsTo]->(segment2)
			connection.add(segment1, connectsTo, segment2);
			// (segment2)-[:connectsTo]->(segment3)
			connection.add(segment2, connectsTo, segment3);
			// (segment1)-[:sensor]->(sensor)
			connection.add(segment1, sensorEdgeType, sensor);
		}
	}

}
