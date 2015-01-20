package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import Concept.Sensor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectingSensorsMatch;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.connectingSensors pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class ConnectingSensorsProcessor implements IMatchProcessor<ConnectingSensorsMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSen1 the value of pattern parameter Sen1 in the currently processed match
   * @param pSen2 the value of pattern parameter Sen2 in the currently processed match
   * 
   */
  public abstract void process(final Sensor pSen1, final Sensor pSen2);
  
  @Override
  public void process(final ConnectingSensorsMatch match) {
    process(match.getSen1(), match.getSen2());
    
  }
}
