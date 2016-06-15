package hu.bme.mit.trainbenchmark.benchmark.iqdcore.operations;

import hu.bme.mit.incqueryds.WildcardInput;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.driver.IQDCoreDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.IQDCoreConnectedSegmentsMatch;

import hu.bme.mit.trainbenchmark.benchmark.iqdcore.match.*;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.queries.IQDCoreQuery;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.*;
import hu.bme.mit.trainbenchmark.benchmark.iqdcore.transformations.repair.*;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

import java.util.Optional;

public class IQDModelOperationFactory extends ModelOperationFactory<IQDCoreMatch, IQDCoreDriver> {
    private WildcardInput input;

    public IQDModelOperationFactory(WildcardInput input) {
        this.input = input;
    }

    @Override
    public ModelOperation<? extends IQDCoreMatch, IQDCoreDriver> createOperation(RailwayOperation operationEnum, Optional<String> workspacePath, IQDCoreDriver driver) throws Exception {
        final String queryDirectory = workspacePath.get() + "trainbenchmark-tool-iqdcore/src/main/resources/";
        switch (operationEnum) {
            // ConnectedSegments
            case CONNECTEDSEGMENTS: {
                final IQDCoreQuery<IQDCoreConnectedSegmentsMatch> query = IQDCoreQuery.create(driver, queryDirectory,
                        RailwayQuery.CONNECTEDSEGMENTS, input);
                final ModelOperation<IQDCoreConnectedSegmentsMatch, IQDCoreDriver> operation = ModelOperation.of(query);
                return operation;
            }
            case CONNECTEDSEGMENTS_INJECT: {
                // TONOTDO
            }
            case CONNECTEDSEGMENTS_REPAIR: {
                final IQDCoreQuery<IQDCoreConnectedSegmentsMatch> query = IQDCoreQuery.create(driver, queryDirectory,
                        RailwayQuery.CONNECTEDSEGMENTS, input);
                final IQDCoreTransformation<IQDCoreConnectedSegmentsMatch> transformation = new IQDCoreTransformationRepairConnectedSegments(
                        driver);
                final ModelOperation<IQDCoreConnectedSegmentsMatch, IQDCoreDriver> operation = ModelOperation.of(query,
                        transformation);
                return operation;
            }

            // PosLength
            case POSLENGTH: {
                final IQDCoreQuery<IQDCorePosLengthMatch> query = IQDCoreQuery.create(driver, queryDirectory,
                        RailwayQuery.POSLENGTH, input);
                final ModelOperation<IQDCorePosLengthMatch, IQDCoreDriver> operation = ModelOperation.of(query);
                return operation;
            }
            case POSLENGTH_INJECT: {
                // TONOTDO
            }
            case POSLENGTH_REPAIR: {
                final IQDCoreQuery<IQDCorePosLengthMatch> query = IQDCoreQuery.create(driver, queryDirectory,
                        RailwayQuery.POSLENGTH, input);
                final IQDCoreTransformation<IQDCorePosLengthMatch> transformation = new IQDCoreTransformationRepairPosLength(
                        driver);
                final ModelOperation<IQDCorePosLengthMatch, IQDCoreDriver> operation = ModelOperation.of(query,
                        transformation);
                return operation;
            }

            // RouteSensor
            case ROUTESENSOR: {
                final IQDCoreQuery<IQDCoreRouteSensorMatch> query = IQDCoreQuery.create(driver, queryDirectory,
                        RailwayQuery.ROUTESENSOR, input);
                final ModelOperation<IQDCoreRouteSensorMatch, IQDCoreDriver> operation = ModelOperation.of(query);
                return operation;
            }
            case ROUTESENSOR_INJECT: {
                // TONOTDO
            }
            case ROUTESENSOR_REPAIR: {
                final IQDCoreQuery<IQDCoreRouteSensorMatch> query = IQDCoreQuery.create(driver, queryDirectory,
                        RailwayQuery.ROUTESENSOR, input);
                final IQDCoreTransformation<IQDCoreRouteSensorMatch> transformation = new IQDCoreTransformationRepairRouteSensor(
                        driver);
                final ModelOperation<IQDCoreRouteSensorMatch, IQDCoreDriver> operation = ModelOperation.of(query,
                        transformation);
                return operation;
            }

            // SemaphoreNeighbor
            case SEMAPHORENEIGHBOR: {
                final IQDCoreQuery<IQDCoreSemaphoreNeighborMatch> query = IQDCoreQuery.create(driver, queryDirectory,
                        RailwayQuery.SEMAPHORENEIGHBOR, input);
                final ModelOperation<IQDCoreSemaphoreNeighborMatch, IQDCoreDriver> operation = ModelOperation.of(query);
                return operation;
            }
            case SEMAPHORENEIGHBOR_INJECT: {
                // TONOTDO
            }
            case SEMAPHORENEIGHBOR_REPAIR: {
                final IQDCoreQuery<IQDCoreSemaphoreNeighborMatch> query = IQDCoreQuery.create(driver, queryDirectory,
                        RailwayQuery.SEMAPHORENEIGHBOR, input);
                final IQDCoreTransformation<IQDCoreSemaphoreNeighborMatch> transformation = new IQDCoreTransformationRepairSemaphoreNeighbor(
                        driver);
                final ModelOperation<IQDCoreSemaphoreNeighborMatch, IQDCoreDriver> operation = ModelOperation.of(query,
                        transformation);
                return operation;
            }

            // SwitchMonitored
            case SWITCHMONITORED: {
                final IQDCoreQuery<IQDCoreSwitchMonitoredMatch> query = IQDCoreQuery.create(driver, queryDirectory,
                        RailwayQuery.SWITCHMONITORED, input);
                final ModelOperation<IQDCoreSwitchMonitoredMatch, IQDCoreDriver> operation = ModelOperation.of(query);
                return operation;
            }
            case SWITCHMONITORED_INJECT: {
                // TONOTDO
            }
            case SWITCHMONITORED_REPAIR: {
                final IQDCoreQuery<IQDCoreSwitchMonitoredMatch> query = IQDCoreQuery.create(driver, queryDirectory,
                        RailwayQuery.SWITCHMONITORED, input);
                final IQDCoreTransformation<IQDCoreSwitchMonitoredMatch> transformation = new IQDCoreTransformationRepairSwitchMonitored(
                        driver);
                final ModelOperation<IQDCoreSwitchMonitoredMatch, IQDCoreDriver> operation = ModelOperation.of(query,
                        transformation);
                return operation;
            }

            // SwitchSet
            case SWITCHSET: {
                final IQDCoreQuery<IQDCoreSwitchSetMatch> query = IQDCoreQuery.create(driver, queryDirectory,
                        RailwayQuery.SWITCHSET, input);
                final ModelOperation<IQDCoreSwitchSetMatch, IQDCoreDriver> operation = ModelOperation.of(query);
                return operation;
            }
            case SWITCHSET_INJECT: {
                // TONOTDO
            }
            case SWITCHSET_REPAIR: {
                final IQDCoreQuery<IQDCoreSwitchSetMatch> query = IQDCoreQuery.create(driver, queryDirectory,
                        RailwayQuery.SWITCHSET, input);
                final IQDCoreTransformation<IQDCoreSwitchSetMatch> transformation = new IQDCoreTransformationRepairSwitchSet(
                        driver);
                final ModelOperation<IQDCoreSwitchSetMatch, IQDCoreDriver> operation = ModelOperation.of(query,
                        transformation);
                return operation;
            }

            default:
                throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
        }
    }
}
