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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.GraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.TinkerGraphTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.Collection;

public class TinkerGraphTransformationRepairConnectedSegments<TTinkerGraphDriver extends GraphDriver>
		extends TinkerGraphTransformation<TinkerGraphConnectedSegmentsMatch, TTinkerGraphDriver> {

	public TinkerGraphTransformationRepairConnectedSegments(final TTinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<TinkerGraphConnectedSegmentsMatch> matches) {
		for (final TinkerGraphConnectedSegmentsMatch match : matches) {
			// delete segment2 with all its relationships
			final Vertex segment2 = match.getSegment2();
			segment2.remove();

			// (segment1)-[:connectsTo]->(segment3)
			match.getSegment1().addEdge(ModelConstants.CONNECTS_TO, match.getSegment3());
		}
	}

}
