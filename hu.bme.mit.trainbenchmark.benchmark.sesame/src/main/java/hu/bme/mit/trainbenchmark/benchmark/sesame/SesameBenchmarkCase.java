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

package hu.bme.mit.trainbenchmark.benchmark.sesame;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sesame.analyzer.SesameModelAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.sesame.checkers.SesameChecker;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.SesameTransformation;

import java.io.IOException;
import java.util.Comparator;

import org.openrdf.model.URI;

public class SesameBenchmarkCase extends AbstractBenchmarkCase<SesameMatch, URI, SesameDriver> {

	protected SesameDriver sesameDriver;
	protected RDFBenchmarkConfig rbc;
	protected SesameModelAnalyzer sesameAnalyzer;

	@Override
	protected void init() throws IOException {
		this.rbc = (RDFBenchmarkConfig) bc;

		driver = sesameDriver = new SesameDriver();
		checker = new SesameChecker(sesameDriver, bc);

		transformation = SesameTransformation.newInstance(sesameDriver, bc.getQuery(),
				bc.getScenario());
		initAnalyzer();
	}

	@Override
	protected void initAnalyzer() {
		analyzer = sesameAnalyzer = new SesameModelAnalyzer(sesameDriver);
		sesameAnalyzer.setBenchmarkConfig(rbc);
	}

	@Override
	protected Comparator<?> getMatchComparator() {
		return new SesameMatchComparator();
	}

	@Override
	protected void initDescription() {
		// TODO Auto-generated method stub

	}

}
