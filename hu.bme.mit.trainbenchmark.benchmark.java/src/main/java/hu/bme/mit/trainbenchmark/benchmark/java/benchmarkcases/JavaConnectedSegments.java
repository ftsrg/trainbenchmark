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
//package hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases;
//
//import hu.bme.mit.trainbenchmark.benchmark.java.matches.JavaConnectedSegmentsMatch;
//import hu.bme.mit.trainbenchmark.railway.Segment;
//import hu.bme.mit.trainbenchmark.railway.Sensor;
//import hu.bme.mit.trainbenchmark.railway.TrackElement;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collection;
//
//import org.eclipse.emf.common.util.EList;
//import org.eclipse.emf.common.util.TreeIterator;
//import org.eclipse.emf.ecore.EObject;
//
//public class JavaConnectedSegments extends JavaBenchmarkCase<JavaConnectedSegmentsMatch> {
//
//	private final int connectedLimit = 5;
//
//	@Override
//	protected Collection<JavaConnectedSegmentsMatch> check() throws IOException {
//		matches = new ArrayList<>();
//
//		final TreeIterator<EObject> contents = container.eAllContents();
//		while (contents.hasNext()) {
//			final EObject eObject = contents.next();
//
//			if (eObject instanceof Sensor) {
//				final Sensor sensor = (Sensor) eObject;
//
//				final EList<TrackElement> trackElements = sensor.getElements();
//				for (final TrackElement element : trackElements) {
//					if (element instanceof Segment) {
//						final Segment firstSegment = (Segment) element;
//						final Segment lastSegment = getConnected(firstSegment, 0);
//
//						if (lastSegment == null) {
//							continue;
//						}
//
//						final Sensor nextSensor = lastSegment.getSensor();
//
//						if (sensor == nextSensor) {
//							matches.add(null);
//						}
//					}
//				}
//			}
//		}
//
//		return matches;
//	}
//
//	protected Segment getConnected(final Segment source, final int index) {
//		if (index == connectedLimit) {
//			return source;
//		}
//
//		final EList<TrackElement> neighbors = source.getConnectsTo();
//		for (final TrackElement element : neighbors) {
//			if (element instanceof Segment) {
//				return getConnected((Segment) element, index + 1);
//			}
//		}
//		return null;
//	}
//
// }
