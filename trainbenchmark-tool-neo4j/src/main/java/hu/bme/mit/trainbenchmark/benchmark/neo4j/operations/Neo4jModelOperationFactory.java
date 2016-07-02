package hu.bme.mit.trainbenchmark.benchmark.neo4j.operations;

import java.io.IOException;
import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jEngine;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQueryConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQueryPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQueryRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQuerySemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQuerySwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQuerySwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.cypher.Neo4jCypherQuery;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jTransformation;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.repair.Neo4jTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class Neo4jModelOperationFactory extends ModelOperationFactory<Neo4jMatch, Neo4jDriver> {

	protected final Neo4jEngine neo4jEngine;

	protected Neo4jModelOperationFactory(final Neo4jEngine neo4jEngine) {
		this.neo4jEngine = neo4jEngine;
	}

	public static Neo4jModelOperationFactory create(final Neo4jEngine neo4jEngine) {
		return new Neo4jModelOperationFactory(neo4jEngine);
	}

	@Override
	public ModelOperation<? extends Neo4jMatch, Neo4jDriver> createOperation(final RailwayOperation operationEnum,
			final Optional<String> workspaceDir, final Neo4jDriver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final ModelQuery<Neo4jConnectedSegmentsMatch, Neo4jDriver> query = createQuery(driver, workspaceDir,
					RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<Neo4jConnectedSegmentsMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			// TODO

		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final ModelQuery<Neo4jConnectedSegmentsMatch, Neo4jDriver> query = new Neo4jCoreQueryConnectedSegments(
					driver);
			final Neo4jTransformation<Neo4jConnectedSegmentsMatch> transformation = new Neo4jTransformationRepairConnectedSegments(
					driver);
			final ModelOperation<Neo4jConnectedSegmentsMatch, Neo4jDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final ModelQuery<Neo4jPosLengthMatch, Neo4jDriver> query = new Neo4jCoreQueryPosLength(driver);
			final ModelOperation<Neo4jPosLengthMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			// TODO
		}
		case POSLENGTH_REPAIR: {
			final ModelQuery<Neo4jPosLengthMatch, Neo4jDriver> query = new Neo4jCoreQueryPosLength(driver);
			final Neo4jTransformation<Neo4jPosLengthMatch> transformation = new Neo4jTransformationRepairPosLength(
					driver);
			final ModelOperation<Neo4jPosLengthMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// RouteSensor
		case ROUTESENSOR: {
			final ModelQuery<Neo4jRouteSensorMatch, Neo4jDriver> query = new Neo4jCoreQueryRouteSensor(driver);
			final ModelOperation<Neo4jRouteSensorMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			// TODO
		}
		case ROUTESENSOR_REPAIR: {
			final ModelQuery<Neo4jRouteSensorMatch, Neo4jDriver> query = new Neo4jCoreQueryRouteSensor(driver);
			final Neo4jTransformation<Neo4jRouteSensorMatch> transformation = new Neo4jTransformationRepairRouteSensor(
					driver);
			final ModelOperation<Neo4jRouteSensorMatch, Neo4jDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final ModelQuery<Neo4jSemaphoreNeighborMatch, Neo4jDriver> query = new Neo4jCoreQuerySemaphoreNeighbor(
					driver);
			final ModelOperation<Neo4jSemaphoreNeighborMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			// TODO
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final ModelQuery<Neo4jSemaphoreNeighborMatch, Neo4jDriver> query = new Neo4jCoreQuerySemaphoreNeighbor(
					driver);
			final Neo4jTransformation<Neo4jSemaphoreNeighborMatch> transformation = new Neo4jTransformationRepairSemaphoreNeighbor(
					driver);
			final ModelOperation<Neo4jSemaphoreNeighborMatch, Neo4jDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final ModelQuery<Neo4jSwitchMonitoredMatch, Neo4jDriver> query = new Neo4jCoreQuerySwitchMonitored(driver);
			final ModelOperation<Neo4jSwitchMonitoredMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			// TODO
		}
		case SWITCHMONITORED_REPAIR: {
			final ModelQuery<Neo4jSwitchMonitoredMatch, Neo4jDriver> query = new Neo4jCoreQuerySwitchMonitored(driver);
			final Neo4jTransformation<Neo4jSwitchMonitoredMatch> transformation = new Neo4jTransformationRepairSwitchMonitored(
					driver);
			final ModelOperation<Neo4jSwitchMonitoredMatch, Neo4jDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final ModelQuery<Neo4jSwitchSetMatch, Neo4jDriver> query = new Neo4jCoreQuerySwitchSet(driver);
			final ModelOperation<Neo4jSwitchSetMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			// TODO
		}
		case SWITCHSET_REPAIR: {
			final ModelQuery<Neo4jSwitchSetMatch, Neo4jDriver> query = new Neo4jCoreQuerySwitchSet(driver);
			final Neo4jTransformation<Neo4jSwitchSetMatch> transformation = new Neo4jTransformationRepairSwitchSet(
					driver);
			final ModelOperation<Neo4jSwitchSetMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}

	@SuppressWarnings("unchecked")
	private <TNeo4jMatch extends Neo4jMatch> ModelQuery<TNeo4jMatch, Neo4jDriver> createQuery(final Neo4jDriver driver,
			final Optional<String> workspaceDir, final RailwayQuery query) throws IOException {
		switch (neo4jEngine) {
		case CYPHER:
			return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCypherQuery(driver, workspaceDir, query);
		case COREAPI:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQueryConnectedSegments(driver);
			case POSLENGTH:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQueryPosLength(driver);
			case ROUTESENSOR:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQueryRouteSensor(driver);
			case SEMAPHORENEIGHBOR:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQuerySemaphoreNeighbor(driver);
			case SWITCHMONITORED:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQuerySwitchMonitored(driver);
			case SWITCHSET:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQuerySwitchSet(driver);
			default:
				throw new UnsupportedOperationException("Query " + query + " not supported.");
			}
		default:
			throw new UnsupportedOperationException("Engine " + neo4jEngine + " not supported.");
		}

	}

}
