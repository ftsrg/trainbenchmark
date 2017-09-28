/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/ConnectedSegments.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.ConnectedSegmentsMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.ConnectedSegmentsQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all public patterns defined in ConnectedSegments.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ConnectedSegments.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.viatra, the group contains the definition of the following patterns: <ul>
 * <li>connectedSegments</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ConnectedSegments extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ConnectedSegments instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ConnectedSegments();
    }
    return INSTANCE;
  }
  
  private static ConnectedSegments INSTANCE;
  
  private ConnectedSegments() throws ViatraQueryException {
    querySpecifications.add(ConnectedSegmentsQuerySpecification.instance());
  }
  
  public ConnectedSegmentsQuerySpecification getConnectedSegments() throws ViatraQueryException {
    return ConnectedSegmentsQuerySpecification.instance();
  }
  
  public ConnectedSegmentsMatcher getConnectedSegments(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ConnectedSegmentsMatcher.on(engine);
  }
}
