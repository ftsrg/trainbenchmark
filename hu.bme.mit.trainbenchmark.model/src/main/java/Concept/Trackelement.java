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
 * <em><b>Trackelement</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link Concept.Trackelement#getTrackElement_sensor <em>Track Element
 * sensor</em>}</li>
 * <li>{@link Concept.Trackelement#getTrackElement_connectsTo <em>Track Element
 * connects To</em>}</li>
 * </ul>
 * </p>
 * 
 * @see Concept.ConceptPackage#getTrackelement()
 * @model
 * @generated
 */
public interface Trackelement extends Thing {
	/**
	 * Returns the value of the '<em><b>Track Element sensor</b></em>' reference
	 * list. The list contents are of type {@link Concept.Sensor}. It is
	 * bidirectional and its opposite is '
	 * {@link Concept.Sensor#getSensor_trackElement
	 * <em>Sensor track Element</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Track Element sensor</em>' reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Track Element sensor</em>' reference list.
	 * @see Concept.ConceptPackage#getTrackelement_TrackElement_sensor()
	 * @see Concept.Sensor#getSensor_trackElement
	 * @model opposite="Sensor_trackElement"
	 * @generated
	 */
	EList<Sensor> getTrackElement_sensor();

	/**
	 * Returns the value of the '<em><b>Track Element connects To</b></em>'
	 * reference list. The list contents are of type
	 * {@link Concept.Trackelement}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Track Element connects To</em>' reference
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Track Element connects To</em>' reference
	 *         list.
	 * @see Concept.ConceptPackage#getTrackelement_TrackElement_connectsTo()
	 * @model
	 * @generated
	 */
	EList<Trackelement> getTrackElement_connectsTo();

} // Trackelement
