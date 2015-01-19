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

package hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.emf.FileBroker;

import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import Concept.ConceptPackage;
import Concept.IndividualContainer;

public abstract class JavaBenchmarkCase<T> implements BenchmarkCase {

	protected String fileName;
	protected BenchmarkResult bmr;
	protected BenchmarkConfig bc;
	protected IndividualContainer pack;
	protected List<T> invalids;

	@Override
	public String getTool() {
		return "Java";
	}

	@Override
	public void load() {
		bmr.startStopper();

		ConceptPackage.eINSTANCE.eClass();
		URI resourceURI = FileBroker.getEMFUri(bc.getBenchmarkArtifact());

		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(resourceURI, true);

		if (resource.getContents().size() > 0 && resource.getContents().get(0) instanceof IndividualContainer) {
			pack = (IndividualContainer) resource.getContents().get(0);
		}
		bmr.setReadTime();
	}

	@Override
	public void init(BenchmarkConfig bc) throws IOException {
		bmr = new BenchmarkResult(this.getTool(), this.getName());
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
	public BenchmarkResult getBenchmarkResult() {
		return bmr;
	}

	@Override
	public void destroy() {

	}

	@Override
	public int getResultSize() {
		return invalids.size();
	}

}
