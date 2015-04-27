package hu.bme.mit.trainbenchmark.benchmark.emfincquery.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver.EMFIncQueryDriver;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.util.Collection;

import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

public abstract class EMFIncQueryChecker<M extends BasePatternMatch> extends Checker<M> {

	protected Collection<M> matches;
	protected final EMFIncQueryDriver<M> eiqDriver;

	protected EMFIncQueryChecker(final EMFIncQueryDriver<M> eiqDriver) {
		this.eiqDriver = eiqDriver;
	}

	public static EMFIncQueryChecker newInstance(final EMFIncQueryDriver eiqDriver, final Query query) {
		switch (query) {
		case POSLENGTH:
			return new EMFIncQueryPosLengthChecker(eiqDriver);
		case ROUTESENSOR:
			return new EMFIncQueryRouteSensorChecker(eiqDriver);
		case SEMAPHORENEIGHBOR:
			return new EMFIncQuerySemaphoreNeighborChecker(eiqDriver);
		case SWITCHSENSOR:
			return new EMFIncQuerySwitchSensorChecker(eiqDriver);
		case SWITCHSET:
			return new EMFIncQuerySwitchSetChecker(eiqDriver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}

	@Override
	public Collection<M> check() {
		return matches;
	}

	public void setMatches(final Collection<M> matches) {
		this.matches = matches;
	}

	public abstract IncQueryMatcher<M> getMatcher() throws IncQueryException;

}
