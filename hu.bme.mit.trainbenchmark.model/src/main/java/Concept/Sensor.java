/**
 */
package Concept;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sensor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link Concept.Sensor#getSensor_trackElement <em>Sensor track Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see Concept.ConceptPackage#getSensor()
 * @model
 * @generated
 */
public interface Sensor extends Thing {
	/**
	 * Returns the value of the '<em><b>Sensor track Element</b></em>' reference list.
	 * The list contents are of type {@link Concept.TrackElement}.
	 * It is bidirectional and its opposite is '{@link Concept.TrackElement#getTrackElement_sensor <em>Track Element sensor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sensor track Element</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sensor track Element</em>' reference list.
	 * @see Concept.ConceptPackage#getSensor_Sensor_trackElement()
	 * @see Concept.TrackElement#getTrackElement_sensor
	 * @model opposite="TrackElement_sensor"
	 * @generated
	 */
	EList<TrackElement> getSensor_trackElement();

} // Sensor
