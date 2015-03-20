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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.PropertyOperation;
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

import org.eclipse.emf.common.util.TreeIterator;
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
	public void read(final String modelPath) throws IOException {
		throw new UnsupportedOperationException("This should be implemented for all EMF-based query engines");
	}

	@Override
	public List<RailwayElement> runQuery() throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Comparator<RailwayElement> getComparator() {
		return new EMFComparator();
	}

	// create

	@Override
	public void insertVertexWithEdge(final List<RailwayElement> sourceVertices, final String sourceVertexType,
			final String targetVertexType, final String edgeType) {

		// get types
		final EClass targetClass = (EClass) RailwayPackage.eINSTANCE.getEClassifier(targetVertexType);
		final EClass sourceClass = (EClass) RailwayPackage.eINSTANCE.getEClassifier(sourceVertexType);
		final EStructuralFeature feature = sourceClass.getEStructuralFeature(edgeType);

		for (final RailwayElement sourceVertex : sourceVertices) {
			insertVertexWithEdge(sourceVertex, targetClass, feature);
		}
	}

	@Override
	public RailwayElement insertVertexWithEdge(final RailwayElement sourceVertex, final String sourceVertexType,
			final String targetVertexType, final String edgeType) throws IOException {
		final EClass targetClass = (EClass) RailwayPackage.eINSTANCE.getEClassifier(targetVertexType);
		final EClass sourceClass = (EClass) RailwayPackage.eINSTANCE.getEClassifier(sourceVertexType);
		final EStructuralFeature feature = sourceClass.getEStructuralFeature(edgeType);

		return insertVertexWithEdge(sourceVertex, targetClass, feature);
	}

	protected RailwayElement insertVertexWithEdge(final RailwayElement sourceVertex, final EClass targetClass,
			final EStructuralFeature feature) {
		final RailwayFactory factory = RailwayFactory.eINSTANCE;
		final EObject targetObject = factory.create(targetClass);

		// set reference to source object
		sourceVertex.eSet(feature, targetObject);

		return (RailwayElement) targetObject;
	}

	@Override
	public void insertEdge(final RailwayElement sourceVertex, final String sourceVertexType, final RailwayElement targetVertex,
			final String edgeType) {
		final EClass sourceClass = (EClass) RailwayPackage.eINSTANCE.getEClassifier(sourceVertexType);
		final EStructuralFeature feature = sourceClass.getEStructuralFeature(edgeType);

		@SuppressWarnings("unchecked")
		final AbstractList<EObject> references = (AbstractList<EObject>) sourceVertex.eGet(feature);
		references.add(targetVertex);
	}

	// read

	@Override
	public List<RailwayElement> collectVertices(final String type) {
		final List<RailwayElement> vertices = new ArrayList<>();

		final EClass clazz = (EClass) RailwayPackage.eINSTANCE.getEClassifier(type);

		final TreeIterator<EObject> contents = container.eAllContents();
		while (contents.hasNext()) {
			final EObject eObject = contents.next();

			// if t's type is a descendant of clazz
			if (clazz.isSuperTypeOf(eObject.eClass())) {
				vertices.add((RailwayElement) eObject);
			}
		}

		return vertices;
	}

	@Override
	public List<RailwayElement> collectOutgoingConnectedVertices(final RailwayElement sourceVertex, final String sourceVertexType,
			final String targetVertexType, final String edgeType) {
		final List<RailwayElement> vertices = new ArrayList<>();
		final EClass sourceClass = (EClass) RailwayPackage.eINSTANCE.getEClassifier(sourceVertexType);
		final EStructuralFeature feature = sourceClass.getEStructuralFeature(edgeType);

		@SuppressWarnings("unchecked")
		final AbstractList<EObject> references = (AbstractList<EObject>) sourceVertex.eGet(feature);
		final EClass clazz = (EClass) RailwayPackage.eINSTANCE.getEClassifier(targetVertexType);
		for (final EObject ref : references) {
			if (clazz.isSuperTypeOf(ref.eClass())) {
				vertices.add((RailwayElement) ref);
			}
		}

		return vertices;
	}

	// update

	@Override
	public void updateProperties(final List<RailwayElement> vertices, final String vertexType, final String propertyName,
			final PropertyOperation attributeOperation) {
		final EClass clazz = (EClass) RailwayPackage.eINSTANCE.getEClassifier(vertexType);
		final EStructuralFeature feature = clazz.getEStructuralFeature(propertyName);

		for (final RailwayElement vertex : vertices) {
			final Integer value = (Integer) vertex.eGet(feature);
			final int newValue = attributeOperation.op(value);
			vertex.eSet(feature, newValue);
		}
	}

	// delete

	@Override
	public void deleteAllIncomingEdges(final List<RailwayElement> vertices, final String sourceVertexType, final String edgeType) {
		final EClass sourceClass = (EClass) RailwayPackage.eINSTANCE.getEClassifier(sourceVertexType);
		final EStructuralFeature feature = sourceClass.getEStructuralFeature(edgeType);
		final EReference reference = (EReference) feature;
		final EReference oppositeReference = reference.getEOpposite();

		for (final RailwayElement vertex : vertices) {
			@SuppressWarnings("unchecked")
			final AbstractList<EObject> outgoingEdges = (AbstractList<EObject>) vertex.eGet(oppositeReference);
			outgoingEdges.clear();
		}
	}

	@Override
	public void deleteAllOutgoingEdges(final List<RailwayElement> vertices, final String vertexType, final String edgeType) {
		final EClass clazz = (EClass) RailwayPackage.eINSTANCE.getEClassifier(vertexType);
		final EStructuralFeature feature = clazz.getEStructuralFeature(edgeType);

		for (final RailwayElement vertex : vertices) {
			@SuppressWarnings("unchecked")
			final AbstractList<EObject> features = (AbstractList<EObject>) vertex.eGet(feature);
			features.clear();
		}
	}

	@Override
	public void deleteOneOutgoingEdge(final List<RailwayElement> vertices, final String vertexType, final String edgeType) {
		final EClass clazz = (EClass) RailwayPackage.eINSTANCE.getEClassifier(vertexType);
		final EStructuralFeature feature = clazz.getEStructuralFeature(edgeType);

		for (final RailwayElement vertex : vertices) {
			if (feature.isMany()) {
				@SuppressWarnings("unchecked")
				final AbstractList<EObject> features = (AbstractList<EObject>) vertex.eGet(feature);

				if (features.size() > 0) {
					final RailwayElement e = (RailwayElement) features.get(0);
					features.remove(0);
					container.getInvalids().add(e);
				}
			} else {
				vertex.eSet(feature, null);
				container.getInvalids().add(vertex);
			}
		}
	}

	@Override
	public void deleteSingleOutgoingEdge(final List<RailwayElement> vertices, final String vertexType, final String edgeType) {
		deleteOneOutgoingEdge(vertices, vertexType, edgeType);
	}

	@Override
	public void deleteVertex(final RailwayElement vertex, final String vertexType) throws IOException {
		// TODO
		// container.getContains().remove(vertex);
	}

	// utility

	public RailwayContainer getContainer() {
		return container;
	}

	public Resource getResource() {
		return resource;
	}

	@Override
	public void deleteVertex(final Long vertex) throws IOException {
		// TODO Auto-generated method stub

	}

}
