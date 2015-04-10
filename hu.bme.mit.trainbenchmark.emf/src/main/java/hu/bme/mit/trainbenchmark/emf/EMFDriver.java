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

import hu.bme.mit.trainbenchmark.benchmark.driver.DatabaseDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayContainer;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public abstract class EMFDriver<M> extends DatabaseDriver<M, RailwayElement> {

	protected RailwayContainer container;
	protected Resource resource;
	protected Comparator<RailwayElement> elementComparator;

	public EMFDriver(final String modelPath) {
		RailwayPackage.eINSTANCE.eClass();

		final URI resourceURI = FileBroker.getEMFUri(modelPath);
		final ResourceSet resourceSet = new ResourceSetImpl();
		resource = resourceSet.getResource(resourceURI, true);

		if (resource.getContents().size() > 0 && resource.getContents().get(0) instanceof RailwayContainer) {
			container = (RailwayContainer) resource.getContents().get(0);
		}

		elementComparator = new RailwayElementComparator();
	}

	@Override
	public void read(final String modelPath) throws IOException {
		throw new UnsupportedOperationException("This should be implemented for all EMF-based query engines");
	}

	@Override
	public Comparator<RailwayElement> getElementComparator() {
		return elementComparator;
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

	// utility

	public RailwayContainer getContainer() {
		return container;
	}

	public Resource getResource() {
		return resource;
	}
}
