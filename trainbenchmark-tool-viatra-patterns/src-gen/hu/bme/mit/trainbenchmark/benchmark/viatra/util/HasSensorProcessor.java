package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import org.eclipse.viatra.query.runtime.api.IMatchProcessor;

import hu.bme.mit.trainbenchmark.benchmark.viatra.HasSensorMatch;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.viatra.hasSensor pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class HasSensorProcessor implements IMatchProcessor<HasSensorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSw the value of pattern parameter sw in the currently processed match
   * 
   */
  public abstract void process(final TrackElement pSw);
  
  @Override
  public void process(final HasSensorMatch match) {
    process(match.getSw());
  }
}
