package hu.bme.mit.trainbenchmark.benchmark.rdf4j.operations;

import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.driver.Rdf4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.matches.Rdf4jSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.queries.Rdf4jQuery;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.Rdf4jTransformation;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.inject.Rdf4jTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.inject.Rdf4jTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.inject.Rdf4jTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.inject.Rdf4jTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.inject.Rdf4jTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.inject.Rdf4jTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.repair.Rdf4jTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.repair.Rdf4jTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.repair.Rdf4jTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.repair.Rdf4jTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.repair.Rdf4jTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.rdf4j.transformations.repair.Rdf4jTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class Rdf4jModelOperationFactory extends ModelOperationFactory<Rdf4jMatch, Rdf4jDriver> {

	protected Rdf4jModelOperationFactory() {

	}

	public static Rdf4jModelOperationFactory create() {
		return new Rdf4jModelOperationFactory();
	}

	@Override
	public ModelOperation<? extends Rdf4jMatch, Rdf4jDriver> createOperation(final RailwayOperation operationEnum, final Optional<String> workspacePath,
			final Rdf4jDriver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final Rdf4jQuery<Rdf4jConnectedSegmentsMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<Rdf4jConnectedSegmentsMatch, Rdf4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			final Rdf4jQuery<Rdf4jConnectedSegmentsInjectMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.CONNECTEDSEGMENTS_INJECT);
			final Rdf4jTransformation<Rdf4jConnectedSegmentsInjectMatch> transformation = new Rdf4jTransformationInjectConnectedSegments(driver);
			final ModelOperation<Rdf4jConnectedSegmentsInjectMatch, Rdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;

		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final Rdf4jQuery<Rdf4jConnectedSegmentsMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.CONNECTEDSEGMENTS);
			final Rdf4jTransformation<Rdf4jConnectedSegmentsMatch> transformation = new Rdf4jTransformationRepairConnectedSegments(driver);
			final ModelOperation<Rdf4jConnectedSegmentsMatch, Rdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final Rdf4jQuery<Rdf4jPosLengthMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.POSLENGTH);
			final ModelOperation<Rdf4jPosLengthMatch, Rdf4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			final Rdf4jQuery<Rdf4jPosLengthInjectMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.POSLENGTH_INJECT);
			final Rdf4jTransformation<Rdf4jPosLengthInjectMatch> transformation = new Rdf4jTransformationInjectPosLength(driver);
			final ModelOperation<Rdf4jPosLengthInjectMatch, Rdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case POSLENGTH_REPAIR: {
			final Rdf4jQuery<Rdf4jPosLengthMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.POSLENGTH);
			final Rdf4jTransformation<Rdf4jPosLengthMatch> transformation = new Rdf4jTransformationRepairPosLength(driver);
			final ModelOperation<Rdf4jPosLengthMatch, Rdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// RouteSensor
		case ROUTESENSOR: {
			final Rdf4jQuery<Rdf4jRouteSensorMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.ROUTESENSOR);
			final ModelOperation<Rdf4jRouteSensorMatch, Rdf4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			final Rdf4jQuery<Rdf4jRouteSensorInjectMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.ROUTESENSOR_INJECT);
			final Rdf4jTransformation<Rdf4jRouteSensorInjectMatch> transformation = new Rdf4jTransformationInjectRouteSensor(driver);
			final ModelOperation<Rdf4jRouteSensorInjectMatch, Rdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case ROUTESENSOR_REPAIR: {
			final Rdf4jQuery<Rdf4jRouteSensorMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.ROUTESENSOR);
			final Rdf4jTransformation<Rdf4jRouteSensorMatch> transformation = new Rdf4jTransformationRepairRouteSensor(driver);
			final ModelOperation<Rdf4jRouteSensorMatch, Rdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final Rdf4jQuery<Rdf4jSemaphoreNeighborMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SEMAPHORENEIGHBOR);
			final ModelOperation<Rdf4jSemaphoreNeighborMatch, Rdf4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			final Rdf4jQuery<Rdf4jSemaphoreNeighborInjectMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SEMAPHORENEIGHBOR_INJECT);
			final Rdf4jTransformation<Rdf4jSemaphoreNeighborInjectMatch> transformation = new Rdf4jTransformationInjectSemaphoreNeighbor(driver);
			final ModelOperation<Rdf4jSemaphoreNeighborInjectMatch, Rdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final Rdf4jQuery<Rdf4jSemaphoreNeighborMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SEMAPHORENEIGHBOR);
			final Rdf4jTransformation<Rdf4jSemaphoreNeighborMatch> transformation = new Rdf4jTransformationRepairSemaphoreNeighbor(driver);
			final ModelOperation<Rdf4jSemaphoreNeighborMatch, Rdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final Rdf4jQuery<Rdf4jSwitchMonitoredMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SWITCHMONITORED);
			final ModelOperation<Rdf4jSwitchMonitoredMatch, Rdf4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			final Rdf4jQuery<Rdf4jSwitchMonitoredInjectMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SWITCHMONITORED_INJECT);
			final Rdf4jTransformation<Rdf4jSwitchMonitoredInjectMatch> transformation = new Rdf4jTransformationInjectSwitchMonitored(driver);
			final ModelOperation<Rdf4jSwitchMonitoredInjectMatch, Rdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHMONITORED_REPAIR: {
			final Rdf4jQuery<Rdf4jSwitchMonitoredMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SWITCHMONITORED);
			final Rdf4jTransformation<Rdf4jSwitchMonitoredMatch> transformation = new Rdf4jTransformationRepairSwitchMonitored(driver);
			final ModelOperation<Rdf4jSwitchMonitoredMatch, Rdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final Rdf4jQuery<Rdf4jSwitchSetMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SWITCHSET);
			final ModelOperation<Rdf4jSwitchSetMatch, Rdf4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			final Rdf4jQuery<Rdf4jSwitchSetInjectMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SWITCHSET_INJECT);
			final Rdf4jTransformation<Rdf4jSwitchSetInjectMatch> transformation = new Rdf4jTransformationInjectSwitchSet(driver);
			final ModelOperation<Rdf4jSwitchSetInjectMatch, Rdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHSET_REPAIR: {
			final Rdf4jQuery<Rdf4jSwitchSetMatch> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SWITCHSET);
			final Rdf4jTransformation<Rdf4jSwitchSetMatch> transformation = new Rdf4jTransformationRepairSwitchSet(driver);
			final ModelOperation<Rdf4jSwitchSetMatch, Rdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}

}
