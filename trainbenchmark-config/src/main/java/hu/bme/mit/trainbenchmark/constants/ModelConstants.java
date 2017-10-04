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

package hu.bme.mit.trainbenchmark.constants;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class ModelConstants {

	public static final String ID = "id";

	// node types
	public static final String REGION = "Region";
	public static final String ROUTE = "Route";
	public static final String SEGMENT = "Segment";
	public static final String SENSOR = "Sensor";
	public static final String SEMAPHORE = "Semaphore";
	public static final String SWITCH = "Switch";
	public static final String SWITCHPOSITION = "SwitchPosition";
	public static final String TRACKELEMENT = "TrackElement";

	// attributes
	public static final String ACTIVE = "active";
	public static final String LENGTH = "length";
	public static final String SIGNAL = "signal";
	public static final String CURRENTPOSITION = "currentPosition";
	public static final String POSITION = "position";

	// references
	public static final String CONNECTS_TO = "connectsTo";
	public static final String ELEMENTS = "elements";
	public static final String EXIT = "exit";
	public static final String ENTRY = "entry";
	public static final String FOLLOWS = "follows";
	public static final String REQUIRES = "requires";
	public static final String MONITORED_BY = "monitoredBy";
	public static final String SEMAPHORES = "semaphores";
	public static final String SENSORS = "sensors";
	public static final String TARGET = "target";

	// inheritance
	public static final Map<String, String> SUPERTYPES = ImmutableMap.of(SEGMENT, TRACKELEMENT, SWITCH, TRACKELEMENT);

}
