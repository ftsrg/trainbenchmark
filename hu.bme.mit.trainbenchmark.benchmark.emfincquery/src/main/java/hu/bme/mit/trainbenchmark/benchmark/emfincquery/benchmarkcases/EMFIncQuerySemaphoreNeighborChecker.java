package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SemaphoreNeighborMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryDriver;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public class EMFIncQuerySemaphoreNeighborChecker extends EMFIncQueryChecker<SemaphoreNeighborMatch> {

	public EMFIncQuerySemaphoreNeighborChecker(final EMFIncQueryDriver<SemaphoreNeighborMatch> eiqDriver) {
		super(eiqDriver);
	}

	@Override
	public IncQueryMatcher<SemaphoreNeighborMatch> getMatcher() throws IncQueryException {
		return SemaphoreNeighborMatcher.on(eiqDriver.getEngine());
	}

}
