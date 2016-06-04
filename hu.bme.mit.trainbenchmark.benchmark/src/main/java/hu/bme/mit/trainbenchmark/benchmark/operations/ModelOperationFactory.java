package hu.bme.mit.trainbenchmark.benchmark.operations;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public abstract class ModelOperationFactory<TDriver extends Driver<?>> {

	public abstract ModelOperation<?, TDriver> createOperation(RailwayOperation operationEnum, TDriver driver);

}
