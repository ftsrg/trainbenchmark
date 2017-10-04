package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.operations;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.config.TinkerGraphEngine;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.GraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.TinkerGraphQuery;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.api.TinkerGraphQueryConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.api.TinkerGraphQueryConnectedSegmentsInject;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.api.TinkerGraphQueryPosLength;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.api.TinkerGraphQueryPosLengthInject;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.api.TinkerGraphQueryRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.api.TinkerGraphQueryRouteSensorInject;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.api.TinkerGraphQuerySemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.api.TinkerGraphQuerySemaphoreNeighborInject;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.api.TinkerGraphQuerySwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.api.TinkerGraphQuerySwitchMonitoredInject;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.api.TinkerGraphQuerySwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.api.TinkerGraphQuerySwitchSetInject;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.gremlin.TinkerGraphGremlinQuery;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.TinkerGraphTransformation;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.inject.TinkerGraphTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.inject.TinkerGraphTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.inject.TinkerGraphTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.inject.TinkerGraphTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.inject.TinkerGraphTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.inject.TinkerGraphTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class TinkerGraphModelOperationFactory<TDriver extends GraphDriver> extends ModelOperationFactory<TinkerGraphMatch, TDriver> {

	protected final TinkerGraphEngine engine;

	public TinkerGraphModelOperationFactory(final TinkerGraphEngine engine) {
		this.engine = engine;
	}

	@Override
	public ModelOperation<? extends TinkerGraphMatch, TDriver> createOperation(final RailwayOperation operationEnum, final String workspaceDir,
			final TDriver driver) throws Exception {

		switch (engine) {
			case CORE_API:
				switch (operationEnum) {
					// ConnectedSegments
					case CONNECTEDSEGMENTS: {
						final TinkerGraphQuery<TinkerGraphConnectedSegmentsMatch, TDriver> query = new TinkerGraphQueryConnectedSegments<>(driver);
						final ModelOperation<TinkerGraphConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query);
						return operation;
					}
					case CONNECTEDSEGMENTS_INJECT: {
						final TinkerGraphQuery<TinkerGraphConnectedSegmentsInjectMatch, TDriver> query = new TinkerGraphQueryConnectedSegmentsInject<>(driver);
						final TinkerGraphTransformation<TinkerGraphConnectedSegmentsInjectMatch, TDriver> transformation = new TinkerGraphTransformationInjectConnectedSegments<>(
							driver);
						final ModelOperation<TinkerGraphConnectedSegmentsInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}
					case CONNECTEDSEGMENTS_REPAIR: {
						final TinkerGraphQuery<TinkerGraphConnectedSegmentsMatch, TDriver> query = new TinkerGraphQueryConnectedSegments<>(driver);
						final TinkerGraphTransformation<TinkerGraphConnectedSegmentsMatch, TDriver> transformation = new TinkerGraphTransformationRepairConnectedSegments<>(
							driver);
						final ModelOperation<TinkerGraphConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}

					// PosLength
					case POSLENGTH: {
						final TinkerGraphQuery<TinkerGraphPosLengthMatch, TDriver> query = new TinkerGraphQueryPosLength<>(driver);
						final ModelOperation<TinkerGraphPosLengthMatch, TDriver> operation = ModelOperation.of(query);
						return operation;
					}
					case POSLENGTH_INJECT: {
						final TinkerGraphQuery<TinkerGraphPosLengthInjectMatch, TDriver> query = new TinkerGraphQueryPosLengthInject<>(driver);
						final TinkerGraphTransformation<TinkerGraphPosLengthInjectMatch, TDriver> transformation = new TinkerGraphTransformationInjectPosLength<>(driver);
						final ModelOperation<TinkerGraphPosLengthInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}
					case POSLENGTH_REPAIR: {
						final TinkerGraphQuery<TinkerGraphPosLengthMatch, TDriver> query = new TinkerGraphQueryPosLength<>(driver);
						final TinkerGraphTransformation<TinkerGraphPosLengthMatch, TDriver> transformation = new TinkerGraphTransformationRepairPosLength<>(driver);
						final ModelOperation<TinkerGraphPosLengthMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}

					// RouteSensor
					case ROUTESENSOR: {
						final TinkerGraphQuery<TinkerGraphRouteSensorMatch, TDriver> query = new TinkerGraphQueryRouteSensor<>(driver);
						final ModelOperation<TinkerGraphRouteSensorMatch, TDriver> operation = ModelOperation.of(query);
						return operation;
					}
					case ROUTESENSOR_INJECT: {
						final TinkerGraphQuery<TinkerGraphRouteSensorInjectMatch, TDriver> query = new TinkerGraphQueryRouteSensorInject<>(driver);
						final TinkerGraphTransformation<TinkerGraphRouteSensorInjectMatch, TDriver> transformation = new TinkerGraphTransformationInjectRouteSensor<>(
							driver);
						final ModelOperation<TinkerGraphRouteSensorInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}
					case ROUTESENSOR_REPAIR: {
						final TinkerGraphQuery<TinkerGraphRouteSensorMatch, TDriver> query = new TinkerGraphQueryRouteSensor<>(driver);
						final TinkerGraphTransformation<TinkerGraphRouteSensorMatch, TDriver> transformation = new TinkerGraphTransformationRepairRouteSensor<>(driver);
						final ModelOperation<TinkerGraphRouteSensorMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}

					// SemaphoreNeighbor
					case SEMAPHORENEIGHBOR: {
						final TinkerGraphQuery<TinkerGraphSemaphoreNeighborMatch, TDriver> query = new TinkerGraphQuerySemaphoreNeighbor<>(driver);
						final ModelOperation<TinkerGraphSemaphoreNeighborMatch, TDriver> operation = ModelOperation.of(query);
						return operation;
					}
					case SEMAPHORENEIGHBOR_INJECT: {
						final TinkerGraphQuery<TinkerGraphSemaphoreNeighborInjectMatch, TDriver> query = new TinkerGraphQuerySemaphoreNeighborInject<>(driver);
						final TinkerGraphTransformation<TinkerGraphSemaphoreNeighborInjectMatch, TDriver> transformation = new TinkerGraphTransformationInjectSemaphoreNeighbor<>(
							driver);
						final ModelOperation<TinkerGraphSemaphoreNeighborInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}
					case SEMAPHORENEIGHBOR_REPAIR: {
						final TinkerGraphQuery<TinkerGraphSemaphoreNeighborMatch, TDriver> query = new TinkerGraphQuerySemaphoreNeighbor<>(driver);
						final TinkerGraphTransformation<TinkerGraphSemaphoreNeighborMatch, TDriver> transformation = new TinkerGraphTransformationRepairSemaphoreNeighbor<>(
							driver);
						final ModelOperation<TinkerGraphSemaphoreNeighborMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}

					// SwitchMonitored
					case SWITCHMONITORED: {
						final TinkerGraphQuery<TinkerGraphSwitchMonitoredMatch, TDriver> query = new TinkerGraphQuerySwitchMonitored<>(driver);
						final ModelOperation<TinkerGraphSwitchMonitoredMatch, TDriver> operation = ModelOperation.of(query);
						return operation;
					}
					case SWITCHMONITORED_INJECT: {
						final TinkerGraphQuery<TinkerGraphSwitchMonitoredInjectMatch, TDriver> query = new TinkerGraphQuerySwitchMonitoredInject<>(driver);
						final TinkerGraphTransformation<TinkerGraphSwitchMonitoredInjectMatch, TDriver> transformation = new TinkerGraphTransformationInjectSwitchMonitored<>(
							driver);
						final ModelOperation<TinkerGraphSwitchMonitoredInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}
					case SWITCHMONITORED_REPAIR: {
						final TinkerGraphQuery<TinkerGraphSwitchMonitoredMatch, TDriver> query = new TinkerGraphQuerySwitchMonitored<>(driver);
						final TinkerGraphTransformation<TinkerGraphSwitchMonitoredMatch, TDriver> transformation = new TinkerGraphTransformationRepairSwitchMonitored<>(
							driver);
						final ModelOperation<TinkerGraphSwitchMonitoredMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}

					// SwitchSet
					case SWITCHSET: {
						final TinkerGraphQuery<TinkerGraphSwitchSetMatch, TDriver> query = new TinkerGraphQuerySwitchSet<>(driver);
						final ModelOperation<TinkerGraphSwitchSetMatch, TDriver> operation = ModelOperation.of(query);
						return operation;
					}
					case SWITCHSET_INJECT: {
						final TinkerGraphQuery<TinkerGraphSwitchSetInjectMatch, TDriver> query = new TinkerGraphQuerySwitchSetInject<>(driver);
						final TinkerGraphTransformation<TinkerGraphSwitchSetInjectMatch, TDriver> transformation = new TinkerGraphTransformationInjectSwitchSet<>(driver);
						final ModelOperation<TinkerGraphSwitchSetInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}
					case SWITCHSET_REPAIR: {
						final TinkerGraphQuery<TinkerGraphSwitchSetMatch, TDriver> query = new TinkerGraphQuerySwitchSet<>(driver);
						final TinkerGraphTransformation<TinkerGraphSwitchSetMatch, TDriver> transformation = new TinkerGraphTransformationRepairSwitchSet<>(driver);
						final ModelOperation<TinkerGraphSwitchSetMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}
				}
			case GREMLIN:
				switch (operationEnum) {
					// ConnectedSegments
					case CONNECTEDSEGMENTS: {
						final TinkerGraphQuery<TinkerGraphConnectedSegmentsMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
						final ModelOperation<TinkerGraphConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query);
						return operation;
					}
					case CONNECTEDSEGMENTS_INJECT: {
						final TinkerGraphQuery<TinkerGraphConnectedSegmentsInjectMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS_INJECT);
						final TinkerGraphTransformation<TinkerGraphConnectedSegmentsInjectMatch, TDriver> transformation = new TinkerGraphTransformationInjectConnectedSegments<>(
							driver);
						final ModelOperation<TinkerGraphConnectedSegmentsInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}
					case CONNECTEDSEGMENTS_REPAIR: {
						final TinkerGraphQuery<TinkerGraphConnectedSegmentsMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
						final TinkerGraphTransformation<TinkerGraphConnectedSegmentsMatch, TDriver> transformation = new TinkerGraphTransformationRepairConnectedSegments<>(
							driver);
						final ModelOperation<TinkerGraphConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}

					// PosLength
					case POSLENGTH: {
						final TinkerGraphQuery<TinkerGraphPosLengthMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH);
						final ModelOperation<TinkerGraphPosLengthMatch, TDriver> operation = ModelOperation.of(query);
						return operation;
					}
					case POSLENGTH_INJECT: {
						final TinkerGraphQuery<TinkerGraphPosLengthInjectMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH_INJECT);
						final TinkerGraphTransformation<TinkerGraphPosLengthInjectMatch, TDriver> transformation = new TinkerGraphTransformationInjectPosLength<>(driver);
						final ModelOperation<TinkerGraphPosLengthInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}
					case POSLENGTH_REPAIR: {
						final TinkerGraphQuery<TinkerGraphPosLengthMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH);
						final TinkerGraphTransformation<TinkerGraphPosLengthMatch, TDriver> transformation = new TinkerGraphTransformationRepairPosLength<>(driver);
						final ModelOperation<TinkerGraphPosLengthMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}

					// RouteSensor
					case ROUTESENSOR: {
						final TinkerGraphQuery<TinkerGraphRouteSensorMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
						final ModelOperation<TinkerGraphRouteSensorMatch, TDriver> operation = ModelOperation.of(query);
						return operation;
					}
					case ROUTESENSOR_INJECT: {
						final TinkerGraphQuery<TinkerGraphRouteSensorInjectMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.ROUTESENSOR_INJECT);
						final TinkerGraphTransformation<TinkerGraphRouteSensorInjectMatch, TDriver> transformation = new TinkerGraphTransformationInjectRouteSensor<>(
							driver);
						final ModelOperation<TinkerGraphRouteSensorInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}
					case ROUTESENSOR_REPAIR: {
						final TinkerGraphQuery<TinkerGraphRouteSensorMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
						final TinkerGraphTransformation<TinkerGraphRouteSensorMatch, TDriver> transformation = new TinkerGraphTransformationRepairRouteSensor<>(driver);
						final ModelOperation<TinkerGraphRouteSensorMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}

					// SemaphoreNeighbor
					case SEMAPHORENEIGHBOR: {
						final TinkerGraphQuery<TinkerGraphSemaphoreNeighborMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
						final ModelOperation<TinkerGraphSemaphoreNeighborMatch, TDriver> operation = ModelOperation.of(query);
						return operation;
					}
					case SEMAPHORENEIGHBOR_INJECT: {
						final TinkerGraphQuery<TinkerGraphSemaphoreNeighborInjectMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR_INJECT);
						final TinkerGraphTransformation<TinkerGraphSemaphoreNeighborInjectMatch, TDriver> transformation = new TinkerGraphTransformationInjectSemaphoreNeighbor<>(
							driver);
						final ModelOperation<TinkerGraphSemaphoreNeighborInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}
					case SEMAPHORENEIGHBOR_REPAIR: {
						final TinkerGraphQuery<TinkerGraphSemaphoreNeighborMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
						final TinkerGraphTransformation<TinkerGraphSemaphoreNeighborMatch, TDriver> transformation = new TinkerGraphTransformationRepairSemaphoreNeighbor<>(
							driver);
						final ModelOperation<TinkerGraphSemaphoreNeighborMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}

					// SwitchMonitored
					case SWITCHMONITORED: {
						final TinkerGraphQuery<TinkerGraphSwitchMonitoredMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
						final ModelOperation<TinkerGraphSwitchMonitoredMatch, TDriver> operation = ModelOperation.of(query);
						return operation;
					}
					case SWITCHMONITORED_INJECT: {
						final TinkerGraphQuery<TinkerGraphSwitchMonitoredInjectMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.SWITCHMONITORED_INJECT);
						final TinkerGraphTransformation<TinkerGraphSwitchMonitoredInjectMatch, TDriver> transformation = new TinkerGraphTransformationInjectSwitchMonitored<>(
							driver);
						final ModelOperation<TinkerGraphSwitchMonitoredInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}
					case SWITCHMONITORED_REPAIR: {
						final TinkerGraphQuery<TinkerGraphSwitchMonitoredMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
						final TinkerGraphTransformation<TinkerGraphSwitchMonitoredMatch, TDriver> transformation = new TinkerGraphTransformationRepairSwitchMonitored<>(
							driver);
						final ModelOperation<TinkerGraphSwitchMonitoredMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}

					// SwitchSet
					case SWITCHSET: {
						final TinkerGraphQuery<TinkerGraphSwitchSetMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET);
						final ModelOperation<TinkerGraphSwitchSetMatch, TDriver> operation = ModelOperation.of(query);
						return operation;
					}
					case SWITCHSET_INJECT: {
						final TinkerGraphQuery<TinkerGraphSwitchSetInjectMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET_INJECT);
						final TinkerGraphTransformation<TinkerGraphSwitchSetInjectMatch, TDriver> transformation = new TinkerGraphTransformationInjectSwitchSet<>(driver);
						final ModelOperation<TinkerGraphSwitchSetInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}
					case SWITCHSET_REPAIR: {
						final TinkerGraphQuery<TinkerGraphSwitchSetMatch, TDriver> query = new TinkerGraphGremlinQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET);
						final TinkerGraphTransformation<TinkerGraphSwitchSetMatch, TDriver> transformation = new TinkerGraphTransformationRepairSwitchSet<>(driver);
						final ModelOperation<TinkerGraphSwitchSetMatch, TDriver> operation = ModelOperation.of(query, transformation);
						return operation;
					}
				}
		}
		throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
	}

}
