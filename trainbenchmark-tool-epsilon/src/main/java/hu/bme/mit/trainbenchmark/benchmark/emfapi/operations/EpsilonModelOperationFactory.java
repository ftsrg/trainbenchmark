package hu.bme.mit.trainbenchmark.benchmark.emfapi.operations;

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.EmfTransformation;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EmfTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EmfTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EmfTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EmfTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EmfTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EmfTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query.EmfApiQuery;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query.EmfApiQueryConnectedSegmentsInject;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query.EmfApiQueryPosLengthInject;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query.EmfApiQueryRouteSensorInject;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query.EmfApiQuerySemaphoreNeighborInject;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query.EmfApiQuerySwitchMonitoredInject;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query.EmfApiQuerySwitchSetInject;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EmfApiQueryConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EmfApiQueryPosLength;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EmfApiQueryRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EmfApiQuerySemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EmfApiQuerySwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases.EmfApiQuerySwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class EpsilonModelOperationFactory<TDriver extends EmfDriver> extends ModelOperationFactory<EmfMatch, TDriver> {

	@Override
	public ModelOperation<? extends EmfMatch, TDriver> createOperation(final RailwayOperation operationEnum, final String workspaceDir,
			final TDriver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final EmfApiQuery<EmfConnectedSegmentsMatch, TDriver> query = new EmfApiQueryConnectedSegments<>(driver);
			final ModelOperation<EmfConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			final EmfApiQuery<EmfConnectedSegmentsInjectMatch, TDriver> query = new EmfApiQueryConnectedSegmentsInject<>(driver);
			final EmfTransformation<EmfConnectedSegmentsInjectMatch, TDriver> transformation = new EmfTransformationInjectConnectedSegments<>(driver);
			final ModelOperation<EmfConnectedSegmentsInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;

		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final EmfApiQuery<EmfConnectedSegmentsMatch, TDriver> query = new EmfApiQueryConnectedSegments<>(driver);
			final EmfTransformation<EmfConnectedSegmentsMatch, TDriver> transformation = new EmfTransformationRepairConnectedSegments<>(driver);
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
			final EmfApiQuery<EmfPosLengthInjectMatch, TDriver> query = new EmfApiQueryPosLengthInject<>(driver);
			final EmfTransformation<EmfPosLengthInjectMatch, TDriver> transformation = new EmfTransformationInjectPosLength<>(driver);
			final ModelOperation<EmfPosLengthInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
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
			final EmfApiQuery<EmfRouteSensorInjectMatch, TDriver> query = new EmfApiQueryRouteSensorInject<>(driver);
			final EmfTransformation<EmfRouteSensorInjectMatch, TDriver> transformation = new EmfTransformationInjectRouteSensor<>(driver);
			final ModelOperation<EmfRouteSensorInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
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
			final EmfApiQuery<EmfSemaphoreNeighborInjectMatch, TDriver> query = new EmfApiQuerySemaphoreNeighborInject<>(driver);
			final EmfTransformation<EmfSemaphoreNeighborInjectMatch, TDriver> transformation = new EmfTransformationInjectSemaphoreNeighbor<>(driver);
			final ModelOperation<EmfSemaphoreNeighborInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final EmfApiQuery<EmfSemaphoreNeighborMatch, TDriver> query = new EmfApiQuerySemaphoreNeighbor<>(driver);
			final EmfTransformation<EmfSemaphoreNeighborMatch, TDriver> transformation = new EmfTransformationRepairSemaphoreNeighbor<>(driver);
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
			final EmfApiQuery<EmfSwitchMonitoredInjectMatch, TDriver> query = new EmfApiQuerySwitchMonitoredInject<>(driver);
			final EmfTransformation<EmfSwitchMonitoredInjectMatch, TDriver> transformation = new EmfTransformationInjectSwitchMonitored<>(driver);
			final ModelOperation<EmfSwitchMonitoredInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
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
			final EmfApiQuery<EmfSwitchSetInjectMatch, TDriver> query = new EmfApiQuerySwitchSetInject<>(driver);
			final EmfTransformation<EmfSwitchSetInjectMatch, TDriver> transformation = new EmfTransformationInjectSwitchSet<>(driver);
			final ModelOperation<EmfSwitchSetInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHSET_REPAIR: {
			final EmfApiQuery<EmfSwitchSetMatch, TDriver> query = new EmfApiQuerySwitchSet<>(driver);
			final EmfTransformation<EmfSwitchSetMatch, TDriver> transformation = new EmfTransformationRepairSwitchSet<>(driver);
			final ModelOperation<EmfSwitchSetMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		default:
			break;
		}
		throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
	}

}
