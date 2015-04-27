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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

public abstract class EMFUserTransformation extends Transformation<RailwayElement> {

	protected EMFDriver driver;

	public EMFUserTransformation(final EMFDriver driver) {
		super();
		this.driver = driver;
	}

	public static EMFUserTransformation newInstance(final EMFDriver driver, final Query query) {
		switch (query) {
		case POSLENGTH:
			return new EMFUserTransformationPosLength(driver);
		case ROUTESENSOR:
			return new EMFUserTransformationRouteSensor(driver);
		case SEMAPHORENEIGHBOR:
			return new EMFUserTransformationSemaphoreNeighbor(driver);
		case SWITCHSENSOR:
			return new EMFUserTransformationSwitchSensor(driver);
		case SWITCHSET:
			return new EMFUserTransformationSwitchSet(driver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}

}
