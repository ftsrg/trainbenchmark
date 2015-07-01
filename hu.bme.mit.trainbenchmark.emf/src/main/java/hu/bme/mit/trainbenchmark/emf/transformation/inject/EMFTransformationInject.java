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
package hu.bme.mit.trainbenchmark.emf.transformation.inject;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.transformation.EMFTransformation;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

public abstract class EMFTransformationInject<T extends RailwayElement> extends EMFTransformation<T> {

	protected EMFDriver driver;

	public EMFTransformationInject(final EMFDriver driver) {
		super();
		this.driver = driver;
	}

}
