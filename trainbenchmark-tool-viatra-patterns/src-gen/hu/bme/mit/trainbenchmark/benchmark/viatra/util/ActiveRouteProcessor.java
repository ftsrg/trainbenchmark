/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/ActiveRoute.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import hu.bme.mit.trainbenchmark.benchmark.viatra.ActiveRouteMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;

/**
 * A match processor tailored for the hu.bme.mit.trainbenchmark.benchmark.viatra.ActiveRoute pattern.
 * 
 * Clients should derive an (anonymous) class that implements the abstract process().
 * 
 */
@SuppressWarnings("all")
public abstract class ActiveRouteProcessor implements IMatchProcessor<ActiveRouteMatch> {
  /**
   * Defines the action that is to be executed on each match.
   * @param pRoute the value of pattern parameter route in the currently processed match
   * 
   */
  public abstract void process(final Route pRoute);
  
  @Override
  public void process(final ActiveRouteMatch match) {
    process(match.getRoute());
  }
}
