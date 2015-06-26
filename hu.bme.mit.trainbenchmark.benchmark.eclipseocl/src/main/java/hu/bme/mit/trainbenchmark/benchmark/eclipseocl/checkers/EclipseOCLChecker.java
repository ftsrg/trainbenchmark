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
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFMatch;
import hu.bme.mit.trainbenchmark.railway.RailwayContainer;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Segment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.OCL.Helper;
import org.eclipse.ocl.ecore.OCL.Query;
import org.eclipse.ocl.ecore.OCLExpression;

public class EclipseOCLChecker extends Checker<EMFMatch> {

	protected Collection<EMFMatch> matches;
	protected OCL ocl;
	protected Query queryEvaluator;
	protected RailwayContainer container;
	protected EMFDriver driver;

	public EclipseOCLChecker(final EMFDriver driver, final BenchmarkConfig bc) throws IOException {
		super();
		this.driver = driver;

		final String oclQuery = Util.readFile(bc.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.eclipseocl/src/main/resources/queries/" + bc.getQuery() + ".ocl");

		ocl = OCL.newInstance();
		final Helper helper = ocl.createOCLHelper();
		helper.setContext(RailwayPackage.eINSTANCE.getSegment());
		try {
			final OCLExpression query = helper.createQuery(oclQuery);
			queryEvaluator = ocl.createQuery(query);
		} catch (final ParserException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Collection<EMFMatch> check() {
		matches = new ArrayList<>();

//		final TreeIterator<EObject> contents = driver.getContainer().eAllContents();
		final Object evaluate = queryEvaluator.evaluate(driver.getContainer());
		System.out.println(evaluate);
		System.out.println(evaluate.getClass());
		final Set<Segment> results = (Set<Segment>) evaluate;
		System.out.println(results.size());
		

		return matches;
	}
}
