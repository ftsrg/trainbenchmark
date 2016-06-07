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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.transformations.inject;

import java.util.Collection;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver.TinkerGraphDriver;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class TinkerGraphTransformationInjectSemaphoreNeighbor extends TinkerGraphTransformationInject {

	public TinkerGraphTransformationInjectSemaphoreNeighbor(final TinkerGraphDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<Vertex> routes) {
		for (final Vertex route : routes) {
			final Iterable<Edge> entries = () -> route.edges(Direction.OUT, ModelConstants.ENTRY);
			for (final Edge entry : entries) {
				entry.remove();
			}
		}
	}

}
