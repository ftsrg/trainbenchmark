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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.inject;

import static hu.bme.mit.trainbenchmark.benchmark.tinkergraph.constants.TinkerGraphConstants.relationshipTypeMonitoredBy;

import java.util.Collection;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;

public class TinkerGraphTransformationInjectSwitchSensor extends TinkerGraphTransformationInject {

	public TinkerGraphTransformationInjectSwitchSensor(final TinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public void performRHS(final Collection<Node> switches) {
		for (final Node sw : switches) {
			final Iterable<Relationship> sensors = sw.getRelationships(relationshipTypeMonitoredBy);
			for (final Relationship sensor : sensors) {
				sensor.delete();
			}
		}
	}

}
