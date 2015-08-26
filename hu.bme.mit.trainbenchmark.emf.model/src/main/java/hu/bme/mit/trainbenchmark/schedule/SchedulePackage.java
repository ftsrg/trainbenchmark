/**
 */
package hu.bme.mit.trainbenchmark.schedule;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see hu.bme.mit.trainbenchmark.schedule.ScheduleFactory
 * @model kind="package"
 * @generated
 */
public interface SchedulePackage extends EPackage {
        /**
         * The package name.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        String eNAME = "schedule";

        /**
         * The package namespace URI.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        String eNS_URI = "http://www.semanticweb.org/ontologies/2015/trainbenchmark/schedule";

        /**
         * The package namespace name.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        String eNS_PREFIX = "hu.bme.mit.trainbenchmark";

        /**
         * The singleton instance of the package.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        SchedulePackage eINSTANCE = hu.bme.mit.trainbenchmark.schedule.impl.SchedulePackageImpl.init();

        /**
         * The meta object id for the '{@link hu.bme.mit.trainbenchmark.schedule.impl.ScheduleContainerImpl <em>Container</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see hu.bme.mit.trainbenchmark.schedule.impl.ScheduleContainerImpl
         * @see hu.bme.mit.trainbenchmark.schedule.impl.SchedulePackageImpl#getScheduleContainer()
         * @generated
         */
        int SCHEDULE_CONTAINER = 0;

        /**
         * The feature id for the '<em><b>Schedules</b></em>' containment reference list.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int SCHEDULE_CONTAINER__SCHEDULES = 0;

        /**
         * The number of structural features of the '<em>Container</em>' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int SCHEDULE_CONTAINER_FEATURE_COUNT = 1;

        /**
         * The number of operations of the '<em>Container</em>' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int SCHEDULE_CONTAINER_OPERATION_COUNT = 0;

        /**
         * The meta object id for the '{@link hu.bme.mit.trainbenchmark.schedule.impl.ScheduleImpl <em>Schedule</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see hu.bme.mit.trainbenchmark.schedule.impl.ScheduleImpl
         * @see hu.bme.mit.trainbenchmark.schedule.impl.SchedulePackageImpl#getSchedule()
         * @generated
         */
        int SCHEDULE = 1;

        /**
         * The feature id for the '<em><b>Destinations</b></em>' containment reference list.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int SCHEDULE__DESTINATIONS = 0;

        /**
         * The feature id for the '<em><b>Status</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int SCHEDULE__STATUS = 1;

        /**
         * The feature id for the '<em><b>Start Date</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int SCHEDULE__START_DATE = 2;

        /**
         * The feature id for the '<em><b>Days</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int SCHEDULE__DAYS = 3;

        /**
         * The feature id for the '<em><b>Planning</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int SCHEDULE__PLANNING = 4;

        /**
         * The feature id for the '<em><b>Origin</b></em>' containment reference.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int SCHEDULE__ORIGIN = 5;

        /**
         * The feature id for the '<em><b>Terminal</b></em>' containment reference.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int SCHEDULE__TERMINAL = 6;

        /**
         * The feature id for the '<em><b>Uid</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int SCHEDULE__UID = 7;

        /**
         * The feature id for the '<em><b>Association</b></em>' containment reference.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int SCHEDULE__ASSOCIATION = 8;

        /**
         * The feature id for the '<em><b>Stp Indicator</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int SCHEDULE__STP_INDICATOR = 9;

        /**
         * The feature id for the '<em><b>End Date</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int SCHEDULE__END_DATE = 10;

        /**
         * The number of structural features of the '<em>Schedule</em>' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int SCHEDULE_FEATURE_COUNT = 11;

        /**
         * The number of operations of the '<em>Schedule</em>' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int SCHEDULE_OPERATION_COUNT = 0;

        /**
         * The meta object id for the '{@link hu.bme.mit.trainbenchmark.schedule.impl.StationImpl <em>Station</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see hu.bme.mit.trainbenchmark.schedule.impl.StationImpl
         * @see hu.bme.mit.trainbenchmark.schedule.impl.SchedulePackageImpl#getStation()
         * @generated
         */
        int STATION = 2;

