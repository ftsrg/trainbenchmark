package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations;

import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;

public class UserTransformationLogic<M, T> extends TransformationLogic<M, T, T> {

	protected UserTransformationLogic(final Comparator comparator) {
		super(comparator);
	}

	protected static Map<Query, String> VERTEX_TYPES = ImmutableMap.of( //
			Query.POSLENGTH, ModelConstants.SEGMENT, //
			Query.ROUTESENSOR, ModelConstants.ROUTE, //
			Query.SEMAPHORENEIGHBOR, ModelConstants.ROUTE, //
			Query.SWITCHSENSOR, ModelConstants.SWITCH, //
			Query.SWITCHSET, ModelConstants.SWITCH //
			);

	@Override
	protected void lhs(final Collection<M> currentMatches) throws IOException {
		final String vertexType = VERTEX_TYPES.get(bc.getQuery());
		candidatesToModify = driver.collectVertices(vertexType);
	}

	@Override
	protected List<T> copyAndSort() {
		final Ordering<T> ordering = Ordering.from(comparator);
		final List<T> sortedMatches = ordering.sortedCopy(candidatesToModify);
		return sortedMatches;
	}

}
