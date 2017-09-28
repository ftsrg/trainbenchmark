/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SemaphoreNeighbor.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.EntrySemaphoreMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SemaphoreNeighborMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.EntrySemaphoreQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.SemaphoreNeighborQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all public patterns defined in SemaphoreNeighbor.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SemaphoreNeighbor.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.viatra, the group contains the definition of the following patterns: <ul>
 * <li>semaphoreNeighbor</li>
 * <li>entrySemaphore</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class SemaphoreNeighbor extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SemaphoreNeighbor instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new SemaphoreNeighbor();
    }
    return INSTANCE;
  }
  
  private static SemaphoreNeighbor INSTANCE;
  
  private SemaphoreNeighbor() throws ViatraQueryException {
    querySpecifications.add(SemaphoreNeighborQuerySpecification.instance());
    querySpecifications.add(EntrySemaphoreQuerySpecification.instance());
  }
  
  public SemaphoreNeighborQuerySpecification getSemaphoreNeighbor() throws ViatraQueryException {
    return SemaphoreNeighborQuerySpecification.instance();
  }
  
  public SemaphoreNeighborMatcher getSemaphoreNeighbor(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SemaphoreNeighborMatcher.on(engine);
  }
  
  public EntrySemaphoreQuerySpecification getEntrySemaphore() throws ViatraQueryException {
    return EntrySemaphoreQuerySpecification.instance();
  }
  
  public EntrySemaphoreMatcher getEntrySemaphore(final ViatraQueryEngine engine) throws ViatraQueryException {
    return EntrySemaphoreMatcher.on(engine);
  }
}
