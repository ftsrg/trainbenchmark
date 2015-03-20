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
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.ID_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.RDF_PREFIX;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.XSD_PREFIX;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.constants.Signal;
import hu.bme.mit.trainbenchmark.constants.Position;
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

public class RDFGeneratorSail extends Generator {

	public RDFGeneratorSail(final String args[]) throws ParseException {
		super();
		generatorConfig = rdfGeneratorConfig = new RDFGeneratorConfig(args);
	}

	@Override
	protected String syntax() {
		return "RDF";
	}

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
		final Resource signalState_Stop    = vf.createURI(BASE_PREFIX + ModelConstants.SIGNAL_STOP);
		final Resource signalState_Failure = vf.createURI(BASE_PREFIX + ModelConstants.SIGNAL_FAILURE);
		final Resource signalState_Go      = vf.createURI(BASE_PREFIX + ModelConstants.SIGNAL_GO);

		final Resource switchState_Left     = vf.createURI(BASE_PREFIX + ModelConstants.SWITCH_LEFT);
		final Resource switchState_Straight = vf.createURI(BASE_PREFIX + ModelConstants.SWITCH_LEFT);
		final Resource switchState_Right    = vf.createURI(BASE_PREFIX + ModelConstants.SWITCH_LEFT);
		final Resource switchState_Failure  = vf.createURI(BASE_PREFIX + ModelConstants.SWITCH_LEFT);
	
		resources = ImmutableMap.<Enum<?>, Resource>builder()
				.put(Signal.STOP, signalState_Stop) 
				.put(Signal.FAILURE, signalState_Failure) 
				.put(Signal.GO, signalState_Go)
				.put(Position.LEFT, switchState_Left)  
				.put(Position.STRAIGHT, switchState_Straight)
				.put(Position.RIGHT, switchState_Right)
				.put(Position.FAILURE, switchState_Failure)
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
						+ (rdfGeneratorConfig.isMetamodel() ? "-metamodel" : "") + ".rdf";
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
		final Resource owlIndividual = vf.createURI(BASE_PREFIX + ID_PREFIX + id);
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

	@Override
	protected Object createVertex(final int id, final String type, final Map<String, Object> attributes, final Map<String, Object> outgoingEdges,
			final Map<String, Object> incomingEdges) throws IOException {
		final Object node = addIndividual(type + id, type);
		for (final Entry<String, Object> attribute : attributes.entrySet()) {
			final Object value = attribute.getValue();

			if (attribute.getKey().equals(ModelConstants.POSITION)
					|| attribute.getKey().equals(ModelConstants.SIGNAL)) {
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
