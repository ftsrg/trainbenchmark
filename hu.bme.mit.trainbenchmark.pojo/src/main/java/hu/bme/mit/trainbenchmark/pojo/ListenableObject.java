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

public abstract class ListenableObject {

	private transient Graph.ChangeNotifier changeNotifier;

	Graph.ChangeNotifier getChangeNotifier() {
		return changeNotifier;
	}

	void setChangeNotifier(Graph.ChangeNotifier changeNotifier) {
		this.changeNotifier = changeNotifier;
	}

	protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		firePropertyChange(this, propertyName, oldValue, newValue);
	}

	protected void firePropertyChange(Object source, String propertyName, Object oldValue, Object newValue) {
		if (changeNotifier != null) {
			changeNotifier.firePropertyChange(source, propertyName, oldValue, newValue);
		}
	}
}
