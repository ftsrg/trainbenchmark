package hu.bme.mit.trainbenchmark.benchmark.operations;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public abstract class ModelOperationFactory<TPatternMatch, TDriver extends Driver> {

	public abstract ModelOperation<? extends TPatternMatch, TDriver> createOperation(final RailwayOperation operationEnum, final String workspaceDir,
			final TDriver driver) throws Exception;

}
