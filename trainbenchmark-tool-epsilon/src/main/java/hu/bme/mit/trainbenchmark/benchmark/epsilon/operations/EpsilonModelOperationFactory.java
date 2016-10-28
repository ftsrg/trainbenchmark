package hu.bme.mit.trainbenchmark.benchmark.epsilon.operations;

import org.eclipse.epsilon.etl.EtlModule;

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;

public class EpsilonModelOperationFactory<TDriver extends EmfDriver> extends ModelOperationFactory<EmfMatch, TDriver> {

	@Override
	public ModelOperation<? extends EmfMatch, TDriver> createOperation(final RailwayOperation operationEnum, final String workspaceDir, final TDriver driver)
			throws Exception {
		final EtlModule etl = new EtlModule();

		// switch (operationEnum) {
		// // ConnectedSegments
		// case CONNECTEDSEGMENTS: {
		// final EpsilonQuery<EmfConnectedSegmentsMatch, TDriver> query = new EpsilonQueryConnectedSegments<>(driver);
		// final ModelOperation<EmfConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query);
		// return operation;
		// }
		// case CONNECTEDSEGMENTS_INJECT: {
		// final EpsilonQuery<EmfConnectedSegmentsInjectMatch, TDriver> query = new
		// EpsilonQueryConnectedSegmentsInject<>(driver);
		// final EmfTransformation<EmfConnectedSegmentsInjectMatch, TDriver> transformation = new
		// EmfTransformationInjectConnectedSegments<>(driver);
		// final ModelOperation<EmfConnectedSegmentsInjectMatch, TDriver> operation = ModelOperation.of(query,
		// transformation);
		// return operation;
		//
		// }
		// case CONNECTEDSEGMENTS_REPAIR: {
		// final EpsilonQuery<EmfConnectedSegmentsMatch, TDriver> query = new EpsilonQueryConnectedSegments<>(driver);
		// final EmfTransformation<EmfConnectedSegmentsMatch, TDriver> transformation = new
		// EmfTransformationRepairConnectedSegments<>(driver);
		// final ModelOperation<EmfConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query,
		// transformation);
		// return operation;
		// }
		//
		// // PosLength
		// case POSLENGTH: {
		// final EpsilonQuery<EmfPosLengthMatch, TDriver> query = new EpsilonQueryPosLength<>(driver);
		// final ModelOperation<EmfPosLengthMatch, TDriver> operation = ModelOperation.of(query);
		// return operation;
		// }
		// case POSLENGTH_INJECT: {
		// final EpsilonQuery<EmfPosLengthInjectMatch, TDriver> query = new EpsilonQueryPosLengthInject<>(driver);
		// final EmfTransformation<EmfPosLengthInjectMatch, TDriver> transformation = new
		// EmfTransformationInjectPosLength<>(driver);
		// final ModelOperation<EmfPosLengthInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
		// return operation;
		// }
		// case POSLENGTH_REPAIR: {
		// final EpsilonQuery<EmfPosLengthMatch, TDriver> query = new EpsilonQueryPosLength<>(driver);
		// final EmfTransformation<EmfPosLengthMatch, TDriver> transformation = new
		// EmfTransformationRepairPosLength<>(driver);
		// final ModelOperation<EmfPosLengthMatch, TDriver> operation = ModelOperation.of(query, transformation);
		// return operation;
		// }
		//
		// // RouteSensor
		// case ROUTESENSOR: {
		// final EpsilonQuery<EmfRouteSensorMatch, TDriver> query = new EpsilonQueryRouteSensor<>(driver);
		// final ModelOperation<EmfRouteSensorMatch, TDriver> operation = ModelOperation.of(query);
		// return operation;
		// }
		// case ROUTESENSOR_INJECT: {
		// final EpsilonQuery<EmfRouteSensorInjectMatch, TDriver> query = new EpsilonQueryRouteSensorInject<>(driver);
		// final EmfTransformation<EmfRouteSensorInjectMatch, TDriver> transformation = new
		// EmfTransformationInjectRouteSensor<>(driver);
		// final ModelOperation<EmfRouteSensorInjectMatch, TDriver> operation = ModelOperation.of(query,
		// transformation);
		// return operation;
		// }
		// case ROUTESENSOR_REPAIR: {
		// final EpsilonQuery<EmfRouteSensorMatch, TDriver> query = new EpsilonQueryRouteSensor<>(driver);
		// final EmfTransformation<EmfRouteSensorMatch, TDriver> transformation = new
		// EmfTransformationRepairRouteSensor<>(driver);
		// final ModelOperation<EmfRouteSensorMatch, TDriver> operation = ModelOperation.of(query, transformation);
		// return operation;
		// }
		//
		// // SemaphoreNeighbor
		// case SEMAPHORENEIGHBOR: {
		// final EpsilonQuery<EmfSemaphoreNeighborMatch, TDriver> query = new EpsilonQuerySemaphoreNeighbor<>(driver);
		// final ModelOperation<EmfSemaphoreNeighborMatch, TDriver> operation = ModelOperation.of(query);
		// return operation;
		// }
		// case SEMAPHORENEIGHBOR_INJECT: {
		// final EpsilonQuery<EmfSemaphoreNeighborInjectMatch, TDriver> query = new
		// EpsilonQuerySemaphoreNeighborInject<>(driver);
		// final EmfTransformation<EmfSemaphoreNeighborInjectMatch, TDriver> transformation = new
		// EmfTransformationInjectSemaphoreNeighbor<>(driver);
		// final ModelOperation<EmfSemaphoreNeighborInjectMatch, TDriver> operation = ModelOperation.of(query,
		// transformation);
		// return operation;
		// }
		// case SEMAPHORENEIGHBOR_REPAIR: {
		// final EpsilonQuery<EmfSemaphoreNeighborMatch, TDriver> query = new EpsilonQuerySemaphoreNeighbor<>(driver);
		// final EmfTransformation<EmfSemaphoreNeighborMatch, TDriver> transformation = new
		// EmfTransformationRepairSemaphoreNeighbor<>(driver);
		// final ModelOperation<EmfSemaphoreNeighborMatch, TDriver> operation = ModelOperation.of(query,
		// transformation);
		// return operation;
		// }
		//
		// // SwitchMonitored
		// case SWITCHMONITORED: {
		// final EpsilonQuery<EmfSwitchMonitoredMatch, TDriver> query = new EpsilonQuerySwitchMonitored<>(driver);
		// final ModelOperation<EmfSwitchMonitoredMatch, TDriver> operation = ModelOperation.of(query);
		// return operation;
		// }
		// case SWITCHMONITORED_INJECT: {
		// final EpsilonQuery<EmfSwitchMonitoredInjectMatch, TDriver> query = new
		// EpsilonQuerySwitchMonitoredInject<>(driver);
		// final EmfTransformation<EmfSwitchMonitoredInjectMatch, TDriver> transformation = new
		// EmfTransformationInjectSwitchMonitored<>(driver);
		// final ModelOperation<EmfSwitchMonitoredInjectMatch, TDriver> operation = ModelOperation.of(query,
		// transformation);
		// return operation;
		// }
		// case SWITCHMONITORED_REPAIR: {
		// final EpsilonQuery<EmfSwitchMonitoredMatch, TDriver> query = new EpsilonQuerySwitchMonitored<>(driver);
		// final EmfTransformation<EmfSwitchMonitoredMatch, TDriver> transformation = new
		// EmfTransformationRepairSwitchMonitored<>(driver);
		// final ModelOperation<EmfSwitchMonitoredMatch, TDriver> operation = ModelOperation.of(query, transformation);
		// return operation;
		// }
		//
		// // SwitchSet
		// case SWITCHSET: {
		// final EpsilonQuery<EmfSwitchSetMatch, TDriver> query = new EpsilonQuerySwitchSet<>(driver);
		// final ModelOperation<EmfSwitchSetMatch, TDriver> operation = ModelOperation.of(query);
		// return operation;
		// }
		// case SWITCHSET_INJECT: {
		// final EpsilonQuery<EmfSwitchSetInjectMatch, TDriver> query = new EpsilonQuerySwitchSetInject<>(driver);
		// final EmfTransformation<EmfSwitchSetInjectMatch, TDriver> transformation = new
		// EmfTransformationInjectSwitchSet<>(driver);
		// final ModelOperation<EmfSwitchSetInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
		// return operation;
		// }
		// case SWITCHSET_REPAIR: {
		// final EpsilonQuery<EmfSwitchSetMatch, TDriver> query = new EpsilonQuerySwitchSet<>(driver);
		// final EmfTransformation<EmfSwitchSetMatch, TDriver> transformation = new
		// EmfTransformationRepairSwitchSet<>(driver);
		// final ModelOperation<EmfSwitchSetMatch, TDriver> operation = ModelOperation.of(query, transformation);
		// return operation;
		// }
		// default:
		// break;
		// }
		throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
	}

}
