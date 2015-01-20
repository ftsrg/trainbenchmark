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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases.batch;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatcher;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.emf.FileBroker;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

import Concept.ConceptPackage;
import Concept.IndividualContainer;

public class Batch implements BenchmarkCase {

	private BenchmarkResult bmr;
	private BenchmarkConfig bc;
	private IndividualContainer pack;

	private AdvancedIncQueryEngine engine;
	private ResourceSet resourceSet;
	private Resource resource;

	private IncQueryMatcher<? extends IPatternMatch> patternMatcher;
	private Collection<? extends IPatternMatch> invalids;

	@Override
	public void init(BenchmarkConfig bc) throws IOException {
		this.bc = bc;

		bmr = new BenchmarkResult(getTool(), getName());
		bmr.setBenchmarkConfig(bc);

		Util.runGC();
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}
	}

	@Override
	public void load() throws IOException {
		bmr.startStopper();

		ConceptPackage.eINSTANCE.eClass();
		URI resourceURI = FileBroker.getEMFUri(bc.getBenchmarkArtifact());
		resourceSet = new ResourceSetImpl();

		try {
			boolean managed = false;
			engine = managed ? AdvancedIncQueryEngine.from(IncQueryEngine.on(resourceSet)) : AdvancedIncQueryEngine
					.createUnmanagedEngine(resourceSet);

			// faster
			if ("posLength".equals(bc.getQuery())) {
				patternMatcher = PosLengthMatcher.on(engine);
			} else {
				// generic
				long mfrTime = System.nanoTime();
				patternMatcher = engine.getMatcher(bc.getQuery());
				System.err.println("Warning!! Generic MatcherFactoryRegistry.getMatcherFactory() was used that took "
						+ (System.nanoTime() - mfrTime) / 1000000 + " ms.");
			}
		} catch (IncQueryException e) {
			throw new IOException(e);
		}
		resource = resourceSet.getResource(resourceURI, true);
		if (resource.getContents().size() > 0 && resource.getContents().get(0) instanceof IndividualContainer) {
			// do not delete this line, as this triggers the loading of the instance model
			pack = (IndividualContainer) resource.getContents().get(0);
		}

		bmr.setReadTime();
	}

	@Override
	public void check() {
		bmr.startStopper();

		invalids = patternMatcher.getAllMatches();

		bmr.addInvalid(invalids.size());
		bmr.addCheckTime();
	}

	@Override
	public void measureMemory() {
		Util.runGC();
		bmr.addMemoryBytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
	}

	@Override
	public void destroy() {
		if (!engine.isManaged()) {
			engine.dispose();
		}
		engine = null;
		patternMatcher = null;
		resource = null;
		resourceSet = null;
		Util.runGC();
	}

	@Override
	public BenchmarkResult getBenchmarkResult() {
		return bmr;
	}

	@Override
	public String getTool() {
		return "EMF-IncQuery";
	}

	@Override
	public String getName() {
		return bc.getQuery();
	}

	@Override
	public int getResultSize() {
		return invalids.size();
	}

}
