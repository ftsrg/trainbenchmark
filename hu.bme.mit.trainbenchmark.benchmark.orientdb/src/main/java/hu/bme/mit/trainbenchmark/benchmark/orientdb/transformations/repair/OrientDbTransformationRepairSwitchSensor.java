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

import static hu.bme.mit.trainbenchmark.benchmark.orientdb.constants.OrientDbConstants.labelSensor;
import static hu.bme.mit.trainbenchmark.benchmark.orientdb.constants.OrientDbConstants.relationshipTypeSensor;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbSwitchSensorMatch;

import java.util.Collection;

import com.tinkerpop.blueprints.Vertex;

public class OrientDbTransformationRepairSwitchSensor extends OrientDbTransformationRepair<OrientDbSwitchSensorMatch>{

	public OrientDbTransformationRepairSwitchSensor(final OrientDbDriver driver) {
		super(driver);
	}
	
	@Override
	public void rhs(final Collection<OrientDbSwitchSensorMatch> matches) {
		for (OrientDbSwitchSensorMatch ssm : matches) {
			final Vertex sw = ssm.getSw();
			final Vertex sensor = driver.getGraphDb().addVertex(null);
			sensor.setProperty("labels", labelSensor);
			sw.addEdge(relationshipTypeSensor, sensor);
		}
	}
	
}
