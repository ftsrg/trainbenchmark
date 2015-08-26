/**
 */
package hu.bme.mit.trainbenchmark.schedule.impl;

import hu.bme.mit.trainbenchmark.schedule.Schedule;
import hu.bme.mit.trainbenchmark.schedule.SchedulePackage;
import hu.bme.mit.trainbenchmark.schedule.SchedulePlanning;
import hu.bme.mit.trainbenchmark.schedule.Station;
import hu.bme.mit.trainbenchmark.schedule.Status;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Schedule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.ScheduleImpl#getDestinations <em>Destinations</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.ScheduleImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.ScheduleImpl#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.ScheduleImpl#getDays <em>Days</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.ScheduleImpl#getPlanning <em>Planning</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.ScheduleImpl#getOrigin <em>Origin</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.ScheduleImpl#getTerminal <em>Terminal</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.ScheduleImpl#getEndDate <em>End Date</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.impl.ScheduleImpl#getStpIndicator <em>Stp Indicator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScheduleImpl extends MinimalEObjectImpl.Container implements Schedule {
        /**
         * The cached value of the '{@link #getDestinations() <em>Destinations</em>}' containment reference list.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getDestinations()
         * @generated
         * @ordered
         */
        protected EList<Station> destinations;

        /**
         * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getStatus()
         * @generated
         * @ordered
         */
        protected static final Status STATUS_EDEFAULT = Status.FREIGHT;

        /**
         * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getStatus()
         * @generated
         * @ordered
         */
        protected Status status = STATUS_EDEFAULT;

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
         * The default value of the '{@link #getPlanning() <em>Planning</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getPlanning()
         * @generated
         * @ordered
         */
        protected static final SchedulePlanning PLANNING_EDEFAULT = SchedulePlanning.PERMANENT;

        /**
         * The cached value of the '{@link #getPlanning() <em>Planning</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getPlanning()
         * @generated
         * @ordered
         */
        protected SchedulePlanning planning = PLANNING_EDEFAULT;

        /**
         * The cached value of the '{@link #getOrigin() <em>Origin</em>}' containment reference.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getOrigin()
         * @generated
         * @ordered
         */
        protected Station origin;

        /**
         * The cached value of the '{@link #getTerminal() <em>Terminal</em>}' containment reference.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #getTerminal()
         * @generated
         * @ordered
         */
        protected Station terminal;

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
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        protected ScheduleImpl() {
                super();
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        protected EClass eStaticClass() {
                return SchedulePackage.Literals.SCHEDULE;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EList<Station> getDestinations() {
                if (destinations == null) {
                        destinations = new EObjectContainmentEList<Station>(Station.class, this, SchedulePackage.SCHEDULE__DESTINATIONS);
                }
                return destinations;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Status getStatus() {
                return status;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setStatus(Status newStatus) {
                Status oldStatus = status;
                status = newStatus == null ? STATUS_EDEFAULT : newStatus;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.SCHEDULE__STATUS, oldStatus, status));
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
                        eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.SCHEDULE__START_DATE, oldStartDate, startDate));
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
                        eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.SCHEDULE__DAYS, oldDays, days));
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public SchedulePlanning getPlanning() {
                return planning;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setPlanning(SchedulePlanning newPlanning) {
                SchedulePlanning oldPlanning = planning;
                planning = newPlanning == null ? PLANNING_EDEFAULT : newPlanning;
                if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.SCHEDULE__PLANNING, oldPlanning, planning));
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Station getOrigin() {
                return origin;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public NotificationChain basicSetOrigin(Station newOrigin, NotificationChain msgs) {
                Station oldOrigin = origin;
                origin = newOrigin;
                if (eNotificationRequired()) {
                        ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SchedulePackage.SCHEDULE__ORIGIN, oldOrigin, newOrigin);
                        if (msgs == null) msgs = notification; else msgs.add(notification);
                }
                return msgs;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setOrigin(Station newOrigin) {
                if (newOrigin != origin) {
                        NotificationChain msgs = null;
                        if (origin != null)
                                msgs = ((InternalEObject)origin).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SchedulePackage.SCHEDULE__ORIGIN, null, msgs);
                        if (newOrigin != null)
                                msgs = ((InternalEObject)newOrigin).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SchedulePackage.SCHEDULE__ORIGIN, null, msgs);
                        msgs = basicSetOrigin(newOrigin, msgs);
                        if (msgs != null) msgs.dispatch();
                }
                else if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.SCHEDULE__ORIGIN, newOrigin, newOrigin));
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Station getTerminal() {
                return terminal;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public NotificationChain basicSetTerminal(Station newTerminal, NotificationChain msgs) {
                Station oldTerminal = terminal;
                terminal = newTerminal;
                if (eNotificationRequired()) {
                        ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SchedulePackage.SCHEDULE__TERMINAL, oldTerminal, newTerminal);
                        if (msgs == null) msgs = notification; else msgs.add(notification);
                }
                return msgs;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void setTerminal(Station newTerminal) {
                if (newTerminal != terminal) {
                        NotificationChain msgs = null;
                        if (terminal != null)
                                msgs = ((InternalEObject)terminal).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SchedulePackage.SCHEDULE__TERMINAL, null, msgs);
                        if (newTerminal != null)
                                msgs = ((InternalEObject)newTerminal).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SchedulePackage.SCHEDULE__TERMINAL, null, msgs);
                        msgs = basicSetTerminal(newTerminal, msgs);
                        if (msgs != null) msgs.dispatch();
                }
                else if (eNotificationRequired())
                        eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.SCHEDULE__TERMINAL, newTerminal, newTerminal));
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
                        eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.SCHEDULE__END_DATE, oldEndDate, endDate));
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
                        eNotify(new ENotificationImpl(this, Notification.SET, SchedulePackage.SCHEDULE__STP_INDICATOR, oldStpIndicator, stpIndicator));
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
                switch (featureID) {
                        case SchedulePackage.SCHEDULE__DESTINATIONS:
                                return ((InternalEList<?>)getDestinations()).basicRemove(otherEnd, msgs);
                        case SchedulePackage.SCHEDULE__ORIGIN:
                                return basicSetOrigin(null, msgs);
                        case SchedulePackage.SCHEDULE__TERMINAL:
                                return basicSetTerminal(null, msgs);
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
                        case SchedulePackage.SCHEDULE__DESTINATIONS:
                                return getDestinations();
                        case SchedulePackage.SCHEDULE__STATUS:
                                return getStatus();
                        case SchedulePackage.SCHEDULE__START_DATE:
                                return getStartDate();
                        case SchedulePackage.SCHEDULE__DAYS:
                                return getDays();
                        case SchedulePackage.SCHEDULE__PLANNING:
                                return getPlanning();
                        case SchedulePackage.SCHEDULE__ORIGIN:
                                return getOrigin();
                        case SchedulePackage.SCHEDULE__TERMINAL:
                                return getTerminal();
                        case SchedulePackage.SCHEDULE__END_DATE:
                                return getEndDate();
                        case SchedulePackage.SCHEDULE__STP_INDICATOR:
                                return getStpIndicator();
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
                        case SchedulePackage.SCHEDULE__DESTINATIONS:
                                getDestinations().clear();
                                getDestinations().addAll((Collection<? extends Station>)newValue);
                                return;
                        case SchedulePackage.SCHEDULE__STATUS:
                                setStatus((Status)newValue);
                                return;
                        case SchedulePackage.SCHEDULE__START_DATE:
                                setStartDate((Date)newValue);
                                return;
                        case SchedulePackage.SCHEDULE__DAYS:
                                setDays((String)newValue);
                                return;
                        case SchedulePackage.SCHEDULE__PLANNING:
                                setPlanning((SchedulePlanning)newValue);
                                return;
                        case SchedulePackage.SCHEDULE__ORIGIN:
                                setOrigin((Station)newValue);
                                return;
                        case SchedulePackage.SCHEDULE__TERMINAL:
                                setTerminal((Station)newValue);
                                return;
                        case SchedulePackage.SCHEDULE__END_DATE:
                                setEndDate((Date)newValue);
                                return;
                        case SchedulePackage.SCHEDULE__STP_INDICATOR:
                                setStpIndicator((String)newValue);
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
                        case SchedulePackage.SCHEDULE__DESTINATIONS:
                                getDestinations().clear();
                                return;
                        case SchedulePackage.SCHEDULE__STATUS:
                                setStatus(STATUS_EDEFAULT);
                                return;
                        case SchedulePackage.SCHEDULE__START_DATE:
                                setStartDate(START_DATE_EDEFAULT);
                                return;
                        case SchedulePackage.SCHEDULE__DAYS:
                                setDays(DAYS_EDEFAULT);
                                return;
                        case SchedulePackage.SCHEDULE__PLANNING:
                                setPlanning(PLANNING_EDEFAULT);
                                return;
                        case SchedulePackage.SCHEDULE__ORIGIN:
                                setOrigin((Station)null);
                                return;
                        case SchedulePackage.SCHEDULE__TERMINAL:
                                setTerminal((Station)null);
                                return;
                        case SchedulePackage.SCHEDULE__END_DATE:
                                setEndDate(END_DATE_EDEFAULT);
                                return;
                        case SchedulePackage.SCHEDULE__STP_INDICATOR:
                                setStpIndicator(STP_INDICATOR_EDEFAULT);
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
                        case SchedulePackage.SCHEDULE__DESTINATIONS:
                                return destinations != null && !destinations.isEmpty();
                        case SchedulePackage.SCHEDULE__STATUS:
                                return status != STATUS_EDEFAULT;
                        case SchedulePackage.SCHEDULE__START_DATE:
                                return START_DATE_EDEFAULT == null ? startDate != null : !START_DATE_EDEFAULT.equals(startDate);
                        case SchedulePackage.SCHEDULE__DAYS:
                                return DAYS_EDEFAULT == null ? days != null : !DAYS_EDEFAULT.equals(days);
                        case SchedulePackage.SCHEDULE__PLANNING:
                                return planning != PLANNING_EDEFAULT;
                        case SchedulePackage.SCHEDULE__ORIGIN:
                                return origin != null;
                        case SchedulePackage.SCHEDULE__TERMINAL:
                                return terminal != null;
                        case SchedulePackage.SCHEDULE__END_DATE:
                                return END_DATE_EDEFAULT == null ? endDate != null : !END_DATE_EDEFAULT.equals(endDate);
                        case SchedulePackage.SCHEDULE__STP_INDICATOR:
                                return STP_INDICATOR_EDEFAULT == null ? stpIndicator != null : !STP_INDICATOR_EDEFAULT.equals(stpIndicator);
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
                result.append(" (status: ");
                result.append(status);
                result.append(", startDate: ");
                result.append(startDate);
                result.append(", days: ");
                result.append(days);
                result.append(", planning: ");
                result.append(planning);
                result.append(", endDate: ");
                result.append(endDate);
                result.append(", stpIndicator: ");
                result.append(stpIndicator);
                result.append(')');
                return result.toString();
        }

} //ScheduleImpl
