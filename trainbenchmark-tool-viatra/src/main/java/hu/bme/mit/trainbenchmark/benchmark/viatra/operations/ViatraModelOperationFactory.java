package hu.bme.mit.trainbenchmark.benchmark.viatra.operations;

import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.viatra.ConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.PosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.RouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraDriver;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQuery;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQueryConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQueryConnectedSegmentsInject;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQueryPosLength;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQueryPosLengthInject;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQueryRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQueryRouteSensorInject;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQuerySemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQuerySemaphoreNeighborInject;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQuerySwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQuerySwitchMonitoredInject;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQuerySwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQuerySwitchSetInject;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.ViatraTransformation;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.inject.ViatraTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.inject.ViatraTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.inject.ViatraTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.inject.ViatraTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.inject.ViatraTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.inject.ViatraTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.repair.ViatraTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.repair.ViatraTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.repair.ViatraTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.repair.ViatraTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.repair.ViatraTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.repair.ViatraTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class ViatraModelOperationFactory extends ModelOperationFactory<BasePatternMatch, ViatraDriver> {

	@Override
	public ModelOperation<? extends BasePatternMatch, ViatraDriver> createOperation(final RailwayOperation operationEnum, final String workspaceDir,
			final ViatraDriver driver) throws Exception {

		switch (operationEnum) {
			// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final ViatraQuery<ConnectedSegmentsMatch> query = new ViatraQueryConnectedSegments(driver);
			final ModelOperation<ConnectedSegmentsMatch, ViatraDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			final ViatraQuery<ConnectedSegmentsInjectMatch> query = new ViatraQueryConnectedSegmentsInject(driver);
			final ViatraTransformation<ConnectedSegmentsInjectMatch> transformation = new ViatraTransformationInjectConnectedSegments(driver);
			final ModelOperation<ConnectedSegmentsInjectMatch, ViatraDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final ViatraQuery<ConnectedSegmentsMatch> query = new ViatraQueryConnectedSegments(driver);
			final ViatraTransformation<ConnectedSegmentsMatch> transformation = new ViatraTransformationRepairConnectedSegments(driver);
			final ModelOperation<ConnectedSegmentsMatch, ViatraDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final ViatraQuery<PosLengthMatch> query = new ViatraQueryPosLength(driver);
			final ModelOperation<PosLengthMatch, ViatraDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			final ViatraQuery<PosLengthInjectMatch> query = new ViatraQueryPosLengthInject(driver);
			final ViatraTransformation<PosLengthInjectMatch> transformation = new ViatraTransformationInjectPosLength(driver);
			final ModelOperation<PosLengthInjectMatch, ViatraDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case POSLENGTH_REPAIR: {
			final ViatraQuery<PosLengthMatch> query = new ViatraQueryPosLength(driver);
			final ViatraTransformationRepairPosLength transformation = new ViatraTransformationRepairPosLength(driver);
			final ModelOperation<PosLengthMatch, ViatraDriver> operation = ModelOperation.of(query, transformation);
			return operation;

		}

			// RouteSensor
		case ROUTESENSOR: {
			final ViatraQuery<RouteSensorMatch> query = new ViatraQueryRouteSensor(driver);
			final ModelOperation<RouteSensorMatch, ViatraDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			final ViatraQuery<RouteSensorInjectMatch> query = new ViatraQueryRouteSensorInject(driver);
			final ViatraTransformation<RouteSensorInjectMatch> transformation = new ViatraTransformationInjectRouteSensor(driver);
			final ModelOperation<RouteSensorInjectMatch, ViatraDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case ROUTESENSOR_REPAIR: {
			final ViatraQuery<RouteSensorMatch> query = new ViatraQueryRouteSensor(driver);
			final ViatraTransformation<RouteSensorMatch> transformation = new ViatraTransformationRepairRouteSensor(driver);
			final ModelOperation<RouteSensorMatch, ViatraDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final ViatraQuery<SemaphoreNeighborMatch> query = new ViatraQuerySemaphoreNeighbor(driver);
			final ModelOperation<SemaphoreNeighborMatch, ViatraDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			final ViatraQuery<SemaphoreNeighborInjectMatch> query = new ViatraQuerySemaphoreNeighborInject(driver);
			final ViatraTransformation<SemaphoreNeighborInjectMatch> transformation = new ViatraTransformationInjectSemaphoreNeighbor(driver);
			final ModelOperation<SemaphoreNeighborInjectMatch, ViatraDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final ViatraQuery<SemaphoreNeighborMatch> query = new ViatraQuerySemaphoreNeighbor(driver);
			final ViatraTransformation<SemaphoreNeighborMatch> transformation = new ViatraTransformationRepairSemaphoreNeighbor(driver);
			final ModelOperation<SemaphoreNeighborMatch, ViatraDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final ViatraQuery<SwitchMonitoredMatch> query = new ViatraQuerySwitchMonitored(driver);
			final ModelOperation<SwitchMonitoredMatch, ViatraDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			final ViatraQuery<SwitchMonitoredInjectMatch> query = new ViatraQuerySwitchMonitoredInject(driver);
			final ViatraTransformation<SwitchMonitoredInjectMatch> transformation = new ViatraTransformationInjectSwitchMonitored(driver);
			final ModelOperation<SwitchMonitoredInjectMatch, ViatraDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHMONITORED_REPAIR: {
			final ViatraQuery<SwitchMonitoredMatch> query = new ViatraQuerySwitchMonitored(driver);
			final ViatraTransformation<SwitchMonitoredMatch> transformation = new ViatraTransformationRepairSwitchMonitored(driver);
			final ModelOperation<SwitchMonitoredMatch, ViatraDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final ViatraQuery<SwitchSetMatch> query = new ViatraQuerySwitchSet(driver);
			final ModelOperation<SwitchSetMatch, ViatraDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			final ViatraQuery<SwitchSetInjectMatch> query = new ViatraQuerySwitchSetInject(driver);
			final ViatraTransformation<SwitchSetInjectMatch> transformation = new ViatraTransformationInjectSwitchSet(driver);
			final ModelOperation<SwitchSetInjectMatch, ViatraDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHSET_REPAIR: {
			final ViatraQuery<SwitchSetMatch> query = new ViatraQuerySwitchSet(driver);
			final ViatraTransformation<SwitchSetMatch> transformation = new ViatraTransformationRepairSwitchSet(driver);
			final ModelOperation<SwitchSetMatch, ViatraDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}

}
