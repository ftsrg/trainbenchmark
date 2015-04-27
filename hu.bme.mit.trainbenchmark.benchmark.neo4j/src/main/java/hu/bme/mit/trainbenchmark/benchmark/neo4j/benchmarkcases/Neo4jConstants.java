package hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTSTO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.DEFINED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.EXIT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.FOLLOWS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR_EDGE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH_EDGE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Label;

public class Neo4jConstants {

	public static final Label labelRoute = DynamicLabel.label(ROUTE);
	public static final Label labelSegment = DynamicLabel.label(SEGMENT);
	public static final Label labelSemaphore = DynamicLabel.label(SEMAPHORE);
	public static final Label labelSensor = DynamicLabel.label(SENSOR);
	public static final Label labelSwitch = DynamicLabel.label(SWITCH);
	public static final Label labelSwitchPosition = DynamicLabel.label(SWITCHPOSITION);
	public static final Label labelTrackElement = DynamicLabel.label(TRACKELEMENT);

	public static final DynamicRelationshipType relationshipTypeConnectsTo = DynamicRelationshipType.withName(CONNECTSTO);
	public static final DynamicRelationshipType relationshipTypeDefinedBy = DynamicRelationshipType.withName(DEFINED_BY);
	public static final DynamicRelationshipType relationshipTypeEntry = DynamicRelationshipType.withName(ENTRY);
	public static final DynamicRelationshipType relationshipTypeExit = DynamicRelationshipType.withName(EXIT);
	public static final DynamicRelationshipType relationshipTypeFollows = DynamicRelationshipType.withName(FOLLOWS);
	public static final DynamicRelationshipType relationshipTypeSensor = DynamicRelationshipType.withName(SENSOR_EDGE);
	public static final DynamicRelationshipType relationshipTypeSwitch = DynamicRelationshipType.withName(SWITCH_EDGE);

}
