/**
 */
package hu.bme.mit.trainbenchmark.railway.impl;

import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;
import hu.bme.mit.trainbenchmark.railway.SwitchStateKind;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Switch</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.SwitchImpl#getSwitch_actualState <em>Switch actual State</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.SwitchImpl#getSwitch_switchPosition <em>Switch switch Position</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SwitchImpl extends TrackElementImpl implements Switch {
	/**
	 * The default value of the '{@link #getSwitch_actualState() <em>Switch actual State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitch_actualState()
	 * @generated
	 * @ordered
	 */
	protected static final SwitchStateKind SWITCH_ACTUAL_STATE_EDEFAULT = SwitchStateKind.POINT_STATE_KIND_FAILURE;

	/**
	 * The cached value of the '{@link #getSwitch_actualState() <em>Switch actual State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitch_actualState()
	 * @generated
	 * @ordered
	 */
	protected SwitchStateKind switch_actualState = SWITCH_ACTUAL_STATE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSwitch_switchPosition() <em>Switch switch Position</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitch_switchPosition()
	 * @generated
	 * @ordered
	 */
	protected EList<SwitchPosition> switch_switchPosition;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SwitchImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RailwayPackage.Literals.SWITCH;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwitchStateKind getSwitch_actualState() {
		return switch_actualState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwitch_actualState(SwitchStateKind newSwitch_actualState) {
		SwitchStateKind oldSwitch_actualState = switch_actualState;
		switch_actualState = newSwitch_actualState == null ? SWITCH_ACTUAL_STATE_EDEFAULT : newSwitch_actualState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RailwayPackage.SWITCH__SWITCH_ACTUAL_STATE, oldSwitch_actualState, switch_actualState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SwitchPosition> getSwitch_switchPosition() {
		if (switch_switchPosition == null) {
			switch_switchPosition = new EObjectWithInverseResolvingEList<SwitchPosition>(SwitchPosition.class, this, RailwayPackage.SWITCH__SWITCH_SWITCH_POSITION, RailwayPackage.SWITCH_POSITION__SWITCH_POSITION_SWITCH);
		}
		return switch_switchPosition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RailwayPackage.SWITCH__SWITCH_SWITCH_POSITION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSwitch_switchPosition()).basicAdd(otherEnd, msgs);
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
			case RailwayPackage.SWITCH__SWITCH_SWITCH_POSITION:
				return ((InternalEList<?>)getSwitch_switchPosition()).basicRemove(otherEnd, msgs);
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
			case RailwayPackage.SWITCH__SWITCH_ACTUAL_STATE:
				return getSwitch_actualState();
			case RailwayPackage.SWITCH__SWITCH_SWITCH_POSITION:
				return getSwitch_switchPosition();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RailwayPackage.SWITCH__SWITCH_ACTUAL_STATE:
				setSwitch_actualState((SwitchStateKind)newValue);
				return;
			case RailwayPackage.SWITCH__SWITCH_SWITCH_POSITION:
				getSwitch_switchPosition().clear();
				getSwitch_switchPosition().addAll((Collection<? extends SwitchPosition>)newValue);
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
			case RailwayPackage.SWITCH__SWITCH_ACTUAL_STATE:
				setSwitch_actualState(SWITCH_ACTUAL_STATE_EDEFAULT);
				return;
			case RailwayPackage.SWITCH__SWITCH_SWITCH_POSITION:
				getSwitch_switchPosition().clear();
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
			case RailwayPackage.SWITCH__SWITCH_ACTUAL_STATE:
				return switch_actualState != SWITCH_ACTUAL_STATE_EDEFAULT;
			case RailwayPackage.SWITCH__SWITCH_SWITCH_POSITION:
				return switch_switchPosition != null && !switch_switchPosition.isEmpty();
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
		result.append(" (Switch_actualState: ");
		result.append(switch_actualState);
		result.append(')');
		return result.toString();
	}

} //SwitchImpl
