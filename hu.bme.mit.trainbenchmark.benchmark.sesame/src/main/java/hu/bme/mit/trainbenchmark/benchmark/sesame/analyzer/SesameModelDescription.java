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

package hu.bme.mit.trainbenchmark.benchmark.sesame.analyzer;

import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.STATION;
import hu.bme.mit.trainbenchmark.benchmark.analyzer.ModelDescription;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

public class SesameModelDescription extends ModelDescription<SesameDriver> {

	protected RDFBenchmarkConfig benchmarkConfig;

	protected String getDegreesStationsQuery;

	public SesameModelDescription(SesameDriver driver) {
		super(driver);
	}

	@Override
	protected void calculateMetrics() {
		RepositoryConnection connection = driver.getConnection();
		initQueries();
		try {
			TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL,
					getDegreesStationsQuery);
			TupleQueryResult results = query.evaluate();

			BindingSet set;
			int degree;
			while (results.hasNext()) {
				set = results.next();
				degree = Integer.parseInt(set.getValue("degree").stringValue());
				addDegree(STATION, degree);
			}
		} catch (RepositoryException | MalformedQueryException | QueryEvaluationException e) {
			throw new RuntimeException(e);
		}

	}

	private void initQueries() {
		String getDegreesStationsPath = benchmarkConfig.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/util/GetDegreesOfStations.sparql";
		try {
			getDegreesStationsQuery = FileUtils
					.readFileToString(new File(getDegreesStationsPath));
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
