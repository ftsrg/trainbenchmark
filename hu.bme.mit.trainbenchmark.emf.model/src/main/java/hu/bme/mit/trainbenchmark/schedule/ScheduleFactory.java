/**
 */
package hu.bme.mit.trainbenchmark.schedule;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage
 * @generated
 */
public interface ScheduleFactory extends EFactory {
        /**
         * The singleton instance of the factory.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        ScheduleFactory eINSTANCE = hu.bme.mit.trainbenchmark.schedule.impl.ScheduleFactoryImpl.init();

        /**
         * Returns a new object of class '<em>Train Container</em>'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return a new object of class '<em>Train Container</em>'.
         * @generated
         */
        TrainContainer createTrainContainer();

        /**
         * Returns a new object of class '<em>Schedule</em>'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return a new object of class '<em>Schedule</em>'.
         * @generated
         */
        Schedule createSchedule();

        /**
         * Returns a new object of class '<em>Train</em>'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return a new object of class '<em>Train</em>'.
         * @generated
         */
        Train createTrain();

        /**
         * Returns a new object of class '<em>Station</em>'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return a new object of class '<em>Station</em>'.
         * @generated
         */
        Station createStation();

        /**
         * Returns a new object of class '<em>Association</em>'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return a new object of class '<em>Association</em>'.
         * @generated
         */
        Association createAssociation();

        /**
         * Returns the package supported by this factory.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the package supported by this factory.
         * @generated
         */
        SchedulePackage getSchedulePackage();

} //ScheduleFactory
