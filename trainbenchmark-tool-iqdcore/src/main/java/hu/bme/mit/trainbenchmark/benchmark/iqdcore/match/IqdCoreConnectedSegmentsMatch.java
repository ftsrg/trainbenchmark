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
package hu.bme.mit.trainbenchmark.benchmark.iqdcore.match;

import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsMatch;
import scala.collection.immutable.Vector;

public class IqdCoreConnectedSegmentsMatch extends IqdCoreMatch implements ConnectedSegmentsMatch {

	public IqdCoreConnectedSegmentsMatch(final Vector<Object> qs) {
		super(qs);
	}

	@Override
	public Long getSegment1() {
		return (Long) qs.apply(0);
	}

	@Override
	public Long getSegment2() {
		return (Long) qs.apply(1);
	}

	@Override
	public Long getSegment3() {
		return (Long) qs.apply(2);
	}

	@Override
	public Long getSegment4() {
		return (Long) qs.apply(3);
	}

	@Override
	public Long getSegment5() {
		return (Long) qs.apply(4);
	}

	@Override
	public Long getSegment6() {
		return (Long) qs.apply(5);
	}

	@Override
	public Long getSensor() {
		return (Long) qs.apply(6);
	}

}
