package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import Concept.Sensor;
import Concept.Trackelement;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SensorTrackelementMatch;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.sensorTrackelement pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class SensorTrackelementProcessor implements IMatchProcessor<SensorTrackelementMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSen the value of pattern parameter Sen in the currently processed match
   * @param pTe the value of pattern parameter Te in the currently processed match
   * 
   */
  public abstract void process(final Sensor pSen, final Trackelement pTe);
  
  @Override
  public void process(final SensorTrackelementMatch match) {
    process(match.getSen(), match.getTe());
    
  }
}
