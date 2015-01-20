package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import Concept.Route;
import Concept.Sensor;
import Concept.Signal;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySignalSensorMatch;
import org.eclipse.incquery.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySignalSensor pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class EntrySignalSensorProcessor implements IMatchProcessor<EntrySignalSensorMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pSig the value of pattern parameter Sig in the currently processed match
   * @param pR2 the value of pattern parameter R2 in the currently processed match
   * @param pSen2 the value of pattern parameter Sen2 in the currently processed match
   * 
   */
  public abstract void process(final Signal pSig, final Route pR2, final Sensor pSen2);
  
  @Override
  public void process(final EntrySignalSensorMatch match) {
    process(match.getSig(), match.getR2(), match.getSen2());
    
  }
}
