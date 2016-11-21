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
package hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.inject;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTS_TO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.ID_PREFIX;

import java.util.Collection;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.repository.RepositoryConnection;

import hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver.Rdf4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.Rdf4jTransformation;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;

public class Rdf4jTransformationInjectConnectedSegments<TRdf4jDriver extends Rdf4jDriver> extends Rdf4jTransformation<Rdf4jConnectedSegmentsInjectMatch, TRdf4jDriver> {

	public Rdf4jTransformationInjectConnectedSegments(final TRdf4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Rdf4jConnectedSegmentsInjectMatch> matches) throws Exception {
		final RepositoryConnection connection = driver.getConnection();
		final ValueFactory vf = driver.getValueFactory();

		final IRI length = vf.createIRI(BASE_PREFIX + LENGTH);
		final IRI connectsTo = vf.createIRI(BASE_PREFIX + CONNECTS_TO);
		final IRI monitoredBy = vf.createIRI(BASE_PREFIX + MONITORED_BY);
		final IRI segmentType = vf.createIRI(BASE_PREFIX + SEGMENT);
		final Literal lengthLiteral = vf.createLiteral(TrainBenchmarkConstants.DEFAULT_SEGMENT_LENGTH);

		for (final Rdf4jConnectedSegmentsInjectMatch csim : matches) {
			// create (segment2) node
			final Long newVertexId = driver.getNewVertexId();
			final IRI segment2 = vf.createIRI(BASE_PREFIX + ID_PREFIX + newVertexId);
			connection.add(segment2, RDF.TYPE, segmentType);

			// (segment1)-[:connectsTo]->(segment2)
			connection.add(csim.getSegment1(), connectsTo, segment2);
			// (segment2)-[:connectsTo]->(segment3)
			connection.add(segment2, connectsTo, csim.getSegment3());

			// (segment2)-[:monitoredBy]->(sensor)
			connection.add(segment2, monitoredBy, csim.getSensor());

			// remove (segment1)-[:connectsTo]->(segment3)
			connection.remove(csim.getSegment1(), connectsTo, csim.getSegment3());
		}
	}

}
