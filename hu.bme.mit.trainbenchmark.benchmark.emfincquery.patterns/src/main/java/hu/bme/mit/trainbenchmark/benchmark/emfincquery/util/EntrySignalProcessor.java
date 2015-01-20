package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import Concept.Route;
import Concept.Signal;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySignalMatch;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySignal pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class EntrySignalProcessor implements IMatchProcessor<EntrySignalMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pR the value of pattern parameter R in the currently processed match
   * @param pSig the value of pattern parameter Sig in the currently processed match
   * 
   */
  public abstract void process(final Route pR, final Signal pSig);
  
  @Override
  public void process(final EntrySignalMatch match) {
    process(match.getR(), match.getSig());
    
  }
}