        /**
         * The feature id for the '<em><b>Neighbors</b></em>' reference list.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int STATION__NEIGHBORS = 0;

        /**
         * The feature id for the '<em><b>Code</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int STATION__CODE = 1;

        /**
         * The number of structural features of the '<em>Station</em>' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int STATION_FEATURE_COUNT = 2;

        /**
         * The number of operations of the '<em>Station</em>' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int STATION_OPERATION_COUNT = 0;

        /**
         * The meta object id for the '{@link hu.bme.mit.trainbenchmark.schedule.impl.AssociationImpl <em>Association</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see hu.bme.mit.trainbenchmark.schedule.impl.AssociationImpl
         * @see hu.bme.mit.trainbenchmark.schedule.impl.SchedulePackageImpl#getAssociation()
         * @generated
         */
        int ASSOCIATION = 3;

        /**
         * The feature id for the '<em><b>Location</b></em>' reference.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int ASSOCIATION__LOCATION = 0;

        /**
         * The feature id for the '<em><b>Category</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int ASSOCIATION__CATEGORY = 1;

        /**
         * The feature id for the '<em><b>Days</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int ASSOCIATION__DAYS = 2;

        /**
         * The feature id for the '<em><b>Associative</b></em>' reference.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int ASSOCIATION__ASSOCIATIVE = 3;

        /**
         * The feature id for the '<em><b>Stp Indicator</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int ASSOCIATION__STP_INDICATOR = 4;

        /**
         * The feature id for the '<em><b>Start Date</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int ASSOCIATION__START_DATE = 5;

        /**
         * The feature id for the '<em><b>End Date</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int ASSOCIATION__END_DATE = 6;

        /**
         * The number of structural features of the '<em>Association</em>' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int ASSOCIATION_FEATURE_COUNT = 7;

        /**
         * The number of operations of the '<em>Association</em>' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @ordered
         */
        int ASSOCIATION_OPERATION_COUNT = 0;

        /**
         * The meta object id for the '{@link hu.bme.mit.trainbenchmark.schedule.AssociationCategory <em>Association Category</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see hu.bme.mit.trainbenchmark.schedule.AssociationCategory
         * @see hu.bme.mit.trainbenchmark.schedule.impl.SchedulePackageImpl#getAssociationCategory()
         * @generated
         */
        int ASSOCIATION_CATEGORY = 4;

        /**
         * The meta object id for the '{@link hu.bme.mit.trainbenchmark.schedule.Status <em>Status</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see hu.bme.mit.trainbenchmark.schedule.Status
         * @see hu.bme.mit.trainbenchmark.schedule.impl.SchedulePackageImpl#getStatus()
         * @generated
         */
        int STATUS = 5;

        /**
         * The meta object id for the '{@link hu.bme.mit.trainbenchmark.schedule.SchedulePlanning <em>Planning</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see hu.bme.mit.trainbenchmark.schedule.SchedulePlanning
         * @see hu.bme.mit.trainbenchmark.schedule.impl.SchedulePackageImpl#getSchedulePlanning()
         * @generated
         */
        int SCHEDULE_PLANNING = 6;


        /**
         * Returns the meta object for class '{@link hu.bme.mit.trainbenchmark.schedule.ScheduleContainer <em>Container</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for class '<em>Container</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.ScheduleContainer
         * @generated
         */
        EClass getScheduleContainer();

        /**
         * Returns the meta object for the containment reference list '{@link hu.bme.mit.trainbenchmark.schedule.ScheduleContainer#getSchedules <em>Schedules</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the containment reference list '<em>Schedules</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.ScheduleContainer#getSchedules()
         * @see #getScheduleContainer()
         * @generated
         */
        EReference getScheduleContainer_Schedules();

        /**
         * Returns the meta object for class '{@link hu.bme.mit.trainbenchmark.schedule.Schedule <em>Schedule</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for class '<em>Schedule</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Schedule
         * @generated
         */
        EClass getSchedule();

        /**
         * Returns the meta object for the containment reference list '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getDestinations <em>Destinations</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the containment reference list '<em>Destinations</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Schedule#getDestinations()
         * @see #getSchedule()
         * @generated
         */
        EReference getSchedule_Destinations();

        /**
         * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getStatus <em>Status</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the attribute '<em>Status</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Schedule#getStatus()
         * @see #getSchedule()
         * @generated
         */
        EAttribute getSchedule_Status();

