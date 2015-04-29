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
package hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFMatch;

import java.util.Collection;

public abstract class JavaChecker extends Checker<EMFMatch> {

	protected Collection<EMFMatch> matches;
	protected final EMFDriver emfDriver;

	public JavaChecker(final EMFDriver emfDriver) {
		this.emfDriver = emfDriver;
	}

	public static JavaChecker newInstance(final EMFDriver driver, final Query query) {
		switch (query) {
		case POSLENGTH:
			return new JavaPosLengthChecker(driver);
		case ROUTESENSOR:
			return new JavaRouteSensorChecker(driver);
		case SEMAPHORENEIGHBOR:
			return new JavaSemaphoreNeighborChecker(driver);
		case SWITCHSENSOR:
			return new JavaSwitchSensorChecker(driver);
		case SWITCHSET:
			return new JavaSwitchSetChecker(driver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}

}
