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

package hu.bme.mit.trainbenchmark.benchmark.jena.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.match.JenaMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.match.JenaMatchComparator;
import hu.bme.mit.trainbenchmark.rdf.RDFBenchmarkConfig;

import java.io.IOException;
import java.util.Comparator;

import com.hp.hpl.jena.rdf.model.Resource;

public class JenaBenchmarkCase extends AbstractBenchmarkCase<JenaMatch, Resource> {

	protected JenaDriver jenaDriver;

	protected RDFBenchmarkConfig getRDFBenchmarkConfig() {
		return (RDFBenchmarkConfig) bc;
	}

	@Override
	protected void init() throws IOException {
		driver = jenaDriver = new JenaDriver();
		checker = new JenaChecker(jenaDriver, bc);
	}

	@Override
	protected Comparator<?> getComparator() {
		switch (bc.getScenario()) {
		case BATCH:
		case USER:
			return driver.getElementComparator();
		case REPAIR:
			return new JenaMatchComparator();
		default:
			throw new UnsupportedOperationException();
		}
	}

}
