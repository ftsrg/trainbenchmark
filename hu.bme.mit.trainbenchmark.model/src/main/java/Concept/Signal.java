/**
 */
package Concept;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Signal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link Concept.Signal#getSignal_actualState <em>Signal actual State</em>}</li>
 * </ul>
 * </p>
 *
 * @see Concept.ConceptPackage#getSignal()
 * @model
 * @generated
 */
public interface Signal extends Thing {
	/**
	 * Returns the value of the '<em><b>Signal actual State</b></em>' attribute.
	 * The literals are from the enumeration {@link Concept.SignalStateKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signal actual State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signal actual State</em>' attribute.
	 * @see Concept.SignalStateKind
	 * @see #setSignal_actualState(SignalStateKind)
	 * @see Concept.ConceptPackage#getSignal_Signal_actualState()
	 * @model required="true"
	 * @generated
	 */
	SignalStateKind getSignal_actualState();

	/**
	 * Sets the value of the '{@link Concept.Signal#getSignal_actualState <em>Signal actual State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signal actual State</em>' attribute.
	 * @see Concept.SignalStateKind
	 * @see #getSignal_actualState()
	 * @generated
	 */
	void setSignal_actualState(SignalStateKind value);

} // Signal
