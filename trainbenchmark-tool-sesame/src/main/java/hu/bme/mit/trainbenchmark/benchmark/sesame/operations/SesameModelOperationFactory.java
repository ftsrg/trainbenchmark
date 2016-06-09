package hu.bme.mit.trainbenchmark.benchmark.sesame.operations;

import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesamePosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.sesame.queries.SesameQuery;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.SesameTransformation;
import hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair.SesameTransformationRepairRouteSensor;
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

		}
		case CONNECTEDSEGMENTS_INJECT: {
			// TODO

		}
		case CONNECTEDSEGMENTS_REPAIR: {

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
			final ModelOperation<SesameRouteSensorMatch, SesameDriver> operation = ModelOperation.of(query, transformation);
			return operation;

		}

			// RouteSensor
		case ROUTESENSOR: {

		}
		case ROUTESENSOR_INJECT: {
			// TODO
		}
		case ROUTESENSOR_REPAIR: {

		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {

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

		}

			// SwitchSet
		case SWITCHSET: {

		}
		case SWITCHSET_INJECT: {
			// TODO
		}
		case SWITCHSET_REPAIR: {

		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}

}
