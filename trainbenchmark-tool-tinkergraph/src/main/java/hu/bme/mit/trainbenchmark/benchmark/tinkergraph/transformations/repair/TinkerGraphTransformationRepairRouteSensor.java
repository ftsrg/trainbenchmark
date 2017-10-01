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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.repair;

import java.util.Collection;

import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.GraphDriver;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.matches.TinkerGraphRouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.TinkerGraphTransformation;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class TinkerGraphTransformationRepairRouteSensor<TTinkerGraphDriver extends GraphDriver>
		extends TinkerGraphTransformation<TinkerGraphRouteSensorMatch, TTinkerGraphDriver> {

	public TinkerGraphTransformationRepairRouteSensor(final TTinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<TinkerGraphRouteSensorMatch> matches) {
		for (final TinkerGraphRouteSensorMatch rsm : matches) {
			final Vertex route = rsm.getRoute();
			final Vertex sensor = rsm.getSensor();
			route.addEdge(ModelConstants.REQUIRES, sensor);
		}
	}

}
