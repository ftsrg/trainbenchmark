package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.HasSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.HasSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SwitchSensorQuerySpecification;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * A pattern group formed of all patterns defined in SwitchSensor.eiq.
 * 
 * <p>Use the static instance as any {@link org.eclipse.incquery.runtime.api.IPatternGroup}, to conveniently prepare
 * an EMF-IncQuery engine for matching all patterns originally defined in file SwitchSensor.eiq,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.emfincquery, the group contains the definition of the following patterns: <ul>
 * <li>SwitchSensor</li>
 * <li>hasSensor</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class SwitchSensor extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws IncQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static SwitchSensor instance() throws IncQueryException {
    if (INSTANCE == null) {
    	INSTANCE = new SwitchSensor();
    }
    return INSTANCE;
  }
  
  private static SwitchSensor INSTANCE;
  
  private SwitchSensor() throws IncQueryException {
    querySpecifications.add(SwitchSensorQuerySpecification.instance());
    querySpecifications.add(HasSensorQuerySpecification.instance());
  }
  
  public SwitchSensorQuerySpecification getSwitchSensor() throws IncQueryException {
    return SwitchSensorQuerySpecification.instance();
  }
  
  public SwitchSensorMatcher getSwitchSensor(final IncQueryEngine engine) throws IncQueryException {
    return SwitchSensorMatcher.on(engine);
  }
  
  public HasSensorQuerySpecification getHasSensor() throws IncQueryException {
    return HasSensorQuerySpecification.instance();
  }
  
  public HasSensorMatcher getHasSensor(final IncQueryEngine engine) throws IncQueryException {
    return HasSensorMatcher.on(engine);
  }
}
