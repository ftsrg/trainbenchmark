package hu.bme.mit.trainbenchmark.benchmark.ingraph.operations;

import hu.bme.mit.ire.TransactionFactory;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.driver.IngraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphActiveRouteMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.match.IngraphSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.queries.IngraphQuery;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.IngraphTransformation;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.inject.IngraphTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.inject.IngraphTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.inject.IngraphTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.inject.IngraphTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.inject.IngraphTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.inject.IngraphTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.repair.IngraphTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.repair.IngraphTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.repair.IngraphTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.repair.IngraphTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.repair.IngraphTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.ingraph.transformations.repair.IngraphTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class IngraphModelOperationFactory extends ModelOperationFactory<IngraphMatch, IngraphDriver> {

	protected final TransactionFactory transactionFactory;

	public IngraphModelOperationFactory(final TransactionFactory transactionFactory) {
		this.transactionFactory = transactionFactory;
	}

	@Override
	public ModelOperation<? extends IngraphMatch, IngraphDriver> createOperation(final RailwayOperation operationEnum, final String workspaceDir,
			final IngraphDriver driver) throws Exception {
		switch (operationEnum) {
		// ActiveRoute
		case ACTIVEROUTE: {
			final IngraphQuery<IngraphActiveRouteMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.ACTIVEROUTE, transactionFactory);
			final ModelOperation<IngraphActiveRouteMatch, IngraphDriver> operation = ModelOperation.of(query);
			return operation;
		}
			// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final IngraphQuery<IngraphConnectedSegmentsMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS, transactionFactory);
			final ModelOperation<IngraphConnectedSegmentsMatch, IngraphDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			final IngraphQuery<IngraphConnectedSegmentsInjectMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS_INJECT, transactionFactory);
			final IngraphTransformation<IngraphConnectedSegmentsInjectMatch> transformation = new IngraphTransformationInjectConnectedSegments(driver);
			final ModelOperation<IngraphConnectedSegmentsInjectMatch, IngraphDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final IngraphQuery<IngraphConnectedSegmentsMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS, transactionFactory);
			final IngraphTransformation<IngraphConnectedSegmentsMatch> transformation = new IngraphTransformationRepairConnectedSegments(driver);
			final ModelOperation<IngraphConnectedSegmentsMatch, IngraphDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final IngraphQuery<IngraphPosLengthMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.POSLENGTH, transactionFactory);
			final ModelOperation<IngraphPosLengthMatch, IngraphDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			final IngraphQuery<IngraphPosLengthInjectMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.POSLENGTH_INJECT, transactionFactory);
			final IngraphTransformation<IngraphPosLengthInjectMatch> transformation = new IngraphTransformationInjectPosLength(driver);
			final ModelOperation<IngraphPosLengthInjectMatch, IngraphDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case POSLENGTH_REPAIR: {
			final IngraphQuery<IngraphPosLengthMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.POSLENGTH, transactionFactory);
			final IngraphTransformation<IngraphPosLengthMatch> transformation = new IngraphTransformationRepairPosLength(driver);
			final ModelOperation<IngraphPosLengthMatch, IngraphDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// RouteSensor
		case ROUTESENSOR: {
			final IngraphQuery<IngraphRouteSensorMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.ROUTESENSOR, transactionFactory);
			final ModelOperation<IngraphRouteSensorMatch, IngraphDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			final IngraphQuery<IngraphRouteSensorInjectMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.ROUTESENSOR_INJECT, transactionFactory);
			final IngraphTransformation<IngraphRouteSensorInjectMatch> transformation = new IngraphTransformationInjectRouteSensor(driver);
			final ModelOperation<IngraphRouteSensorInjectMatch, IngraphDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case ROUTESENSOR_REPAIR: {
			final IngraphQuery<IngraphRouteSensorMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.ROUTESENSOR, transactionFactory);
			final IngraphTransformation<IngraphRouteSensorMatch> transformation = new IngraphTransformationRepairRouteSensor(driver);
			final ModelOperation<IngraphRouteSensorMatch, IngraphDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final IngraphQuery<IngraphSemaphoreNeighborMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR, transactionFactory);
			final ModelOperation<IngraphSemaphoreNeighborMatch, IngraphDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			final IngraphQuery<IngraphSemaphoreNeighborInjectMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR_INJECT, transactionFactory);
			final IngraphTransformation<IngraphSemaphoreNeighborInjectMatch> transformation = new IngraphTransformationInjectSemaphoreNeighbor(driver);
			final ModelOperation<IngraphSemaphoreNeighborInjectMatch, IngraphDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final IngraphQuery<IngraphSemaphoreNeighborMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR, transactionFactory);
			final IngraphTransformation<IngraphSemaphoreNeighborMatch> transformation = new IngraphTransformationRepairSemaphoreNeighbor(driver);
			final ModelOperation<IngraphSemaphoreNeighborMatch, IngraphDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final IngraphQuery<IngraphSwitchMonitoredMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.SWITCHMONITORED, transactionFactory);
			final ModelOperation<IngraphSwitchMonitoredMatch, IngraphDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			final IngraphQuery<IngraphSwitchMonitoredInjectMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.SWITCHMONITORED_INJECT, transactionFactory);
			final IngraphTransformation<IngraphSwitchMonitoredInjectMatch> transformation = new IngraphTransformationInjectSwitchMonitored(driver);
			final ModelOperation<IngraphSwitchMonitoredInjectMatch, IngraphDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHMONITORED_REPAIR: {
			final IngraphQuery<IngraphSwitchMonitoredMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.SWITCHMONITORED, transactionFactory);
			final IngraphTransformation<IngraphSwitchMonitoredMatch> transformation = new IngraphTransformationRepairSwitchMonitored(driver);
			final ModelOperation<IngraphSwitchMonitoredMatch, IngraphDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final IngraphQuery<IngraphSwitchSetMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.SWITCHSET, transactionFactory);
			final ModelOperation<IngraphSwitchSetMatch, IngraphDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			final IngraphQuery<IngraphSwitchSetInjectMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.SWITCHSET_INJECT, transactionFactory);
			final IngraphTransformation<IngraphSwitchSetInjectMatch> transformation = new IngraphTransformationInjectSwitchSet(driver);
			final ModelOperation<IngraphSwitchSetInjectMatch, IngraphDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHSET_REPAIR: {
			final IngraphQuery<IngraphSwitchSetMatch> query = IngraphQuery.create(driver, workspaceDir, RailwayQuery.SWITCHSET, transactionFactory);
			final IngraphTransformation<IngraphSwitchSetMatch> transformation = new IngraphTransformationRepairSwitchSet(driver);
			final ModelOperation<IngraphSwitchSetMatch, IngraphDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}
}
