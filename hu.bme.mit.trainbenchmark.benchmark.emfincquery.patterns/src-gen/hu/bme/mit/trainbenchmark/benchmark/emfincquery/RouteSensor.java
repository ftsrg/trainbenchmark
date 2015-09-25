package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.InverseDefinedByMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.InverseDefinedByQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.RouteSensorQuerySpecification;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * A pattern group formed of all patterns defined in RouteSensor.eiq.
 * 
 * <p>Use the static instance as any {@link org.eclipse.incquery.runtime.api.IPatternGroup}, to conveniently prepare
 * an EMF-IncQuery engine for matching all patterns originally defined in file RouteSensor.eiq,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.emfincquery, the group contains the definition of the following patterns: <ul>
 * <li>RouteSensor</li>
 * <li>inverseDefinedBy</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class RouteSensor extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws IncQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static RouteSensor instance() throws IncQueryException {
    if (INSTANCE == null) {
    	INSTANCE = new RouteSensor();
    }
    return INSTANCE;
  }
  
  private static RouteSensor INSTANCE;
  
  private RouteSensor() throws IncQueryException {
    querySpecifications.add(RouteSensorQuerySpecification.instance());
    querySpecifications.add(InverseDefinedByQuerySpecification.instance());
  }
  
  public RouteSensorQuerySpecification getRouteSensor() throws IncQueryException {
    return RouteSensorQuerySpecification.instance();
  }
  
  public RouteSensorMatcher getRouteSensor(final IncQueryEngine engine) throws IncQueryException {
    return RouteSensorMatcher.on(engine);
  }
  
  public InverseDefinedByQuerySpecification getInverseDefinedBy() throws IncQueryException {
    return InverseDefinedByQuerySpecification.instance();
  }
  
  public InverseDefinedByMatcher getInverseDefinedBy(final IncQueryEngine engine) throws IncQueryException {
    return InverseDefinedByMatcher.on(engine);
  }
}
