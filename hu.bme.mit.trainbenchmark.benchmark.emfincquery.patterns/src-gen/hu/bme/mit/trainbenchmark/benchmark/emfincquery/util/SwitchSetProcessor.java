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

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.switchSet pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class SwitchSetProcessor implements IMatchProcessor<SwitchSetMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSemaphore the value of pattern parameter semaphore in the currently processed match
   * @param pRoute the value of pattern parameter route in the currently processed match
   * @param pSwP the value of pattern parameter swP in the currently processed match
   * @param pSw the value of pattern parameter sw in the currently processed match
   * 
   */
  public abstract void process(final Semaphore pSemaphore, final Route pRoute, final SwitchPosition pSwP, final Switch pSw);
  
  @Override
  public void process(final SwitchSetMatch match) {
    process(match.getSemaphore(), match.getRoute(), match.getSwP(), match.getSw());
  }
}
