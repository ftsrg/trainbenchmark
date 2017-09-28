/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SwitchMonitoredInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchMonitoredInjectMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.SwitchMonitoredInjectQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all public patterns defined in SwitchMonitoredInject.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SwitchMonitoredInject.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.viatra, the group contains the definition of the following patterns: <ul>
 * <li>switchMonitoredInject</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class SwitchMonitoredInject extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SwitchMonitoredInject instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new SwitchMonitoredInject();
    }
    return INSTANCE;
  }
  
  private static SwitchMonitoredInject INSTANCE;
  
  private SwitchMonitoredInject() throws ViatraQueryException {
    querySpecifications.add(SwitchMonitoredInjectQuerySpecification.instance());
  }
  
  public SwitchMonitoredInjectQuerySpecification getSwitchMonitoredInject() throws ViatraQueryException {
    return SwitchMonitoredInjectQuerySpecification.instance();
  }
  
  public SwitchMonitoredInjectMatcher getSwitchMonitoredInject(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SwitchMonitoredInjectMatcher.on(engine);
  }
}
