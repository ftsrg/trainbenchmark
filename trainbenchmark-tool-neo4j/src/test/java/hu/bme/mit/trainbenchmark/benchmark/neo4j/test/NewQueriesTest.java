package hu.bme.mit.trainbenchmark.benchmark.neo4j.test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBaseBuilder;
import hu.bme.mit.trainbenchmark.benchmark.config.TransformationChangeSetStrategy;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.Neo4jBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jBenchmarkConfigBuilder;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jEngine;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.neo4j.config.Neo4jDeployment;
import hu.bme.mit.trainbenchmark.neo4j.config.Neo4jGraphFormat;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class NewQueriesTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	protected final int benchmarkId = 0;
	protected final long timeout = 120;
	protected final int runs = 1;
	protected final int smallSize = 1;
	protected final int largeSize = 2;

	protected final BenchmarkConfigBaseBuilder bcbb = new BenchmarkConfigBaseBuilder().setBenchmarkId(benchmarkId)
		.setTimeout(timeout).setRuns(runs);

	protected final BenchmarkConfigBaseBuilder bcbbTransformation = bcbb
		.setTransformationChangeSetStrategy(TransformationChangeSetStrategy.FIXED).setTransformationConstant(10)
		.setQueryTransformationCount(2);

	@Parameterized.Parameters(name = "deployment={0}, engine={1}, format={2}")
	public static Iterable<? extends Object[]> data() {
		return Arrays.asList(new Object[][]{ //
			{ Neo4jDeployment.IN_MEMORY, Neo4jEngine.CYPHER,   Neo4jGraphFormat.GRAPHML }, //
//			{ Neo4jDeployment.EMBEDDED,  Neo4jEngine.CYPHER,   Neo4jGraphFormat.GRAPHML }, //
//			{ Neo4jDeployment.EMBEDDED,  Neo4jEngine.CYPHER,   Neo4jGraphFormat.CYPHER  }, //
		});
	}

	@Parameterized.Parameter(value = 0)
	public Neo4jDeployment deployment;

	@Parameterized.Parameter(value = 1)
	public Neo4jEngine engine;

	@Parameterized.Parameter(value = 2)
	public Neo4jGraphFormat graphFormat;

	protected BenchmarkResult runTest(final BenchmarkConfigBase bcb) throws Exception {
		final Neo4jBenchmarkConfig bc = new Neo4jBenchmarkConfigBuilder().setConfigBase(bcb).setDeployment(deployment)
			.setEngine(engine).setGraphFormat(graphFormat).createConfig();
		final Neo4jBenchmarkScenario scenario = new Neo4jBenchmarkScenario(bc);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}

	@Test
	public void activeRouteTest() throws Exception {
		// Arrange
		final String workload = "ActiveRouteTest";
		final String modelFilename = "railway-repair-" + largeSize;
		final List<RailwayOperation> operations = ImmutableList.of(//
			RailwayOperation.ACTIVEROUTE //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
			.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.ACTIVEROUTE).get(0), Matchers.equalTo(7));
	}

	@Test
	public void routeLengthTest() throws Exception {
		// Arrange
		final String workload = "RouteLengthTest";
		final String modelFilename = "railway-repair-" + largeSize;
		final List<RailwayOperation> operations = ImmutableList.of(//
			RailwayOperation.ROUTELENGTH //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
			.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.ROUTELENGTH).get(0), Matchers.equalTo(8));
	}

	@Test
	public void routeReachabilityTest() throws Exception {
		// Arrange
		final String workload = "NewQueriesTest";
		final String modelFilename = "railway-repair-" + largeSize;
		final List<RailwayOperation> operations = ImmutableList.of(//
			RailwayOperation.ROUTEREACHABILITY //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
			.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.ROUTEREACHABILITY).get(0), Matchers.equalTo(26));
	}
}
