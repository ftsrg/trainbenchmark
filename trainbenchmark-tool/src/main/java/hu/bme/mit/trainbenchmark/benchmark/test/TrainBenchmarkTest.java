package hu.bme.mit.trainbenchmark.benchmark.test;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBaseBuilder;
import hu.bme.mit.trainbenchmark.benchmark.config.TransformationChangeSetStrategy;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.config.ExecutionConfig;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

@RunWith(Parameterized.class)
public abstract class TrainBenchmarkTest {

	@Parameters(name="size={0}")
	public static Iterable<? extends Object> data() {
		return Arrays.asList(1, 2, 4, 8, 16, 32, 64, 128);
	}

	@Parameter
	public int size;

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	protected final int benchmarkId = 0;
	protected ExecutionConfig executionConfig = ExecutionConfig.defaultExecutionConfig();
	protected final long timeout = 120;
	protected final int runs = 1;
	protected final int smallSize = 1;
	protected final int largeSize = 2;

	final BenchmarkConfigBaseBuilder bcbb = new BenchmarkConfigBaseBuilder().setBenchmarkId(benchmarkId)
			.setTimeout(timeout).setRuns(runs);

	final BenchmarkConfigBaseBuilder bcbbTransformation = bcbb
			.setTransformationChangeSetStrategy(TransformationChangeSetStrategy.FIXED).setTransformationConstant(10)
			.setQueryTransformationCount(1);

	protected abstract BenchmarkResult runTest(BenchmarkConfigBase bcb) throws Exception;

	// batch

	@Ignore@Test
	public void batchTest() throws Exception {
		// Arrange
		final String workload = "BatchModelTest";
		final String modelFilename = "railway-batch-" + smallSize;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS, //
				RailwayOperation.POSLENGTH, //
				RailwayOperation.ROUTESENSOR, //
				RailwayOperation.SEMAPHORENEIGHBOR, //
				RailwayOperation.SWITCHSET, //
				RailwayOperation.SWITCHMONITORED //
		);
		final BenchmarkConfigBase bcb = bcbb.setRuns(runs).setModelFilename(modelFilename).setOperations(operations)
				.setWorkload(workload).createConfigBase();

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
		final String modelFilename = "railway-inject-" + size;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS, //
				RailwayOperation.POSLENGTH, //
				RailwayOperation.ROUTESENSOR, //
				RailwayOperation.SEMAPHORENEIGHBOR, //
				RailwayOperation.SWITCHSET, //
				RailwayOperation.SWITCHMONITORED, //
				RailwayOperation.CONNECTEDSEGMENTS_INJECT, //
				RailwayOperation.POSLENGTH_INJECT, //
				RailwayOperation.ROUTESENSOR_INJECT, //
				RailwayOperation.SEMAPHORENEIGHBOR_INJECT, //
				RailwayOperation.SWITCHSET_INJECT, //
				RailwayOperation.SWITCHMONITORED_INJECT //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
				.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		
		System.out.println("===== " + size + " =====");
		System.out.println(RailwayQuery.CONNECTEDSEGMENTS + ": " + allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(0));
		System.out.println(RailwayQuery.POSLENGTH         + ": " + allMatches.get(RailwayQuery.POSLENGTH).get(0));
		System.out.println(RailwayQuery.ROUTESENSOR       + ": " + allMatches.get(RailwayQuery.ROUTESENSOR).get(0));
		System.out.println(RailwayQuery.SEMAPHORENEIGHBOR + ": " + allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0));
		System.out.println(RailwayQuery.SWITCHMONITORED   + ": " + allMatches.get(RailwayQuery.SWITCHMONITORED).get(0));
		System.out.println(RailwayQuery.SWITCHSET         + ": " + allMatches.get(RailwayQuery.SWITCHSET).get(0));
		System.out.println();
		
