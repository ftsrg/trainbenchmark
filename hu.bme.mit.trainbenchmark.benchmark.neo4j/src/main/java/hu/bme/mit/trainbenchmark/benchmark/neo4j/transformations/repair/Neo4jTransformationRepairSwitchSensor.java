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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair;

import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.labelSensor;
import static hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants.relationshipTypeSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchSensorMatch;

import java.util.Collection;

import org.neo4j.graphdb.Node;

public class Neo4jTransformationRepairSwitchSensor extends Neo4jTransformationRepair<Neo4jSwitchSensorMatch> {

	public Neo4jTransformationRepairSwitchSensor(final Neo4jDriver neoDriver) {
		super(neoDriver);
	}

	@Override
	public void rhs(final Collection<Neo4jSwitchSensorMatch> matches) {
		for (final Neo4jSwitchSensorMatch ssnm : matches) {
			final Node sw = ssnm.getSw();
			final Node sensor = neoDriver.getGraphDb().createNode(labelSensor);
			sw.createRelationshipTo(sensor, relationshipTypeSensor);
		}
	}

}
