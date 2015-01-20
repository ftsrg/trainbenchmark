package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import Concept.Route;
import Concept.Sensor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.HeadMatch;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.head pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class HeadProcessor implements IMatchProcessor<HeadMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSen the value of pattern parameter Sen in the currently processed match
   * @param pR the value of pattern parameter R in the currently processed match
   * 
   */
  public abstract void process(final Sensor pSen, final Route pR);
  
  @Override
  public void process(final HeadMatch match) {
    process(match.getSen(), match.getR());
    
  }
}
