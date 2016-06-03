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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTS_TO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.ID_PREFIX;

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
	public void activate(final Collection<URI> segments) throws Exception {
		final RepositoryConnection connection = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final URI connectsTo = vf.createURI(BASE_PREFIX + CONNECTS_TO);
		final URI monitoredByEdgeType = vf.createURI(BASE_PREFIX + MONITORED_BY);

		final URI segmentType = vf.createURI(BASE_PREFIX + SEGMENT);

		for (final URI segment1 : segments) {
			// get (segment3) node
			final RepositoryResult<Statement> connectsToEdges0 = connection.getStatements(segment1, connectsTo, null, true);
			while (connectsToEdges0.hasNext()) {
				final Statement connectsToEdge0 = connectsToEdges0.next();
				final Value segment3 = connectsToEdge0.getObject();

				// delete (segment1)-[:connectsTo]->(segment3) edge
				connection.remove(connectsToEdge0);

				// insert (segment2) node
				final Long newVertexId = driver.getNewVertexId();
				final URI segment2 = vf.createURI(BASE_PREFIX + ID_PREFIX + newVertexId);
				connection.add(segment2, RDF.TYPE, segmentType);

				// insert the edges of the (segment2) node				
				// (segment1)-[:connectsTo]->(segment2)
				connection.add(segment1, connectsTo, segment2);
				// (segment2)-[:connectsTo]->(segment3)
				connection.add(segment2, connectsTo, segment3);
				
				// get (sensor) nodes and insert (segment1)-[:monitoredBy]->(sensor) edges
				final RepositoryResult<Statement> monitoredByEdges = connection.getStatements(segment1, monitoredByEdgeType, null, true);
				while (monitoredByEdges.hasNext()) {
					final Statement monitoredByEdge = monitoredByEdges.next();
					final Value sensor = monitoredByEdge.getObject();
					connection.add(segment2, monitoredByEdgeType, sensor);
				}
			}
		}
	}

}
