package hu.bme.mit.trainbenchmark.benchmark.jena.operations;

import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.jena.benchmarkcases.JenaQuery;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaPosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaRouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaSwitchMonitoredInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaSwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.JenaTransformation;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectPosLength;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject.JenaTransformationInjectSwitchSet;
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
			final JenaQuery<JenaConnectedSegmentsInjectMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.CONNECTEDSEGMENTS_INJECT);
			final JenaTransformation<JenaConnectedSegmentsInjectMatch> transformation = new JenaTransformationInjectConnectedSegments(driver);
			final ModelOperation<JenaConnectedSegmentsInjectMatch, JenaDriver> operation = ModelOperation.of(query, transformation);
			return operation;
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
			final JenaQuery<JenaPosLengthInjectMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.POSLENGTH_INJECT);
			final JenaTransformation<JenaPosLengthInjectMatch> transformation = new JenaTransformationInjectPosLength(driver);
			final ModelOperation<JenaPosLengthInjectMatch, JenaDriver> operation = ModelOperation.of(query, transformation);
			return operation;

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
			final JenaQuery<JenaRouteSensorInjectMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.ROUTESENSOR_INJECT);
			final JenaTransformation<JenaRouteSensorInjectMatch> transformation = new JenaTransformationInjectRouteSensor(driver);
			final ModelOperation<JenaRouteSensorInjectMatch, JenaDriver> operation = ModelOperation.of(query, transformation);
			return operation;
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
			final JenaQuery<JenaSemaphoreNeighborInjectMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.SEMAPHORENEIGHBOR_INJECT);
			final JenaTransformation<JenaSemaphoreNeighborInjectMatch> transformation = new JenaTransformationInjectSemaphoreNeighbor(driver);
			final ModelOperation<JenaSemaphoreNeighborInjectMatch, JenaDriver> operation = ModelOperation.of(query, transformation);
			return operation;
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
			final JenaQuery<JenaSwitchMonitoredInjectMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.SWITCHMONITORED_INJECT);
			final JenaTransformation<JenaSwitchMonitoredInjectMatch> transformation = new JenaTransformationInjectSwitchMonitored(driver);
			final ModelOperation<JenaSwitchMonitoredInjectMatch, JenaDriver> operation = ModelOperation.of(query, transformation);
			return operation;
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
			final JenaQuery<JenaSwitchSetInjectMatch> query = JenaQuery.create(driver, workspacePath, RailwayQuery.SWITCHSET_INJECT);
			final JenaTransformation<JenaSwitchSetInjectMatch> transformation = new JenaTransformationInjectSwitchSet(driver);
			final ModelOperation<JenaSwitchSetInjectMatch, JenaDriver> operation = ModelOperation.of(query, transformation);
			return operation;
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
