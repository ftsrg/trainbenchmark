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
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerHelper;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;

public class TinkerGraphDriver extends Driver<Vertex> {

	public static final String TYPE = "type";
	protected TinkerGraph graph = TinkerGraph.open();
	protected final Comparator<Vertex> vertexComparator = new VertexComparator();

	public TinkerGraphDriver() throws IOException {
		super();
	}


	@Override
	public void read(final String filePath) throws XMLStreamException, IOException {
		final String graphFilePath = filePath + getPostfix();
		graph.createIndex(TYPE, Vertex.class);
		graph.io(IoCore.graphson()).readGraph(graphFilePath);
	}

	// read

	@Override
	public Collection<Vertex> collectVertices(final String type) {
		final List<? extends Vertex> vertices = TinkerHelper.queryVertexIndex(graph, TYPE, type);
		return (List<Vertex>) vertices;
	}

	public TinkerGraph getGraph() {
		return graph;
	}
	
	@Override
	public Comparator<Vertex> getElementComparator() {
		return vertexComparator;
	}

	@Override
	public String getPostfix() {
		return "-tinkerpop.graphson";
	}

}
