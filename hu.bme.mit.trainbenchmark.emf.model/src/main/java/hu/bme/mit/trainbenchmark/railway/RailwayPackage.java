/**
 */
package hu.bme.mit.trainbenchmark.railway;

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
 * @see hu.bme.mit.trainbenchmark.railway.RailwayFactory
 * @model kind="package"
 * @generated
 */
public interface RailwayPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "railway";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl";

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
	RailwayPackage eINSTANCE = hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl.init();

	/**
	 * The meta object id for the '{@link hu.bme.mit.trainbenchmark.railway.impl.RailwayElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayElementImpl
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getRailwayElement()
	 * @generated
	 */
	int RAILWAY_ELEMENT = 6;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RAILWAY_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RAILWAY_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.trainbenchmark.railway.impl.TrackElementImpl <em>Track Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.trainbenchmark.railway.impl.TrackElementImpl
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getTrackElement()
	 * @generated
	 */
	int TRACK_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Track Element sensor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_ELEMENT__TRACK_ELEMENT_SENSOR = RAILWAY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Track Element connects To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_ELEMENT__TRACK_ELEMENT_CONNECTS_TO = RAILWAY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Track Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_ELEMENT_FEATURE_COUNT = RAILWAY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Track Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_ELEMENT_OPERATION_COUNT = RAILWAY_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.trainbenchmark.railway.impl.SegmentImpl <em>Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.trainbenchmark.railway.impl.SegmentImpl
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSegment()
	 * @generated
	 */
	int SEGMENT = 0;

	/**
	 * The feature id for the '<em><b>Track Element sensor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__TRACK_ELEMENT_SENSOR = TRACK_ELEMENT__TRACK_ELEMENT_SENSOR;

	/**
	 * The feature id for the '<em><b>Track Element connects To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__TRACK_ELEMENT_CONNECTS_TO = TRACK_ELEMENT__TRACK_ELEMENT_CONNECTS_TO;

	/**
	 * The feature id for the '<em><b>Segment length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__SEGMENT_LENGTH = TRACK_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT_FEATURE_COUNT = TRACK_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT_OPERATION_COUNT = TRACK_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.trainbenchmark.railway.impl.SwitchImpl <em>Switch</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.trainbenchmark.railway.impl.SwitchImpl
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSwitch()
	 * @generated
	 */
	int SWITCH = 2;

	/**
	 * The feature id for the '<em><b>Track Element sensor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__TRACK_ELEMENT_SENSOR = TRACK_ELEMENT__TRACK_ELEMENT_SENSOR;

	/**
	 * The feature id for the '<em><b>Track Element connects To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__TRACK_ELEMENT_CONNECTS_TO = TRACK_ELEMENT__TRACK_ELEMENT_CONNECTS_TO;

	/**
	 * The feature id for the '<em><b>Switch actual State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__SWITCH_ACTUAL_STATE = TRACK_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Switch switch Position</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__SWITCH_SWITCH_POSITION = TRACK_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Switch</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_FEATURE_COUNT = TRACK_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Switch</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_OPERATION_COUNT = TRACK_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.trainbenchmark.railway.impl.RouteImpl <em>Route</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RouteImpl
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getRoute()
	 * @generated
	 */
	int ROUTE = 3;

	/**
	 * The feature id for the '<em><b>Route entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTE__ROUTE_ENTRY = RAILWAY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Route switch Position</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTE__ROUTE_SWITCH_POSITION = RAILWAY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Route exit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTE__ROUTE_EXIT = RAILWAY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Route route Definition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTE__ROUTE_ROUTE_DEFINITION = RAILWAY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Route</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTE_FEATURE_COUNT = RAILWAY_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Route</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTE_OPERATION_COUNT = RAILWAY_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.trainbenchmark.railway.impl.SignalImpl <em>Signal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.trainbenchmark.railway.impl.SignalImpl
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSignal()
	 * @generated
	 */
	int SIGNAL = 4;

	/**
	 * The feature id for the '<em><b>Signal actual State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL__SIGNAL_ACTUAL_STATE = RAILWAY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Signal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL_FEATURE_COUNT = RAILWAY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Signal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNAL_OPERATION_COUNT = RAILWAY_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.trainbenchmark.railway.impl.SwitchPositionImpl <em>Switch Position</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.trainbenchmark.railway.impl.SwitchPositionImpl
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSwitchPosition()
	 * @generated
	 */
	int SWITCH_POSITION = 5;

	/**
	 * The feature id for the '<em><b>Switch Position switch</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_POSITION__SWITCH_POSITION_SWITCH = RAILWAY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Switch Position switch State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_POSITION__SWITCH_POSITION_SWITCH_STATE = RAILWAY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Switch Position route</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_POSITION__SWITCH_POSITION_ROUTE = RAILWAY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Switch Position</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_POSITION_FEATURE_COUNT = RAILWAY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Switch Position</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_POSITION_OPERATION_COUNT = RAILWAY_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.trainbenchmark.railway.impl.SensorImpl <em>Sensor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.trainbenchmark.railway.impl.SensorImpl
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSensor()
	 * @generated
	 */
	int SENSOR = 7;

	/**
	 * The feature id for the '<em><b>Sensor track Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__SENSOR_TRACK_ELEMENT = RAILWAY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Sensor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR_FEATURE_COUNT = RAILWAY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Sensor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR_OPERATION_COUNT = RAILWAY_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.trainbenchmark.railway.impl.RailwayContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayContainerImpl
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getRailwayContainer()
	 * @generated
	 */
	int RAILWAY_CONTAINER = 8;

	/**
	 * The feature id for the '<em><b>Contains</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RAILWAY_CONTAINER__CONTAINS = 0;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RAILWAY_CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RAILWAY_CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.trainbenchmark.railway.SignalStateKind <em>Signal State Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.trainbenchmark.railway.SignalStateKind
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSignalStateKind()
	 * @generated
	 */
	int SIGNAL_STATE_KIND = 9;

	/**
	 * The meta object id for the '{@link hu.bme.mit.trainbenchmark.railway.SwitchStateKind <em>Switch State Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.trainbenchmark.railway.SwitchStateKind
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSwitchStateKind()
	 * @generated
	 */
	int SWITCH_STATE_KIND = 10;


	/**
	 * Returns the meta object for class '{@link hu.bme.mit.trainbenchmark.railway.Segment <em>Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Segment</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Segment
	 * @generated
	 */
	EClass getSegment();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.railway.Segment#getSegment_length <em>Segment length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Segment length</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Segment#getSegment_length()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_Segment_length();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.trainbenchmark.railway.TrackElement <em>Track Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Track Element</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.TrackElement
	 * @generated
	 */
	EClass getTrackElement();

	/**
	 * Returns the meta object for the reference list '{@link hu.bme.mit.trainbenchmark.railway.TrackElement#getTrackElement_sensor <em>Track Element sensor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Track Element sensor</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.TrackElement#getTrackElement_sensor()
	 * @see #getTrackElement()
	 * @generated
	 */
	EReference getTrackElement_TrackElement_sensor();

	/**
	 * Returns the meta object for the reference list '{@link hu.bme.mit.trainbenchmark.railway.TrackElement#getTrackElement_connectsTo <em>Track Element connects To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Track Element connects To</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.TrackElement#getTrackElement_connectsTo()
	 * @see #getTrackElement()
	 * @generated
	 */
	EReference getTrackElement_TrackElement_connectsTo();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.trainbenchmark.railway.Switch <em>Switch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Switch</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Switch
	 * @generated
	 */
	EClass getSwitch();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.railway.Switch#getSwitch_actualState <em>Switch actual State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Switch actual State</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Switch#getSwitch_actualState()
	 * @see #getSwitch()
	 * @generated
	 */
	EAttribute getSwitch_Switch_actualState();

	/**
	 * Returns the meta object for the reference list '{@link hu.bme.mit.trainbenchmark.railway.Switch#getSwitch_switchPosition <em>Switch switch Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Switch switch Position</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Switch#getSwitch_switchPosition()
	 * @see #getSwitch()
	 * @generated
	 */
	EReference getSwitch_Switch_switchPosition();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.trainbenchmark.railway.Route <em>Route</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Route</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Route
	 * @generated
	 */
	EClass getRoute();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.trainbenchmark.railway.Route#getRoute_entry <em>Route entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Route entry</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Route#getRoute_entry()
	 * @see #getRoute()
	 * @generated
	 */
	EReference getRoute_Route_entry();

	/**
	 * Returns the meta object for the reference list '{@link hu.bme.mit.trainbenchmark.railway.Route#getRoute_switchPosition <em>Route switch Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Route switch Position</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Route#getRoute_switchPosition()
	 * @see #getRoute()
	 * @generated
	 */
	EReference getRoute_Route_switchPosition();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.trainbenchmark.railway.Route#getRoute_exit <em>Route exit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Route exit</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Route#getRoute_exit()
	 * @see #getRoute()
	 * @generated
	 */
	EReference getRoute_Route_exit();

	/**
	 * Returns the meta object for the reference list '{@link hu.bme.mit.trainbenchmark.railway.Route#getRoute_routeDefinition <em>Route route Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Route route Definition</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Route#getRoute_routeDefinition()
	 * @see #getRoute()
	 * @generated
	 */
	EReference getRoute_Route_routeDefinition();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.trainbenchmark.railway.Signal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Signal</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Signal
	 * @generated
	 */
	EClass getSignal();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.railway.Signal#getSignal_actualState <em>Signal actual State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signal actual State</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Signal#getSignal_actualState()
	 * @see #getSignal()
	 * @generated
	 */
	EAttribute getSignal_Signal_actualState();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.trainbenchmark.railway.SwitchPosition <em>Switch Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Switch Position</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.SwitchPosition
	 * @generated
	 */
	EClass getSwitchPosition();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitchPosition_switch <em>Switch Position switch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Switch Position switch</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitchPosition_switch()
	 * @see #getSwitchPosition()
	 * @generated
	 */
	EReference getSwitchPosition_SwitchPosition_switch();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitchPosition_switchState <em>Switch Position switch State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Switch Position switch State</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitchPosition_switchState()
	 * @see #getSwitchPosition()
	 * @generated
	 */
	EAttribute getSwitchPosition_SwitchPosition_switchState();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitchPosition_route <em>Switch Position route</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Switch Position route</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.SwitchPosition#getSwitchPosition_route()
	 * @see #getSwitchPosition()
	 * @generated
	 */
	EReference getSwitchPosition_SwitchPosition_route();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.trainbenchmark.railway.RailwayElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayElement
	 * @generated
	 */
	EClass getRailwayElement();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.trainbenchmark.railway.Sensor <em>Sensor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sensor</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Sensor
	 * @generated
	 */
	EClass getSensor();

	/**
	 * Returns the meta object for the reference list '{@link hu.bme.mit.trainbenchmark.railway.Sensor#getSensor_trackElement <em>Sensor track Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sensor track Element</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Sensor#getSensor_trackElement()
	 * @see #getSensor()
	 * @generated
	 */
	EReference getSensor_Sensor_trackElement();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.trainbenchmark.railway.RailwayContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayContainer
	 * @generated
	 */
	EClass getRailwayContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.trainbenchmark.railway.RailwayContainer#getContains <em>Contains</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contains</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayContainer#getContains()
	 * @see #getRailwayContainer()
	 * @generated
	 */
	EReference getRailwayContainer_Contains();

	/**
	 * Returns the meta object for enum '{@link hu.bme.mit.trainbenchmark.railway.SignalStateKind <em>Signal State Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Signal State Kind</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.SignalStateKind
	 * @generated
	 */
	EEnum getSignalStateKind();

	/**
	 * Returns the meta object for enum '{@link hu.bme.mit.trainbenchmark.railway.SwitchStateKind <em>Switch State Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Switch State Kind</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.SwitchStateKind
	 * @generated
	 */
	EEnum getSwitchStateKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RailwayFactory getRailwayFactory();

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
		 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.railway.impl.SegmentImpl <em>Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.trainbenchmark.railway.impl.SegmentImpl
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSegment()
		 * @generated
		 */
		EClass SEGMENT = eINSTANCE.getSegment();

		/**
		 * The meta object literal for the '<em><b>Segment length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__SEGMENT_LENGTH = eINSTANCE.getSegment_Segment_length();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.railway.impl.TrackElementImpl <em>Track Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.trainbenchmark.railway.impl.TrackElementImpl
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getTrackElement()
		 * @generated
		 */
		EClass TRACK_ELEMENT = eINSTANCE.getTrackElement();

		/**
		 * The meta object literal for the '<em><b>Track Element sensor</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_ELEMENT__TRACK_ELEMENT_SENSOR = eINSTANCE.getTrackElement_TrackElement_sensor();

		/**
		 * The meta object literal for the '<em><b>Track Element connects To</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_ELEMENT__TRACK_ELEMENT_CONNECTS_TO = eINSTANCE.getTrackElement_TrackElement_connectsTo();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.railway.impl.SwitchImpl <em>Switch</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.trainbenchmark.railway.impl.SwitchImpl
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSwitch()
		 * @generated
		 */
		EClass SWITCH = eINSTANCE.getSwitch();

		/**
		 * The meta object literal for the '<em><b>Switch actual State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWITCH__SWITCH_ACTUAL_STATE = eINSTANCE.getSwitch_Switch_actualState();

		/**
		 * The meta object literal for the '<em><b>Switch switch Position</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH__SWITCH_SWITCH_POSITION = eINSTANCE.getSwitch_Switch_switchPosition();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.railway.impl.RouteImpl <em>Route</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RouteImpl
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getRoute()
		 * @generated
		 */
		EClass ROUTE = eINSTANCE.getRoute();

		/**
		 * The meta object literal for the '<em><b>Route entry</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROUTE__ROUTE_ENTRY = eINSTANCE.getRoute_Route_entry();

		/**
		 * The meta object literal for the '<em><b>Route switch Position</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROUTE__ROUTE_SWITCH_POSITION = eINSTANCE.getRoute_Route_switchPosition();

		/**
		 * The meta object literal for the '<em><b>Route exit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROUTE__ROUTE_EXIT = eINSTANCE.getRoute_Route_exit();

		/**
		 * The meta object literal for the '<em><b>Route route Definition</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROUTE__ROUTE_ROUTE_DEFINITION = eINSTANCE.getRoute_Route_routeDefinition();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.railway.impl.SignalImpl <em>Signal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.trainbenchmark.railway.impl.SignalImpl
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSignal()
		 * @generated
		 */
		EClass SIGNAL = eINSTANCE.getSignal();

		/**
		 * The meta object literal for the '<em><b>Signal actual State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIGNAL__SIGNAL_ACTUAL_STATE = eINSTANCE.getSignal_Signal_actualState();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.railway.impl.SwitchPositionImpl <em>Switch Position</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.trainbenchmark.railway.impl.SwitchPositionImpl
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSwitchPosition()
		 * @generated
		 */
		EClass SWITCH_POSITION = eINSTANCE.getSwitchPosition();

		/**
		 * The meta object literal for the '<em><b>Switch Position switch</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_POSITION__SWITCH_POSITION_SWITCH = eINSTANCE.getSwitchPosition_SwitchPosition_switch();

		/**
		 * The meta object literal for the '<em><b>Switch Position switch State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWITCH_POSITION__SWITCH_POSITION_SWITCH_STATE = eINSTANCE.getSwitchPosition_SwitchPosition_switchState();

		/**
		 * The meta object literal for the '<em><b>Switch Position route</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_POSITION__SWITCH_POSITION_ROUTE = eINSTANCE.getSwitchPosition_SwitchPosition_route();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.railway.impl.RailwayElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayElementImpl
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getRailwayElement()
		 * @generated
		 */
		EClass RAILWAY_ELEMENT = eINSTANCE.getRailwayElement();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.railway.impl.SensorImpl <em>Sensor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.trainbenchmark.railway.impl.SensorImpl
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSensor()
		 * @generated
		 */
		EClass SENSOR = eINSTANCE.getSensor();

		/**
		 * The meta object literal for the '<em><b>Sensor track Element</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SENSOR__SENSOR_TRACK_ELEMENT = eINSTANCE.getSensor_Sensor_trackElement();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.railway.impl.RailwayContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayContainerImpl
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getRailwayContainer()
		 * @generated
		 */
		EClass RAILWAY_CONTAINER = eINSTANCE.getRailwayContainer();

		/**
		 * The meta object literal for the '<em><b>Contains</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RAILWAY_CONTAINER__CONTAINS = eINSTANCE.getRailwayContainer_Contains();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.railway.SignalStateKind <em>Signal State Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.trainbenchmark.railway.SignalStateKind
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSignalStateKind()
		 * @generated
		 */
		EEnum SIGNAL_STATE_KIND = eINSTANCE.getSignalStateKind();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.railway.SwitchStateKind <em>Switch State Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.trainbenchmark.railway.SwitchStateKind
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSwitchStateKind()
		 * @generated
		 */
		EEnum SWITCH_STATE_KIND = eINSTANCE.getSwitchStateKind();

	}

} //RailwayPackage
