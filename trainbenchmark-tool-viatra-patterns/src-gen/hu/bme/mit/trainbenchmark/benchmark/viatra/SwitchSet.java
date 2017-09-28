/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SwitchSet.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchSetMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.SwitchSetQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all public patterns defined in SwitchSet.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file SwitchSet.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.viatra, the group contains the definition of the following patterns: <ul>
 * <li>switchSet</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class SwitchSet extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SwitchSet instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new SwitchSet();
    }
    return INSTANCE;
  }
  
  private static SwitchSet INSTANCE;
  
  private SwitchSet() throws ViatraQueryException {
    querySpecifications.add(SwitchSetQuerySpecification.instance());
  }
  
  public SwitchSetQuerySpecification getSwitchSet() throws ViatraQueryException {
    return SwitchSetQuerySpecification.instance();
  }
  
  public SwitchSetMatcher getSwitchSet(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SwitchSetMatcher.on(engine);
  }
}
