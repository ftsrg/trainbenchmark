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
package hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.user;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTSTO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.ID_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;

import java.io.IOException;
import java.util.Collection;

import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

public class SesameTransformationUserConnectedSegments extends SesameTransformationUser {

	public SesameTransformationUserConnectedSegments(final SesameDriver sesameDriver) {
		super(sesameDriver);
	}

	@Override
	public void rhs(final Collection<URI> segments) throws IOException {
		final RepositoryConnection connection = sesameDriver.getConnection();
		final ValueFactory vf = sesameDriver.getValueFactory();

		final URI connectsTo = vf.createURI(BASE_PREFIX + CONNECTSTO);
		final URI sensorEdgeType = vf.createURI(BASE_PREFIX + SENSOR_EDGE);
		
		final URI segmentType = vf.createURI(BASE_PREFIX + SEGMENT);
		
		for (final URI segment1 : segments) {
			try {
				final RepositoryResult<Statement> connectsToEdges0 = connection.getStatements(segment1, connectsTo, null, true);
				if (!connectsToEdges0.hasNext()) {
					continue;
				}
				final RepositoryResult<Statement> sensorEdges = connection.getStatements(segment1, sensorEdgeType, null, true);
				if (!sensorEdges.hasNext()) {
					continue;
				}
				final Statement connectsToEdge0 = connectsToEdges0.next();
				final Statement sensorEdge = sensorEdges.next();
				
				final Value segment3 = connectsToEdge0.getObject();
				final Value sensor = sensorEdge.getObject();
				
				// delete (segment1)-[:connectsTo]->(segment3) edge
				connection.remove(connectsToEdge0);
				
				// create (segment2) node
				final URI segment2 = vf.createURI(BASE_PREFIX + ID_PREFIX + sesameDriver.getNewVertexId());
				connection.add(segment2, RDF.TYPE, segmentType);
				// (segment1)-[:connectsTo]->(segment2)
				connection.add(segment1, connectsTo, segment2);
				// (segment2)-[:connectsTo]->(segment3)
				connection.add(segment2, connectsTo, segment3);
				// (segment1)-[:sensor]->(sensor)
				connection.add(segment1, sensorEdgeType, sensor);
			} catch (final RepositoryException e) {
				throw new IOException();
			}
		}
	}

}
