/**
 */
package hu.bme.mit.trainbenchmark.schedule.impl;

import hu.bme.mit.trainbenchmark.schedule.Schedule;
import hu.bme.mit.trainbenchmark.schedule.ScheduleContainer;
import hu.bme.mit.trainbenchmark.schedule.SchedulePackage;

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
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.ScheduleContainerImpl#getSchedules <em>Schedules</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScheduleContainerImpl extends MinimalEObjectImpl.Container implements ScheduleContainer {
        /**
         * The cached value of the '{@link #getSchedules() <em>Schedules</em>}' containment reference list.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getSchedules()
         * @generated
         * @ordered
         */
        protected EList<Schedule> schedules;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected ScheduleContainerImpl() {
                super();
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        protected EClass eStaticClass() {
                return SchedulePackage.Literals.SCHEDULE_CONTAINER;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EList<Schedule> getSchedules() {
                if (schedules == null) {
                        schedules = new EObjectContainmentEList<Schedule>(Schedule.class, this, SchedulePackage.SCHEDULE_CONTAINER__SCHEDULES);
                }
                return schedules;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
                switch (featureID) {
                        case SchedulePackage.SCHEDULE_CONTAINER__SCHEDULES:
                                return ((InternalEList<?>)getSchedules()).basicRemove(otherEnd, msgs);
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
                        case SchedulePackage.SCHEDULE_CONTAINER__SCHEDULES:
                                return getSchedules();
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
                        case SchedulePackage.SCHEDULE_CONTAINER__SCHEDULES:
                                getSchedules().clear();
                                getSchedules().addAll((Collection<? extends Schedule>)newValue);
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
                        case SchedulePackage.SCHEDULE_CONTAINER__SCHEDULES:
                                getSchedules().clear();
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
                        case SchedulePackage.SCHEDULE_CONTAINER__SCHEDULES:
                                return schedules != null && !schedules.isEmpty();
                }
                return super.eIsSet(featureID);
        }

} //ScheduleContainerImpl
