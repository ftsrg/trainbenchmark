package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SignalNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SignalNeighborMatcher;
import hu.bme.mit.trainbenchmark.railway.Signal;

import java.util.Set;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class SignalNeighbor extends EMFIncQueryBenchmarkCase<Signal, SignalNeighborMatch> {

	@Override
	protected Set<Signal> getResultSet() throws IncQueryException {
		return SignalNeighborMatcher.on(engine).getAllValuesOfsignal();
	}

	@Override
	protected IncQueryMatcher<SignalNeighborMatch> getMatcher() throws IncQueryException {
		return SignalNeighborMatcher.on(engine);
	}

	@Override
	protected Signal extract(final SignalNeighborMatch match) {
		return match.getSignal();
	}
	
}
