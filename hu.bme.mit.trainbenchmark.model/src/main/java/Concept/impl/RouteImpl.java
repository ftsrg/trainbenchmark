/**
 */
package Concept.impl;

import Concept.ConceptPackage;
import Concept.Route;
import Concept.Sensor;
import Concept.Signal;
import Concept.SwitchPosition;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Route</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Concept.impl.RouteImpl#getRoute_entry <em>Route entry</em>}</li>
 *   <li>{@link Concept.impl.RouteImpl#getRoute_switchPosition <em>Route switch Position</em>}</li>
 *   <li>{@link Concept.impl.RouteImpl#getRoute_exit <em>Route exit</em>}</li>
 *   <li>{@link Concept.impl.RouteImpl#getRoute_routeDefinition <em>Route route Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RouteImpl extends ThingImpl implements Route {
	/**
	 * The cached value of the '{@link #getRoute_entry() <em>Route entry</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoute_entry()
	 * @generated
	 * @ordered
	 */
	protected Signal route_entry;

	/**
	 * The cached value of the '{@link #getRoute_switchPosition() <em>Route switch Position</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoute_switchPosition()
	 * @generated
	 * @ordered
	 */
	protected EList<SwitchPosition> route_switchPosition;

	/**
	 * The cached value of the '{@link #getRoute_exit() <em>Route exit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoute_exit()
	 * @generated
	 * @ordered
	 */
	protected Signal route_exit;

	/**
	 * The cached value of the '{@link #getRoute_routeDefinition() <em>Route route Definition</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoute_routeDefinition()
	 * @generated
	 * @ordered
	 */
	protected EList<Sensor> route_routeDefinition;

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
		return ConceptPackage.Literals.ROUTE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signal getRoute_entry() {
		if (route_entry != null && route_entry.eIsProxy()) {
			InternalEObject oldRoute_entry = (InternalEObject)route_entry;
			route_entry = (Signal)eResolveProxy(oldRoute_entry);
			if (route_entry != oldRoute_entry) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConceptPackage.ROUTE__ROUTE_ENTRY, oldRoute_entry, route_entry));
			}
		}
		return route_entry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signal basicGetRoute_entry() {
		return route_entry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoute_entry(Signal newRoute_entry) {
		Signal oldRoute_entry = route_entry;
		route_entry = newRoute_entry;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConceptPackage.ROUTE__ROUTE_ENTRY, oldRoute_entry, route_entry));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SwitchPosition> getRoute_switchPosition() {
		if (route_switchPosition == null) {
			route_switchPosition = new EObjectWithInverseResolvingEList<SwitchPosition>(SwitchPosition.class, this, ConceptPackage.ROUTE__ROUTE_SWITCH_POSITION, ConceptPackage.SWITCH_POSITION__SWITCH_POSITION_ROUTE);
		}
		return route_switchPosition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signal getRoute_exit() {
		if (route_exit != null && route_exit.eIsProxy()) {
			InternalEObject oldRoute_exit = (InternalEObject)route_exit;
			route_exit = (Signal)eResolveProxy(oldRoute_exit);
			if (route_exit != oldRoute_exit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConceptPackage.ROUTE__ROUTE_EXIT, oldRoute_exit, route_exit));
			}
		}
		return route_exit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signal basicGetRoute_exit() {
		return route_exit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoute_exit(Signal newRoute_exit) {
		Signal oldRoute_exit = route_exit;
		route_exit = newRoute_exit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConceptPackage.ROUTE__ROUTE_EXIT, oldRoute_exit, route_exit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Sensor> getRoute_routeDefinition() {
		if (route_routeDefinition == null) {
			route_routeDefinition = new EObjectResolvingEList<Sensor>(Sensor.class, this, ConceptPackage.ROUTE__ROUTE_ROUTE_DEFINITION);
		}
		return route_routeDefinition;
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
			case ConceptPackage.ROUTE__ROUTE_SWITCH_POSITION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRoute_switchPosition()).basicAdd(otherEnd, msgs);
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
			case ConceptPackage.ROUTE__ROUTE_SWITCH_POSITION:
				return ((InternalEList<?>)getRoute_switchPosition()).basicRemove(otherEnd, msgs);
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
			case ConceptPackage.ROUTE__ROUTE_ENTRY:
				if (resolve) return getRoute_entry();
				return basicGetRoute_entry();
			case ConceptPackage.ROUTE__ROUTE_SWITCH_POSITION:
				return getRoute_switchPosition();
			case ConceptPackage.ROUTE__ROUTE_EXIT:
				if (resolve) return getRoute_exit();
				return basicGetRoute_exit();
			case ConceptPackage.ROUTE__ROUTE_ROUTE_DEFINITION:
				return getRoute_routeDefinition();
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
			case ConceptPackage.ROUTE__ROUTE_ENTRY:
				setRoute_entry((Signal)newValue);
				return;
			case ConceptPackage.ROUTE__ROUTE_SWITCH_POSITION:
				getRoute_switchPosition().clear();
				getRoute_switchPosition().addAll((Collection<? extends SwitchPosition>)newValue);
				return;
			case ConceptPackage.ROUTE__ROUTE_EXIT:
				setRoute_exit((Signal)newValue);
				return;
			case ConceptPackage.ROUTE__ROUTE_ROUTE_DEFINITION:
				getRoute_routeDefinition().clear();
				getRoute_routeDefinition().addAll((Collection<? extends Sensor>)newValue);
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
			case ConceptPackage.ROUTE__ROUTE_ENTRY:
				setRoute_entry((Signal)null);
				return;
			case ConceptPackage.ROUTE__ROUTE_SWITCH_POSITION:
				getRoute_switchPosition().clear();
				return;
			case ConceptPackage.ROUTE__ROUTE_EXIT:
				setRoute_exit((Signal)null);
				return;
			case ConceptPackage.ROUTE__ROUTE_ROUTE_DEFINITION:
				getRoute_routeDefinition().clear();
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
			case ConceptPackage.ROUTE__ROUTE_ENTRY:
				return route_entry != null;
			case ConceptPackage.ROUTE__ROUTE_SWITCH_POSITION:
				return route_switchPosition != null && !route_switchPosition.isEmpty();
			case ConceptPackage.ROUTE__ROUTE_EXIT:
				return route_exit != null;
			case ConceptPackage.ROUTE__ROUTE_ROUTE_DEFINITION:
				return route_routeDefinition != null && !route_routeDefinition.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RouteImpl
