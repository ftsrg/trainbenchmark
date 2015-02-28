/**
 */
package hu.bme.mit.trainbenchmark.railway;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Switch Position</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitchPosition_switch <em>Switch Position switch</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitchPosition_switchState <em>Switch Position switch State</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitchPosition_route <em>Switch Position route</em>}</li>
 * </ul>
 * </p>
 *
 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getSwitchPosition()
 * @model
 * @generated
 */
public interface SwitchPosition extends RailwayElement {
	/**
	 * Returns the value of the '<em><b>Switch Position switch</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link hu.bme.mit.trainbenchmark.railway.Switch#getSwitch_switchPosition <em>Switch switch Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Switch Position switch</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Switch Position switch</em>' reference.
	 * @see #setSwitchPosition_switch(Switch)
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getSwitchPosition_SwitchPosition_switch()
	 * @see hu.bme.mit.trainbenchmark.railway.Switch#getSwitch_switchPosition
	 * @model opposite="Switch_switchPosition" required="true"
	 * @generated
	 */
	Switch getSwitchPosition_switch();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitchPosition_switch <em>Switch Position switch</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Switch Position switch</em>' reference.
	 * @see #getSwitchPosition_switch()
	 * @generated
	 */
	void setSwitchPosition_switch(Switch value);

	/**
	 * Returns the value of the '<em><b>Switch Position switch State</b></em>' attribute.
	 * The literals are from the enumeration {@link hu.bme.mit.trainbenchmark.railway.SwitchState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Switch Position switch State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Switch Position switch State</em>' attribute.
	 * @see hu.bme.mit.trainbenchmark.railway.SwitchState
	 * @see #setSwitchPosition_switchState(SwitchState)
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getSwitchPosition_SwitchPosition_switchState()
	 * @model required="true"
	 * @generated
	 */
	SwitchState getSwitchPosition_switchState();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitchPosition_switchState <em>Switch Position switch State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Switch Position switch State</em>' attribute.
	 * @see hu.bme.mit.trainbenchmark.railway.SwitchState
	 * @see #getSwitchPosition_switchState()
	 * @generated
	 */
	void setSwitchPosition_switchState(SwitchState value);

	/**
	 * Returns the value of the '<em><b>Switch Position route</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link hu.bme.mit.trainbenchmark.railway.Route#getRoute_switchPosition <em>Route switch Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Switch Position route</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Switch Position route</em>' reference.
	 * @see #setSwitchPosition_route(Route)
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getSwitchPosition_SwitchPosition_route()
	 * @see hu.bme.mit.trainbenchmark.railway.Route#getRoute_switchPosition
	 * @model opposite="Route_switchPosition" required="true"
	 * @generated
	 */
	Route getSwitchPosition_route();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitchPosition_route <em>Switch Position route</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Switch Position route</em>' reference.
	 * @see #getSwitchPosition_route()
	 * @generated
	 */
	void setSwitchPosition_route(Route value);

} // SwitchPosition
