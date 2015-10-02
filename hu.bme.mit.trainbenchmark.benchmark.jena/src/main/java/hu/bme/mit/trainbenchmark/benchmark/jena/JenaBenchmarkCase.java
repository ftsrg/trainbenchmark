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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCaseRunner;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.jena.benchmarkcases.JenaChecker;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.match.JenaMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.match.JenaMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.JenaTransformation;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;

public class JenaBenchmarkCase extends AbstractBenchmarkCaseRunner<JenaMatch, Resource, JenaDriver, RDFBenchmarkConfig, JenaChecker> {

	@Override
	protected void initialize() throws Exception {
		driver = new JenaDriver(bc);
		checker = new JenaChecker(driver, bc);
	}

	@Override
	protected Transformation<?> getTransformation() {
		return JenaTransformation.newInstance(driver, bc.getQuery(), bc.getScenario());
	}

	@Override
	protected Comparator<?> getMatchComparator() {
		return new JenaMatchComparator();
	}

}
