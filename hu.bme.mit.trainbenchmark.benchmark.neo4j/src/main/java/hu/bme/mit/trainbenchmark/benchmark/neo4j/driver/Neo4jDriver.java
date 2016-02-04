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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.GATHERS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.io.FileUtils;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.shell.tools.imp.format.graphml.XmlGraphMLReader;
import org.neo4j.shell.tools.imp.util.MapNodeCache;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class Neo4jDriver extends Driver<Node> {

	protected final RelationshipType definedByEdge = DynamicRelationshipType.withName(GATHERS);
	protected final RelationshipType entryEdge = DynamicRelationshipType.withName(ENTRY);
	protected final RelationshipType sensorEdge = DynamicRelationshipType.withName(MONITORED_BY);

	protected final Label sensorLabel = DynamicLabel.label(SENSOR);

	protected Transaction tx;
	protected GraphDatabaseService graphDb;
	protected final Comparator<Node> nodeComparator = new NodeComparator();
	protected final String dbPath;

	public Neo4jDriver(final String workspacePath) throws IOException {
		super();
		this.dbPath = workspacePath + "/models/neo4j-dbs/railway-database";
	}

	@Override
	public void initialize() throws Exception {
		super.initialize();
		
		// delete old database directory
		if (new File(dbPath).exists()) {
			FileUtils.deleteDirectory(new File(dbPath));
		}
	}
	
	@Override
	public void destroy() {
		graphDb.shutdown();
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
	public void read(final String filePath) throws FileNotFoundException, XMLStreamException {
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(dbPath);

		try (Transaction tx = graphDb.beginTx()) {
			final XmlGraphMLReader xmlGraphMLReader = new XmlGraphMLReader(graphDb);
			xmlGraphMLReader.nodeLabels(true);
			xmlGraphMLReader.parseXML(new BufferedReader(new FileReader(filePath + getPostfix())),
					MapNodeCache.usingHashMap());
			tx.success();
		}
	}

	public Collection<Neo4jMatch> runQuery(final RailwayQuery query, final String queryDefinition) throws IOException {
		final Collection<Neo4jMatch> results = new ArrayList<>();

		try (Transaction tx = graphDb.beginTx()) {
			final Result executionResult = graphDb.execute(queryDefinition);
			while (executionResult.hasNext()) {
				final Map<String, Object> row = executionResult.next();
				results.add(Neo4jMatch.createMatch(query, row));
			}
		}

		return results;
	}

	// read

	@Override
	public Collection<Node> collectVertices(final String type) {
		final ResourceIterator<Node> iterator = graphDb.findNodes(DynamicLabel.label(type));

		@SuppressWarnings("unchecked")
		final Collection<Node> vertices = IteratorUtils.toList(iterator);
		return vertices;
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
	public String getPostfix() {
		return ".graphml";
	}

}
