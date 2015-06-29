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

package hu.bme.mit.trainbenchmark.benchmark.virtuoso.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.virtuoso.driver.VirtuosoDriver;

import java.io.IOException;
import java.util.Collection;

import org.openrdf.model.URI;

public class VirtuosoBenchmarkCase extends AbstractBenchmarkCase<URI>{

	protected RDFBenchmarkConfig rdfConfig;
		
	@Override
	public void init() throws IOException {
		this.rdfConfig = (RDFBenchmarkConfig) bc;
		final String queryPath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/queries/" + getName() + ".sparql";
		driver = new VirtuosoDriver(queryPath);
	}

	@Override
	protected void read() throws IOException {
		driver.read(bc.getModelPathNameWithoutExtension() + ".ttl");
	}

	@Override
	protected Collection<URI> check() throws IOException {
		matches = driver.runQuery();
		return matches;
	}

	@Override
	protected void destroy() throws IOException {
		driver.destroy();
	}
}
