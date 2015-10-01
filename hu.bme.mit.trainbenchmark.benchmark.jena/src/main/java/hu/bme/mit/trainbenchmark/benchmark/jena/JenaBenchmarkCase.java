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

package hu.bme.mit.trainbenchmark.benchmark.jena;

import java.util.Comparator;

import com.hp.hpl.jena.rdf.model.Resource;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.jena.benchmarkcases.JenaChecker;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.match.JenaMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.match.JenaMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.JenaTransformation;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;

public class JenaBenchmarkCase extends AbstractBenchmarkCase<JenaMatch, Resource> {

	protected JenaDriver jenaDriver;

	@Override
	protected void initialize() throws Exception {
		super.initialize();

		final RDFBenchmarkConfig rdfbc = (RDFBenchmarkConfig) bc;
		driver = jenaDriver = new JenaDriver(rdfbc);
		checker = new JenaChecker(jenaDriver, rdfbc);

		if (bc.getScenario().hasTranformation()) {
			transformation = JenaTransformation.newInstance(jenaDriver, bc.getQuery(), bc.getScenario());
		}
	}

	@Override
	protected Comparator<?> getMatchComparator() {
		return new JenaMatchComparator();
	}

}
