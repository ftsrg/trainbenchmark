package hu.bme.mit.trainbenchmark.benchmark.sesame.operations;

import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesamePosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameVertexMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.queries.SesameQuery;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.SesameTransformation;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.inject.SesameTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.inject.SesameTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.inject.SesameTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.inject.SesameTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.inject.SesameTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.inject.SesameTransformationInjectSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair.SesameTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair.SesameTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair.SesameTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair.SesameTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair.SesameTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair.SesameTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class SesameModelOperationFactory extends ModelOperationFactory<SesameMatch, SesameDriver> {

	protected SesameModelOperationFactory() {

	}

	public static SesameModelOperationFactory create() {
		return new SesameModelOperationFactory();
	}

	@Override
	public ModelOperation<? extends SesameMatch, SesameDriver> createOperation(final RailwayOperation operationEnum, final Optional<String> workspacePath,
			final SesameDriver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final SesameQuery<SesameConnectedSegmentsMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<SesameConnectedSegmentsMatch, SesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			final SesameQuery<SesameVertexMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.CONNECTEDSEGMENTS_INJECT);
			final SesameTransformation<SesameVertexMatch> transformation = new SesameTransformationInjectConnectedSegments(driver);
			final ModelOperation<SesameVertexMatch, SesameDriver> operation = ModelOperation.of(query, transformation);
			return operation;

		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final SesameQuery<SesameConnectedSegmentsMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.CONNECTEDSEGMENTS);
			final SesameTransformation<SesameConnectedSegmentsMatch> transformation = new SesameTransformationRepairConnectedSegments(driver);
			final ModelOperation<SesameConnectedSegmentsMatch, SesameDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final SesameQuery<SesamePosLengthMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.POSLENGTH);
			final ModelOperation<SesamePosLengthMatch, SesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			final SesameQuery<SesameVertexMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.POSLENGTH_INJECT);
			final SesameTransformation<SesameVertexMatch> transformation = new SesameTransformationInjectPosLength(driver);
			final ModelOperation<SesameVertexMatch, SesameDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case POSLENGTH_REPAIR: {
			final SesameQuery<SesamePosLengthMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.POSLENGTH);
			final SesameTransformation<SesamePosLengthMatch> transformation = new SesameTransformationRepairPosLength(driver);
			final ModelOperation<SesamePosLengthMatch, SesameDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// RouteSensor
		case ROUTESENSOR: {
			final SesameQuery<SesameRouteSensorMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.ROUTESENSOR);
			final ModelOperation<SesameRouteSensorMatch, SesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			final SesameQuery<SesameVertexMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.ROUTESENSOR_INJECT);
			final SesameTransformation<SesameVertexMatch> transformation = new SesameTransformationInjectRouteSensor(driver);
			final ModelOperation<SesameVertexMatch, SesameDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case ROUTESENSOR_REPAIR: {
			final SesameQuery<SesameRouteSensorMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.ROUTESENSOR);
			final SesameTransformation<SesameRouteSensorMatch> transformation = new SesameTransformationRepairRouteSensor(driver);
			final ModelOperation<SesameRouteSensorMatch, SesameDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final SesameQuery<SesameSemaphoreNeighborMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.SEMAPHORENEIGHBOR);
			final ModelOperation<SesameSemaphoreNeighborMatch, SesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			final SesameQuery<SesameVertexMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.SEMAPHORENEIGHBOR_INJECT);
			final SesameTransformation<SesameVertexMatch> transformation = new SesameTransformationInjectSemaphoreNeighbor(driver);
			final ModelOperation<SesameVertexMatch, SesameDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final SesameQuery<SesameSemaphoreNeighborMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.SEMAPHORENEIGHBOR);
			final SesameTransformation<SesameSemaphoreNeighborMatch> transformation = new SesameTransformationRepairSemaphoreNeighbor(driver);
			final ModelOperation<SesameSemaphoreNeighborMatch, SesameDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final SesameQuery<SesameSwitchMonitoredMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.SWITCHMONITORED);
			final ModelOperation<SesameSwitchMonitoredMatch, SesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			final SesameQuery<SesameVertexMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.SWITCHMONITORED_INJECT);
			final SesameTransformation<SesameVertexMatch> transformation = new SesameTransformationInjectSwitchMonitored(driver);
			final ModelOperation<SesameVertexMatch, SesameDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHMONITORED_REPAIR: {
			final SesameQuery<SesameSwitchMonitoredMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.SWITCHMONITORED);
			final SesameTransformation<SesameSwitchMonitoredMatch> transformation = new SesameTransformationRepairSwitchMonitored(driver);
			final ModelOperation<SesameSwitchMonitoredMatch, SesameDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final SesameQuery<SesameSwitchSetMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.SWITCHSET);
			final ModelOperation<SesameSwitchSetMatch, SesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			final SesameQuery<SesameVertexMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.SWITCHSET_INJECT);
			final SesameTransformation<SesameVertexMatch> transformation = new SesameTransformationInjectSwitchSet(driver);
			final ModelOperation<SesameVertexMatch, SesameDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHSET_REPAIR: {
			final SesameQuery<SesameSwitchSetMatch> query = SesameQuery.create(driver, workspacePath, RailwayQuery.SWITCHSET);
			final SesameTransformation<SesameSwitchSetMatch> transformation = new SesameTransformationRepairSwitchSet(driver);
			final ModelOperation<SesameSwitchSetMatch, SesameDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}

}
