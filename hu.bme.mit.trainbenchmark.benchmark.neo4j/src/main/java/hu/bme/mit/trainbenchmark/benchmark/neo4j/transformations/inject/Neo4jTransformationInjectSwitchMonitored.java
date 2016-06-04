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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.inject;

import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeMonitoredBy;

import java.util.Collection;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;

public class Neo4jTransformationInjectSwitchMonitored extends Neo4jTransformationInject {

	public Neo4jTransformationInjectSwitchMonitored(final Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Node> switches) {
		for (final Node sw : switches) {
			final Iterable<Relationship> sensors = sw.getRelationships(relationshipTypeMonitoredBy);
			for (final Relationship sensor : sensors) {
				sensor.delete();
			}
		}
	}

}
