package hu.bme.mit.trainbenchmark.benchmark.test;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBaseBuilder;
import hu.bme.mit.trainbenchmark.benchmark.config.TransformationChangeSetStrategy;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.config.ExecutionConfig;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class TrainBenchmarkTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	protected final int benchmarkId = 0;
	protected ExecutionConfig executionConfig = ExecutionConfig.defaultExecutionConfig();
	protected final long timeout = 120;
	protected final int runs = 1;
	protected final int size = 1;
	protected final TransformationChangeSetStrategy strategy = TransformationChangeSetStrategy.FIXED;
	protected final int transformationConstant = 10;

	final BenchmarkConfigBaseBuilder bcbb = new BenchmarkConfigBaseBuilder().setBenchmarkId(benchmarkId)
			.setTimeout(timeout).setRuns(runs).setTransformationChangeSetStrategy(strategy)
			.setTransformationConstant(transformationConstant);

	protected abstract BenchmarkResult runTest(BenchmarkConfigBase bcb) throws Exception;

	// batch
	@Ignore
	@Test
	public void batchTest() throws Exception {
		// Arrange
		final String workload = "BatchModelTest";
		final int queryTransformationCount = 0;
		final String modelFilename = "railway-batch-" + size;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS, //
				RailwayOperation.POSLENGTH, //
				RailwayOperation.ROUTESENSOR, //
				RailwayOperation.SEMAPHORENEIGHBOR, //
				RailwayOperation.SWITCHSET, //
				RailwayOperation.SWITCHMONITORED //
		);
		final BenchmarkConfigBase bcb = bcbb.setRuns(runs).setQueryTransformationCount(queryTransformationCount)
				.setModelFilename(modelFilename).setOperations(operations).setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(0), Matchers.equalTo(0));
	}

	// inject
	@Test
	public void injectTest() throws Exception {
		// Arrange
		final String workload = "InjectTest";
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-inject-" + size;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS, //
				RailwayOperation.POSLENGTH, //
				RailwayOperation.ROUTESENSOR, //
				RailwayOperation.SEMAPHORENEIGHBOR, //
				RailwayOperation.SWITCHSET, //
				RailwayOperation.SWITCHMONITORED, //
//				RailwayOperation.CONNECTEDSEGMENTS_INJECT, //
				RailwayOperation.POSLENGTH_INJECT, //
				RailwayOperation.ROUTESENSOR_INJECT, //
				RailwayOperation.SEMAPHORENEIGHBOR_INJECT, //
				RailwayOperation.SWITCHSET_INJECT, //
				RailwayOperation.SWITCHMONITORED_INJECT //
		);
		final BenchmarkConfigBase bcb = bcbb.setQueryTransformationCount(queryTransformationCount)
				.setModelFilename(modelFilename).setOperations(operations).setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(0), Matchers.equalTo(3));
