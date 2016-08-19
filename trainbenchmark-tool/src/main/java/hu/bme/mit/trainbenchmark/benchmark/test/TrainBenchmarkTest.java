package hu.bme.mit.trainbenchmark.benchmark.test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigCore;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.List;

public abstract class TrainBenchmarkTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	protected abstract BenchmarkResult runTest(BenchmarkConfigCore bcc) throws Exception;

	protected final String xms = "1G";
	protected final String xmx = "1G";
	protected final long timeout = 120;
	protected final int runs = 1;

	// batch

	@Test
	public void batchTest() throws Exception {
		// Arrange
		final String workload = "BatchModelTest";
		final int queryTransformationCount = 0;
		final String modelFilename = "railway-batch-1";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS, //
				RailwayOperation.POSLENGTH, //
				RailwayOperation.ROUTESENSOR, //
				RailwayOperation.SEMAPHORENEIGHBOR, //
				RailwayOperation.SWITCHMONITORED, //
				RailwayOperation.SWITCHSET //
		);
		final BenchmarkConfigCore bcc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations, workload);

		// Act
		final BenchmarkResult result = runTest(bcc);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(0), Matchers.equalTo(0));
	}

	// repair

	@Test
	public void repairTest() throws Exception {
		// Arrange
		final String workload = "RepairTest";
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-repair-1";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS_REPAIR, //
				RailwayOperation.POSLENGTH_REPAIR, //
				RailwayOperation.ROUTESENSOR_REPAIR, //
				RailwayOperation.SEMAPHORENEIGHBOR_REPAIR, //
				RailwayOperation.SWITCHMONITORED_REPAIR, //
				RailwayOperation.SWITCHSET_REPAIR //
		);
		final BenchmarkConfigCore bcc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations, workload);

		// Act
		final BenchmarkResult result = runTest(bcc);

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

	@Test
	public void connectedSegmentsRepairTest() throws Exception {
		// Arrange
		final String workload = "ConnectedSegmentsRepairTest";
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-repair-1";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS_REPAIR //
		);
		final BenchmarkConfigCore bcc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations, workload);

		// Act
		final BenchmarkResult result = runTest(bcc);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(0), Matchers.equalTo(8));
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(1), Matchers.equalTo(0));
	}

	@Test
	public void posLengthRepairTest() throws Exception {
		// Arrange
		final String workload = "PosLengthRepairTest";
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-repair-1";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.POSLENGTH_REPAIR //
		);
		final BenchmarkConfigCore bcc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations, workload);

		// Act
		final BenchmarkResult result = runTest(bcc);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(0), Matchers.equalTo(95));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(1), Matchers.equalTo(85));
	}

	@Test
	public void routeSensorRepairTest() throws Exception {
		// Arrange
		final String workload = "RouteSensorRepairTest";
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-repair-1";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.ROUTESENSOR_REPAIR //
		);
		final BenchmarkConfigCore bcc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations, workload);

		// Act
		final BenchmarkResult result = runTest(bcc);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(0), Matchers.equalTo(18));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(1), Matchers.equalTo(8));
	}

	@Test
	public void semaphoreNeighborRepairTest() throws Exception {
		// Arrange
		final String workload = "SemaphoreNeighborRepairTest";
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-repair-1";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SEMAPHORENEIGHBOR_REPAIR //
		);
		final BenchmarkConfigCore bcc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations, workload);

		// Act
		final BenchmarkResult result = runTest(bcc);

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
		final String modelFilename = "railway-repair-1";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SWITCHMONITORED_REPAIR //
		);
		final BenchmarkConfigCore bcc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations, workload);

		// Act
		final BenchmarkResult result = runTest(bcc);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(1), Matchers.equalTo(0));
	}

	@Test
	public void switchSetRepairTest() throws Exception {
		// Arrange
		final String workload = "SwitchSetRepairTest";
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-repair-1";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SWITCHSET_REPAIR //
		);
		final BenchmarkConfigCore bcc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations, workload);

		// Act
		final BenchmarkResult result = runTest(bcc);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(0), Matchers.equalTo(5));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(1), Matchers.equalTo(0));
	}
	// inject

	@Test
	public void connectedSegmentsInjectTest() throws Exception {
		// Arrange
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-inject-1";
		final String workload = "ConnectedSegmentsInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.CONNECTEDSEGMENTS, //
				RailwayOperation.CONNECTEDSEGMENTS_INJECT //
		);
		final BenchmarkConfigCore bcc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations, workload);

		// Act
		final BenchmarkResult result = runTest(bcc);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(0), Matchers.equalTo(3));
		collector.checkThat(allMatches.get(RailwayQuery.CONNECTEDSEGMENTS).get(1), Matchers.equalTo(13));
	}

	@Test
	public void posLengthInjectTest() throws Exception {
		// Arrange
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-inject-1";
		final String workload = "PosLengthInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.POSLENGTH, //
				RailwayOperation.POSLENGTH_INJECT //
		);
		final BenchmarkConfigCore bcc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations, workload);

		// Act
		final BenchmarkResult result = runTest(bcc);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(0), Matchers.equalTo(11));
		collector.checkThat(allMatches.get(RailwayQuery.POSLENGTH).get(1), Matchers.equalTo(21));
	}

	@Test
	public void routeSensorInjectTest() throws Exception {
		// Arrange
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-inject-1";
		final String workload = "RouteSensorInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.ROUTESENSOR, //
				RailwayOperation.ROUTESENSOR_INJECT //
		);
		final BenchmarkConfigCore bcc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations, workload);

		// Act
		final BenchmarkResult result = runTest(bcc);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(0), Matchers.equalTo(9));
		collector.checkThat(allMatches.get(RailwayQuery.ROUTESENSOR).get(1), Matchers.equalTo(19));
	}

	@Test
	public void semaphoreNeighborInjectTest() throws Exception {
		// Arrange
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-inject-1";
		final String workload = "SemaphoreNeighborInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SEMAPHORENEIGHBOR, //
				RailwayOperation.SEMAPHORENEIGHBOR_INJECT //
		);
		final BenchmarkConfigCore bcc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations, workload);

		// Act
		final BenchmarkResult result = runTest(bcc);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(0), Matchers.equalTo(12));
		collector.checkThat(allMatches.get(RailwayQuery.SEMAPHORENEIGHBOR).get(1), Matchers.equalTo(32));
	}

	@Test
	public void switchMonitoredInjectTest() throws Exception {
		// Arrange
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-inject-1";
		final String workload = "SwitchMonitoredInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SWITCHMONITORED, //
				RailwayOperation.SWITCHMONITORED_INJECT //
		);
		final BenchmarkConfigCore bcc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations, workload);

		// Act
		final BenchmarkResult result = runTest(bcc);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHMONITORED).get(1), Matchers.equalTo(10));
	}

	@Test
	public void switchSetInjectTest() throws Exception {
		// Arrange
		final int queryTransformationCount = 1;
		final String modelFilename = "railway-inject-1";
		final String workload = "SwitchSetInjectTest";
		final List<RailwayOperation> operations = ImmutableList.of(//
				RailwayOperation.SWITCHSET, //
				RailwayOperation.SWITCHSET_INJECT //
		);
		final BenchmarkConfigCore bcc = new BenchmarkConfigCore(xms, xmx, timeout, runs, queryTransformationCount, modelFilename, operations, workload);

		// Act
		final BenchmarkResult result = runTest(bcc);

		// Assert
		final ListMultimap<RailwayQuery, Integer> allMatches = result.getLastRunResult().getMatches();
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(0), Matchers.equalTo(0));
		collector.checkThat(allMatches.get(RailwayQuery.SWITCHSET).get(1), Matchers.equalTo(6));
	}

}
