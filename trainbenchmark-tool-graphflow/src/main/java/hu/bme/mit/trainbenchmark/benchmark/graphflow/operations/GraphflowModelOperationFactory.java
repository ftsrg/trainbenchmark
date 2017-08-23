package hu.bme.mit.trainbenchmark.benchmark.graphflow.operations;

import hu.bme.mit.trainbenchmark.benchmark.graphflow.driver.GraphflowDriver;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowMatch;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.queries.GraphflowQuery;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.GraphflowTransformation;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.inject.GraphflowTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.inject.GraphflowTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.inject.GraphflowTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.inject.GraphflowTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.inject.GraphflowTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.inject.GraphflowTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.repair.GraphflowTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.repair.GraphflowTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.repair.GraphflowTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.repair.GraphflowTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.repair.GraphflowTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.repair.GraphflowTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class GraphflowModelOperationFactory extends ModelOperationFactory<GraphflowMatch, GraphflowDriver> {

	public GraphflowModelOperationFactory() {
	}

	@Override
	public ModelOperation<? extends GraphflowMatch, GraphflowDriver> createOperation(final RailwayOperation operationEnum, final String workspaceDir,
																					 final GraphflowDriver driver) throws Exception {
		switch (operationEnum) {
			// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final ModelQuery<GraphflowConnectedSegmentsMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<GraphflowConnectedSegmentsMatch, GraphflowDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			final ModelQuery<GraphflowConnectedSegmentsInjectMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir,
					RailwayQuery.CONNECTEDSEGMENTS_INJECT);
			final GraphflowTransformation<GraphflowConnectedSegmentsInjectMatch> transformation = new GraphflowTransformationInjectConnectedSegments(driver,
					workspaceDir);
			final ModelOperation<GraphflowConnectedSegmentsInjectMatch, GraphflowDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final ModelQuery<GraphflowConnectedSegmentsMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final GraphflowTransformation<GraphflowConnectedSegmentsMatch> transformation = new GraphflowTransformationRepairConnectedSegments(driver,
					workspaceDir);
			final ModelOperation<GraphflowConnectedSegmentsMatch, GraphflowDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final ModelQuery<GraphflowPosLengthMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH);
			final ModelOperation<GraphflowPosLengthMatch, GraphflowDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			final ModelQuery<GraphflowPosLengthInjectMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH_INJECT);
			final GraphflowTransformation<GraphflowPosLengthInjectMatch> transformation = new GraphflowTransformationInjectPosLength(driver, workspaceDir);
			final ModelOperation<GraphflowPosLengthInjectMatch, GraphflowDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case POSLENGTH_REPAIR: {
			final ModelQuery<GraphflowPosLengthMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir, RailwayQuery.POSLENGTH);
			final GraphflowTransformation<GraphflowPosLengthMatch> transformation = new GraphflowTransformationRepairPosLength(driver, workspaceDir);
			final ModelOperation<GraphflowPosLengthMatch, GraphflowDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// RouteSensor
		case ROUTESENSOR: {
			final ModelQuery<GraphflowRouteSensorMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
			final ModelOperation<GraphflowRouteSensorMatch, GraphflowDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			final ModelQuery<GraphflowRouteSensorInjectMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir,
					RailwayQuery.ROUTESENSOR_INJECT);
			final GraphflowTransformation<GraphflowRouteSensorInjectMatch> transformation = new GraphflowTransformationInjectRouteSensor(driver,
					workspaceDir);
			final ModelOperation<GraphflowRouteSensorInjectMatch, GraphflowDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case ROUTESENSOR_REPAIR: {
			final ModelQuery<GraphflowRouteSensorMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
			final GraphflowTransformation<GraphflowRouteSensorMatch> transformation = new GraphflowTransformationRepairRouteSensor(driver, workspaceDir);
			final ModelOperation<GraphflowRouteSensorMatch, GraphflowDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final ModelQuery<GraphflowSemaphoreNeighborMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final ModelOperation<GraphflowSemaphoreNeighborMatch, GraphflowDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			final ModelQuery<GraphflowSemaphoreNeighborInjectMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir,
					RailwayQuery.SEMAPHORENEIGHBOR_INJECT);
			final GraphflowTransformation<GraphflowSemaphoreNeighborInjectMatch> transformation = new GraphflowTransformationInjectSemaphoreNeighbor(driver,
					workspaceDir);
			final ModelOperation<GraphflowSemaphoreNeighborInjectMatch, GraphflowDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final ModelQuery<GraphflowSemaphoreNeighborMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final GraphflowTransformation<GraphflowSemaphoreNeighborMatch> transformation = new GraphflowTransformationRepairSemaphoreNeighbor(driver,
					workspaceDir);
			final ModelOperation<GraphflowSemaphoreNeighborMatch, GraphflowDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final ModelQuery<GraphflowSwitchMonitoredMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
			final ModelOperation<GraphflowSwitchMonitoredMatch, GraphflowDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			final ModelQuery<GraphflowSwitchMonitoredInjectMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir,
					RailwayQuery.SWITCHMONITORED_INJECT);
			final GraphflowTransformation<GraphflowSwitchMonitoredInjectMatch> transformation = new GraphflowTransformationInjectSwitchMonitored(driver,
					workspaceDir);
			final ModelOperation<GraphflowSwitchMonitoredInjectMatch, GraphflowDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHMONITORED_REPAIR: {
			final ModelQuery<GraphflowSwitchMonitoredMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
			final GraphflowTransformation<GraphflowSwitchMonitoredMatch> transformation = new GraphflowTransformationRepairSwitchMonitored(driver,
					workspaceDir);
			final ModelOperation<GraphflowSwitchMonitoredMatch, GraphflowDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final ModelQuery<GraphflowSwitchSetMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET);
			final ModelOperation<GraphflowSwitchSetMatch, GraphflowDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			final ModelQuery<GraphflowSwitchSetInjectMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET_INJECT);
			final GraphflowTransformation<GraphflowSwitchSetInjectMatch> transformation = new GraphflowTransformationInjectSwitchSet(driver, workspaceDir);
			final ModelOperation<GraphflowSwitchSetInjectMatch, GraphflowDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHSET_REPAIR: {
			final ModelQuery<GraphflowSwitchSetMatch, GraphflowDriver> query = new GraphflowQuery<>(driver, workspaceDir, RailwayQuery.SWITCHSET);
			final GraphflowTransformation<GraphflowSwitchSetMatch> transformation = new GraphflowTransformationRepairSwitchSet(driver, workspaceDir);
			final ModelOperation<GraphflowSwitchSetMatch, GraphflowDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}

}
