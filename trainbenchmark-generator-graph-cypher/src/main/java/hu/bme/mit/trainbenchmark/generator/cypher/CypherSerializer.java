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

package hu.bme.mit.trainbenchmark.generator.cypher;

import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.cypher.config.CypherGeneratorConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SUPERTYPES;

public class CypherSerializer extends ModelSerializer<CypherGeneratorConfig> {

	protected BufferedWriter writer;

	public CypherSerializer(final CypherGeneratorConfig gc) {
		super(gc);
	}

	@Override
	public String syntax() {
		return "Cypher";
	}

	public void write(final String s) throws IOException {
		writer.write(s + "\n");
	}

	@Override
	public void initModel() throws IOException {
		final String cypherPath = gc.getConfigBase().getModelPathWithoutExtension() + ".cypher";
		final File cypherFile = new File(cypherPath);
		writer = new BufferedWriter(new FileWriter(cypherFile));
	}

	@Override
	public void persistModel() throws IOException, InterruptedException {
		writer.close();
	}

	@Override
	public Object createVertex(final int id, final String type, final Map<String, ? extends Object> attributes, final Map<String, Object> outgoingEdges,
			final Map<String, Object> incomingEdges) throws IOException {

		StringBuilder query = new StringBuilder("CREATE (node");

		//If we have supertypes, we add them first
		if (SUPERTYPES.containsKey(type)){
			final String ancestorType = SUPERTYPES.get(type);
			query.append(":" + ancestorType);
		}

		//Then we add the type
		query.append(":" + type);

		//Setting the attributes
		query.append(" {id: " + id);
		if (!attributes.isEmpty()){
			query.append(", ");
			query.append(
				attributes.entrySet().stream().map(
					e -> (e.getKey() + ": " + valueToString(e.getValue()))
				).collect(Collectors.joining(", "))
			);
		}
		query.append("});");

		write(query.toString());

		//Adding relationships
		for(Entry<String, Object> entry : outgoingEdges.entrySet()) {
			write("MATCH (from {id: " + id + "}), (to {id: " + entry.getValue() + "}) CREATE (from)-[:" + entry.getKey() + "]->(to);");
		}

		for (Entry<String, Object> entry : incomingEdges.entrySet()) {
			write("MATCH (from {id: " + entry.getValue() + "}), (to {id: " + id + "}) CREATE (from)-[:" + entry.getKey() + "]->(to);");
		}

		return id;
	}

	@Override
	public void createEdge(final String label, final Object from, final Object to) throws IOException {
		if (from == null || to == null) {
			return;
		}

		write("MATCH (from {id: " + from + "}), (to {id: " + to + "}) CREATE (from)-[:" + label + "]->(to);");
	}

	@Override
	public void setAttribute(final String type, final Object node, final String key, final Object value) throws IOException {
		final String stringValue = valueToString(value);

		write("MATCH (node:" + type + " {id: " + node + "}) SET node." + key + "=" + stringValue + ";");
	}

	private String valueToString(final Object value) {
		if (value.toString().equals("true") || value.toString().equals("false")) return value.toString();
		try {
			Integer.parseInt(value.toString());
			return value.toString();
		} catch (NumberFormatException e){
			return "'" + value + "'";
		}
	}

}
