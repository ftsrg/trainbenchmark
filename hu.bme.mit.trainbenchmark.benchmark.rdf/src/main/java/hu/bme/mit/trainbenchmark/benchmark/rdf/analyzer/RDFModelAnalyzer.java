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

package hu.bme.mit.trainbenchmark.benchmark.rdf.analyzer;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.ModelAnalyzer;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public abstract class RDFModelAnalyzer<D extends Driver<?>> extends ModelAnalyzer<D> {

	public RDFModelAnalyzer(D driver) {
		super(driver);
	}

	protected String getDegreesQuery;

	protected String getTriples;

	protected RDFBenchmarkConfig benchmarkConfig;

	protected void initQueries() {
		String degreesQueryPath = benchmarkConfig.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/util/GetDegrees.sparql";
		String triplesQueryPath = benchmarkConfig.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/util/GetTriples.sparql";
		try {
			getDegreesQuery = FileUtils.readFileToString(new File(degreesQueryPath));
			getTriples = FileUtils.readFileToString(new File(triplesQueryPath));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public RDFBenchmarkConfig getBenchmarkConfig() {
		return benchmarkConfig;
	}

	public void setBenchmarkConfig(RDFBenchmarkConfig benchmarkConfig) {
		this.benchmarkConfig = benchmarkConfig;
	}
}
