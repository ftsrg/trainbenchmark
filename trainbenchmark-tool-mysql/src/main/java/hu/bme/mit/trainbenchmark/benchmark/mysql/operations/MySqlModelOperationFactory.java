package hu.bme.mit.trainbenchmark.benchmark.mysql.operations;

import hu.bme.mit.trainbenchmark.benchmark.mysql.driver.MySqlDriver;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.queries.SqlQuery;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.SqlTransformation;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SqlTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SqlTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SqlTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SqlTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SqlTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SqlTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class MySqlModelOperationFactory extends ModelOperationFactory<SqlMatch, MySqlDriver> {

	@Override
	public ModelOperation<? extends SqlMatch, MySqlDriver> createOperation(final RailwayOperation operationEnum, final String workspaceDir,
			final MySqlDriver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final SqlQuery<SqlConnectedSegmentsMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<SqlConnectedSegmentsMatch, MySqlDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			final SqlQuery<SqlConnectedSegmentsInjectMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS_INJECT);
			final SqlTransformation<SqlConnectedSegmentsInjectMatch, MySqlDriver> transformation = new SqlTransformationInjectConnectedSegments<>(driver,
					workspaceDir);
			final ModelOperation<SqlConnectedSegmentsInjectMatch, MySqlDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final SqlQuery<SqlConnectedSegmentsMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final SqlTransformation<SqlConnectedSegmentsMatch, MySqlDriver> transformation = new SqlTransformationRepairConnectedSegments<>(driver,
					workspaceDir);
			final ModelOperation<SqlConnectedSegmentsMatch, MySqlDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final SqlQuery<SqlPosLengthMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH);
			final ModelOperation<SqlPosLengthMatch, MySqlDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			final SqlQuery<SqlPosLengthInjectMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH_INJECT);
			final SqlTransformation<SqlPosLengthInjectMatch, MySqlDriver> transformation = new SqlTransformationInjectPosLength<>(driver, workspaceDir);
			final ModelOperation<SqlPosLengthInjectMatch, MySqlDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case POSLENGTH_REPAIR: {
			final SqlQuery<SqlPosLengthMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH);
			final SqlTransformation<SqlPosLengthMatch, MySqlDriver> transformation = new SqlTransformationRepairPosLength<>(driver, workspaceDir);
			final ModelOperation<SqlPosLengthMatch, MySqlDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// RouteSensor
		case ROUTESENSOR: {
			final SqlQuery<SqlRouteSensorMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
			final ModelOperation<SqlRouteSensorMatch, MySqlDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			final SqlQuery<SqlRouteSensorInjectMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.ROUTESENSOR_INJECT);
			final SqlTransformation<SqlRouteSensorInjectMatch, MySqlDriver> transformation = new SqlTransformationInjectRouteSensor<>(driver, workspaceDir);
			final ModelOperation<SqlRouteSensorInjectMatch, MySqlDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case ROUTESENSOR_REPAIR: {
			final SqlQuery<SqlRouteSensorMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
			final SqlTransformation<SqlRouteSensorMatch, MySqlDriver> transformation = new SqlTransformationRepairRouteSensor<>(driver, workspaceDir);
			final ModelOperation<SqlRouteSensorMatch, MySqlDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final SqlQuery<SqlSemaphoreNeighborMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final ModelOperation<SqlSemaphoreNeighborMatch, MySqlDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			final SqlQuery<SqlSemaphoreNeighborInjectMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR_INJECT);
			final SqlTransformation<SqlSemaphoreNeighborInjectMatch, MySqlDriver> transformation = new SqlTransformationInjectSemaphoreNeighbor<>(driver,
					workspaceDir);
			final ModelOperation<SqlSemaphoreNeighborInjectMatch, MySqlDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final SqlQuery<SqlSemaphoreNeighborMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final SqlTransformation<SqlSemaphoreNeighborMatch, MySqlDriver> transformation = new SqlTransformationRepairSemaphoreNeighbor<>(driver,
					workspaceDir);
			final ModelOperation<SqlSemaphoreNeighborMatch, MySqlDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final SqlQuery<SqlSwitchMonitoredMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
			final ModelOperation<SqlSwitchMonitoredMatch, MySqlDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			final SqlQuery<SqlSwitchMonitoredInjectMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SWITCHMONITORED_INJECT);
			final SqlTransformation<SqlSwitchMonitoredInjectMatch, MySqlDriver> transformation = new SqlTransformationInjectSwitchMonitored<>(driver,
					workspaceDir);
			final ModelOperation<SqlSwitchMonitoredInjectMatch, MySqlDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHMONITORED_REPAIR: {
			final SqlQuery<SqlSwitchMonitoredMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
			final SqlTransformation<SqlSwitchMonitoredMatch, MySqlDriver> transformation = new SqlTransformationRepairSwitchMonitored<>(driver, workspaceDir);
			final ModelOperation<SqlSwitchMonitoredMatch, MySqlDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final SqlQuery<SqlSwitchSetMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET);
			final ModelOperation<SqlSwitchSetMatch, MySqlDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			final SqlQuery<SqlSwitchSetInjectMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET_INJECT);
			final SqlTransformation<SqlSwitchSetInjectMatch, MySqlDriver> transformation = new SqlTransformationInjectSwitchSet<>(driver, workspaceDir);
			final ModelOperation<SqlSwitchSetInjectMatch, MySqlDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHSET_REPAIR: {
			final SqlQuery<SqlSwitchSetMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET);
			final SqlTransformation<SqlSwitchSetMatch, MySqlDriver> transformation = new SqlTransformationRepairSwitchSet<>(driver, workspaceDir);
			final ModelOperation<SqlSwitchSetMatch, MySqlDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		default:
			break;
		}
		throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
	}

}
