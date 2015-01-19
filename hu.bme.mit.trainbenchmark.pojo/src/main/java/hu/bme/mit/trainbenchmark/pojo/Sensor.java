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

package hu.bme.mit.trainbenchmark.pojo;

import java.util.ArrayList;
import java.util.List;

public class Sensor extends ListenableObject {

	private List<TrackElement> trackElements;

	public List<TrackElement> getTrackElements() {
		return trackElements;
	}

	public void setTrackElements(List<TrackElement> trackElements) {
		this.trackElements = trackElements;
	}

	public void addTrackElement(TrackElement trackElement) {
		if (trackElements == null) {
			trackElements = new ArrayList<>();
		}
		trackElements.add(trackElement);
	}

	public void removeTrackElement(TrackElement trackElement) {
		trackElements.remove(trackElement);
	}

	public void clearTrackElements() {
		for (TrackElement trackElement : trackElements) {
			trackElement.removeSensor(this);
			firePropertyChange(trackElement, "sensors", trackElement.getSensors(), trackElement.getSensors());
		}
		trackElements.clear();
		firePropertyChange("trackElements", trackElements, trackElements);
	}
}
