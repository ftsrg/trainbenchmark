/**
 */
package hu.bme.mit.trainbenchmark.schedule.impl;

import hu.bme.mit.trainbenchmark.schedule.Association;
import hu.bme.mit.trainbenchmark.schedule.AssociationCategory;
import hu.bme.mit.trainbenchmark.schedule.Schedule;
import hu.bme.mit.trainbenchmark.schedule.SchedulePackage;
import hu.bme.mit.trainbenchmark.schedule.Station;

import java.util.Date;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.AssociationImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.AssociationImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.AssociationImpl#getDays <em>Days</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.AssociationImpl#getAssociative <em>Associative</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.AssociationImpl#getStpIndicator <em>Stp Indicator</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.AssociationImpl#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.AssociationImpl#getEndDate <em>End Date</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssociationImpl extends MinimalEObjectImpl.Container implements Association {
        /**
         * The cached value of the '{@link #getLocation() <em>Location</em>}' reference.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getLocation()
         * @generated
         * @ordered
         */
        protected Station location;

        /**
         * The default value of the '{@link #getCategory() <em>Category</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getCategory()
         * @generated
         * @ordered
         */
        protected static final AssociationCategory CATEGORY_EDEFAULT = AssociationCategory.JOIN;

        /**
         * The cached value of the '{@link #getCategory() <em>Category</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getCategory()
         * @generated
         * @ordered
         */
        protected AssociationCategory category = CATEGORY_EDEFAULT;

        /**
         * The default value of the '{@link #getDays() <em>Days</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getDays()
         * @generated
         * @ordered
         */
        protected static final String DAYS_EDEFAULT = null;

        /**
         * The cached value of the '{@link #getDays() <em>Days</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getDays()
         * @generated
         * @ordered
         */
        protected String days = DAYS_EDEFAULT;

        /**
         * The cached value of the '{@link #getAssociative() <em>Associative</em>}' reference.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getAssociative()
         * @generated
         * @ordered
         */
        protected Schedule associative;

        /**
         * The default value of the '{@link #getStpIndicator() <em>Stp Indicator</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getStpIndicator()
         * @generated
         * @ordered
         */
        protected static final String STP_INDICATOR_EDEFAULT = null;

        /**
         * The cached value of the '{@link #getStpIndicator() <em>Stp Indicator</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getStpIndicator()
         * @generated
         * @ordered
         */
        protected String stpIndicator = STP_INDICATOR_EDEFAULT;

        /**
         * The default value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getStartDate()
         * @generated
         * @ordered
         */
        protected static final Date START_DATE_EDEFAULT = null;

        /**
         * The cached value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getStartDate()
         * @generated
         * @ordered
         */
        protected Date startDate = START_DATE_EDEFAULT;

        /**
         * The default value of the '{@link #getEndDate() <em>End Date</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getEndDate()
         * @generated
         * @ordered
         */
        protected static final Date END_DATE_EDEFAULT = null;

        /**
         * The cached value of the '{@link #getEndDate() <em>End Date</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getEndDate()
         * @generated
         * @ordered
         */
        protected Date endDate = END_DATE_EDEFAULT;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected AssociationImpl() {
                super();
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        protected EClass eStaticClass() {
                return SchedulePackage.Literals.ASSOCIATION;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Station getLocation() {
                if (location != null && location.eIsProxy()) {
                        InternalEObject oldLocation = (InternalEObject)location;
                        location = (Station)eResolveProxy(oldLocation);
                        if (location != oldLocation) {
                                if (eNotificationRequired())
                                        eNotify(new ENotificationImpl(this, Notification.RESOLVE, SchedulePackage.ASSOCIATION__LOCATION, oldLocation, location));
                        }
                }
                return location;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Station basicGetLocation() {
                return location;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setLocation(Station newLocation) {
                Station oldLocation = location;
                location = newLocation;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.ASSOCIATION__LOCATION, oldLocation, location));
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public AssociationCategory getCategory() {
                return category;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setCategory(AssociationCategory newCategory) {
                AssociationCategory oldCategory = category;
                category = newCategory == null ? CATEGORY_EDEFAULT : newCategory;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.ASSOCIATION__CATEGORY, oldCategory, category));
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public String getDays() {
                return days;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setDays(String newDays) {
                String oldDays = days;
                days = newDays;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.ASSOCIATION__DAYS, oldDays, days));
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Schedule getAssociative() {
                if (associative != null && associative.eIsProxy()) {
                        InternalEObject oldAssociative = (InternalEObject)associative;
                        associative = (Schedule)eResolveProxy(oldAssociative);
                        if (associative != oldAssociative) {
                                if (eNotificationRequired())
                                        eNotify(new ENotificationImpl(this, Notification.RESOLVE, SchedulePackage.ASSOCIATION__ASSOCIATIVE, oldAssociative, associative));
                        }
                }
                return associative;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Schedule basicGetAssociative() {
                return associative;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setAssociative(Schedule newAssociative) {
                Schedule oldAssociative = associative;
                associative = newAssociative;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.ASSOCIATION__ASSOCIATIVE, oldAssociative, associative));
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public String getStpIndicator() {
                return stpIndicator;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setStpIndicator(String newStpIndicator) {
                String oldStpIndicator = stpIndicator;
                stpIndicator = newStpIndicator;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.ASSOCIATION__STP_INDICATOR, oldStpIndicator, stpIndicator));
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Date getStartDate() {
                return startDate;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setStartDate(Date newStartDate) {
                Date oldStartDate = startDate;
                startDate = newStartDate;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.ASSOCIATION__START_DATE, oldStartDate, startDate));
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Date getEndDate() {
                return endDate;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setEndDate(Date newEndDate) {
                Date oldEndDate = endDate;
                endDate = newEndDate;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.ASSOCIATION__END_DATE, oldEndDate, endDate));
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        public Object eGet(int featureID, boolean resolve, boolean coreType) {
                switch (featureID) {
                        case SchedulePackage.ASSOCIATION__LOCATION:
                                if (resolve) return getLocation();
                                return basicGetLocation();
                        case SchedulePackage.ASSOCIATION__CATEGORY:
                                return getCategory();
                        case SchedulePackage.ASSOCIATION__DAYS:
                                return getDays();
                        case SchedulePackage.ASSOCIATION__ASSOCIATIVE:
                                if (resolve) return getAssociative();
                                return basicGetAssociative();
                        case SchedulePackage.ASSOCIATION__STP_INDICATOR:
                                return getStpIndicator();
                        case SchedulePackage.ASSOCIATION__START_DATE:
                                return getStartDate();
                        case SchedulePackage.ASSOCIATION__END_DATE:
                                return getEndDate();
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
                        case SchedulePackage.ASSOCIATION__LOCATION:
                                setLocation((Station)newValue);
                                return;
                        case SchedulePackage.ASSOCIATION__CATEGORY:
                                setCategory((AssociationCategory)newValue);
                                return;
                        case SchedulePackage.ASSOCIATION__DAYS:
                                setDays((String)newValue);
                                return;
                        case SchedulePackage.ASSOCIATION__ASSOCIATIVE:
                                setAssociative((Schedule)newValue);
                                return;
                        case SchedulePackage.ASSOCIATION__STP_INDICATOR:
                                setStpIndicator((String)newValue);
                                return;
                        case SchedulePackage.ASSOCIATION__START_DATE:
                                setStartDate((Date)newValue);
                                return;
                        case SchedulePackage.ASSOCIATION__END_DATE:
                                setEndDate((Date)newValue);
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
                        case SchedulePackage.ASSOCIATION__LOCATION:
                                setLocation((Station)null);
                                return;
                        case SchedulePackage.ASSOCIATION__CATEGORY:
                                setCategory(CATEGORY_EDEFAULT);
                                return;
                        case SchedulePackage.ASSOCIATION__DAYS:
                                setDays(DAYS_EDEFAULT);
                                return;
                        case SchedulePackage.ASSOCIATION__ASSOCIATIVE:
                                setAssociative((Schedule)null);
                                return;
                        case SchedulePackage.ASSOCIATION__STP_INDICATOR:
                                setStpIndicator(STP_INDICATOR_EDEFAULT);
                                return;
                        case SchedulePackage.ASSOCIATION__START_DATE:
                                setStartDate(START_DATE_EDEFAULT);
                                return;
                        case SchedulePackage.ASSOCIATION__END_DATE:
                                setEndDate(END_DATE_EDEFAULT);
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
                        case SchedulePackage.ASSOCIATION__LOCATION:
                                return location != null;
                        case SchedulePackage.ASSOCIATION__CATEGORY:
                                return category != CATEGORY_EDEFAULT;
                        case SchedulePackage.ASSOCIATION__DAYS:
                                return DAYS_EDEFAULT == null ? days != null : !DAYS_EDEFAULT.equals(days);
                        case SchedulePackage.ASSOCIATION__ASSOCIATIVE:
                                return associative != null;
                        case SchedulePackage.ASSOCIATION__STP_INDICATOR:
                                return STP_INDICATOR_EDEFAULT == null ? stpIndicator != null : !STP_INDICATOR_EDEFAULT.equals(stpIndicator);
                        case SchedulePackage.ASSOCIATION__START_DATE:
                                return START_DATE_EDEFAULT == null ? startDate != null : !START_DATE_EDEFAULT.equals(startDate);
                        case SchedulePackage.ASSOCIATION__END_DATE:
                                return END_DATE_EDEFAULT == null ? endDate != null : !END_DATE_EDEFAULT.equals(endDate);
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
                result.append(" (category: ");
                result.append(category);
                result.append(", days: ");
                result.append(days);
                result.append(", stpIndicator: ");
                result.append(stpIndicator);
                result.append(", startDate: ");
                result.append(startDate);
                result.append(", endDate: ");
                result.append(endDate);
                result.append(')');
                return result.toString();
        }

} //AssociationImpl
