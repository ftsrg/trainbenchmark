package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import hu.bme.mit.trainbenchmark.benchmark.viatra.RouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.viatra.RouteSensorInject pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class RouteSensorInjectProcessor implements IMatchProcessor<RouteSensorInjectMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pRoute the value of pattern parameter route in the currently processed match
   * @param pSensor the value of pattern parameter sensor in the currently processed match
   * 
   */
  public abstract void process(final Route pRoute, final Sensor pSensor);
  
  @Override
  public void process(final RouteSensorInjectMatch match) {
    process(match.getRoute(), match.getSensor());
  }
}
