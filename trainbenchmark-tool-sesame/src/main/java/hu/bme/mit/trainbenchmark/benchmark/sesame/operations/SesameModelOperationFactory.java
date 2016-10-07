package hu.bme.mit.trainbenchmark.benchmark.sesame.operations;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesamePosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesamePosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameSwitchSetMatch;
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

public class SesameModelOperationFactory<TSesameDriver extends SesameDriver>
		extends ModelOperationFactory<SesameMatch, TSesameDriver> {

	@Override
	public ModelOperation<? extends SesameMatch, TSesameDriver> createOperation(final RailwayOperation operationEnum,
			final String workspaceDir, final TSesameDriver driver) throws Exception {
		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final SesameQuery<SesameConnectedSegmentsMatch, TSesameDriver> query = SesameQuery.create(driver,
					workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<SesameConnectedSegmentsMatch, TSesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			final SesameQuery<SesameConnectedSegmentsInjectMatch, TSesameDriver> query = SesameQuery.create(driver,
					workspaceDir, RailwayQuery.CONNECTEDSEGMENTS_INJECT);
			final SesameTransformation<SesameConnectedSegmentsInjectMatch, TSesameDriver> transformation = new SesameTransformationInjectConnectedSegments<>(
					driver);
			final ModelOperation<SesameConnectedSegmentsInjectMatch, TSesameDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;

		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final SesameQuery<SesameConnectedSegmentsMatch, TSesameDriver> query = SesameQuery.create(driver,
					workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final SesameTransformation<SesameConnectedSegmentsMatch, TSesameDriver> transformation = new SesameTransformationRepairConnectedSegments<>(
					driver);
			final ModelOperation<SesameConnectedSegmentsMatch, TSesameDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

		// PosLength
		case POSLENGTH: {
			final SesameQuery<SesamePosLengthMatch, TSesameDriver> query = SesameQuery.create(driver, workspaceDir,
					RailwayQuery.POSLENGTH);
			final ModelOperation<SesamePosLengthMatch, TSesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			final SesameQuery<SesamePosLengthInjectMatch, TSesameDriver> query = SesameQuery.create(driver,
					workspaceDir, RailwayQuery.POSLENGTH_INJECT);
			final SesameTransformation<SesamePosLengthInjectMatch, TSesameDriver> transformation = new SesameTransformationInjectPosLength<>(
					driver);
			final ModelOperation<SesamePosLengthInjectMatch, TSesameDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}
		case POSLENGTH_REPAIR: {
			final SesameQuery<SesamePosLengthMatch, TSesameDriver> query = SesameQuery.create(driver, workspaceDir,
					RailwayQuery.POSLENGTH);
			final SesameTransformation<SesamePosLengthMatch, TSesameDriver> transformation = new SesameTransformationRepairPosLength<>(
					driver);
			final ModelOperation<SesamePosLengthMatch, TSesameDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

		// RouteSensor
		case ROUTESENSOR: {
			final SesameQuery<SesameRouteSensorMatch, TSesameDriver> query = SesameQuery.create(driver, workspaceDir,
					RailwayQuery.ROUTESENSOR);
			final ModelOperation<SesameRouteSensorMatch, TSesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			final SesameQuery<SesameRouteSensorInjectMatch, TSesameDriver> query = SesameQuery.create(driver,
					workspaceDir, RailwayQuery.ROUTESENSOR_INJECT);
			final SesameTransformation<SesameRouteSensorInjectMatch, TSesameDriver> transformation = new SesameTransformationInjectRouteSensor<>(
					driver);
			final ModelOperation<SesameRouteSensorInjectMatch, TSesameDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}
		case ROUTESENSOR_REPAIR: {
			final SesameQuery<SesameRouteSensorMatch, TSesameDriver> query = SesameQuery.create(driver, workspaceDir,
					RailwayQuery.ROUTESENSOR);
			final SesameTransformation<SesameRouteSensorMatch, TSesameDriver> transformation = new SesameTransformationRepairRouteSensor<>(
					driver);
			final ModelOperation<SesameRouteSensorMatch, TSesameDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

		// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final SesameQuery<SesameSemaphoreNeighborMatch, TSesameDriver> query = SesameQuery.create(driver,
					workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final ModelOperation<SesameSemaphoreNeighborMatch, TSesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			final SesameQuery<SesameSemaphoreNeighborInjectMatch, TSesameDriver> query = SesameQuery.create(driver,
					workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR_INJECT);
			final SesameTransformation<SesameSemaphoreNeighborInjectMatch, TSesameDriver> transformation = new SesameTransformationInjectSemaphoreNeighbor<>(
					driver);
			final ModelOperation<SesameSemaphoreNeighborInjectMatch, TSesameDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final SesameQuery<SesameSemaphoreNeighborMatch, TSesameDriver> query = SesameQuery.create(driver,
					workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final SesameTransformation<SesameSemaphoreNeighborMatch, TSesameDriver> transformation = new SesameTransformationRepairSemaphoreNeighbor<>(
					driver);
			final ModelOperation<SesameSemaphoreNeighborMatch, TSesameDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

		// SwitchMonitored
		case SWITCHMONITORED: {
			final SesameQuery<SesameSwitchMonitoredMatch, TSesameDriver> query = SesameQuery.create(driver,
					workspaceDir, RailwayQuery.SWITCHMONITORED);
			final ModelOperation<SesameSwitchMonitoredMatch, TSesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			final SesameQuery<SesameSwitchMonitoredInjectMatch, TSesameDriver> query = SesameQuery.create(driver,
					workspaceDir, RailwayQuery.SWITCHMONITORED_INJECT);
			final SesameTransformation<SesameSwitchMonitoredInjectMatch, TSesameDriver> transformation = new SesameTransformationInjectSwitchMonitored<>(
					driver);
			final ModelOperation<SesameSwitchMonitoredInjectMatch, TSesameDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}
		case SWITCHMONITORED_REPAIR: {
			final SesameQuery<SesameSwitchMonitoredMatch, TSesameDriver> query = SesameQuery.create(driver,
					workspaceDir, RailwayQuery.SWITCHMONITORED);
			final SesameTransformation<SesameSwitchMonitoredMatch, TSesameDriver> transformation = new SesameTransformationRepairSwitchMonitored<>(
					driver);
			final ModelOperation<SesameSwitchMonitoredMatch, TSesameDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

		// SwitchSet
		case SWITCHSET: {
			final SesameQuery<SesameSwitchSetMatch, TSesameDriver> query = SesameQuery.create(driver, workspaceDir,
					RailwayQuery.SWITCHSET);
			final ModelOperation<SesameSwitchSetMatch, TSesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			final SesameQuery<SesameSwitchSetInjectMatch, TSesameDriver> query = SesameQuery.create(driver,
					workspaceDir, RailwayQuery.SWITCHSET_INJECT);
			final SesameTransformation<SesameSwitchSetInjectMatch, TSesameDriver> transformation = new SesameTransformationInjectSwitchSet<>(
					driver);
			final ModelOperation<SesameSwitchSetInjectMatch, TSesameDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}
		case SWITCHSET_REPAIR: {
			final SesameQuery<SesameSwitchSetMatch, TSesameDriver> query = SesameQuery.create(driver, workspaceDir,
					RailwayQuery.SWITCHSET);
			final SesameTransformation<SesameSwitchSetMatch, TSesameDriver> transformation = new SesameTransformationRepairSwitchSet<>(
					driver);
			final ModelOperation<SesameSwitchSetMatch, TSesameDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}

}
