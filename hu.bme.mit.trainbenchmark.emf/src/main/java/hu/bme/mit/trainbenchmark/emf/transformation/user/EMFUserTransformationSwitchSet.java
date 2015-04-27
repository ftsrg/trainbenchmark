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
package hu.bme.mit.trainbenchmark.emf.transformation.user;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.Position;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.Switch;

import java.util.Collection;

public class EMFUserTransformationSwitchSet extends EMFUserTransformation {

	public EMFUserTransformationSwitchSet(final EMFDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<RailwayElement> switches) {
		for (final Object railwayElement : switches) {
			final Switch sw = (Switch) railwayElement;
			final Position currentPosition = sw.getCurrentPosition();
			final Position newCurrentPosition = Position.get((currentPosition.ordinal() + 1) % Position.VALUES.size());
			sw.setCurrentPosition(newCurrentPosition);
		}
	}

}
