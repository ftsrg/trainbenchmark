package hu.bme.mit.trainbenchmark.benchmark.operations;

import java.util.Optional;

/**
 * @param <ModelFragment> defines the data transfer object. ModelFragment objects are the output of the query and (optionally) the input   
 * 
 * Defines a model operation, consisting of
 * <ul>
 * <li>a model query,</li>
 * <li>(optionally) a model transformation</li>
 * </ul>
 * 
 * Examples:
 * <ul>
 * <li>PosLength query, PosLength repair transformation</li>
 * <li>RouteSensor inject transformation LHS (collecting routes), RouteSensor inject transformation RHS</li>
 * <li>SwitchSet query, no transformation</li>
 * </ul>
 */
public class ModelOperation<ModelFragment> {

	protected final ModelQuery<ModelFragment> query;
	protected final Optional<ModelTransformation<ModelFragment>> transformation;
	
	protected ModelOperation(ModelQuery<ModelFragment> query, Optional<ModelTransformation<ModelFragment>> transformation) {
		super();
		this.query = query;
		this.transformation = transformation;
	}
	
	public static <ModelFragment> ModelOperation<ModelFragment> of(ModelQuery<ModelFragment> query) {
		return new ModelOperation<>(query, Optional.empty());
	}
	
	public static <ModelFragment> ModelOperation<ModelFragment> of(ModelQuery<ModelFragment> query, ModelTransformation<ModelFragment> transformation) {
		return new ModelOperation<>(query, Optional.of(transformation));
	}

	public ModelQuery<ModelFragment> getQuery() {
		return query;
	}
	
	public ModelTransformation<ModelFragment> getTransformation() {
		return transformation.get();
	}
	
}
