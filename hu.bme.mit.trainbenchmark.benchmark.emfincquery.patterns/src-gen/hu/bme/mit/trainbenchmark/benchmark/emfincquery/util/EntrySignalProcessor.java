package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySignalMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Signal;
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
   * @param pRoute the value of pattern parameter route in the currently processed match
   * @param pSignal the value of pattern parameter signal in the currently processed match
   * 
   */
  public abstract void process(final Route pRoute, final Signal pSignal);
  
  @Override
  public void process(final EntrySignalMatch match) {
    process(match.getRoute(), match.getSignal());
  }
}
