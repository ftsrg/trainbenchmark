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

package hu.bme.mit.trainbenchmark.generator.rdf;

import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.ID_PREFIX;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;

import hu.bme.mit.trainbenchmark.generator.Generator;
import hu.bme.mit.trainbenchmark.generator.rdf.config.RDFGeneratorConfig;
import hu.bme.mit.trainbenchmark.rdf.RDFHelper;

public class RDFGenerator extends Generator {

	public RDFGenerator(final String args[]) throws ParseException {
		super();
		generatorConfig = rdfGeneratorConfig = new RDFGeneratorConfig(args);
	}

	@Override
	protected String syntax() {
		return "RDF" + (rdfGeneratorConfig.isMetamodel() ? "-metamodel" : "");
	}

	protected RDFGeneratorConfig rdfGeneratorConfig;
	protected BufferedWriter file;

	@Override
	public void initModel() throws IOException {
		// source file
		final String postfix = rdfGeneratorConfig.isMetamodel() ? "-metamodel" : "";
		final String srcFilePath = generatorConfig.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/metamodel/railway" + postfix + ".ttl";

		final File srcFile = new File(srcFilePath);

		// destination file
		final String destFilePath = generatorConfig.getModelPathNameWithoutExtension() + postfix + ".ttl";
		final File destFile = new File(destFilePath);

		// this overwrites the destination file if it exists
		FileUtils.copyFile(srcFile, destFile);

		file = new BufferedWriter(new FileWriter(destFile, true));
	}

	@Override
	public void persistModel() throws IOException {
		file.close();
	}

	@Override
	protected Object createVertex(final int id, final String type, final Map<String, Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges) throws IOException {

		// vertex id and type
		final String triple = String.format(":%s%d a :%s", ID_PREFIX, id, type);
		final StringBuilder vertex = new StringBuilder(triple);

		// (id)-[]->() attributes
		for (final Entry<String, Object> attribute : attributes.entrySet()) {
			final String attributeTriple = String.format(" ;\n\t:%s %s", attribute.getKey(), stringValue(attribute.getValue()));
			vertex.append(attributeTriple);
		}

		// (id)-[]->() edges
		for (final Entry<String, Object> outgoingEdge : outgoingEdges.entrySet()) {
			if (outgoingEdge.getValue() == null) {
				continue;
			}

			final String edgeTriple = String.format(" ;\n\t:%s :%s%s", outgoingEdge.getKey(), ID_PREFIX, outgoingEdge.getValue());
			vertex.append(edgeTriple);
		}

		vertex.append(" .");
		write(vertex.toString());

		// ()-[]->(id) edges
		for (final Entry<String, Object> incomingEdge : incomingEdges.entrySet()) {
			createEdge(incomingEdge.getKey(), incomingEdge.getValue(), id);
		}

		return id;
	}

	@Override
	protected void createEdge(final String label, final Object from, final Object to) throws IOException {
		if (from == null || to == null) {
			return;
		}
		final String triple = String.format(":%s%s :%s :%s%s .", ID_PREFIX, from, label, ID_PREFIX, to);
		write(triple);
	}

	@Override
	protected void setAttribute(final String type, final Object node, final String key, final Object value) throws IOException {
		final String triple = String.format(":%s%s :%s %s", ID_PREFIX, node, key, stringValue(value));
		write(triple + ".");

	}

	private String stringValue(final Object value) {
		if (value instanceof Integer) {
			return String.format("\"%d\"^^xsd:int", value);
		}
		if (value instanceof Enum<?>) {
			final Enum<?> e = (Enum<?>) value;
			return String.format(":%s", RDFHelper.addEnumPrefix(e));
		} else {
			return value.toString();
		}
	}

	public void write(final String s) throws IOException {
		file.write(s + "\n\n");
	}

}
