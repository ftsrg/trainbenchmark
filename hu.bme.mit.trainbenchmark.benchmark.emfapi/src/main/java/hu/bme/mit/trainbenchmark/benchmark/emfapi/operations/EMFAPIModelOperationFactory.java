package hu.bme.mit.trainbenchmark.benchmark.emfapi.operations;

import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EMFMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EMFPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.EMFTransformation;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EMFTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EMFAPIModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EMFAPIPosLengthQuery;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;

public class EMFAPIModelOperationFactory {

	public static <TDriver extends EMFDriver> ModelOperation<? extends EMFMatch, TDriver> createOperation(
			final RailwayOperation operationEnum, final TDriver driver) {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final EMFAPIModelQuery<EMFPosLengthMatch, TDriver> query = new EMFAPIPosLengthQuery<>(driver);
			final ModelOperation<EMFPosLengthMatch, TDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			// TODO
		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final EMFAPIModelQuery<EMFPosLengthMatch, TDriver> query = new EMFAPIPosLengthQuery<>(driver);
			final EMFTransformation<EMFPosLengthMatch, TDriver> transformation = new EMFTransformationRepairPosLength<>(driver);
			final ModelOperation<EMFPosLengthMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		// PosLength
		case POSLENGTH: {
			
		}
		case POSLENGTH_INJECT: {

		}
		case POSLENGTH_REPAIR: {

		}
		// RouteSensor
		case ROUTESENSOR: {

		}
		case ROUTESENSOR_INJECT: {

		}
		case ROUTESENSOR_REPAIR: {

		}

		// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {

		}
		case SEMAPHORENEIGHBOR_INJECT: {

		}
		case SEMAPHORENEIGHBOR_REPAIR: {

		}

		// SwitchSensor
		case SWITCHSENSOR: {

		}
		case SWITCHSENSOR_INJECT: {

		}
		case SWITCHSENSOR_REPAIR: {

		}

		// SwitchSet
		case SWITCHSET: {

		}
		case SWITCHSET_INJECT: {

		}
		case SWITCHSET_REPAIR: {

		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}

}
