package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import org.eclipse.viatra.query.runtime.api.IMatchProcessor;

import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.viatra.switchSet pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class SwitchSetProcessor implements IMatchProcessor<SwitchSetMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSemaphore the value of pattern parameter semaphore in the currently processed match
   * @param pRoute the value of pattern parameter route in the currently processed match
   * @param pSwP the value of pattern parameter swP in the currently processed match
   * @param pSw the value of pattern parameter sw in the currently processed match
   * 
   */
  public abstract void process(final Semaphore pSemaphore, final Route pRoute, final SwitchPosition pSwP, final Switch pSw);
  
  @Override
  public void process(final SwitchSetMatch match) {
    process(match.getSemaphore(), match.getRoute(), match.getSwP(), match.getSw());
  }
}
