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

package hu.bme.mit.trainbenchmark.generator;

import java.io.IOException;
import java.util.Map;

public abstract class FormatGenerator extends Generator {

	public abstract String syntax();

	public abstract void initModel() throws IOException;

	public abstract void persistModel() throws Exception;

	// the createVertex() methods with fewer arguments are final

	public final Object createVertex(final String type) throws IOException {
		return createVertex(type, emptyMap);
	}

	public final Object createVertex(final String type, final Map<String, Object> attributes) throws IOException {
		return createVertex(type, attributes, emptyMap);
	}

	public final Object createVertex(final String type, final Map<String, Object> attributes,
			final Map<String, Object> outgoingEdges) throws IOException {
		return createVertex(type, attributes, outgoingEdges, emptyMap);
	}

	public final Object createVertex(final String type, final Map<String, Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges)
			throws IOException {
		final Object vertex = createVertex(id, type, attributes, outgoingEdges, incomingEdges);
		id++;
		return vertex;
	}

	public abstract Object createVertex(final int id, final String type, final Map<String, Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges)
			throws IOException;

	public abstract void createEdge(String label, Object from, Object to) throws IOException;

	public abstract void setAttribute(String type, Object node, String key, Object value) throws IOException;

	public abstract void startTransaction() throws IOException;

	public abstract void endTransaction();

}
