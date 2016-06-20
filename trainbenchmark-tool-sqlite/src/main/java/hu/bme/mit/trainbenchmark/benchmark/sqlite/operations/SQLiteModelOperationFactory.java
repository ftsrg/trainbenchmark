package hu.bme.mit.trainbenchmark.benchmark.sqlite.operations;

import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.sql.benchmarkcases.SqlQuery;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.driver.SQLiteDriver;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.transformations.repair.SQLiteTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.transformations.repair.SQLiteTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class SQLiteModelOperationFactory extends ModelOperationFactory<SqlMatch, SQLiteDriver> {

	protected SQLiteModelOperationFactory() {

	}

	public static SQLiteModelOperationFactory create() {
		return new SQLiteModelOperationFactory();
	}

	@Override
	public ModelOperation<? extends SqlMatch, SQLiteDriver> createOperation(final RailwayOperation operationEnum,
			final Optional<String> workspaceDir, final SQLiteDriver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final SqlQuery<SqlConnectedSegmentsMatch, SQLiteDriver> query = new SqlQuery<>(driver,
					workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<SqlConnectedSegmentsMatch, SQLiteDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			// TODO
			break;

			// final SqlApiQuery<, TDriver> query = new SqlApiQueryConnectedSegments<>(driver);
			// final SqlTransformation<, TDriver> transformation = new SqlTransformationInjectConnectedSegments<>(
			// driver);
			// final ModelOperation<, TDriver> operation = ModelOperation.of(query, transformation);
			// return operation;

		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final SqlQuery<SqlConnectedSegmentsMatch, SQLiteDriver> query = new SqlQuery<>(driver,
					workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final SQLiteTransformationRepairConnectedSegments transformation = new SQLiteTransformationRepairConnectedSegments(
					driver, workspaceDir);
			final ModelOperation<SqlConnectedSegmentsMatch, SQLiteDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final SqlQuery<SqlPosLengthMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.POSLENGTH);
			final ModelOperation<SqlPosLengthMatch, SQLiteDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			// TODO
			break;
		}
		case POSLENGTH_REPAIR: {
			final SqlQuery<SqlPosLengthMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.POSLENGTH);
			final SqlTransformationRepairPosLength<SQLiteDriver> transformation = new SqlTransformationRepairPosLength<>(driver,
					workspaceDir);
			final ModelOperation<SqlPosLengthMatch, SQLiteDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// RouteSensor
		case ROUTESENSOR: {
			final SqlQuery<SqlRouteSensorMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.ROUTESENSOR);
			final ModelOperation<SqlRouteSensorMatch, SQLiteDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			// TODO
			break;
		}
		case ROUTESENSOR_REPAIR: {
			final SqlQuery<SqlRouteSensorMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.ROUTESENSOR);
			final SqlTransformationRepairRouteSensor transformation = new SqlTransformationRepairRouteSensor(driver,
					workspaceDir);
			final ModelOperation<SqlRouteSensorMatch, SQLiteDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final SqlQuery<SqlSemaphoreNeighborMatch, SQLiteDriver> query = new SqlQuery<>(driver,
					workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final ModelOperation<SqlSemaphoreNeighborMatch, SQLiteDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			// TODO
			break;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final SqlQuery<SqlSemaphoreNeighborMatch, SQLiteDriver> query = new SqlQuery<>(driver,
					workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final SqlTransformationRepairSemaphoreNeighbor transformation = new SqlTransformationRepairSemaphoreNeighbor(
					driver, workspaceDir);
			final ModelOperation<SqlSemaphoreNeighborMatch, SQLiteDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final SqlQuery<SqlSwitchMonitoredMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.SWITCHMONITORED);
			final ModelOperation<SqlSwitchMonitoredMatch, SQLiteDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			// TODO
			break;
		}
		case SWITCHMONITORED_REPAIR: {
			final SqlQuery<SqlSwitchMonitoredMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.SWITCHMONITORED);
			final SQLiteTransformationRepairSwitchMonitored transformation = new SQLiteTransformationRepairSwitchMonitored(
					driver, workspaceDir);
			final ModelOperation<SqlSwitchMonitoredMatch, SQLiteDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final SqlQuery<SqlSwitchSetMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.SWITCHSET);
			final ModelOperation<SqlSwitchSetMatch, SQLiteDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			// TODO
			break;
		}
		case SWITCHSET_REPAIR: {
			final SqlQuery<SqlSwitchSetMatch, SQLiteDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.SWITCHSET);
			final SqlTransformationRepairSwitchSet transformation = new SqlTransformationRepairSwitchSet(driver,
					workspaceDir);
			final ModelOperation<SqlSwitchSetMatch, SQLiteDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		}
		throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
	}

}
