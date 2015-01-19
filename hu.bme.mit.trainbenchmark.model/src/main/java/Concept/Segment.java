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
 * <em><b>Segment</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link Concept.Segment#getSegment_length <em>Segment length</em>}</li>
 * </ul>
 * </p>
 * 
 * @see Concept.ConceptPackage#getSegment()
 * @model
 * @generated
 */
public interface Segment extends Trackelement {
	/**
	 * Returns the value of the '<em><b>Segment length</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Segment length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Segment length</em>' attribute.
	 * @see #setSegment_length(int)
	 * @see Concept.ConceptPackage#getSegment_Segment_length()
	 * @model required="true"
	 * @generated
	 */
	int getSegment_length();

	/**
	 * Sets the value of the '{@link Concept.Segment#getSegment_length
	 * <em>Segment length</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Segment length</em>' attribute.
	 * @see #getSegment_length()
	 * @generated
	 */
	void setSegment_length(int value);

} // Segment
