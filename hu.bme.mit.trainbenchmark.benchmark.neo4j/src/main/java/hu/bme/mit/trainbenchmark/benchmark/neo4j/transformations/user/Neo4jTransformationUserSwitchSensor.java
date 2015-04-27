/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.user;

import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;

import java.util.Collection;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

public class Neo4jTransformationUserSwitchSensor extends Neo4jTransformationUser {

	public Neo4jTransformationUserSwitchSensor(final Neo4jDriver neoDriver) {
		super(neoDriver);
	}

	@Override
	public void rhs(final Collection<Node> switches) {
		for (final Node sw : switches) {
			final Iterable<Relationship> sensors = sw.getRelationships(relationshipTypeSensor);
			for (final Relationship sensor : sensors) {
				sensor.delete();
			}
		}
	}

}
