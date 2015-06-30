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
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.InverseRouteDefinitionMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.inverseRouteDefinition pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class InverseRouteDefinitionProcessor implements IMatchProcessor<InverseRouteDefinitionMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSensor the value of pattern parameter sensor in the currently processed match
   * @param pRoute the value of pattern parameter route in the currently processed match
   * 
   */
  public abstract void process(final Sensor pSensor, final Route pRoute);
  
  @Override
  public void process(final InverseRouteDefinitionMatch match) {
    process(match.getSensor(), match.getRoute());
  }
}
