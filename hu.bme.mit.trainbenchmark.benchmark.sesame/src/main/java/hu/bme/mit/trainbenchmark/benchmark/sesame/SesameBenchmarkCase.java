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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.layers.AnalyzedBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.layers.DescribedBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.layers.VersatileBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sesame.analyzer.SesameModelAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.sesame.analyzer.SesameModelDescription;
import hu.bme.mit.trainbenchmark.benchmark.sesame.analyzer.SesameQueryAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.sesame.checkers.SesameChecker;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.SesameTransformation;

import java.io.IOException;
import java.util.Comparator;

import org.openrdf.model.URI;

public class SesameBenchmarkCase extends BenchmarkCase<SesameMatch, URI, SesameDriver> implements
		VersatileBenchmarkCase, AnalyzedBenchmarkCase, DescribedBenchmarkCase {

	protected SesameDriver sesameDriver;
	protected RDFBenchmarkConfig rbc;
	protected SesameModelAnalyzer sesameModelAnalyzer;
	protected SesameQueryAnalyzer sesameQueryAnalyzer;
	protected SesameModelDescription sesameDescription;
	protected SesameChecker sesameChecker;

	@Override
	protected void init() throws IOException {
		this.rbc = (RDFBenchmarkConfig) benchmarkConfig;

		driver = sesameDriver = new SesameDriver();
		sesameChecker = (SesameChecker) (checker = new SesameChecker(sesameDriver, benchmarkConfig));

		transformation = SesameTransformation.newInstance(sesameDriver, benchmarkConfig.getQuery(),
				benchmarkConfig.getScenario());
	}

	@Override
	public void initAnalyzer() {
		modelAnalyzer = sesameModelAnalyzer = new SesameModelAnalyzer(sesameDriver);
		sesameModelAnalyzer.setBenchmarkConfig(rbc);
		queryAnalyzer = sesameQueryAnalyzer = new SesameQueryAnalyzer(sesameDriver);
		sesameQueryAnalyzer.setQueryString(sesameChecker.getQueryDefinition());
	}

	@Override
	protected Comparator<?> getMatchComparator() {
		return new SesameMatchComparator();
	}

	@Override
	public void initDescription() {
		modelAnalyzer = sesameDescription = new SesameModelDescription(sesameDriver);
		sesameDescription.setBenchmarkConfig(rbc);
	}

	@Override
	public void modify() throws IOException {
		final String query = queryInitializer.resolveQuery(rbc.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/",
				".sparql");
		sesameChecker.setQueryDefinition(query);
		sesameQueryAnalyzer.setQueryString(query);
	}
}
