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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.repair;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jApiTransformation;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;
import org.neo4j.graphdb.Node;

import java.util.Collection;

public class Neo4jApiTransformationRepairRouteSensor extends Neo4jApiTransformation<Neo4jRouteSensorMatch> {

	public Neo4jApiTransformationRepairRouteSensor(final Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Neo4jRouteSensorMatch> matches) {
		for (final Neo4jRouteSensorMatch match : matches) {
			final Node route = match.getRoute();
			final Node sensor = match.getSensor();
			route.createRelationshipTo(sensor, Neo4jConstants.relationshipTypeRequires);
		}
	}

}
