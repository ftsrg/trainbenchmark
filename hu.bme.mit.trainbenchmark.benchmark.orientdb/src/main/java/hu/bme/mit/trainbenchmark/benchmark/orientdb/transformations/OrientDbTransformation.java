package hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair.OrientDbTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair.OrientDbTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair.OrientDbTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair.OrientDbTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair.OrientDbTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.repair.OrientDbTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.user.OrientDbTransformationUserConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.user.OrientDbTransformationUserPosLength;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.user.OrientDbTransformationUserRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.user.OrientDbTransformationUserSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.user.OrientDbTransformationUserSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.transformations.user.OrientDbTransformationUserSwitchSet;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public abstract class OrientDbTransformation<M> extends Transformation<M> {

	protected OrientDbDriver orientDriver;

	protected OrientDbTransformation(final OrientDbDriver orientDriver) {
		this.orientDriver = orientDriver;
	}

	public static Transformation<?> newInstance(final OrientDbDriver orientDriver, final Query query, final Scenario scenario) {
		switch (scenario) {
		case REPAIR:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new OrientDbTransformationRepairConnectedSegments(orientDriver);
			case POSLENGTH:
				return new OrientDbTransformationRepairPosLength(orientDriver);
			case ROUTESENSOR:
				return new OrientDbTransformationRepairRouteSensor(orientDriver);
			case SEMAPHORENEIGHBOR:
				return new OrientDbTransformationRepairSemaphoreNeighbor(orientDriver);
			case SWITCHSENSOR:
				return new OrientDbTransformationRepairSwitchSensor(orientDriver);
			case SWITCHSET:
				return new OrientDbTransformationRepairSwitchSet(orientDriver);
			default:
				break;
			}
		case USER:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return new OrientDbTransformationUserConnectedSegments(orientDriver);
			case POSLENGTH:
				return new OrientDbTransformationUserPosLength(orientDriver);
			case ROUTESENSOR:
				return new OrientDbTransformationUserRouteSensor(orientDriver);
			case SEMAPHORENEIGHBOR:
				return new OrientDbTransformationUserSemaphoreNeighbor(orientDriver);
			case SWITCHSENSOR:
				return new OrientDbTransformationUserSwitchSensor(orientDriver);
			case SWITCHSET:
				return new OrientDbTransformationUserSwitchSet(orientDriver);
			default:
				break;
			}
		default:
			break;
		}
		throw new UnsupportedOperationException("Query: " + query.toString() + ", scenario: " + scenario);
	}
	
}
