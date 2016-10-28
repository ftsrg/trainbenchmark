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
package hu.bme.mit.trainbenchmark.benchmark.viatra.driver;

import org.apache.log4j.Level;
import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngineOptions;
import org.eclipse.viatra.query.runtime.emf.EMFScope;
import org.eclipse.viatra.query.runtime.localsearch.matcher.integration.LocalSearchHints;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.viatra.config.ViatraBackend;

public class ViatraDriver extends EmfDriver {

	protected ViatraBackend backend;
	protected AdvancedViatraQueryEngine engine;

	public ViatraDriver(final ViatraBackend backend) {
		super();
		this.backend = backend;
	}

	@Override
	public void initialize() throws Exception {
		super.initialize();

		ViatraQueryLoggingUtil.getDefaultLogger().setLevel(Level.OFF);
		final EMFScope scope = new EMFScope(resourceSet);

		final ViatraQueryEngine vqe;

		switch (backend) {
		case INCREMENTAL:
			vqe = ViatraQueryEngine.on(scope);
			break;
		case LOCAL_SEARCH:
			// see https://wiki.eclipse.org/VIATRA/Query/UserDocumentation/API/LocalSearch
			final QueryEvaluationHint localSearchHint = LocalSearchHints.getDefault().build();
			final ViatraQueryEngineOptions options = ViatraQueryEngineOptions.defineOptions(). //
					withDefaultHint(localSearchHint).build();
			vqe = ViatraQueryEngine.on(scope, options);
			break;
		default:
			throw new UnsupportedOperationException("Backend: " + backend + " not supported");
		}
		engine = AdvancedViatraQueryEngine.from(vqe);
	}

	@Override
	public void read(final String modelPath) throws Exception {
		super.read(modelPath);
	}

	public AdvancedViatraQueryEngine getEngine() {
		return engine;
	}

}
