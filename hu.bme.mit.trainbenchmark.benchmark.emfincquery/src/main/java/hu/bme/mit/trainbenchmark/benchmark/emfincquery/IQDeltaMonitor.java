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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery;


import java.util.HashSet;

import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.incquery.runtime.rete.misc.DeltaMonitor;

public class IQDeltaMonitor <T> {
	private final DeltaMonitor<? extends IPatternMatch> deltaMonitor;
	private HashSet<T> results;
	private String parameterName;
	
	public IQDeltaMonitor(IncQueryMatcher<? extends IPatternMatch> patternMatcher, String parameterName) {
		this.parameterName = parameterName;
		
		
		deltaMonitor = patternMatcher.newDeltaMonitor(true);
		results = new HashSet<T>();
	}
	
	@SuppressWarnings("unchecked") // This is the responsibility of the caller...
	public HashSet<T> getMatching(){
		for (IPatternMatch ipm : deltaMonitor.matchFoundEvents)
			results.add((T) ipm.get(parameterName));
		deltaMonitor.matchFoundEvents.clear();
		
		for (IPatternMatch ipm : deltaMonitor.matchLostEvents)
			results.remove((T) ipm.get(parameterName));
		deltaMonitor.matchLostEvents.clear();
		
		return results;
	}

}
