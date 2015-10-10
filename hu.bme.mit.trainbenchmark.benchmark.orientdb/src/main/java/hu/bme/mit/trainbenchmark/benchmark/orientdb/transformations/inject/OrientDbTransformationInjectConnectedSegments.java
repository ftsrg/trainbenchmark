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
package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.inject;

import static hu.bme.mit.trainbenchmark.benchmark.orientdb.constants.OrientDbConstants.labelSegment;
import static hu.bme.mit.trainbenchmark.benchmark.orientdb.constants.OrientDbConstants.relationshipTypeConnectsTo;
import static hu.bme.mit.trainbenchmark.benchmark.orientdb.constants.OrientDbConstants.relationshipTypeSensor;

import java.io.IOException;
import java.util.Collection;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;

public class OrientDbTransformationInjectConnectedSegments extends OrientDbTransformationInject {

	public OrientDbTransformationInjectConnectedSegments(final OrientDbDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<Vertex> segments) throws IOException {
		for (final Vertex segment1 : segments) {
			final Iterable<Edge> sensors = segment1.getEdges(Direction.OUT, relationshipTypeSensor);
			if (!sensors.iterator().hasNext()) {
				continue;
			}
			
			final Vertex sensor = sensors.iterator().next().getVertex(Direction.IN);
			final Iterable<Edge> segment3s = segment1.getEdges(Direction.OUT, relationshipTypeConnectsTo);
			
			if (!segment3s.iterator().hasNext()) {
				continue;
			}
			final Edge connectsTo = segment3s.iterator().next();
			final Vertex segment3 = connectsTo.getVertex(Direction.IN);
			
			// transformation
			connectsTo.remove();
			final Vertex segment2 = driver.getGraphDb().addVertex(null);
			segment2.setProperty("labels", labelSegment);
			segment1.addEdge(relationshipTypeConnectsTo, segment2);
			segment2.addEdge(relationshipTypeConnectsTo, segment3);
			segment2.addEdge(relationshipTypeSensor, sensor);
		}
	}

}
