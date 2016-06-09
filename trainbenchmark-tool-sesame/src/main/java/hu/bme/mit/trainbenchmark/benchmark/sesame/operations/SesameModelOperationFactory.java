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
import hu.bme.mit.trainbenchmark.benchmark.sesame.queries.SesameQuery;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.SesameTransformation;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair.SesameTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair.SesameTransformationRepairRouteSensor;
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
	public ModelOperation<? extends SesameMatch, SesameDriver> createOperation(final RailwayOperation operationEnum,
			final Optional<String> queryDirectory, final SesameDriver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final SesameQuery<SesameConnectedSegmentsMatch> query = SesameQuery.create(driver, queryDirectory,
					RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<SesameConnectedSegmentsMatch, SesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			// TODO

		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final SesameQuery<SesameConnectedSegmentsMatch> query = SesameQuery.create(driver, queryDirectory,
					RailwayQuery.CONNECTEDSEGMENTS);
			final SesameTransformation<SesameConnectedSegmentsMatch> transformation = new SesameTransformationRepairConnectedSegments(
					driver);
			final ModelOperation<SesameConnectedSegmentsMatch, SesameDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final SesameQuery<SesamePosLengthMatch> query = SesameQuery.create(driver, queryDirectory,
					RailwayQuery.POSLENGTH);
			final ModelOperation<SesamePosLengthMatch, SesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			// TODO
		}
		case POSLENGTH_REPAIR: {
			final SesameQuery<SesameRouteSensorMatch> query = SesameQuery.create(driver, queryDirectory,
					RailwayQuery.POSLENGTH);
			final SesameTransformation<SesameRouteSensorMatch> transformation = new SesameTransformationRepairRouteSensor(
					driver);
			final ModelOperation<SesameRouteSensorMatch, SesameDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;

		}

			// RouteSensor
		case ROUTESENSOR: {
			final SesameQuery<SesameConnectedSegmentsMatch> query = SesameQuery.create(driver, queryDirectory,
					RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<SesameConnectedSegmentsMatch, SesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			// TODO
		}
		case ROUTESENSOR_REPAIR: {
			final SesameQuery<SesameRouteSensorMatch> query = SesameQuery.create(driver, queryDirectory,
					RailwayQuery.ROUTESENSOR);
			final SesameTransformation<SesameRouteSensorMatch> transformation = new SesameTransformationRepairRouteSensor(
					driver);
			final ModelOperation<SesameRouteSensorMatch, SesameDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final SesameQuery<SesameSemaphoreNeighborMatch> query = SesameQuery.create(driver, queryDirectory,
					RailwayQuery.SEMAPHORENEIGHBOR);
			final ModelOperation<SesameSemaphoreNeighborMatch, SesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			// TODO
		}
		case SEMAPHORENEIGHBOR_REPAIR: {

		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final SesameQuery<SesameSwitchMonitoredMatch> query = SesameQuery.create(driver, queryDirectory,
					RailwayQuery.SWITCHMONITORED);
			final ModelOperation<SesameSwitchMonitoredMatch, SesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			// TODO
		}
		case SWITCHMONITORED_REPAIR: {
			final SesameQuery<SesameSwitchMonitoredMatch> query = SesameQuery.create(driver, queryDirectory,
					RailwayQuery.SWITCHMONITORED);
			final SesameTransformation<SesameSwitchMonitoredMatch> transformation = new SesameTransformationRepairSwitchMonitored(
					driver);
			final ModelOperation<SesameSwitchMonitoredMatch , SesameDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final SesameQuery<SesameSwitchSetMatch> query = SesameQuery.create(driver, queryDirectory,
					RailwayQuery.SWITCHSET);
			final ModelOperation<SesameSwitchSetMatch, SesameDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			// TODO
		}
		case SWITCHSET_REPAIR: {
			final SesameQuery<SesameSwitchSetMatch> query = SesameQuery.create(driver, queryDirectory,
					RailwayQuery.SWITCHSET);
			final SesameTransformation<SesameSwitchSetMatch> transformation = new SesameTransformationRepairSwitchSet(
					driver);
			final ModelOperation<SesameSwitchSetMatch, SesameDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}

}
