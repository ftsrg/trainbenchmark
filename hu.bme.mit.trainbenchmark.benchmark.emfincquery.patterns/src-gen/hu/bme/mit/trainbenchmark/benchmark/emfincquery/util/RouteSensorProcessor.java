package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensor pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class RouteSensorProcessor implements IMatchProcessor<RouteSensorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSensor the value of pattern parameter sensor in the currently processed match
   * 
   */
  public abstract void process(final Sensor pSensor);
  
  @Override
  public void process(final RouteSensorMatch match) {
    process(match.getSensor());
  }
}
