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
import hu.bme.mit.trainbenchmark.railway.Segment;

import java.util.Collection;

public class EMFUserTransformationPosLength extends EMFUserTransformation<Segment> {

	public EMFUserTransformationPosLength(final EMFDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(final Collection<Segment> segments) {
		for (final Segment segment : segments) {
			segment.setLength(0);
		}
	}

}
