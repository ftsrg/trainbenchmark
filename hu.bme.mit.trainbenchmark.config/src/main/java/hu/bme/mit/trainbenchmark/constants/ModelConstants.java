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

package hu.bme.mit.trainbenchmark.constants;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class ModelConstants {

	public static final String ID = "id";
	
//	// node types
//	public static final String ROUTE = "Route";
//	public static final String SEGMENT = "Segment";
//	public static final String SENSOR = "Sensor";
//	public static final String SEMAPHORE = "Semaphore";
//	public static final String SWITCH = "Switch";
//	public static final String SWITCHPOSITION = "SwitchPosition";
//	public static final String TRACKELEMENT = "TrackElement";
//
//	// attributes
//	public static final String LENGTH = "length";
//	public static final String SIGNAL = "signal";
//	public static final String CURRENTPOSITION = "currentPosition";
//	public static final String POSITION = "position";
//
//	// references
//	public static final String EXIT = "exit";
//	public static final String ENTRY = "entry";
//	public static final String DEFINED_BY = "definedBy";
//	public static final String FOLLOWS = "follows";
//	public static final String CONNECTSTO = "connectsTo";
//	public static final String SENSOR_EDGE = "sensor";
//	public static final String SWITCH_EDGE = "switch";
	
	// node types
	public static final String ROUTE = "Route";
	public static final String SEGMENT = "Segment";
	public static final String SENSOR = "Sensor";
	public static final String SIGNAL = "Signal";
	public static final String SWITCH = "Switch";
	public static final String SWITCHPOSITION = "SwitchPosition";
	public static final String TRACKELEMENT = "TrackElement";

	// attributes
	public static final String SEGMENT_LENGTH = "Segment_length";
	public static final String SIGNAL_CURRENTSTATE = "Signal_currentState";
	public static final String SWITCH_CURRENTSTATE = "Switch_currentState";

	// references
	public static final String ROUTE_EXIT = "Route_exit";
	public static final String ROUTE_ENTRY = "Route_entry";
	public static final String ROUTE_ROUTEDEFINITION = "Route_routeDefinition";
	public static final String ROUTE_SWITCHPOSITION = "Route_switchPosition";
	public static final String TRACKELEMENT_CONNECTSTO = "TrackElement_connectsTo";
	public static final String TRACKELEMENT_SENSOR = "TrackElement_sensor";
	public static final String SWITCHPOSITION_SWITCH = "SwitchPosition_switch";
	public static final String SWITCHPOSITION_SWITCHSTATE = "SwitchPosition_switchState";

	// enumeration strings (for RDF-based representations)
	public static final String SIGNALSTATE_FAILURE = "SIGNALSTATE_FAILURE";
	public static final String SIGNALSTATE_STOP = "SIGNALSTATE_STOP";
	public static final String SIGNALSTATE_GO = "SIGNALSTATE_GO";

	public static final String SWITCHSTATE_FAILURE = "SWITCHSTATE_FAILURE";
	public static final String SWITCHSTATE_LEFT = "SWITCHSTATE_LEFT";
	public static final String SWITCHSTATE_RIGHT = "SWITCHSTATE_RIGHT";
	public static final String SWITCHSTATE_STRAIGHT = "SWITCHSTATE_STRAIGHT";

	// inheritance
	public static final Map<String, String> ancestors = ImmutableMap.of(SEGMENT, TRACKELEMENT, SWITCH, TRACKELEMENT);

}
