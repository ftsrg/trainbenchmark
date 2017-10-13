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

public interface ConnectedSegmentsMatch extends Match {

	Object getSensor();

	Object getSegment1();

	Object getSegment2();

	Object getSegment3();

	Object getSegment4();

	Object getSegment5();

	Object getSegment6();

	default Object[] toArray() {
		return new Object[] { getSensor(), getSegment1(), getSegment2(), getSegment3(), getSegment4(), getSegment5(), getSegment6() };
	}

}
