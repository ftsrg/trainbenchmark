/**
 */
package hu.bme.mit.trainbenchmark.schedule.impl;

import hu.bme.mit.trainbenchmark.schedule.SchedulePackage;
import hu.bme.mit.trainbenchmark.schedule.Station;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Station</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.StationImpl#getNeighbors <em>Neighbors</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.StationImpl#getCode <em>Code</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.StationImpl#getStanox <em>Stanox</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.StationImpl#getNalco <em>Nalco</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StationImpl extends MinimalEObjectImpl.Container implements Station {
	/**
	 * The cached value of the '{@link #getNeighbors() <em>Neighbors</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNeighbors()
	 * @generated
	 * @ordered
	 */
	protected EList<Station> neighbors;

	/**
	 * The default value of the '{@link #getCode() <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected static final String CODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCode() <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected String code = CODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStanox() <em>Stanox</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStanox()
	 * @generated
	 * @ordered
	 */
	protected static final String STANOX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStanox() <em>Stanox</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStanox()
	 * @generated
	 * @ordered
	 */
	protected String stanox = STANOX_EDEFAULT;

	/**
	 * The default value of the '{@link #getNalco() <em>Nalco</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNalco()
	 * @generated
	 * @ordered
	 */
	protected static final String NALCO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNalco() <em>Nalco</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNalco()
	 * @generated
	 * @ordered
	 */
	protected String nalco = NALCO_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SchedulePackage.Literals.STATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Station> getNeighbors() {
		if (neighbors == null) {
			neighbors = new EObjectResolvingEList<Station>(Station.class, this, SchedulePackage.STATION__NEIGHBORS);
		}
		return neighbors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCode() {
		return code;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCode(String newCode) {
		String oldCode = code;
		code = newCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.STATION__CODE, oldCode, code));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStanox() {
		return stanox;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStanox(String newStanox) {
		String oldStanox = stanox;
		stanox = newStanox;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.STATION__STANOX, oldStanox, stanox));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNalco() {
		return nalco;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNalco(String newNalco) {
		String oldNalco = nalco;
		nalco = newNalco;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.STATION__NALCO, oldNalco, nalco));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SchedulePackage.STATION__NEIGHBORS:
				return getNeighbors();
			case SchedulePackage.STATION__CODE:
				return getCode();
			case SchedulePackage.STATION__STANOX:
				return getStanox();
			case SchedulePackage.STATION__NALCO:
				return getNalco();
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
			case SchedulePackage.STATION__NEIGHBORS:
				getNeighbors().clear();
				getNeighbors().addAll((Collection<? extends Station>)newValue);
				return;
			case SchedulePackage.STATION__CODE:
				setCode((String)newValue);
				return;
			case SchedulePackage.STATION__STANOX:
				setStanox((String)newValue);
				return;
			case SchedulePackage.STATION__NALCO:
				setNalco((String)newValue);
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
			case SchedulePackage.STATION__NEIGHBORS:
				getNeighbors().clear();
				return;
			case SchedulePackage.STATION__CODE:
				setCode(CODE_EDEFAULT);
				return;
			case SchedulePackage.STATION__STANOX:
				setStanox(STANOX_EDEFAULT);
				return;
			case SchedulePackage.STATION__NALCO:
				setNalco(NALCO_EDEFAULT);
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
			case SchedulePackage.STATION__NEIGHBORS:
				return neighbors != null && !neighbors.isEmpty();
			case SchedulePackage.STATION__CODE:
				return CODE_EDEFAULT == null ? code != null : !CODE_EDEFAULT.equals(code);
			case SchedulePackage.STATION__STANOX:
				return STANOX_EDEFAULT == null ? stanox != null : !STANOX_EDEFAULT.equals(stanox);
			case SchedulePackage.STATION__NALCO:
				return NALCO_EDEFAULT == null ? nalco != null : !NALCO_EDEFAULT.equals(nalco);
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
		result.append(" (code: ");
		result.append(code);
		result.append(", stanox: ");
		result.append(stanox);
		result.append(", nalco: ");
		result.append(nalco);
		result.append(')');
		return result.toString();
	}

} //StationImpl
