package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.TrackElementConnectedMatch;
import hu.bme.mit.trainbenchmark.railway.TrackElement;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.trackElementConnected pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class TrackElementConnectedProcessor implements IMatchProcessor<TrackElementConnectedMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pTe1 the value of pattern parameter te1 in the currently processed match
   * @param pTe2 the value of pattern parameter te2 in the currently processed match
   * 
   */
  public abstract void process(final TrackElement pTe1, final TrackElement pTe2);
  
  @Override
  public void process(final TrackElementConnectedMatch match) {
    process(match.getTe1(), match.getTe2());
  }
}
