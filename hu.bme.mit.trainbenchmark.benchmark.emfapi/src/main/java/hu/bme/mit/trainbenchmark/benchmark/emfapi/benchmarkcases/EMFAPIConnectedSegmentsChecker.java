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
package hu.bme.mit.trainbenchmark.benchmark.emfapi.benchmarkcases;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

public class EMFAPIConnectedSegmentsChecker extends EMFAPIChecker<EMFConnectedSegmentsMatch> {

	public EMFAPIConnectedSegmentsChecker(final EMFDriver emfDriver) {
		super(emfDriver);
	}

	@Override
	public Collection<EMFConnectedSegmentsMatch> check() {
		matches = new ArrayList<>();

		final TreeIterator<EObject> contents = emfDriver.getContainer().eAllContents();
		while (contents.hasNext()) {
			final EObject eObject = contents.next();

			if (eObject instanceof Sensor) {
				// sensor
				final Sensor sensor = (Sensor) eObject;

				// segment1
				for (final TrackElement element1 : sensor.getElements()) {
					if (!(element1 instanceof Segment)) {
						continue;
					}
					final Segment segment1 = (Segment) element1;

					// segment2
					for (final TrackElement element2 : segment1.getConnectsTo()) {
						if (!(element2 instanceof Segment)) {
							continue;
						}
						final Segment segment2 = (Segment) element2;

						// segment3
						for (final TrackElement element3 : segment2.getConnectsTo()) {
							if (!(element3 instanceof Segment)) {
								continue;
							}
							final Segment segment3 = (Segment) element3;

							// segment4
							for (final TrackElement element4 : segment3.getConnectsTo()) {
								if (!(element4 instanceof Segment)) {
									continue;
								}
								final Segment segment4 = (Segment) element4;

								// segment5
								for (final TrackElement element5 : segment4.getConnectsTo()) {
									if (!(element5 instanceof Segment)) {
										continue;
									}
									final Segment segment5 = (Segment) element5;

									// segment6
									for (final TrackElement element6 : segment5.getConnectsTo()) {
										if (!(element6 instanceof Segment)) {
											continue;
										}
										final Segment segment6 = (Segment) element6;

										if (segment6.getSensor() != sensor) {
											continue;
										}

										// segment6.getSensor == sensor
										final EMFConnectedSegmentsMatch csm = new EMFConnectedSegmentsMatch(sensor, segment1, segment2,
												segment3, segment4, segment5, segment6);
										matches.add(csm);
									}
								}
							}
						}
					}
				}
			}
		}

		return matches;
	}

}
