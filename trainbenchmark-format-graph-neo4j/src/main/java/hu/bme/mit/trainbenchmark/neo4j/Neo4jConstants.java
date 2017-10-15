
package hu.bme.mit.trainbenchmark.neo4j;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTS_TO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.EXIT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.FOLLOWS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.REQUIRES;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TARGET;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TRACKELEMENT;

public class Neo4jConstants {

	public static final String QUERY_EXTENSION = "cypher";

	public static final String CSV_EXTENSION = "csv";
	public static final String GRAPHML_POSTFIX = "-neo4j.graphml";

	public static final Label labelRoute = Label.label(ROUTE);
	public static final Label labelSegment = Label.label(SEGMENT);
	public static final Label labelSemaphore = Label.label(SEMAPHORE);
	public static final Label labelSensor = Label.label(SENSOR);
	public static final Label labelSwitch = Label.label(SWITCH);
	public static final Label labelSwitchPosition = Label.label(SWITCHPOSITION);
	public static final Label labelTrackElement = Label.label(TRACKELEMENT);

	public static final RelationshipType relationshipTypeConnectsTo = RelationshipType.withName(CONNECTS_TO);
	public static final RelationshipType relationshipTypeEntry = RelationshipType.withName(ENTRY);
	public static final RelationshipType relationshipTypeExit = RelationshipType.withName(EXIT);
	public static final RelationshipType relationshipTypeFollows = RelationshipType.withName(FOLLOWS);
	public static final RelationshipType relationshipTypeRequires = RelationshipType.withName(REQUIRES);
	public static final RelationshipType relationshipTypeMonitoredBy = RelationshipType.withName(MONITORED_BY);
	public static final RelationshipType relationshipTypeTarget = RelationshipType.withName(TARGET);

	public static final String CYPHER_DIR = "/trainbenchmark-tool-neo4j/src/main/resources/";

}
