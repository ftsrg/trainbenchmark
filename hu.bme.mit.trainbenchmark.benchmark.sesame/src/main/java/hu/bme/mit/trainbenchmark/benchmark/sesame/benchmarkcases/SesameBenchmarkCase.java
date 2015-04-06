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

package hu.bme.mit.trainbenchmark.benchmark.sesame.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.rdf.RDFBenchmarkConfig;

import java.io.IOException;
import java.util.Collection;

import org.openrdf.model.URI;
import org.openrdf.query.BindingSet;

public class SesameBenchmarkCase extends AbstractBenchmarkCase<BindingSet, URI> {

	protected RDFBenchmarkConfig rbc;

	@Override
	protected void init() throws IOException {
		this.rbc = (RDFBenchmarkConfig) bc;

		final String queryPath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/queries/" + getName()
				+ ".sparql";
		driver = new SesameDriver(queryPath);
	}

	@Override
	public void read() throws IOException {
		driver.read(bc.getModelPathNameWithoutExtension() + ".ttl");
	}

	@Override
	public Collection<BindingSet> check() throws IOException {
		matches = driver.runQuery();
		return matches;
	}

}
