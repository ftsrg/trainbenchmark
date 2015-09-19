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
package hu.bme.mit.trainbenchmark.benchmark.fourstore;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.layers.AnalyzedBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.layers.VersatileBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.analysis.FourstoreModelAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.checkers.FourStoreChecker;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.config.FourStoreBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.fourstore.driver.FourStoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf.analyzer.RDFQueryAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatchComparator;

import java.io.IOException;
import java.util.Comparator;

import org.openrdf.model.URI;

public class FourStoreBenchmarkCase extends BenchmarkCase<SesameMatch, URI, FourStoreDriver> implements
		VersatileBenchmarkCase, AnalyzedBenchmarkCase {

	protected FourStoreBenchmarkConfig fourStoreBenchmarkConfig;
	protected FourStoreDriver fourStoreDriver;
	protected FourstoreModelAnalyzer fourstoreModelAnalyzer;
	protected RDFQueryAnalyzer rdfQueryAnalyzer;
	protected FourStoreChecker fourStoreChecker;

	@Override
	protected void init() throws Exception {
		this.fourStoreBenchmarkConfig = (FourStoreBenchmarkConfig) benchmarkConfig;
		driver = fourStoreDriver = new FourStoreDriver();
		checker = fourStoreChecker = new FourStoreChecker(fourStoreDriver, fourStoreBenchmarkConfig);
		fourStoreDriver.init();

//		transformation = FourStoreTransformation.newInstance(fourStoreDriver,
//				benchmarkConfig.getQuery(), benchmarkConfig.getScenario());
	}

	@Override
	protected Comparator<?> getMatchComparator() {
		return new SesameMatchComparator();
	}

	@Override
	public void initAnalyzer() {
		modelAnalyzer = fourstoreModelAnalyzer = new FourstoreModelAnalyzer(fourStoreDriver);
		fourstoreModelAnalyzer.setBenchmarkConfig(fourStoreBenchmarkConfig);
		queryAnalyzer = rdfQueryAnalyzer = new RDFQueryAnalyzer(fourStoreDriver);
		rdfQueryAnalyzer.setQueryString(fourStoreChecker.getQueryDefinition());

	}

	@Override
	public void modify() throws IOException {
		if (benchmarkConfig.isVersatile()) {
			final String query = queryInitializer
					.resolveQuery(fourStoreBenchmarkConfig.getWorkspacePath()
							+ "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/",
							".sparql");
			fourStoreChecker.setQueryDefinition(query);
			rdfQueryAnalyzer.setQueryString(query);
		}

	}

}
