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

import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.CONNECTSTO;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.DEFINED_BY;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.ENTRY;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.EXIT;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.FOLLOWS;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.ROUTE;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SEGMENT;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SENSOR;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SENSOR_EDGE;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SWITCH;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SWITCHPOSITION;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.SWITCH_EDGE;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.TRACKELEMENT;

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
