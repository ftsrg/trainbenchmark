package hu.bme.mit.trainbenchmark.benchmark.neo4j.operations;

import java.io.IOException;
import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jEngine;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jActiveRouteMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQueryConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQueryConnectedSegmentsInject;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQueryPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQueryPosLengthInject;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQueryRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQueryRouteSensorInject;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQuerySemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQuerySemaphoreNeighborInject;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQuerySwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQuerySwitchMonitoredInject;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQuerySwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.core.Neo4jCoreQuerySwitchSetInject;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.cypher.Neo4jCypherQuery;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jTransformation;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.inject.Neo4jTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.inject.Neo4jTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.inject.Neo4jTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.inject.Neo4jTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.inject.Neo4jTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.inject.Neo4jTransformationInjectSwitchSet;
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

	public Neo4jModelOperationFactory(final Neo4jEngine neo4jEngine) {
		this.neo4jEngine = neo4jEngine;
	}

	@Override
	public ModelOperation<? extends Neo4jMatch, Neo4jDriver> createOperation(final RailwayOperation operationEnum, final Optional<String> workspaceDir,
			final Neo4jDriver driver) throws Exception {

		switch (operationEnum) {
		// ActiveRoute
		case ACTIVEROUTE: {
			final ModelQuery<Neo4jActiveRouteMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.ACTIVEROUTE);
			final ModelOperation<Neo4jActiveRouteMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}

		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final ModelQuery<Neo4jConnectedSegmentsMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<Neo4jConnectedSegmentsMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			final ModelQuery<Neo4jConnectedSegmentsInjectMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS_INJECT);
			final Neo4jTransformation<Neo4jConnectedSegmentsInjectMatch> transformation = new Neo4jTransformationInjectConnectedSegments(driver);
			final ModelOperation<Neo4jConnectedSegmentsInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final ModelQuery<Neo4jConnectedSegmentsMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final Neo4jTransformation<Neo4jConnectedSegmentsMatch> transformation = new Neo4jTransformationRepairConnectedSegments(driver);
			final ModelOperation<Neo4jConnectedSegmentsMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final ModelQuery<Neo4jPosLengthMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.POSLENGTH);
			final ModelOperation<Neo4jPosLengthMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			final ModelQuery<Neo4jPosLengthInjectMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.POSLENGTH_INJECT);
			final Neo4jTransformation<Neo4jPosLengthInjectMatch> transformation = new Neo4jTransformationInjectPosLength(driver);
			final ModelOperation<Neo4jPosLengthInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case POSLENGTH_REPAIR: {
			final ModelQuery<Neo4jPosLengthMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.POSLENGTH);
			final Neo4jTransformation<Neo4jPosLengthMatch> transformation = new Neo4jTransformationRepairPosLength(driver);
			final ModelOperation<Neo4jPosLengthMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// RouteSensor
		case ROUTESENSOR: {
			final ModelQuery<Neo4jRouteSensorMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
			final ModelOperation<Neo4jRouteSensorMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			final ModelQuery<Neo4jRouteSensorInjectMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.ROUTESENSOR_INJECT);
			final Neo4jTransformation<Neo4jRouteSensorInjectMatch> transformation = new Neo4jTransformationInjectRouteSensor(driver);
			final ModelOperation<Neo4jRouteSensorInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case ROUTESENSOR_REPAIR: {
			final ModelQuery<Neo4jRouteSensorMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
			final Neo4jTransformation<Neo4jRouteSensorMatch> transformation = new Neo4jTransformationRepairRouteSensor(driver);
			final ModelOperation<Neo4jRouteSensorMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final ModelQuery<Neo4jSemaphoreNeighborMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final ModelOperation<Neo4jSemaphoreNeighborMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			final ModelQuery<Neo4jSemaphoreNeighborInjectMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR_INJECT);
			final Neo4jTransformation<Neo4jSemaphoreNeighborInjectMatch> transformation = new Neo4jTransformationInjectSemaphoreNeighbor(driver);
			final ModelOperation<Neo4jSemaphoreNeighborInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final ModelQuery<Neo4jSemaphoreNeighborMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final Neo4jTransformation<Neo4jSemaphoreNeighborMatch> transformation = new Neo4jTransformationRepairSemaphoreNeighbor(driver);
			final ModelOperation<Neo4jSemaphoreNeighborMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final ModelQuery<Neo4jSwitchMonitoredMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
			final ModelOperation<Neo4jSwitchMonitoredMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			final ModelQuery<Neo4jSwitchMonitoredInjectMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.SWITCHMONITORED_INJECT);
			final Neo4jTransformation<Neo4jSwitchMonitoredInjectMatch> transformation = new Neo4jTransformationInjectSwitchMonitored(driver);
			final ModelOperation<Neo4jSwitchMonitoredInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHMONITORED_REPAIR: {
			final ModelQuery<Neo4jSwitchMonitoredMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
			final Neo4jTransformation<Neo4jSwitchMonitoredMatch> transformation = new Neo4jTransformationRepairSwitchMonitored(driver);
			final ModelOperation<Neo4jSwitchMonitoredMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final ModelQuery<Neo4jSwitchSetMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.SWITCHSET);
			final ModelOperation<Neo4jSwitchSetMatch, Neo4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			final ModelQuery<Neo4jSwitchSetInjectMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.SWITCHSET_INJECT);
			final Neo4jTransformation<Neo4jSwitchSetInjectMatch> transformation = new Neo4jTransformationInjectSwitchSet(driver);
			final ModelOperation<Neo4jSwitchSetInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHSET_REPAIR: {
			final ModelQuery<Neo4jSwitchSetMatch, Neo4jDriver> query = createQuery(driver, workspaceDir, RailwayQuery.SWITCHSET);
			final Neo4jTransformation<Neo4jSwitchSetMatch> transformation = new Neo4jTransformationRepairSwitchSet(driver);
			final ModelOperation<Neo4jSwitchSetMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}

	@SuppressWarnings("unchecked")
	private <TNeo4jMatch extends Neo4jMatch> ModelQuery<TNeo4jMatch, Neo4jDriver> createQuery(final Neo4jDriver driver, final Optional<String> workspaceDir,
			final RailwayQuery query) throws IOException {
		switch (neo4jEngine) {
		case CYPHER:
			return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCypherQuery(driver, workspaceDir, query);
		case COREAPI:
			switch (query) {
			case CONNECTEDSEGMENTS:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQueryConnectedSegments(driver);
			case CONNECTEDSEGMENTS_INJECT:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQueryConnectedSegmentsInject(driver);
			case POSLENGTH:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQueryPosLength(driver);
			case POSLENGTH_INJECT:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQueryPosLengthInject(driver);
			case ROUTESENSOR:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQueryRouteSensor(driver);
			case ROUTESENSOR_INJECT:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQueryRouteSensorInject(driver);
			case SEMAPHORENEIGHBOR:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQuerySemaphoreNeighbor(driver);
			case SEMAPHORENEIGHBOR_INJECT:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQuerySemaphoreNeighborInject(driver);
			case SWITCHMONITORED:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQuerySwitchMonitored(driver);
			case SWITCHMONITORED_INJECT:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQuerySwitchMonitoredInject(driver);
			case SWITCHSET:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQuerySwitchSet(driver);
			case SWITCHSET_INJECT:
				return (ModelQuery<TNeo4jMatch, Neo4jDriver>) new Neo4jCoreQuerySwitchSetInject(driver);
			default:
				throw new UnsupportedOperationException("Query " + query + " not supported.");
			}
		default:
			throw new UnsupportedOperationException("Engine " + neo4jEngine + " not supported.");
		}

	}

}
