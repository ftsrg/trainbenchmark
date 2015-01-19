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
package Concept.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import Concept.ConceptPackage;
import Concept.Sensor;
import Concept.Trackelement;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Trackelement</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link Concept.impl.TrackelementImpl#getTrackElement_sensor <em>Track
 * Element sensor</em>}</li>
 * <li>{@link Concept.impl.TrackelementImpl#getTrackElement_connectsTo <em>Track
 * Element connects To</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class TrackelementImpl extends ThingImpl implements Trackelement {
	/**
	 * The cached value of the '{@link #getTrackElement_sensor()
	 * <em>Track Element sensor</em>}' reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getTrackElement_sensor()
	 * @generated
	 * @ordered
	 */
	protected EList<Sensor> trackElement_sensor;

	/**
	 * The cached value of the '{@link #getTrackElement_connectsTo()
	 * <em>Track Element connects To</em>}' reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getTrackElement_connectsTo()
	 * @generated
	 * @ordered
	 */
	protected EList<Trackelement> trackElement_connectsTo;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected TrackelementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConceptPackage.Literals.TRACKELEMENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Sensor> getTrackElement_sensor() {
		if (trackElement_sensor == null) {
			trackElement_sensor = new EObjectWithInverseResolvingEList.ManyInverse<Sensor>(Sensor.class, this,
					ConceptPackage.TRACKELEMENT__TRACK_ELEMENT_SENSOR, ConceptPackage.SENSOR__SENSOR_TRACK_ELEMENT);
		}
		return trackElement_sensor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Trackelement> getTrackElement_connectsTo() {
		if (trackElement_connectsTo == null) {
			trackElement_connectsTo = new EObjectResolvingEList<Trackelement>(Trackelement.class, this,
					ConceptPackage.TRACKELEMENT__TRACK_ELEMENT_CONNECTS_TO);
		}
		return trackElement_connectsTo;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ConceptPackage.TRACKELEMENT__TRACK_ELEMENT_SENSOR:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getTrackElement_sensor()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ConceptPackage.TRACKELEMENT__TRACK_ELEMENT_SENSOR:
			return ((InternalEList<?>) getTrackElement_sensor()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ConceptPackage.TRACKELEMENT__TRACK_ELEMENT_SENSOR:
			return getTrackElement_sensor();
		case ConceptPackage.TRACKELEMENT__TRACK_ELEMENT_CONNECTS_TO:
			return getTrackElement_connectsTo();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ConceptPackage.TRACKELEMENT__TRACK_ELEMENT_SENSOR:
			getTrackElement_sensor().clear();
			getTrackElement_sensor().addAll((Collection<? extends Sensor>) newValue);
			return;
		case ConceptPackage.TRACKELEMENT__TRACK_ELEMENT_CONNECTS_TO:
			getTrackElement_connectsTo().clear();
			getTrackElement_connectsTo().addAll((Collection<? extends Trackelement>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ConceptPackage.TRACKELEMENT__TRACK_ELEMENT_SENSOR:
			getTrackElement_sensor().clear();
			return;
		case ConceptPackage.TRACKELEMENT__TRACK_ELEMENT_CONNECTS_TO:
			getTrackElement_connectsTo().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ConceptPackage.TRACKELEMENT__TRACK_ELEMENT_SENSOR:
			return trackElement_sensor != null && !trackElement_sensor.isEmpty();
		case ConceptPackage.TRACKELEMENT__TRACK_ELEMENT_CONNECTS_TO:
			return trackElement_connectsTo != null && !trackElement_connectsTo.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // TrackelementImpl
