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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.IQDeltaMonitor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.config.EMFIncQueryBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import java.io.IOException;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;

import Concept.IndividualContainer;

public abstract class EMFIncQueryBenchmarkCase<T> implements BenchmarkCase {

	protected BenchmarkResult bmr;
	protected BenchmarkConfig bc;
	protected IndividualContainer pack;

	protected AdvancedIncQueryEngine engine;
	protected ResourceSet resourceSet;
	protected Resource resource;

	protected List<T> invalids;
	protected IQDeltaMonitor<T> iqDeltaMonitor;	
	
	@Override
	public String getTool() {
		return "EMF-IncQuery";
	}

	protected EMFIncQueryBenchmarkConfig getEIQBC() {
		return (EMFIncQueryBenchmarkConfig) bc;
	}

	@Override
	public void init(BenchmarkConfig bc) throws IOException {
		bmr = new BenchmarkResult(getTool(), getName());
		bmr.setBenchmarkConfig(bc);

		this.bc = bc;
		Util.runGC();
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}
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
		resource = null;
		resourceSet = null;
		Util.runGC();
	}

	@Override
	public BenchmarkResult getBenchmarkResult() {
		return bmr;
	}
	
	@Override
	public int getResultSize() {
		return invalids.size();
	}

}
