/**
 */
package hu.bme.mit.trainbenchmark.railway.impl;

import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Signal;
import hu.bme.mit.trainbenchmark.railway.SignalState;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Signal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.SignalImpl#getSignal_currentState <em>Signal current State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SignalImpl extends RailwayElementImpl implements Signal {
	/**
	 * The default value of the '{@link #getSignal_currentState() <em>Signal current State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignal_currentState()
	 * @generated
	 * @ordered
	 */
	protected static final SignalState SIGNAL_CURRENT_STATE_EDEFAULT = SignalState.FAILURE;

	/**
	 * The cached value of the '{@link #getSignal_currentState() <em>Signal current State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignal_currentState()
	 * @generated
	 * @ordered
	 */
	protected SignalState signal_currentState = SIGNAL_CURRENT_STATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SignalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RailwayPackage.Literals.SIGNAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SignalState getSignal_currentState() {
		return signal_currentState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSignal_currentState(SignalState newSignal_currentState) {
		SignalState oldSignal_currentState = signal_currentState;
		signal_currentState = newSignal_currentState == null ? SIGNAL_CURRENT_STATE_EDEFAULT : newSignal_currentState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RailwayPackage.SIGNAL__SIGNAL_CURRENT_STATE, oldSignal_currentState, signal_currentState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RailwayPackage.SIGNAL__SIGNAL_CURRENT_STATE:
				return getSignal_currentState();
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
			case RailwayPackage.SIGNAL__SIGNAL_CURRENT_STATE:
				setSignal_currentState((SignalState)newValue);
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
			case RailwayPackage.SIGNAL__SIGNAL_CURRENT_STATE:
				setSignal_currentState(SIGNAL_CURRENT_STATE_EDEFAULT);
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
			case RailwayPackage.SIGNAL__SIGNAL_CURRENT_STATE:
				return signal_currentState != SIGNAL_CURRENT_STATE_EDEFAULT;
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
		result.append(" (Signal_currentState: ");
		result.append(signal_currentState);
		result.append(')');
		return result.toString();
	}

} //SignalImpl
