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
package hu.bme.mit.trainbenchmark.benchmark.eclipseocl.checkers;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFMatch;
import hu.bme.mit.trainbenchmark.railway.RailwayContainer;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.OCL.Helper;
import org.eclipse.ocl.ecore.OCL.Query;
import org.eclipse.ocl.ecore.OCLExpression;

public abstract class EclipseOCLChecker<T extends EMFMatch> extends Checker<T> {

	protected Collection<T> matches;
	protected OCL ocl;
	protected Query queryEvaluator;
	protected RailwayContainer container;
	protected EMFDriver driver;

	public EclipseOCLChecker(final EMFDriver driver, final BenchmarkConfig bc) throws IOException {
		super();
		this.driver = driver;

		final String oclQuery = FileUtils.readFileToString(new File(bc.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.eclipseocl/src/main/resources/queries/" + bc.getQuery() + ".ocl"));

		ocl = OCL.newInstance();
		final Helper helper = ocl.createOCLHelper();
		try {
			helper.setContext(getContext());
			final OCLExpression query = helper.createQuery(oclQuery);
			queryEvaluator = ocl.createQuery(query);
		} catch (final ParserException e) {
			throw new IOException(e);
		}
	}

	protected abstract EClassifier getContext();

	public static EclipseOCLChecker<?> newInstance(final EMFDriver driver, final BenchmarkConfig bc) throws IOException {
		switch (bc.getQuery()) {
		case CONNECTEDSEGMENTS:
			return new EclipseOCLConnectedSegmentsChecker(driver, bc);
		case POSLENGTH:
			return new EclipseOCLPosLengthChecker(driver, bc);
		case ROUTESENSOR:
			return new EclipseOCLRouteSensorChecker(driver, bc);
		case SEMAPHORENEIGHBOR:
			return new EclipseOCLSemaphoreNeighborChecker(driver, bc);
		case SWITCHSENSOR:
			return new EclipseOCLSwitchSensorChecker(driver, bc);
		case SWITCHSET:
			return new EclipseOCLSwitchSetChecker(driver, bc);
		default:
			throw new UnsupportedOperationException("Query " + bc.getQuery() + " not supported");
		}
	}

}
