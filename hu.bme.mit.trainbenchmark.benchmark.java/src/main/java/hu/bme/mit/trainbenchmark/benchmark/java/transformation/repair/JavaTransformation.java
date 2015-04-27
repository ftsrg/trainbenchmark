package hu.bme.mit.trainbenchmark.benchmark.java.transformation.repair;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFUserTransformationPosLength;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFUserTransformationRouteSensor;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFUserTransformationSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFUserTransformationSwitchSensor;
import hu.bme.mit.trainbenchmark.emf.transformation.user.EMFUserTransformationSwitchSet;

public abstract class JavaTransformation<M> extends Transformation<M> {

	public static Transformation<?> createTransformation(final EMFDriver driver, final Query query, final Scenario scenario) {
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
				return new EMFUserTransformationRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return new EMFUserTransformationSemaphoreNeighbor(driver);
			case SWITCHSENSOR:
				return new EMFUserTransformationSwitchSensor(driver);
			case SWITCHSET:
				return new EMFUserTransformationSwitchSet(driver);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("query: " + query.toString() + ", scenario: " + scenario);
	}
}
