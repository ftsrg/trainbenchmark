/**
 */
package hu.bme.mit.trainbenchmark.schedule.impl;

import hu.bme.mit.trainbenchmark.schedule.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScheduleFactoryImpl extends EFactoryImpl implements ScheduleFactory {
        /**
         * Creates the default factory implementation.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public static ScheduleFactory init() {
                try {
                        ScheduleFactory theScheduleFactory = (ScheduleFactory)EPackage.Registry.INSTANCE.getEFactory(SchedulePackage.eNS_URI);
                        if (theScheduleFactory != null) {
                                return theScheduleFactory;
                        }
                }
                catch (Exception exception) {
                        EcorePlugin.INSTANCE.log(exception);
                }
                return new ScheduleFactoryImpl();
        }

        /**
         * Creates an instance of the factory.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public ScheduleFactoryImpl() {
                super();
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        public EObject create(EClass eClass) {
                switch (eClass.getClassifierID()) {
                        case SchedulePackage.TRAIN_CONTAINER: return createTrainContainer();
                        case SchedulePackage.SCHEDULE: return createSchedule();
                        case SchedulePackage.TRAIN: return createTrain();
                        case SchedulePackage.STATION: return createStation();
                        case SchedulePackage.ASSOCIATION: return createAssociation();
                        default:
                                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
                }
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        public Object createFromString(EDataType eDataType, String initialValue) {
                switch (eDataType.getClassifierID()) {
                        case SchedulePackage.ASSOCIATION_CATEGORY:
                                return createAssociationCategoryFromString(eDataType, initialValue);
                        case SchedulePackage.STATUS:
                                return createStatusFromString(eDataType, initialValue);
                        case SchedulePackage.SCHEDULE_PLANNING:
                                return createSchedulePlanningFromString(eDataType, initialValue);
                        default:
                                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
                }
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @Override
        public String convertToString(EDataType eDataType, Object instanceValue) {
                switch (eDataType.getClassifierID()) {
                        case SchedulePackage.ASSOCIATION_CATEGORY:
                                return convertAssociationCategoryToString(eDataType, instanceValue);
                        case SchedulePackage.STATUS:
                                return convertStatusToString(eDataType, instanceValue);
                        case SchedulePackage.SCHEDULE_PLANNING:
                                return convertSchedulePlanningToString(eDataType, instanceValue);
                        default:
                                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
                }
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public TrainContainer createTrainContainer() {
                TrainContainerImpl trainContainer = new TrainContainerImpl();
                return trainContainer;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Schedule createSchedule() {
                ScheduleImpl schedule = new ScheduleImpl();
                return schedule;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Train createTrain() {
                TrainImpl train = new TrainImpl();
                return train;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Station createStation() {
                StationImpl station = new StationImpl();
                return station;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Association createAssociation() {
                AssociationImpl association = new AssociationImpl();
                return association;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public AssociationCategory createAssociationCategoryFromString(EDataType eDataType, String initialValue) {
                AssociationCategory result = AssociationCategory.get(initialValue);
                if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
                return result;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public String convertAssociationCategoryToString(EDataType eDataType, Object instanceValue) {
                return instanceValue == null ? null : instanceValue.toString();
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Status createStatusFromString(EDataType eDataType, String initialValue) {
                Status result = Status.get(initialValue);
                if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
                return result;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public String convertStatusToString(EDataType eDataType, Object instanceValue) {
                return instanceValue == null ? null : instanceValue.toString();
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public SchedulePlanning createSchedulePlanningFromString(EDataType eDataType, String initialValue) {
                SchedulePlanning result = SchedulePlanning.get(initialValue);
                if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
                return result;
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public String convertSchedulePlanningToString(EDataType eDataType, Object instanceValue) {
                return instanceValue == null ? null : instanceValue.toString();
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public SchedulePackage getSchedulePackage() {
                return (SchedulePackage)getEPackage();
        }

        /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @deprecated
         * @generated
         */
        @Deprecated
        public static SchedulePackage getPackage() {
                return SchedulePackage.eINSTANCE;
        }

} //ScheduleFactoryImpl