//		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(1), Matchers.equalTo(13));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(0), Matchers.equalTo(11));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(1), Matchers.equalTo(21));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(0), Matchers.equalTo(9));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(1), Matchers.equalTo(13));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0), Matchers.equalTo(12));
//		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(1), Matchers.equalTo(22));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(1), Matchers.equalTo(26));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(1), Matchers.equalTo(10));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(1), Matchers.equalTo(0));

		System.out.println("-----------");
	}

	// repair
	@Ignore
	@Test
	public void repairTest() throws Exception {
		// Arrange
		final String workload = "RepairTest";
		final int queryTransformationCount = 5;
		final String modelFilename = "railway-repair-" + size;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS_REPAIR, //
				RailwayOperation.POSLENGTH_REPAIR, //
				RailwayOperation.ROUTESENSOR_REPAIR, //
				RailwayOperation.SEMAPHORENEIGHBOR_REPAIR, //
				RailwayOperation.SWITCHSET_REPAIR, //
				RailwayOperation.SWITCHMONITORED_REPAIR //
		);
		final BenchmarkConfigBase bcb = bcbb.setQueryTransformationCount(queryTransformationCount)
				.setModelFilename(modelFilename).setOperations(operations).setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(0), Matchers.equalTo(8));
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(1), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(0), Matchers.equalTo(95));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(1), Matchers.equalTo(85));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(0), Matchers.equalTo(18));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(1), Matchers.equalTo(8));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0), Matchers.equalTo(3));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(1), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(1), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(0), Matchers.equalTo(5));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(1), Matchers.equalTo(0));
	}

	@Ignore
	@Test
	public void connectedSegmentsRepairTest() throws Exception {
		// Arrange
		final String workload = "ConnectedSegmentsRepairTest";
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-repair-" + size;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS_REPAIR //
		);
		final BenchmarkConfigBase bcb = bcbb.setQueryTransformationCount(queryTransformationCount)
				.setModelFilename(modelFilename).setOperations(operations).setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(0), Matchers.equalTo(8));
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(1), Matchers.equalTo(0));
	}

	@Ignore
	@Test
	public void posLengthRepairTest() throws Exception {
		// Arrange
		final String workload = "PosLengthRepairTest";
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-repair-" + size;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.POSLENGTH_REPAIR //
		);
		final BenchmarkConfigBase bcb = bcbb.setQueryTransformationCount(queryTransformationCount)
				.setModelFilename(modelFilename).setOperations(operations).setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(0), Matchers.equalTo(95));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(1), Matchers.equalTo(85));
	}

	@Ignore
	@Test
	public void routeSensorRepairTest() throws Exception {
		// Arrange
		final String workload = "RouteSensorRepairTest";
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-repair-" + size;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.ROUTESENSOR_REPAIR //
		);
		final BenchmarkConfigBase bcb = bcbb.setQueryTransformationCount(queryTransformationCount)
				.setModelFilename(modelFilename).setOperations(operations).setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(0), Matchers.equalTo(18));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(1), Matchers.equalTo(8));
	}

	@Ignore
	@Test
	public void semaphoreNeighborRepairTest() throws Exception {
		// Arrange
		final String workload = "SemaphoreNeighborRepairTest";
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-repair-" + size;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SEMAPHORENEIGHBOR_REPAIR //
		);
		final BenchmarkConfigBase bcb = bcbb.setQueryTransformationCount(queryTransformationCount)
				.setModelFilename(modelFilename).setOperations(operations).setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0), Matchers.equalTo(3));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(1), Matchers.equalTo(0));
	}

	@Test
	public void switchMonitoredRepairTest() throws Exception {
		// Arrange
		final String workload = "SwitchMonitoredRepairTest";
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-repair-" + size;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SWITCHMONITORED_REPAIR //
		);
		final BenchmarkConfigBase bcb = bcbb.setQueryTransformationCount(queryTransformationCount)
				.setModelFilename(modelFilename).setOperations(operations).setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(1), Matchers.equalTo(0));
	}

	@Ignore
	@Test
	public void switchSetRepairTest() throws Exception {
		// Arrange
		final String workload = "SwitchSetRepairTest";
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-repair-" + size;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SWITCHSET_REPAIR //
		);
		final BenchmarkConfigBase bcb = bcbb.setQueryTransformationCount(queryTransformationCount)
				.setModelFilename(modelFilename).setOperations(operations).setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(0), Matchers.equalTo(5));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(1), Matchers.equalTo(0));
	}
	// inject

	@Ignore
	@Test
	public void connectedSegmentsInjectTest() throws Exception {
		// Arrange
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-inject-" + size;
		final String workload = "ConnectedSegmentsInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS, //
				RailwayOperation.CONNECTEDSEGMENTS_INJECT //
		);
		final BenchmarkConfigBase bcb = bcbb.setQueryTransformationCount(queryTransformationCount)
				.setModelFilename(modelFilename).setOperations(operations).setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(0), Matchers.equalTo(3));
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(1), Matchers.equalTo(13));
	}

	@Ignore
	@Test
	public void posLengthInjectTest() throws Exception {
		// Arrange
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-inject-" + size;
		final String workload = "PosLengthInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.POSLENGTH, //
				RailwayOperation.POSLENGTH_INJECT //
		);
		final BenchmarkConfigBase bcb = bcbb.setQueryTransformationCount(queryTransformationCount)
				.setModelFilename(modelFilename).setOperations(operations).setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(0), Matchers.equalTo(11));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(1), Matchers.equalTo(21));
	}

	@Ignore
	@Test
	public void routeSensorInjectTest() throws Exception {
		// Arrange
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-inject-" + size;
		final String workload = "RouteSensorInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.ROUTESENSOR, //
				RailwayOperation.ROUTESENSOR_INJECT //
		);
		final BenchmarkConfigBase bcb = bcbb.setQueryTransformationCount(queryTransformationCount)
				.setModelFilename(modelFilename).setOperations(operations).setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(0), Matchers.equalTo(9));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(1), Matchers.equalTo(19));
	}

	@Ignore
	@Test
	public void semaphoreNeighborInjectTest() throws Exception {
		// Arrange
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-inject-" + size;
		final String workload = "SemaphoreNeighborInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SEMAPHORENEIGHBOR, //
				RailwayOperation.SEMAPHORENEIGHBOR_INJECT //
		);
		final BenchmarkConfigBase bcb = bcbb.setQueryTransformationCount(queryTransformationCount)
				.setModelFilename(modelFilename).setOperations(operations).setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0), Matchers.equalTo(12));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(1), Matchers.equalTo(32));
	}

	@Ignore
	@Test
	public void switchMonitoredInjectTest() throws Exception {
		// Arrange
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-inject-" + size;
		final String workload = "SwitchMonitoredInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SWITCHMONITORED, //
				RailwayOperation.SWITCHMONITORED_INJECT //
		);
		final BenchmarkConfigBase bcb = bcbb.setQueryTransformationCount(queryTransformationCount)
				.setModelFilename(modelFilename).setOperations(operations).setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(1), Matchers.equalTo(10));
	}

	@Ignore
	@Test
	public void switchSetInjectTest() throws Exception {
		// Arrange
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-inject-" + size;
		final String workload = "SwitchSetInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SWITCHSET, //
				RailwayOperation.SWITCHSET_INJECT //
		);
		final BenchmarkConfigBase bcb = bcbb.setQueryTransformationCount(queryTransformationCount)
				.setModelFilename(modelFilename).setOperations(operations).setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(1), Matchers.equalTo(6));
	}

}
