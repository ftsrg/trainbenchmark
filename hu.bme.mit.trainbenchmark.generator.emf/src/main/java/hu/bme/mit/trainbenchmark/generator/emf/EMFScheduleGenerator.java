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

package hu.bme.mit.trainbenchmark.generator.emf;

import static hu.bme.mit.trainbenchmark.constants.schedule.ScheduleConstants.TRAIN;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.schedule.ScheduleFactory;
import hu.bme.mit.trainbenchmark.schedule.SchedulePackage;
import hu.bme.mit.trainbenchmark.schedule.Train;
import hu.bme.mit.trainbenchmark.schedule.TrainContainer;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EMFScheduleGenerator extends EMFFormatGenerator {

	protected TrainContainer container;
	protected ScheduleFactory factory;

	public EMFScheduleGenerator(GeneratorConfig generatorConfig) {
		super(generatorConfig);
	}

	@Override
	protected void initializeFactory() {
		factory = ScheduleFactory.eINSTANCE;
	}

	@Override
	protected EObject getContainer() {
		container = factory.createTrainContainer();
		return container;
	}

	@Override
	public Object createVertex(int id, String type, Map<String, Object> attributes,
			Map<String, Object> outgoingEdges, Map<String, Object> incomingEdges)
			throws IOException {
		final EClass clazz = (EClass) SchedulePackage.eINSTANCE.getEClassifier(type);
		final EObject node = factory.create(clazz);
		for (final Entry<String, Object> attribute : attributes.entrySet()) {
			setAttribute(clazz, node, attribute.getKey(), attribute.getValue());
		}

		switch (type) {
		case TRAIN:
			container.getTrains().add((Train) node);
		}

		for (final Entry<String, Object> outgoingEdge : outgoingEdges.entrySet()) {
			createEdge(outgoingEdge.getKey(), node, outgoingEdge.getValue());
		}

		for (final Entry<String, Object> incomingEdge : incomingEdges.entrySet()) {
			createEdge(incomingEdge.getKey(), incomingEdge.getValue(), node);
		}

		return node;
	}

	@Override
	public void createEdge(String label, Object from, Object to) throws IOException {
		if (from == null || to == null) {
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
	public void setAttribute(String type, Object node, String key, Object value) throws IOException {
		final EClass clazz = (EClass) SchedulePackage.eINSTANCE.getEClassifier(type);
		setAttribute(clazz, (EObject) node, key, value);
	}

	protected void setAttribute(final EClass clazz, final EObject node, final String key, Object value) {
		final EStructuralFeature feature = clazz.getEStructuralFeature(key);
		node.eSet(feature, value);
	}

}
