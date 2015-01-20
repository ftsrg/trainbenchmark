package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import Concept.Switch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensorMatch;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.switchSensor pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class SwitchSensorProcessor implements IMatchProcessor<SwitchSensorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pIndividual the value of pattern parameter Individual in the currently processed match
   * 
   */
  public abstract void process(final Switch pIndividual);
  
  @Override
  public void process(final SwitchSensorMatch match) {
    process(match.getIndividual());
    
  }
}
