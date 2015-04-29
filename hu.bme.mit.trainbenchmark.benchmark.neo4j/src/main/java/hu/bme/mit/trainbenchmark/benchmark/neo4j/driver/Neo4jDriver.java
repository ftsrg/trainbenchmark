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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.DEFINED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;
import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchSetMatch;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.io.FileUtils;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.shell.tools.imp.format.graphml.XmlGraphMLReader;
import org.neo4j.shell.tools.imp.util.MapNodeCache;
import org.neo4j.tooling.GlobalGraphOperations;

public class Neo4jDriver extends Driver<Node> {

	protected final RelationshipType definedByEdge = DynamicRelationshipType.withName(DEFINED_BY);
	protected final RelationshipType entryEdge = DynamicRelationshipType.withName(ENTRY);
	protected final RelationshipType sensorEdge = DynamicRelationshipType.withName(SENSOR_EDGE);

	protected final Label sensorLabel = DynamicLabel.label(SENSOR);

	protected Transaction tx;
	protected GraphDatabaseService graphDb;
	protected String dbPath;
	protected Comparator<Node> nodeComparator = new NodeComparator();

	public Neo4jDriver(final String dbPath) throws IOException {
		// delete old directory
		if (new File(dbPath).exists()) {
			FileUtils.deleteDirectory(new File(dbPath));
		}

		// start the database
		this.dbPath = dbPath;
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
	public void read(final String filePath) throws IOException {
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(dbPath);

		try (Transaction tx = graphDb.beginTx()) {
			final XmlGraphMLReader xmlGraphMLReader = new XmlGraphMLReader(graphDb);
			xmlGraphMLReader.nodeLabels(true);
			xmlGraphMLReader.parseXML(new BufferedReader(new FileReader(filePath + getExtension())), MapNodeCache.usingHashMap());
			tx.success();
		} catch (final XMLStreamException e) {
			throw new IOException(e);
		}
	}

	public List<Neo4jMatch> runQuery(final Query query, final String queryDefinition) throws IOException {
		final List<Neo4jMatch> results = new ArrayList<>();

		try (Transaction tx = graphDb.beginTx()) {
			final ExecutionEngine engine = new ExecutionEngine(graphDb);
			final ExecutionResult result = engine.execute(queryDefinition);
			for (final Map<String, Object> row : result) {
				results.add(createMatch(query, row));
			}
		}

		return results;
	}

	protected Neo4jMatch createMatch(final Query query, final Map<String, Object> row) {
		switch (query) {
		case POSLENGTH:
			return new Neo4jPosLengthMatch(row);
		case ROUTESENSOR:
			return new Neo4jRouteSensorMatch(row);
		case SEMAPHORENEIGHBOR:
			return new Neo4jSemaphoreNeighborMatch(row);
		case SWITCHSENSOR:
			return new Neo4jSwitchSensorMatch(row);
		case SWITCHSET:
			return new Neo4jSwitchSetMatch(row);
		default:
			throw new UnsupportedOperationException("Query not supported: " + query);
		}
	}

	@Override
	public void destroy() {
		graphDb.shutdown();
	}

	// read

	@Override
	public List<Node> collectVertices(final String type) {
		final ResourceIterable<Node> nodes = GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(DynamicLabel.label(type));

		final ResourceIterator<Node> iterator = nodes.iterator();
		@SuppressWarnings("unchecked")
		final List<Node> list = IteratorUtils.toList(iterator);
		return list;
	}

	// utility

	public GraphDatabaseService getGraphDb() {
		return graphDb;
	}

	@Override
	public Comparator<Node> getElementComparator() {
		return nodeComparator;
	}

	@Override
	public String getExtension() {
		return ".graphml";
	}

}
