/**
 */
package hu.bme.mit.trainbenchmark.schedule;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.ScheduleContainer#getSchedules <em>Schedules</em>}</li>
 * </ul>
 * </p>
 *
 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getScheduleContainer()
 * @model
 * @generated
 */
public interface ScheduleContainer extends EObject {
        /**
         * Returns the value of the '<em><b>Schedules</b></em>' containment reference list.
         * The list contents are of type {@link hu.bme.mit.trainbenchmark.schedule.Schedule}.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Schedules</em>' containment reference list isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Schedules</em>' containment reference list.
         * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getScheduleContainer_Schedules()
         * @model containment="true"
         * @generated
         */
        EList<Schedule> getSchedules();

} // ScheduleContainer
