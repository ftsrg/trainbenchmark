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

import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.ASSOCIATION;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.ASSOCIATIONS;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.SCHEDULE;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.SCHEDULES;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.STATION;
import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleModelConstants.TRAIN;
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
	protected String getDegreesTrainsToSchedulesQuery;
	protected String getDegreesTrainsToAssociationsQuery;
	protected String getNumberOfClassesQuery;
	protected String getStationsOfSchedulesQuery;

	private RepositoryConnection connection;

	public SesameModelDescription(SesameDriver driver) {
		super(driver);
	}

	@Override
	protected void calculateMetrics() {
		connection = driver.getConnection();
		initQueries();
		try {
			calculateDegrees(STATION);
			calculateDegrees(TRAIN);
			calculateDegrees(SCHEDULE);
			calculateDegrees(ASSOCIATION);
			calculateDegrees(ASSOCIATIONS);
			calculateDegrees(SCHEDULES);

			calculateElements();

			calculateRepetitiveSchedules();
		} catch (RepositoryException | MalformedQueryException | QueryEvaluationException e) {
			throw new RuntimeException(e);
		}

	}

	private void calculateDegrees(final String type) throws QueryEvaluationException,
			RepositoryException, MalformedQueryException {
		TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, getQuery(type));
		TupleQueryResult results = query.evaluate();

		BindingSet set;
		int degree;
		while (results.hasNext()) {
			set = results.next();
			degree = Integer.parseInt(set.getValue("degree").stringValue());
			addDegree(type, degree);
		}

	}

	private void calculateElements() throws QueryEvaluationException, RepositoryException,
			MalformedQueryException {
		TupleQuery query = connection
				.prepareTupleQuery(QueryLanguage.SPARQL, getNumberOfClassesQuery);
		TupleQueryResult results = query.evaluate();

		BindingSet set;
		String clazz;
		while (results.hasNext()) {
			set = results.next();
			clazz = set.getValue("class").stringValue();
			// remove URI
			clazz = clazz.substring(clazz.indexOf("#") + 1);
			numberOfElements.put(clazz, Integer.parseInt(set.getValue("count").stringValue()));
		}
	}

	private void calculateRepetitiveSchedules() throws QueryEvaluationException, RepositoryException,
			MalformedQueryException {
		TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL,
				getStationsOfSchedulesQuery);
		TupleQueryResult results = query.evaluate();

		BindingSet set;
		String prevSchedule = null;
		String schedule;
		while (results.hasNext()) {
			set = results.next();
			schedule = set.getValue("schedule").stringValue();
			if (prevSchedule == null) {
				prevSchedule = schedule;
			}
			addStationOfSchedule(schedule, set.getValue("station").stringValue());
			if (!schedule.equals(prevSchedule)) {
				checkRepetitiveSchedules(prevSchedule);
				prevSchedule = schedule;
			}
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
		case ASSOCIATIONS:
			return getDegreesTrainsToAssociationsQuery;
		case SCHEDULES:
			return getDegreesTrainsToSchedulesQuery;
		default:
			throw new IllegalArgumentException("Unsupported query type!");
		}
	}

	private void initQueries() {
		String queryPath = benchmarkConfig.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.rdf/src/main/resources/queries/util/";
		String getDegreesStationsPath = queryPath + "GetDegreesOfStations.sparql";
		String getDegreesTrainsPath = queryPath + "GetDegreesOfTrains.sparql";
		String getDegreesSchedulesPath = queryPath + "GetDegreesOfSchedules.sparql";
		String getDegreesAssociationsPath = queryPath + "GetDegreesOfAssociations.sparql";
		String getDegreesTrainsToSchedulesPath = queryPath + "GetDegreesOfTrainsToSchedules.sparql";
		String getDegreesTrainsToAssociationsPath = queryPath
				+ "GetDegreesOfTrainsToAssociations.sparql";
		String getNumberOfClassesPath = queryPath + "GetNumberOfClasses.sparql";
		String getStationsOfSchedulesPath = queryPath + "GetStationsOfSchedules.sparql";

		try {
			getDegreesStationsQuery = FileUtils
					.readFileToString(new File(getDegreesStationsPath));
			getDegreesTrainsQuery = FileUtils.readFileToString(new File(getDegreesTrainsPath));
			getDegreesSchedulesQuery = FileUtils.readFileToString(new File(
					getDegreesSchedulesPath));
			getDegreesAssociationsQuery = FileUtils.readFileToString(new File(
					getDegreesAssociationsPath));
			getDegreesTrainsToSchedulesQuery = FileUtils.readFileToString(new File(
					getDegreesTrainsToSchedulesPath));
			getDegreesTrainsToAssociationsQuery = FileUtils.readFileToString(new File(
					getDegreesTrainsToAssociationsPath));
			getNumberOfClassesQuery = FileUtils
					.readFileToString(new File(getNumberOfClassesPath));
			getStationsOfSchedulesQuery = FileUtils.readFileToString(new File(
					getStationsOfSchedulesPath));

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
