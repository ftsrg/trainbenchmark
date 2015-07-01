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
package hu.bme.mit.trainbenchmark.benchmark.orientdb.constants;

public class OrientDbConstants {

	public static final String labelRoute = ":Route";
	public static final String labelSegment = ":TrackElement:Segment";
	public static final String labelSemaphore = ":Semaphore";
	public static final String labelSensor = ":Sensor";
	public static final String labelSwitch = ":Switch:TrackElement";
	public static final String labelSwitchPosition = ":SwitchPosition";
	// public static final String labelTrackElement = ":TrackElement";

	public static final String relationshipTypeConnectsTo = "connectsTo";
	public static final String relationshipTypeDefinedBy = "definedBy";
	public static final String relationshipTypeEntry = "entry";
	public static final String relationshipTypeExit = "exit";
	public static final String relationshipTypeFollows = "follows";
	public static final String relationshipTypeSensor = "sensor";
	public static final String relationshipTypeSwitch = "switch";

}
