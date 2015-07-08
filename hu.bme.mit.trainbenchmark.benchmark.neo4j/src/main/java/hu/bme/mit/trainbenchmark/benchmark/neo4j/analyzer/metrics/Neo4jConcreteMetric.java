package hu.bme.mit.trainbenchmark.benchmark.neo4j.analyzer.metrics;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.metrics.ConcreteMetric;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

public abstract class Neo4jConcreteMetric extends ConcreteMetric<Neo4jDriver> {

	protected GraphDatabaseService database;

	protected GlobalGraphOperations graphOperations;

	protected Transaction tx;

	public Neo4jConcreteMetric(Neo4jDriver driver) {
		super(driver);
		database = driver.getGraphDb();
		graphOperations = GlobalGraphOperations.at(database);
	}

	public void beginTransaction() {
		tx = database.beginTx();
	}

	public void finishTransaction() {
		tx.success();
		tx.close();
	}

	public GraphDatabaseService getDatabase() {
		return database;
	}

	public void setDatabase(GraphDatabaseService database) {
		this.database = database;
	}

	public GlobalGraphOperations getGraphOperations() {
		return graphOperations;
	}

	public void setGraphOperations(GlobalGraphOperations graphOperations) {
		this.graphOperations = graphOperations;
	}

}
