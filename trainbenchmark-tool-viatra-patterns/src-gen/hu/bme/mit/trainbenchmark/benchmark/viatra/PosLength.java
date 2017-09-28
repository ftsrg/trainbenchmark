/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/PosLength.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.PosLengthMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.PosLengthQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all public patterns defined in PosLength.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file PosLength.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.viatra, the group contains the definition of the following patterns: <ul>
 * <li>posLength</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class PosLength extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PosLength instance() throws ViatraQueryException {
    if (INSTANCE == null) {
        INSTANCE = new PosLength();
    }
    return INSTANCE;
  }
  
  private static PosLength INSTANCE;
  
  private PosLength() throws ViatraQueryException {
    querySpecifications.add(PosLengthQuerySpecification.instance());
  }
  
  public PosLengthQuerySpecification getPosLength() throws ViatraQueryException {
    return PosLengthQuerySpecification.instance();
  }
  
  public PosLengthMatcher getPosLength(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PosLengthMatcher.on(engine);
  }
}
