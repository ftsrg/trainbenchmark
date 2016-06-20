package hu.bme.mit.trainbenchmark.benchmark.eclipseocl.operations;

import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.queries.EclipseOclQuery;
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.queries.EclipseOclQueryConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.queries.EclipseOclQueryPosLength;
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.queries.EclipseOclQueryRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.queries.EclipseOclQuerySemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.queries.EclipseOclQuerySwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.queries.EclipseOclQuerySwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.EmfTransformation;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairConnectedSegments;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairPosLength;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairRouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairSemaphoreNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairSwitchMonitored;
import hu.bme.mit.trainbenchmark.benchmark.emf.transformation.repair.EmfTransformationRepairSwitchSet;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class EclipseOclModelOperationFactory extends ModelOperationFactory<EmfMatch, EmfDriver> {

	protected EclipseOclModelOperationFactory() {

	}

	public static EclipseOclModelOperationFactory create() {
		return new EclipseOclModelOperationFactory();
	}

	@Override
	public ModelOperation<? extends EmfMatch, EmfDriver> createOperation(final RailwayOperation operationEnum,
			final Optional<String> workspaceDir, final EmfDriver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final EclipseOclQueryConnectedSegments query = new EclipseOclQueryConnectedSegments(driver, workspaceDir);
			final ModelOperation<EmfConnectedSegmentsMatch, EmfDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			// TODO
			break;

			// final EmfApiQuery<, EmfDriver> query = new EmfApiQueryConnectedSegments<>(driver);
			// final EmfTransformation<, EmfDriver> transformation = new EmfTransformationInjectConnectedSegments<>(
			// driver);
			// final ModelOperation<, EmfDriver> operation = ModelOperation.of(query, transformation);
			// return operation;

		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final EclipseOclQuery<EmfConnectedSegmentsMatch> query = new EclipseOclQueryConnectedSegments(driver,
					workspaceDir);
			final EmfTransformation<EmfConnectedSegmentsMatch, EmfDriver> transformation = new EmfTransformationRepairConnectedSegments<>(
					driver);
			final ModelOperation<EmfConnectedSegmentsMatch, EmfDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final EclipseOclQuery<EmfPosLengthMatch> query = new EclipseOclQueryPosLength(driver, workspaceDir);
			final ModelOperation<EmfPosLengthMatch, EmfDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			// TODO
			break;
		}
		case POSLENGTH_REPAIR: {
			final EclipseOclQuery<EmfPosLengthMatch> query = new EclipseOclQueryPosLength(driver, workspaceDir);
			final EmfTransformation<EmfPosLengthMatch, EmfDriver> transformation = new EmfTransformationRepairPosLength<>(
					driver);
			final ModelOperation<EmfPosLengthMatch, EmfDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// RouteSensor
		case ROUTESENSOR: {
			final EclipseOclQuery<EmfRouteSensorMatch> query = new EclipseOclQueryRouteSensor(driver, workspaceDir);
			final ModelOperation<EmfRouteSensorMatch, EmfDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			// TODO
			break;
		}
		case ROUTESENSOR_REPAIR: {
			final EclipseOclQuery<EmfRouteSensorMatch> query = new EclipseOclQueryRouteSensor(driver, workspaceDir);
			final EmfTransformation<EmfRouteSensorMatch, EmfDriver> transformation = new EmfTransformationRepairRouteSensor<>(
					driver);
			final ModelOperation<EmfRouteSensorMatch, EmfDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final EclipseOclQuery<EmfSemaphoreNeighborMatch> query = new EclipseOclQuerySemaphoreNeighbor(driver,
					workspaceDir);
			final ModelOperation<EmfSemaphoreNeighborMatch, EmfDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			// TODO
			break;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final EclipseOclQuery<EmfSemaphoreNeighborMatch> query = new EclipseOclQuerySemaphoreNeighbor(driver,
					workspaceDir);
			final EmfTransformation<EmfSemaphoreNeighborMatch, EmfDriver> transformation = new EmfTransformationRepairSemaphoreNeighbor<>(
					driver);
			final ModelOperation<EmfSemaphoreNeighborMatch, EmfDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final EclipseOclQuery<EmfSwitchMonitoredMatch> query = new EclipseOclQuerySwitchMonitored(driver,
					workspaceDir);
			final ModelOperation<EmfSwitchMonitoredMatch, EmfDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			// TODO
			break;
		}
		case SWITCHMONITORED_REPAIR: {
			final EclipseOclQuery<EmfSwitchMonitoredMatch> query = new EclipseOclQuerySwitchMonitored(driver,
					workspaceDir);
			final EmfTransformation<EmfSwitchMonitoredMatch, EmfDriver> transformation = new EmfTransformationRepairSwitchMonitored<>(
					driver);
			final ModelOperation<EmfSwitchMonitoredMatch, EmfDriver> operation = ModelOperation.of(query,
					transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final EclipseOclQuery<EmfSwitchSetMatch> query = new EclipseOclQuerySwitchSet(driver, workspaceDir);
			final ModelOperation<EmfSwitchSetMatch, EmfDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			// TODO
			break;
		}
		case SWITCHSET_REPAIR: {
			final EclipseOclQuery<EmfSwitchSetMatch> query = new EclipseOclQuerySwitchSet(driver, workspaceDir);
			final EmfTransformation<EmfSwitchSetMatch, EmfDriver> transformation = new EmfTransformationRepairSwitchSet<>(
					driver);
			final ModelOperation<EmfSwitchSetMatch, EmfDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		}
		throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
	}

}
