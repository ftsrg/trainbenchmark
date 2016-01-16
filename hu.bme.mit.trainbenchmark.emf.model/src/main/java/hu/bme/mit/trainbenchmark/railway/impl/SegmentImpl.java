/**
 */
package hu.bme.mit.trainbenchmark.railway.impl;

import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Segment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.SegmentImpl#getLength <em>Length</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.SegmentImpl#getSemaphores <em>Semaphores</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.SegmentImpl#getNeighbors <em>Neighbors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SegmentImpl extends TrackElementImpl implements Segment {
	/**
	 * The default value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected static final int LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected int length = LENGTH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSemaphores() <em>Semaphores</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSemaphores()
	 * @generated
	 * @ordered
	 */
	protected EList<Semaphore> semaphores;

	/**
	 * The cached value of the '{@link #getNeighbors() <em>Neighbors</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNeighbors()
	 * @generated
	 * @ordered
	 */
	protected EList<TrackElement> neighbors;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SegmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RailwayPackage.Literals.SEGMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLength() {
		return length;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLength(int newLength) {
		int oldLength = length;
		length = newLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RailwayPackage.SEGMENT__LENGTH, oldLength, length));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Semaphore> getSemaphores() {
		if (semaphores == null) {
			semaphores = new EObjectContainmentEList<Semaphore>(Semaphore.class, this, RailwayPackage.SEGMENT__SEMAPHORES);
		}
		return semaphores;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TrackElement> getNeighbors() {
		if (neighbors == null) {
			neighbors = new EObjectResolvingEList<TrackElement>(TrackElement.class, this, RailwayPackage.SEGMENT__NEIGHBORS);
		}
		return neighbors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RailwayPackage.SEGMENT__SEMAPHORES:
				return ((InternalEList<?>)getSemaphores()).basicRemove(otherEnd, msgs);
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
			case RailwayPackage.SEGMENT__LENGTH:
				return getLength();
			case RailwayPackage.SEGMENT__SEMAPHORES:
				return getSemaphores();
			case RailwayPackage.SEGMENT__NEIGHBORS:
				return getNeighbors();
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
			case RailwayPackage.SEGMENT__LENGTH:
				setLength((Integer)newValue);
				return;
			case RailwayPackage.SEGMENT__SEMAPHORES:
				getSemaphores().clear();
				getSemaphores().addAll((Collection<? extends Semaphore>)newValue);
				return;
			case RailwayPackage.SEGMENT__NEIGHBORS:
				getNeighbors().clear();
				getNeighbors().addAll((Collection<? extends TrackElement>)newValue);
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
			case RailwayPackage.SEGMENT__LENGTH:
				setLength(LENGTH_EDEFAULT);
				return;
			case RailwayPackage.SEGMENT__SEMAPHORES:
				getSemaphores().clear();
				return;
			case RailwayPackage.SEGMENT__NEIGHBORS:
				getNeighbors().clear();
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
			case RailwayPackage.SEGMENT__LENGTH:
				return length != LENGTH_EDEFAULT;
			case RailwayPackage.SEGMENT__SEMAPHORES:
				return semaphores != null && !semaphores.isEmpty();
			case RailwayPackage.SEGMENT__NEIGHBORS:
				return neighbors != null && !neighbors.isEmpty();
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
		result.append(" (length: ");
		result.append(length);
		result.append(')');
		return result.toString();
	}

} //SegmentImpl
