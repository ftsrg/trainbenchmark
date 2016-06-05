package hu.bme.mit.trainbenchmark.benchmark.phases;

import java.util.Collection;

import com.google.common.collect.ImmutableList;

import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkExecutor;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class InitializeOperationsPhase extends Phase {

	public InitializeOperationsPhase(final BenchmarkExecutor<?, ?> executor) {
		super(executor);
	}

	@Override
	public void run() throws Exception {
		final Collection<RailwayOperation> railwayOperations = ImmutableList.of(RailwayOperation.POSLENGTH_REPAIR);
		executor.initializeOperations(railwayOperations );		
	}

}
