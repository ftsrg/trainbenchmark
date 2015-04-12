/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.eclipseocl.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayContainer;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.OCL.Helper;
import org.eclipse.ocl.ecore.OCLExpression;

public abstract class EclipseOCLBenchmarkCase<T extends RailwayElement> extends AbstractBenchmarkCase<T> {

	protected RailwayContainer container;

	protected OCL ocl;
	protected OCLExpression query;

	@Override
	public void read() {
		final String modelPath = bc.getModelPathNameWithoutExtension() + ".emf";
		final EMFDriver emfDriver = new EMFDriver(modelPath);
		driver = (Driver<T>) emfDriver;

		container = emfDriver.getContainer();
	}

	@Override
	protected void init() throws IOException {
		ocl = OCL.newInstance();
		final String oclConstraint = Util.readFile(bc.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.eclipseocl/src/main/resources/queries/" + getName() + ".ocl");

		final Helper helper = ocl.createOCLHelper();
		helper.setContext(RailwayPackage.eINSTANCE.getSegment());
		try {
			query = helper.createQuery(oclConstraint);
		} catch (final ParserException e) {
			throw new IOException(e);
		}
	}
	
	@Override
	protected Collection<T> check() {
		matches = new ArrayList<>();
		
		final TreeIterator<EObject> contents = container.eAllContents();
		while (contents.hasNext()) {
			final EObject eObject = contents.next();

			final Object result = ocl.evaluate(eObject, query);
			if (result == Boolean.TRUE) {
				matches.add((T) eObject);
			}
		}
				
		return matches;
	}


}
