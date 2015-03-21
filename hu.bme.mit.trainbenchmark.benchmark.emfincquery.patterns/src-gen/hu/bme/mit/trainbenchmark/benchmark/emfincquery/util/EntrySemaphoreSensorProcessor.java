package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySemaphoreSensorMatch;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySemaphoreSensor pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class EntrySemaphoreSensorProcessor implements IMatchProcessor<EntrySemaphoreSensorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSemaphore the value of pattern parameter semaphore in the currently processed match
   * @param pSensor2 the value of pattern parameter sensor2 in the currently processed match
   * 
   */
  public abstract void process(final Semaphore pSemaphore, final Sensor pSensor2);
  
  @Override
  public void process(final EntrySemaphoreSensorMatch match) {
    process(match.getSemaphore(), match.getSensor2());
  }
}
