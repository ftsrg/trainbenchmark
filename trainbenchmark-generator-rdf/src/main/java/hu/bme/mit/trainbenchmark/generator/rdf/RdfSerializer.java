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

import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.rdf.config.RdfGeneratorConfig;
import hu.bme.mit.trainbenchmark.rdf.RdfConstants;
import hu.bme.mit.trainbenchmark.rdf.RdfHelper;
import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.ID_PREFIX;

/**
 * This is really horrible spaghetti code. Sorry. At least it's decently quick. (@szarnyasg)
 */
public class RdfSerializer extends ModelSerializer<RdfGeneratorConfig> {

	protected BufferedWriter file;
	protected String RDF_METAMODEL_DIR = "/trainbenchmark-format-rdf/src/main/resources/metamodel/";

	public RdfSerializer(final RdfGeneratorConfig generatorConfig) {
		super(generatorConfig);
	}

	@Override
	public String syntax() {
		return "RDF-" + gc.getFormat().getExtension() + gc.getModelFlavor();
	}

	@Override
	public void initModel() throws IOException {
		// source file
		final String modelFlavor = gc.getModelFlavor();
		final String extension = gc.getExtension();
		final String postfix = modelFlavor + "." + extension;
		final String srcFilePath = gc.getConfigBase().getWorkspaceDir() + RDF_METAMODEL_DIR + "railway" + postfix;
		final File srcFile = new File(srcFilePath);

		// destination file
		final String destFilePath = gc.getConfigBase().getModelPathWithoutExtension() + postfix;
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
	public Object createVertex(final int id, final String type, final Map<String, ? extends Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges) throws IOException {
		final String xsdInteger;
		final String rdfType;
		switch (gc.getFormat()) {
			case NTRIPLES:
				rdfType = "<http://www.semanticweb.org/ontologies/2015/trainbenchmark#type>";
				xsdInteger = "<http://www.w3.org/2001/XMLSchema#int>";
				break;
			case TURTLE:
				rdfType = "a";
				xsdInteger = "xsd:int";
				break;
			default:
				throw new UnsupportedOperationException("RDF format " + gc.getFormat() + " not supported");
		}

		// vertex id and type
		final String triple = String.format("%s %s %s",
			prefixed(ID_PREFIX+id),
			rdfType,
			prefixed(type)
		);
		final StringBuilder vertex = new StringBuilder(triple);

		final String linePrefix;
		switch (gc.getFormat()) {
		case NTRIPLES:
			linePrefix = String.format(" .\n%s ", prefixed(ID_PREFIX+id));
			break;
		case TURTLE:
			linePrefix = " ;\n\t";
			break;
		default:
			throw new UnsupportedOperationException("RDF format " + gc.getFormat() + " not supported");
		}

		// if required, we manually insert the inferred triples
		if (gc.isInferred()) {
			if (ModelConstants.SUPERTYPES.containsKey(type)) {
				final String superType = ModelConstants.SUPERTYPES.get(type);
				final String superTypeTriple = String.format("%s%s %s", linePrefix, rdfType, prefixed(superType));
				vertex.append(superTypeTriple);
			}
		}

		// (id)-[]->() attributes
		for (final Entry<String, ? extends Object> attribute : attributes.entrySet()) {
			final String attributeTriple = String.format("%s%s %s", linePrefix,
					prefixed(attribute.getKey()),
					stringValue(attribute.getValue(), xsdInteger));
			vertex.append(attributeTriple);
		}

		// (id)-[]->() edges
		for (final Entry<String, Object> outgoingEdge : outgoingEdges.entrySet()) {
			if (outgoingEdge.getValue() == null) {
				continue;
			}

			final String edgeTriple = String.format("%s%s %s",
				linePrefix,
				prefixed(outgoingEdge.getKey()),
				prefixed(ID_PREFIX+outgoingEdge.getValue())
			);
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
	public void createEdge(final String label, final Object from, final Object to) throws IOException {
		if (from == null || to == null) {
			return;
		}
		final String triple = String.format("%s %s %s .",
			prefixed(ID_PREFIX+from),
			prefixed(label),
			prefixed(ID_PREFIX+to)
		);
		write(triple);
	}

	protected void write(final String s) throws IOException {
		switch (gc.getFormat()) {
		case NTRIPLES:
			file.write(s + "\n");
			break;
		case TURTLE:
			file.write(s + "\n\n");
			break;
		default:
			throw new UnsupportedOperationException("RDF format " + gc.getFormat() + " not supported");
		}
	}

	protected String stringValue(final Object value, final String xsdInteger) {
		if (value instanceof Boolean) {
			final String boolString = Boolean.toString((Boolean) value);
			switch (gc.getFormat()) {
				case TURTLE:
					return boolString;
				case NTRIPLES:
					return String.format("\"%s\"^^<xs:boolean>", boolString);
			}
		}
		if (value instanceof Integer) {
			return String.format("\"%d\"^^" + xsdInteger, value);
		}
		if (value instanceof Enum<?>) {
			final Enum<?> e = (Enum<?>) value;
			return prefixed(RdfHelper.addEnumPrefix(e));
		} else {
			return value.toString();
		}
	}

	protected String prefixed(final String s) {
		switch (gc.getFormat()) {
			case TURTLE:
				return ":" + s;
			case NTRIPLES:
				return String.format("<%s%s>", RdfConstants.BASE_PREFIX, s);
		}
		throw new UnsupportedOperationException("RDF format " + gc.getFormat() + " not supported");
	}

}
