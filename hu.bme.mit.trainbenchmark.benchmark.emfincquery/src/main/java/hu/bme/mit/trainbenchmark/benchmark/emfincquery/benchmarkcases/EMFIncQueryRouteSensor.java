package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensorMatcher;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class EMFIncQueryRouteSensor extends EMFIncQueryBenchmarkCase<RouteSensorMatch> {

	@Override
	protected IncQueryMatcher<RouteSensorMatch> getMatcher() throws IncQueryException {
		return RouteSensorMatcher.on(engine);
	}

}
