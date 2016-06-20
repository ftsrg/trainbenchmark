package hu.bme.mit.trainbenchmark.benchmark.neo4j.operations;

import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQuery;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQueryConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQueryPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQueryRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQuerySemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQuerySwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQuerySwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jTransformation;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class Neo4jModelOperationFactory extends ModelOperationFactory<Neo4jMatch, Neo4jDriver> {

	protected Neo4jModelOperationFactory() {

	}

	public static Neo4jModelOperationFactory create() {
		return new Neo4jModelOperationFactory();
	}

	@Override
	public ModelOperation<? extends Neo4jMatch, Neo4jDriver> createOperation(final RailwayOperation operationEnum,
			final Optional<String> workspacePath, final Neo4jDriver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final Neo4jCoreQuery<Neo4jConnectedSegmentsMatch> query = new Neo4jCoreQueryConnectedSegments(driver);
			final ModelOperation<Neo4jConnectedSegmentsMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			// TODO

		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final Neo4jCoreQuery<Neo4jConnectedSegmentsMatch> query = new Neo4jCoreQueryConnectedSegments(driver);
			final Neo4jTransformation<Neo4jConnectedSegmentsMatch> transformation = new Neo4jTransformationRepairConnectedSegments(
					driver);
			final ModelOperation<Neo4jConnectedSegmentsMatch, Neo4jDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final Neo4jCoreQuery<Neo4jPosLengthMatch> query = new Neo4jCoreQueryPosLength(driver);
			final ModelOperation<Neo4jPosLengthMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			// TODO
		}
		case POSLENGTH_REPAIR: {
			final Neo4jCoreQuery<Neo4jPosLengthMatch> query = new Neo4jCoreQueryPosLength(driver);
			final Neo4jTransformation<Neo4jPosLengthMatch> transformation = new Neo4jTransformationRepairPosLength(
					driver);
			final ModelOperation<Neo4jPosLengthMatch, Neo4jDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;

		}

			// RouteSensor
		case ROUTESENSOR: {
			final Neo4jCoreQuery<Neo4jRouteSensorMatch> query = new Neo4jCoreQueryRouteSensor(driver);
			final ModelOperation<Neo4jRouteSensorMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			// TODO
		}
		case ROUTESENSOR_REPAIR: {
			final Neo4jCoreQuery<Neo4jRouteSensorMatch> query = new Neo4jCoreQueryRouteSensor(driver);
			final Neo4jTransformation<Neo4jRouteSensorMatch> transformation = new Neo4jTransformationRepairRouteSensor(
					driver);
			final ModelOperation<Neo4jRouteSensorMatch, Neo4jDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final Neo4jCoreQuery<Neo4jSemaphoreNeighborMatch> query = new Neo4jCoreQuerySemaphoreNeighbor(driver);
			final ModelOperation<Neo4jSemaphoreNeighborMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			// TODO
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final Neo4jCoreQuery<Neo4jSemaphoreNeighborMatch> query = new Neo4jCoreQuerySemaphoreNeighbor(driver);
			final Neo4jTransformation<Neo4jSemaphoreNeighborMatch> transformation = new Neo4jTransformationRepairSemaphoreNeighbor(
					driver);
			final ModelOperation<Neo4jSemaphoreNeighborMatch, Neo4jDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final Neo4jCoreQuery<Neo4jSwitchMonitoredMatch> query = new Neo4jCoreQuerySwitchMonitored(driver);
			final ModelOperation<Neo4jSwitchMonitoredMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			// TODO
		}
		case SWITCHMONITORED_REPAIR: {
			final Neo4jCoreQuery<Neo4jSwitchMonitoredMatch> query = new Neo4jCoreQuerySwitchMonitored(driver);
			final Neo4jTransformation<Neo4jSwitchMonitoredMatch> transformation = new Neo4jTransformationRepairSwitchMonitored(
					driver);
			final ModelOperation<Neo4jSwitchMonitoredMatch, Neo4jDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final Neo4jCoreQuery<Neo4jSwitchSetMatch> query = new Neo4jCoreQuerySwitchSet(driver);
			final ModelOperation<Neo4jSwitchSetMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			// TODO
		}
		case SWITCHSET_REPAIR: {
			final Neo4jCoreQuery<Neo4jSwitchSetMatch> query = new Neo4jCoreQuerySwitchSet(driver);
			final Neo4jTransformation<Neo4jSwitchSetMatch> transformation = new Neo4jTransformationRepairSwitchSet(
					driver);
			final ModelOperation<Neo4jSwitchSetMatch, Neo4jDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}

}
