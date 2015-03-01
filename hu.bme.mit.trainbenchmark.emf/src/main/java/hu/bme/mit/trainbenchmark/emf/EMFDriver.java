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
package hu.bme.mit.trainbenchmark.emf;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.AttributeOperation;
import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayContainer;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.RailwayFactory;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class EMFDriver extends DatabaseDriver<RailwayElement> {
	
	protected RailwayContainer container;
	protected Resource resource;

	public EMFDriver(final String modelPath) {
		RailwayPackage.eINSTANCE.eClass();

		final URI resourceURI = FileBroker.getEMFUri(modelPath);
		final ResourceSet resourceSet = new ResourceSetImpl();
		resource = resourceSet.getResource(resourceURI, true);

		if (resource.getContents().size() > 0 && resource.getContents().get(0) instanceof RailwayContainer) {
			container = (RailwayContainer) resource.getContents().get(0);
		}
	}

	@Override
	public List<RailwayElement> collectVertices(final String type) {
		final List<RailwayElement> vertices = new ArrayList<>();

		final EClass clazz = (EClass) RailwayPackage.eINSTANCE.getEClassifier(type);
		for (final RailwayElement t : container.getContains()) {
			// if t's type is a descendant of clazz
			if (clazz.isSuperTypeOf(t.eClass())) {
				vertices.add(t);
			}
		}

		return vertices;
	}

	@Override
	public void deleteAllOutgoingEdges(final Object vertex, final String edgeType) {
		final EObject object = (EObject) vertex;
		final EStructuralFeature feature = object.eClass().getEStructuralFeature(edgeType);
		final AbstractList<EObject> features = (AbstractList<EObject>) object.eGet(feature);
		features.clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteAllIncomingEdges(final Object vertex, final String edgeType, final String sourceVertexType) {
		final EObject object = (EObject) vertex;

		final EClass clazz = (EClass) RailwayPackage.eINSTANCE.getEClassifier(sourceVertexType);
		final EStructuralFeature feature = clazz.getEStructuralFeature(edgeType);
		final EReference reference = (EReference) feature;
		final EReference oppositeReference = reference.getEOpposite();

		final AbstractList<EObject> outgoingEdges = (AbstractList<EObject>) object.eGet(oppositeReference);
		outgoingEdges.clear();
	}

	@Override
	public void updateProperty(final Object vertex, final String vertexType, final String propertyName,
			final AttributeOperation attributeOperation) {
		final EObject object = (EObject) vertex;
		final EStructuralFeature feature = object.eClass().getEStructuralFeature(propertyName);

		final Integer value = (Integer) object.eGet(feature);
		final int newValue = attributeOperation.op(value);
		object.eSet(feature, newValue);
	}

	@Override
	public void deleteOneOutgoingEdge(final Object vertex, final String edgeType) {
		final EObject object = (EObject) vertex;
		final EStructuralFeature feature = object.eClass().getEStructuralFeature(edgeType);

		if (feature.isMany()) {
			final AbstractList<EObject> features = (AbstractList<EObject>) object.eGet(feature);

			if (features.size() > 0) {
				features.remove(0);
			}
		} else {
			object.eSet(feature, null);
		}
	}

	@Override
	public void deleteOutgoingEdge(final Object vertex, final String vertexType, final String edgeType) {
		deleteOneOutgoingEdge(vertex, edgeType);
	}

	@Override
	public void insertVertexWithEdge(final Object sourceVertex, final String sourceVertexType, final String targetVertexType,
			final String edgeType) {
		// create target object
		final RailwayFactory factory = RailwayFactory.eINSTANCE;
		final EClass targetClass = (EClass) RailwayPackage.eINSTANCE.getEClassifier(targetVertexType);
		final EObject targetObject = factory.create(targetClass);

		// set reference to source object
		final EClass sourceClass = (EClass) RailwayPackage.eINSTANCE.getEClassifier(sourceVertexType);
		final EStructuralFeature feature = sourceClass.getEStructuralFeature(edgeType);
		final EObject sourceObject = (EObject) sourceVertex;
		final AbstractList<EObject> references = (AbstractList<EObject>) sourceObject.eGet(feature);
		references.add(targetObject);
	}
	
	public RailwayContainer getRoot() {
		return container;
	}

	public Resource getResource() {
		return resource;
	}

	@Override
	public void read(final String modelPath) throws IOException {
		
	}

	@Override
	public List<RailwayElement> runQuery() throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Comparator<RailwayElement> getComparator() {
		return new EMFComparator();
	}

}
