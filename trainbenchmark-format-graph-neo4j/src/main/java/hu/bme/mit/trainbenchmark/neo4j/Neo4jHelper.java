package hu.bme.mit.trainbenchmark.neo4j;

import hu.bme.mit.trainbenchmark.neo4j.config.Neo4jDeployment;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseBuilder;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.test.TestGraphDatabaseFactory;

import java.io.File;

public class Neo4jHelper {

	public static GraphDatabaseBuilder getBuilder(final Neo4jDeployment deployment, final File graphDatabaseDirectory) {
		switch (deployment) {
			case EMBEDDED:
				return new GraphDatabaseFactory() .newEmbeddedDatabaseBuilder(graphDatabaseDirectory);
			case IN_MEMORY:
				return new TestGraphDatabaseFactory().newImpermanentDatabaseBuilder();
		}
		throw new IllegalStateException("Deployment mode '" + deployment + "' not supported.");
	}

	public static GraphDatabaseService startGraphDatabase(final Neo4jDeployment deployment, final File graphDatabaseDirectory) {
		return getBuilder(deployment, graphDatabaseDirectory)
			.setConfig("apoc.export.file.enabled", "true")
			.setConfig("apoc.import.file.enabled", "true")
			.newGraphDatabase();
	}

}
