/**
 */
package hu.bme.mit.trainbenchmark.railway;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Route</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.Route#getRoute_entry <em>Route entry</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.Route#getRoute_switchPosition <em>Route switch Position</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.Route#getRoute_exit <em>Route exit</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.Route#getRoute_routeDefinition <em>Route route Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getRoute()
 * @model
 * @generated
 */
public interface Route extends RailwayElement {
	/**
	 * Returns the value of the '<em><b>Route entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Route entry</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Route entry</em>' reference.
	 * @see #setRoute_entry(Signal)
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getRoute_Route_entry()
	 * @model required="true"
	 * @generated
	 */
	Signal getRoute_entry();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.railway.Route#getRoute_entry <em>Route entry</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Route entry</em>' reference.
	 * @see #getRoute_entry()
	 * @generated
	 */
	void setRoute_entry(Signal value);

	/**
	 * Returns the value of the '<em><b>Route switch Position</b></em>' reference list.
	 * The list contents are of type {@link hu.bme.mit.trainbenchmark.railway.SwitchPosition}.
	 * It is bidirectional and its opposite is '{@link hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitchPosition_route <em>Switch Position route</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Route switch Position</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Route switch Position</em>' reference list.
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getRoute_Route_switchPosition()
	 * @see hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitchPosition_route
	 * @model opposite="SwitchPosition_route"
	 * @generated
	 */
	EList<SwitchPosition> getRoute_switchPosition();

	/**
	 * Returns the value of the '<em><b>Route exit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Route exit</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Route exit</em>' reference.
	 * @see #setRoute_exit(Signal)
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getRoute_Route_exit()
	 * @model required="true"
	 * @generated
	 */
	Signal getRoute_exit();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.railway.Route#getRoute_exit <em>Route exit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Route exit</em>' reference.
	 * @see #getRoute_exit()
	 * @generated
	 */
	void setRoute_exit(Signal value);

	/**
	 * Returns the value of the '<em><b>Route route Definition</b></em>' reference list.
	 * The list contents are of type {@link hu.bme.mit.trainbenchmark.railway.Sensor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Route route Definition</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Route route Definition</em>' reference list.
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getRoute_Route_routeDefinition()
	 * @model lower="2"
	 * @generated
	 */
	EList<Sensor> getRoute_routeDefinition();

} // Route
