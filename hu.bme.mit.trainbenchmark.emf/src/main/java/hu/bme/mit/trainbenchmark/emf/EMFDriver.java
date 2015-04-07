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
import hu.bme.mit.trainbenchmark.railway.Position;
import hu.bme.mit.trainbenchmark.railway.RailwayContainer;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.Switch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
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
	public Comparator<M> getMatchComparator() {
		return null;
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

	// user

	@Override
	public void posLengthUser(final Collection<RailwayElement> segments) throws IOException {
		for (final RailwayElement railwayElement : segments) {
			final Segment segment = (Segment) railwayElement;
			segment.setLength(0);
		}

	}

	@Override
	public void routeSensorUser(final Collection<RailwayElement> routes) throws IOException {
		for (final RailwayElement railwayElement : routes) {
			final Route route = (Route) railwayElement;
			final EList<Sensor> definedBys = route.getDefinedBy();

			// delete the first edge
			if (definedBys.size() > 0) {
				definedBys.remove(0);
			}
		}
	}

	@Override
	public void semaphoreNeighborUser(final Collection<RailwayElement> routes) throws IOException {
		for (final RailwayElement railwayElement : routes) {
			final Route route = (Route) railwayElement;
			route.setEntry(null);
		}
	}

	@Override
	public void switchSensorUser(final Collection<RailwayElement> switches) throws IOException {
		for (final RailwayElement railwayElement : switches) {
			final Switch sw = (Switch) railwayElement;
			sw.setSensor(null);
		}
	}

	@Override
	public void switchSetUser(final Collection<RailwayElement> switches) throws IOException {
		for (final RailwayElement railwayElement : switches) {
			final Switch sw = (Switch) railwayElement;
			final Position currentPosition = sw.getCurrentPosition();
			final Position newCurrentPosition = Position.get((currentPosition.ordinal() + 1) % Position.VALUES.size());
			sw.setCurrentPosition(newCurrentPosition);
		}

	}
}
