/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.railway.Segment;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegments pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class ConnectedSegmentsProcessor implements IMatchProcessor<ConnectedSegmentsMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSegment the value of pattern parameter segment in the currently processed match
   * 
   */
  public abstract void process(final Segment pSegment);
  
  @Override
  public void process(final ConnectedSegmentsMatch match) {
    process(match.getSegment());
  }
}
