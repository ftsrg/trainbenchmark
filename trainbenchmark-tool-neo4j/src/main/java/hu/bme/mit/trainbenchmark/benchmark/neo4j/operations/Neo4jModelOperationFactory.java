package hu.bme.mit.trainbenchmark.benchmark.neo4j.operations;

import hu.bme.mit.trainbenchmark.benchmark.neo4j.config.Neo4jEngine;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jActiveRouteMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteReachabilityMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.api.Neo4JApiQueryConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.api.Neo4JApiQueryConnectedSegmentsInject;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.api.Neo4JApiQueryPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.api.Neo4JApiQueryPosLengthInject;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.api.Neo4JApiQueryRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.api.Neo4JApiQueryRouteSensorInject;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.api.Neo4JApiQuerySemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.api.Neo4JApiQuerySemaphoreNeighborInject;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.api.Neo4JApiQuerySwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.api.Neo4JApiQuerySwitchMonitoredInject;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.api.Neo4JApiQuerySwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.api.Neo4JApiQuerySwitchSetInject;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.queries.cypher.Neo4jCypherQuery;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jApiTransformation;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.Neo4jCypherTransformation;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.inject.Neo4jApiTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.inject.Neo4jApiTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.inject.Neo4jApiTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.inject.Neo4jApiTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.inject.Neo4jApiTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.inject.Neo4jApiTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.repair.Neo4jApiTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.repair.Neo4jApiTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.repair.Neo4jApiTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.repair.Neo4jApiTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.repair.Neo4jApiTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.api.repair.Neo4jApiTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.cypher.inject.Neo4jCypherTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.cypher.inject.Neo4jCypherTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.cypher.inject.Neo4jCypherTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.cypher.inject.Neo4jCypherTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.cypher.inject.Neo4jCypherTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.cypher.inject.Neo4jCypherTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.cypher.repair.Neo4jCypherTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.cypher.repair.Neo4jCypherTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.cypher.repair.Neo4jCypherTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.cypher.repair.Neo4jCypherTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.cypher.repair.Neo4jCypherTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.cypher.repair.Neo4jCypherTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class Neo4jModelOperationFactory extends ModelOperationFactory<Neo4jMatch, Neo4jDriver> {

	protected final Neo4jEngine engine;

	public Neo4jModelOperationFactory(final Neo4jEngine engine) {
		this.engine = engine;
	}

	@Override
	public ModelOperation<? extends Neo4jMatch, Neo4jDriver> createOperation(final RailwayOperation operationEnum, final String workspaceDir,
			final Neo4jDriver driver) throws Exception {

		switch (engine) {
			case CORE_API:
				switch (operationEnum) {
					// ConnectedSegments
				case CONNECTEDSEGMENTS: {
					final ModelQuery<Neo4jConnectedSegmentsMatch, Neo4jDriver> query = new Neo4JApiQueryConnectedSegments(driver);
					final ModelOperation<Neo4jConnectedSegmentsMatch, Neo4jDriver> operation = ModelOperation.of(query);
					return operation;
				}
				case CONNECTEDSEGMENTS_INJECT: {
					final ModelQuery<Neo4jConnectedSegmentsInjectMatch, Neo4jDriver> query = new Neo4JApiQueryConnectedSegmentsInject(driver);
					final Neo4jApiTransformation<Neo4jConnectedSegmentsInjectMatch> transformation = new Neo4jApiTransformationInjectConnectedSegments(driver);
					final ModelOperation<Neo4jConnectedSegmentsInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}
				case CONNECTEDSEGMENTS_REPAIR: {
					final ModelQuery<Neo4jConnectedSegmentsMatch, Neo4jDriver> query = new Neo4JApiQueryConnectedSegments(driver);
					final Neo4jApiTransformation<Neo4jConnectedSegmentsMatch> transformation = new Neo4jApiTransformationRepairConnectedSegments(driver);
					final ModelOperation<Neo4jConnectedSegmentsMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}

					// PosLength
				case POSLENGTH: {
					final ModelQuery<Neo4jPosLengthMatch, Neo4jDriver> query = new Neo4JApiQueryPosLength(driver);
					final ModelOperation<Neo4jPosLengthMatch, Neo4jDriver> operation = ModelOperation.of(query);
					return operation;
				}
				case POSLENGTH_INJECT: {
					final ModelQuery<Neo4jPosLengthInjectMatch, Neo4jDriver> query = new Neo4JApiQueryPosLengthInject(driver);
					final Neo4jApiTransformation<Neo4jPosLengthInjectMatch> transformation = new Neo4jApiTransformationInjectPosLength(driver);
					final ModelOperation<Neo4jPosLengthInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}
				case POSLENGTH_REPAIR: {
					final ModelQuery<Neo4jPosLengthMatch, Neo4jDriver> query = new Neo4JApiQueryPosLength(driver);
					final Neo4jApiTransformation<Neo4jPosLengthMatch> transformation = new Neo4jApiTransformationRepairPosLength(driver);
					final ModelOperation<Neo4jPosLengthMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}

					// RouteSensor
				case ROUTESENSOR: {
					final ModelQuery<Neo4jRouteSensorMatch, Neo4jDriver> query = new Neo4JApiQueryRouteSensor(driver);
					final ModelOperation<Neo4jRouteSensorMatch, Neo4jDriver> operation = ModelOperation.of(query);
					return operation;
				}
				case ROUTESENSOR_INJECT: {
					final ModelQuery<Neo4jRouteSensorInjectMatch, Neo4jDriver> query = new Neo4JApiQueryRouteSensorInject(driver);
					final Neo4jApiTransformation<Neo4jRouteSensorInjectMatch> transformation = new Neo4jApiTransformationInjectRouteSensor(driver);
					final ModelOperation<Neo4jRouteSensorInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}
				case ROUTESENSOR_REPAIR: {
					final ModelQuery<Neo4jRouteSensorMatch, Neo4jDriver> query = new Neo4JApiQueryRouteSensor(driver);
					final Neo4jApiTransformation<Neo4jRouteSensorMatch> transformation = new Neo4jApiTransformationRepairRouteSensor(driver);
					final ModelOperation<Neo4jRouteSensorMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}

					// SemaphoreNeighbor
				case SEMAPHORENEIGHBOR: {
					final ModelQuery<Neo4jSemaphoreNeighborMatch, Neo4jDriver> query = new Neo4JApiQuerySemaphoreNeighbor(driver);
					final ModelOperation<Neo4jSemaphoreNeighborMatch, Neo4jDriver> operation = ModelOperation.of(query);
					return operation;
				}
				case SEMAPHORENEIGHBOR_INJECT: {
					final ModelQuery<Neo4jSemaphoreNeighborInjectMatch, Neo4jDriver> query = new Neo4JApiQuerySemaphoreNeighborInject(driver);
					final Neo4jApiTransformation<Neo4jSemaphoreNeighborInjectMatch> transformation = new Neo4jApiTransformationInjectSemaphoreNeighbor(driver);
					final ModelOperation<Neo4jSemaphoreNeighborInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}
				case SEMAPHORENEIGHBOR_REPAIR: {
					final ModelQuery<Neo4jSemaphoreNeighborMatch, Neo4jDriver> query = new Neo4JApiQuerySemaphoreNeighbor(driver);
					final Neo4jApiTransformation<Neo4jSemaphoreNeighborMatch> transformation = new Neo4jApiTransformationRepairSemaphoreNeighbor(driver);
					final ModelOperation<Neo4jSemaphoreNeighborMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}

					// SwitchMonitored
				case SWITCHMONITORED: {
					final ModelQuery<Neo4jSwitchMonitoredMatch, Neo4jDriver> query = new Neo4JApiQuerySwitchMonitored(driver);
					final ModelOperation<Neo4jSwitchMonitoredMatch, Neo4jDriver> operation = ModelOperation.of(query);
					return operation;
				}
				case SWITCHMONITORED_INJECT: {
					final ModelQuery<Neo4jSwitchMonitoredInjectMatch, Neo4jDriver> query = new Neo4JApiQuerySwitchMonitoredInject(driver);
					final Neo4jApiTransformation<Neo4jSwitchMonitoredInjectMatch> transformation = new Neo4jApiTransformationInjectSwitchMonitored(driver);
					final ModelOperation<Neo4jSwitchMonitoredInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}
				case SWITCHMONITORED_REPAIR: {
					final ModelQuery<Neo4jSwitchMonitoredMatch, Neo4jDriver> query = new Neo4JApiQuerySwitchMonitored(driver);
					final Neo4jApiTransformation<Neo4jSwitchMonitoredMatch> transformation = new Neo4jApiTransformationRepairSwitchMonitored(driver);
					final ModelOperation<Neo4jSwitchMonitoredMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}

					// SwitchSet
				case SWITCHSET: {
					final ModelQuery<Neo4jSwitchSetMatch, Neo4jDriver> query = new Neo4JApiQuerySwitchSet(driver);
					final ModelOperation<Neo4jSwitchSetMatch, Neo4jDriver> operation = ModelOperation.of(query);
					return operation;
				}
				case SWITCHSET_INJECT: {
					final ModelQuery<Neo4jSwitchSetInjectMatch, Neo4jDriver> query = new Neo4JApiQuerySwitchSetInject(driver);
					final Neo4jApiTransformation<Neo4jSwitchSetInjectMatch> transformation = new Neo4jApiTransformationInjectSwitchSet(driver);
					final ModelOperation<Neo4jSwitchSetInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}
				case SWITCHSET_REPAIR: {
					final ModelQuery<Neo4jSwitchSetMatch, Neo4jDriver> query = new Neo4JApiQuerySwitchSet(driver);
					final Neo4jApiTransformation<Neo4jSwitchSetMatch> transformation = new Neo4jApiTransformationRepairSwitchSet(driver);
					final ModelOperation<Neo4jSwitchSetMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}
			}
			case CYPHER:
				switch (operationEnum) {
					// ActiveRoute
				case ACTIVEROUTE: {
					final ModelQuery<Neo4jActiveRouteMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir, RailwayQuery.ACTIVEROUTE);
					final ModelOperation<Neo4jActiveRouteMatch, Neo4jDriver> operation = ModelOperation.of(query);
					return operation;
				}

					// ConnectedSegments
				case CONNECTEDSEGMENTS: {
					final ModelQuery<Neo4jConnectedSegmentsMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
					final ModelOperation<Neo4jConnectedSegmentsMatch, Neo4jDriver> operation = ModelOperation.of(query);
					return operation;
				}
				case CONNECTEDSEGMENTS_INJECT: {
					final ModelQuery<Neo4jConnectedSegmentsInjectMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir,
							RailwayQuery.CONNECTEDSEGMENTS_INJECT);
					final Neo4jCypherTransformation<Neo4jConnectedSegmentsInjectMatch> transformation = new Neo4jCypherTransformationInjectConnectedSegments(driver,
							workspaceDir);
					final ModelOperation<Neo4jConnectedSegmentsInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}
				case CONNECTEDSEGMENTS_REPAIR: {
					final ModelQuery<Neo4jConnectedSegmentsMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
					final Neo4jCypherTransformation<Neo4jConnectedSegmentsMatch> transformation = new Neo4jCypherTransformationRepairConnectedSegments(driver,
							workspaceDir);
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

					// RouteReachability
				case ROUTELENGTH: {
					final ModelQuery<Neo4jRouteLengthMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir, RailwayQuery.ROUTELENGTH);
					final ModelOperation<Neo4jRouteLengthMatch, Neo4jDriver> operation = ModelOperation.of(query);
					return operation;
				}

					// RouteReachability
				case ROUTEREACHABILITY: {
					final ModelQuery<Neo4jRouteReachabilityMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir, RailwayQuery.ROUTEREACHABILITY);
					final ModelOperation<Neo4jRouteReachabilityMatch, Neo4jDriver> operation = ModelOperation.of(query);
					return operation;
				}

					// RouteSensor
				case ROUTESENSOR: {
					final ModelQuery<Neo4jRouteSensorMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
					final ModelOperation<Neo4jRouteSensorMatch, Neo4jDriver> operation = ModelOperation.of(query);
					return operation;
				}
				case ROUTESENSOR_INJECT: {
					final ModelQuery<Neo4jRouteSensorInjectMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir,
							RailwayQuery.ROUTESENSOR_INJECT);
					final Neo4jCypherTransformation<Neo4jRouteSensorInjectMatch> transformation = new Neo4jCypherTransformationInjectRouteSensor(driver,
							workspaceDir);
					final ModelOperation<Neo4jRouteSensorInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}
				case ROUTESENSOR_REPAIR: {
					final ModelQuery<Neo4jRouteSensorMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
					final Neo4jCypherTransformation<Neo4jRouteSensorMatch> transformation = new Neo4jCypherTransformationRepairRouteSensor(driver, workspaceDir);
					final ModelOperation<Neo4jRouteSensorMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}

					// SemaphoreNeighbor
				case SEMAPHORENEIGHBOR: {
					final ModelQuery<Neo4jSemaphoreNeighborMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
					final ModelOperation<Neo4jSemaphoreNeighborMatch, Neo4jDriver> operation = ModelOperation.of(query);
					return operation;
				}
				case SEMAPHORENEIGHBOR_INJECT: {
					final ModelQuery<Neo4jSemaphoreNeighborInjectMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir,
							RailwayQuery.SEMAPHORENEIGHBOR_INJECT);
					final Neo4jCypherTransformation<Neo4jSemaphoreNeighborInjectMatch> transformation = new Neo4jCypherTransformationInjectSemaphoreNeighbor(driver,
							workspaceDir);
					final ModelOperation<Neo4jSemaphoreNeighborInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}
				case SEMAPHORENEIGHBOR_REPAIR: {
					final ModelQuery<Neo4jSemaphoreNeighborMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
					final Neo4jCypherTransformation<Neo4jSemaphoreNeighborMatch> transformation = new Neo4jCypherTransformationRepairSemaphoreNeighbor(driver,
							workspaceDir);
					final ModelOperation<Neo4jSemaphoreNeighborMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}

					// SwitchMonitored
				case SWITCHMONITORED: {
					final ModelQuery<Neo4jSwitchMonitoredMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
					final ModelOperation<Neo4jSwitchMonitoredMatch, Neo4jDriver> operation = ModelOperation.of(query);
					return operation;
				}
				case SWITCHMONITORED_INJECT: {
					final ModelQuery<Neo4jSwitchMonitoredInjectMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir,
							RailwayQuery.SWITCHMONITORED_INJECT);
					final Neo4jCypherTransformation<Neo4jSwitchMonitoredInjectMatch> transformation = new Neo4jCypherTransformationInjectSwitchMonitored(driver,
							workspaceDir);
					final ModelOperation<Neo4jSwitchMonitoredInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}
				case SWITCHMONITORED_REPAIR: {
					final ModelQuery<Neo4jSwitchMonitoredMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
					final Neo4jCypherTransformation<Neo4jSwitchMonitoredMatch> transformation = new Neo4jCypherTransformationRepairSwitchMonitored(driver,
							workspaceDir);
					final ModelOperation<Neo4jSwitchMonitoredMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}

					// SwitchSet
				case SWITCHSET: {
					final ModelQuery<Neo4jSwitchSetMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET);
					final ModelOperation<Neo4jSwitchSetMatch, Neo4jDriver> operation = ModelOperation.of(query);
					return operation;
				}
				case SWITCHSET_INJECT: {
					final ModelQuery<Neo4jSwitchSetInjectMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET_INJECT);
					final Neo4jCypherTransformation<Neo4jSwitchSetInjectMatch> transformation = new Neo4jCypherTransformationInjectSwitchSet(driver, workspaceDir);
					final ModelOperation<Neo4jSwitchSetInjectMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}
				case SWITCHSET_REPAIR: {
					final ModelQuery<Neo4jSwitchSetMatch, Neo4jDriver> query = new Neo4jCypherQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET);
					final Neo4jCypherTransformation<Neo4jSwitchSetMatch> transformation = new Neo4jCypherTransformationRepairSwitchSet(driver, workspaceDir);
					final ModelOperation<Neo4jSwitchSetMatch, Neo4jDriver> operation = ModelOperation.of(query, transformation);
					return operation;
				}
			}
		}
		throw new UnsupportedOperationException("Operation " + operationEnum + " not supported for Neo4j engine " + engine + ".");
	}

}
