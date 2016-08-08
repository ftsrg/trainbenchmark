package hu.bme.mit.trainbenchmark.benchmark.jena.operations;

import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.jena.benchmarkcases.JenaQuery;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.JenaTransformation;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair.JenaTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class JenaModelOperationFactory extends ModelOperationFactory<JenaMatch, JenaDriver> {

	protected JenaModelOperationFactory() {

	}

	public static JenaModelOperationFactory create() {
		return new JenaModelOperationFactory();
	}

	@Override
	public ModelOperation<? extends JenaMatch, JenaDriver> createOperation(final RailwayOperation operationEnum, final Optional<String> workspacePath,
			final JenaDriver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final JenaQuery<JenaConnectedSegmentsMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<JenaConnectedSegmentsMatch, JenaDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			// TODO

		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final JenaQuery<JenaConnectedSegmentsMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.CONNECTEDSEGMENTS);
			final JenaTransformation<JenaConnectedSegmentsMatch> transformation = new JenaTransformationRepairConnectedSegments(driver);
			final ModelOperation<JenaConnectedSegmentsMatch, JenaDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final JenaQuery<JenaPosLengthMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.POSLENGTH);
			final ModelOperation<JenaPosLengthMatch, JenaDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			// TODO
		}
		case POSLENGTH_REPAIR: {
			final JenaQuery<JenaPosLengthMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.POSLENGTH);
			final JenaTransformation<JenaPosLengthMatch> transformation = new JenaTransformationRepairPosLength(driver);
			final ModelOperation<JenaPosLengthMatch, JenaDriver> operation = ModelOperation.of(query, transformation);
			return operation;

		}

			// RouteSensor
		case ROUTESENSOR: {
			final JenaQuery<JenaRouteSensorMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.ROUTESENSOR);
			final ModelOperation<JenaRouteSensorMatch, JenaDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			// TODO
		}
		case ROUTESENSOR_REPAIR: {
			final JenaQuery<JenaRouteSensorMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.ROUTESENSOR);
			final JenaTransformation<JenaRouteSensorMatch> transformation = new JenaTransformationRepairRouteSensor(driver);
			final ModelOperation<JenaRouteSensorMatch, JenaDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final JenaQuery<JenaSemaphoreNeighborMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.SEMAPHORENEIGHBOR);
			final ModelOperation<JenaSemaphoreNeighborMatch, JenaDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			// TODO
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final JenaQuery<JenaSemaphoreNeighborMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.SEMAPHORENEIGHBOR);
			final JenaTransformation<JenaSemaphoreNeighborMatch> transformation = new JenaTransformationRepairSemaphoreNeighbor(driver);
			final ModelOperation<JenaSemaphoreNeighborMatch, JenaDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final JenaQuery<JenaSwitchMonitoredMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.SWITCHMONITORED);
			final ModelOperation<JenaSwitchMonitoredMatch, JenaDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			// TODO
		}
		case SWITCHMONITORED_REPAIR: {
			final JenaQuery<JenaSwitchMonitoredMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.SWITCHMONITORED);
			final JenaTransformation<JenaSwitchMonitoredMatch> transformation = new JenaTransformationRepairSwitchMonitored(driver);
			final ModelOperation<JenaSwitchMonitoredMatch, JenaDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final JenaQuery<JenaSwitchSetMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.SWITCHSET);
			final ModelOperation<JenaSwitchSetMatch, JenaDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			// TODO
		}
		case SWITCHSET_REPAIR: {
			final JenaQuery<JenaSwitchSetMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.SWITCHSET);
			final JenaTransformation<JenaSwitchSetMatch> transformation = new JenaTransformationRepairSwitchSet(driver);
			final ModelOperation<JenaSwitchSetMatch, JenaDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

		default:
			throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
		}
	}

}
