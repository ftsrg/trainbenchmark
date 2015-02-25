package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensorMatch;
import hu.bme.mit.trainbenchmark.railway.Switch;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensor pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class SwitchSensorProcessor implements IMatchProcessor<SwitchSensorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSw the value of pattern parameter sw in the currently processed match
   * 
   */
  public abstract void process(final Switch pSw);
  
  @Override
  public void process(final SwitchSensorMatch match) {
    process(match.getSw());
  }
}
