/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
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
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.Position;
import hu.bme.mit.trainbenchmark.constants.Signal;
import hu.bme.mit.trainbenchmark.generator.Generator;
import hu.bme.mit.trainbenchmark.generator.rdf.config.RDFGeneratorConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;

import com.google.common.collect.ImmutableMap;

public class RDFGenerator extends Generator {

	public RDFGenerator(final String args[]) throws ParseException {
		super();
		generatorConfig = rdfGeneratorConfig = new RDFGeneratorConfig(args);
	}

	@Override
	protected String syntax() {
		return "RDF";
	}

	protected RDFGeneratorConfig rdfGeneratorConfig;
	protected BufferedWriter file;

	protected final Map<Object, String> resources = ImmutableMap.<Object, String>builder() //
			.put(Signal.FAILURE, ModelConstants.SIGNAL_FAILURE) //
			.put(Signal.STOP, ModelConstants.SIGNAL_STOP) // 
			.put(Signal.GO, ModelConstants.SIGNAL_GO) //
			.put(Position.LEFT, ModelConstants.SWITCH_LEFT) //
			.put(Position.STRAIGHT, ModelConstants.SWITCH_STRAIGHT) //
			.put(Position.RIGHT, ModelConstants.SWITCH_RIGHT) //
			.put(Position.FAILURE, ModelConstants.SWITCH_FAILURE) //
			.build(); 

	@Override
	public void initModel() throws IOException {
		// source file (DDL operations)
		final String srcFilePath = generatorConfig.getWorkspacePath()
				+ "/hu.bme.mit.trainbenchmark.rdf/src/main/resources/metamodel/header.ttl";
		final File srcFile = new File(srcFilePath);

		// destination file
		final String destFilePath = generatorConfig.getInstanceModelPath() + "/railway" + generatorConfig.getVariant()
				+ generatorConfig.getSize() + ".ttl";
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
		System.out.println(triple);
		write(triple);
	}
	
	@Override
	protected void setAttribute(final String type, final Object node, final String key, final Object value) throws IOException {
		final String triple = String.format(":%s%s :%s %s", ID_PREFIX, node, key, stringValue(value));
		write(triple + ".");
		
	}
		
	private String stringValue(final Object value) {
		String stringValue;
		if (value instanceof Integer) {
			stringValue = String.format("\"%d\"^^xsd:int", value);
		} else {
			stringValue = String.format(":%s", resources.get(value));
		}
		
		return stringValue;
	}

	public void write(final String s) throws IOException {
		file.write(s + "\n\n");
	}
	
}
