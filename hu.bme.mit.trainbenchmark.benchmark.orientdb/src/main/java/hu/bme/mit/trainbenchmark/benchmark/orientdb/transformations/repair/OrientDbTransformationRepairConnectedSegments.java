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
package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair;

import static hu.bme.mit.trainbenchmark.benchmark.orientdb.constants.OrientDbConstants.relationshipTypeConnectsTo;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbConnectedSegmentsMatch;

import java.io.IOException;
import java.util.Collection;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

public class OrientDbTransformationRepairConnectedSegments extends OrientDbTransformationRepair<OrientDbConnectedSegmentsMatch> {

	public OrientDbTransformationRepairConnectedSegments(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

	@Override
	public void rhs(final Collection<OrientDbConnectedSegmentsMatch> matches) throws IOException {
		for (final OrientDbConnectedSegmentsMatch csm : matches) {
			// delete segment2 with all its relationships
			final Vertex segment2 = csm.getSegment2();
			for (Edge edge : segment2.getEdges(Direction.BOTH)) {
				edge.remove();
			}
			segment2.remove();
			// (segment1)-[:connectsTo]->(segment3)
			csm.getSegment1().addEdge(relationshipTypeConnectsTo, csm.getSegment3());
		}
	}

}