        /**
         * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getStartDate <em>Start Date</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the attribute '<em>Start Date</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Schedule#getStartDate()
         * @see #getSchedule()
         * @generated
         */
        EAttribute getSchedule_StartDate();

        /**
         * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getDays <em>Days</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the attribute '<em>Days</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Schedule#getDays()
         * @see #getSchedule()
         * @generated
         */
        EAttribute getSchedule_Days();

        /**
         * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getPlanning <em>Planning</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the attribute '<em>Planning</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Schedule#getPlanning()
         * @see #getSchedule()
         * @generated
         */
        EAttribute getSchedule_Planning();

        /**
         * Returns the meta object for the containment reference '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getOrigin <em>Origin</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the containment reference '<em>Origin</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Schedule#getOrigin()
         * @see #getSchedule()
         * @generated
         */
        EReference getSchedule_Origin();

        /**
         * Returns the meta object for the containment reference '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getTerminal <em>Terminal</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the containment reference '<em>Terminal</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Schedule#getTerminal()
         * @see #getSchedule()
         * @generated
         */
        EReference getSchedule_Terminal();

        /**
         * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getUid <em>Uid</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the attribute '<em>Uid</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Schedule#getUid()
         * @see #getSchedule()
         * @generated
         */
        EAttribute getSchedule_Uid();

        /**
         * Returns the meta object for the containment reference '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getAssociation <em>Association</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the containment reference '<em>Association</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Schedule#getAssociation()
         * @see #getSchedule()
         * @generated
         */
        EReference getSchedule_Association();

        /**
         * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getStpIndicator <em>Stp Indicator</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the attribute '<em>Stp Indicator</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Schedule#getStpIndicator()
         * @see #getSchedule()
         * @generated
         */
        EAttribute getSchedule_StpIndicator();

        /**
         * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getEndDate <em>End Date</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the attribute '<em>End Date</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Schedule#getEndDate()
         * @see #getSchedule()
         * @generated
         */
        EAttribute getSchedule_EndDate();

        /**
         * Returns the meta object for class '{@link hu.bme.mit.trainbenchmark.schedule.Station <em>Station</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for class '<em>Station</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Station
         * @generated
         */
        EClass getStation();

        /**
         * Returns the meta object for the reference list '{@link hu.bme.mit.trainbenchmark.schedule.Station#getNeighbors <em>Neighbors</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the reference list '<em>Neighbors</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Station#getNeighbors()
         * @see #getStation()
         * @generated
         */
        EReference getStation_Neighbors();

        /**
         * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.schedule.Station#getCode <em>Code</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the attribute '<em>Code</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Station#getCode()
         * @see #getStation()
         * @generated
         */
        EAttribute getStation_Code();

        /**
         * Returns the meta object for class '{@link hu.bme.mit.trainbenchmark.schedule.Association <em>Association</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for class '<em>Association</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Association
         * @generated
         */
        EClass getAssociation();

        /**
         * Returns the meta object for the reference '{@link hu.bme.mit.trainbenchmark.schedule.Association#getLocation <em>Location</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the reference '<em>Location</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Association#getLocation()
         * @see #getAssociation()
         * @generated
         */
        EReference getAssociation_Location();

        /**
         * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.schedule.Association#getCategory <em>Category</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the attribute '<em>Category</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Association#getCategory()
         * @see #getAssociation()
         * @generated
         */
        EAttribute getAssociation_Category();

        /**
         * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.schedule.Association#getDays <em>Days</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the attribute '<em>Days</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Association#getDays()
         * @see #getAssociation()
         * @generated
         */
        EAttribute getAssociation_Days();

        /**
         * Returns the meta object for the reference '{@link hu.bme.mit.trainbenchmark.schedule.Association#getAssociative <em>Associative</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the reference '<em>Associative</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Association#getAssociative()
         * @see #getAssociation()
         * @generated
         */
        EReference getAssociation_Associative();

        /**
         * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.schedule.Association#getStpIndicator <em>Stp Indicator</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the attribute '<em>Stp Indicator</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Association#getStpIndicator()
         * @see #getAssociation()
         * @generated
         */
        EAttribute getAssociation_StpIndicator();

