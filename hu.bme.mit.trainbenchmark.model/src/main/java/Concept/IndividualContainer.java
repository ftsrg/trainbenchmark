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
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Individual Container</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link Concept.IndividualContainer#getContains <em>Contains</em>}</li>
 * </ul>
 * </p>
 * 
 * @see Concept.ConceptPackage#getIndividualContainer()
 * @model
 * @generated
 */
public interface IndividualContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Contains</b></em>' containment reference
	 * list. The list contents are of type {@link Concept.Thing}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contains</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Contains</em>' containment reference list.
	 * @see Concept.ConceptPackage#getIndividualContainer_Contains()
	 * @model containment="true"
	 * @generated
	 */
	EList<Thing> getContains();

} // IndividualContainer
