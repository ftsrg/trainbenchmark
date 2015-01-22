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

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.IQDeltaMonitor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.IncQueryCommon;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensorMatcher;
import hu.bme.mit.trainbenchmark.emf.FileBroker;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.exception.IncQueryException;

import Concept.ConceptPackage;
import Concept.IndividualContainer;
import Concept.Switch;

public class SwitchSensor extends EMFIncQueryBenchmarkCase<Switch> {

	@Override
	public String getName() {
		return "SwitchSensor";
	}

	@Override
	public void load() throws IOException {
		bmr.startStopper();

		ConceptPackage.eINSTANCE.eClass();
		URI resourceURI = FileBroker.getEMFUri(bc.getBenchmarkArtifact());
		resourceSet = new ResourceSetImpl();

		try {
			IncQueryCommon.setEIQOptions(getEIQBC());
			engine = AdvancedIncQueryEngine.createUnmanagedEngine(resourceSet);
			SwitchSensorMatcher patternMatcher = SwitchSensorMatcher.on(engine);
			iqDeltaMonitor = new IQDeltaMonitor<Switch>(patternMatcher, "Individual");
		} catch (IncQueryException e) {
			throw new IOException(e);
		}
		resource = resourceSet.getResource(resourceURI, true);
		if (resource.getContents().size() > 0 && resource.getContents().get(0) instanceof IndividualContainer) {
			pack = (IndividualContainer) resource.getContents().get(0);
		}

		bmr.setReadTime();
	}

	@Override
	public void check() {
		bmr.startStopper();
		invalids = new ArrayList<Switch>(iqDeltaMonitor.getMatching());
		bmr.addInvalid(invalids.size());
		bmr.addCheckTime();
	}

}