        /**
         * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.schedule.Association#getStartDate <em>Start Date</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the attribute '<em>Start Date</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Association#getStartDate()
         * @see #getAssociation()
         * @generated
         */
        EAttribute getAssociation_StartDate();

        /**
         * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.schedule.Association#getEndDate <em>End Date</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for the attribute '<em>End Date</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Association#getEndDate()
         * @see #getAssociation()
         * @generated
         */
        EAttribute getAssociation_EndDate();

        /**
         * Returns the meta object for enum '{@link hu.bme.mit.trainbenchmark.schedule.AssociationCategory <em>Association Category</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for enum '<em>Association Category</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.AssociationCategory
         * @generated
         */
        EEnum getAssociationCategory();

        /**
         * Returns the meta object for enum '{@link hu.bme.mit.trainbenchmark.schedule.Status <em>Status</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for enum '<em>Status</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.Status
         * @generated
         */
        EEnum getStatus();

        /**
         * Returns the meta object for enum '{@link hu.bme.mit.trainbenchmark.schedule.SchedulePlanning <em>Planning</em>}'.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the meta object for enum '<em>Planning</em>'.
         * @see hu.bme.mit.trainbenchmark.schedule.SchedulePlanning
         * @generated
         */
        EEnum getSchedulePlanning();

        /**
         * Returns the factory that creates the instances of the model.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @return the factory that creates the instances of the model.
         * @generated
         */
        ScheduleFactory getScheduleFactory();

        /**
         * <!-- begin-user-doc -->
         * Defines literals for the meta objects that represent
         * <ul>
         *   <li>each class,</li>
         *   <li>each feature of each class,</li>
         *   <li>each operation of each class,</li>
         *   <li>each enum,</li>
         *   <li>and each data type</li>
         * </ul>
         * <!-- end-user-doc -->
         * @generated
         */
        interface Literals {
                /**
                 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.schedule.impl.ScheduleContainerImpl <em>Container</em>}' class.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @see hu.bme.mit.trainbenchmark.schedule.impl.ScheduleContainerImpl
                 * @see hu.bme.mit.trainbenchmark.schedule.impl.SchedulePackageImpl#getScheduleContainer()
                 * @generated
                 */
                EClass SCHEDULE_CONTAINER = eINSTANCE.getScheduleContainer();

                /**
                 * The meta object literal for the '<em><b>Schedules</b></em>' containment reference list feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EReference SCHEDULE_CONTAINER__SCHEDULES = eINSTANCE.getScheduleContainer_Schedules();

                /**
                 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.schedule.impl.ScheduleImpl <em>Schedule</em>}' class.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @see hu.bme.mit.trainbenchmark.schedule.impl.ScheduleImpl
                 * @see hu.bme.mit.trainbenchmark.schedule.impl.SchedulePackageImpl#getSchedule()
                 * @generated
                 */
                EClass SCHEDULE = eINSTANCE.getSchedule();

                /**
                 * The meta object literal for the '<em><b>Destinations</b></em>' containment reference list feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EReference SCHEDULE__DESTINATIONS = eINSTANCE.getSchedule_Destinations();

                /**
                 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EAttribute SCHEDULE__STATUS = eINSTANCE.getSchedule_Status();

                /**
                 * The meta object literal for the '<em><b>Start Date</b></em>' attribute feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EAttribute SCHEDULE__START_DATE = eINSTANCE.getSchedule_StartDate();

                /**
                 * The meta object literal for the '<em><b>Days</b></em>' attribute feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EAttribute SCHEDULE__DAYS = eINSTANCE.getSchedule_Days();

                /**
                 * The meta object literal for the '<em><b>Planning</b></em>' attribute feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EAttribute SCHEDULE__PLANNING = eINSTANCE.getSchedule_Planning();

                /**
                 * The meta object literal for the '<em><b>Origin</b></em>' containment reference feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EReference SCHEDULE__ORIGIN = eINSTANCE.getSchedule_Origin();

                /**
                 * The meta object literal for the '<em><b>Terminal</b></em>' containment reference feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EReference SCHEDULE__TERMINAL = eINSTANCE.getSchedule_Terminal();

                /**
                 * The meta object literal for the '<em><b>Uid</b></em>' attribute feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EAttribute SCHEDULE__UID = eINSTANCE.getSchedule_Uid();

                /**
                 * The meta object literal for the '<em><b>Association</b></em>' containment reference feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EReference SCHEDULE__ASSOCIATION = eINSTANCE.getSchedule_Association();

                /**
                 * The meta object literal for the '<em><b>Stp Indicator</b></em>' attribute feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EAttribute SCHEDULE__STP_INDICATOR = eINSTANCE.getSchedule_StpIndicator();

                /**
                 * The meta object literal for the '<em><b>End Date</b></em>' attribute feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EAttribute SCHEDULE__END_DATE = eINSTANCE.getSchedule_EndDate();

                /**
                 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.schedule.impl.StationImpl <em>Station</em>}' class.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @see hu.bme.mit.trainbenchmark.schedule.impl.StationImpl
                 * @see hu.bme.mit.trainbenchmark.schedule.impl.SchedulePackageImpl#getStation()
                 * @generated
                 */
                EClass STATION = eINSTANCE.getStation();

