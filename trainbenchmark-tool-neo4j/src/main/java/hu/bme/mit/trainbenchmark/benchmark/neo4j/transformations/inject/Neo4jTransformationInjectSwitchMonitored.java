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

import java.util.Collection;

import org.neo4j.graphdb.Relationship;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jTransformation;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;

public class Neo4jTransformationInjectSwitchMonitored extends Neo4jTransformation<Neo4jSwitchMonitoredInjectMatch> {

	public Neo4jTransformationInjectSwitchMonitored(final Neo4jDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Neo4jSwitchMonitoredInjectMatch> matches) {
		for (final Neo4jSwitchMonitoredInjectMatch match : matches) {
			final Iterable<Relationship> sensors = match.getSw().getRelationships(Neo4jConstants.relationshipTypeMonitoredBy);
			for (final Relationship sensor : sensors) {
				sensor.delete();
			}
		}
	}

}
