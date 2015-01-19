/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package Concept;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Switch Position</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link Concept.SwitchPosition#getSwitchPosition_switch <em>Switch
 * Position switch</em>}</li>
 * <li>{@link Concept.SwitchPosition#getSwitchPosition_switchState <em>Switch
 * Position switch State</em>}</li>
 * <li>{@link Concept.SwitchPosition#getSwitchPosition_route <em>Switch Position
 * route</em>}</li>
 * </ul>
 * </p>
 * 
 * @see Concept.ConceptPackage#getSwitchPosition()
 * @model
 * @generated
 */
public interface SwitchPosition extends Thing {
	/**
	 * Returns the value of the '<em><b>Switch Position switch</b></em>'
	 * reference. It is bidirectional and its opposite is '
	 * {@link Concept.Switch#getSwitch_switchPosition
	 * <em>Switch switch Position</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Switch Position switch</em>' reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Switch Position switch</em>' reference.
	 * @see #setSwitchPosition_switch(Switch)
	 * @see Concept.ConceptPackage#getSwitchPosition_SwitchPosition_switch()
	 * @see Concept.Switch#getSwitch_switchPosition
	 * @model opposite="Switch_switchPosition" required="true"
	 * @generated
	 */
	Switch getSwitchPosition_switch();

	/**
	 * Sets the value of the '
	 * {@link Concept.SwitchPosition#getSwitchPosition_switch
	 * <em>Switch Position switch</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Switch Position switch</em>'
	 *            reference.
	 * @see #getSwitchPosition_switch()
	 * @generated
	 */
	void setSwitchPosition_switch(Switch value);

	/**
	 * Returns the value of the '<em><b>Switch Position switch State</b></em>'
	 * attribute. The literals are from the enumeration
	 * {@link Concept.SwitchStateKind}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Switch Position switch State</em>' attribute
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Switch Position switch State</em>'
	 *         attribute.
	 * @see Concept.SwitchStateKind
	 * @see #setSwitchPosition_switchState(SwitchStateKind)
	 * @see Concept.ConceptPackage#getSwitchPosition_SwitchPosition_switchState()
	 * @model required="true"
	 * @generated
	 */
	SwitchStateKind getSwitchPosition_switchState();

	/**
	 * Sets the value of the '
	 * {@link Concept.SwitchPosition#getSwitchPosition_switchState
	 * <em>Switch Position switch State</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Switch Position switch State</em>'
	 *            attribute.
	 * @see Concept.SwitchStateKind
	 * @see #getSwitchPosition_switchState()
	 * @generated
	 */
	void setSwitchPosition_switchState(SwitchStateKind value);

	/**
	 * Returns the value of the '<em><b>Switch Position route</b></em>'
	 * reference. It is bidirectional and its opposite is '
	 * {@link Concept.Route#getRoute_switchPosition
	 * <em>Route switch Position</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Switch Position route</em>' reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Switch Position route</em>' reference.
	 * @see #setSwitchPosition_route(Route)
	 * @see Concept.ConceptPackage#getSwitchPosition_SwitchPosition_route()
	 * @see Concept.Route#getRoute_switchPosition
	 * @model opposite="Route_switchPosition" required="true"
	 * @generated
	 */
	Route getSwitchPosition_route();

	/**
	 * Sets the value of the '
	 * {@link Concept.SwitchPosition#getSwitchPosition_route
	 * <em>Switch Position route</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Switch Position route</em>'
	 *            reference.
	 * @see #getSwitchPosition_route()
	 * @generated
	 */
	void setSwitchPosition_route(Route value);

} // SwitchPosition