                /**
                 * The meta object literal for the '<em><b>Neighbors</b></em>' reference list feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EReference STATION__NEIGHBORS = eINSTANCE.getStation_Neighbors();

                /**
                 * The meta object literal for the '<em><b>Code</b></em>' attribute feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EAttribute STATION__CODE = eINSTANCE.getStation_Code();

                /**
                 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.schedule.impl.AssociationImpl <em>Association</em>}' class.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @see hu.bme.mit.trainbenchmark.schedule.impl.AssociationImpl
                 * @see hu.bme.mit.trainbenchmark.schedule.impl.SchedulePackageImpl#getAssociation()
                 * @generated
                 */
                EClass ASSOCIATION = eINSTANCE.getAssociation();

                /**
                 * The meta object literal for the '<em><b>Location</b></em>' reference feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EReference ASSOCIATION__LOCATION = eINSTANCE.getAssociation_Location();

                /**
                 * The meta object literal for the '<em><b>Category</b></em>' attribute feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EAttribute ASSOCIATION__CATEGORY = eINSTANCE.getAssociation_Category();

                /**
                 * The meta object literal for the '<em><b>Days</b></em>' attribute feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EAttribute ASSOCIATION__DAYS = eINSTANCE.getAssociation_Days();

                /**
                 * The meta object literal for the '<em><b>Associative</b></em>' reference feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EReference ASSOCIATION__ASSOCIATIVE = eINSTANCE.getAssociation_Associative();

                /**
                 * The meta object literal for the '<em><b>Stp Indicator</b></em>' attribute feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EAttribute ASSOCIATION__STP_INDICATOR = eINSTANCE.getAssociation_StpIndicator();

                /**
                 * The meta object literal for the '<em><b>Start Date</b></em>' attribute feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EAttribute ASSOCIATION__START_DATE = eINSTANCE.getAssociation_StartDate();

                /**
                 * The meta object literal for the '<em><b>End Date</b></em>' attribute feature.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @generated
                 */
                EAttribute ASSOCIATION__END_DATE = eINSTANCE.getAssociation_EndDate();

                /**
                 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.schedule.AssociationCategory <em>Association Category</em>}' enum.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @see hu.bme.mit.trainbenchmark.schedule.AssociationCategory
                 * @see hu.bme.mit.trainbenchmark.schedule.impl.SchedulePackageImpl#getAssociationCategory()
                 * @generated
                 */
                EEnum ASSOCIATION_CATEGORY = eINSTANCE.getAssociationCategory();

                /**
                 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.schedule.Status <em>Status</em>}' enum.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @see hu.bme.mit.trainbenchmark.schedule.Status
                 * @see hu.bme.mit.trainbenchmark.schedule.impl.SchedulePackageImpl#getStatus()
                 * @generated
                 */
                EEnum STATUS = eINSTANCE.getStatus();

                /**
                 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.schedule.SchedulePlanning <em>Planning</em>}' enum.
                 * <!-- begin-user-doc -->
                 * <!-- end-user-doc -->
                 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePlanning
                 * @see hu.bme.mit.trainbenchmark.schedule.impl.SchedulePackageImpl#getSchedulePlanning()
                 * @generated
                 */
                EEnum SCHEDULE_PLANNING = eINSTANCE.getSchedulePlanning();

        }

} //SchedulePackage
