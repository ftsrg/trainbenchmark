package hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaMatch;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

import java.util.Collection;

public abstract class JavaChecker extends Checker<JavaMatch> {

	protected Collection<JavaMatch> matches;
	protected final EMFDriver emfDriver;

	public JavaChecker(final EMFDriver emfDriver) {
		this.emfDriver = emfDriver;
	}

	public static JavaChecker createChecker(final EMFDriver driver, final Query query) {
		switch (query) {
		case POSLENGTH:
			return new JavaPosLengthChecker(driver);
		case ROUTESENSOR:
			return new JavaRouteSensorChecker(driver);
		case SEMAPHORENEIGHBOR:
			return new JavaSemaphoreNeighborChecker(driver);
		case SWITCHSENSOR:
			return new JavaSwitchSensorChecker(driver);
		case SWITCHSET:
			return new JavaSwitchSetChecker(driver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}

}
