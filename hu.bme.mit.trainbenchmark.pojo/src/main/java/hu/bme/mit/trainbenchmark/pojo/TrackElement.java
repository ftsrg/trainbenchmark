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

public class TrackElement extends ListenableObject {

	private long id = -1;

	private List<Sensor> sensors;
	private List<TrackElement> connectsTo;

	public List<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

	public boolean hasSensors() {
		return sensors != null && !sensors.isEmpty();
	}

	public void addSensor(Sensor sensor) {
		if (sensors == null) {
			sensors = new ArrayList<>();
		}
		sensors.add(sensor);
	}

	public void removeSensor(Sensor sensor) {
		sensors.remove(sensor);
	}

	public void addSensorBidirectionallyAndFire(Sensor sensor) {
		addSensor(sensor);
		sensor.addTrackElement(this);
		firePropertyChange(sensor, "trackElements", sensor.getTrackElements(), sensor.getTrackElements());
		firePropertyChange("sensors", sensors, sensors);
	}

	public void clearSensors() {
		for (Sensor sensor : sensors) {
			sensor.removeTrackElement(this);
			firePropertyChange(sensor, "trackElements", sensor.getTrackElements(), sensor.getTrackElements());
		}
		sensors.clear();
		firePropertyChange("sensors", sensors, sensors);
	}

	public long getId() {
		if (id < 0) {
			id = IdGenerator.next();
		}
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<TrackElement> getConnectsTo() {
		return connectsTo;
	}

	public void setConnectsTo(List<TrackElement> connectsTo) {
		this.connectsTo = connectsTo;
	}

	public void addConnectsTo(TrackElement element) {
		if (connectsTo == null) {
			connectsTo = new ArrayList<>();
		}
		connectsTo.add(element);
	}
}
