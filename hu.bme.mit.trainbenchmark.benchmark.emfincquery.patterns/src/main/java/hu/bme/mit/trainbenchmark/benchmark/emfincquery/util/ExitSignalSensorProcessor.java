package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import Concept.Route;
import Concept.Sensor;
import Concept.Signal;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ExitSignalSensorMatch;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.exitSignalSensor pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class ExitSignalSensorProcessor implements IMatchProcessor<ExitSignalSensorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSig the value of pattern parameter Sig in the currently processed match
   * @param pR1 the value of pattern parameter R1 in the currently processed match
   * @param pSen1 the value of pattern parameter Sen1 in the currently processed match
   * 
   */
  public abstract void process(final Signal pSig, final Route pR1, final Sensor pSen1);
  
  @Override
  public void process(final ExitSignalSensorMatch match) {
    process(match.getSig(), match.getR1(), match.getSen1());
    
  }
}
