package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensorMatcher;
import hu.bme.mit.trainbenchmark.railway.Sensor;

import java.util.Set;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class EMFIncQueryRouteSensor extends EMFIncQueryBenchmarkCase<Sensor, RouteSensorMatch> {

	@Override
	protected Set<Sensor> getResultSet() throws IncQueryException {
		return RouteSensorMatcher.on(engine).getAllValuesOfsensor();
	}

	@Override
	protected IncQueryMatcher<RouteSensorMatch> getMatcher() throws IncQueryException {
		return RouteSensorMatcher.on(engine);
	}

	@Override
	protected Sensor extract(final RouteSensorMatch match) {
		return match.getSensor();
	}
	
}
