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

import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.RDF_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.XSD_PREFIX;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.SignalStateKind;
import hu.bme.mit.trainbenchmark.constants.SwitchStateKind;
import hu.bme.mit.trainbenchmark.generator.Generator;
import hu.bme.mit.trainbenchmark.generator.rdf.config.RDFGeneratorConfig;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.cli.ParseException;
import org.openrdf.OpenRDFException;
import org.openrdf.model.Literal;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.helpers.RDFWriterBase;
import org.openrdf.rio.rdfxml.RDFXMLWriter;
import org.openrdf.rio.turtle.TurtleWriter;
import org.openrdf.sail.memory.MemoryStore;

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

	protected long id = 0;
	protected RDFGeneratorConfig rdfGeneratorConfig;
	protected RepositoryConnection con;
	protected Repository myRepository;
	protected ValueFactory vf;
	protected Map<Enum<?>, Resource> resources;

	@Override
	public void initModel() throws IOException {
		try {
			myRepository = new SailRepository(new MemoryStore());
			myRepository.initialize();

			con = myRepository.getConnection();
			con.setNamespace("", BASE_PREFIX);
			con.setNamespace("xsd", XSD_PREFIX);

			vf = con.getValueFactory();
		} catch (final OpenRDFException e) {
			new IOException(e);
		}

		// @formatter:off
		final Resource signalStateKind_Stop    = vf.createURI(BASE_PREFIX + ModelConstants.SIGNALSTATEKIND_STOP);
		final Resource signalStateKind_Failure = vf.createURI(BASE_PREFIX + ModelConstants.SIGNALSTATEKIND_FAILURE);
		final Resource signalStateKind_Go      = vf.createURI(BASE_PREFIX + ModelConstants.SIGNALSTATEKIND_GO);

		final Resource pointStateKind_Left     = vf.createURI(BASE_PREFIX + ModelConstants.POINTSTATEKIND_LEFT);
		final Resource pointStateKind_Straight = vf.createURI(BASE_PREFIX + ModelConstants.POINTSTATEKIND_LEFT);
		final Resource pointStateKind_Right    = vf.createURI(BASE_PREFIX + ModelConstants.POINTSTATEKIND_LEFT);
		final Resource pointStateKind_Failure  = vf.createURI(BASE_PREFIX + ModelConstants.POINTSTATEKIND_LEFT);

		resources = ImmutableMap.<Enum<?>, Resource>builder()
				.put(SignalStateKind.SIGNALSTATEKIND_STOP, signalStateKind_Stop) 
				.put(SignalStateKind.SIGNALSTATEKIND_FAILURE, signalStateKind_Failure) 
				.put(SignalStateKind.SIGNALSTATEKIND_GO, signalStateKind_Go)
				.put(SwitchStateKind.POINT_STATE_KIND_LEFT, pointStateKind_Left)  
				.put(SwitchStateKind.POINT_STATE_KIND_STRAIGHT, pointStateKind_Straight)
				.put(SwitchStateKind.POINT_STATE_KIND_RIGHT, pointStateKind_Right)
				.put(SwitchStateKind.POINT_STATE_KIND_FAILURE, pointStateKind_Failure)
				.build();
		// @formatter:on		
	}

	@Override
	public void persistModel() throws IOException {
		try {
			con.commit();

			String fileName;
			RDFWriterBase writer = null;
			switch (rdfGeneratorConfig.getRdfFormat()) {
			case RDFXML:
				fileName = generatorConfig.getInstanceModelPath() + "/railway" + generatorConfig.getVariant() + generatorConfig.getSize()
						+ (rdfGeneratorConfig.isMetamodel() ? "-metamodel" : "") + ".owl";
				writer = new RDFXMLWriter(new FileWriter(fileName));
				break;
			case TURTLE:
				fileName = generatorConfig.getInstanceModelPath() + "/railway" + generatorConfig.getVariant() + generatorConfig.getSize()
						+ ".ttl";
				writer = new TurtleWriter(new FileWriter(fileName));
				writer.handleNamespace("", BASE_PREFIX);
				break;
			}

			con.export(writer);
			con.close();
		} catch (RepositoryException | RDFHandlerException e) {
			throw new IOException(e);
		}
	}

	protected Resource addIndividual(final String name, final String type) throws IOException {
		id++;
		final Resource owlIndividual = vf.createURI(BASE_PREFIX + "x" + id);
		final URI relation = vf.createURI(RDF_PREFIX + "type");
		final Resource owlClass = vf.createURI(BASE_PREFIX + type);
		final Statement stmt = vf.createStatement(owlIndividual, relation, owlClass);
		try {
			con.add(stmt, (Resource) null);
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
		return owlIndividual;
	}

	protected void addRelation(final String label, final Object source, final Object target) throws IOException {
		final URI relation = vf.createURI(BASE_PREFIX + label);
		final Statement stmt = vf.createStatement((Resource) source, relation, (Resource) target);
		try {
			con.add(stmt, (Resource) null);
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	protected void addDataRelation(final Object source, final String relationName, final Integer value) throws IOException {
		final URI relation = vf.createURI(BASE_PREFIX + relationName);

		final Literal valueLiteral = vf.createLiteral(value);
		final Statement stmt = vf.createStatement((Resource) source, relation, valueLiteral);
		try {
			con.add(stmt, (Resource) null);
		} catch (final RepositoryException e) {
			throw new IOException(e);
		}
	}

	long i = 0;

	@Override
	protected Object createNode(final String type, final Map<String, Object> attributes, final Map<String, Object> outgoingEdges,
			final Map<String, Object> incomingEdges) throws IOException {
		i++;
		final Object node = addIndividual(type + i, type);
		for (final Entry<String, Object> attribute : attributes.entrySet()) {
			final Object value = attribute.getValue();

			if (attribute.getKey().equals(ModelConstants.SWITCHPOSITION_SWITCHSTATE)
					|| attribute.getKey().equals(ModelConstants.SIGNAL_ACTUALSTATE)) {
				addRelation(attribute.getKey(), node, resources.get(value));
			} else if (value instanceof Integer) {
				addDataRelation(node, attribute.getKey(), (Integer) value);
			}
		}

		for (final Entry<String, Object> outgoingEdge : outgoingEdges.entrySet()) {
			if (outgoingEdge.getValue() instanceof Resource) {
				addRelation(outgoingEdge.getKey(), node, outgoingEdge.getValue());
			}
		}

		for (final Entry<String, Object> incomingEdge : incomingEdges.entrySet()) {
			if (incomingEdge.getValue() instanceof Resource) {
				addRelation(incomingEdge.getKey(), incomingEdge.getValue(), node);
			}
		}

		return node;
	}

	@Override
	protected void createEdge(final String label, final Object from, final Object to) throws IOException {
		addRelation(label, from, to);
	}

	@Override
	protected void setAttribute(final String type, final Object node, final String key, final Object value) throws IOException {
		addRelation(key, node, resources.get(value));
	}

}
