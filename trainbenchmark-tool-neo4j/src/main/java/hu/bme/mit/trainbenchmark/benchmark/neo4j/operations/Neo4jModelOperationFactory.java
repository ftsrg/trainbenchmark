package hu.bme.mit.trainbenchmark.benchmark.neo4j.operations;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jEngine;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
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
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jCoreTransformation;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jCypherTransformation;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.core.inject.Neo4jCoreTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.core.inject.Neo4jCoreTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.core.inject.Neo4jCoreTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.core.inject.Neo4jCoreTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.core.inject.Neo4jCoreTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.core.inject.Neo4jCoreTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.core.repair.Neo4jCoreTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.core.repair.Neo4jCoreTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.core.repair.Neo4jCoreTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.core.repair.Neo4jCoreTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.core.repair.Neo4jCoreTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.core.repair.Neo4jCoreTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.cypher.inject.Neo4jCypherTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.cypher.repair.Neo4jCypherTransformationRepairPosLength;
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
	public ModelOperation<? extends Neo4jMatch, Neo4jDriver> createOperation(final RailwayOperation operationEnum, final String workspaceDir,
			final Neo4jDriver driver) throws Exception {

		switch (neo4jEngine) {
		case COREAPI:
			switch (operationEnum) {
			// ActiveRoute
			case ACTIVEROUTE: {
				// final ModelQuery<Neo4jActiveRouteMatch, Neo4jDriver> query = new ... // TODO
				// final ModelOperation<Neo4jActiveRouteMatch, Neo4jDriver> operation = ModelOperation.of(query);
				// return operation;
			}

				// ConnectedSegments
			case CONNECTEDSEGMENTS: {
				final ModelQuery<Neo4jConnectedSegmentsMatch, Neo4jDriver> query = new Neo4jCoreQueryConnectedSegments(driver);
				final ModelOperation<Neo4jConnectedSegmentsMatch, Neo4jDriver> operation = ModelOperation.of(query);
				return operation;
			}
			case CONNECTEDSEGMENTS_INJECT: {
				final ModelQuery<Neo4jConnectedSegmentsInjectMatch, Neo4jDriver> query = new Neo4jCoreQueryConnectedSegmentsInject(driver);
				final Neo4jCoreTransformation<Neo4jConnectedSegmentsInjectMatch> transformation = new Neo4jCoreTransformationInjectConnectedSegments(driver);
				final ModelOperation<Neo4jConnectedSegmentsInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case CONNECTEDSEGMENTS_REPAIR: {
				final ModelQuery<Neo4jConnectedSegmentsMatch, Neo4jDriver> query = new Neo4jCoreQueryConnectedSegments(driver);
				final Neo4jCoreTransformation<Neo4jConnectedSegmentsMatch> transformation = new Neo4jCoreTransformationRepairConnectedSegments(driver);
				final ModelOperation<Neo4jConnectedSegmentsMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

				// PosLength
			case POSLENGTH: {
				final ModelQuery<Neo4jPosLengthMatch, Neo4jDriver> query = new Neo4jCoreQueryPosLength(driver);
				final ModelOperation<Neo4jPosLengthMatch, Neo4jDriver> operation = ModelOperation.of(query);
				return operation;
			}
			case POSLENGTH_INJECT: {
				final ModelQuery<Neo4jPosLengthInjectMatch, Neo4jDriver> query = new Neo4jCoreQueryPosLengthInject(driver);
				final Neo4jCoreTransformation<Neo4jPosLengthInjectMatch> transformation = new Neo4jCoreTransformationInjectPosLength(driver);
				final ModelOperation<Neo4jPosLengthInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case POSLENGTH_REPAIR: {
				final ModelQuery<Neo4jPosLengthMatch, Neo4jDriver> query = new Neo4jCoreQueryPosLength(driver);
				final Neo4jCoreTransformation<Neo4jPosLengthMatch> transformation = new Neo4jCoreTransformationRepairPosLength(driver);
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
				final ModelQuery<Neo4jRouteSensorInjectMatch, Neo4jDriver> query = new Neo4jCoreQueryRouteSensorInject(driver);
				final Neo4jCoreTransformation<Neo4jRouteSensorInjectMatch> transformation = new Neo4jCoreTransformationInjectRouteSensor(driver);
				final ModelOperation<Neo4jRouteSensorInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case ROUTESENSOR_REPAIR: {
				final ModelQuery<Neo4jRouteSensorMatch, Neo4jDriver> query = new Neo4jCoreQueryRouteSensor(driver);
				final Neo4jCoreTransformation<Neo4jRouteSensorMatch> transformation = new Neo4jCoreTransformationRepairRouteSensor(driver);
				final ModelOperation<Neo4jRouteSensorMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

				// SemaphoreNeighbor
			case SEMAPHORENEIGHBOR: {
				final ModelQuery<Neo4jSemaphoreNeighborMatch, Neo4jDriver> query = new Neo4jCoreQuerySemaphoreNeighbor(driver);
				final ModelOperation<Neo4jSemaphoreNeighborMatch, Neo4jDriver> operation = ModelOperation.of(query);
				return operation;
			}
			case SEMAPHORENEIGHBOR_INJECT: {
				final ModelQuery<Neo4jSemaphoreNeighborInjectMatch, Neo4jDriver> query = new Neo4jCoreQuerySemaphoreNeighborInject(driver);
				final Neo4jCoreTransformation<Neo4jSemaphoreNeighborInjectMatch> transformation = new Neo4jCoreTransformationInjectSemaphoreNeighbor(driver);
				final ModelOperation<Neo4jSemaphoreNeighborInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case SEMAPHORENEIGHBOR_REPAIR: {
				final ModelQuery<Neo4jSemaphoreNeighborMatch, Neo4jDriver> query = new Neo4jCoreQuerySemaphoreNeighbor(driver);
				final Neo4jCoreTransformation<Neo4jSemaphoreNeighborMatch> transformation = new Neo4jCoreTransformationRepairSemaphoreNeighbor(driver);
				final ModelOperation<Neo4jSemaphoreNeighborMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

				// SwitchMonitored
			case SWITCHMONITORED: {
				final ModelQuery<Neo4jSwitchMonitoredMatch, Neo4jDriver> query = new Neo4jCoreQuerySwitchMonitored(driver);
				final ModelOperation<Neo4jSwitchMonitoredMatch, Neo4jDriver> operation = ModelOperation.of(query);
				return operation;
			}
			case SWITCHMONITORED_INJECT: {
				final ModelQuery<Neo4jSwitchMonitoredInjectMatch, Neo4jDriver> query = new Neo4jCoreQuerySwitchMonitoredInject(driver);
				final Neo4jCoreTransformation<Neo4jSwitchMonitoredInjectMatch> transformation = new Neo4jCoreTransformationInjectSwitchMonitored(driver);
				final ModelOperation<Neo4jSwitchMonitoredInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case SWITCHMONITORED_REPAIR: {
				final ModelQuery<Neo4jSwitchMonitoredMatch, Neo4jDriver> query = new Neo4jCoreQuerySwitchMonitored(driver);
				final Neo4jCoreTransformation<Neo4jSwitchMonitoredMatch> transformation = new Neo4jCoreTransformationRepairSwitchMonitored(driver);
				final ModelOperation<Neo4jSwitchMonitoredMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

				// SwitchSet
			case SWITCHSET: {
				final ModelQuery<Neo4jSwitchSetMatch, Neo4jDriver> query = new Neo4jCoreQuerySwitchSet(driver);
				final ModelOperation<Neo4jSwitchSetMatch, Neo4jDriver> operation = ModelOperation.of(query);
				return operation;
			}
			case SWITCHSET_INJECT: {
				final ModelQuery<Neo4jSwitchSetInjectMatch, Neo4jDriver> query = new Neo4jCoreQuerySwitchSetInject(driver);
				final Neo4jCoreTransformation<Neo4jSwitchSetInjectMatch> transformation = new Neo4jCoreTransformationInjectSwitchSet(driver);
				final ModelOperation<Neo4jSwitchSetInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case SWITCHSET_REPAIR: {
				final ModelQuery<Neo4jSwitchSetMatch, Neo4jDriver> query = new Neo4jCoreQuerySwitchSet(driver);
				final Neo4jCoreTransformation<Neo4jSwitchSetMatch> transformation = new Neo4jCoreTransformationRepairSwitchSet(driver);
				final ModelOperation<Neo4jSwitchSetMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

			default:
				throw new UnsupportedOperationException("Operation " + operationEnum + " not supported for Neo4j engine " + neo4jEngine + ".");
			}
		case CYPHER:
			switch (operationEnum) {
			// ActiveRoute
			case ACTIVEROUTE: {
				// final ModelQuery<Neo4jActiveRouteMatch, Neo4jDriver> query = new ... // TODO
				// final ModelOperation<Neo4jActiveRouteMatch, Neo4jDriver> operation = ModelOperation.of(query);
				// return operation;
			}

				// ConnectedSegments
			case CONNECTEDSEGMENTS: {
				final ModelQuery<Neo4jConnectedSegmentsMatch, Neo4jDriver> query = new Neo4jCoreQueryConnectedSegments(driver);
				final ModelOperation<Neo4jConnectedSegmentsMatch, Neo4jDriver> operation = ModelOperation.of(query);
				return operation;
			}
			case CONNECTEDSEGMENTS_INJECT: {
				final ModelQuery<Neo4jConnectedSegmentsInjectMatch, Neo4jDriver> query = new Neo4jCoreQueryConnectedSegmentsInject(driver);
				final Neo4jCoreTransformation<Neo4jConnectedSegmentsInjectMatch> transformation = new Neo4jCoreTransformationInjectConnectedSegments(driver);
				final ModelOperation<Neo4jConnectedSegmentsInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case CONNECTEDSEGMENTS_REPAIR: {
				final ModelQuery<Neo4jConnectedSegmentsMatch, Neo4jDriver> query = new Neo4jCoreQueryConnectedSegments(driver);
				final Neo4jCoreTransformation<Neo4jConnectedSegmentsMatch> transformation = new Neo4jCoreTransformationRepairConnectedSegments(driver);
				final ModelOperation<Neo4jConnectedSegmentsMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

				// PosLength
			case POSLENGTH: {
				final ModelQuery<Neo4jPosLengthMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH);
				final ModelOperation<Neo4jPosLengthMatch, Neo4jDriver> operation = ModelOperation.of(query);
				return operation;
			}
			case POSLENGTH_INJECT: {
				final ModelQuery<Neo4jPosLengthInjectMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH_INJECT);
				final Neo4jCypherTransformation<Neo4jPosLengthInjectMatch> transformation = new Neo4jCypherTransformationInjectPosLength(driver, workspaceDir);
				final ModelOperation<Neo4jPosLengthInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case POSLENGTH_REPAIR: {
				final ModelQuery<Neo4jPosLengthMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH);
				final Neo4jCypherTransformation<Neo4jPosLengthMatch> transformation = new Neo4jCypherTransformationRepairPosLength(driver, workspaceDir);
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
				final ModelQuery<Neo4jRouteSensorInjectMatch, Neo4jDriver> query = new Neo4jCoreQueryRouteSensorInject(driver);
				final Neo4jCoreTransformation<Neo4jRouteSensorInjectMatch> transformation = new Neo4jCoreTransformationInjectRouteSensor(driver);
				final ModelOperation<Neo4jRouteSensorInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case ROUTESENSOR_REPAIR: {
				final ModelQuery<Neo4jRouteSensorMatch, Neo4jDriver> query = new Neo4jCoreQueryRouteSensor(driver);
				final Neo4jCoreTransformation<Neo4jRouteSensorMatch> transformation = new Neo4jCoreTransformationRepairRouteSensor(driver);
				final ModelOperation<Neo4jRouteSensorMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

				// SemaphoreNeighbor
			case SEMAPHORENEIGHBOR: {
				final ModelQuery<Neo4jSemaphoreNeighborMatch, Neo4jDriver> query = new Neo4jCoreQuerySemaphoreNeighbor(driver);
				final ModelOperation<Neo4jSemaphoreNeighborMatch, Neo4jDriver> operation = ModelOperation.of(query);
				return operation;
			}
			case SEMAPHORENEIGHBOR_INJECT: {
				final ModelQuery<Neo4jSemaphoreNeighborInjectMatch, Neo4jDriver> query = new Neo4jCoreQuerySemaphoreNeighborInject(driver);
				final Neo4jCoreTransformation<Neo4jSemaphoreNeighborInjectMatch> transformation = new Neo4jCoreTransformationInjectSemaphoreNeighbor(driver);
				final ModelOperation<Neo4jSemaphoreNeighborInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case SEMAPHORENEIGHBOR_REPAIR: {
				final ModelQuery<Neo4jSemaphoreNeighborMatch, Neo4jDriver> query = new Neo4jCoreQuerySemaphoreNeighbor(driver);
				final Neo4jCoreTransformation<Neo4jSemaphoreNeighborMatch> transformation = new Neo4jCoreTransformationRepairSemaphoreNeighbor(driver);
				final ModelOperation<Neo4jSemaphoreNeighborMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

				// SwitchMonitored
			case SWITCHMONITORED: {
				final ModelQuery<Neo4jSwitchMonitoredMatch, Neo4jDriver> query = new Neo4jCoreQuerySwitchMonitored(driver);
				final ModelOperation<Neo4jSwitchMonitoredMatch, Neo4jDriver> operation = ModelOperation.of(query);
				return operation;
			}
			case SWITCHMONITORED_INJECT: {
				final ModelQuery<Neo4jSwitchMonitoredInjectMatch, Neo4jDriver> query = new Neo4jCoreQuerySwitchMonitoredInject(driver);
				final Neo4jCoreTransformation<Neo4jSwitchMonitoredInjectMatch> transformation = new Neo4jCoreTransformationInjectSwitchMonitored(driver);
				final ModelOperation<Neo4jSwitchMonitoredInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case SWITCHMONITORED_REPAIR: {
				final ModelQuery<Neo4jSwitchMonitoredMatch, Neo4jDriver> query = new Neo4jCoreQuerySwitchMonitored(driver);
				final Neo4jCoreTransformation<Neo4jSwitchMonitoredMatch> transformation = new Neo4jCoreTransformationRepairSwitchMonitored(driver);
				final ModelOperation<Neo4jSwitchMonitoredMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

				// SwitchSet
			case SWITCHSET: {
				final ModelQuery<Neo4jSwitchSetMatch, Neo4jDriver> query = new Neo4jCoreQuerySwitchSet(driver);
				final ModelOperation<Neo4jSwitchSetMatch, Neo4jDriver> operation = ModelOperation.of(query);
				return operation;
			}
			case SWITCHSET_INJECT: {
				final ModelQuery<Neo4jSwitchSetInjectMatch, Neo4jDriver> query = new Neo4jCoreQuerySwitchSetInject(driver);
				final Neo4jCoreTransformation<Neo4jSwitchSetInjectMatch> transformation = new Neo4jCoreTransformationInjectSwitchSet(driver);
				final ModelOperation<Neo4jSwitchSetInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case SWITCHSET_REPAIR: {
				final ModelQuery<Neo4jSwitchSetMatch, Neo4jDriver> query = new Neo4jCoreQuerySwitchSet(driver);
				final Neo4jCoreTransformation<Neo4jSwitchSetMatch> transformation = new Neo4jCoreTransformationRepairSwitchSet(driver);
				final ModelOperation<Neo4jSwitchSetMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

			default:
				throw new UnsupportedOperationException("Operation " + operationEnum + " not supported for Neo4j engine " + neo4jEngine + ".");
			}
		default:
			throw new UnsupportedOperationException("Neo4j engine " + neo4jEngine + " not supported.");
		}

	}

}
