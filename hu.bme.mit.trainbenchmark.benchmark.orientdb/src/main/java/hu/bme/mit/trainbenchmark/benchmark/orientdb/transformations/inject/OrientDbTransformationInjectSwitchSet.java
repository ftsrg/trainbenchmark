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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.constants.Position;

import java.util.Collection;

import com.tinkerpop.blueprints.Vertex;

public class OrientDbTransformationInjectSwitchSet extends OrientDbTransformationInject {

	public OrientDbTransformationInjectSwitchSet(final OrientDbDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<Vertex> switches) {
		for (final Vertex sw : switches) {
			final String currentPositionString = sw.getProperty(CURRENTPOSITION);
			final Position currentPosition = Position.valueOf(currentPositionString);
			final Position newCurrentPosition = Position.values()[(currentPosition.ordinal() + 1) % Position.values().length];			
			sw.setProperty(CURRENTPOSITION, newCurrentPosition.toString());
		}
	}
	
}
