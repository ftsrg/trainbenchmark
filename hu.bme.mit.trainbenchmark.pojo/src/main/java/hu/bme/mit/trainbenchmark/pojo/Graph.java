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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Graph implements Iterable<Object> {

	private transient ChangeNotifier changeNotifier;

	private Set<Segment> segments = new HashSet<>();
	private Set<Switch> switches = new HashSet<>();
	private Set<SwitchPosition> switchPositions = new HashSet<>();
	private Set<Signal> signals = new HashSet<>();
	private Set<Route> routes = new HashSet<>();
	private Set<Sensor> sensors = new HashSet<>();

	public void add(Object object) {
		if (object instanceof Segment) {
			segments.add((Segment) object);
		} else if (object instanceof Switch) {
			switches.add((Switch) object);
		} else if (object instanceof SwitchPosition) {
			switchPositions.add((SwitchPosition) object);
		} else if (object instanceof Signal) {
			signals.add((Signal) object);
		} else if (object instanceof Route) {
			routes.add((Route) object);
		} else if (object instanceof Sensor) {
			sensors.add((Sensor) object);
		} else {
			throw new RuntimeException("Unknown object type: " + object.getClass());
		}

		registerNotifier(object);
	}

	public void wireBidirectionConnections() {
		for (Segment segment : segments) {
			if (segment.getSensors() != null) {
				for (Sensor sensor : segment.getSensors()) {
					sensor.addTrackElement(segment);
				}
			}
		}
		for (Switch switchObj : switches) {
			if (switchObj.getSensors() != null) {
				for (Sensor sensor : switchObj.getSensors()) {
					sensor.addTrackElement(switchObj);
				}
			}
		}
	}

	public Collection<Segment> getSegments() {
		return segments;
	}

	public Collection<Route> getRoutes() {
		return routes;
	}

	public Collection<Switch> getSwitches() {
		return switches;
	}

	private void registerNotifier(Object object) {
		if (changeNotifier != null && object instanceof ListenableObject) {
			((ListenableObject) object).setChangeNotifier(changeNotifier);
		}
	}

	public String toString() {
		return "segments: " + segments.size() + "; switches: " + switches.size() + "; switchPositions: " + switchPositions.size()
				+ "; signals: " + signals.size() + "; routes: " + routes.size() + "; sensors: " + sensors.size();
	}

	@Override
	public Iterator<Object> iterator() {
		return new MultiIterator(segments, switches, switchPositions, signals, routes, sensors);
	}

	public static class MultiIterator implements Iterator<Object> {

		private Iterable<? extends Object>[] iterables;
		private int counter = 0;
		private Iterator<? extends Object> currentIterator;
		private Object next;

		public MultiIterator(Iterable<? extends Object>... iterables) {
			this.iterables = iterables;
			this.next = fetchNext();
		}

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public Object next() {
			Object current = next;
			next = fetchNext();
			return current;
		}

		private Object fetchNext() {
			while (currentIterator != null || counter < iterables.length) {
				if (currentIterator == null) {
					currentIterator = iterables[counter++].iterator();
				}
				if (currentIterator.hasNext()) {
					return currentIterator.next();
				} else {
					currentIterator = null;
				}
			}
			return null;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		checkNotifier();
		changeNotifier.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		if (changeNotifier != null) {
			changeNotifier.removePropertyChangeListener(listener);
		}
	}

	private void checkNotifier() {
		if (changeNotifier == null) {
			changeNotifier = new ChangeNotifier();
			for (Object object : this) {
				registerNotifier(object);
			}
		}
	}

	public static class ChangeNotifier {
		private Set<PropertyChangeListener> listeners = new HashSet<>();

		public void addPropertyChangeListener(PropertyChangeListener listener) {
			listeners.add(listener);
		}

		public void removePropertyChangeListener(PropertyChangeListener listener) {
			listeners.remove(listener);
		}

		void firePropertyChange(Object source, String propertyName, Object oldValue, Object newValue) {
			PropertyChangeEvent event = new PropertyChangeEvent(source, propertyName, oldValue, newValue);
			for (PropertyChangeListener listener : listeners) {
				listener.propertyChange(event);
			}
		}
	}
}
