package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import Concept.Trackelement;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.TrackelementConnectedMatch;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.trackelementConnected pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class TrackelementConnectedProcessor implements IMatchProcessor<TrackelementConnectedMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pTe1 the value of pattern parameter Te1 in the currently processed match
   * @param pTe2 the value of pattern parameter Te2 in the currently processed match
   * 
   */
  public abstract void process(final Trackelement pTe1, final Trackelement pTe2);
  
  @Override
  public void process(final TrackelementConnectedMatch match) {
    process(match.getTe1(), match.getTe2());
    
  }
}
