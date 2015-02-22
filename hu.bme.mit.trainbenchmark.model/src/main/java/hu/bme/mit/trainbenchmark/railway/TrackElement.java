/**
 */
package hu.bme.mit.trainbenchmark.railway;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Track Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.TrackElement#getTrackElement_sensor <em>Track Element sensor</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.TrackElement#getTrackElement_connectsTo <em>Track Element connects To</em>}</li>
 * </ul>
 * </p>
 *
 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getTrackElement()
 * @model
 * @generated
 */
public interface TrackElement extends Thing {
	/**
	 * Returns the value of the '<em><b>Track Element sensor</b></em>' reference list.
	 * The list contents are of type {@link hu.bme.mit.trainbenchmark.railway.Sensor}.
	 * It is bidirectional and its opposite is '{@link hu.bme.mit.trainbenchmark.railway.Sensor#getSensor_trackElement <em>Sensor track Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Track Element sensor</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Track Element sensor</em>' reference list.
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getTrackElement_TrackElement_sensor()
	 * @see hu.bme.mit.trainbenchmark.railway.Sensor#getSensor_trackElement
	 * @model opposite="Sensor_trackElement"
	 * @generated
	 */
	EList<Sensor> getTrackElement_sensor();

	/**
	 * Returns the value of the '<em><b>Track Element connects To</b></em>' reference list.
	 * The list contents are of type {@link hu.bme.mit.trainbenchmark.railway.TrackElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Track Element connects To</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Track Element connects To</em>' reference list.
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getTrackElement_TrackElement_connectsTo()
	 * @model
	 * @generated
	 */
	EList<TrackElement> getTrackElement_connectsTo();

} // TrackElement
