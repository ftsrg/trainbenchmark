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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Sensor</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link Concept.Sensor#getSensor_trackElement <em>Sensor track Element
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see Concept.ConceptPackage#getSensor()
 * @model
 * @generated
 */
public interface Sensor extends Thing {
	/**
	 * Returns the value of the '<em><b>Sensor track Element</b></em>' reference
	 * list. The list contents are of type {@link Concept.Trackelement}. It is
	 * bidirectional and its opposite is '
	 * {@link Concept.Trackelement#getTrackElement_sensor
	 * <em>Track Element sensor</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sensor track Element</em>' reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Sensor track Element</em>' reference list.
	 * @see Concept.ConceptPackage#getSensor_Sensor_trackElement()
	 * @see Concept.Trackelement#getTrackElement_sensor
	 * @model opposite="TrackElement_sensor"
	 * @generated
	 */
	EList<Trackelement> getSensor_trackElement();

} // Sensor
