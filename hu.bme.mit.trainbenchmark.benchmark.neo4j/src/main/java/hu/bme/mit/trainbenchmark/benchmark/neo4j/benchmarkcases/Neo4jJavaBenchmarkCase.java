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


public abstract class Neo4jJavaBenchmarkCase extends Neo4jBenchmarkCase {

	final Label labelRoute = DynamicLabel.label(ROUTE);
	final Label labelSegment = DynamicLabel.label(SEGMENT);
	final Label labelSemaphore = DynamicLabel.label(SEMAPHORE);
	final Label labelSensor = DynamicLabel.label(SENSOR);
	final Label labelSwitch = DynamicLabel.label(SWITCH);
	final Label labelSwitchPosition = DynamicLabel.label(SWITCHPOSITION);
	final Label labelTrackElement = DynamicLabel.label(TRACKELEMENT);

	final DynamicRelationshipType relationshipTypeConnectsTo = DynamicRelationshipType.withName(CONNECTSTO);
	final DynamicRelationshipType relationshipTypeDefinedBy = DynamicRelationshipType.withName(DEFINED_BY);
	final DynamicRelationshipType relationshipTypeEntry = DynamicRelationshipType.withName(ENTRY);
	final DynamicRelationshipType relationshipTypeExit = DynamicRelationshipType.withName(EXIT);
	final DynamicRelationshipType relationshipTypeFollows = DynamicRelationshipType.withName(FOLLOWS);
	final DynamicRelationshipType relationshipTypeSensor = DynamicRelationshipType.withName(SENSOR_EDGE);
	final DynamicRelationshipType relationshipTypeSwitch = DynamicRelationshipType.withName(SWITCH_EDGE);

}
