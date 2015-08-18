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

import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.ASSOCIATION;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.SCHEDULE;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.STATION;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.TRAIN;
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
	protected String getDegreesTrainsQuery;
	protected String getDegreesSchedulesQuery;
	protected String getDegreesAssociationsQuery;

	private RepositoryConnection connection;

	public SesameModelDescription(SesameDriver driver) {
		super(driver);
	}

	@Override
	protected void calculateMetrics() {
		connection = driver.getConnection();
		initQueries();
		calculateDegrees(STATION);
		calculateDegrees(TRAIN);
		calculateDegrees(SCHEDULE);
		calculateDegrees(ASSOCIATION);

	}

	private void calculateDegrees(final String type) {
		try {
			TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, getQuery(type));
			TupleQueryResult results = query.evaluate();

			BindingSet set;
			int degree;
			while (results.hasNext()) {
				set = results.next();
				degree = Integer.parseInt(set.getValue("degree").stringValue());
				addDegree(type, degree);
			}
		} catch (RepositoryException | MalformedQueryException | QueryEvaluationException e) {
			throw new RuntimeException(e);
		}
	}

	private String getQuery(final String type) {
		switch (type) {
		case STATION:
			return getDegreesStationsQuery;
		case TRAIN:
			return getDegreesTrainsQuery;
		case SCHEDULE:
			return getDegreesSchedulesQuery;
		case ASSOCIATION:
			return getDegreesAssociationsQuery;
		default:
			throw new IllegalArgumentException("Unsupported query type!");
		}
	}

	private void initQueries() {
		String getDegreesStationsPath = benchmarkConfig.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/util/GetDegreesOfStations.sparql";
		String getDegreesTrainsPath = benchmarkConfig.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/util/GetDegreesOfTrains.sparql";
		String getDegreesSchedulesPath = benchmarkConfig.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/util/GetDegreesOfSchedules.sparql";
		String getDegreesAssociationsPath = benchmarkConfig.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/util/GetDegreesOfAssociations.sparql";
		try {
			getDegreesStationsQuery = FileUtils
					.readFileToString(new File(getDegreesStationsPath));
			getDegreesTrainsQuery = FileUtils.readFileToString(new File(getDegreesTrainsPath));
			getDegreesSchedulesQuery = FileUtils.readFileToString(new File(
					getDegreesSchedulesPath));
			getDegreesAssociationsQuery = FileUtils.readFileToString(new File(
					getDegreesAssociationsPath));
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
