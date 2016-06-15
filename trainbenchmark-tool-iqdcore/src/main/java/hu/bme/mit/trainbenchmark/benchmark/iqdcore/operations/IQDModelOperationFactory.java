package hu.bme.mit.trainbenchmark.benchmark.iqdcore.operations;

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

/**
 * Created by wafle on 6/10/2016.
 */
public class IQDModelOperationFactory extends ModelOperationFactory<IQDCoreMatch, IQDCoreDriver> {
    @Override
    public ModelOperation<? extends IQDCoreMatch, IQDCoreDriver> createOperation(RailwayOperation operationEnum, Optional<String> workspacePath, IQDCoreDriver driver) throws Exception {
        switch (operationEnum) {
            // ConnectedSegments
            case CONNECTEDSEGMENTS: {
                final IQDCoreQuery<IQDCoreConnectedSegmentsMatch> query = IQDCoreQuery.create(driver, workspacePath,
                        RailwayQuery.CONNECTEDSEGMENTS);
                final ModelOperation<IQDCoreConnectedSegmentsMatch, IQDCoreDriver> operation = ModelOperation.of(query);
                return operation;
            }
            case CONNECTEDSEGMENTS_INJECT: {
                // TODO

            }
            case CONNECTEDSEGMENTS_REPAIR: {
                final IQDCoreQuery<IQDCoreConnectedSegmentsMatch> query = IQDCoreQuery.create(driver, workspacePath,
                        RailwayQuery.CONNECTEDSEGMENTS);
                final IQDCoreTransformation<IQDCoreConnectedSegmentsMatch> transformation = new IQDCoreTransformationRepairConnectedSegments(
                        driver);
                final ModelOperation<IQDCoreConnectedSegmentsMatch, IQDCoreDriver> operation = ModelOperation.of(query,
                        transformation);
                return operation;
            }

            // PosLength
            case POSLENGTH: {
                final IQDCoreQuery<IQDCorePosLengthMatch> query = IQDCoreQuery.create(driver, workspacePath,
                        RailwayQuery.POSLENGTH);
                final ModelOperation<IQDCorePosLengthMatch, IQDCoreDriver> operation = ModelOperation.of(query);
                return operation;
            }
            case POSLENGTH_INJECT: {
                // TODO
            }
            case POSLENGTH_REPAIR: {
                final IQDCoreQuery<IQDCorePosLengthMatch> query = IQDCoreQuery.create(driver, workspacePath,
                        RailwayQuery.POSLENGTH);
                final IQDCoreTransformation<IQDCorePosLengthMatch> transformation = new IQDCoreTransformationRepairPosLength(
                        driver);
                final ModelOperation<IQDCorePosLengthMatch, IQDCoreDriver> operation = ModelOperation.of(query,
                        transformation);
                return operation;

            }

            // RouteSensor
            case ROUTESENSOR: {
                final IQDCoreQuery<IQDCoreRouteSensorMatch> query = IQDCoreQuery.create(driver, workspacePath,
                        RailwayQuery.ROUTESENSOR);
                final ModelOperation<IQDCoreRouteSensorMatch, IQDCoreDriver> operation = ModelOperation.of(query);
                return operation;
            }
            case ROUTESENSOR_INJECT: {
                // TODO
            }
            case ROUTESENSOR_REPAIR: {
                final IQDCoreQuery<IQDCoreRouteSensorMatch> query = IQDCoreQuery.create(driver, workspacePath,
                        RailwayQuery.ROUTESENSOR);
                final IQDCoreTransformation<IQDCoreRouteSensorMatch> transformation = new IQDCoreTransformationRepairRouteSensor(
                        driver);
                final ModelOperation<IQDCoreRouteSensorMatch, IQDCoreDriver> operation = ModelOperation.of(query,
                        transformation);
                return operation;
            }

            // SemaphoreNeighbor
            case SEMAPHORENEIGHBOR: {
                final IQDCoreQuery<IQDCoreSemaphoreNeighborMatch> query = IQDCoreQuery.create(driver, workspacePath,
                        RailwayQuery.SEMAPHORENEIGHBOR);
                final ModelOperation<IQDCoreSemaphoreNeighborMatch, IQDCoreDriver> operation = ModelOperation.of(query);
                return operation;
            }
            case SEMAPHORENEIGHBOR_INJECT: {
                // TODO
            }
            case SEMAPHORENEIGHBOR_REPAIR: {
                final IQDCoreQuery<IQDCoreSemaphoreNeighborMatch> query = IQDCoreQuery.create(driver, workspacePath,
                        RailwayQuery.SEMAPHORENEIGHBOR);
                final IQDCoreTransformation<IQDCoreSemaphoreNeighborMatch> transformation = new IQDCoreTransformationRepairSemaphoreNeighbor(
                        driver);
                final ModelOperation<IQDCoreSemaphoreNeighborMatch, IQDCoreDriver> operation = ModelOperation.of(query,
                        transformation);
                return operation;
            }

            // SwitchMonitored
            case SWITCHMONITORED: {
                final IQDCoreQuery<IQDCoreSwitchMonitoredMatch> query = IQDCoreQuery.create(driver, workspacePath,
                        RailwayQuery.SWITCHMONITORED);
                final ModelOperation<IQDCoreSwitchMonitoredMatch, IQDCoreDriver> operation = ModelOperation.of(query);
                return operation;
            }
            case SWITCHMONITORED_INJECT: {
                // TODO
            }
            case SWITCHMONITORED_REPAIR: {
                final IQDCoreQuery<IQDCoreSwitchMonitoredMatch> query = IQDCoreQuery.create(driver, workspacePath,
                        RailwayQuery.SWITCHMONITORED);
                final IQDCoreTransformation<IQDCoreSwitchMonitoredMatch> transformation = new IQDCoreTransformationRepairSwitchMonitored(
                        driver);
                final ModelOperation<IQDCoreSwitchMonitoredMatch, IQDCoreDriver> operation = ModelOperation.of(query,
                        transformation);
                return operation;
            }

            // SwitchSet
            case SWITCHSET: {
                final IQDCoreQuery<IQDCoreSwitchSetMatch> query = IQDCoreQuery.create(driver, workspacePath,
                        RailwayQuery.SWITCHSET);
                final ModelOperation<IQDCoreSwitchSetMatch, IQDCoreDriver> operation = ModelOperation.of(query);
                return operation;
            }
            case SWITCHSET_INJECT: {
                // TODO
            }
            case SWITCHSET_REPAIR: {
                final IQDCoreQuery<IQDCoreSwitchSetMatch> query = IQDCoreQuery.create(driver, workspacePath,
                        RailwayQuery.SWITCHSET);
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
