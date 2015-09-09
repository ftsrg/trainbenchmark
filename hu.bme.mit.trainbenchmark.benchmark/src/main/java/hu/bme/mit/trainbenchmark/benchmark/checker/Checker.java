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
package hu.bme.mit.trainbenchmark.benchmark.checker;

import hu.bme.mit.trainbenchmark.benchmark.matches.MatchProcessor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Checker<M> {

	public abstract Collection<M> check() throws Exception;

//	public abstract Map<String, Object> processMatches(final Collection<M> matches);

	public Map<String, Object> processMatches(Collection<M> matches) {
		Map<String, Object> processedMatches = new HashMap<String, Object>();
		if (matches == null) {
			return processedMatches;
		}
		for (M match : matches) {
			if (match instanceof MatchProcessor) {
				processedMatches.putAll(((MatchProcessor) match).process());
			}
		}
		return processedMatches;
	}

	public void destroy() {
	}

}
