package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySignalSensorMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.Signal;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySignalSensor pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class EntrySignalSensorProcessor implements IMatchProcessor<EntrySignalSensorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSignal the value of pattern parameter signal in the currently processed match
   * @param pRoute2 the value of pattern parameter route2 in the currently processed match
   * @param pSensor2 the value of pattern parameter sensor2 in the currently processed match
   * 
   */
  public abstract void process(final Signal pSignal, final Route pRoute2, final Sensor pSensor2);
  
  @Override
  public void process(final EntrySignalSensorMatch match) {
    process(match.getSignal(), match.getRoute2(), match.getSensor2());
  }
}
