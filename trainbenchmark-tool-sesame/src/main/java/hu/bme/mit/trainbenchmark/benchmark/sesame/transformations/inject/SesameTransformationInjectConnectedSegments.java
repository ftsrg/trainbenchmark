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

import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.repository.RepositoryConnection;

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameConnectedSegmentsInjectMatch;

public class SesameTransformationInjectConnectedSegments extends SesameTransformationInject<SesameConnectedSegmentsInjectMatch> {

	public SesameTransformationInjectConnectedSegments(final SesameDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<SesameConnectedSegmentsInjectMatch> connectedSegmentsInjectMatches) throws Exception {
		final RepositoryConnection connection = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final URI connectsTo = vf.createURI(BASE_PREFIX + CONNECTS_TO);
		final URI monitoredBy = vf.createURI(BASE_PREFIX + MONITORED_BY);
		final URI segmentType = vf.createURI(BASE_PREFIX + SEGMENT);

		for (final SesameConnectedSegmentsInjectMatch csim : connectedSegmentsInjectMatches) {
			// create (segment2) node
			final Long newVertexId = driver.getNewVertexId();
			final URI segment2 = vf.createURI(BASE_PREFIX + ID_PREFIX + newVertexId);
			connection.add(segment2, RDF.TYPE, segmentType);

			// (segment1)-[:connectsTo]->(segment2)
			connection.add(csim.getSegment1(), connectsTo, segment2);
			// (segment2)-[:connectsTo]->(segment3)
			connection.add(segment2, connectsTo, csim.getSegment3());
			// (segment2)-[:monitoredBy]->(sensor)
			connection.add(segment2, monitoredBy, csim.getSensor());
			connection.remove(csim.getSegment1(), connectsTo, csim.getSegment3());
		}
	}

}
