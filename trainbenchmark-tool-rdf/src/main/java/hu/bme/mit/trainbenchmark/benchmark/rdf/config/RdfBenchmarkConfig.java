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

package hu.bme.mit.trainbenchmark.benchmark.rdf.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;

public abstract class RdfBenchmarkConfig extends BenchmarkConfig {

	protected boolean inferencing;

	protected RdfBenchmarkConfig(final BenchmarkConfigBase configBase, final boolean inferencing) {
		super(configBase);
		this.inferencing = inferencing;
	}

	public boolean isInferencing() {
		return inferencing;
	}

	protected String getToolNamePostfix() {
		return isInferencing() ? " (Inferencing)" : " (No Inferencing)";
	}

}
