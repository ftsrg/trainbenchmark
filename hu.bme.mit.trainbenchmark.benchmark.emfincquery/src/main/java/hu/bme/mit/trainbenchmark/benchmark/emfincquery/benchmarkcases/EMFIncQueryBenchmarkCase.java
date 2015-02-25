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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractTransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.IncQueryCommon;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.emf.FileBroker;
import hu.bme.mit.trainbenchmark.railway.RailwayContainer;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.emf.EMFScope;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.util.IncQueryLoggingUtil;

public abstract class EMFIncQueryBenchmarkCase<T extends RailwayElement, Match extends IPatternMatch> extends
		AbstractTransformationBenchmarkCase<T> {

	protected RailwayContainer container;

	protected AdvancedIncQueryEngine engine;
	protected ResourceSet resourceSet;
	protected Resource resource;
	protected IncQueryMatcher<Match> matcher;

	protected EMFIncQueryBenchmarkConfig getEMFIncQueryBenchmarkConfig() {
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
	}

	@Override
	public List<T> check() {
		try {
			results = new ArrayList<>(getResultSet());
		} catch (final IncQueryException e) {
			throw new RuntimeException(e);
		}
		return results;
	}

	@Override
	public void read() throws IOException {
		RailwayPackage.eINSTANCE.eClass();
		final URI resourceURI = FileBroker.getEMFUri(bc.getBenchmarkArtifact());
		resourceSet = new ResourceSetImpl();

		try {
			IncQueryCommon.setEIQOptions(getEMFIncQueryBenchmarkConfig());
			final EMFScope emfScope = new EMFScope(resourceSet);
			engine = AdvancedIncQueryEngine.createUnmanagedEngine(emfScope);
		} catch (final IncQueryException e) {
			throw new IOException(e);
		}

		resource = resourceSet.getResource(resourceURI, true);
		if (resource.getContents().size() > 0 && resource.getContents().get(0) instanceof RailwayContainer) {
			container = (RailwayContainer) resource.getContents().get(0);
		}
	}

	protected abstract Set<T> getResultSet() throws IncQueryException;

}
