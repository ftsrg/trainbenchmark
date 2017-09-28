/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SwitchMonitored.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.HasSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchMonitoredMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.HasSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.SwitchMonitoredQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all public patterns defined in SwitchMonitored.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SwitchMonitored.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.viatra, the group contains the definition of the following patterns: <ul>
 * <li>switchMonitored</li>
 * <li>hasSensor</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class SwitchMonitored extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SwitchMonitored instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new SwitchMonitored();
    }
    return INSTANCE;
  }
  
  private static SwitchMonitored INSTANCE;
  
  private SwitchMonitored() throws ViatraQueryException {
    querySpecifications.add(SwitchMonitoredQuerySpecification.instance());
    querySpecifications.add(HasSensorQuerySpecification.instance());
  }
  
  public SwitchMonitoredQuerySpecification getSwitchMonitored() throws ViatraQueryException {
    return SwitchMonitoredQuerySpecification.instance();
  }
  
  public SwitchMonitoredMatcher getSwitchMonitored(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SwitchMonitoredMatcher.on(engine);
  }
  
  public HasSensorQuerySpecification getHasSensor() throws ViatraQueryException {
    return HasSensorQuerySpecification.instance();
  }
  
  public HasSensorMatcher getHasSensor(final ViatraQueryEngine engine) throws ViatraQueryException {
    return HasSensorMatcher.on(engine);
  }
}
