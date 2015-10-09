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

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.OCL.Helper;
import org.eclipse.ocl.ecore.OCL.Query;
import org.eclipse.ocl.ecore.OCLExpression;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFMatch;
import hu.bme.mit.trainbenchmark.railway.RailwayContainer;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

public abstract class EclipseOCLChecker<TMatch extends EMFMatch> extends Checker<TMatch> {

	protected Collection<TMatch> matches;
	protected OCL ocl;
	protected Query queryEvaluator;
	protected RailwayContainer container;
	protected EMFDriver driver;

	public EclipseOCLChecker(final EMFDriver driver, final BenchmarkConfig bc) throws IOException, ParserException {
		super();
		this.driver = driver;

		final String oclQuery = FileUtils.readFileToString(new File(bc.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.eclipseocl/src/main/resources/queries/" + bc.getQuery() + ".ocl"));

		ocl = OCL.newInstance();
		final Helper helper = ocl.createOCLHelper();
		helper.setContext(RailwayPackage.eINSTANCE.getRailwayContainer());
		final OCLExpression query = helper.createQuery(oclQuery);
		queryEvaluator = ocl.createQuery(query);
	}

	public static EclipseOCLChecker<?> newInstance(final EMFDriver driver, final BenchmarkConfig bc) throws Exception {
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
