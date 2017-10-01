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
package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public abstract class GraphDriver<TGraph extends Graph> extends Driver {

	public static final String LABEL = "label";
	protected TGraph graph;

	protected GraphDriver() throws IOException {
		super();
	}

	public Collection<Vertex> getVertices(final String type) {
		final Collection<Vertex> vertices = new ArrayList<>();

		final Iterable<Vertex> allVertices = () -> graph.vertices();
		for (final Vertex vertex : allVertices) {
			if (vertex.label().equals(type)) {
				vertices.add(vertex);
			}
		}

		return vertices;
	}

	public TGraph getGraph() {
		return graph;
	}

	@Override
	public String getPostfix() {
		return "-tinkerpop.graphml";
	}

}
