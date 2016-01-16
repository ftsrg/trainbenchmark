/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.neo4j.constants;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CONNECTS_TO;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.EXIT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.FOLLOWS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.GATHERS;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.MONITORED_BY;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.TARGET;
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

	public static final DynamicRelationshipType relationshipTypeConnectsTo = DynamicRelationshipType.withName(CONNECTS_TO);
	public static final DynamicRelationshipType relationshipTypeEntry = DynamicRelationshipType.withName(ENTRY);
	public static final DynamicRelationshipType relationshipTypeExit = DynamicRelationshipType.withName(EXIT);
	public static final DynamicRelationshipType relationshipTypeFollows = DynamicRelationshipType.withName(FOLLOWS);
	public static final DynamicRelationshipType relationshipTypeGathers = DynamicRelationshipType.withName(GATHERS);
	public static final DynamicRelationshipType relationshipTypeMonitoredBy = DynamicRelationshipType.withName(MONITORED_BY);
	public static final DynamicRelationshipType relationshipTypeSwitch = DynamicRelationshipType.withName(TARGET);

}
