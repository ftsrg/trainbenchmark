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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.inject;

import java.util.Collection;

import org.neo4j.graphdb.Relationship;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jApiTransformation;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;

public class Neo4jApiTransformationInjectRouteSensor extends Neo4jApiTransformation<Neo4jRouteSensorInjectMatch> {

	public Neo4jApiTransformationInjectRouteSensor(final Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Neo4jRouteSensorInjectMatch> matches) {
		for (final Neo4jRouteSensorInjectMatch match : matches) {
			final Iterable<Relationship> requiress = match.getRoute().getRelationships(Neo4jConstants.relationshipTypeRequires);
			for (final Relationship requires : requiress) {
				if (requires.getEndNode().equals(match.getSensor())) {
					requires.delete();
				}
			}
		}
	}

}
