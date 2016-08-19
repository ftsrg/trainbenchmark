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
import org.eclipse.viatra.query.runtime.emf.EMFScope;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;

public class ViatraDriver extends EmfDriver {

	protected AdvancedViatraQueryEngine engine;

	public AdvancedViatraQueryEngine getEngine() {
		return engine;
	}
	
	public ViatraDriver() {
		super();
	}
	
	@Override
	public void initialize() throws Exception {
		super.initialize();

		ViatraQueryLoggingUtil.getDefaultLogger().setLevel(Level.OFF);
		final EMFScope emfScope = new EMFScope(resourceSet);
		engine = AdvancedViatraQueryEngine.from(ViatraQueryEngine.on(emfScope));
	}

	@Override
	public void read(final String modelPath) throws Exception {
		super.read(modelPath);
	}

}
