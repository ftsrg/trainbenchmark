package hu.bme.mit.trainbenchmark.benchmark.java.transformation.repair;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFUserTransformationPosLength;

public abstract class JavaTransformation<M> extends Transformation<M> {

	public static Transformation<?> createTransformation(final EMFDriver driver, final Query query, final Scenario scenario) {
		System.out.println();

		switch (scenario) {
		case REPAIR:
			switch (query) {
			case POSLENGTH:
				return new JavaTransformationRepairPosLength();
			case ROUTESENSOR:
				return new JavaTransformationRepairRouteSensor();
			case SEMAPHORENEIGHBOR:
				return new JavaTransformationRepairSemaphoreNeighbor();
			case SWITCHSENSOR:
				return new JavaTransformationRepairSwitchSensor();
			case SWITCHSET:
				return new JavaTransformationRepairSwitchSet();
			default:
				break;
			}
		case USER:
			switch (query) {
			case POSLENGTH:
				return new EMFUserTransformationPosLength(driver);
			case ROUTESENSOR:
				return new EMFUserTransformationPosLength(driver);
			case SEMAPHORENEIGHBOR:
				return new EMFUserTransformationPosLength(driver);
			case SWITCHSENSOR:
				return new EMFUserTransformationPosLength(driver);
			case SWITCHSET:
				return new EMFUserTransformationPosLength(driver);
			default:
				break;
			}
		default:
			break;
		}
		return null;
		// throw new UnsupportedOperationException("query: " + query.toString() + ", scenario: " + scenario);
	}
}
