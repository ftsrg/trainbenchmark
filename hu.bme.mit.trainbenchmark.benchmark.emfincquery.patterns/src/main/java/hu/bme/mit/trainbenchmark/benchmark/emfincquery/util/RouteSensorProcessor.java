package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import Concept.Sensor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensorMatch;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.routeSensor pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class RouteSensorProcessor implements IMatchProcessor<RouteSensorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSen the value of pattern parameter Sen in the currently processed match
   * 
   */
  public abstract void process(final Sensor pSen);
  
  @Override
  public void process(final RouteSensorMatch match) {
    process(match.getSen());
    
  }
}
