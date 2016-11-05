/**
 */
package hu.bme.mit.trainbenchmark.railway.impl;

import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Route</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.RouteImpl#getEntry <em>Entry</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.RouteImpl#getFollows <em>Follows</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.RouteImpl#getExit <em>Exit</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.RouteImpl#getRequires <em>Requires</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.RouteImpl#isActive <em>Active</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RouteImpl extends RailwayElementImpl implements Route {
	/**
	 * The cached value of the '{@link #getEntry() <em>Entry</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntry()
	 * @generated
	 * @ordered
	 */
	protected Semaphore entry;

	/**
	 * The cached value of the '{@link #getFollows() <em>Follows</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFollows()
	 * @generated
	 * @ordered
	 */
	protected EList<SwitchPosition> follows;

	/**
	 * The cached value of the '{@link #getExit() <em>Exit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExit()
	 * @generated
	 * @ordered
	 */
	protected Semaphore exit;

	/**
	 * The cached value of the '{@link #getRequires() <em>Requires</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequires()
	 * @generated
	 * @ordered
	 */
	protected EList<Sensor> requires;

	/**
	 * The default value of the '{@link #isActive() <em>Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isActive() <em>Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActive()
	 * @generated
	 * @ordered
	 */
	protected boolean active = ACTIVE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RouteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RailwayPackage.Literals.ROUTE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Semaphore getEntry() {
		if (entry != null && entry.eIsProxy()) {
			InternalEObject oldEntry = (InternalEObject)entry;
			entry = (Semaphore)eResolveProxy(oldEntry);
			if (entry != oldEntry) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RailwayPackage.ROUTE__ENTRY, oldEntry, entry));
			}
		}
		return entry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Semaphore basicGetEntry() {
		return entry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEntry(Semaphore newEntry) {
		Semaphore oldEntry = entry;
		entry = newEntry;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RailwayPackage.ROUTE__ENTRY, oldEntry, entry));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SwitchPosition> getFollows() {
		if (follows == null) {
			follows = new EObjectContainmentWithInverseEList<SwitchPosition>(SwitchPosition.class, this, RailwayPackage.ROUTE__FOLLOWS, RailwayPackage.SWITCH_POSITION__ROUTE);
		}
		return follows;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Semaphore getExit() {
		if (exit != null && exit.eIsProxy()) {
			InternalEObject oldExit = (InternalEObject)exit;
			exit = (Semaphore)eResolveProxy(oldExit);
			if (exit != oldExit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RailwayPackage.ROUTE__EXIT, oldExit, exit));
			}
		}
		return exit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Semaphore basicGetExit() {
		return exit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExit(Semaphore newExit) {
		Semaphore oldExit = exit;
		exit = newExit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RailwayPackage.ROUTE__EXIT, oldExit, exit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Sensor> getRequires() {
		if (requires == null) {
			requires = new EObjectResolvingEList<Sensor>(Sensor.class, this, RailwayPackage.ROUTE__REQUIRES);
		}
		return requires;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActive(boolean newActive) {
		boolean oldActive = active;
		active = newActive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RailwayPackage.ROUTE__ACTIVE, oldActive, active));
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
			case RailwayPackage.ROUTE__FOLLOWS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFollows()).basicAdd(otherEnd, msgs);
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
			case RailwayPackage.ROUTE__FOLLOWS:
				return ((InternalEList<?>)getFollows()).basicRemove(otherEnd, msgs);
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
			case RailwayPackage.ROUTE__ENTRY:
				if (resolve) return getEntry();
				return basicGetEntry();
			case RailwayPackage.ROUTE__FOLLOWS:
				return getFollows();
			case RailwayPackage.ROUTE__EXIT:
				if (resolve) return getExit();
				return basicGetExit();
			case RailwayPackage.ROUTE__REQUIRES:
				return getRequires();
			case RailwayPackage.ROUTE__ACTIVE:
				return isActive();
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
			case RailwayPackage.ROUTE__ENTRY:
				setEntry((Semaphore)newValue);
				return;
			case RailwayPackage.ROUTE__FOLLOWS:
				getFollows().clear();
				getFollows().addAll((Collection<? extends SwitchPosition>)newValue);
				return;
			case RailwayPackage.ROUTE__EXIT:
				setExit((Semaphore)newValue);
				return;
			case RailwayPackage.ROUTE__REQUIRES:
				getRequires().clear();
				getRequires().addAll((Collection<? extends Sensor>)newValue);
				return;
			case RailwayPackage.ROUTE__ACTIVE:
				setActive((Boolean)newValue);
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
			case RailwayPackage.ROUTE__ENTRY:
				setEntry((Semaphore)null);
				return;
			case RailwayPackage.ROUTE__FOLLOWS:
				getFollows().clear();
				return;
			case RailwayPackage.ROUTE__EXIT:
				setExit((Semaphore)null);
				return;
			case RailwayPackage.ROUTE__REQUIRES:
				getRequires().clear();
				return;
			case RailwayPackage.ROUTE__ACTIVE:
				setActive(ACTIVE_EDEFAULT);
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
			case RailwayPackage.ROUTE__ENTRY:
				return entry != null;
			case RailwayPackage.ROUTE__FOLLOWS:
				return follows != null && !follows.isEmpty();
			case RailwayPackage.ROUTE__EXIT:
				return exit != null;
			case RailwayPackage.ROUTE__REQUIRES:
				return requires != null && !requires.isEmpty();
			case RailwayPackage.ROUTE__ACTIVE:
				return active != ACTIVE_EDEFAULT;
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
		result.append(" (active: ");
		result.append(active);
		result.append(')');
		return result.toString();
	}

} //RouteImpl
