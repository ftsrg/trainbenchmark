/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.fourstore.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.config.FourStoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.driver.FourStoreDriver;

import java.io.IOException;
import java.util.Collection;

public class FourStoreBenchmarkCase extends AbstractBenchmarkCase<Long> {

	protected FourStoreBenchmarkConfig fsbc;

	@Override
	public void init() throws IOException {
		this.fsbc = (FourStoreBenchmarkConfig) bc;

		final String queryPath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/queries/" + getName()
				+ ".sparql";
		driver = new FourStoreDriver(queryPath);
	}

	@Override
	public void read() throws IOException {
		driver.read(bc.getModelPathNameWithoutExtension() + ".ttl");
	}

	@Override
	public void destroy() throws IOException {
		driver.destroy();
	}

	@Override
	public Collection<Long> check() throws IOException {
		matches = driver.runQuery();
		return matches;
	}

}
