package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;
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
   * @param pRoute the value of pattern parameter route in the currently processed match
   * @param pSensor the value of pattern parameter sensor in the currently processed match
   * @param pSwitchPosition the value of pattern parameter switchPosition in the currently processed match
   * @param pSw the value of pattern parameter sw in the currently processed match
   * 
   */
  public abstract void process(final Route pRoute, final Sensor pSensor, final SwitchPosition pSwitchPosition, final Switch pSw);
  
  @Override
  public void process(final RouteSensorMatch match) {
    process(match.getRoute(), match.getSensor(), match.getSwitchPosition(), match.getSw());
  }
}
