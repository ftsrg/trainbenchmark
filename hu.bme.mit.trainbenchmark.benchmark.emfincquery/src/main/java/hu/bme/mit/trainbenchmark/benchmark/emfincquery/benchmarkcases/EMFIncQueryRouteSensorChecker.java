package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryDriver;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class EMFIncQueryRouteSensorChecker extends EMFIncQueryChecker<RouteSensorMatch> {

	public EMFIncQueryRouteSensorChecker(final EMFIncQueryDriver<RouteSensorMatch> eiqDriver) {
		super(eiqDriver);
	}

	@Override
	public IncQueryMatcher<RouteSensorMatch> getMatcher() throws IncQueryException {
		return RouteSensorMatcher.on(eiqDriver.getEngine());
	}

}
