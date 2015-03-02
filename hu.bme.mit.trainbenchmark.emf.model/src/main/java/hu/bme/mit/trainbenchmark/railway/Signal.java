/**
 */
package hu.bme.mit.trainbenchmark.railway;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Signal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.Signal#getSignal_currentState <em>Signal current State</em>}</li>
 * </ul>
 * </p>
 *
 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getSignal()
 * @model
 * @generated
 */
public interface Signal extends RailwayElement {
	/**
	 * Returns the value of the '<em><b>Signal current State</b></em>' attribute.
	 * The literals are from the enumeration {@link hu.bme.mit.trainbenchmark.railway.SignalState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signal current State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signal current State</em>' attribute.
	 * @see hu.bme.mit.trainbenchmark.railway.SignalState
	 * @see #setSignal_currentState(SignalState)
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getSignal_Signal_currentState()
	 * @model required="true"
	 * @generated
	 */
	SignalState getSignal_currentState();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.railway.Signal#getSignal_currentState <em>Signal current State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signal current State</em>' attribute.
	 * @see hu.bme.mit.trainbenchmark.railway.SignalState
	 * @see #getSignal_currentState()
	 * @generated
	 */
	void setSignal_currentState(SignalState value);

} // Signal
