package hu.bme.mit.trainbenchmark.generator;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public abstract class ModelSerializer<TGeneratorConfig extends GeneratorConfig> {
	
	protected final TGeneratorConfig gc;
	protected int id = 1;

	public ModelSerializer(TGeneratorConfig gc) {
		this.gc = gc;
	}
	
	public abstract String syntax();

	public abstract void initModel() throws IOException;

	public abstract void persistModel() throws Exception;

	// the createVertex() methods with fewer arguments are final

	public final Object createVertex(final String type) throws IOException {
		return createVertex(type, Collections.<String, Object> emptyMap(), Collections.<String, Object> emptyMap(),
				Collections.<String, Object> emptyMap());
	}

	public final Object createVertex(final String type, final Map<String, ? extends Object> attributes) throws IOException {
		return createVertex(type, attributes, Collections.<String, Object> emptyMap(), Collections.<String, Object> emptyMap());
	}

	public final Object createVertex(final String type, final Map<String, ? extends Object> attributes,
			final Map<String, Object> outgoingEdges) throws IOException {
		return createVertex(type, attributes, outgoingEdges, Collections.<String, Object> emptyMap());
	}

	public final Object createVertex(final String type, final Map<String, ? extends Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges) throws IOException {
		final Object vertex = createVertex(id, type, attributes, outgoingEdges, incomingEdges);
		id++;
		return vertex;
	}

	public abstract Object createVertex(final int id, final String type, final Map<String, ? extends Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges) throws IOException;

	public abstract void createEdge(String label, Object from, Object to) throws IOException;

	public void beginTransaction() throws IOException {
	};

	public void endTransaction() throws IOException {
	};

}
