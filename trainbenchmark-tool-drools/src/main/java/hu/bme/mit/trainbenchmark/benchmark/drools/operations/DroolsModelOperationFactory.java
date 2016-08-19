package hu.bme.mit.trainbenchmark.benchmark.drools.operations;

import java.util.Optional;

import hu.bme.mit.trainbenchmark.benchmark.drools.driver.DroolsDriver;
import hu.bme.mit.trainbenchmark.benchmark.drools.matches.DroolsConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools.matches.DroolsPosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools.matches.DroolsRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools.matches.DroolsSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools.matches.DroolsSwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools.matches.DroolsSwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.drools.queries.DroolsQuery;
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

public class DroolsModelOperationFactory extends ModelOperationFactory<EmfMatch, DroolsDriver> {

	@Override
	public ModelOperation<? extends EmfMatch, DroolsDriver> createOperation(final RailwayOperation operationEnum, final Optional<String> workspaceDir,
			final DroolsDriver driver) throws Exception {

		switch (operationEnum) {
		// ConnectedSegments
		case CONNECTEDSEGMENTS: {
			final DroolsQuery<DroolsConnectedSegmentsMatch> query = DroolsQuery.create(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final ModelOperation<DroolsConnectedSegmentsMatch, DroolsDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case CONNECTEDSEGMENTS_INJECT: {
			final EmfApiQuery<EmfConnectedSegmentsInjectMatch, DroolsDriver> query = new EmfApiQueryConnectedSegmentsInject<>(driver);
			final EmfTransformation<EmfConnectedSegmentsInjectMatch, DroolsDriver> transformation = new EmfTransformationInjectConnectedSegments<>(driver);
			final ModelOperation<EmfConnectedSegmentsInjectMatch, DroolsDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case CONNECTEDSEGMENTS_REPAIR: {
			final DroolsQuery<DroolsConnectedSegmentsMatch> query = DroolsQuery.create(driver, workspaceDir, RailwayQuery.CONNECTEDSEGMENTS);
			final EmfTransformationRepairConnectedSegments<DroolsDriver, DroolsConnectedSegmentsMatch> transformation = new EmfTransformationRepairConnectedSegments<>(
					driver);
			final ModelOperation<DroolsConnectedSegmentsMatch, DroolsDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// PosLength
		case POSLENGTH: {
			final DroolsQuery<DroolsPosLengthMatch> query = DroolsQuery.create(driver, workspaceDir, RailwayQuery.POSLENGTH);
			final ModelOperation<DroolsPosLengthMatch, DroolsDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case POSLENGTH_INJECT: {
			final EmfApiQuery<EmfPosLengthInjectMatch, DroolsDriver> query = new EmfApiQueryPosLengthInject<>(driver);
			final EmfTransformation<EmfPosLengthInjectMatch, DroolsDriver> transformation = new EmfTransformationInjectPosLength<>(driver);
			final ModelOperation<EmfPosLengthInjectMatch, DroolsDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case POSLENGTH_REPAIR: {
			final DroolsQuery<DroolsPosLengthMatch> query = DroolsQuery.create(driver, workspaceDir, RailwayQuery.POSLENGTH);
			final EmfTransformation<DroolsPosLengthMatch, DroolsDriver> transformation = new EmfTransformationRepairPosLength<>(driver);
			final ModelOperation<DroolsPosLengthMatch, DroolsDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// RouteSensor
		case ROUTESENSOR: {
			final DroolsQuery<DroolsRouteSensorMatch> query = DroolsQuery.create(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
			final ModelOperation<DroolsRouteSensorMatch, DroolsDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case ROUTESENSOR_INJECT: {
			final EmfApiQuery<EmfRouteSensorInjectMatch, DroolsDriver> query = new EmfApiQueryRouteSensorInject<>(driver);
			final EmfTransformation<EmfRouteSensorInjectMatch, DroolsDriver> transformation = new EmfTransformationInjectRouteSensor<>(driver);
			final ModelOperation<EmfRouteSensorInjectMatch, DroolsDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case ROUTESENSOR_REPAIR: {
			final DroolsQuery<DroolsRouteSensorMatch> query = DroolsQuery.create(driver, workspaceDir, RailwayQuery.ROUTESENSOR);
			final EmfTransformationRepairRouteSensor<DroolsDriver, DroolsRouteSensorMatch> transformation = new EmfTransformationRepairRouteSensor<>(driver);
			final ModelOperation<DroolsRouteSensorMatch, DroolsDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SemaphoreNeighbor
		case SEMAPHORENEIGHBOR: {
			final DroolsQuery<DroolsSemaphoreNeighborMatch> query = DroolsQuery.create(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final ModelOperation<DroolsSemaphoreNeighborMatch, DroolsDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SEMAPHORENEIGHBOR_INJECT: {
			final EmfApiQuery<EmfSemaphoreNeighborInjectMatch, DroolsDriver> query = new EmfApiQuerySemaphoreNeighborInject<>(driver);
			final EmfTransformation<EmfSemaphoreNeighborInjectMatch, DroolsDriver> transformation = new EmfTransformationInjectSemaphoreNeighbor<>(driver);
			final ModelOperation<EmfSemaphoreNeighborInjectMatch, DroolsDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SEMAPHORENEIGHBOR_REPAIR: {
			final DroolsQuery<DroolsSemaphoreNeighborMatch> query = DroolsQuery.create(driver, workspaceDir, RailwayQuery.SEMAPHORENEIGHBOR);
			final EmfTransformationRepairSemaphoreNeighbor<DroolsDriver, DroolsSemaphoreNeighborMatch> transformation = new EmfTransformationRepairSemaphoreNeighbor<>(
					driver);
			final ModelOperation<DroolsSemaphoreNeighborMatch, DroolsDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchMonitored
		case SWITCHMONITORED: {
			final DroolsQuery<DroolsSwitchMonitoredMatch> query = DroolsQuery.create(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
			final ModelOperation<DroolsSwitchMonitoredMatch, DroolsDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHMONITORED_INJECT: {
			final EmfApiQuery<EmfSwitchMonitoredInjectMatch, DroolsDriver> query = new EmfApiQuerySwitchMonitoredInject<>(driver);
			final EmfTransformation<EmfSwitchMonitoredInjectMatch, DroolsDriver> transformation = new EmfTransformationInjectSwitchMonitored<>(driver);
			final ModelOperation<EmfSwitchMonitoredInjectMatch, DroolsDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHMONITORED_REPAIR: {
			final DroolsQuery<DroolsSwitchMonitoredMatch> query = DroolsQuery.create(driver, workspaceDir, RailwayQuery.SWITCHMONITORED);
			final EmfTransformationRepairSwitchMonitored<DroolsDriver, DroolsSwitchMonitoredMatch> transformation = new EmfTransformationRepairSwitchMonitored<>(
					driver);
			final ModelOperation<DroolsSwitchMonitoredMatch, DroolsDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}

			// SwitchSet
		case SWITCHSET: {
			final DroolsQuery<DroolsSwitchSetMatch> query = DroolsQuery.create(driver, workspaceDir, RailwayQuery.SWITCHSET);
			final ModelOperation<DroolsSwitchSetMatch, DroolsDriver> operation = ModelOperation.of(query);
			return operation;
		}
		case SWITCHSET_INJECT: {
			final EmfApiQuery<EmfSwitchSetInjectMatch, DroolsDriver> query = new EmfApiQuerySwitchSetInject<>(driver);
			final EmfTransformation<EmfSwitchSetInjectMatch, DroolsDriver> transformation = new EmfTransformationInjectSwitchSet<>(driver);
			final ModelOperation<EmfSwitchSetInjectMatch, DroolsDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		case SWITCHSET_REPAIR: {
			final DroolsQuery<DroolsSwitchSetMatch> query = DroolsQuery.create(driver, workspaceDir, RailwayQuery.SWITCHSET);
			final EmfTransformation<DroolsSwitchSetMatch, DroolsDriver> transformation = new EmfTransformationRepairSwitchSet<>(driver);
			final ModelOperation<DroolsSwitchSetMatch, DroolsDriver> operation = ModelOperation.of(query, transformation);
			return operation;
		}
		default:
			break;
		}
		throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
	}

}
