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

import static hu.bme.mit.trainbenchmark.benchmark.orientdb.constants.OrientDbConstants.relationshipTypeDefinedBy;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbRouteSensorMatch;

import java.util.Collection;

import com.tinkerpop.blueprints.Vertex;

public class OrientDbTransformationRepairRouteSensor extends OrientDbTransformationRepair<OrientDbRouteSensorMatch> {

	public OrientDbTransformationRepairRouteSensor(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

	@Override
	public void rhs(final Collection<OrientDbRouteSensorMatch> matches) {
		for (final OrientDbRouteSensorMatch rsm : matches) {
			final Vertex route = rsm.getRoute();
			final Vertex sensor = rsm.getSensor();
			route.addEdge(relationshipTypeDefinedBy, sensor);
		}
	}
	
}
