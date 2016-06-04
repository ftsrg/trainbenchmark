package hu.bme.mit.trainbenchmark.benchmark.operations;

import java.util.Arrays;
import java.util.List;

public class ModelOperationRegistry {
	
	protected final List<ModelOperation<?, ?>> operations;
	
	protected ModelOperationRegistry(ModelOperation<?, ?>[] operations) {
		this.operations = Arrays.asList(operations);
	}
	
	public static ModelOperationRegistry of(ModelOperation<?, ?>... operations) {
		return new ModelOperationRegistry(operations);
	}
	
	public void registerOperation(ModelOperation<?, ?> operation) {
		operations.add(operation);
	}
	
	public List<ModelOperation<?, ?>> getOperations() {
		return operations;
	}
	
}
