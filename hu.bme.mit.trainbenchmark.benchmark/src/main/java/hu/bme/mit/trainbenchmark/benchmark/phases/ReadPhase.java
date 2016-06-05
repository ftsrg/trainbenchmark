package hu.bme.mit.trainbenchmark.benchmark.phases;

import java.util.Collection;

import com.google.common.collect.ImmutableList;

import hu.bme.mit.trainbenchmark.benchmark.executor.BenchmarkExecutor;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class ReadPhase extends Phase {
	
	public ReadPhase(final BenchmarkExecutor executor) {
		super(executor);
	}

	@Override
	public void run() throws Exception {
		executor.read();
	}
	
	@Override
	public void cleanup() throws Exception {
		final Collection<RailwayOperation> railwayOperations = ImmutableList.of(RailwayOperation.POSLENGTH_REPAIR);
		executor.initializeOperations(railwayOperations );
	}

}
