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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.IncQueryCommon;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.IncQueryDeltaMonitor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.emf.FileBroker;
import hu.bme.mit.trainbenchmark.railway.RailwayContainer;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.util.IncQueryLoggingUtil;

public abstract class EMFIncQueryBenchmarkCase<T> extends AbstractBenchmarkCase<T> {

	protected BenchmarkResult bmr;
	protected BenchmarkConfig bc;
	protected RailwayContainer pack;

	protected AdvancedIncQueryEngine engine;
	protected ResourceSet resourceSet;
	protected Resource resource;

	protected IncQueryDeltaMonitor<T> incqueryDeltaMonitor;	
		
	protected EMFIncQueryBenchmarkConfig getEIQBC() {
		return (EMFIncQueryBenchmarkConfig) bc;
	}

	@Override
	public void init() throws IOException {
		IncQueryLoggingUtil.getDefaultLogger().setLevel(Level.OFF);
	}

	@Override
	public void destroy() {
		if (!engine.isManaged()) {
			engine.dispose();
		}
		engine = null;
		resource = null;
		resourceSet = null;
	}

	@Override
	public List<T> check() {
		results = new ArrayList<T>(incqueryDeltaMonitor.getMatching());
		return results;
	}

	@Override
	public void read() throws IOException {
		RailwayPackage.eINSTANCE.eClass();
		final URI resourceURI = FileBroker.getEMFUri(bc.getBenchmarkArtifact());
		resourceSet = new ResourceSetImpl();

		try {
			IncQueryCommon.setEIQOptions(getEIQBC());
			engine = AdvancedIncQueryEngine.createUnmanagedEngine(resourceSet);
//			final M patternMatcher = null; //= engine.getMatcher("posLength");
//			final PosLengthMatcher patternMatcher = PosLengthMatcher.on(engine);

//			final IQDeltaMonitor<Segment> iqDeltaMonitor = new IQDeltaMonitor<Segment>(patternMatcher, "Individual");
			final IncQueryDeltaMonitor<T> iqDeltaMonitor = new IncQueryDeltaMonitor<T>(patternMatcher, "Individual");
		} catch (final IncQueryException e) {
			throw new IOException(e);
		}
		
		resource = resourceSet.getResource(resourceURI, true);
		if (resource.getContents().size() > 0 && resource.getContents().get(0) instanceof RailwayContainer) {
			pack = (RailwayContainer) resource.getContents().get(0);
		}
	}

}