//		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(0), Matchers.equalTo(8));
//		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(1), Matchers.equalTo(18));
//		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(0), Matchers.equalTo(17));
//		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(1), Matchers.equalTo(27));
//		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(0), Matchers.equalTo(5));
//		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(1), Matchers.equalTo(11));
//		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0), Matchers.equalTo(3));
//		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(1), Matchers.equalTo(12));
//		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(0), Matchers.equalTo(0));
//		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(1), Matchers.equalTo(10));
//		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(0), Matchers.equalTo(2));
//		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(1), Matchers.equalTo(0));
	}

	// repair

	@Ignore@Test
	public void repairTest() throws Exception {
		// Arrange
		final String workload = "RepairTest";
		final String modelFilename = "railway-repair-" + smallSize;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS_REPAIR, //
				RailwayOperation.POSLENGTH_REPAIR, //
				RailwayOperation.ROUTESENSOR_REPAIR, //
				RailwayOperation.SEMAPHORENEIGHBOR_REPAIR, //
				RailwayOperation.SWITCHSET_REPAIR, //
				RailwayOperation.SWITCHMONITORED_REPAIR //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
				.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(0), Matchers.equalTo(4));
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(1), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(0), Matchers.equalTo(97));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(1), Matchers.equalTo(87));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(0), Matchers.equalTo(17));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(1), Matchers.equalTo(7));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0), Matchers.equalTo(1));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(1), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(1), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(0), Matchers.equalTo(3));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(1), Matchers.equalTo(3));
	}

	@Ignore@Test
	public void connectedSegmentsRepairTest() throws Exception {
		// Arrange
		final String workload = "ConnectedSegmentsRepairTest";
		final String modelFilename = "railway-repair-" + largeSize;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS_REPAIR //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
				.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(0), Matchers.equalTo(11));
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(1), Matchers.equalTo(1));
	}

	@Ignore@Test
	public void posLengthRepairTest() throws Exception {
		// Arrange
		final String workload = "PosLengthRepairTest";
		final String modelFilename = "railway-repair-" + largeSize;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.POSLENGTH_REPAIR //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
				.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(0), Matchers.equalTo(238));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(1), Matchers.equalTo(228));
	}

	@Ignore@Test
	public void routeSensorRepairTest() throws Exception {
		// Arrange
		final String workload = "RouteSensorRepairTest";
		final String modelFilename = "railway-repair-" + largeSize;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.ROUTESENSOR_REPAIR //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
				.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(0), Matchers.equalTo(51));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(1), Matchers.equalTo(41));
	}

	@Ignore@Test
	public void semaphoreNeighborRepairTest() throws Exception {
		// Arrange
		final String workload = "SemaphoreNeighborRepairTest";
		final String modelFilename = "railway-repair-" + largeSize;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SEMAPHORENEIGHBOR_REPAIR //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
				.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0), Matchers.equalTo(11));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(1), Matchers.equalTo(0));
	}

	@Ignore@Test
	public void switchMonitoredRepairTest() throws Exception {
		// Arrange
		final String workload = "SwitchMonitoredRepairTest";
		final String modelFilename = "railway-repair-" + largeSize;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SWITCHMONITORED_REPAIR //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
				.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(0), Matchers.equalTo(1));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(1), Matchers.equalTo(0));
	}

	@Ignore@Test
	public void switchSetRepairTest() throws Exception {
		// Arrange
		final String workload = "SwitchSetRepairTest";
		final String modelFilename = "railway-repair-" + largeSize;
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SWITCHSET_REPAIR //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
				.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(0), Matchers.equalTo(10));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(1), Matchers.equalTo(0));
	}

	// inject

	@Ignore@Test
	public void connectedSegmentsInjectTest() throws Exception {
		// Arrange
		final String modelFilename = "railway-inject-" + largeSize;
		final String workload = "ConnectedSegmentsInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS, //
				RailwayOperation.CONNECTEDSEGMENTS_INJECT //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
				.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(0), Matchers.equalTo(18));
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(1), Matchers.equalTo(28));
	}

	@Ignore@Test
	public void posLengthInjectTest() throws Exception {
		// Arrange
		final String modelFilename = "railway-inject-" + largeSize;
		final String workload = "PosLengthInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.POSLENGTH, //
				RailwayOperation.POSLENGTH_INJECT //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
				.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(0), Matchers.equalTo(44));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(1), Matchers.equalTo(54));
	}

	@Ignore@Test
	public void routeSensorInjectTest() throws Exception {
		// Arrange
		final String modelFilename = "railway-inject-" + largeSize;
		final String workload = "RouteSensorInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.ROUTESENSOR, //
				RailwayOperation.ROUTESENSOR_INJECT //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
				.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(0), Matchers.equalTo(16));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(1), Matchers.equalTo(26));
	}

	@Ignore@Test
	public void semaphoreNeighborInjectTest() throws Exception {
		// Arrange
		final String modelFilename = "railway-inject-" + largeSize;
		final String workload = "SemaphoreNeighborInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SEMAPHORENEIGHBOR, //
				RailwayOperation.SEMAPHORENEIGHBOR_INJECT //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
				.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0), Matchers.equalTo(3));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(1), Matchers.equalTo(45));
	}

	@Ignore@Test
	public void switchMonitoredInjectTest() throws Exception {
		// Arrange
		final String modelFilename = "railway-inject-" + largeSize;
		final String workload = "SwitchMonitoredInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SWITCHMONITORED, //
				RailwayOperation.SWITCHMONITORED_INJECT //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
				.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(1), Matchers.equalTo(10));
	}

	@Ignore@Test
	public void switchSetInjectTest() throws Exception {
		// Arrange
		final String modelFilename = "railway-inject-" + largeSize;
		final String workload = "SwitchSetInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SWITCHSET, //
				RailwayOperation.SWITCHSET_INJECT //
		);
		final BenchmarkConfigBase bcb = bcbbTransformation.setModelFilename(modelFilename).setOperations(operations)
				.setWorkload(workload).createConfigBase();

		// Act
		final BenchmarkResult result = runTest(bcb);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(0), Matchers.equalTo(5));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(1), Matchers.equalTo(11));
	}

}
