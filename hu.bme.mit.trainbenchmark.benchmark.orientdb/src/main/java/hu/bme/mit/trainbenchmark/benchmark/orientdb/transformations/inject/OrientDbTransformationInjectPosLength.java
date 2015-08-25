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
package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.inject;

import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.LENGTH;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;

import java.util.Collection;

import com.tinkerpop.blueprints.Vertex;

public class OrientDbTransformationInjectPosLength extends OrientDbTransformationInject {

	public OrientDbTransformationInjectPosLength(final OrientDbDriver orientDriver) {
		super(orientDriver);
	}

	@Override
	public void rhs(final Collection<Vertex> segments) {
		for (final Vertex segment : segments) {
			segment.setProperty(LENGTH, 0);
		}
	}
	
}
