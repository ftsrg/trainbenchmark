/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package Concept;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see Concept.ConceptFactory
 * @model kind="package"
 * @generated
 */
public interface ConceptPackage extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "Concept";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "Concept";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	ConceptPackage eINSTANCE = Concept.impl.ConceptPackageImpl.init();

	/**
	 * The meta object id for the '{@link Concept.impl.ThingImpl <em>Thing</em>}
	 * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see Concept.impl.ThingImpl
	 * @see Concept.impl.ConceptPackageImpl#getThing()
	 * @generated
	 */
	int THING = 6;

	/**
	 * The number of structural features of the '<em>Thing</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int THING_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link Concept.impl.TrackelementImpl
	 * <em>Trackelement</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see Concept.impl.TrackelementImpl
	 * @see Concept.impl.ConceptPackageImpl#getTrackelement()
	 * @generated
	 */
	int TRACKELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Track Element sensor</b></em>' reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRACKELEMENT__TRACK_ELEMENT_SENSOR = THING_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Track Element connects To</b></em>'
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRACKELEMENT__TRACK_ELEMENT_CONNECTS_TO = THING_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Trackelement</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRACKELEMENT_FEATURE_COUNT = THING_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link Concept.impl.SegmentImpl
	 * <em>Segment</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see Concept.impl.SegmentImpl
	 * @see Concept.impl.ConceptPackageImpl#getSegment()
	 * @generated
	 */
	int SEGMENT = 0;

	/**
	 * The feature id for the '<em><b>Track Element sensor</b></em>' reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SEGMENT__TRACK_ELEMENT_SENSOR = TRACKELEMENT__TRACK_ELEMENT_SENSOR;

	/**
	 * The feature id for the '<em><b>Track Element connects To</b></em>'
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SEGMENT__TRACK_ELEMENT_CONNECTS_TO = TRACKELEMENT__TRACK_ELEMENT_CONNECTS_TO;

	/**
	 * The feature id for the '<em><b>Segment length</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SEGMENT__SEGMENT_LENGTH = TRACKELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Segment</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SEGMENT_FEATURE_COUNT = TRACKELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link Concept.impl.SwitchImpl
	 * <em>Switch</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see Concept.impl.SwitchImpl
	 * @see Concept.impl.ConceptPackageImpl#getSwitch()
	 * @generated
	 */
	int SWITCH = 2;

	/**
	 * The feature id for the '<em><b>Track Element sensor</b></em>' reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SWITCH__TRACK_ELEMENT_SENSOR = TRACKELEMENT__TRACK_ELEMENT_SENSOR;

	/**
	 * The feature id for the '<em><b>Track Element connects To</b></em>'
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SWITCH__TRACK_ELEMENT_CONNECTS_TO = TRACKELEMENT__TRACK_ELEMENT_CONNECTS_TO;

	/**
	 * The feature id for the '<em><b>Switch actual State</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SWITCH__SWITCH_ACTUAL_STATE = TRACKELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Switch switch Position</b></em>' reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SWITCH__SWITCH_SWITCH_POSITION = TRACKELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Switch</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SWITCH_FEATURE_COUNT = TRACKELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link Concept.impl.RouteImpl <em>Route</em>}
	 * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see Concept.impl.RouteImpl
	 * @see Concept.impl.ConceptPackageImpl#getRoute()
	 * @generated
	 */
	int ROUTE = 3;

	/**
	 * The feature id for the '<em><b>Route entry</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ROUTE__ROUTE_ENTRY = THING_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Route switch Position</b></em>' reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ROUTE__ROUTE_SWITCH_POSITION = THING_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Route exit</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ROUTE__ROUTE_EXIT = THING_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Route route Definition</b></em>' reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ROUTE__ROUTE_ROUTE_DEFINITION = THING_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Route</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ROUTE_FEATURE_COUNT = THING_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link Concept.impl.SignalImpl
	 * <em>Signal</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see Concept.impl.SignalImpl
	 * @see Concept.impl.ConceptPackageImpl#getSignal()
	 * @generated
	 */
	int SIGNAL = 4;

	/**
	 * The feature id for the '<em><b>Signal actual State</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SIGNAL__SIGNAL_ACTUAL_STATE = THING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Signal</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SIGNAL_FEATURE_COUNT = THING_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link Concept.impl.SwitchPositionImpl
	 * <em>Switch Position</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see Concept.impl.SwitchPositionImpl
	 * @see Concept.impl.ConceptPackageImpl#getSwitchPosition()
	 * @generated
	 */
	int SWITCH_POSITION = 5;

	/**
	 * The feature id for the '<em><b>Switch Position switch</b></em>'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SWITCH_POSITION__SWITCH_POSITION_SWITCH = THING_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Switch Position switch State</b></em>'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SWITCH_POSITION__SWITCH_POSITION_SWITCH_STATE = THING_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Switch Position route</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SWITCH_POSITION__SWITCH_POSITION_ROUTE = THING_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Switch Position</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SWITCH_POSITION_FEATURE_COUNT = THING_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link Concept.impl.SensorImpl
	 * <em>Sensor</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see Concept.impl.SensorImpl
	 * @see Concept.impl.ConceptPackageImpl#getSensor()
	 * @generated
	 */
	int SENSOR = 7;

	/**
	 * The feature id for the '<em><b>Sensor track Element</b></em>' reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SENSOR__SENSOR_TRACK_ELEMENT = THING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Sensor</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SENSOR_FEATURE_COUNT = THING_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link Concept.impl.IndividualContainerImpl
	 * <em>Individual Container</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see Concept.impl.IndividualContainerImpl
	 * @see Concept.impl.ConceptPackageImpl#getIndividualContainer()
	 * @generated
	 */
	int INDIVIDUAL_CONTAINER = 8;

	/**
	 * The feature id for the '<em><b>Contains</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INDIVIDUAL_CONTAINER__CONTAINS = 0;

	/**
	 * The number of structural features of the '<em>Individual Container</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INDIVIDUAL_CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link Concept.SignalStateKind
	 * <em>Signal State Kind</em>}' enum. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see Concept.SignalStateKind
	 * @see Concept.impl.ConceptPackageImpl#getSignalStateKind()
	 * @generated
	 */
	int SIGNAL_STATE_KIND = 9;

	/**
	 * The meta object id for the '{@link Concept.SwitchStateKind
	 * <em>Switch State Kind</em>}' enum. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see Concept.SwitchStateKind
	 * @see Concept.impl.ConceptPackageImpl#getSwitchStateKind()
	 * @generated
	 */
	int SWITCH_STATE_KIND = 10;

	/**
	 * Returns the meta object for class '{@link Concept.Segment
	 * <em>Segment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Segment</em>'.
	 * @see Concept.Segment
	 * @generated
	 */
	EClass getSegment();

	/**
	 * Returns the meta object for the attribute '
	 * {@link Concept.Segment#getSegment_length <em>Segment length</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Segment length</em>'.
	 * @see Concept.Segment#getSegment_length()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_Segment_length();

	/**
	 * Returns the meta object for class '{@link Concept.Trackelement
	 * <em>Trackelement</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Trackelement</em>'.
	 * @see Concept.Trackelement
	 * @generated
	 */
	EClass getTrackelement();

	/**
	 * Returns the meta object for the reference list '
	 * {@link Concept.Trackelement#getTrackElement_sensor
	 * <em>Track Element sensor</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the reference list '
	 *         <em>Track Element sensor</em>'.
	 * @see Concept.Trackelement#getTrackElement_sensor()
	 * @see #getTrackelement()
	 * @generated
	 */
	EReference getTrackelement_TrackElement_sensor();

	/**
	 * Returns the meta object for the reference list '
	 * {@link Concept.Trackelement#getTrackElement_connectsTo
	 * <em>Track Element connects To</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the reference list '
	 *         <em>Track Element connects To</em>'.
	 * @see Concept.Trackelement#getTrackElement_connectsTo()
	 * @see #getTrackelement()
	 * @generated
	 */
	EReference getTrackelement_TrackElement_connectsTo();

	/**
	 * Returns the meta object for class '{@link Concept.Switch <em>Switch</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Switch</em>'.
	 * @see Concept.Switch
	 * @generated
	 */
	EClass getSwitch();

	/**
	 * Returns the meta object for the attribute '
	 * {@link Concept.Switch#getSwitch_actualState <em>Switch actual State</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Switch actual State</em>'.
	 * @see Concept.Switch#getSwitch_actualState()
	 * @see #getSwitch()
	 * @generated
	 */
	EAttribute getSwitch_Switch_actualState();

	/**
	 * Returns the meta object for the reference list '
	 * {@link Concept.Switch#getSwitch_switchPosition
	 * <em>Switch switch Position</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the reference list '
	 *         <em>Switch switch Position</em>'.
	 * @see Concept.Switch#getSwitch_switchPosition()
	 * @see #getSwitch()
	 * @generated
	 */
	EReference getSwitch_Switch_switchPosition();

	/**
	 * Returns the meta object for class '{@link Concept.Route <em>Route</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Route</em>'.
	 * @see Concept.Route
	 * @generated
	 */
	EClass getRoute();

	/**
	 * Returns the meta object for the reference '
	 * {@link Concept.Route#getRoute_entry <em>Route entry</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Route entry</em>'.
	 * @see Concept.Route#getRoute_entry()
	 * @see #getRoute()
	 * @generated
	 */
	EReference getRoute_Route_entry();

	/**
	 * Returns the meta object for the reference list '
	 * {@link Concept.Route#getRoute_switchPosition
	 * <em>Route switch Position</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the reference list '
	 *         <em>Route switch Position</em>'.
	 * @see Concept.Route#getRoute_switchPosition()
	 * @see #getRoute()
	 * @generated
	 */
	EReference getRoute_Route_switchPosition();

	/**
	 * Returns the meta object for the reference '
	 * {@link Concept.Route#getRoute_exit <em>Route exit</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Route exit</em>'.
	 * @see Concept.Route#getRoute_exit()
	 * @see #getRoute()
	 * @generated
	 */
	EReference getRoute_Route_exit();

	/**
	 * Returns the meta object for the reference list '
	 * {@link Concept.Route#getRoute_routeDefinition
	 * <em>Route route Definition</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the reference list '
	 *         <em>Route route Definition</em>'.
	 * @see Concept.Route#getRoute_routeDefinition()
	 * @see #getRoute()
	 * @generated
	 */
	EReference getRoute_Route_routeDefinition();

	/**
	 * Returns the meta object for class '{@link Concept.Signal <em>Signal</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Signal</em>'.
	 * @see Concept.Signal
	 * @generated
	 */
	EClass getSignal();

	/**
	 * Returns the meta object for the attribute '
	 * {@link Concept.Signal#getSignal_actualState <em>Signal actual State</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Signal actual State</em>'.
	 * @see Concept.Signal#getSignal_actualState()
	 * @see #getSignal()
	 * @generated
	 */
	EAttribute getSignal_Signal_actualState();

	/**
	 * Returns the meta object for class '{@link Concept.SwitchPosition
	 * <em>Switch Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Switch Position</em>'.
	 * @see Concept.SwitchPosition
	 * @generated
	 */
	EClass getSwitchPosition();

	/**
	 * Returns the meta object for the reference '
	 * {@link Concept.SwitchPosition#getSwitchPosition_switch
	 * <em>Switch Position switch</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the reference '
	 *         <em>Switch Position switch</em>'.
	 * @see Concept.SwitchPosition#getSwitchPosition_switch()
	 * @see #getSwitchPosition()
	 * @generated
	 */
	EReference getSwitchPosition_SwitchPosition_switch();

	/**
	 * Returns the meta object for the attribute '
	 * {@link Concept.SwitchPosition#getSwitchPosition_switchState
	 * <em>Switch Position switch State</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the attribute '
	 *         <em>Switch Position switch State</em>'.
	 * @see Concept.SwitchPosition#getSwitchPosition_switchState()
	 * @see #getSwitchPosition()
	 * @generated
	 */
	EAttribute getSwitchPosition_SwitchPosition_switchState();

	/**
	 * Returns the meta object for the reference '
	 * {@link Concept.SwitchPosition#getSwitchPosition_route
	 * <em>Switch Position route</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Switch Position route</em>
	 *         '.
	 * @see Concept.SwitchPosition#getSwitchPosition_route()
	 * @see #getSwitchPosition()
	 * @generated
	 */
	EReference getSwitchPosition_SwitchPosition_route();

	/**
	 * Returns the meta object for class '{@link Concept.Thing <em>Thing</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Thing</em>'.
	 * @see Concept.Thing
	 * @generated
	 */
	EClass getThing();

	/**
	 * Returns the meta object for class '{@link Concept.Sensor <em>Sensor</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Sensor</em>'.
	 * @see Concept.Sensor
	 * @generated
	 */
	EClass getSensor();

	/**
	 * Returns the meta object for the reference list '
	 * {@link Concept.Sensor#getSensor_trackElement
	 * <em>Sensor track Element</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the reference list '
	 *         <em>Sensor track Element</em>'.
	 * @see Concept.Sensor#getSensor_trackElement()
	 * @see #getSensor()
	 * @generated
	 */
	EReference getSensor_Sensor_trackElement();

	/**
	 * Returns the meta object for class '{@link Concept.IndividualContainer
	 * <em>Individual Container</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Individual Container</em>'.
	 * @see Concept.IndividualContainer
	 * @generated
	 */
	EClass getIndividualContainer();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link Concept.IndividualContainer#getContains <em>Contains</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Contains</em>'.
	 * @see Concept.IndividualContainer#getContains()
	 * @see #getIndividualContainer()
	 * @generated
	 */
	EReference getIndividualContainer_Contains();

	/**
	 * Returns the meta object for enum '{@link Concept.SignalStateKind
	 * <em>Signal State Kind</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for enum '<em>Signal State Kind</em>'.
	 * @see Concept.SignalStateKind
	 * @generated
	 */
	EEnum getSignalStateKind();

	/**
	 * Returns the meta object for enum '{@link Concept.SwitchStateKind
	 * <em>Switch State Kind</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for enum '<em>Switch State Kind</em>'.
	 * @see Concept.SwitchStateKind
	 * @generated
	 */
	EEnum getSwitchStateKind();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ConceptFactory getConceptFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that
	 * represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link Concept.impl.SegmentImpl
		 * <em>Segment</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see Concept.impl.SegmentImpl
		 * @see Concept.impl.ConceptPackageImpl#getSegment()
		 * @generated
		 */
		EClass SEGMENT = eINSTANCE.getSegment();

		/**
		 * The meta object literal for the '<em><b>Segment length</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SEGMENT__SEGMENT_LENGTH = eINSTANCE.getSegment_Segment_length();

		/**
		 * The meta object literal for the '
		 * {@link Concept.impl.TrackelementImpl <em>Trackelement</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see Concept.impl.TrackelementImpl
		 * @see Concept.impl.ConceptPackageImpl#getTrackelement()
		 * @generated
		 */
		EClass TRACKELEMENT = eINSTANCE.getTrackelement();

		/**
		 * The meta object literal for the '<em><b>Track Element sensor</b></em>
		 * ' reference list feature. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @generated
		 */
		EReference TRACKELEMENT__TRACK_ELEMENT_SENSOR = eINSTANCE.getTrackelement_TrackElement_sensor();

		/**
		 * The meta object literal for the '
		 * <em><b>Track Element connects To</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference TRACKELEMENT__TRACK_ELEMENT_CONNECTS_TO = eINSTANCE.getTrackelement_TrackElement_connectsTo();

		/**
		 * The meta object literal for the '{@link Concept.impl.SwitchImpl
		 * <em>Switch</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see Concept.impl.SwitchImpl
		 * @see Concept.impl.ConceptPackageImpl#getSwitch()
		 * @generated
		 */
		EClass SWITCH = eINSTANCE.getSwitch();

		/**
		 * The meta object literal for the '<em><b>Switch actual State</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SWITCH__SWITCH_ACTUAL_STATE = eINSTANCE.getSwitch_Switch_actualState();

		/**
		 * The meta object literal for the '
		 * <em><b>Switch switch Position</b></em>' reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SWITCH__SWITCH_SWITCH_POSITION = eINSTANCE.getSwitch_Switch_switchPosition();

		/**
		 * The meta object literal for the '{@link Concept.impl.RouteImpl
		 * <em>Route</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see Concept.impl.RouteImpl
		 * @see Concept.impl.ConceptPackageImpl#getRoute()
		 * @generated
		 */
		EClass ROUTE = eINSTANCE.getRoute();

		/**
		 * The meta object literal for the '<em><b>Route entry</b></em>'
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ROUTE__ROUTE_ENTRY = eINSTANCE.getRoute_Route_entry();

		/**
		 * The meta object literal for the '
		 * <em><b>Route switch Position</b></em>' reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ROUTE__ROUTE_SWITCH_POSITION = eINSTANCE.getRoute_Route_switchPosition();

		/**
		 * The meta object literal for the '<em><b>Route exit</b></em>'
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ROUTE__ROUTE_EXIT = eINSTANCE.getRoute_Route_exit();

		/**
		 * The meta object literal for the '
		 * <em><b>Route route Definition</b></em>' reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference ROUTE__ROUTE_ROUTE_DEFINITION = eINSTANCE.getRoute_Route_routeDefinition();

		/**
		 * The meta object literal for the '{@link Concept.impl.SignalImpl
		 * <em>Signal</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see Concept.impl.SignalImpl
		 * @see Concept.impl.ConceptPackageImpl#getSignal()
		 * @generated
		 */
		EClass SIGNAL = eINSTANCE.getSignal();

		/**
		 * The meta object literal for the '<em><b>Signal actual State</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SIGNAL__SIGNAL_ACTUAL_STATE = eINSTANCE.getSignal_Signal_actualState();

		/**
		 * The meta object literal for the '
		 * {@link Concept.impl.SwitchPositionImpl <em>Switch Position</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see Concept.impl.SwitchPositionImpl
		 * @see Concept.impl.ConceptPackageImpl#getSwitchPosition()
		 * @generated
		 */
		EClass SWITCH_POSITION = eINSTANCE.getSwitchPosition();

		/**
		 * The meta object literal for the '
		 * <em><b>Switch Position switch</b></em>' reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SWITCH_POSITION__SWITCH_POSITION_SWITCH = eINSTANCE.getSwitchPosition_SwitchPosition_switch();

		/**
		 * The meta object literal for the '
		 * <em><b>Switch Position switch State</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SWITCH_POSITION__SWITCH_POSITION_SWITCH_STATE = eINSTANCE.getSwitchPosition_SwitchPosition_switchState();

		/**
		 * The meta object literal for the '
		 * <em><b>Switch Position route</b></em>' reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SWITCH_POSITION__SWITCH_POSITION_ROUTE = eINSTANCE.getSwitchPosition_SwitchPosition_route();

		/**
		 * The meta object literal for the '{@link Concept.impl.ThingImpl
		 * <em>Thing</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see Concept.impl.ThingImpl
		 * @see Concept.impl.ConceptPackageImpl#getThing()
		 * @generated
		 */
		EClass THING = eINSTANCE.getThing();

		/**
		 * The meta object literal for the '{@link Concept.impl.SensorImpl
		 * <em>Sensor</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see Concept.impl.SensorImpl
		 * @see Concept.impl.ConceptPackageImpl#getSensor()
		 * @generated
		 */
		EClass SENSOR = eINSTANCE.getSensor();

		/**
		 * The meta object literal for the '<em><b>Sensor track Element</b></em>
		 * ' reference list feature. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @generated
		 */
		EReference SENSOR__SENSOR_TRACK_ELEMENT = eINSTANCE.getSensor_Sensor_trackElement();

		/**
		 * The meta object literal for the '
		 * {@link Concept.impl.IndividualContainerImpl
		 * <em>Individual Container</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see Concept.impl.IndividualContainerImpl
		 * @see Concept.impl.ConceptPackageImpl#getIndividualContainer()
		 * @generated
		 */
		EClass INDIVIDUAL_CONTAINER = eINSTANCE.getIndividualContainer();

		/**
		 * The meta object literal for the '<em><b>Contains</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INDIVIDUAL_CONTAINER__CONTAINS = eINSTANCE.getIndividualContainer_Contains();

		/**
		 * The meta object literal for the '{@link Concept.SignalStateKind
		 * <em>Signal State Kind</em>}' enum. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see Concept.SignalStateKind
		 * @see Concept.impl.ConceptPackageImpl#getSignalStateKind()
		 * @generated
		 */
		EEnum SIGNAL_STATE_KIND = eINSTANCE.getSignalStateKind();

		/**
		 * The meta object literal for the '{@link Concept.SwitchStateKind
		 * <em>Switch State Kind</em>}' enum. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see Concept.SwitchStateKind
		 * @see Concept.impl.ConceptPackageImpl#getSwitchStateKind()
		 * @generated
		 */
		EEnum SWITCH_STATE_KIND = eINSTANCE.getSwitchStateKind();

	}

} // ConceptPackage
