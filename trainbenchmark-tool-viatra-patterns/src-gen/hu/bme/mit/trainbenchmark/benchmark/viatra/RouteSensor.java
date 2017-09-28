/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/RouteSensor.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.RequiredMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.RouteSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.RequiredQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.RouteSensorQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all public patterns defined in RouteSensor.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file RouteSensor.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.viatra, the group contains the definition of the following patterns: <ul>
 * <li>routeSensor</li>
 * <li>required</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class RouteSensor extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static RouteSensor instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new RouteSensor();
    }
    return INSTANCE;
  }
  
  private static RouteSensor INSTANCE;
  
  private RouteSensor() throws ViatraQueryException {
    querySpecifications.add(RouteSensorQuerySpecification.instance());
    querySpecifications.add(RequiredQuerySpecification.instance());
  }
  
  public RouteSensorQuerySpecification getRouteSensor() throws ViatraQueryException {
    return RouteSensorQuerySpecification.instance();
  }
  
  public RouteSensorMatcher getRouteSensor(final ViatraQueryEngine engine) throws ViatraQueryException {
    return RouteSensorMatcher.on(engine);
  }
  
  public RequiredQuerySpecification getRequired() throws ViatraQueryException {
    return RequiredQuerySpecification.instance();
  }
  
  public RequiredMatcher getRequired(final ViatraQueryEngine engine) throws ViatraQueryException {
    return RequiredMatcher.on(engine);
  }
}
