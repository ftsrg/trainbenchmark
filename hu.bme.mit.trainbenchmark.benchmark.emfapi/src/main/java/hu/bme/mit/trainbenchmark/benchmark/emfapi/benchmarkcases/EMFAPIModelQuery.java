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
package hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases;

import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EMFMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

public abstract class EMFAPIModelQuery<TMatch extends EMFMatch, TDriver extends EMFDriver> extends ModelQuery<TMatch, TDriver> {

	protected Collection<TMatch> matches;
	
	public EMFAPIModelQuery(final TDriver driver) {
		super(driver);
	}

	public static <TDriver extends EMFDriver> EMFAPIModelQuery<?, TDriver> newInstance(final TDriver driver, final RailwayQuery query) {
		switch (query) {
//		case CONNECTEDSEGMENTS:
//			return new EMFAPIConnectedSegmentsQuery(driver);
		case POSLENGTH:
			return new EMFAPIPosLengthQuery<TDriver>(driver);
//		case ROUTESENSOR:
//			return new EMFAPIRouteSensorQuery(driver);
//		case SEMAPHORENEIGHBOR:
//			return new EMFAPISemaphoreNeighborQuery(driver);
//		case SWITCHSENSOR:
//			return new EMFAPISwitchSensorQuery(driver);
//		case SWITCHSET:
//			return new EMFAPISwitchSetQuery(driver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}
	
}
