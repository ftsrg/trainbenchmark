package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import Concept.Trackelement;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.HasSensorMatch;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.hasSensor pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class HasSensorProcessor implements IMatchProcessor<HasSensorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pTrackelement the value of pattern parameter Trackelement in the currently processed match
   * 
   */
  public abstract void process(final Trackelement pTrackelement);
  
  @Override
  public void process(final HasSensorMatch match) {
    process(match.getTrackelement());
    
  }
}
