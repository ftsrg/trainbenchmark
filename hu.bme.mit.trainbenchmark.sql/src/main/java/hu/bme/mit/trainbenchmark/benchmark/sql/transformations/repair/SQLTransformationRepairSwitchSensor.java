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
package hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair;

import hu.bme.mit.trainbenchmark.sql.driver.SQLDriver;
import hu.bme.mit.trainbenchmark.sql.match.SQLSwitchSensorMatch;

import java.util.Collection;

import javax.xml.soap.Node;

public class SQLTransformationRepairSwitchSensor extends SQLTransformationRepair<SQLSwitchSensorMatch> {

	public SQLTransformationRepairSwitchSensor(final SQLDriver sqlDriver) {
		super(sqlDriver);
	}

	@Override
	public void rhs(final Collection<SQLSwitchSensorMatch> matches) {
		for (final SQLSwitchSensorMatch ssnm : matches) {
			final Node sw = ssnm.getSw();
			final Node sensor = sqlDriver.getGraphDb().createNode(labelSensor);
			sw.createRelationshipTo(sensor, relationshipTypeSensor);
		}
	}

}
