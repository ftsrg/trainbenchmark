package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensorMatcher;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class EMFIncQuerySwitchSensor extends EMFIncQueryBenchmarkCase<SwitchSensorMatch> {

	@Override
	protected IncQueryMatcher<SwitchSensorMatch> getMatcher() throws IncQueryException {
		return SwitchSensorMatcher.on(engine);
	}

}
