/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
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
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.Switch#getCurrentPosition <em>Current Position</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.Switch#getPositions <em>Positions</em>}</li>
 * </ul>
 * </p>
 *
 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getSwitch()
 * @model
 * @generated
 */
public interface Switch extends TrackElement {
	/**
	 * Returns the value of the '<em><b>Current Position</b></em>' attribute.
	 * The literals are from the enumeration {@link hu.bme.mit.trainbenchmark.railway.Position}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Current Position</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Current Position</em>' attribute.
	 * @see hu.bme.mit.trainbenchmark.railway.Position
	 * @see #setCurrentPosition(Position)
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getSwitch_CurrentPosition()
	 * @model required="true"
	 * @generated
	 */
	Position getCurrentPosition();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.railway.Switch#getCurrentPosition <em>Current Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Current Position</em>' attribute.
	 * @see hu.bme.mit.trainbenchmark.railway.Position
	 * @see #getCurrentPosition()
	 * @generated
	 */
	void setCurrentPosition(Position value);

	/**
	 * Returns the value of the '<em><b>Positions</b></em>' reference list.
	 * The list contents are of type {@link hu.bme.mit.trainbenchmark.railway.SwitchPosition}.
	 * It is bidirectional and its opposite is '{@link hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitch <em>Switch</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Positions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Positions</em>' reference list.
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getSwitch_Positions()
	 * @see hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitch
	 * @model opposite="switch"
	 * @generated
	 */
	EList<SwitchPosition> getPositions();

} // Switch
