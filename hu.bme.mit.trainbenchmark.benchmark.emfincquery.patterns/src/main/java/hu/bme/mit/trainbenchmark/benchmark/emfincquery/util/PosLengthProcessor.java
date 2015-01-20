package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import Concept.Segment;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatch;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.posLength pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class PosLengthProcessor implements IMatchProcessor<PosLengthMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSource the value of pattern parameter Source in the currently processed match
   * @param pTarget the value of pattern parameter Target in the currently processed match
   * 
   */
  public abstract void process(final Segment pSource, final Integer pTarget);
  
  @Override
  public void process(final PosLengthMatch match) {
    process(match.getSource(), match.getTarget());
    
  }
}
