/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/PosLengthInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.PosLengthInjectMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.PosLengthInjectQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all public patterns defined in PosLengthInject.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PosLengthInject.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.viatra, the group contains the definition of the following patterns: <ul>
 * <li>posLengthInject</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PosLengthInject extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PosLengthInject instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new PosLengthInject();
    }
    return INSTANCE;
  }
  
  private static PosLengthInject INSTANCE;
  
  private PosLengthInject() throws ViatraQueryException {
    querySpecifications.add(PosLengthInjectQuerySpecification.instance());
  }
  
  public PosLengthInjectQuerySpecification getPosLengthInject() throws ViatraQueryException {
    return PosLengthInjectQuerySpecification.instance();
  }
  
  public PosLengthInjectMatcher getPosLengthInject(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PosLengthInjectMatcher.on(engine);
  }
}
