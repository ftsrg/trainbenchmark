package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectingSensorsMatch;
import hu.bme.mit.trainbenchmark.railway.Sensor;
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
   * @param pSensor1 the value of pattern parameter sensor1 in the currently processed match
   * @param pSensor2 the value of pattern parameter sensor2 in the currently processed match
   * 
   */
  public abstract void process(final Sensor pSensor1, final Sensor pSensor2);
  
  @Override
  public void process(final ConnectingSensorsMatch match) {
    process(match.getSensor1(), match.getSensor2());
  }
}
