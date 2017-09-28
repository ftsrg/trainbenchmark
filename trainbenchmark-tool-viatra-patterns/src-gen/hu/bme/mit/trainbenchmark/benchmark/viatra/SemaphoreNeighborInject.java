/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SemaphoreNeighborInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.SemaphoreNeighborInjectMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.SemaphoreNeighborInjectQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all public patterns defined in SemaphoreNeighborInject.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SemaphoreNeighborInject.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.viatra, the group contains the definition of the following patterns: <ul>
 * <li>semaphoreNeighborInject</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class SemaphoreNeighborInject extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SemaphoreNeighborInject instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new SemaphoreNeighborInject();
    }
    return INSTANCE;
  }
  
  private static SemaphoreNeighborInject INSTANCE;
  
  private SemaphoreNeighborInject() throws ViatraQueryException {
    querySpecifications.add(SemaphoreNeighborInjectQuerySpecification.instance());
  }
  
  public SemaphoreNeighborInjectQuerySpecification getSemaphoreNeighborInject() throws ViatraQueryException {
    return SemaphoreNeighborInjectQuerySpecification.instance();
  }
  
  public SemaphoreNeighborInjectMatcher getSemaphoreNeighborInject(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SemaphoreNeighborInjectMatcher.on(engine);
  }
}
