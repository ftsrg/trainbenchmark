package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.ActiveRouteMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.MisalignedSwitchMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.ActiveRouteQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.MisalignedSwitchQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedPatternGroup;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;

/**
 * A pattern group formed of all patterns defined in ActiveRoute.vql.
 * 
 * <p>Use the static instance as any {@link org.eclipse.viatra.query.runtime.api.IPatternGroup}, to conveniently prepare
 * a VIATRA Query engine for matching all patterns originally defined in file ActiveRoute.vql,
 * in order to achieve better performance than one-by-one on-demand matcher initialization.
 * 
 * <p> From package hu.bme.mit.trainbenchmark.benchmark.viatra, the group contains the definition of the following patterns: <ul>
 * <li>ActiveRoute</li>
 * <li>misalignedSwitch</li>
 * </ul>
 * 
 * @see IPatternGroup
 * 
 */
@SuppressWarnings("all")
public final class ActiveRoute extends BaseGeneratedPatternGroup {
  /**
   * Access the pattern group.
   * 
   * @return the singleton instance of the group
   * @throws ViatraQueryException if there was an error loading the generated code of pattern specifications
   * 
   */
  public static ActiveRoute instance() throws ViatraQueryException {
    if (INSTANCE == null) {
    	INSTANCE = new ActiveRoute();
    }
    return INSTANCE;
  }
  
  private static ActiveRoute INSTANCE;
  
  private ActiveRoute() throws ViatraQueryException {
    querySpecifications.add(ActiveRouteQuerySpecification.instance());
    querySpecifications.add(MisalignedSwitchQuerySpecification.instance());
  }
  
  public ActiveRouteQuerySpecification getActiveRoute() throws ViatraQueryException {
    return ActiveRouteQuerySpecification.instance();
  }
  
  public ActiveRouteMatcher getActiveRoute(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ActiveRouteMatcher.on(engine);
  }
  
  public MisalignedSwitchQuerySpecification getMisalignedSwitch() throws ViatraQueryException {
    return MisalignedSwitchQuerySpecification.instance();
  }
  
  public MisalignedSwitchMatcher getMisalignedSwitch(final ViatraQueryEngine engine) throws ViatraQueryException {
    return MisalignedSwitchMatcher.on(engine);
  }
}
