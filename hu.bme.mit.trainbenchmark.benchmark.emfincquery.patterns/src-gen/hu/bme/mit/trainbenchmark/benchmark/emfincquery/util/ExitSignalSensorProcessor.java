package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ExitSignalSensorMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.Signal;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.exitSignalSensor pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class ExitSignalSensorProcessor implements IMatchProcessor<ExitSignalSensorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSignal the value of pattern parameter signal in the currently processed match
   * @param pRoute1 the value of pattern parameter route1 in the currently processed match
   * @param pSensor1 the value of pattern parameter sensor1 in the currently processed match
   * 
   */
  public abstract void process(final Signal pSignal, final Route pRoute1, final Sensor pSensor1);
  
  @Override
  public void process(final ExitSignalSensorMatch match) {
    process(match.getSignal(), match.getRoute1(), match.getSensor1());
  }
}
