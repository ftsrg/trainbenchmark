/**
 */
package Concept.impl;

import Concept.ConceptFactory;
import Concept.ConceptPackage;
import Concept.IndividualContainer;
import Concept.Route;
import Concept.Segment;
import Concept.Sensor;
import Concept.Signal;
import Concept.SignalStateKind;
import Concept.Switch;
import Concept.SwitchPosition;
import Concept.SwitchStateKind;
import Concept.Thing;
import Concept.TrackElement;

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
public class ConceptPackageImpl extends EPackageImpl implements ConceptPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass segmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass trackElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass switchEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass routeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass signalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass switchPositionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass thingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sensorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass individualContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum signalStateKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum switchStateKindEEnum = null;

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
	 * @see Concept.ConceptPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ConceptPackageImpl() {
		super(eNS_URI, ConceptFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ConceptPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ConceptPackage init() {
		if (isInited) return (ConceptPackage)EPackage.Registry.INSTANCE.getEPackage(ConceptPackage.eNS_URI);

		// Obtain or create and register package
		ConceptPackageImpl theConceptPackage = (ConceptPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ConceptPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ConceptPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theConceptPackage.createPackageContents();

		// Initialize created meta-data
		theConceptPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theConceptPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ConceptPackage.eNS_URI, theConceptPackage);
		return theConceptPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSegment() {
		return segmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSegment_Segment_length() {
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTrackElement() {
		return trackElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrackElement_TrackElement_sensor() {
		return (EReference)trackElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrackElement_TrackElement_connectsTo() {
		return (EReference)trackElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSwitch() {
		return switchEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwitch_Switch_actualState() {
		return (EAttribute)switchEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwitch_Switch_switchPosition() {
		return (EReference)switchEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoute() {
		return routeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoute_Route_entry() {
		return (EReference)routeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoute_Route_switchPosition() {
		return (EReference)routeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoute_Route_exit() {
		return (EReference)routeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoute_Route_routeDefinition() {
		return (EReference)routeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSignal() {
		return signalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSignal_Signal_actualState() {
		return (EAttribute)signalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSwitchPosition() {
		return switchPositionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwitchPosition_SwitchPosition_switch() {
		return (EReference)switchPositionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwitchPosition_SwitchPosition_switchState() {
		return (EAttribute)switchPositionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwitchPosition_SwitchPosition_route() {
		return (EReference)switchPositionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThing() {
		return thingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSensor() {
		return sensorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSensor_Sensor_trackElement() {
		return (EReference)sensorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIndividualContainer() {
		return individualContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIndividualContainer_Contains() {
		return (EReference)individualContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSignalStateKind() {
		return signalStateKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getSwitchStateKind() {
		return switchStateKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConceptFactory getConceptFactory() {
		return (ConceptFactory)getEFactoryInstance();
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
		segmentEClass = createEClass(SEGMENT);
		createEAttribute(segmentEClass, SEGMENT__SEGMENT_LENGTH);

		trackElementEClass = createEClass(TRACK_ELEMENT);
		createEReference(trackElementEClass, TRACK_ELEMENT__TRACK_ELEMENT_SENSOR);
		createEReference(trackElementEClass, TRACK_ELEMENT__TRACK_ELEMENT_CONNECTS_TO);

		switchEClass = createEClass(SWITCH);
		createEAttribute(switchEClass, SWITCH__SWITCH_ACTUAL_STATE);
		createEReference(switchEClass, SWITCH__SWITCH_SWITCH_POSITION);

		routeEClass = createEClass(ROUTE);
		createEReference(routeEClass, ROUTE__ROUTE_ENTRY);
		createEReference(routeEClass, ROUTE__ROUTE_SWITCH_POSITION);
		createEReference(routeEClass, ROUTE__ROUTE_EXIT);
		createEReference(routeEClass, ROUTE__ROUTE_ROUTE_DEFINITION);

		signalEClass = createEClass(SIGNAL);
		createEAttribute(signalEClass, SIGNAL__SIGNAL_ACTUAL_STATE);

		switchPositionEClass = createEClass(SWITCH_POSITION);
		createEReference(switchPositionEClass, SWITCH_POSITION__SWITCH_POSITION_SWITCH);
		createEAttribute(switchPositionEClass, SWITCH_POSITION__SWITCH_POSITION_SWITCH_STATE);
		createEReference(switchPositionEClass, SWITCH_POSITION__SWITCH_POSITION_ROUTE);

		thingEClass = createEClass(THING);

		sensorEClass = createEClass(SENSOR);
		createEReference(sensorEClass, SENSOR__SENSOR_TRACK_ELEMENT);

		individualContainerEClass = createEClass(INDIVIDUAL_CONTAINER);
		createEReference(individualContainerEClass, INDIVIDUAL_CONTAINER__CONTAINS);

		// Create enums
		signalStateKindEEnum = createEEnum(SIGNAL_STATE_KIND);
		switchStateKindEEnum = createEEnum(SWITCH_STATE_KIND);
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
		segmentEClass.getESuperTypes().add(this.getTrackElement());
		trackElementEClass.getESuperTypes().add(this.getThing());
		switchEClass.getESuperTypes().add(this.getTrackElement());
		routeEClass.getESuperTypes().add(this.getThing());
		signalEClass.getESuperTypes().add(this.getThing());
		switchPositionEClass.getESuperTypes().add(this.getThing());
		sensorEClass.getESuperTypes().add(this.getThing());

		// Initialize classes and features; add operations and parameters
		initEClass(segmentEClass, Segment.class, "Segment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSegment_Segment_length(), ecorePackage.getEInt(), "Segment_length", null, 1, 1, Segment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(trackElementEClass, TrackElement.class, "TrackElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTrackElement_TrackElement_sensor(), this.getSensor(), this.getSensor_Sensor_trackElement(), "TrackElement_sensor", null, 0, -1, TrackElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTrackElement_TrackElement_connectsTo(), this.getTrackElement(), null, "TrackElement_connectsTo", null, 0, -1, TrackElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(switchEClass, Switch.class, "Switch", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSwitch_Switch_actualState(), this.getSwitchStateKind(), "Switch_actualState", null, 1, 1, Switch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSwitch_Switch_switchPosition(), this.getSwitchPosition(), this.getSwitchPosition_SwitchPosition_switch(), "Switch_switchPosition", null, 0, -1, Switch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(routeEClass, Route.class, "Route", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRoute_Route_entry(), this.getSignal(), null, "Route_entry", null, 1, 1, Route.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRoute_Route_switchPosition(), this.getSwitchPosition(), this.getSwitchPosition_SwitchPosition_route(), "Route_switchPosition", null, 0, -1, Route.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRoute_Route_exit(), this.getSignal(), null, "Route_exit", null, 1, 1, Route.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRoute_Route_routeDefinition(), this.getSensor(), null, "Route_routeDefinition", null, 2, -1, Route.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(signalEClass, Signal.class, "Signal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSignal_Signal_actualState(), this.getSignalStateKind(), "Signal_actualState", null, 1, 1, Signal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(switchPositionEClass, SwitchPosition.class, "SwitchPosition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSwitchPosition_SwitchPosition_switch(), this.getSwitch(), this.getSwitch_Switch_switchPosition(), "SwitchPosition_switch", null, 1, 1, SwitchPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSwitchPosition_SwitchPosition_switchState(), this.getSwitchStateKind(), "SwitchPosition_switchState", null, 1, 1, SwitchPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSwitchPosition_SwitchPosition_route(), this.getRoute(), this.getRoute_Route_switchPosition(), "SwitchPosition_route", null, 1, 1, SwitchPosition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(thingEClass, Thing.class, "Thing", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sensorEClass, Sensor.class, "Sensor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSensor_Sensor_trackElement(), this.getTrackElement(), this.getTrackElement_TrackElement_sensor(), "Sensor_trackElement", null, 0, -1, Sensor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(individualContainerEClass, IndividualContainer.class, "IndividualContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIndividualContainer_Contains(), this.getThing(), null, "contains", null, 0, -1, IndividualContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(signalStateKindEEnum, SignalStateKind.class, "SignalStateKind");
		addEEnumLiteral(signalStateKindEEnum, SignalStateKind.SIGNAL_STATE_KIND_STOP);
		addEEnumLiteral(signalStateKindEEnum, SignalStateKind.SIGNAL_STATE_KIND_FAILURE);
		addEEnumLiteral(signalStateKindEEnum, SignalStateKind.SIGNAL_STATE_KIND_GO);

		initEEnum(switchStateKindEEnum, SwitchStateKind.class, "SwitchStateKind");
		addEEnumLiteral(switchStateKindEEnum, SwitchStateKind.POINT_STATE_KIND_FAILURE);
		addEEnumLiteral(switchStateKindEEnum, SwitchStateKind.POINT_STATE_KIND_LEFT);
		addEEnumLiteral(switchStateKindEEnum, SwitchStateKind.POINT_STATE_KIND_RIGHT);
		addEEnumLiteral(switchStateKindEEnum, SwitchStateKind.POINT_STATE_KIND_STRAIGHT);

		// Create resource
		createResource(eNS_URI);
	}

} //ConceptPackageImpl
