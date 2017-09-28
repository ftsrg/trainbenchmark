/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/ConnectedSegmentsInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.ConnectedSegmentsInjectMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.ConnectedSegmentsInjectQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all public patterns defined in ConnectedSegmentsInject.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ConnectedSegmentsInject.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.viatra, the group contains the definition of the following patterns: <ul>
 * <li>connectedSegmentsInject</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ConnectedSegmentsInject extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ConnectedSegmentsInject instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new ConnectedSegmentsInject();
    }
    return INSTANCE;
  }
  
  private static ConnectedSegmentsInject INSTANCE;
  
  private ConnectedSegmentsInject() throws ViatraQueryException {
    querySpecifications.add(ConnectedSegmentsInjectQuerySpecification.instance());
  }
  
  public ConnectedSegmentsInjectQuerySpecification getConnectedSegmentsInject() throws ViatraQueryException {
    return ConnectedSegmentsInjectQuerySpecification.instance();
  }
  
  public ConnectedSegmentsInjectMatcher getConnectedSegmentsInject(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ConnectedSegmentsInjectMatcher.on(engine);
  }
}
