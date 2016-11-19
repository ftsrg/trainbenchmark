package hu.bme.mit.trainbenchmark.benchmark.sqlite.operations;

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
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SqlTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SqlTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SqlTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.inject.SqlTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.driver.SQLiteDriver;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.transformations.inject.SQLiteTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.transformations.inject.SQLiteTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.transformations.repair.SQLiteTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.transformations.repair.SQLiteTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class SQLiteModelOperationFactory extends ModelOperationFactory<SqlMatch, SQLiteDriver> {

	@Override
	public ModelOperation<? extends SqlMatch, SQLiteDriver> createOperation(final RailwayOperation operationEnum, final String workspaceDir,
			final SQLiteDriver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final SqlQuery<SqlConnectedSegmentsMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<SqlConnectedSegmentsMatch, SQLiteDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			final SqlQuery<SqlConnectedSegmentsInjectMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS_INJECT);
			final SqlTransformation<SqlConnectedSegmentsInjectMatch, SQLiteDriver> transformation = new SQLiteTransformationInjectConnectedSegments(driver,
					workspaceDir);
			final ModelOperation<SqlConnectedSegmentsInjectMatch, SQLiteDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final SqlQuery<SqlConnectedSegmentsMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final SqlTransformation<SqlConnectedSegmentsMatch, SQLiteDriver> transformation = new SQLiteTransformationRepairConnectedSegments(driver,
					workspaceDir);
			final ModelOperation<SqlConnectedSegmentsMatch, SQLiteDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final SqlQuery<SqlPosLengthMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH);
			final ModelOperation<SqlPosLengthMatch, SQLiteDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			final SqlQuery<SqlPosLengthInjectMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH_INJECT);
			final SqlTransformation<SqlPosLengthInjectMatch, SQLiteDriver> transformation = new SqlTransformationInjectPosLength<>(driver, workspaceDir);
			final ModelOperation<SqlPosLengthInjectMatch, SQLiteDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case POSLENGTH_REPAIR: {
			final SqlQuery<SqlPosLengthMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH);
			final SqlTransformation<SqlPosLengthMatch, SQLiteDriver> transformation = new SqlTransformationRepairPosLength<>(driver, workspaceDir);
			final ModelOperation<SqlPosLengthMatch, SQLiteDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// RouteSensor
		case ROUTESENSOR: {
			final SqlQuery<SqlRouteSensorMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
			final ModelOperation<SqlRouteSensorMatch, SQLiteDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			final SqlQuery<SqlRouteSensorInjectMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.ROUTESENSOR_INJECT);
			final SqlTransformation<SqlRouteSensorInjectMatch, SQLiteDriver> transformation = new SQLiteTransformationInjectRouteSensor(driver, workspaceDir);
			final ModelOperation<SqlRouteSensorInjectMatch, SQLiteDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case ROUTESENSOR_REPAIR: {
			final SqlQuery<SqlRouteSensorMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
			final SqlTransformation<SqlRouteSensorMatch, SQLiteDriver> transformation = new SqlTransformationRepairRouteSensor<>(driver, workspaceDir);
			final ModelOperation<SqlRouteSensorMatch, SQLiteDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final SqlQuery<SqlSemaphoreNeighborMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final ModelOperation<SqlSemaphoreNeighborMatch, SQLiteDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			final SqlQuery<SqlSemaphoreNeighborInjectMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR_INJECT);
			final SqlTransformation<SqlSemaphoreNeighborInjectMatch, SQLiteDriver> transformation = new SqlTransformationInjectSemaphoreNeighbor<>(driver,
					workspaceDir);
			final ModelOperation<SqlSemaphoreNeighborInjectMatch, SQLiteDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final SqlQuery<SqlSemaphoreNeighborMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final SqlTransformation<SqlSemaphoreNeighborMatch, SQLiteDriver> transformation = new SqlTransformationRepairSemaphoreNeighbor<>(driver,
					workspaceDir);
			final ModelOperation<SqlSemaphoreNeighborMatch, SQLiteDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final SqlQuery<SqlSwitchMonitoredMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
			final ModelOperation<SqlSwitchMonitoredMatch, SQLiteDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			final SqlQuery<SqlSwitchMonitoredInjectMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SWITCHMONITORED_INJECT);
			final SqlTransformation<SqlSwitchMonitoredInjectMatch, SQLiteDriver> transformation = new SqlTransformationInjectSwitchMonitored<>(driver,
					workspaceDir);
			final ModelOperation<SqlSwitchMonitoredInjectMatch, SQLiteDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHMONITORED_REPAIR: {
			final SqlQuery<SqlSwitchMonitoredMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
			final SqlTransformation<SqlSwitchMonitoredMatch, SQLiteDriver> transformation = new SQLiteTransformationRepairSwitchMonitored(driver, workspaceDir);
			final ModelOperation<SqlSwitchMonitoredMatch, SQLiteDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final SqlQuery<SqlSwitchSetMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET);
			final ModelOperation<SqlSwitchSetMatch, SQLiteDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			final SqlQuery<SqlSwitchSetInjectMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET_INJECT);
			final SqlTransformation<SqlSwitchSetInjectMatch, SQLiteDriver> transformation = new SqlTransformationInjectSwitchSet<>(driver, workspaceDir);
			final ModelOperation<SqlSwitchSetInjectMatch, SQLiteDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHSET_REPAIR: {
			final SqlQuery<SqlSwitchSetMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET);
			final SqlTransformation<SqlSwitchSetMatch, SQLiteDriver> transformation = new SqlTransformationRepairSwitchSet<>(driver, workspaceDir);
			final ModelOperation<SqlSwitchSetMatch, SQLiteDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		default:
			break;
		}
		throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
	}

}
