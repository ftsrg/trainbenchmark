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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.inject;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;

import java.util.Collection;
import java.util.Iterator;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class TinkerGraphTransformationInjectConnectedSegments extends TinkerGraphTransformationInject {

	public TinkerGraphTransformationInjectConnectedSegments(final TinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public void performRHS(final Collection<Vertex> segments) {
		for (final Vertex segment1 : segments) {
			final Vertex segment2 = driver.getGraph().addVertex(ModelConstants.SEGMENT);

			final Iterable<Vertex> sensors = () -> segment1.vertices(Direction.OUT, ModelConstants.MONITORED_BY);
			// (segment2)-[:monitoredBy]->(sensor)
			for (final Vertex sensor : sensors) {
				segment2.addEdge(MONITORED_BY, sensor);
			}

			// delete (segment1)-[:connectsTo]->(segment3)
			final Iterator<Edge> connectsToEdges = segment1.edges(Direction.OUT, ModelConstants.CONNECTS_TO);
			if (!connectsToEdges.hasNext()) {
				continue;
			}
			final Edge connectsTo = connectsToEdges.next();
			final Vertex segment3 = connectsTo.inVertex();
			connectsTo.remove();
			// (segment1)-[:connectsTo]->(segment2)
			segment1.addEdge(ModelConstants.CONNECTS_TO, segment2);
			// (segment2)-[:connectsTo]->(segment3)
			segment2.addEdge(ModelConstants.CONNECTS_TO, segment3);
		}
	}

}
