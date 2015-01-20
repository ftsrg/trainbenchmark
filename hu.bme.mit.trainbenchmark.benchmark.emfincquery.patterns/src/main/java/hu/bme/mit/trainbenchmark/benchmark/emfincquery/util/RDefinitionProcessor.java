package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import Concept.Route;
import Concept.Sensor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RDefinitionMatch;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.rDefinition pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class RDefinitionProcessor implements IMatchProcessor<RDefinitionMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pR the value of pattern parameter R in the currently processed match
   * @param pSen the value of pattern parameter Sen in the currently processed match
   * 
   */
  public abstract void process(final Route pR, final Sensor pSen);
  
  @Override
  public void process(final RDefinitionMatch match) {
    process(match.getR(), match.getSen());
    
  }
}
