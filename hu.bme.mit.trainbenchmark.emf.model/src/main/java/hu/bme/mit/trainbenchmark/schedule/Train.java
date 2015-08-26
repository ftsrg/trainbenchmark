/**
 */
package hu.bme.mit.trainbenchmark.schedule;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Train</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Train#getSchedules <em>Schedules</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Train#getAssociations <em>Associations</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Train#getUid <em>Uid</em>}</li>
 * </ul>
 * </p>
 *
 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getTrain()
 * @model
 * @generated
 */
public interface Train extends EObject {
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
         * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getTrain_Schedules()
         * @model containment="true" required="true"
         * @generated
         */
        EList<Schedule> getSchedules();

        /**
         * Returns the value of the '<em><b>Associations</b></em>' containment reference list.
         * The list contents are of type {@link hu.bme.mit.trainbenchmark.schedule.Association}.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Associations</em>' containment reference list isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Associations</em>' containment reference list.
         * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getTrain_Associations()
         * @model containment="true"
         * @generated
         */
        EList<Association> getAssociations();

        /**
         * Returns the value of the '<em><b>Uid</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Uid</em>' attribute isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Uid</em>' attribute.
         * @see #setUid(String)
         * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getTrain_Uid()
         * @model required="true"
         * @generated
         */
        String getUid();

        /**
         * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Train#getUid <em>Uid</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @param value the new value of the '<em>Uid</em>' attribute.
         * @see #getUid()
         * @generated
         */
        void setUid(String value);

} // Train
