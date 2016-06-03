package hu.bme.mit.trainbenchmark.benchmark.operations;

import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;

/**
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
 * 
 * @param <TPatternMatch>
 *            defines the data transfer object of the query and transformation operations. ModelFragment objects are the output of the query
 *            and (optionally) the input
 */
public class ModelOperation<TPatternMatch, TDriver extends Driver<?>> {

	protected final ModelQuery<TPatternMatch, TDriver> query;
	protected final Optional<ModelTransformation<TPatternMatch, TDriver>> transformation;

	public ModelOperation(final ModelQuery<TPatternMatch, TDriver> query) {
		super();
		this.query = query;
		this.transformation = null;
	}

	public ModelOperation(final ModelQuery<TPatternMatch, TDriver> query,
			Optional<ModelTransformation<TPatternMatch, TDriver>> transformation) {
		super();
		this.query = query;
		this.transformation = transformation;
	}

	public static <TPatternMatch, TDriver extends Driver<?>> ModelOperation<TPatternMatch, TDriver> of(
			final ModelQuery<TPatternMatch, TDriver> query) {
		return new ModelOperation<>(query, Optional.empty());
	}

	public static <TPatternMatch, TDriver extends Driver<?>> ModelOperation<TPatternMatch, TDriver> of(
			final ModelQuery<TPatternMatch, TDriver> query, final ModelTransformation<TPatternMatch, TDriver> transformation) {
		return new ModelOperation<>(query, Optional.of(transformation));
	}

	public ModelQuery<TPatternMatch, TDriver> getQuery() {
		return query;
	}

	public ModelTransformation<TPatternMatch, TDriver> getTransformation() {
		return transformation.get();
	}

}
