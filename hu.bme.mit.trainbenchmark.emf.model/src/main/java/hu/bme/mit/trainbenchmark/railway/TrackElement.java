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
 * A representation of the model object '<em><b>Track Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.TrackElement#getSensor <em>Sensor</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.TrackElement#getConnectsTo <em>Connects To</em>}</li>
 * </ul>
 * </p>
 *
 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getTrackElement()
 * @model abstract="true"
 * @generated
 */
public interface TrackElement extends RailwayElement {
	/**
	 * Returns the value of the '<em><b>Sensor</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link hu.bme.mit.trainbenchmark.railway.Sensor#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sensor</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sensor</em>' container reference.
	 * @see #setSensor(Sensor)
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getTrackElement_Sensor()
	 * @see hu.bme.mit.trainbenchmark.railway.Sensor#getElements
	 * @model opposite="elements" transient="false"
	 * @generated
	 */
	Sensor getSensor();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.railway.TrackElement#getSensor <em>Sensor</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sensor</em>' container reference.
	 * @see #getSensor()
	 * @generated
	 */
	void setSensor(Sensor value);

	/**
	 * Returns the value of the '<em><b>Connects To</b></em>' reference list.
	 * The list contents are of type {@link hu.bme.mit.trainbenchmark.railway.TrackElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connects To</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connects To</em>' reference list.
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getTrackElement_ConnectsTo()
	 * @model
	 * @generated
	 */
	EList<TrackElement> getConnectsTo();

} // TrackElement
