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
package hu.bme.mit.trainbenchmark.benchmark.ingraph.match;

import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsInjectMatch;
import scala.collection.immutable.Map;

// The code of the match classes is correct, but it does not compile in Eclipse. See
// http://stackoverflow.com/questions/10852923/method-is-ambiguous-for-the-type-but-the-types-are-not-ambiguous-and-the-erro
public class IngraphConnectedSegmentsInjectMatch extends IngraphMatch implements ConnectedSegmentsInjectMatch {

	public IngraphConnectedSegmentsInjectMatch(final Map<Object, Object> qs) {
		super(qs);
	}

	@Override
	public Long getSensor() {
		return (Long) qs.get(0).get();
	}

	@Override
	public Long getSegment1() {
		return (Long) qs.get(1).get();
	}

	@Override
	public Long getSegment3() {
		return (Long) qs.get(2).get();
	}

}
