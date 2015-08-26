/**
 */
package hu.bme.mit.trainbenchmark.schedule;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Train Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.TrainContainer#getTrains <em>Trains</em>}</li>
 * </ul>
 * </p>
 *
 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getTrainContainer()
 * @model
 * @generated
 */
public interface TrainContainer extends EObject {
        /**
         * Returns the value of the '<em><b>Trains</b></em>' containment reference list.
         * The list contents are of type {@link hu.bme.mit.trainbenchmark.schedule.Train}.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Trains</em>' containment reference list isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Trains</em>' containment reference list.
         * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getTrainContainer_Trains()
         * @model containment="true" required="true"
         * @generated
         */
        EList<Train> getTrains();

} // TrainContainer
