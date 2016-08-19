package hu.bme.mit.trainbenchmark.benchmark.iqdcore.operations;

import java.util.Optional;

import hu.bme.mit.incqueryds.TransactionFactory;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IqdCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreActiveRouteMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCorePosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCorePosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IqdCoreSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.queries.IqdCoreQuery;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.IqdCoreTransformation;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.inject.IqdCoreTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.inject.IqdCoreTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.inject.IqdCoreTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.inject.IqdCoreTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.inject.IqdCoreTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.inject.IqdCoreTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IqdCoreTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IqdCoreTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IqdCoreTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IqdCoreTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IqdCoreTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.IqdCoreTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class IqdCoreModelOperationFactory extends ModelOperationFactory<IqdCoreMatch, IqdCoreDriver> {

	protected final TransactionFactory transactionFactory;
	protected final String RELATIVE_QUERY_DIR = "trainbenchmark-tool-iqdcore/src/main/resources/";

	public IqdCoreModelOperationFactory(final TransactionFactory transactionFactory) {
		this.transactionFactory = transactionFactory;
	}

	@Override
	public ModelOperation<? extends IqdCoreMatch, IqdCoreDriver> createOperation(final RailwayOperation operationEnum, final Optional<String> workspacePath,
			final IqdCoreDriver driver) throws Exception {
		final String queryDirectory = workspacePath.get() + RELATIVE_QUERY_DIR;

		switch (operationEnum) {
		// ActiveRoute
		case ACTIVEROUTE: {
			final IqdCoreQuery<IqdCoreActiveRouteMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.ACTIVEROUTE, transactionFactory);
			final ModelOperation<IqdCoreActiveRouteMatch, IqdCoreDriver> operation = ModelOperation.of(query);
			return operation;
		}
			// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final IqdCoreQuery<IqdCoreConnectedSegmentsMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.CONNECTEDSEGMENTS, transactionFactory);
			final ModelOperation<IqdCoreConnectedSegmentsMatch, IqdCoreDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			final IqdCoreQuery<IqdCoreConnectedSegmentsInjectMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.CONNECTEDSEGMENTS_INJECT, transactionFactory);
			final IqdCoreTransformation<IqdCoreConnectedSegmentsInjectMatch> transformation = new IqdCoreTransformationInjectConnectedSegments(driver);
			final ModelOperation<IqdCoreConnectedSegmentsInjectMatch, IqdCoreDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final IqdCoreQuery<IqdCoreConnectedSegmentsMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.CONNECTEDSEGMENTS, transactionFactory);
			final IqdCoreTransformation<IqdCoreConnectedSegmentsMatch> transformation = new IqdCoreTransformationRepairConnectedSegments(driver);
			final ModelOperation<IqdCoreConnectedSegmentsMatch, IqdCoreDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final IqdCoreQuery<IqdCorePosLengthMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.POSLENGTH, transactionFactory);
			final ModelOperation<IqdCorePosLengthMatch, IqdCoreDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			final IqdCoreQuery<IqdCorePosLengthInjectMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.POSLENGTH_INJECT, transactionFactory);
			final IqdCoreTransformation<IqdCorePosLengthInjectMatch> transformation = new IqdCoreTransformationInjectPosLength(driver);
			final ModelOperation<IqdCorePosLengthInjectMatch, IqdCoreDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case POSLENGTH_REPAIR: {
			final IqdCoreQuery<IqdCorePosLengthMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.POSLENGTH, transactionFactory);
			final IqdCoreTransformation<IqdCorePosLengthMatch> transformation = new IqdCoreTransformationRepairPosLength(driver);
			final ModelOperation<IqdCorePosLengthMatch, IqdCoreDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// RouteSensor
		case ROUTESENSOR: {
			final IqdCoreQuery<IqdCoreRouteSensorMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.ROUTESENSOR, transactionFactory);
			final ModelOperation<IqdCoreRouteSensorMatch, IqdCoreDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			final IqdCoreQuery<IqdCoreRouteSensorInjectMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.ROUTESENSOR_INJECT, transactionFactory);
			final IqdCoreTransformation<IqdCoreRouteSensorInjectMatch> transformation = new IqdCoreTransformationInjectRouteSensor(driver);
			final ModelOperation<IqdCoreRouteSensorInjectMatch, IqdCoreDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case ROUTESENSOR_REPAIR: {
			final IqdCoreQuery<IqdCoreRouteSensorMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.ROUTESENSOR, transactionFactory);
			final IqdCoreTransformation<IqdCoreRouteSensorMatch> transformation = new IqdCoreTransformationRepairRouteSensor(driver);
			final ModelOperation<IqdCoreRouteSensorMatch, IqdCoreDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final IqdCoreQuery<IqdCoreSemaphoreNeighborMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.SEMAPHORENEIGHBOR, transactionFactory);
			final ModelOperation<IqdCoreSemaphoreNeighborMatch, IqdCoreDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			final IqdCoreQuery<IqdCoreSemaphoreNeighborInjectMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.SEMAPHORENEIGHBOR_INJECT, transactionFactory);
			final IqdCoreTransformation<IqdCoreSemaphoreNeighborInjectMatch> transformation = new IqdCoreTransformationInjectSemaphoreNeighbor(driver);
			final ModelOperation<IqdCoreSemaphoreNeighborInjectMatch, IqdCoreDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final IqdCoreQuery<IqdCoreSemaphoreNeighborMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.SEMAPHORENEIGHBOR, transactionFactory);
			final IqdCoreTransformation<IqdCoreSemaphoreNeighborMatch> transformation = new IqdCoreTransformationRepairSemaphoreNeighbor(driver);
			final ModelOperation<IqdCoreSemaphoreNeighborMatch, IqdCoreDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final IqdCoreQuery<IqdCoreSwitchMonitoredMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.SWITCHMONITORED, transactionFactory);
			final ModelOperation<IqdCoreSwitchMonitoredMatch, IqdCoreDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			final IqdCoreQuery<IqdCoreSwitchMonitoredInjectMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.SWITCHMONITORED_INJECT, transactionFactory);
			final IqdCoreTransformation<IqdCoreSwitchMonitoredInjectMatch> transformation = new IqdCoreTransformationInjectSwitchMonitored(driver);
			final ModelOperation<IqdCoreSwitchMonitoredInjectMatch, IqdCoreDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHMONITORED_REPAIR: {
			final IqdCoreQuery<IqdCoreSwitchMonitoredMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.SWITCHMONITORED, transactionFactory);
			final IqdCoreTransformation<IqdCoreSwitchMonitoredMatch> transformation = new IqdCoreTransformationRepairSwitchMonitored(driver);
			final ModelOperation<IqdCoreSwitchMonitoredMatch, IqdCoreDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final IqdCoreQuery<IqdCoreSwitchSetMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.SWITCHSET, transactionFactory);
			final ModelOperation<IqdCoreSwitchSetMatch, IqdCoreDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			final IqdCoreQuery<IqdCoreSwitchSetInjectMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.SWITCHSET_INJECT, transactionFactory);
			final IqdCoreTransformation<IqdCoreSwitchSetInjectMatch> transformation = new IqdCoreTransformationInjectSwitchSet(driver);
			final ModelOperation<IqdCoreSwitchSetInjectMatch, IqdCoreDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHSET_REPAIR: {
			final IqdCoreQuery<IqdCoreSwitchSetMatch> query = IqdCoreQuery.create(driver, queryDirectory, RailwayQuery.SWITCHSET, transactionFactory);
			final IqdCoreTransformation<IqdCoreSwitchSetMatch> transformation = new IqdCoreTransformationRepairSwitchSet(driver);
			final ModelOperation<IqdCoreSwitchSetMatch, IqdCoreDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}
}
