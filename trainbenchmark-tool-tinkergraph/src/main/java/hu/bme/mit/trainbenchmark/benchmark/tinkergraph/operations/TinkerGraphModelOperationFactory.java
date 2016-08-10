package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.operations;

import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.TinkerGraphQuery;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.TinkerGraphQueryConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.TinkerGraphQueryPosLength;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.TinkerGraphQueryRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.TinkerGraphQuerySemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.TinkerGraphQuerySwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.queries.TinkerGraphQuerySwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.TinkerGraphTransformation;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair.TinkerGraphTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class TinkerGraphModelOperationFactory<TDriver extends TinkerGraphDriver> extends ModelOperationFactory<TinkerGraphMatch, TDriver> {

	protected TinkerGraphModelOperationFactory() {

	}

	public static <TDriver extends TinkerGraphDriver> TinkerGraphModelOperationFactory<TDriver> create() {
		return new TinkerGraphModelOperationFactory<>();
	}

	@Override
	public ModelOperation<? extends TinkerGraphMatch, TDriver> createOperation(final RailwayOperation operationEnum, final Optional<String> workspacePath,
			final TDriver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final TinkerGraphQuery<TinkerGraphConnectedSegmentsMatch, TDriver> query = new TinkerGraphQueryConnectedSegments<>(driver);
			final ModelOperation<TinkerGraphConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			final TinkerGraphQuery<TinkerGraphConnectedSegmentsMatch, TDriver> query = new TinkerGraphQueryConnectedSegments<>(driver);
			final TinkerGraphTransformation<TinkerGraphConnectedSegmentsMatch, TDriver> transformation = new TinkerGraphTransformationRepairConnectedSegments<>(
					driver);
			final ModelOperation<TinkerGraphConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query, transformation);
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
			// TODO
			break;
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
			// TODO
			break;
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
			// TODO
			break;
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
			// TODO
			break;
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
			// TODO
			break;
		}
		case SWITCHSET_REPAIR: {
			final TinkerGraphQuery<TinkerGraphSwitchSetMatch, TDriver> query = new TinkerGraphQuerySwitchSet<>(driver);
			final TinkerGraphTransformation<TinkerGraphSwitchSetMatch, TDriver> transformation = new TinkerGraphTransformationRepairSwitchSet<>(driver);
			final ModelOperation<TinkerGraphSwitchSetMatch, TDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		}
		throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
	}

}
