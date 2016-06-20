package hu.bme.mit.trainbenchmark.benchmark.mysql.operations;

import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.mysql.driver.MySqlDriver;
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
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.repair.SqlTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class MySqlModelOperationFactory extends ModelOperationFactory<SqlMatch, MySqlDriver> {

	protected MySqlModelOperationFactory() {

	}

	public static MySqlModelOperationFactory create() {
		return new MySqlModelOperationFactory();
	}

	@Override
	public ModelOperation<? extends SqlMatch, MySqlDriver> createOperation(final RailwayOperation operationEnum,
			final Optional<String> workspaceDir, final MySqlDriver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final SqlQuery<SqlConnectedSegmentsMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<SqlConnectedSegmentsMatch, MySqlDriver> operation = ModelOperation.of(query);
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
			final SqlQuery<SqlConnectedSegmentsMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.CONNECTEDSEGMENTS);
			final SqlTransformationRepairConnectedSegments<MySqlDriver> transformation = new SqlTransformationRepairConnectedSegments<>(
					driver, workspaceDir);
			final ModelOperation<SqlConnectedSegmentsMatch, MySqlDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final SqlQuery<SqlPosLengthMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.POSLENGTH);
			final ModelOperation<SqlPosLengthMatch, MySqlDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			// TODO
			break;
		}
		case POSLENGTH_REPAIR: {
			final SqlQuery<SqlPosLengthMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.POSLENGTH);
			final SqlTransformationRepairPosLength<MySqlDriver> transformation = new SqlTransformationRepairPosLength<>(
					driver, workspaceDir);
			final ModelOperation<SqlPosLengthMatch, MySqlDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// RouteSensor
		case ROUTESENSOR: {
			final SqlQuery<SqlRouteSensorMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.ROUTESENSOR);
			final ModelOperation<SqlRouteSensorMatch, MySqlDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			// TODO
			break;
		}
		case ROUTESENSOR_REPAIR: {
			final SqlQuery<SqlRouteSensorMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.ROUTESENSOR);
			final SqlTransformationRepairRouteSensor<MySqlDriver> transformation = new SqlTransformationRepairRouteSensor<>(
					driver, workspaceDir);
			final ModelOperation<SqlRouteSensorMatch, MySqlDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final SqlQuery<SqlSemaphoreNeighborMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.SEMAPHORENEIGHBOR);
			final ModelOperation<SqlSemaphoreNeighborMatch, MySqlDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			// TODO
			break;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final SqlQuery<SqlSemaphoreNeighborMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.SEMAPHORENEIGHBOR);
			final SqlTransformationRepairSemaphoreNeighbor<MySqlDriver> transformation = new SqlTransformationRepairSemaphoreNeighbor<>(
					driver, workspaceDir);
			final ModelOperation<SqlSemaphoreNeighborMatch, MySqlDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final SqlQuery<SqlSwitchMonitoredMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.SWITCHMONITORED);
			final ModelOperation<SqlSwitchMonitoredMatch, MySqlDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			// TODO
			break;
		}
		case SWITCHMONITORED_REPAIR: {
			final SqlQuery<SqlSwitchMonitoredMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.SWITCHMONITORED);
			final SqlTransformationRepairSwitchMonitored<MySqlDriver> transformation = new SqlTransformationRepairSwitchMonitored<>(
					driver, workspaceDir);
			final ModelOperation<SqlSwitchMonitoredMatch, MySqlDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final SqlQuery<SqlSwitchSetMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.SWITCHSET);
			final ModelOperation<SqlSwitchSetMatch, MySqlDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			// TODO
			break;
		}
		case SWITCHSET_REPAIR: {
			final SqlQuery<SqlSwitchSetMatch, MySqlDriver> query = new SqlQuery<>(driver, workspaceDir,
					RailwayQuery.SWITCHSET);
			final SqlTransformationRepairSwitchSet<MySqlDriver> transformation = new SqlTransformationRepairSwitchSet<>(
					driver, workspaceDir);
			final ModelOperation<SqlSwitchSetMatch, MySqlDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		}
		throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
	}

}
