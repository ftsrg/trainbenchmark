package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public abstract class Neo4jCypherTransformation<TNeo4jMatch extends Neo4jMatch>
		extends Neo4jTransformation<TNeo4jMatch> {

	protected final String transformationDefinition;

	public Neo4jCypherTransformation(final Neo4jDriver driver, final String workspaceDir,
			final RailwayOperation operation) throws IOException {
		super(driver);
		this.transformationDefinition = FileUtils.readFileToString(
			new File(workspaceDir + Neo4jConstants.CYPHER_DIR
				+ "transformations/" + operation + "Rhs." + Neo4jConstants.QUERY_EXTENSION),
			StandardCharsets.UTF_8);
	}

}
