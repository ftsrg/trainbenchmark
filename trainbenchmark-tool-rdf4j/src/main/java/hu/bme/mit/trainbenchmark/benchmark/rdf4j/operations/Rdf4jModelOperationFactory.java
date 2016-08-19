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

public class Rdf4jModelOperationFactory<TRdf4jDriver extends Rdf4jDriver> extends ModelOperationFactory<Rdf4jMatch, TRdf4jDriver> {

	@Override
	public ModelOperation<? extends Rdf4jMatch, TRdf4jDriver> createOperation(final RailwayOperation operationEnum, final Optional<String> workspacePath,
			final TRdf4jDriver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final Rdf4jQuery<Rdf4jConnectedSegmentsMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<Rdf4jConnectedSegmentsMatch, TRdf4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			final Rdf4jQuery<Rdf4jConnectedSegmentsInjectMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.CONNECTEDSEGMENTS_INJECT);
			final Rdf4jTransformation<Rdf4jConnectedSegmentsInjectMatch, TRdf4jDriver> transformation = new Rdf4jTransformationInjectConnectedSegments<TRdf4jDriver>(driver);
			final ModelOperation<Rdf4jConnectedSegmentsInjectMatch, TRdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;

		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final Rdf4jQuery<Rdf4jConnectedSegmentsMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.CONNECTEDSEGMENTS);
			final Rdf4jTransformation<Rdf4jConnectedSegmentsMatch, TRdf4jDriver> transformation = new Rdf4jTransformationRepairConnectedSegments<TRdf4jDriver>(driver);
			final ModelOperation<Rdf4jConnectedSegmentsMatch, TRdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final Rdf4jQuery<Rdf4jPosLengthMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.POSLENGTH);
			final ModelOperation<Rdf4jPosLengthMatch, TRdf4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			final Rdf4jQuery<Rdf4jPosLengthInjectMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.POSLENGTH_INJECT);
			final Rdf4jTransformation<Rdf4jPosLengthInjectMatch, TRdf4jDriver> transformation = new Rdf4jTransformationInjectPosLength<TRdf4jDriver>(driver);
			final ModelOperation<Rdf4jPosLengthInjectMatch, TRdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case POSLENGTH_REPAIR: {
			final Rdf4jQuery<Rdf4jPosLengthMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.POSLENGTH);
			final Rdf4jTransformation<Rdf4jPosLengthMatch, TRdf4jDriver> transformation = new Rdf4jTransformationRepairPosLength<TRdf4jDriver>(driver);
			final ModelOperation<Rdf4jPosLengthMatch, TRdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// RouteSensor
		case ROUTESENSOR: {
			final Rdf4jQuery<Rdf4jRouteSensorMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.ROUTESENSOR);
			final ModelOperation<Rdf4jRouteSensorMatch, TRdf4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			final Rdf4jQuery<Rdf4jRouteSensorInjectMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.ROUTESENSOR_INJECT);
			final Rdf4jTransformation<Rdf4jRouteSensorInjectMatch, TRdf4jDriver> transformation = new Rdf4jTransformationInjectRouteSensor<TRdf4jDriver>(driver);
			final ModelOperation<Rdf4jRouteSensorInjectMatch, TRdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case ROUTESENSOR_REPAIR: {
			final Rdf4jQuery<Rdf4jRouteSensorMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.ROUTESENSOR);
			final Rdf4jTransformation<Rdf4jRouteSensorMatch, TRdf4jDriver> transformation = new Rdf4jTransformationRepairRouteSensor<TRdf4jDriver>(driver);
			final ModelOperation<Rdf4jRouteSensorMatch, TRdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final Rdf4jQuery<Rdf4jSemaphoreNeighborMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SEMAPHORENEIGHBOR);
			final ModelOperation<Rdf4jSemaphoreNeighborMatch, TRdf4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			final Rdf4jQuery<Rdf4jSemaphoreNeighborInjectMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SEMAPHORENEIGHBOR_INJECT);
			final Rdf4jTransformation<Rdf4jSemaphoreNeighborInjectMatch, TRdf4jDriver> transformation = new Rdf4jTransformationInjectSemaphoreNeighbor<TRdf4jDriver>(driver);
			final ModelOperation<Rdf4jSemaphoreNeighborInjectMatch, TRdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final Rdf4jQuery<Rdf4jSemaphoreNeighborMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SEMAPHORENEIGHBOR);
			final Rdf4jTransformation<Rdf4jSemaphoreNeighborMatch, TRdf4jDriver> transformation = new Rdf4jTransformationRepairSemaphoreNeighbor<TRdf4jDriver>(driver);
			final ModelOperation<Rdf4jSemaphoreNeighborMatch, TRdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final Rdf4jQuery<Rdf4jSwitchMonitoredMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SWITCHMONITORED);
			final ModelOperation<Rdf4jSwitchMonitoredMatch, TRdf4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			final Rdf4jQuery<Rdf4jSwitchMonitoredInjectMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SWITCHMONITORED_INJECT);
			final Rdf4jTransformation<Rdf4jSwitchMonitoredInjectMatch, TRdf4jDriver> transformation = new Rdf4jTransformationInjectSwitchMonitored<TRdf4jDriver>(driver);
			final ModelOperation<Rdf4jSwitchMonitoredInjectMatch, TRdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHMONITORED_REPAIR: {
			final Rdf4jQuery<Rdf4jSwitchMonitoredMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SWITCHMONITORED);
			final Rdf4jTransformation<Rdf4jSwitchMonitoredMatch, TRdf4jDriver> transformation = new Rdf4jTransformationRepairSwitchMonitored<TRdf4jDriver>(driver);
			final ModelOperation<Rdf4jSwitchMonitoredMatch, TRdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final Rdf4jQuery<Rdf4jSwitchSetMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SWITCHSET);
			final ModelOperation<Rdf4jSwitchSetMatch, TRdf4jDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			final Rdf4jQuery<Rdf4jSwitchSetInjectMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SWITCHSET_INJECT);
			final Rdf4jTransformation<Rdf4jSwitchSetInjectMatch, TRdf4jDriver> transformation = new Rdf4jTransformationInjectSwitchSet<TRdf4jDriver>(driver);
			final ModelOperation<Rdf4jSwitchSetInjectMatch, TRdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHSET_REPAIR: {
			final Rdf4jQuery<Rdf4jSwitchSetMatch, TRdf4jDriver> query = Rdf4jQuery.create(driver, workspacePath, RailwayQuery.SWITCHSET);
			final Rdf4jTransformation<Rdf4jSwitchSetMatch, TRdf4jDriver> transformation = new Rdf4jTransformationRepairSwitchSet<TRdf4jDriver>(driver);
			final ModelOperation<Rdf4jSwitchSetMatch, TRdf4jDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

		default:
			break;
		}
		throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
	}

}
