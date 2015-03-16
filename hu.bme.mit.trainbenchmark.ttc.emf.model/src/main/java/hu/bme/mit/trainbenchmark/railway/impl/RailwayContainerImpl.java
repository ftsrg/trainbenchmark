/**
 */
package hu.bme.mit.trainbenchmark.railway.impl;

import hu.bme.mit.trainbenchmark.railway.RailwayContainer;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.RailwayContainerImpl#getInvalids <em>Invalids</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.RailwayContainerImpl#getSemaphores <em>Semaphores</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.impl.RailwayContainerImpl#getRoutes <em>Routes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RailwayContainerImpl extends MinimalEObjectImpl.Container implements RailwayContainer {
	/**
	 * The cached value of the '{@link #getInvalids() <em>Invalids</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvalids()
	 * @generated
	 * @ordered
	 */
	protected EList<RailwayElement> invalids;

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
	 * The cached value of the '{@link #getRoutes() <em>Routes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoutes()
	 * @generated
	 * @ordered
	 */
	protected EList<Route> routes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RailwayContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RailwayPackage.Literals.RAILWAY_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RailwayElement> getInvalids() {
		if (invalids == null) {
			invalids = new EObjectContainmentEList<RailwayElement>(RailwayElement.class, this, RailwayPackage.RAILWAY_CONTAINER__INVALIDS);
		}
		return invalids;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Semaphore> getSemaphores() {
		if (semaphores == null) {
			semaphores = new EObjectContainmentEList<Semaphore>(Semaphore.class, this, RailwayPackage.RAILWAY_CONTAINER__SEMAPHORES);
		}
		return semaphores;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Route> getRoutes() {
		if (routes == null) {
			routes = new EObjectContainmentEList<Route>(Route.class, this, RailwayPackage.RAILWAY_CONTAINER__ROUTES);
		}
		return routes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RailwayPackage.RAILWAY_CONTAINER__INVALIDS:
				return ((InternalEList<?>)getInvalids()).basicRemove(otherEnd, msgs);
			case RailwayPackage.RAILWAY_CONTAINER__SEMAPHORES:
				return ((InternalEList<?>)getSemaphores()).basicRemove(otherEnd, msgs);
			case RailwayPackage.RAILWAY_CONTAINER__ROUTES:
				return ((InternalEList<?>)getRoutes()).basicRemove(otherEnd, msgs);
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
			case RailwayPackage.RAILWAY_CONTAINER__INVALIDS:
				return getInvalids();
			case RailwayPackage.RAILWAY_CONTAINER__SEMAPHORES:
				return getSemaphores();
			case RailwayPackage.RAILWAY_CONTAINER__ROUTES:
				return getRoutes();
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
			case RailwayPackage.RAILWAY_CONTAINER__INVALIDS:
				getInvalids().clear();
				getInvalids().addAll((Collection<? extends RailwayElement>)newValue);
				return;
			case RailwayPackage.RAILWAY_CONTAINER__SEMAPHORES:
				getSemaphores().clear();
				getSemaphores().addAll((Collection<? extends Semaphore>)newValue);
				return;
			case RailwayPackage.RAILWAY_CONTAINER__ROUTES:
				getRoutes().clear();
				getRoutes().addAll((Collection<? extends Route>)newValue);
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
			case RailwayPackage.RAILWAY_CONTAINER__INVALIDS:
				getInvalids().clear();
				return;
			case RailwayPackage.RAILWAY_CONTAINER__SEMAPHORES:
				getSemaphores().clear();
				return;
			case RailwayPackage.RAILWAY_CONTAINER__ROUTES:
				getRoutes().clear();
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
			case RailwayPackage.RAILWAY_CONTAINER__INVALIDS:
				return invalids != null && !invalids.isEmpty();
			case RailwayPackage.RAILWAY_CONTAINER__SEMAPHORES:
				return semaphores != null && !semaphores.isEmpty();
			case RailwayPackage.RAILWAY_CONTAINER__ROUTES:
				return routes != null && !routes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RailwayContainerImpl
