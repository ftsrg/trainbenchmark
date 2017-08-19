package hu.bme.mit.trainbenchmark.neo4j.apoc;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;

public class Neo4jHelper {

	public static GraphDatabaseService startGraphDatabase(final File graphDatabaseDirectory) {
		final GraphDatabaseService graphDb = new GraphDatabaseFactory()
			.newEmbeddedDatabaseBuilder(graphDatabaseDirectory)
			.setConfig("apoc.export.file.enabled", "true")
			.setConfig("apoc.import.file.enabled", "true")
			.newGraphDatabase();
		return graphDb;
	}

}
