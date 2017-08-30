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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.driver;

import apoc.export.graphml.ExportGraphML;
import apoc.graph.Graphs;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.comparators.NodeComparator;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jHelper;
import hu.bme.mit.trainbenchmark.neo4j.apoc.ApocHelper;
import hu.bme.mit.trainbenchmark.neo4j.config.Neo4jGraphFormat;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.schema.Schema;
import org.neo4j.kernel.api.exceptions.KernelException;

import javax.xml.stream.XMLStreamException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Neo4jDriver extends Driver {

	protected Transaction tx;
	protected GraphDatabaseService graphDb;
	protected final Comparator<Node> nodeComparator = new NodeComparator();
	protected final File databaseDirectory;
	protected final Neo4jGraphFormat graphFormat;

	public Neo4jDriver(final String modelDir, final Neo4jGraphFormat graphFormat) throws IOException {
		super();
		this.graphFormat = graphFormat;
		this.databaseDirectory = new File(modelDir + "/neo4j-dbs/railway-database");
	}

	@Override
	public void initialize() throws Exception {
		super.initialize();

		// delete old database directory
		if (databaseDirectory.exists()) {
			FileUtils.deleteDirectory(databaseDirectory);
		}
	}

	@Override
	public void destroy() {
		if (graphDb != null) {
			graphDb.shutdown();
		}
	}

	@Override
	public void beginTransaction() {
		tx = graphDb.beginTx();
	}

	@Override
	public void finishTransaction() {
		tx.success();
		tx.close();
	}

	@Override
	public void read(final String modelPath)
		throws XMLStreamException, IOException, KernelException {
		switch (graphFormat) {
		case CSV:
			readCsv(modelPath);
			break;
		case GRAPHML:
			readGraphMl(modelPath);
			break;
		case CYPHER:
			readCypher(modelPath);
			break;
		default:
			throw new UnsupportedOperationException("Format " + graphFormat + " not supported");
		}
	}

	private void startDb() {
		graphDb = Neo4jHelper.startGraphDatabase(databaseDirectory);

		try (final Transaction tx = graphDb.beginTx()) {
			final Schema schema = graphDb.schema();
			schema.indexFor(Neo4jConstants.labelSegment).on(ModelConstants.ID).create();
			schema.indexFor(Neo4jConstants.labelSegment).on(ModelConstants.LENGTH).create();
			schema.indexFor(Neo4jConstants.labelSemaphore).on(ModelConstants.SIGNAL).create();
			schema.indexFor(Neo4jConstants.labelRoute).on(ModelConstants.ACTIVE).create();
			tx.success();
		}
		try (final Transaction tx = graphDb.beginTx()) {
			final Schema schema = graphDb.schema();
			schema.awaitIndexesOnline(5, TimeUnit.MINUTES);
		}
	}

	private void readCsv(String modelPath) throws IOException {
		final String neo4jHome =   "../neo4j-server";
		final String dbPath =      "../models/neo4j-dbs/railway-database";
		final File databaseDirectory = new File(dbPath);

		if (databaseDirectory.exists()) {
		  FileUtils.deleteDirectory(databaseDirectory);
		}

		final String rawImportCommand = "%NEO4J_HOME%/bin/neo4j-import --into %DB_PATH% " //
		    + "--nodes:Region %MODEL_PREFIX%-Region.csv " //
		    + "--nodes:Route %MODEL_PREFIX%-Route.csv " //
		    + "--nodes:Segment:TrackElement %MODEL_PREFIX%-Segment.csv " //
		    + "--nodes:Semaphore %MODEL_PREFIX%-Semaphore.csv " //
		    + "--nodes:Sensor %MODEL_PREFIX%-Sensor.csv " //
		    + "--nodes:Switch:TrackElement %MODEL_PREFIX%-Switch.csv " //
		    + "--nodes:SwitchPosition %MODEL_PREFIX%-SwitchPosition.csv " //
		    + "--relationships:connectsTo %MODEL_PREFIX%-connectsTo.csv " //
		    + "--relationships:entry %MODEL_PREFIX%-entry.csv " //
		    + "--relationships:exit %MODEL_PREFIX%-exit.csv "//
		    + "--relationships:follows %MODEL_PREFIX%-follows.csv "//
		    + "--relationships:monitoredBy %MODEL_PREFIX%-monitoredBy.csv "//
		    + "--relationships:requires %MODEL_PREFIX%-requires.csv "//
		    + "--relationships:target %MODEL_PREFIX%-target.csv";
		final String importCommand = rawImportCommand //
		    .replaceAll("%NEO4J_HOME%", neo4jHome) //
		    .replaceAll("%DB_PATH%", dbPath) //
		    .replaceAll("%MODEL_PREFIX%", modelPath);
		final CommandLine cmdLine = CommandLine.parse(importCommand);
		final DefaultExecutor executor = new DefaultExecutor();
		final int exitValue = executor.execute(cmdLine);
		if (exitValue != 0) {
		  throw new IOException("Neo4j import failed");
		}
		startDb();
	}

	private void readCypher(String modelPath) throws IOException {
		startDb();
		final File cypherFile = new File(modelPath);
		try(final Transaction tx = graphDb.beginTx()) {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(cypherFile));
			String line = null;
			while ((line = bufferedReader.readLine()) != null){
				graphDb.execute(line);
			}
			tx.success();
		}
	}

	private void readGraphMl(String modelPath) throws FileNotFoundException, XMLStreamException, KernelException {
		startDb();

		ApocHelper.registerProcedure(graphDb, ExportGraphML.class, Graphs.class);
		try (final Transaction tx = graphDb.beginTx()) {
			graphDb.execute(String.format( //
				"CALL apoc.import.graphml('%s', {batchSize: 10000, readLabels: true})", //
				modelPath //
			));
			tx.success();
		}
	}

	@Override
	public String getPostfix() {
		switch (graphFormat) {
		case CSV:
			return ""; // hack as we have multiple CSVs
		case GRAPHML:
			return ".graphml";
		case CYPHER:
			return ".cypher";
		default:
			throw new UnsupportedOperationException("Format " + graphFormat + " not supported");
		}
	}

	public Collection<Neo4jMatch> runQuery(final RailwayQuery query, final String queryDefinition) throws IOException {
		final Collection<Neo4jMatch> results = new ArrayList<>();

		final Result executionResult = graphDb.execute(queryDefinition);
		while (executionResult.hasNext()) {
			final Map<String, Object> row = executionResult.next();
			results.add(Neo4jMatch.createMatch(query, row));
		}

		return results;
	}

	public void runTransformation(final String transformationDefinition, final Map<String, Object> parameters)
			throws IOException {
		graphDb.execute(transformationDefinition, parameters);
	}

	// utility

	public GraphDatabaseService getGraphDb() {
		return graphDb;
	}

}
