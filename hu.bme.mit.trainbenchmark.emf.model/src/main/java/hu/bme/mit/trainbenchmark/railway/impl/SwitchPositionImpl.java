/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
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
package hu.bme.mit.trainbenchmark.railway.impl;

import hu.bme.mit.trainbenchmark.railway.Position;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Switch Position</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.SwitchPositionImpl#getSwitch <em>Switch</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.SwitchPositionImpl#getPosition <em>Position</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.SwitchPositionImpl#getRoute <em>Route</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SwitchPositionImpl extends RailwayElementImpl implements SwitchPosition {
	/**
	 * The cached value of the '{@link #getSwitch() <em>Switch</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitch()
	 * @generated
	 * @ordered
	 */
	protected Switch switch_;

	/**
	 * The default value of the '{@link #getPosition() <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected static final Position POSITION_EDEFAULT = Position.FAILURE;

	/**
	 * The cached value of the '{@link #getPosition() <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected Position position = POSITION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SwitchPositionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RailwayPackage.Literals.SWITCH_POSITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Switch getSwitch() {
		if (switch_ != null && switch_.eIsProxy()) {
			InternalEObject oldSwitch = (InternalEObject)switch_;
			switch_ = (Switch)eResolveProxy(oldSwitch);
			if (switch_ != oldSwitch) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RailwayPackage.SWITCH_POSITION__SWITCH, oldSwitch, switch_));
			}
		}
		return switch_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Switch basicGetSwitch() {
		return switch_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSwitch(Switch newSwitch, NotificationChain msgs) {
		Switch oldSwitch = switch_;
		switch_ = newSwitch;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RailwayPackage.SWITCH_POSITION__SWITCH, oldSwitch, newSwitch);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwitch(Switch newSwitch) {
		if (newSwitch != switch_) {
			NotificationChain msgs = null;
			if (switch_ != null)
				msgs = ((InternalEObject)switch_).eInverseRemove(this, RailwayPackage.SWITCH__POSITIONS, Switch.class, msgs);
			if (newSwitch != null)
				msgs = ((InternalEObject)newSwitch).eInverseAdd(this, RailwayPackage.SWITCH__POSITIONS, Switch.class, msgs);
			msgs = basicSetSwitch(newSwitch, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RailwayPackage.SWITCH_POSITION__SWITCH, newSwitch, newSwitch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPosition(Position newPosition) {
		Position oldPosition = position;
		position = newPosition == null ? POSITION_EDEFAULT : newPosition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RailwayPackage.SWITCH_POSITION__POSITION, oldPosition, position));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Route getRoute() {
		if (eContainerFeatureID() != RailwayPackage.SWITCH_POSITION__ROUTE) return null;
		return (Route)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRoute(Route newRoute, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRoute, RailwayPackage.SWITCH_POSITION__ROUTE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoute(Route newRoute) {
		if (newRoute != eInternalContainer() || (eContainerFeatureID() != RailwayPackage.SWITCH_POSITION__ROUTE && newRoute != null)) {
			if (EcoreUtil.isAncestor(this, newRoute))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRoute != null)
				msgs = ((InternalEObject)newRoute).eInverseAdd(this, RailwayPackage.ROUTE__FOLLOWS, Route.class, msgs);
			msgs = basicSetRoute(newRoute, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RailwayPackage.SWITCH_POSITION__ROUTE, newRoute, newRoute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RailwayPackage.SWITCH_POSITION__SWITCH:
				if (switch_ != null)
					msgs = ((InternalEObject)switch_).eInverseRemove(this, RailwayPackage.SWITCH__POSITIONS, Switch.class, msgs);
				return basicSetSwitch((Switch)otherEnd, msgs);
			case RailwayPackage.SWITCH_POSITION__ROUTE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRoute((Route)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RailwayPackage.SWITCH_POSITION__SWITCH:
				return basicSetSwitch(null, msgs);
			case RailwayPackage.SWITCH_POSITION__ROUTE:
				return basicSetRoute(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case RailwayPackage.SWITCH_POSITION__ROUTE:
				return eInternalContainer().eInverseRemove(this, RailwayPackage.ROUTE__FOLLOWS, Route.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RailwayPackage.SWITCH_POSITION__SWITCH:
				if (resolve) return getSwitch();
				return basicGetSwitch();
			case RailwayPackage.SWITCH_POSITION__POSITION:
				return getPosition();
			case RailwayPackage.SWITCH_POSITION__ROUTE:
				return getRoute();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RailwayPackage.SWITCH_POSITION__SWITCH:
				setSwitch((Switch)newValue);
				return;
			case RailwayPackage.SWITCH_POSITION__POSITION:
				setPosition((Position)newValue);
				return;
			case RailwayPackage.SWITCH_POSITION__ROUTE:
				setRoute((Route)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RailwayPackage.SWITCH_POSITION__SWITCH:
				setSwitch((Switch)null);
				return;
			case RailwayPackage.SWITCH_POSITION__POSITION:
				setPosition(POSITION_EDEFAULT);
				return;
			case RailwayPackage.SWITCH_POSITION__ROUTE:
				setRoute((Route)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RailwayPackage.SWITCH_POSITION__SWITCH:
				return switch_ != null;
			case RailwayPackage.SWITCH_POSITION__POSITION:
				return position != POSITION_EDEFAULT;
			case RailwayPackage.SWITCH_POSITION__ROUTE:
				return getRoute() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (position: ");
		result.append(position);
		result.append(')');
		return result.toString();
	}

} //SwitchPositionImpl
