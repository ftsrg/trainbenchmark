package hu.bme.mit.trainbenchmark.emf.transformation.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;

public abstract class EMFUserTransformation extends Transformation<RailwayElement> {

	protected EMFDriver driver;

	public EMFUserTransformation(final EMFDriver driver) {
		super();
		this.driver = driver;
	}

	public static EMFUserTransformation newInstance(final EMFDriver driver, final Query query) {
		switch (query) {
		case POSLENGTH:
			return new EMFUserTransformationPosLength(driver);
		case ROUTESENSOR:
			return new EMFUserTransformationRouteSensor(driver);
		case SEMAPHORENEIGHBOR:
			return new EMFUserTransformationSemaphoreNeighbor(driver);
		case SWITCHSENSOR:
			return new EMFUserTransformationSwitchSensor(driver);
		case SWITCHSET:
			return new EMFUserTransformationSwitchSet(driver);
		default:
			throw new UnsupportedOperationException("Query " + query + " not supported");
		}
	}

}
