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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ConnectedSegmentsQuerySpecification;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * A pattern group formed of all patterns defined in ConnectedSegments.eiq.
 * 
 * <p>Use the static instance as any {@link org.eclipse.incquery.runtime.api.IPatternGroup}, to conveniently prepare
 * an EMF-IncQuery engine for matching all patterns originally defined in file ConnectedSegments.eiq,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.emfincquery, the group contains the definition of the following patterns: <ul>
 * <li>ConnectedSegments</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ConnectedSegments extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws IncQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ConnectedSegments instance() throws IncQueryException {
    if (INSTANCE == null) {
    	INSTANCE = new ConnectedSegments();
    }
    return INSTANCE;
  }
  
  private static ConnectedSegments INSTANCE;
  
  private ConnectedSegments() throws IncQueryException {
    querySpecifications.add(ConnectedSegmentsQuerySpecification.instance());
  }
  
  public ConnectedSegmentsQuerySpecification getConnectedSegments() throws IncQueryException {
    return ConnectedSegmentsQuerySpecification.instance();
  }
  
  public ConnectedSegmentsMatcher getConnectedSegments(final IncQueryEngine engine) throws IncQueryException {
    return ConnectedSegmentsMatcher.on(engine);
  }
}
