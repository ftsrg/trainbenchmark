package hu.bme.mit.trainbenchmark.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

import java.io.File;

public class Neo4jHelper {

	public static GraphDatabaseService startGraphDatabase(final File graphDatabaseDirectory, final String modelDir) {
		final GraphDatabaseService graphDb = new GraphDatabaseFactory()
			.newEmbeddedDatabaseBuilder(graphDatabaseDirectory)
			.setConfig("apoc.export.file.enabled", "true")
			.setConfig("apoc.import.file.enabled", "true")
			.setConfig(GraphDatabaseSettings.load_csv_file_url_root, new File(modelDir).getAbsolutePath())
			.newGraphDatabase();
		return graphDb;
	}

}
