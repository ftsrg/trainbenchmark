/**
 */
package hu.bme.mit.trainbenchmark.railway.impl;

import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Signal;
import hu.bme.mit.trainbenchmark.railway.SignalStateKind;

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
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.SignalImpl#getSignal_actualState <em>Signal actual State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SignalImpl extends ThingImpl implements Signal {
	/**
	 * The default value of the '{@link #getSignal_actualState() <em>Signal actual State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignal_actualState()
	 * @generated
	 * @ordered
	 */
	protected static final SignalStateKind SIGNAL_ACTUAL_STATE_EDEFAULT = SignalStateKind.SIGNAL_STATE_KIND_STOP;

	/**
	 * The cached value of the '{@link #getSignal_actualState() <em>Signal actual State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignal_actualState()
	 * @generated
	 * @ordered
	 */
	protected SignalStateKind signal_actualState = SIGNAL_ACTUAL_STATE_EDEFAULT;

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
	public SignalStateKind getSignal_actualState() {
		return signal_actualState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSignal_actualState(SignalStateKind newSignal_actualState) {
		SignalStateKind oldSignal_actualState = signal_actualState;
		signal_actualState = newSignal_actualState == null ? SIGNAL_ACTUAL_STATE_EDEFAULT : newSignal_actualState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RailwayPackage.SIGNAL__SIGNAL_ACTUAL_STATE, oldSignal_actualState, signal_actualState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RailwayPackage.SIGNAL__SIGNAL_ACTUAL_STATE:
				return getSignal_actualState();
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
			case RailwayPackage.SIGNAL__SIGNAL_ACTUAL_STATE:
				setSignal_actualState((SignalStateKind)newValue);
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
			case RailwayPackage.SIGNAL__SIGNAL_ACTUAL_STATE:
				setSignal_actualState(SIGNAL_ACTUAL_STATE_EDEFAULT);
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
			case RailwayPackage.SIGNAL__SIGNAL_ACTUAL_STATE:
				return signal_actualState != SIGNAL_ACTUAL_STATE_EDEFAULT;
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
		result.append(" (Signal_actualState: ");
		result.append(signal_actualState);
		result.append(')');
		return result.toString();
	}

} //SignalImpl
