package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.railway.Switch;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchMonitored pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class SwitchMonitoredProcessor implements IMatchProcessor<SwitchMonitoredMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSw the value of pattern parameter sw in the currently processed match
   * 
   */
  public abstract void process(final Switch pSw);
  
  @Override
  public void process(final SwitchMonitoredMatch match) {
    process(match.getSw());
  }
}
