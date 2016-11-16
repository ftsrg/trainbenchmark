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
package hu.bme.mit.trainbenchmark.benchmark.viatra.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;

public class ViatraBenchmarkConfig extends BenchmarkConfig {

	protected ViatraBackend backend;

	protected ViatraBenchmarkConfig(final BenchmarkConfigBase configBase,
			final ViatraBackend backend) {
		super(configBase);
		this.backend = backend;
	}

	public ViatraBackend getBackend() {
		return backend;
	}

	@Override
	public String getToolName() {
		return String.format("VIATRA (%s)", getBackend().toString());
	}

	@Override
	public String getProjectName() {
		return "viatra";
	}

}
