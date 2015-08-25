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

package hu.bme.mit.trainbenchmark.constants.schedule;

public class ScheduleModelConstants {

	public static final String ID = "id";

	// node types
	public static final String TRAIN = "Train";
	public static final String SCHEDULE = "Schedule";
	public static final String STATION = "Station";
	public static final String ASSOCIATION = "Association";

	// attributes
	public static final String TRAIN_UID = "uid";
	public static final String CATEGORY = "category";
	public static final String DAYS = "days";
	public static final String STATUS = "status";
	public static final String START_DATE = "startDate";
	public static final String TRANSACTION = "transaction";
	public static final String PLANNING = "planning";
	public static final String CODE = "code";
	public static final String STANOX = "stanox";
	public static final String NALCO = "nalco";

	// references
	public static final String SCHEDULES = "schedules";
	public static final String ASSOCIATIONS = "associations";
	public static final String ASSOCIATIVE = "associative";
	public static final String ORIGIN = "origin";
	public static final String TERMINAL = "terminal";
	public static final String DESTINATIONS = "destinations";
	public static final String LOCATION = "location";
	public static final String NEIGHBORS = "neighbors";
}
