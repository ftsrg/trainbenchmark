package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.PosLengthQuerySpecification;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * A pattern group formed of all patterns defined in posLength.eiq.
 * 
 * <p>Use the static instance as any {@link org.eclipse.incquery.runtime.api.IPatternGroup}, to conveniently prepare
 * an EMF-IncQuery engine for matching all patterns originally defined in file posLength.eiq,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.emfincquery, the group contains the definition of the following patterns: <ul>
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
   * @throws IncQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static PosLength instance() throws IncQueryException {
    if (INSTANCE == null) {
    	INSTANCE = new PosLength();
    }
    return INSTANCE;
    
  }
  
  private static PosLength INSTANCE;
  
  private PosLength() throws IncQueryException {
    querySpecifications.add(PosLengthQuerySpecification.instance());
    
  }
  
  public PosLengthQuerySpecification getPosLength() throws IncQueryException {
    return PosLengthQuerySpecification.instance();
  }
  
  public PosLengthMatcher getPosLength(final IncQueryEngine engine) throws IncQueryException {
    return PosLengthMatcher.on(engine);
  }
}
