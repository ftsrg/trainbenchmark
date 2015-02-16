/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractTransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.NotImplementedException;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.shell.tools.imp.format.graphml.XmlGraphMLReader;
import org.neo4j.shell.tools.imp.util.MapNodeCache;
import org.neo4j.tooling.GlobalGraphOperations;

public class Neo4jBenchmarkCase extends AbstractTransformationBenchmarkCase<Node> {

	protected Neo4jBenchmarkConfig nbc;

	protected GraphDatabaseService graphDb;
	protected String graphDbPath;
	protected String query;

	@Override
	public String getTool() {
		return "Neo4j" + (nbc == null || !nbc.isRamDisk() ? "" : "-ramdisk");
	}

	@Override
	public void init(final BenchmarkConfig bc) throws IOException {
		this.bc = bc;
		this.nbc = (Neo4jBenchmarkConfig) bc;

		bmr = new BenchmarkResult(getTool(), getName());
		bmr.setBenchmarkConfig(bc);

		graphDbPath = bc.getWorkspacePath() + "/models/neo4j-dbs/railway-database";
		query = FileUtils.readFileToString(new File(bc.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.benchmark.neo4j/src/main/resources/queries/" + getName() + ".cypher"));

		Util.runGC();
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}
	}

	@Override
	public void load() throws FileNotFoundException, IOException {
		bmr.startStopper();

		// start with a clean slate: delete old directory
		if (new File(graphDbPath).exists()) {
			FileUtils.deleteDirectory(new File(graphDbPath));
		}

		// load the database (and shut down)
		loadGraphMl(bc.getBenchmarkArtifact(), graphDbPath);

		// start the database
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(graphDbPath);
		driver = new Neo4jDriver(graphDb);

		bmr.setReadTime();
	}

	@Override
	public void check() {
		bmr.startStopper();

		if (nbc.isJavaApi()) {
			invalids = checkJava();
		} else {
			invalids = runCypherQuery(graphDb, query);
		}

		bmr.addInvalid(invalids.size());
		bmr.addCheckTime();
	}

	protected List<Node> checkJava() {
		throw new NotImplementedException("");
	}

	@Override
	public void measureMemory() {
		Util.runGC();
		bmr.addMemoryBytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
	}

	@Override
	public void destroy() {
		graphDb.shutdown();
	}

	@Override
	public BenchmarkResult getBenchmarkResult() {
		return bmr;
	}

	public void loadGraphMl(final String graphMlFile, final String dbPath) throws FileNotFoundException, IOException {
		final GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(dbPath);

		try (Transaction tx = graphDb.beginTx()) {
			final XmlGraphMLReader xmlGraphMLReader = new XmlGraphMLReader(graphDb);
			xmlGraphMLReader.nodeLabels(true);
			xmlGraphMLReader.parseXML(new BufferedReader(new FileReader(graphMlFile)), MapNodeCache.usingHashMap());
			tx.success();
		} catch (final XMLStreamException e) {
			throw new IOException(e);
		}

		graphDb.shutdown();
	}

	public List<Node> getNodesByTypes(final String typeName) {
		final ResourceIterable<Node> nodes = GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label(typeName));

		final ResourceIterator<Node> iterator = nodes.iterator();
		final List<Node> list = IteratorUtils.toList(iterator);

		return list;
	}

	public List<Node> runCypherQuery(final GraphDatabaseService graphDb, final String query) {
		final List<Node> invalids = new ArrayList<>();

		try (Transaction tx = graphDb.beginTx()) {
			final ExecutionEngine engine = new ExecutionEngine(graphDb);
			final ExecutionResult result = engine.execute(query);

			for (final Map<String, Object> row : result) {
				final Node x = (Node) row.get(result.columns().get(0));
				invalids.add(x);
			}
		}

		return invalids;
	}

	@Override
	public int getResultSize() {
		return invalids.size();
	}

}
