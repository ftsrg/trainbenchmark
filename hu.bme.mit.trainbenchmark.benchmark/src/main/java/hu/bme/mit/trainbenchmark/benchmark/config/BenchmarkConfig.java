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

package hu.bme.mit.trainbenchmark.benchmark.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import hu.bme.mit.trainbenchmark.config.AbstractConfig;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public final class BenchmarkConfig extends AbstractConfig {

	protected final int runs;
	protected final int queryTransformationCount;
	protected final String toolName;
	protected final String modelPath;
	protected final Collection<RailwayOperation> railwayOperations;
	
	public BenchmarkConfig(final int runs, final int queryTransformatioCount, final String toolName, final String modelPath, final Collection<RailwayOperation> railwayOperations) {
		super();
		this.runs = runs;
		this.queryTransformationCount = queryTransformatioCount;
		this.toolName = toolName;
		this.modelPath = modelPath;
		this.railwayOperations = railwayOperations;
	}

	public void saveToFile(final String path) throws FileNotFoundException {
		final Kryo kryo = new Kryo();
		try (final Output output = new Output(new FileOutputStream(path))) {
			kryo.writeObject(output, this);
		}
	}

	public static BenchmarkConfig fromFile(final String path) throws FileNotFoundException {
		final Kryo kryo = new Kryo();
		try (final Input input = new Input(new FileInputStream(path))) {
			final BenchmarkConfig benchmarkConfig = kryo.readObject(input, BenchmarkConfig.class);
			return benchmarkConfig;
		}
	}

	public int getRuns() {
		return runs;
	}

	public int getQueryTransformationCount() {
		return queryTransformationCount;
	}
	
	public String getToolName() {
		return toolName;
	}

	public String getModelPath() {
		return modelPath;
	}

	public Collection<RailwayOperation> getRailwayOperations() {
		return railwayOperations;
	}

}
