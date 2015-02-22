/**
 */
package Concept.impl;

import Concept.ConceptPackage;
import Concept.Sensor;
import Concept.TrackElement;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sensor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Concept.impl.SensorImpl#getSensor_trackElement <em>Sensor track Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SensorImpl extends ThingImpl implements Sensor {
	/**
	 * The cached value of the '{@link #getSensor_trackElement() <em>Sensor track Element</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSensor_trackElement()
	 * @generated
	 * @ordered
	 */
	protected EList<TrackElement> sensor_trackElement;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SensorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConceptPackage.Literals.SENSOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TrackElement> getSensor_trackElement() {
		if (sensor_trackElement == null) {
			sensor_trackElement = new EObjectWithInverseResolvingEList.ManyInverse<TrackElement>(TrackElement.class, this, ConceptPackage.SENSOR__SENSOR_TRACK_ELEMENT, ConceptPackage.TRACK_ELEMENT__TRACK_ELEMENT_SENSOR);
		}
		return sensor_trackElement;
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
			case ConceptPackage.SENSOR__SENSOR_TRACK_ELEMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSensor_trackElement()).basicAdd(otherEnd, msgs);
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
			case ConceptPackage.SENSOR__SENSOR_TRACK_ELEMENT:
				return ((InternalEList<?>)getSensor_trackElement()).basicRemove(otherEnd, msgs);
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
			case ConceptPackage.SENSOR__SENSOR_TRACK_ELEMENT:
				return getSensor_trackElement();
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
			case ConceptPackage.SENSOR__SENSOR_TRACK_ELEMENT:
				getSensor_trackElement().clear();
				getSensor_trackElement().addAll((Collection<? extends TrackElement>)newValue);
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
			case ConceptPackage.SENSOR__SENSOR_TRACK_ELEMENT:
				getSensor_trackElement().clear();
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
			case ConceptPackage.SENSOR__SENSOR_TRACK_ELEMENT:
				return sensor_trackElement != null && !sensor_trackElement.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SensorImpl
