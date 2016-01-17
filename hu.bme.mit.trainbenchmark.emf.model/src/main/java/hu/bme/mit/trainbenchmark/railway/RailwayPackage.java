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
	String eNS_URI = "http://www.semanticweb.org/ontologies/2015/trainbenchmark";

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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RAILWAY_ELEMENT__ID = 0;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RAILWAY_ELEMENT_FEATURE_COUNT = 1;

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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_ELEMENT__ID = RAILWAY_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Monitored By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_ELEMENT__MONITORED_BY = RAILWAY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Connects To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACK_ELEMENT__CONNECTS_TO = RAILWAY_ELEMENT_FEATURE_COUNT + 1;

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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__ID = TRACK_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Monitored By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__MONITORED_BY = TRACK_ELEMENT__MONITORED_BY;

	/**
	 * The feature id for the '<em><b>Connects To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__CONNECTS_TO = TRACK_ELEMENT__CONNECTS_TO;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__LENGTH = TRACK_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Semaphores</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__SEMAPHORES = TRACK_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Neighbors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__NEIGHBORS = TRACK_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT_FEATURE_COUNT = TRACK_ELEMENT_FEATURE_COUNT + 3;

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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__ID = TRACK_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Monitored By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__MONITORED_BY = TRACK_ELEMENT__MONITORED_BY;

	/**
	 * The feature id for the '<em><b>Connects To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__CONNECTS_TO = TRACK_ELEMENT__CONNECTS_TO;

	/**
	 * The feature id for the '<em><b>Current Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__CURRENT_POSITION = TRACK_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Positions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__POSITIONS = TRACK_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Left</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__LEFT = TRACK_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Right</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__RIGHT = TRACK_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__FROM = TRACK_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Switch</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_FEATURE_COUNT = TRACK_ELEMENT_FEATURE_COUNT + 5;

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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTE__ID = RAILWAY_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Entry</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTE__ENTRY = RAILWAY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Follows</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTE__FOLLOWS = RAILWAY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Exit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTE__EXIT = RAILWAY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Gathers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTE__GATHERS = RAILWAY_ELEMENT_FEATURE_COUNT + 3;

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
	 * The meta object id for the '{@link hu.bme.mit.trainbenchmark.railway.impl.SemaphoreImpl <em>Semaphore</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.trainbenchmark.railway.impl.SemaphoreImpl
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSemaphore()
	 * @generated
	 */
	int SEMAPHORE = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMAPHORE__ID = RAILWAY_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Signal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMAPHORE__SIGNAL = RAILWAY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Semaphore</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMAPHORE_FEATURE_COUNT = RAILWAY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Semaphore</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMAPHORE_OPERATION_COUNT = RAILWAY_ELEMENT_OPERATION_COUNT + 0;

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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_POSITION__ID = RAILWAY_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_POSITION__TARGET = RAILWAY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_POSITION__POSITION = RAILWAY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Route</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_POSITION__ROUTE = RAILWAY_ELEMENT_FEATURE_COUNT + 2;

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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__ID = RAILWAY_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Monitors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR__MONITORS = RAILWAY_ELEMENT_FEATURE_COUNT + 0;

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
	 * The feature id for the '<em><b>Routes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RAILWAY_CONTAINER__ROUTES = 0;

	/**
	 * The feature id for the '<em><b>Regions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RAILWAY_CONTAINER__REGIONS = 1;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RAILWAY_CONTAINER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RAILWAY_CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.trainbenchmark.railway.impl.RegionImpl <em>Region</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RegionImpl
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getRegion()
	 * @generated
	 */
	int REGION = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION__ID = RAILWAY_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION__ELEMENTS = RAILWAY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sensors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION__SENSORS = RAILWAY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Region</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_FEATURE_COUNT = RAILWAY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Region</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGION_OPERATION_COUNT = RAILWAY_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link hu.bme.mit.trainbenchmark.railway.Signal <em>Signal</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.trainbenchmark.railway.Signal
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSignal()
	 * @generated
	 */
	int SIGNAL = 10;

	/**
	 * The meta object id for the '{@link hu.bme.mit.trainbenchmark.railway.Position <em>Position</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see hu.bme.mit.trainbenchmark.railway.Position
	 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getPosition()
	 * @generated
	 */
	int POSITION = 11;


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
	 * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.railway.Segment#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Segment#getLength()
	 * @see #getSegment()
	 * @generated
	 */
	EAttribute getSegment_Length();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.trainbenchmark.railway.Segment#getSemaphores <em>Semaphores</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Semaphores</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Segment#getSemaphores()
	 * @see #getSegment()
	 * @generated
	 */
	EReference getSegment_Semaphores();

	/**
	 * Returns the meta object for the reference list '{@link hu.bme.mit.trainbenchmark.railway.Segment#getNeighbors <em>Neighbors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Neighbors</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Segment#getNeighbors()
	 * @see #getSegment()
	 * @generated
	 */
	EReference getSegment_Neighbors();

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
	 * Returns the meta object for the reference list '{@link hu.bme.mit.trainbenchmark.railway.TrackElement#getMonitoredBy <em>Monitored By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Monitored By</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.TrackElement#getMonitoredBy()
	 * @see #getTrackElement()
	 * @generated
	 */
	EReference getTrackElement_MonitoredBy();

	/**
	 * Returns the meta object for the reference list '{@link hu.bme.mit.trainbenchmark.railway.TrackElement#getConnectsTo <em>Connects To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Connects To</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.TrackElement#getConnectsTo()
	 * @see #getTrackElement()
	 * @generated
	 */
	EReference getTrackElement_ConnectsTo();

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
	 * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.railway.Switch#getCurrentPosition <em>Current Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Current Position</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Switch#getCurrentPosition()
	 * @see #getSwitch()
	 * @generated
	 */
	EAttribute getSwitch_CurrentPosition();

	/**
	 * Returns the meta object for the reference list '{@link hu.bme.mit.trainbenchmark.railway.Switch#getPositions <em>Positions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Positions</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Switch#getPositions()
	 * @see #getSwitch()
	 * @generated
	 */
	EReference getSwitch_Positions();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.trainbenchmark.railway.Switch#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Left</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Switch#getLeft()
	 * @see #getSwitch()
	 * @generated
	 */
	EReference getSwitch_Left();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.trainbenchmark.railway.Switch#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Right</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Switch#getRight()
	 * @see #getSwitch()
	 * @generated
	 */
	EReference getSwitch_Right();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.trainbenchmark.railway.Switch#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Switch#getFrom()
	 * @see #getSwitch()
	 * @generated
	 */
	EReference getSwitch_From();

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
	 * Returns the meta object for the reference '{@link hu.bme.mit.trainbenchmark.railway.Route#getEntry <em>Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Entry</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Route#getEntry()
	 * @see #getRoute()
	 * @generated
	 */
	EReference getRoute_Entry();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.trainbenchmark.railway.Route#getFollows <em>Follows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Follows</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Route#getFollows()
	 * @see #getRoute()
	 * @generated
	 */
	EReference getRoute_Follows();

	/**
	 * Returns the meta object for the reference '{@link hu.bme.mit.trainbenchmark.railway.Route#getExit <em>Exit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Exit</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Route#getExit()
	 * @see #getRoute()
	 * @generated
	 */
	EReference getRoute_Exit();

	/**
	 * Returns the meta object for the reference list '{@link hu.bme.mit.trainbenchmark.railway.Route#getGathers <em>Gathers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Gathers</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Route#getGathers()
	 * @see #getRoute()
	 * @generated
	 */
	EReference getRoute_Gathers();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.trainbenchmark.railway.Semaphore <em>Semaphore</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Semaphore</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Semaphore
	 * @generated
	 */
	EClass getSemaphore();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.railway.Semaphore#getSignal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signal</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Semaphore#getSignal()
	 * @see #getSemaphore()
	 * @generated
	 */
	EAttribute getSemaphore_Signal();

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
	 * Returns the meta object for the reference '{@link hu.bme.mit.trainbenchmark.railway.SwitchPosition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.SwitchPosition#getTarget()
	 * @see #getSwitchPosition()
	 * @generated
	 */
	EReference getSwitchPosition_Target();

	/**
	 * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.railway.SwitchPosition#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Position</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.SwitchPosition#getPosition()
	 * @see #getSwitchPosition()
	 * @generated
	 */
	EAttribute getSwitchPosition_Position();

	/**
	 * Returns the meta object for the container reference '{@link hu.bme.mit.trainbenchmark.railway.SwitchPosition#getRoute <em>Route</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Route</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.SwitchPosition#getRoute()
	 * @see #getSwitchPosition()
	 * @generated
	 */
	EReference getSwitchPosition_Route();

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
	 * Returns the meta object for the attribute '{@link hu.bme.mit.trainbenchmark.railway.RailwayElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayElement#getId()
	 * @see #getRailwayElement()
	 * @generated
	 */
	EAttribute getRailwayElement_Id();

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
	 * Returns the meta object for the reference list '{@link hu.bme.mit.trainbenchmark.railway.Sensor#getMonitors <em>Monitors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Monitors</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Sensor#getMonitors()
	 * @see #getSensor()
	 * @generated
	 */
	EReference getSensor_Monitors();

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
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.trainbenchmark.railway.RailwayContainer#getRoutes <em>Routes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Routes</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayContainer#getRoutes()
	 * @see #getRailwayContainer()
	 * @generated
	 */
	EReference getRailwayContainer_Routes();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.trainbenchmark.railway.RailwayContainer#getRegions <em>Regions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Regions</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayContainer#getRegions()
	 * @see #getRailwayContainer()
	 * @generated
	 */
	EReference getRailwayContainer_Regions();

	/**
	 * Returns the meta object for class '{@link hu.bme.mit.trainbenchmark.railway.Region <em>Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Region</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Region
	 * @generated
	 */
	EClass getRegion();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.trainbenchmark.railway.Region#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Region#getElements()
	 * @see #getRegion()
	 * @generated
	 */
	EReference getRegion_Elements();

	/**
	 * Returns the meta object for the containment reference list '{@link hu.bme.mit.trainbenchmark.railway.Region#getSensors <em>Sensors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sensors</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Region#getSensors()
	 * @see #getRegion()
	 * @generated
	 */
	EReference getRegion_Sensors();

	/**
	 * Returns the meta object for enum '{@link hu.bme.mit.trainbenchmark.railway.Signal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Signal</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Signal
	 * @generated
	 */
	EEnum getSignal();

	/**
	 * Returns the meta object for enum '{@link hu.bme.mit.trainbenchmark.railway.Position <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Position</em>'.
	 * @see hu.bme.mit.trainbenchmark.railway.Position
	 * @generated
	 */
	EEnum getPosition();

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
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT__LENGTH = eINSTANCE.getSegment_Length();

		/**
		 * The meta object literal for the '<em><b>Semaphores</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEGMENT__SEMAPHORES = eINSTANCE.getSegment_Semaphores();

		/**
		 * The meta object literal for the '<em><b>Neighbors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEGMENT__NEIGHBORS = eINSTANCE.getSegment_Neighbors();

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
		 * The meta object literal for the '<em><b>Monitored By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_ELEMENT__MONITORED_BY = eINSTANCE.getTrackElement_MonitoredBy();

		/**
		 * The meta object literal for the '<em><b>Connects To</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRACK_ELEMENT__CONNECTS_TO = eINSTANCE.getTrackElement_ConnectsTo();

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
		 * The meta object literal for the '<em><b>Current Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWITCH__CURRENT_POSITION = eINSTANCE.getSwitch_CurrentPosition();

		/**
		 * The meta object literal for the '<em><b>Positions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH__POSITIONS = eINSTANCE.getSwitch_Positions();

		/**
		 * The meta object literal for the '<em><b>Left</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH__LEFT = eINSTANCE.getSwitch_Left();

		/**
		 * The meta object literal for the '<em><b>Right</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH__RIGHT = eINSTANCE.getSwitch_Right();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH__FROM = eINSTANCE.getSwitch_From();

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
		 * The meta object literal for the '<em><b>Entry</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROUTE__ENTRY = eINSTANCE.getRoute_Entry();

		/**
		 * The meta object literal for the '<em><b>Follows</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROUTE__FOLLOWS = eINSTANCE.getRoute_Follows();

		/**
		 * The meta object literal for the '<em><b>Exit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROUTE__EXIT = eINSTANCE.getRoute_Exit();

		/**
		 * The meta object literal for the '<em><b>Gathers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROUTE__GATHERS = eINSTANCE.getRoute_Gathers();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.railway.impl.SemaphoreImpl <em>Semaphore</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.trainbenchmark.railway.impl.SemaphoreImpl
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSemaphore()
		 * @generated
		 */
		EClass SEMAPHORE = eINSTANCE.getSemaphore();

		/**
		 * The meta object literal for the '<em><b>Signal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEMAPHORE__SIGNAL = eINSTANCE.getSemaphore_Signal();

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
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_POSITION__TARGET = eINSTANCE.getSwitchPosition_Target();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWITCH_POSITION__POSITION = eINSTANCE.getSwitchPosition_Position();

		/**
		 * The meta object literal for the '<em><b>Route</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_POSITION__ROUTE = eINSTANCE.getSwitchPosition_Route();

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
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RAILWAY_ELEMENT__ID = eINSTANCE.getRailwayElement_Id();

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
		 * The meta object literal for the '<em><b>Monitors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SENSOR__MONITORS = eINSTANCE.getSensor_Monitors();

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
		 * The meta object literal for the '<em><b>Routes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RAILWAY_CONTAINER__ROUTES = eINSTANCE.getRailwayContainer_Routes();

		/**
		 * The meta object literal for the '<em><b>Regions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RAILWAY_CONTAINER__REGIONS = eINSTANCE.getRailwayContainer_Regions();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.railway.impl.RegionImpl <em>Region</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RegionImpl
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getRegion()
		 * @generated
		 */
		EClass REGION = eINSTANCE.getRegion();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGION__ELEMENTS = eINSTANCE.getRegion_Elements();

		/**
		 * The meta object literal for the '<em><b>Sensors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGION__SENSORS = eINSTANCE.getRegion_Sensors();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.railway.Signal <em>Signal</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.trainbenchmark.railway.Signal
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getSignal()
		 * @generated
		 */
		EEnum SIGNAL = eINSTANCE.getSignal();

		/**
		 * The meta object literal for the '{@link hu.bme.mit.trainbenchmark.railway.Position <em>Position</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see hu.bme.mit.trainbenchmark.railway.Position
		 * @see hu.bme.mit.trainbenchmark.railway.impl.RailwayPackageImpl#getPosition()
		 * @generated
		 */
		EEnum POSITION = eINSTANCE.getPosition();

	}

} //RailwayPackage
