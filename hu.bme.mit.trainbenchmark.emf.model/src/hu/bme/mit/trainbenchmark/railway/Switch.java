/**
 */
package hu.bme.mit.trainbenchmark.railway;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Switch</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.Switch#getSwitch_actualState <em>Switch actual State</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.Switch#getSwitch_switchPosition <em>Switch switch Position</em>}</li>
 * </ul>
 * </p>
 *
 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getSwitch()
 * @model
 * @generated
 */
public interface Switch extends TrackElement {
	/**
	 * Returns the value of the '<em><b>Switch actual State</b></em>' attribute.
	 * The literals are from the enumeration {@link hu.bme.mit.trainbenchmark.railway.SwitchState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Switch actual State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Switch actual State</em>' attribute.
	 * @see hu.bme.mit.trainbenchmark.railway.SwitchState
	 * @see #setSwitch_actualState(SwitchState)
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getSwitch_Switch_actualState()
	 * @model required="true"
	 * @generated
	 */
	SwitchState getSwitch_actualState();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.railway.Switch#getSwitch_actualState <em>Switch actual State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Switch actual State</em>' attribute.
	 * @see hu.bme.mit.trainbenchmark.railway.SwitchState
	 * @see #getSwitch_actualState()
	 * @generated
	 */
	void setSwitch_actualState(SwitchState value);

	/**
	 * Returns the value of the '<em><b>Switch switch Position</b></em>' reference list.
	 * The list contents are of type {@link hu.bme.mit.trainbenchmark.railway.SwitchPosition}.
	 * It is bidirectional and its opposite is '{@link hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitchPosition_switch <em>Switch Position switch</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Switch switch Position</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Switch switch Position</em>' reference list.
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getSwitch_Switch_switchPosition()
	 * @see hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitchPosition_switch
	 * @model opposite="SwitchPosition_switch"
	 * @generated
	 */
	EList<SwitchPosition> getSwitch_switchPosition();

} // Switch
