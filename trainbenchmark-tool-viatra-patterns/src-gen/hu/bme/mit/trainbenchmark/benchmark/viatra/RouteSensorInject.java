/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/RouteSensorInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.RouteSensorInjectMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.RouteSensorInjectQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all public patterns defined in RouteSensorInject.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file RouteSensorInject.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.viatra, the group contains the definition of the following patterns: <ul>
 * <li>routeSensorInject</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class RouteSensorInject extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static RouteSensorInject instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new RouteSensorInject();
    }
    return INSTANCE;
  }
  
  private static RouteSensorInject INSTANCE;
  
  private RouteSensorInject() throws ViatraQueryException {
    querySpecifications.add(RouteSensorInjectQuerySpecification.instance());
  }
  
  public RouteSensorInjectQuerySpecification getRouteSensorInject() throws ViatraQueryException {
    return RouteSensorInjectQuerySpecification.instance();
  }
  
  public RouteSensorInjectMatcher getRouteSensorInject(final ViatraQueryEngine engine) throws ViatraQueryException {
    return RouteSensorInjectMatcher.on(engine);
  }
}
