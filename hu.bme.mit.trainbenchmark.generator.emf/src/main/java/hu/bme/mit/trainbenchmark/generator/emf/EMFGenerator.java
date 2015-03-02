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

package hu.bme.mit.trainbenchmark.generator.emf;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SIGNAL_CURRENTSTATE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCHPOSITION_SWITCHSTATE;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH_CURRENTSTATE;
import hu.bme.mit.trainbenchmark.emf.EMFUtil;
import hu.bme.mit.trainbenchmark.emf.FileBroker;
import hu.bme.mit.trainbenchmark.generator.Generator;
import hu.bme.mit.trainbenchmark.generator.emf.config.EMFGeneratorConfig;
import hu.bme.mit.trainbenchmark.railway.RailwayContainer;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.RailwayFactory;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.cli.ParseException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class EMFGenerator extends Generator {

	public EMFGenerator(final String args[]) throws ParseException {
		super();
		generatorConfig = emfGeneratorConfig = new EMFGeneratorConfig(args);
	}

	@Override
	protected String syntax() {
		return "EMF";
	}

	protected EMFGeneratorConfig emfGeneratorConfig;
	protected Resource resource;
	protected RailwayFactory factory;
	protected RailwayContainer cont;

	@Override
	public void initModel() {
		final String uuidStr = emfGeneratorConfig.isGenUUID() ? "uuid-" : "";

		final String fileName = generatorConfig.getInstanceModelPath() + "/railway" + generatorConfig.getVariant() + uuidStr
				+ generatorConfig.getSize() + ".emf";
		final URI resourceURI = FileBroker.getEMFUri(fileName);

		if (emfGeneratorConfig.isGenUUID()) {
			EMFUtil.registerUUIDXMIResourceFactory();
		}

		final ResourceSet resourceSet = new ResourceSetImpl();

		resource = resourceSet.createResource(resourceURI);
		resource.getContents().clear();

		factory = RailwayFactory.eINSTANCE;
		cont = factory.createRailwayContainer();
		resource.getContents().add(cont);
	}

	@Override
	public void persistModel() throws IOException {
		resource.save(null);
	}

	@Override
	protected Object createVertex(final long id, final String type, final Map<String, Object> attributes, final Map<String, Object> outgoingEdges,
			final Map<String, Object> incomingEdges) throws IOException {
		final EClass clazz = (EClass) RailwayPackage.eINSTANCE.getEClassifier(type);
		final RailwayElement railwayElement = (RailwayElement) RailwayFactory.eINSTANCE.create(clazz);
		railwayElement.setId(id);
		
		for (final Entry<String, Object> attribute : attributes.entrySet()) {
			setAttribute(clazz, railwayElement, attribute.getKey(), attribute.getValue());
		}
		cont.getContains().add(railwayElement);

		for (final Entry<String, Object> outgoingEdge : outgoingEdges.entrySet()) {
			createEdge(outgoingEdge.getKey(), railwayElement, outgoingEdge.getValue());
		}

		for (final Entry<String, Object> incomingEdge : incomingEdges.entrySet()) {
			createEdge(incomingEdge.getKey(), incomingEdge.getValue(), railwayElement);
		}

		return railwayElement;
	}

	@Override
	protected void createEdge(final String label, final Object from, final Object to) throws IOException {
		if (to instanceof Long) {
			return;
		}

		final EObject objectFrom = (EObject) from;
		final EStructuralFeature edgeType = objectFrom.eClass().getEStructuralFeature(label);

		if (edgeType.isMany()) {
			final List<Object> l = (List<Object>) objectFrom.eGet(edgeType);
			l.add(to);
		} else {
			objectFrom.eSet(edgeType, to);
		}
	}

	@Override
	protected void setAttribute(final String type, final Object node, final String key, final Object value) throws IOException {
		final EClass clazz = (EClass) RailwayPackage.eINSTANCE.getEClassifier(type);
		setAttribute(clazz, (RailwayElement) node, key, value);
	}

	protected void setAttribute(final EClass clazz, final RailwayElement node, final String key, Object value) {
		// change the enum value from the
		// hu.bme.mit.trainbenchmark.constants.SignalState enum to the
		// hu.bme.mit.trainbenchmark.SignalState enum
		if (SIGNAL_CURRENTSTATE.equals(key)) {
			final int ordinal = ((hu.bme.mit.trainbenchmark.constants.SignalState) value).ordinal();
			value = hu.bme.mit.trainbenchmark.railway.SignalState.get(ordinal);
		} else if (SWITCH_CURRENTSTATE.equals(key) || SWITCHPOSITION_SWITCHSTATE.equals(key)) {
			final int ordinal = ((hu.bme.mit.trainbenchmark.constants.SwitchState) value).ordinal();
			value = hu.bme.mit.trainbenchmark.railway.SwitchState.get(ordinal);
		}

		final EStructuralFeature feature = clazz.getEStructuralFeature(key);
		node.eSet(feature, value);
	}

}
