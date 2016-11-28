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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.io.FileUtils;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.schema.Schema;
import org.neo4j.kernel.internal.GraphDatabaseAPI;
import org.neo4j.shell.InterruptSignalHandler;
import org.neo4j.shell.ShellException;
import org.neo4j.shell.SilentLocalOutput;
import org.neo4j.shell.impl.SameJvmClient;
import org.neo4j.shell.kernel.GraphDatabaseShellServer;
import org.neo4j.shell.tools.imp.format.graphml.XmlGraphMLReader;
import org.neo4j.shell.tools.imp.util.MapNodeCache;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.comparators.NodeComparator;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;
import hu.bme.mit.trainbenchmark.neo4j.config.Neo4jGraphFormat;

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
			throws FileNotFoundException, XMLStreamException, RemoteException, ShellException {
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(databaseDirectory);

		switch (graphFormat) {
		case BINARY:
			readBinary(modelPath);
			break;
		case CSV:
			readCsv(modelPath);
			break;
		case GRAPHML:
			readGraphMl(modelPath);
			break;
		default:
			throw new UnsupportedOperationException("Format " + graphFormat + " not supported");
		}
	}

	private void readCsv(String modelPath) {
		throw new UnsupportedOperationException("CSV not supported");
	}

	private void readBinary(String modelPath) throws RemoteException, ShellException {
		final SameJvmClient client = new SameJvmClient(Collections.<String, Serializable>emptyMap(),
				new GraphDatabaseShellServer((GraphDatabaseAPI) graphDb), InterruptSignalHandler.getHandler());

		final String importCommand = String.format("import-binary -i %s", modelPath);
		System.out.println(importCommand);
		client.evaluate(importCommand, new SilentLocalOutput());
	}

	private void readGraphMl(String modelPath) throws FileNotFoundException, XMLStreamException {
		try (Transaction tx = graphDb.beginTx()) {
			final Schema schema = graphDb.schema();
			schema.indexFor(Neo4jConstants.labelSegment).on(ModelConstants.LENGTH);
			schema.indexFor(Neo4jConstants.labelSemaphore).on(ModelConstants.SIGNAL);
			schema.indexFor(Neo4jConstants.labelRoute).on(ModelConstants.ACTIVE);
			schema.awaitIndexesOnline(5, TimeUnit.MINUTES);
			tx.success();
		}

		try (Transaction tx = graphDb.beginTx()) {
			final XmlGraphMLReader xmlGraphMLReader = new XmlGraphMLReader(graphDb);
			xmlGraphMLReader.nodeLabels(true);
			xmlGraphMLReader.parseXML(new BufferedReader(new FileReader(modelPath)), MapNodeCache.usingHashMap());
			tx.success();
		}
	}

	@Override
	public String getPostfix() {
		switch (graphFormat) {
		case BINARY:
			return ".bin";
		case CSV:
			return ".csv";
		case GRAPHML:
			return ".graphml";
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
