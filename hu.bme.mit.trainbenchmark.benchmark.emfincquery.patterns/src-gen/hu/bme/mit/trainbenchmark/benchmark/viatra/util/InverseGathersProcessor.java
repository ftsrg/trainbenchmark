package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import hu.bme.mit.trainbenchmark.benchmark.viatra.InverseGathersMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.viatra.inverseGathers pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class InverseGathersProcessor implements IMatchProcessor<InverseGathersMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSensor the value of pattern parameter sensor in the currently processed match
   * @param pRoute the value of pattern parameter route in the currently processed match
   * 
   */
  public abstract void process(final Sensor pSensor, final Route pRoute);
  
  @Override
  public void process(final InverseGathersMatch match) {
    process(match.getSensor(), match.getRoute());
  }
}
