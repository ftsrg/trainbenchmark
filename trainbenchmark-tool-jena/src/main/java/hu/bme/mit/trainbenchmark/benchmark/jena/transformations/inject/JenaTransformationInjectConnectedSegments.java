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

import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.JenaTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;

import java.util.Collection;

import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.ID_PREFIX;

public class JenaTransformationInjectConnectedSegments extends JenaTransformation<JenaConnectedSegmentsInjectMatch> {

	public JenaTransformationInjectConnectedSegments(final JenaDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<JenaConnectedSegmentsInjectMatch> matches) throws Exception {
		final Model model = driver.getModel();

		final Property length = model.getProperty(BASE_PREFIX + ModelConstants.LENGTH);
		final Property connectsTo = model.getProperty(BASE_PREFIX + ModelConstants.CONNECTS_TO);
		final Property monitoredBy = model.getProperty(BASE_PREFIX + ModelConstants.MONITORED_BY);
		final Property segmentType = model.getProperty(BASE_PREFIX + ModelConstants.SEGMENT);

		for (final JenaConnectedSegmentsInjectMatch match : matches) {
			// create (segment2) node
			final Long newVertexId = driver.generateNewVertexId();
			final Resource segment2 = model.createResource(BASE_PREFIX + ID_PREFIX + newVertexId);
			model.add(model.createStatement(segment2, RDF.type, segmentType));
			model.add(model.createLiteralStatement(segment2, length, TrainBenchmarkConstants.DEFAULT_SEGMENT_LENGTH));

			// (segment1)-[:connectsTo]->(segment2)
			model.add(match.getSegment1(), connectsTo, segment2);
			// (segment2)-[:connectsTo]->(segment3)
			model.add(segment2, connectsTo, match.getSegment3());
			// (segment2)-[:monitoredBy]->(sensor)
			model.add(segment2, monitoredBy, match.getSensor());

			// remove (segment1)-[:connectsTo]->(segment3)
			model.remove(match.getSegment1(), connectsTo, match.getSegment3());
		}
	}

}
