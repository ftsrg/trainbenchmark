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
package hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.test;

import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriverTest;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.io.FileUtils;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.shell.tools.imp.format.graphml.XmlGraphMLReader;
import org.neo4j.shell.tools.imp.util.MapNodeCache;

public class Neo4jDriverTest extends DatabaseDriverTest {

	@Override
	public void init() throws IOException {
		final String modelPath = "../models/railway-test-1.graphml";
		final String dbPath = "../models/neo4j-dbs";

		if (new File(dbPath).exists()) {
			FileUtils.deleteDirectory(new File(dbPath));
		}

		final GraphDatabaseService gds1 = new GraphDatabaseFactory().newEmbeddedDatabase(dbPath);

		try (Transaction tx = gds1.beginTx()) {
			final XmlGraphMLReader xmlGraphMLReader = new XmlGraphMLReader(gds1);
			xmlGraphMLReader.nodeLabels(true);
			xmlGraphMLReader.parseXML(new BufferedReader(new FileReader(modelPath)), MapNodeCache.usingHashMap());
			tx.success();
		} catch (final XMLStreamException e) {
			throw new IOException(e);
		}

		gds1.shutdown();
		
		final GraphDatabaseService gds2 = new GraphDatabaseFactory().newEmbeddedDatabase(dbPath);
		driver = new Neo4jDriver(gds2);
	}

	@Override
	protected long extractLength(final Object segment) throws IOException {
		final Node segmentNode = (Node) segment;
		final Object property = segmentNode.getProperty(ModelConstants.SEGMENT_LENGTH);
		
		final Integer length = (Integer) property; 
		return length;
	}

}
