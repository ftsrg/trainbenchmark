/**
 */
package hu.bme.mit.trainbenchmark.railway.impl;

import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Track Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.TrackElementImpl#getSensor <em>Sensor</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.TrackElementImpl#getConnectsTo <em>Connects To</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TrackElementImpl extends RailwayElementImpl implements TrackElement {
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
	public Sensor getSensor() {
		if (eContainerFeatureID() != RailwayPackage.TRACK_ELEMENT__SENSOR) return null;
		return (Sensor)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSensor(Sensor newSensor, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newSensor, RailwayPackage.TRACK_ELEMENT__SENSOR, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSensor(Sensor newSensor) {
		if (newSensor != eInternalContainer() || (eContainerFeatureID() != RailwayPackage.TRACK_ELEMENT__SENSOR && newSensor != null)) {
			if (EcoreUtil.isAncestor(this, newSensor))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSensor != null)
				msgs = ((InternalEObject)newSensor).eInverseAdd(this, RailwayPackage.SENSOR__ELEMENTS, Sensor.class, msgs);
			msgs = basicSetSensor(newSensor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RailwayPackage.TRACK_ELEMENT__SENSOR, newSensor, newSensor));
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
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RailwayPackage.TRACK_ELEMENT__SENSOR:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSensor((Sensor)otherEnd, msgs);
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
			case RailwayPackage.TRACK_ELEMENT__SENSOR:
				return basicSetSensor(null, msgs);
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
			case RailwayPackage.TRACK_ELEMENT__SENSOR:
				return eInternalContainer().eInverseRemove(this, RailwayPackage.SENSOR__ELEMENTS, Sensor.class, msgs);
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
			case RailwayPackage.TRACK_ELEMENT__SENSOR:
				return getSensor();
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
			case RailwayPackage.TRACK_ELEMENT__SENSOR:
				setSensor((Sensor)newValue);
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
			case RailwayPackage.TRACK_ELEMENT__SENSOR:
				setSensor((Sensor)null);
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
			case RailwayPackage.TRACK_ELEMENT__SENSOR:
				return getSensor() != null;
			case RailwayPackage.TRACK_ELEMENT__CONNECTS_TO:
				return connectsTo != null && !connectsTo.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TrackElementImpl
