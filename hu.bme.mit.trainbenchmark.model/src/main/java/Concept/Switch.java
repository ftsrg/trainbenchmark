/**
 */
package Concept;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Switch</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link Concept.Switch#getSwitch_actualState <em>Switch actual State</em>}</li>
 *   <li>{@link Concept.Switch#getSwitch_switchPosition <em>Switch switch Position</em>}</li>
 * </ul>
 * </p>
 *
 * @see Concept.ConceptPackage#getSwitch()
 * @model
 * @generated
 */
public interface Switch extends TrackElement {
	/**
	 * Returns the value of the '<em><b>Switch actual State</b></em>' attribute.
	 * The literals are from the enumeration {@link Concept.SwitchStateKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Switch actual State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Switch actual State</em>' attribute.
	 * @see Concept.SwitchStateKind
	 * @see #setSwitch_actualState(SwitchStateKind)
	 * @see Concept.ConceptPackage#getSwitch_Switch_actualState()
	 * @model required="true"
	 * @generated
	 */
	SwitchStateKind getSwitch_actualState();

	/**
	 * Sets the value of the '{@link Concept.Switch#getSwitch_actualState <em>Switch actual State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Switch actual State</em>' attribute.
	 * @see Concept.SwitchStateKind
	 * @see #getSwitch_actualState()
	 * @generated
	 */
	void setSwitch_actualState(SwitchStateKind value);

	/**
	 * Returns the value of the '<em><b>Switch switch Position</b></em>' reference list.
	 * The list contents are of type {@link Concept.SwitchPosition}.
	 * It is bidirectional and its opposite is '{@link Concept.SwitchPosition#getSwitchPosition_switch <em>Switch Position switch</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Switch switch Position</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Switch switch Position</em>' reference list.
	 * @see Concept.ConceptPackage#getSwitch_Switch_switchPosition()
	 * @see Concept.SwitchPosition#getSwitchPosition_switch
	 * @model opposite="SwitchPosition_switch"
	 * @generated
	 */
	EList<SwitchPosition> getSwitch_switchPosition();

} // Switch
