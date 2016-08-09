package hu.bme.mit.trainbenchmark.benchmark.drools5.operations;

import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.drools5.driver.Drools5Driver;
import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5RouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5SwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools5.matches.Drools5SwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools5.queries.Drools5Query;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.EmfTransformation;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EmfTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EmfTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EmfTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EmfTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EmfTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.inject.EmfTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query.EmfApiQuery;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query.EmfApiQueryConnectedSegmentsInject;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query.EmfApiQueryPosLengthInject;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query.EmfApiQueryRouteSensorInject;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query.EmfApiQuerySemaphoreNeighborInject;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query.EmfApiQuerySwitchMonitoredInject;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query.EmfApiQuerySwitchSetInject;
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
	public ModelOperation<? extends EmfMatch, Drools5Driver> createOperation(final RailwayOperation operationEnum, final Optional<String> workspaceDir,
			final Drools5Driver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final Drools5Query<Drools5ConnectedSegmentsMatch> query = Drools5Query.create(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<Drools5ConnectedSegmentsMatch, Drools5Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			final EmfApiQuery<EmfConnectedSegmentsInjectMatch, Drools5Driver> query = new EmfApiQueryConnectedSegmentsInject<>(driver);
			final EmfTransformation<EmfConnectedSegmentsInjectMatch, Drools5Driver> transformation = new EmfTransformationInjectConnectedSegments<>(driver);
			final ModelOperation<EmfConnectedSegmentsInjectMatch, Drools5Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final Drools5Query<Drools5ConnectedSegmentsMatch> query = Drools5Query.create(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final EmfTransformationRepairConnectedSegments<Drools5Driver, Drools5ConnectedSegmentsMatch> transformation = new EmfTransformationRepairConnectedSegments<>(
					driver);
			final ModelOperation<Drools5ConnectedSegmentsMatch, Drools5Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final Drools5Query<Drools5PosLengthMatch> query = Drools5Query.create(driver, workspaceDir, RailwayQuery.POSLENGTH);
			final ModelOperation<Drools5PosLengthMatch, Drools5Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			final EmfApiQuery<EmfPosLengthInjectMatch, Drools5Driver> query = new EmfApiQueryPosLengthInject<>(driver);
			final EmfTransformation<EmfPosLengthInjectMatch, Drools5Driver> transformation = new EmfTransformationInjectPosLength<>(driver);
			final ModelOperation<EmfPosLengthInjectMatch, Drools5Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case POSLENGTH_REPAIR: {
			final Drools5Query<Drools5PosLengthMatch> query = Drools5Query.create(driver, workspaceDir, RailwayQuery.POSLENGTH);
			final EmfTransformation<Drools5PosLengthMatch, Drools5Driver> transformation = new EmfTransformationRepairPosLength<>(driver);
			final ModelOperation<Drools5PosLengthMatch, Drools5Driver> operation = ModelOperation.of(query, transformation);
			return operation;

		}

			// RouteSensor
		case ROUTESENSOR: {
			final Drools5Query<Drools5RouteSensorMatch> query = Drools5Query.create(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
			final ModelOperation<Drools5RouteSensorMatch, Drools5Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			final EmfApiQuery<EmfRouteSensorInjectMatch, Drools5Driver> query = new EmfApiQueryRouteSensorInject<>(driver);
			final EmfTransformation<EmfRouteSensorInjectMatch, Drools5Driver> transformation = new EmfTransformationInjectRouteSensor<>(driver);
			final ModelOperation<EmfRouteSensorInjectMatch, Drools5Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case ROUTESENSOR_REPAIR: {
			final Drools5Query<Drools5RouteSensorMatch> query = Drools5Query.create(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
			final EmfTransformationRepairRouteSensor<Drools5Driver, Drools5RouteSensorMatch> transformation = new EmfTransformationRepairRouteSensor<>(driver);
			final ModelOperation<Drools5RouteSensorMatch, Drools5Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final Drools5Query<Drools5SemaphoreNeighborMatch> query = Drools5Query.create(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final ModelOperation<Drools5SemaphoreNeighborMatch, Drools5Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			final EmfApiQuery<EmfSemaphoreNeighborInjectMatch, Drools5Driver> query = new EmfApiQuerySemaphoreNeighborInject<>(driver);
			final EmfTransformation<EmfSemaphoreNeighborInjectMatch, Drools5Driver> transformation = new EmfTransformationInjectSemaphoreNeighbor<>(driver);
			final ModelOperation<EmfSemaphoreNeighborInjectMatch, Drools5Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final Drools5Query<Drools5SemaphoreNeighborMatch> query = Drools5Query.create(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final EmfTransformationRepairSemaphoreNeighbor<Drools5Driver, Drools5SemaphoreNeighborMatch> transformation = new EmfTransformationRepairSemaphoreNeighbor<>(
					driver);
			final ModelOperation<Drools5SemaphoreNeighborMatch, Drools5Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final Drools5Query<Drools5SwitchMonitoredMatch> query = Drools5Query.create(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
			final ModelOperation<Drools5SwitchMonitoredMatch, Drools5Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			final EmfApiQuery<EmfSwitchMonitoredInjectMatch, Drools5Driver> query = new EmfApiQuerySwitchMonitoredInject<>(driver);
			final EmfTransformation<EmfSwitchMonitoredInjectMatch, Drools5Driver> transformation = new EmfTransformationInjectSwitchMonitored<>(driver);
			final ModelOperation<EmfSwitchMonitoredInjectMatch, Drools5Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHMONITORED_REPAIR: {
			final Drools5Query<Drools5SwitchMonitoredMatch> query = Drools5Query.create(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
			final EmfTransformationRepairSwitchMonitored<Drools5Driver, Drools5SwitchMonitoredMatch> transformation = new EmfTransformationRepairSwitchMonitored<>(
					driver);
			final ModelOperation<Drools5SwitchMonitoredMatch, Drools5Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final Drools5Query<Drools5SwitchSetMatch> query = Drools5Query.create(driver, workspaceDir, RailwayQuery.SWITCHSET);
			final ModelOperation<Drools5SwitchSetMatch, Drools5Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			final EmfApiQuery<EmfSwitchSetInjectMatch, Drools5Driver> query = new EmfApiQuerySwitchSetInject<>(driver);
			final EmfTransformation<EmfSwitchSetInjectMatch, Drools5Driver> transformation = new EmfTransformationInjectSwitchSet<>(driver);
			final ModelOperation<EmfSwitchSetInjectMatch, Drools5Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHSET_REPAIR: {
			final Drools5Query<Drools5SwitchSetMatch> query = Drools5Query.create(driver, workspaceDir, RailwayQuery.SWITCHSET);
			final EmfTransformation<Drools5SwitchSetMatch, Drools5Driver> transformation = new EmfTransformationRepairSwitchSet<>(driver);
			final ModelOperation<Drools5SwitchSetMatch, Drools5Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		}
		throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
	}

}
