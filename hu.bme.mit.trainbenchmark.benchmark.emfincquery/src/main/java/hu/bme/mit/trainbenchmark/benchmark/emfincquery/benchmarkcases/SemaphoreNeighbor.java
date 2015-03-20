package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SemaphoreNeighborMatcher;
import hu.bme.mit.trainbenchmark.railway.Route;

import java.util.Set;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class SemaphoreNeighbor extends EMFIncQueryBenchmarkCase<Route, SemaphoreNeighborMatch> {

	@Override
	protected Set<Route> getResultSet() throws IncQueryException {
		return SemaphoreNeighborMatcher.on(engine).getAllValuesOfroute1();
	}

	@Override
	protected IncQueryMatcher<SemaphoreNeighborMatch> getMatcher() throws IncQueryException {
		return SemaphoreNeighborMatcher.on(engine);
	}

	@Override
	protected Route extract(final SemaphoreNeighborMatch match) {
		return match.getRoute1();
	}
	
}
