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

package hu.bme.mit.trainbenchmark.benchmark.rdf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigWrapper;

public abstract class RdfBenchmarkConfigWrapper extends BenchmarkConfigWrapper {

	protected boolean inferencing;

	protected RdfBenchmarkConfigWrapper() {
	}

	public RdfBenchmarkConfigWrapper(final BenchmarkConfigCore benchmarkConfig, final boolean inferencing) {
		super(benchmarkConfig);
		this.inferencing = inferencing;
	}

	public boolean isInferencing() {
		return inferencing;
	}

	protected String getToolNamePostfix() {
		return isInferencing() ? " (Inferencing)" : " (No Inferencing)";
	}

	public static RdfBenchmarkConfigWrapper fromFile(final String path) throws FileNotFoundException {
		final Kryo kryo = new Kryo();
		try (final Input input = new Input(new FileInputStream(path))) {
			final RdfBenchmarkConfigWrapper benchmarkConfig = kryo.readObject(input, RdfBenchmarkConfigWrapper.class);
			return benchmarkConfig;
		}
	}

}
