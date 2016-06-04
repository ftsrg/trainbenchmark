package hu.bme.mit.trainbenchmark.benchmark.emfapi.operations;

import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.EmfTransformation;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EmfApiQuery;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EmfApiQueryConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EmfApiQueryPosLength;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EmfApiQueryRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EmfApiQuerySemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EmfApiQuerySwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EmfApiQuerySwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.emf.EmfDriver;

public class EmfApiModelOperationFactory<TDriver extends EmfDriver> extends ModelOperationFactory<TDriver> {

	@Override
	public ModelOperation<? extends EmfMatch, TDriver> createOperation(
			final RailwayOperation operationEnum, final TDriver driver) {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final EmfApiQuery<EmfConnectedSegmentsMatch, TDriver> query = new EmfApiQueryConnectedSegments<>(driver);
			final ModelOperation<EmfConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			// TODO

//			final EmfApiQuery<, TDriver> query = new EmfApiQueryConnectedSegments<>(driver);
//			final EmfTransformation<, TDriver> transformation = new EmfTransformationInjectConnectedSegments<>(
//					driver);
//			final ModelOperation<, TDriver> operation = ModelOperation.of(query, transformation);
//			return operation;

		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final EmfApiQuery<EmfConnectedSegmentsMatch, TDriver> query = new EmfApiQueryConnectedSegments<>(driver);
			final EmfTransformation<EmfConnectedSegmentsMatch, TDriver> transformation = new EmfTransformationRepairConnectedSegments<>(
					driver);
			final ModelOperation<EmfConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

		// PosLength
		case POSLENGTH: {
			final EmfApiQuery<EmfPosLengthMatch, TDriver> query = new EmfApiQueryPosLength<>(driver);
			final ModelOperation<EmfPosLengthMatch, TDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			// TODO
		}
		case POSLENGTH_REPAIR: {
			final EmfApiQuery<EmfPosLengthMatch, TDriver> query = new EmfApiQueryPosLength<>(driver);
			final EmfTransformation<EmfPosLengthMatch, TDriver> transformation = new EmfTransformationRepairPosLength<>(driver);
			final ModelOperation<EmfPosLengthMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

		// RouteSensor
		case ROUTESENSOR: {
			final EmfApiQuery<EmfRouteSensorMatch, TDriver> query = new EmfApiQueryRouteSensor<>(driver);
			final ModelOperation<EmfRouteSensorMatch, TDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			// TODO
		}
		case ROUTESENSOR_REPAIR: {
			final EmfApiQuery<EmfRouteSensorMatch, TDriver> query = new EmfApiQueryRouteSensor<>(driver);
			final EmfTransformation<EmfRouteSensorMatch, TDriver> transformation = new EmfTransformationRepairRouteSensor<>(driver);
			final ModelOperation<EmfRouteSensorMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

		// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final EmfApiQuery<EmfSemaphoreNeighborMatch, TDriver> query = new EmfApiQuerySemaphoreNeighbor<>(driver);
			final ModelOperation<EmfSemaphoreNeighborMatch, TDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			// TODO
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final EmfApiQuery<EmfSemaphoreNeighborMatch, TDriver> query = new EmfApiQuerySemaphoreNeighbor<>(driver);
			final EmfTransformation<EmfSemaphoreNeighborMatch, TDriver> transformation = new EmfTransformationRepairSemaphoreNeighbor<>(
					driver);
			final ModelOperation<EmfSemaphoreNeighborMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

		// SwitchMonitored
		case SWITCHMONITORED: {
			final EmfApiQuery<EmfSwitchMonitoredMatch, TDriver> query = new EmfApiQuerySwitchMonitored<>(driver);
			final ModelOperation<EmfSwitchMonitoredMatch, TDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			// TODO
		}
		case SWITCHMONITORED_REPAIR: {
			final EmfApiQuery<EmfSwitchMonitoredMatch, TDriver> query = new EmfApiQuerySwitchMonitored<>(driver);
			final EmfTransformation<EmfSwitchMonitoredMatch, TDriver> transformation = new EmfTransformationRepairSwitchMonitored<>(driver);
			final ModelOperation<EmfSwitchMonitoredMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

		// SwitchSet
		case SWITCHSET: {
			final EmfApiQuery<EmfSwitchSetMatch, TDriver> query = new EmfApiQuerySwitchSet<>(driver);
			final ModelOperation<EmfSwitchSetMatch, TDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			// TODO
		}
		case SWITCHSET_REPAIR: {
			final EmfApiQuery<EmfSwitchSetMatch, TDriver> query = new EmfApiQuerySwitchSet<>(driver);
			final EmfTransformation<EmfSwitchSetMatch, TDriver> transformation = new EmfTransformationRepairSwitchSet<>(driver);
			final ModelOperation<EmfSwitchSetMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}

}
