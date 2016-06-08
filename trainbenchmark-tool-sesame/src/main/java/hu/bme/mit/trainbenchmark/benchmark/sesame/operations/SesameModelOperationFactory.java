package hu.bme.mit.trainbenchmark.benchmark.sesame.operations;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class SesameModelOperationFactory extends ModelOperationFactory<SesameMatch, SesameDriver> {

	protected SesameModelOperationFactory() {
		
	}
	
	public static SesameModelOperationFactory create() {		
		return new SesameModelOperationFactory();
	}
	
	@Override
	public ModelOperation<? extends SesameMatch, SesameDriver> createOperation(
			final RailwayOperation operationEnum, final SesameDriver driver) {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {

		}
		case CONNECTEDSEGMENTS_INJECT: {
			// TODO



		}
		case CONNECTEDSEGMENTS_REPAIR: {

		}

		// PosLength
		case POSLENGTH: {
		}
		case POSLENGTH_INJECT: {
			// TODO
		}
		case POSLENGTH_REPAIR: {

		}

		// RouteSensor
		case ROUTESENSOR: {

		}
		case ROUTESENSOR_INJECT: {
			// TODO
		}
		case ROUTESENSOR_REPAIR: {

		}

		// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {

		}
		case SEMAPHORENEIGHBOR_INJECT: {
			// TODO
		}
		case SEMAPHORENEIGHBOR_REPAIR: {

		}

		// SwitchMonitored
		case SWITCHMONITORED: {

		}
		case SWITCHMONITORED_INJECT: {
			// TODO
		}
		case SWITCHMONITORED_REPAIR: {

		}

		// SwitchSet
		case SWITCHSET: {

		}
		case SWITCHSET_INJECT: {
			// TODO
		}
		case SWITCHSET_REPAIR: {

		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}

}
