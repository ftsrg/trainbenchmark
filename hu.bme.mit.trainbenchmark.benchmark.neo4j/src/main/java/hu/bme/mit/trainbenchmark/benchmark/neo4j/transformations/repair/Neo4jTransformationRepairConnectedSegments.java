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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair;

import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeConnectsTo;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jConnectedSegmentsMatch;

import java.util.Collection;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

public class Neo4jTransformationRepairConnectedSegments extends Neo4jTransformationRepair<Neo4jConnectedSegmentsMatch> {

	public Neo4jTransformationRepairConnectedSegments(final Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<Neo4jConnectedSegmentsMatch> matches) {
		for (final Neo4jConnectedSegmentsMatch csm : matches) {
			// delete segment2 with all its relationships
			final Node segment2 = csm.getSegment2();
			for (final Relationship relationship : segment2.getRelationships()) {
				relationship.delete();
			}
			segment2.delete();
			// (segment1)-[:connectsTo]->(segment3)
			csm.getSegment1().createRelationshipTo(csm.getSegment3(), relationshipTypeConnectsTo);
		}
	}

}
