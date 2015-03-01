package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SignalNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SignalNeighborMatcher;
import hu.bme.mit.trainbenchmark.railway.Route;

import java.util.Set;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class SignalNeighbor extends EMFIncQueryBenchmarkCase<Route, SignalNeighborMatch> {

	@Override
	protected Set<Route> getResultSet() throws IncQueryException {
		return SignalNeighborMatcher.on(engine).getAllValuesOfroute1();
	}

	@Override
	protected IncQueryMatcher<SignalNeighborMatch> getMatcher() throws IncQueryException {
		return SignalNeighborMatcher.on(engine);
	}

	@Override
	protected Route extract(final SignalNeighborMatch match) {
		return match.getRoute1();
	}
	
}
