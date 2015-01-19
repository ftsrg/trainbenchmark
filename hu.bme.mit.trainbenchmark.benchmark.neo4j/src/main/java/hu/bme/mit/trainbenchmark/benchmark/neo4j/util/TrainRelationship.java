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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.util;

import org.neo4j.graphdb.RelationshipType;

public enum TrainRelationship implements RelationshipType {
	SIGNAL_ACTUALSTATE, ROUTE_ENTRY, ROUTE_EXIT, ROUTE_ROUTEDEFINITION, ROUTE_SWITCHPOSITION, TRACKELEMENT_SENSOR, TRACKELEMENT_CONNECTSTO, SWITCHPOSITION_SWITCH,
}
