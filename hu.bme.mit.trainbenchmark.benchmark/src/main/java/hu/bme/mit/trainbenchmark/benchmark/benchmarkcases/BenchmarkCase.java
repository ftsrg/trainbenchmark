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

package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;

import java.io.IOException;

public interface BenchmarkCase {

	public BenchmarkResult getBenchmarkResult();

	public String getTool();

	public String getName();

	public void init(BenchmarkConfig bc) throws IOException;

	public void measureMemory() throws IOException;

	public void destroy() throws IOException;

	public void load() throws IOException;

	public void check() throws IOException;

	public int getResultSize();

}
