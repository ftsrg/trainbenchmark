package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySemaphoreMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SemaphoreNeighborMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.EntrySemaphoreQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SemaphoreNeighborQuerySpecification;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * A pattern group formed of all patterns defined in SemaphoreNeighbor.eiq.
 * 
 * <p>Use the static instance as any {@link org.eclipse.incquery.runtime.api.IPatternGroup}, to conveniently prepare
 * an EMF-IncQuery engine for matching all patterns originally defined in file SemaphoreNeighbor.eiq,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.emfincquery, the group contains the definition of the following patterns: <ul>
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
   * @throws IncQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SemaphoreNeighbor instance() throws IncQueryException {
    if (INSTANCE == null) {
    	INSTANCE = new SemaphoreNeighbor();
    }
    return INSTANCE;
  }
  
  private static SemaphoreNeighbor INSTANCE;
  
  private SemaphoreNeighbor() throws IncQueryException {
    querySpecifications.add(SemaphoreNeighborQuerySpecification.instance());
    querySpecifications.add(EntrySemaphoreQuerySpecification.instance());
  }
  
  public SemaphoreNeighborQuerySpecification getSemaphoreNeighbor() throws IncQueryException {
    return SemaphoreNeighborQuerySpecification.instance();
  }
  
  public SemaphoreNeighborMatcher getSemaphoreNeighbor(final IncQueryEngine engine) throws IncQueryException {
    return SemaphoreNeighborMatcher.on(engine);
  }
  
  public EntrySemaphoreQuerySpecification getEntrySemaphore() throws IncQueryException {
    return EntrySemaphoreQuerySpecification.instance();
  }
  
  public EntrySemaphoreMatcher getEntrySemaphore(final IncQueryEngine engine) throws IncQueryException {
    return EntrySemaphoreMatcher.on(engine);
  }
}
