package hu.bme.mit.trainbenchmark.emf.transformation.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.TransformationAction;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

public abstract class EMFUserTransformation extends TransformationAction {

	protected EMFDriver driver;

	public EMFUserTransformation(final EMFDriver driver) {
		super();
		this.driver = driver;
	}

	public static EMFUserTransformation createTransformation(final EMFDriver driver, final Query query) {
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
