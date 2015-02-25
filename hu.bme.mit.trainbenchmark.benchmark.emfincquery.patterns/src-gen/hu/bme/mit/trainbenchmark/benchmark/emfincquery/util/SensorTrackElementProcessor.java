package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SensorTrackElementMatch;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.sensorTrackElement pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class SensorTrackElementProcessor implements IMatchProcessor<SensorTrackElementMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSen the value of pattern parameter sen in the currently processed match
   * @param pTe the value of pattern parameter te in the currently processed match
   * 
   */
  public abstract void process(final Sensor pSen, final TrackElement pTe);
  
  @Override
  public void process(final SensorTrackElementMatch match) {
    process(match.getSen(), match.getTe());
  }
}
