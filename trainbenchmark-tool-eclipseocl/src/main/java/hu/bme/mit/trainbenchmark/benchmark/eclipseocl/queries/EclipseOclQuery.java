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
package hu.bme.mit.trainbenchmark.benchmark.eclipseocl.queries;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.OCL.Helper;
import org.eclipse.ocl.ecore.OCL.Query;
import org.eclipse.ocl.ecore.OCLExpression;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.railway.RailwayContainer;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

public abstract class EclipseOclQuery<TMatch extends EmfMatch> extends ModelQuery<TMatch, EmfDriver> {

	protected Collection<TMatch> matches;
	protected OCL ocl;
	protected Query queryEvaluator;
	protected RailwayContainer container;

	public EclipseOclQuery(final EmfDriver driver, final BenchmarkConfigCore benchmarkConfig, final RailwayQuery query)
			throws IOException, ParserException {
		super(query, driver);

		final String oclQueryDefinition = FileUtils.readFileToString(new File(benchmarkConfig.getWorkspaceDir()
				+ "/trainbenchmark-tool-eclipseocl/src/main/resources/queries/" + query + ".ocl"));

		ocl = OCL.newInstance();
		final Helper helper = ocl.createOCLHelper();
		helper.setContext(RailwayPackage.eINSTANCE.getRailwayContainer());
		final OCLExpression expression = helper.createQuery(oclQueryDefinition);
		queryEvaluator = ocl.createQuery(expression);
	}

	public static EclipseOclQuery<?> newInstance(final EmfDriver driver, final BenchmarkConfigCore benchmarkConfig,
			final RailwayQuery query) throws Exception {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new EclipseOclQueryConnectedSegments(driver, benchmarkConfig);
		case POSLENGTH:
			return new EclipseOclQueryPosLength(driver, benchmarkConfig);
		case ROUTESENSOR:
			return new EclipseOclQueryRouteSensor(driver, benchmarkConfig);
		case SEMAPHORENEIGHBOR:
			return new EclipseOclQuerySemaphoreNeighbor(driver, benchmarkConfig);
		case SWITCHMONITORED:
			return new EclipseOclQuerySwitchMonitored(driver, benchmarkConfig);
		case SWITCHSET:
			return new EclipseOclQuerySwitchSet(driver, benchmarkConfig);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}

}
