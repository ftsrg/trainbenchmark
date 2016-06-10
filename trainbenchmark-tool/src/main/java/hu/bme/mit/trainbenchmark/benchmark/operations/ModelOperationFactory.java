package hu.bme.mit.trainbenchmark.benchmark.operations;

import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public abstract class ModelOperationFactory<TPatternMatch, TDriver extends Driver<?>> {

	public abstract ModelOperation<? extends TPatternMatch, TDriver> createOperation(
			final RailwayOperation operationEnum, final Optional<String> workspacePath, final TDriver driver)
					throws Exception;

}
