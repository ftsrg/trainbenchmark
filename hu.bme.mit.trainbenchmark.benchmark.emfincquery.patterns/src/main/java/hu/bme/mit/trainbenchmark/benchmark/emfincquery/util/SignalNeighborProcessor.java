package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import Concept.Route;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SignalNeighborMatch;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.signalNeighbor pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class SignalNeighborProcessor implements IMatchProcessor<SignalNeighborMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pR1 the value of pattern parameter R1 in the currently processed match
   * 
   */
  public abstract void process(final Route pR1);
  
  @Override
  public void process(final SignalNeighborMatch match) {
    process(match.getR1());
    
  }
}
