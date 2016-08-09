package hu.bme.mit.trainbenchmark.benchmark.drools6.operations;

import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.drools6.driver.Drools6Driver;
import hu.bme.mit.trainbenchmark.benchmark.drools6.matches.Drools6ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools6.matches.Drools6PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools6.matches.Drools6RouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools6.matches.Drools6SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools6.matches.Drools6SwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools6.matches.Drools6SwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools6.queries.Drools6Query;
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

public class Drools6ModelOperationFactory extends ModelOperationFactory<EmfMatch, Drools6Driver> {

	protected Drools6ModelOperationFactory() {

	}

	public static Drools6ModelOperationFactory create() {
		return new Drools6ModelOperationFactory();
	}

	@Override
	public ModelOperation<? extends EmfMatch, Drools6Driver> createOperation(final RailwayOperation operationEnum, final Optional<String> workspaceDir,
			final Drools6Driver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final Drools6Query<Drools6ConnectedSegmentsMatch> query = Drools6Query.create(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<Drools6ConnectedSegmentsMatch, Drools6Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			final EmfApiQuery<EmfConnectedSegmentsInjectMatch, Drools6Driver> query = new EmfApiQueryConnectedSegmentsInject<>(driver);
			final EmfTransformation<EmfConnectedSegmentsInjectMatch, Drools6Driver> transformation = new EmfTransformationInjectConnectedSegments<>(driver);
			final ModelOperation<EmfConnectedSegmentsInjectMatch, Drools6Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final Drools6Query<Drools6ConnectedSegmentsMatch> query = Drools6Query.create(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final EmfTransformationRepairConnectedSegments<Drools6Driver, Drools6ConnectedSegmentsMatch> transformation = new EmfTransformationRepairConnectedSegments<>(
					driver);
			final ModelOperation<Drools6ConnectedSegmentsMatch, Drools6Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final Drools6Query<Drools6PosLengthMatch> query = Drools6Query.create(driver, workspaceDir, RailwayQuery.POSLENGTH);
			final ModelOperation<Drools6PosLengthMatch, Drools6Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			final EmfApiQuery<EmfPosLengthInjectMatch, Drools6Driver> query = new EmfApiQueryPosLengthInject<>(driver);
			final EmfTransformation<EmfPosLengthInjectMatch, Drools6Driver> transformation = new EmfTransformationInjectPosLength<>(driver);
			final ModelOperation<EmfPosLengthInjectMatch, Drools6Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case POSLENGTH_REPAIR: {
			final Drools6Query<Drools6PosLengthMatch> query = Drools6Query.create(driver, workspaceDir, RailwayQuery.POSLENGTH);
			final EmfTransformation<Drools6PosLengthMatch, Drools6Driver> transformation = new EmfTransformationRepairPosLength<>(driver);
			final ModelOperation<Drools6PosLengthMatch, Drools6Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// RouteSensor
		case ROUTESENSOR: {
			final Drools6Query<Drools6RouteSensorMatch> query = Drools6Query.create(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
			final ModelOperation<Drools6RouteSensorMatch, Drools6Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			final EmfApiQuery<EmfRouteSensorInjectMatch, Drools6Driver> query = new EmfApiQueryRouteSensorInject<>(driver);
			final EmfTransformation<EmfRouteSensorInjectMatch, Drools6Driver> transformation = new EmfTransformationInjectRouteSensor<>(driver);
			final ModelOperation<EmfRouteSensorInjectMatch, Drools6Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case ROUTESENSOR_REPAIR: {
			final Drools6Query<Drools6RouteSensorMatch> query = Drools6Query.create(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
			final EmfTransformationRepairRouteSensor<Drools6Driver, Drools6RouteSensorMatch> transformation = new EmfTransformationRepairRouteSensor<>(driver);
			final ModelOperation<Drools6RouteSensorMatch, Drools6Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final Drools6Query<Drools6SemaphoreNeighborMatch> query = Drools6Query.create(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final ModelOperation<Drools6SemaphoreNeighborMatch, Drools6Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			final EmfApiQuery<EmfSemaphoreNeighborInjectMatch, Drools6Driver> query = new EmfApiQuerySemaphoreNeighborInject<>(driver);
			final EmfTransformation<EmfSemaphoreNeighborInjectMatch, Drools6Driver> transformation = new EmfTransformationInjectSemaphoreNeighbor<>(driver);
			final ModelOperation<EmfSemaphoreNeighborInjectMatch, Drools6Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final Drools6Query<Drools6SemaphoreNeighborMatch> query = Drools6Query.create(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final EmfTransformationRepairSemaphoreNeighbor<Drools6Driver, Drools6SemaphoreNeighborMatch> transformation = new EmfTransformationRepairSemaphoreNeighbor<>(
					driver);
			final ModelOperation<Drools6SemaphoreNeighborMatch, Drools6Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final Drools6Query<Drools6SwitchMonitoredMatch> query = Drools6Query.create(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
			final ModelOperation<Drools6SwitchMonitoredMatch, Drools6Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			final EmfApiQuery<EmfSwitchMonitoredInjectMatch, Drools6Driver> query = new EmfApiQuerySwitchMonitoredInject<>(driver);
			final EmfTransformation<EmfSwitchMonitoredInjectMatch, Drools6Driver> transformation = new EmfTransformationInjectSwitchMonitored<>(driver);
			final ModelOperation<EmfSwitchMonitoredInjectMatch, Drools6Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHMONITORED_REPAIR: {
			final Drools6Query<Drools6SwitchMonitoredMatch> query = Drools6Query.create(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
			final EmfTransformationRepairSwitchMonitored<Drools6Driver, Drools6SwitchMonitoredMatch> transformation = new EmfTransformationRepairSwitchMonitored<>(
					driver);
			final ModelOperation<Drools6SwitchMonitoredMatch, Drools6Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final Drools6Query<Drools6SwitchSetMatch> query = Drools6Query.create(driver, workspaceDir, RailwayQuery.SWITCHSET);
			final ModelOperation<Drools6SwitchSetMatch, Drools6Driver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			final EmfApiQuery<EmfSwitchSetInjectMatch, Drools6Driver> query = new EmfApiQuerySwitchSetInject<>(driver);
			final EmfTransformation<EmfSwitchSetInjectMatch, Drools6Driver> transformation = new EmfTransformationInjectSwitchSet<>(driver);
			final ModelOperation<EmfSwitchSetInjectMatch, Drools6Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHSET_REPAIR: {
			final Drools6Query<Drools6SwitchSetMatch> query = Drools6Query.create(driver, workspaceDir, RailwayQuery.SWITCHSET);
			final EmfTransformation<Drools6SwitchSetMatch, Drools6Driver> transformation = new EmfTransformationRepairSwitchSet<>(driver);
			final ModelOperation<Drools6SwitchSetMatch, Drools6Driver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		}
		throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
	}

}
