/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.emf.matches.EMFMatch;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

public class JavaConnectedSegments extends JavaChecker {

	public JavaConnectedSegments(final EMFDriver emfDriver) {
		super(emfDriver);
	}

	@Override
	public Collection<EMFMatch> check() {
		matches = new ArrayList<>();

		final TreeIterator<EObject> contents = emfDriver.getContainer().eAllContents();
		while (contents.hasNext()) {
			final EObject eObject = contents.next();

			if (eObject instanceof Sensor) {
				//sensor
				final Sensor sensor1 = (Sensor) eObject;

				// segment1
				for (final TrackElement element1 : sensor1.getElements()) {
					if (element1 instanceof Segment) {					
						final Segment segment1 = (Segment) element1;
						
						// segment2
						for (final TrackElement element2 : segment1.getConnectsTo()) {
							if (element2 instanceof Segment) {
								final Segment segment2 = (Segment) element2;
								
								
							}							
						}					
						
						
						
						if (lastSegment == null) {
							continue;
						}

						final Sensor nextSensor = lastSegment.getSensor();

						if (sensor1 == nextSensor) {
							matches.add(null);
						}
						
						final ConnectedSegmentsMatch csm = new EMFConnectedSegmentsMatch(sensor1, sensor2, segment1, segment2, segment3, segment4, segment5, segment6);
					}
				}
			}
		}

		return matches;
	}

	protected Segment getConnected(final Segment source, final int index) {
		if (index == connectedLimit) {
			return source;
		}

		final EList<TrackElement> neighbors = source.getConnectsTo();
		for (final TrackElement element : neighbors) {
			if (element instanceof Segment) {
				return getConnected((Segment) element, index + 1);
			}
		}
		return null;
	}

}
