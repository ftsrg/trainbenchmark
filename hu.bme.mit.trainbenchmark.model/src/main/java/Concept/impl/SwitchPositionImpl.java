/**
 */
package Concept.impl;

import Concept.ConceptPackage;
import Concept.Route;
import Concept.Switch;
import Concept.SwitchPosition;
import Concept.SwitchStateKind;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Switch Position</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Concept.impl.SwitchPositionImpl#getSwitchPosition_switch <em>Switch Position switch</em>}</li>
 *   <li>{@link Concept.impl.SwitchPositionImpl#getSwitchPosition_switchState <em>Switch Position switch State</em>}</li>
 *   <li>{@link Concept.impl.SwitchPositionImpl#getSwitchPosition_route <em>Switch Position route</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SwitchPositionImpl extends ThingImpl implements SwitchPosition {
	/**
	 * The cached value of the '{@link #getSwitchPosition_switch() <em>Switch Position switch</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitchPosition_switch()
	 * @generated
	 * @ordered
	 */
	protected Switch switchPosition_switch;

	/**
	 * The default value of the '{@link #getSwitchPosition_switchState() <em>Switch Position switch State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitchPosition_switchState()
	 * @generated
	 * @ordered
	 */
	protected static final SwitchStateKind SWITCH_POSITION_SWITCH_STATE_EDEFAULT = SwitchStateKind.POINT_STATE_KIND_FAILURE;

	/**
	 * The cached value of the '{@link #getSwitchPosition_switchState() <em>Switch Position switch State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitchPosition_switchState()
	 * @generated
	 * @ordered
	 */
	protected SwitchStateKind switchPosition_switchState = SWITCH_POSITION_SWITCH_STATE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSwitchPosition_route() <em>Switch Position route</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitchPosition_route()
	 * @generated
	 * @ordered
	 */
	protected Route switchPosition_route;

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
		return ConceptPackage.Literals.SWITCH_POSITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Switch getSwitchPosition_switch() {
		if (switchPosition_switch != null && switchPosition_switch.eIsProxy()) {
			InternalEObject oldSwitchPosition_switch = (InternalEObject)switchPosition_switch;
			switchPosition_switch = (Switch)eResolveProxy(oldSwitchPosition_switch);
			if (switchPosition_switch != oldSwitchPosition_switch) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_SWITCH, oldSwitchPosition_switch, switchPosition_switch));
			}
		}
		return switchPosition_switch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Switch basicGetSwitchPosition_switch() {
		return switchPosition_switch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSwitchPosition_switch(Switch newSwitchPosition_switch, NotificationChain msgs) {
		Switch oldSwitchPosition_switch = switchPosition_switch;
		switchPosition_switch = newSwitchPosition_switch;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_SWITCH, oldSwitchPosition_switch, newSwitchPosition_switch);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwitchPosition_switch(Switch newSwitchPosition_switch) {
		if (newSwitchPosition_switch != switchPosition_switch) {
			NotificationChain msgs = null;
			if (switchPosition_switch != null)
				msgs = ((InternalEObject)switchPosition_switch).eInverseRemove(this, ConceptPackage.SWITCH__SWITCH_SWITCH_POSITION, Switch.class, msgs);
			if (newSwitchPosition_switch != null)
				msgs = ((InternalEObject)newSwitchPosition_switch).eInverseAdd(this, ConceptPackage.SWITCH__SWITCH_SWITCH_POSITION, Switch.class, msgs);
			msgs = basicSetSwitchPosition_switch(newSwitchPosition_switch, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_SWITCH, newSwitchPosition_switch, newSwitchPosition_switch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwitchStateKind getSwitchPosition_switchState() {
		return switchPosition_switchState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwitchPosition_switchState(SwitchStateKind newSwitchPosition_switchState) {
		SwitchStateKind oldSwitchPosition_switchState = switchPosition_switchState;
		switchPosition_switchState = newSwitchPosition_switchState == null ? SWITCH_POSITION_SWITCH_STATE_EDEFAULT : newSwitchPosition_switchState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_SWITCH_STATE, oldSwitchPosition_switchState, switchPosition_switchState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Route getSwitchPosition_route() {
		if (switchPosition_route != null && switchPosition_route.eIsProxy()) {
			InternalEObject oldSwitchPosition_route = (InternalEObject)switchPosition_route;
			switchPosition_route = (Route)eResolveProxy(oldSwitchPosition_route);
			if (switchPosition_route != oldSwitchPosition_route) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_ROUTE, oldSwitchPosition_route, switchPosition_route));
			}
		}
		return switchPosition_route;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Route basicGetSwitchPosition_route() {
		return switchPosition_route;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSwitchPosition_route(Route newSwitchPosition_route, NotificationChain msgs) {
		Route oldSwitchPosition_route = switchPosition_route;
		switchPosition_route = newSwitchPosition_route;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_ROUTE, oldSwitchPosition_route, newSwitchPosition_route);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwitchPosition_route(Route newSwitchPosition_route) {
		if (newSwitchPosition_route != switchPosition_route) {
			NotificationChain msgs = null;
			if (switchPosition_route != null)
				msgs = ((InternalEObject)switchPosition_route).eInverseRemove(this, ConceptPackage.ROUTE__ROUTE_SWITCH_POSITION, Route.class, msgs);
			if (newSwitchPosition_route != null)
				msgs = ((InternalEObject)newSwitchPosition_route).eInverseAdd(this, ConceptPackage.ROUTE__ROUTE_SWITCH_POSITION, Route.class, msgs);
			msgs = basicSetSwitchPosition_route(newSwitchPosition_route, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_ROUTE, newSwitchPosition_route, newSwitchPosition_route));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_SWITCH:
				if (switchPosition_switch != null)
					msgs = ((InternalEObject)switchPosition_switch).eInverseRemove(this, ConceptPackage.SWITCH__SWITCH_SWITCH_POSITION, Switch.class, msgs);
				return basicSetSwitchPosition_switch((Switch)otherEnd, msgs);
			case ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_ROUTE:
				if (switchPosition_route != null)
					msgs = ((InternalEObject)switchPosition_route).eInverseRemove(this, ConceptPackage.ROUTE__ROUTE_SWITCH_POSITION, Route.class, msgs);
				return basicSetSwitchPosition_route((Route)otherEnd, msgs);
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
			case ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_SWITCH:
				return basicSetSwitchPosition_switch(null, msgs);
			case ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_ROUTE:
				return basicSetSwitchPosition_route(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_SWITCH:
				if (resolve) return getSwitchPosition_switch();
				return basicGetSwitchPosition_switch();
			case ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_SWITCH_STATE:
				return getSwitchPosition_switchState();
			case ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_ROUTE:
				if (resolve) return getSwitchPosition_route();
				return basicGetSwitchPosition_route();
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
			case ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_SWITCH:
				setSwitchPosition_switch((Switch)newValue);
				return;
			case ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_SWITCH_STATE:
				setSwitchPosition_switchState((SwitchStateKind)newValue);
				return;
			case ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_ROUTE:
				setSwitchPosition_route((Route)newValue);
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
			case ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_SWITCH:
				setSwitchPosition_switch((Switch)null);
				return;
			case ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_SWITCH_STATE:
				setSwitchPosition_switchState(SWITCH_POSITION_SWITCH_STATE_EDEFAULT);
				return;
			case ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_ROUTE:
				setSwitchPosition_route((Route)null);
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
			case ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_SWITCH:
				return switchPosition_switch != null;
			case ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_SWITCH_STATE:
				return switchPosition_switchState != SWITCH_POSITION_SWITCH_STATE_EDEFAULT;
			case ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_ROUTE:
				return switchPosition_route != null;
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
		result.append(" (SwitchPosition_switchState: ");
		result.append(switchPosition_switchState);
		result.append(')');
		return result.toString();
	}

} //SwitchPositionImpl
