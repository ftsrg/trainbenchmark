package hu.bme.mit.trainbenchmark.benchmark.viatra.operations;

import java.util.Optional;

import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.viatra.ActiveRouteMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraDriver;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQuery;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQueryActiveRoute;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQueryConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQueryPosLength;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQueryRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQuerySemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQuerySwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.viatra.queries.ViatraQuerySwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.repair.ViatraTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.repair.ViatraTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.repair.ViatraTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.repair.ViatraTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.repair.ViatraTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.repair.ViatraTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class ViatraModelOperationFactory extends ModelOperationFactory<BasePatternMatch, ViatraDriver> {

	protected ViatraModelOperationFactory() {

	}

	public static ViatraModelOperationFactory create() {
		return new ViatraModelOperationFactory();
	}

	@Override
	public ModelOperation<? extends BasePatternMatch, ViatraDriver> createOperation(
			final RailwayOperation operationEnum, final Optional<String> workspaceDir, final ViatraDriver driver)
					throws Exception {

		switch (operationEnum) {
		// ActiveRoute
		case ACTIVEROUTE: {
			final ViatraQuery<ActiveRouteMatch> query = new ViatraQueryActiveRoute(driver);
			final ModelOperation<ActiveRouteMatch, ViatraDriver> operation = ModelOperation.of(query);
			return operation;
		}
		
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final ViatraQuery<ConnectedSegmentsMatch> query = new ViatraQueryConnectedSegments(driver);
			final ModelOperation<ConnectedSegmentsMatch, ViatraDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			// TODO

		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final ViatraQuery<ConnectedSegmentsMatch> query = new ViatraQueryConnectedSegments(driver);
			final ViatraTransformationRepairConnectedSegments transformation = new ViatraTransformationRepairConnectedSegments(
					driver);
			final ModelOperation<ConnectedSegmentsMatch, ViatraDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final ViatraQuery<PosLengthMatch> query = new ViatraQueryPosLength(driver);
			final ModelOperation<PosLengthMatch, ViatraDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			// TODO
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
			// TODO
		}
		case ROUTESENSOR_REPAIR: {
			final ViatraQuery<RouteSensorMatch> query = new ViatraQueryRouteSensor(driver);
			final ViatraTransformationRepairRouteSensor transformation = new ViatraTransformationRepairRouteSensor(
					driver);
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
			// TODO
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final ViatraQuery<SemaphoreNeighborMatch> query = new ViatraQuerySemaphoreNeighbor(driver);
			final ViatraTransformationRepairSemaphoreNeighbor transformation = new ViatraTransformationRepairSemaphoreNeighbor(
					driver);
			final ModelOperation<SemaphoreNeighborMatch, ViatraDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final ViatraQuery<SwitchMonitoredMatch> query = new ViatraQuerySwitchMonitored(driver);
			final ModelOperation<SwitchMonitoredMatch, ViatraDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			// TODO
		}
		case SWITCHMONITORED_REPAIR: {
			final ViatraQuery<SwitchMonitoredMatch> query = new ViatraQuerySwitchMonitored(driver);
			final ViatraTransformationRepairSwitchMonitored transformation = new ViatraTransformationRepairSwitchMonitored(
					driver);
			final ModelOperation<SwitchMonitoredMatch, ViatraDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final ViatraQuery<SwitchSetMatch> query = new ViatraQuerySwitchSet(driver);
			final ModelOperation<SwitchSetMatch, ViatraDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			// TODO
		}
		case SWITCHSET_REPAIR: {
			final ViatraQuery<SwitchSetMatch> query = new ViatraQuerySwitchSet(driver);
			final ViatraTransformationRepairSwitchSet transformation = new ViatraTransformationRepairSwitchSet(driver);
			final ModelOperation<SwitchSetMatch, ViatraDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}

}
