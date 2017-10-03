package hu.bme.mit.trainbenchmark.benchmark.orientdb.operations;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.driver.OrientDbDriver;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbMatch;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.matches.OrientDbPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.orientdb.queries.OrientDbQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class OrientDbModelOperationFactory extends ModelOperationFactory<OrientDbMatch, OrientDbDriver> {

	@Override
	public ModelOperation<? extends OrientDbMatch, OrientDbDriver> createOperation(final RailwayOperation operationEnum, final String workspaceDir,
			final OrientDbDriver driver) throws Exception {
		switch (operationEnum) {
//			// ConnectedSegments
//		case CONNECTEDSEGMENTS: {
//			final ModelQuery<OrientDbConnectedSegmentsMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
//			final ModelOperation<OrientDbConnectedSegmentsMatch, OrientDbDriver> operation = ModelOperation.of(query);
//			return operation;
//		}
//		case CONNECTEDSEGMENTS_INJECT: {
//			final ModelQuery<OrientDbConnectedSegmentsInjectMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir,
//					RailwayQuery.CONNECTEDSEGMENTS_INJECT);
//			final OrientDbCypherTransformation<OrientDbConnectedSegmentsInjectMatch> transformation = new OrientDbCypherTransformationInjectConnectedSegments(driver,
//					workspaceDir);
//			final ModelOperation<OrientDbConnectedSegmentsInjectMatch, OrientDbDriver> operation = ModelOperation.of(query, transformation);
//			return operation;
//		}
//		case CONNECTEDSEGMENTS_REPAIR: {
//			final ModelQuery<OrientDbConnectedSegmentsMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
//			final OrientDbCypherTransformation<OrientDbConnectedSegmentsMatch> transformation = new OrientDbCypherTransformationRepairConnectedSegments(driver,
//					workspaceDir);
//			final ModelOperation<OrientDbConnectedSegmentsMatch, OrientDbDriver> operation = ModelOperation.of(query, transformation);
//			return operation;
//		}

			// PosLength
		case POSLENGTH: {
			final ModelQuery<OrientDbPosLengthMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH);
			final ModelOperation<OrientDbPosLengthMatch, OrientDbDriver> operation = ModelOperation.of(query);
			return operation;
		}
//		case POSLENGTH_INJECT: {
//			final ModelQuery<OrientDbPosLengthInjectMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH_INJECT);
//			final OrientDbCypherTransformation<OrientDbPosLengthInjectMatch> transformation = new OrientDbCypherTransformationInjectPosLength(driver, workspaceDir);
//			final ModelOperation<OrientDbPosLengthInjectMatch, OrientDbDriver> operation = ModelOperation.of(query, transformation);
//			return operation;
//		}
		case POSLENGTH_REPAIR: {
			final ModelQuery<OrientDbPosLengthMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH);
//			final OrientDbCypherTransformation<OrientDbPosLengthMatch> transformation = new OrientDbCypherTransformationRepairPosLength(driver, workspaceDir);
//			final ModelOperation<OrientDbPosLengthMatch, OrientDbDriver> operation = ModelOperation.of(query, transformation);
			final ModelOperation<OrientDbPosLengthMatch, OrientDbDriver> operation = ModelOperation.of(query);
			return operation;
		}
//
//			// RouteSensor
//		case ROUTESENSOR: {
//			final ModelQuery<OrientDbRouteSensorMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
//			final ModelOperation<OrientDbRouteSensorMatch, OrientDbDriver> operation = ModelOperation.of(query);
//			return operation;
//		}
//		case ROUTESENSOR_INJECT: {
//			final ModelQuery<OrientDbRouteSensorInjectMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir,
//					RailwayQuery.ROUTESENSOR_INJECT);
//			final OrientDbCypherTransformation<OrientDbRouteSensorInjectMatch> transformation = new OrientDbCypherTransformationInjectRouteSensor(driver,
//					workspaceDir);
//			final ModelOperation<OrientDbRouteSensorInjectMatch, OrientDbDriver> operation = ModelOperation.of(query, transformation);
//			return operation;
//		}
//		case ROUTESENSOR_REPAIR: {
//			final ModelQuery<OrientDbRouteSensorMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
//			final OrientDbCypherTransformation<OrientDbRouteSensorMatch> transformation = new OrientDbCypherTransformationRepairRouteSensor(driver, workspaceDir);
//			final ModelOperation<OrientDbRouteSensorMatch, OrientDbDriver> operation = ModelOperation.of(query, transformation);
//			return operation;
//		}
//
//			// SemaphoreNeighbor
//		case SEMAPHORENEIGHBOR: {
//			final ModelQuery<OrientDbSemaphoreNeighborMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
//			final ModelOperation<OrientDbSemaphoreNeighborMatch, OrientDbDriver> operation = ModelOperation.of(query);
//			return operation;
//		}
//		case SEMAPHORENEIGHBOR_INJECT: {
//			final ModelQuery<OrientDbSemaphoreNeighborInjectMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir,
//					RailwayQuery.SEMAPHORENEIGHBOR_INJECT);
//			final OrientDbCypherTransformation<OrientDbSemaphoreNeighborInjectMatch> transformation = new OrientDbCypherTransformationInjectSemaphoreNeighbor(driver,
//					workspaceDir);
//			final ModelOperation<OrientDbSemaphoreNeighborInjectMatch, OrientDbDriver> operation = ModelOperation.of(query, transformation);
//			return operation;
//		}
//		case SEMAPHORENEIGHBOR_REPAIR: {
//			final ModelQuery<OrientDbSemaphoreNeighborMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
//			final OrientDbCypherTransformation<OrientDbSemaphoreNeighborMatch> transformation = new OrientDbCypherTransformationRepairSemaphoreNeighbor(driver,
//					workspaceDir);
//			final ModelOperation<OrientDbSemaphoreNeighborMatch, OrientDbDriver> operation = ModelOperation.of(query, transformation);
//			return operation;
//		}
//
//			// SwitchMonitored
//		case SWITCHMONITORED: {
//			final ModelQuery<OrientDbSwitchMonitoredMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
//			final ModelOperation<OrientDbSwitchMonitoredMatch, OrientDbDriver> operation = ModelOperation.of(query);
//			return operation;
//		}
//		case SWITCHMONITORED_INJECT: {
//			final ModelQuery<OrientDbSwitchMonitoredInjectMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir,
//					RailwayQuery.SWITCHMONITORED_INJECT);
//			final OrientDbCypherTransformation<OrientDbSwitchMonitoredInjectMatch> transformation = new OrientDbCypherTransformationInjectSwitchMonitored(driver,
//					workspaceDir);
//			final ModelOperation<OrientDbSwitchMonitoredInjectMatch, OrientDbDriver> operation = ModelOperation.of(query, transformation);
//			return operation;
//		}
//		case SWITCHMONITORED_REPAIR: {
//			final ModelQuery<OrientDbSwitchMonitoredMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
//			final OrientDbCypherTransformation<OrientDbSwitchMonitoredMatch> transformation = new OrientDbCypherTransformationRepairSwitchMonitored(driver,
//					workspaceDir);
//			final ModelOperation<OrientDbSwitchMonitoredMatch, OrientDbDriver> operation = ModelOperation.of(query, transformation);
//			return operation;
//		}
//
//			// SwitchSet
//		case SWITCHSET: {
//			final ModelQuery<OrientDbSwitchSetMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET);
//			final ModelOperation<OrientDbSwitchSetMatch, OrientDbDriver> operation = ModelOperation.of(query);
//			return operation;
//		}
//		case SWITCHSET_INJECT: {
//			final ModelQuery<OrientDbSwitchSetInjectMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET_INJECT);
//			final OrientDbCypherTransformation<OrientDbSwitchSetInjectMatch> transformation = new OrientDbCypherTransformationInjectSwitchSet(driver, workspaceDir);
//			final ModelOperation<OrientDbSwitchSetInjectMatch, OrientDbDriver> operation = ModelOperation.of(query, transformation);
//			return operation;
//		}
//		case SWITCHSET_REPAIR: {
//			final ModelQuery<OrientDbSwitchSetMatch, OrientDbDriver> query = new OrientDbQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET);
//			final OrientDbCypherTransformation<OrientDbSwitchSetMatch> transformation = new OrientDbCypherTransformationRepairSwitchSet(driver, workspaceDir);
//			final ModelOperation<OrientDbSwitchSetMatch, OrientDbDriver> operation = ModelOperation.of(query, transformation);
//			return operation;
//		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported for.");
		}
	}

}
