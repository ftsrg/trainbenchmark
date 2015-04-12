package hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations;

import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;

public class UserTransformation<M, T> extends Transformation<M, T, T> {

	protected static Map<String, String> VERTEX_TYPES = ImmutableMap.of( //
			QueryConstants.POSLENGTH, ModelConstants.SEGMENT, //
			QueryConstants.ROUTESENSOR, ModelConstants.ROUTE, //
			QueryConstants.SEMAPHORENEIGHBOR, ModelConstants.ROUTE, //
			QueryConstants.SWITCHSENSOR, ModelConstants.SWITCH, //
			QueryConstants.SWITCHSET, ModelConstants.SWITCH //
			);

	@Override
	protected void lhs() throws IOException {
		final String vertexType = VERTEX_TYPES.get(bc.getQuery());
		candidatesToModify = driver.collectVertices(vertexType);
	}

	@Override
	protected List<T> copyAndSort() {
		final Ordering<T> ordering = Ordering.from(driver.getElementComparator());
		final List<T> sortedMatches = ordering.sortedCopy(candidatesToModify);
		return sortedMatches;
	}

	@Override
	protected void rhs() throws IOException {
		// TODO Auto-generated method stub

	}

}
