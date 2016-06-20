package hu.bme.mit.trainbenchmark.benchmark.drools5.operations;

import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.drools5.checkers.Drools5Query;
import hu.bme.mit.trainbenchmark.benchmark.drools5.driver.Drools5Driver;
import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5RouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5SwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5SwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.EmfTransformation;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class Drools5ModelOperationFactory extends ModelOperationFactory<EmfMatch, Drools5Driver> {

	protected Drools5ModelOperationFactory() {

	}

	public static Drools5ModelOperationFactory create() {
		return new Drools5ModelOperationFactory();
	}

	@Override
	public ModelOperation<? extends EmfMatch, Drools5Driver> createOperation(final RailwayOperation operationEnum,
			final Optional<String> workspaceDir, final Drools5Driver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final Drools5Query<Drools5ConnectedSegmentsMatch> query = Drools5Query.create(driver, workspaceDir,
					RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<Drools5ConnectedSegmentsMatch, Drools5Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			// TODO

		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final Drools5Query<Drools5ConnectedSegmentsMatch> query = Drools5Query.create(driver, workspaceDir,
					RailwayQuery.CONNECTEDSEGMENTS);
			final EmfTransformationRepairConnectedSegments<Drools5Driver, Drools5ConnectedSegmentsMatch> transformation = new EmfTransformationRepairConnectedSegments<>(
					driver);
			final ModelOperation<Drools5ConnectedSegmentsMatch, Drools5Driver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final Drools5Query<Drools5PosLengthMatch> query = Drools5Query.create(driver, workspaceDir,
					RailwayQuery.POSLENGTH);
			final ModelOperation<Drools5PosLengthMatch, Drools5Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			// TODO
		}
		case POSLENGTH_REPAIR: {
			final Drools5Query<Drools5PosLengthMatch> query = Drools5Query.create(driver, workspaceDir,
					RailwayQuery.POSLENGTH);
			final EmfTransformation<Drools5PosLengthMatch, Drools5Driver> transformation = new EmfTransformationRepairPosLength<>(
					driver);
			final ModelOperation<Drools5PosLengthMatch, Drools5Driver> operation = ModelOperation.of(query,
					transformation);
			return operation;

		}

			// RouteSensor
		case ROUTESENSOR: {
			final Drools5Query<Drools5RouteSensorMatch> query = Drools5Query.create(driver, workspaceDir,
					RailwayQuery.ROUTESENSOR);
			final ModelOperation<Drools5RouteSensorMatch, Drools5Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			// TODO
		}
		case ROUTESENSOR_REPAIR: {
			final Drools5Query<Drools5RouteSensorMatch> query = Drools5Query.create(driver, workspaceDir,
					RailwayQuery.ROUTESENSOR);
			final EmfTransformationRepairRouteSensor<Drools5Driver, Drools5RouteSensorMatch> transformation = new EmfTransformationRepairRouteSensor<>(
					driver);
			final ModelOperation<Drools5RouteSensorMatch, Drools5Driver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final Drools5Query<Drools5SemaphoreNeighborMatch> query = Drools5Query.create(driver, workspaceDir,
					RailwayQuery.SEMAPHORENEIGHBOR);
			final ModelOperation<Drools5SemaphoreNeighborMatch, Drools5Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			// TODO
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final Drools5Query<Drools5SemaphoreNeighborMatch> query = Drools5Query.create(driver, workspaceDir,
					RailwayQuery.SEMAPHORENEIGHBOR);
			final EmfTransformationRepairSemaphoreNeighbor<Drools5Driver, Drools5SemaphoreNeighborMatch> transformation = new EmfTransformationRepairSemaphoreNeighbor<>(
					driver);
			final ModelOperation<Drools5SemaphoreNeighborMatch, Drools5Driver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final Drools5Query<Drools5SwitchMonitoredMatch> query = Drools5Query.create(driver, workspaceDir,
					RailwayQuery.SWITCHMONITORED);
			final ModelOperation<Drools5SwitchMonitoredMatch, Drools5Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			// TODO
		}
		case SWITCHMONITORED_REPAIR: {
			final Drools5Query<Drools5SwitchMonitoredMatch> query = Drools5Query.create(driver, workspaceDir,
					RailwayQuery.SWITCHMONITORED);
			final EmfTransformationRepairSwitchMonitored<Drools5Driver, Drools5SwitchMonitoredMatch> transformation = new EmfTransformationRepairSwitchMonitored<>(
					driver);
			final ModelOperation<Drools5SwitchMonitoredMatch, Drools5Driver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final Drools5Query<Drools5SwitchSetMatch> query = Drools5Query.create(driver, workspaceDir,
					RailwayQuery.SWITCHSET);
			final ModelOperation<Drools5SwitchSetMatch, Drools5Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			// TODO
		}
		case SWITCHSET_REPAIR: {
			final Drools5Query<Drools5SwitchSetMatch> query = Drools5Query.create(driver, workspaceDir,
					RailwayQuery.SWITCHSET);
			final EmfTransformation<Drools5SwitchSetMatch, Drools5Driver> transformation = new EmfTransformationRepairSwitchSet<>(
					driver);
			final ModelOperation<Drools5SwitchSetMatch, Drools5Driver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}

}
