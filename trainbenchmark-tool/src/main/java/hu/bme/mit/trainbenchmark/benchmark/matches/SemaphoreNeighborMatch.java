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
package hu.bme.mit.trainbenchmark.benchmark.matches;

public interface SemaphoreNeighborMatch extends Match {

	Object getSemaphore();

	Object getRoute1();

	Object getRoute2();

	Object getSensor1();

	Object getSensor2();

	Object getTe1();

	Object getTe2();

	default Object[] toArray() {
		return new Object[] { getSemaphore(), getRoute1(), getRoute2(), getSensor1(), getSensor2(), getTe1(), getTe2() };
	}

}
