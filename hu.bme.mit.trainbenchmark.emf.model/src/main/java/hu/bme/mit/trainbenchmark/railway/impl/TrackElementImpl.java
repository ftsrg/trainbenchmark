/**
 */
package hu.bme.mit.trainbenchmark.railway.impl;

import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Track Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.TrackElementImpl#getMonitoredBy <em>Monitored By</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.TrackElementImpl#getConnectsTo <em>Connects To</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TrackElementImpl extends RailwayElementImpl implements TrackElement {
	/**
	 * The cached value of the '{@link #getMonitoredBy() <em>Monitored By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonitoredBy()
	 * @generated
	 * @ordered
	 */
	protected EList<Sensor> monitoredBy;

	/**
	 * The cached value of the '{@link #getConnectsTo() <em>Connects To</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectsTo()
	 * @generated
	 * @ordered
	 */
	protected EList<TrackElement> connectsTo;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TrackElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RailwayPackage.Literals.TRACK_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Sensor> getMonitoredBy() {
		if (monitoredBy == null) {
			monitoredBy = new EObjectWithInverseResolvingEList.ManyInverse<Sensor>(Sensor.class, this, RailwayPackage.TRACK_ELEMENT__MONITORED_BY, RailwayPackage.SENSOR__MONITORS);
		}
		return monitoredBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TrackElement> getConnectsTo() {
		if (connectsTo == null) {
			connectsTo = new EObjectResolvingEList<TrackElement>(TrackElement.class, this, RailwayPackage.TRACK_ELEMENT__CONNECTS_TO);
		}
		return connectsTo;
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
			case RailwayPackage.TRACK_ELEMENT__MONITORED_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMonitoredBy()).basicAdd(otherEnd, msgs);
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
			case RailwayPackage.TRACK_ELEMENT__MONITORED_BY:
				return ((InternalEList<?>)getMonitoredBy()).basicRemove(otherEnd, msgs);
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
			case RailwayPackage.TRACK_ELEMENT__MONITORED_BY:
				return getMonitoredBy();
			case RailwayPackage.TRACK_ELEMENT__CONNECTS_TO:
				return getConnectsTo();
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
			case RailwayPackage.TRACK_ELEMENT__MONITORED_BY:
				getMonitoredBy().clear();
				getMonitoredBy().addAll((Collection<? extends Sensor>)newValue);
				return;
			case RailwayPackage.TRACK_ELEMENT__CONNECTS_TO:
				getConnectsTo().clear();
				getConnectsTo().addAll((Collection<? extends TrackElement>)newValue);
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
			case RailwayPackage.TRACK_ELEMENT__MONITORED_BY:
				getMonitoredBy().clear();
				return;
			case RailwayPackage.TRACK_ELEMENT__CONNECTS_TO:
				getConnectsTo().clear();
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
			case RailwayPackage.TRACK_ELEMENT__MONITORED_BY:
				return monitoredBy != null && !monitoredBy.isEmpty();
			case RailwayPackage.TRACK_ELEMENT__CONNECTS_TO:
				return connectsTo != null && !connectsTo.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TrackElementImpl
