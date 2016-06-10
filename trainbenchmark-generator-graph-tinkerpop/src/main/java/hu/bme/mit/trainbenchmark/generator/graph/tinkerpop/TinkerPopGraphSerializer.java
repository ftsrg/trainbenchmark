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

package hu.bme.mit.trainbenchmark.generator.graph.tinkerpop;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.stream.XMLStreamException;

import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.io.Io.Builder;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.config.GraphFormat;
import hu.bme.mit.trainbenchmark.generator.graph.tinkerpop.config.TinkerPopGraphGeneratorConfigWrapper;

public class TinkerPopGraphSerializer extends ModelSerializer<TinkerPopGraphGeneratorConfigWrapper> {

	protected TinkerPopGraphGeneratorConfigWrapper graphGeneratorConfig;
	protected TinkerGraph graph = TinkerGraph.open();

	public TinkerPopGraphSerializer(final TinkerPopGraphGeneratorConfigWrapper generatorConfig) {
		super(generatorConfig);
	}

	@Override
	public String syntax() {
		return "tinkergraph";
	}

	@Override
	public void initModel() throws IOException {
	}

	@Override
	public Object createVertex(final int id, final String type, final Map<String, ? extends Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges) {
		// NOTE: multiple inheritance is not supported
		final Vertex vertex = graph.addVertex(type);
		
		for (final Entry<String, ? extends Object> attribute : attributes.entrySet()) {
			final String key = attribute.getKey();
			Object value = attribute.getValue();

			// convert the value to string if it's an enum
			value = enumsToString(value);
			vertex.property(key, value);
		}

		for (final Entry<String, Object> outgoingEdge : outgoingEdges.entrySet()) {
			final String label = outgoingEdge.getKey();
			if (outgoingEdge.getValue() instanceof Vertex) {
				final Vertex targetVertex = (Vertex) outgoingEdge.getValue();
				vertex.addEdge(label, targetVertex);
			}
		}

		for (final Entry<String, Object> incomingEdge : incomingEdges.entrySet()) {
			final String label = incomingEdge.getKey();
			if (incomingEdge.getValue() instanceof Vertex) {
				final Vertex sourceVertex = (Vertex) incomingEdge.getValue();
				sourceVertex.addEdge(label, vertex);
			}
		}

		return vertex;
	}

	private Object enumsToString(Object value) {
		if (value instanceof Enum) {
			final Enum<?> e = (Enum<?>) value;
			value = e.toString();
		}
		return value;
	}

	@Override
	public void createEdge(final String label, final Object from, final Object to) {
		if (from == null || to == null) {
			return;
		}

		final Vertex source = (Vertex) from;
		final Vertex target = (Vertex) to;

		source.addEdge(label, target);
	}

	@Override
	public void setAttribute(final String type, final Object node, final String key, final Object value) {
		final Vertex v = (Vertex) node;

		final Object attributeValue = enumsToString(value);
		v.property(key, attributeValue);
	}

	@Override
	public void persistModel() throws IOException, XMLStreamException, ClassNotFoundException, IllegalAccessException,
			InstantiationException {
		final GraphFormat format = generatorConfigWrapper.getGraphFormat();
		Builder<?> builder = null;
		switch (format) {
		case GRAPHML:
			builder = IoCore.graphml();
			break;
		case GRAPHSON:
			builder = IoCore.graphson();
			break;
		case GRYO:
			builder = IoCore.gryo();
			break;
		default:
			throw new UnsupportedOperationException("Format " + format + " is not supported.");
		}

		final String extension = "-tinkerpop." + format.toString().toLowerCase();
		final String fileName = generatorConfigWrapper.getGeneratorConfig().getModelPathWithoutExtension() + extension;
		graph.io(builder).writeGraph(fileName);
		graph.close();
	}

}
