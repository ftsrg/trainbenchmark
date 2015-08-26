/**
 */
package hu.bme.mit.trainbenchmark.schedule.impl;

import hu.bme.mit.trainbenchmark.schedule.Association;
import hu.bme.mit.trainbenchmark.schedule.AssociationCategory;
import hu.bme.mit.trainbenchmark.schedule.Schedule;
import hu.bme.mit.trainbenchmark.schedule.ScheduleFactory;
import hu.bme.mit.trainbenchmark.schedule.SchedulePackage;
import hu.bme.mit.trainbenchmark.schedule.SchedulePlanning;
import hu.bme.mit.trainbenchmark.schedule.Station;
import hu.bme.mit.trainbenchmark.schedule.Status;
import hu.bme.mit.trainbenchmark.schedule.Train;
import hu.bme.mit.trainbenchmark.schedule.TrainContainer;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SchedulePackageImpl extends EPackageImpl implements SchedulePackage {
        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        private EClass trainContainerEClass = null;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        private EClass scheduleEClass = null;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        private EClass trainEClass = null;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        private EClass stationEClass = null;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        private EClass associationEClass = null;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        private EEnum associationCategoryEEnum = null;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        private EEnum statusEEnum = null;

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        private EEnum schedulePlanningEEnum = null;

        /**
         * Creates an instance of the model <b>Package</b>, registered with
         * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
         * package URI value.
         * <p>Note: the correct way to create the package is via the static
         * factory method {@link #init init()}, which also performs
         * initialization of the package, or returns the registered package,
         * if one already exists.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.emf.ecore.EPackage.Registry
         * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#eNS_URI
         * @see #init()
         * @generated
         */
        private SchedulePackageImpl() {
                super(eNS_URI, ScheduleFactory.eINSTANCE);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        private static boolean isInited = false;

        /**
         * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
         * 
         * <p>This method is used to initialize {@link SchedulePackage#eINSTANCE} when that field is accessed.
         * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #eNS_URI
         * @see #createPackageContents()
         * @see #initializePackageContents()
         * @generated
         */
        public static SchedulePackage init() {
                if (isInited) return (SchedulePackage)EPackage.Registry.INSTANCE.getEPackage(SchedulePackage.eNS_URI);

                // Obtain or create and register package
                SchedulePackageImpl theSchedulePackage = (SchedulePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SchedulePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SchedulePackageImpl());

                isInited = true;

                // Create package meta-data objects
                theSchedulePackage.createPackageContents();

                // Initialize created meta-data
                theSchedulePackage.initializePackageContents();

                // Mark meta-data to indicate it can't be changed
                theSchedulePackage.freeze();

  
                // Update the registry and return the package
                EPackage.Registry.INSTANCE.put(SchedulePackage.eNS_URI, theSchedulePackage);
                return theSchedulePackage;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EClass getTrainContainer() {
                return trainContainerEClass;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EReference getTrainContainer_Trains() {
                return (EReference)trainContainerEClass.getEStructuralFeatures().get(0);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EClass getSchedule() {
                return scheduleEClass;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EReference getSchedule_Destinations() {
                return (EReference)scheduleEClass.getEStructuralFeatures().get(0);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EAttribute getSchedule_Status() {
                return (EAttribute)scheduleEClass.getEStructuralFeatures().get(1);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EAttribute getSchedule_StartDate() {
                return (EAttribute)scheduleEClass.getEStructuralFeatures().get(2);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EAttribute getSchedule_Days() {
                return (EAttribute)scheduleEClass.getEStructuralFeatures().get(3);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EAttribute getSchedule_Planning() {
                return (EAttribute)scheduleEClass.getEStructuralFeatures().get(4);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EReference getSchedule_Origin() {
                return (EReference)scheduleEClass.getEStructuralFeatures().get(5);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EReference getSchedule_Terminal() {
                return (EReference)scheduleEClass.getEStructuralFeatures().get(6);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EAttribute getSchedule_EndDate() {
                return (EAttribute)scheduleEClass.getEStructuralFeatures().get(7);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EAttribute getSchedule_StpIndicator() {
                return (EAttribute)scheduleEClass.getEStructuralFeatures().get(8);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EClass getTrain() {
                return trainEClass;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EReference getTrain_Schedules() {
                return (EReference)trainEClass.getEStructuralFeatures().get(0);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EReference getTrain_Associations() {
                return (EReference)trainEClass.getEStructuralFeatures().get(1);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EAttribute getTrain_Uid() {
                return (EAttribute)trainEClass.getEStructuralFeatures().get(2);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EClass getStation() {
                return stationEClass;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EReference getStation_Neighbors() {
                return (EReference)stationEClass.getEStructuralFeatures().get(0);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EAttribute getStation_Code() {
                return (EAttribute)stationEClass.getEStructuralFeatures().get(1);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EClass getAssociation() {
                return associationEClass;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EReference getAssociation_Location() {
                return (EReference)associationEClass.getEStructuralFeatures().get(0);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EReference getAssociation_Associative() {
                return (EReference)associationEClass.getEStructuralFeatures().get(1);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EAttribute getAssociation_Category() {
                return (EAttribute)associationEClass.getEStructuralFeatures().get(2);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EAttribute getAssociation_Days() {
                return (EAttribute)associationEClass.getEStructuralFeatures().get(3);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EAttribute getAssociation_StpIndicator() {
                return (EAttribute)associationEClass.getEStructuralFeatures().get(4);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EAttribute getAssociation_StartDate() {
                return (EAttribute)associationEClass.getEStructuralFeatures().get(5);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EAttribute getAssociation_EndDate() {
                return (EAttribute)associationEClass.getEStructuralFeatures().get(6);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EEnum getAssociationCategory() {
                return associationCategoryEEnum;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EEnum getStatus() {
                return statusEEnum;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EEnum getSchedulePlanning() {
                return schedulePlanningEEnum;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public ScheduleFactory getScheduleFactory() {
                return (ScheduleFactory)getEFactoryInstance();
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        private boolean isCreated = false;

        /**
         * Creates the meta-model objects for the package.  This method is
         * guarded to have no affect on any invocation but its first.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void createPackageContents() {
                if (isCreated) return;
                isCreated = true;

                // Create classes and their features
                trainContainerEClass = createEClass(TRAIN_CONTAINER);
                createEReference(trainContainerEClass, TRAIN_CONTAINER__TRAINS);

                scheduleEClass = createEClass(SCHEDULE);
                createEReference(scheduleEClass, SCHEDULE__DESTINATIONS);
                createEAttribute(scheduleEClass, SCHEDULE__STATUS);
                createEAttribute(scheduleEClass, SCHEDULE__START_DATE);
                createEAttribute(scheduleEClass, SCHEDULE__DAYS);
                createEAttribute(scheduleEClass, SCHEDULE__PLANNING);
                createEReference(scheduleEClass, SCHEDULE__ORIGIN);
                createEReference(scheduleEClass, SCHEDULE__TERMINAL);
                createEAttribute(scheduleEClass, SCHEDULE__END_DATE);
                createEAttribute(scheduleEClass, SCHEDULE__STP_INDICATOR);

                trainEClass = createEClass(TRAIN);
                createEReference(trainEClass, TRAIN__SCHEDULES);
                createEReference(trainEClass, TRAIN__ASSOCIATIONS);
                createEAttribute(trainEClass, TRAIN__UID);

                stationEClass = createEClass(STATION);
                createEReference(stationEClass, STATION__NEIGHBORS);
                createEAttribute(stationEClass, STATION__CODE);

                associationEClass = createEClass(ASSOCIATION);
                createEReference(associationEClass, ASSOCIATION__LOCATION);
                createEReference(associationEClass, ASSOCIATION__ASSOCIATIVE);
                createEAttribute(associationEClass, ASSOCIATION__CATEGORY);
                createEAttribute(associationEClass, ASSOCIATION__DAYS);
                createEAttribute(associationEClass, ASSOCIATION__STP_INDICATOR);
                createEAttribute(associationEClass, ASSOCIATION__START_DATE);
                createEAttribute(associationEClass, ASSOCIATION__END_DATE);

                // Create enums
                associationCategoryEEnum = createEEnum(ASSOCIATION_CATEGORY);
                statusEEnum = createEEnum(STATUS);
                schedulePlanningEEnum = createEEnum(SCHEDULE_PLANNING);
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        private boolean isInitialized = false;

        /**
         * Complete the initialization of the package and its meta-model.  This
         * method is guarded to have no affect on any invocation but its first.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public void initializePackageContents() {
                if (isInitialized) return;
                isInitialized = true;

                // Initialize package
                setName(eNAME);
                setNsPrefix(eNS_PREFIX);
                setNsURI(eNS_URI);

                // Create type parameters

                // Set bounds for type parameters

                // Add supertypes to classes

                // Initialize classes, features, and operations; add parameters
                initEClass(trainContainerEClass, TrainContainer.class, "TrainContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
                initEReference(getTrainContainer_Trains(), this.getTrain(), null, "trains", null, 1, -1, TrainContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

                initEClass(scheduleEClass, Schedule.class, "Schedule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
                initEReference(getSchedule_Destinations(), this.getStation(), null, "destinations", null, 0, -1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEAttribute(getSchedule_Status(), this.getStatus(), "status", null, 0, 1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEAttribute(getSchedule_StartDate(), ecorePackage.getEDate(), "startDate", null, 0, 1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEAttribute(getSchedule_Days(), ecorePackage.getEString(), "days", null, 0, 1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEAttribute(getSchedule_Planning(), this.getSchedulePlanning(), "planning", null, 0, 1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEReference(getSchedule_Origin(), this.getStation(), null, "origin", null, 0, 1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEReference(getSchedule_Terminal(), this.getStation(), null, "terminal", null, 0, 1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEAttribute(getSchedule_EndDate(), ecorePackage.getEDate(), "endDate", null, 0, 1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
                initEAttribute(getSchedule_StpIndicator(), ecorePackage.getEString(), "stpIndicator", null, 0, 1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

                initEClass(trainEClass, Train.class, "Train", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
                initEReference(getTrain_Schedules(), this.getSchedule(), null, "schedules", null, 1, -1, Train.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEReference(getTrain_Associations(), this.getAssociation(), null, "associations", null, 0, -1, Train.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEAttribute(getTrain_Uid(), ecorePackage.getEString(), "uid", null, 1, 1, Train.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

                initEClass(stationEClass, Station.class, "Station", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
                initEReference(getStation_Neighbors(), this.getStation(), null, "neighbors", null, 1, -1, Station.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEAttribute(getStation_Code(), ecorePackage.getEString(), "code", null, 1, 1, Station.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

                initEClass(associationEClass, Association.class, "Association", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
                initEReference(getAssociation_Location(), this.getStation(), null, "location", null, 1, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEReference(getAssociation_Associative(), this.getTrain(), null, "associative", null, 1, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEAttribute(getAssociation_Category(), this.getAssociationCategory(), "category", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEAttribute(getAssociation_Days(), ecorePackage.getEString(), "days", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEAttribute(getAssociation_StpIndicator(), ecorePackage.getEString(), "stpIndicator", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
                initEAttribute(getAssociation_StartDate(), ecorePackage.getEDate(), "startDate", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
                initEAttribute(getAssociation_EndDate(), ecorePackage.getEDate(), "endDate", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

                // Initialize enums and add enum literals
                initEEnum(associationCategoryEEnum, AssociationCategory.class, "AssociationCategory");
                addEEnumLiteral(associationCategoryEEnum, AssociationCategory.JOIN);
                addEEnumLiteral(associationCategoryEEnum, AssociationCategory.DIVIDE);
                addEEnumLiteral(associationCategoryEEnum, AssociationCategory.NEXT);

                initEEnum(statusEEnum, Status.class, "Status");
                addEEnumLiteral(statusEEnum, Status.FREIGHT);
                addEEnumLiteral(statusEEnum, Status.PASSENGER);
                addEEnumLiteral(statusEEnum, Status.SHIP);
                addEEnumLiteral(statusEEnum, Status.BUS);
                addEEnumLiteral(statusEEnum, Status.TRIP);

                initEEnum(schedulePlanningEEnum, SchedulePlanning.class, "SchedulePlanning");
                addEEnumLiteral(schedulePlanningEEnum, SchedulePlanning.PERMANENT);
                addEEnumLiteral(schedulePlanningEEnum, SchedulePlanning.SHORT_TERM);

                // Create resource
                createResource(eNS_URI);
        }

} //SchedulePackageImpl
