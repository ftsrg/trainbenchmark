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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.stream.XMLStreamException;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;

public class TinkerGraphDriver extends Driver<Vertex> {

	public static final String LABEL = "label";
	protected TinkerGraph graph;

	protected TinkerGraphDriver() throws IOException {
		super();
	}
	
	public static TinkerGraphDriver create() throws IOException {
		return new TinkerGraphDriver();
	}

	@Override
	public void read(final String modelPath) throws XMLStreamException, IOException {
		graph = TinkerGraph.open();
		graph.createIndex(LABEL, Vertex.class);
		graph.io(IoCore.graphson()).readGraph(modelPath);
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

	public TinkerGraph getGraph() {
		return graph;
	}

	@Override
	public String getPostfix() {
		return "-tinkerpop.graphson";
	}

}
