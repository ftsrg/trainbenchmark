/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/RouteSensorInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.RouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.RouteSensorInjectQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;

/**
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.viatra.routeSensorInject pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link RouteSensorInjectMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern routeSensorInject(route, sensor)
 * {
 * 	Route.requires(route, sensor);
 * }
 * </pre></code>
 * 
 * @see RouteSensorInjectMatch
 * @see RouteSensorInjectProcessor
 * @see RouteSensorInjectQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class RouteSensorInjectMatcher extends BaseMatcher<RouteSensorInjectMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static RouteSensorInjectMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    RouteSensorInjectMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (RouteSensorInjectMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static RouteSensorInjectMatcher create() throws ViatraQueryException {
    return new RouteSensorInjectMatcher();
  }
  
  private final static int POSITION_ROUTE = 0;
  
  private final static int POSITION_SENSOR = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(RouteSensorInjectMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private RouteSensorInjectMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @return matches represented as a RouteSensorInjectMatch object.
   * 
   */
  public Collection<RouteSensorInjectMatch> getAllMatches(final Route pRoute, final Sensor pSensor) {
    return rawGetAllMatches(new Object[]{pRoute, pSensor});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @return a match represented as a RouteSensorInjectMatch object, or null if no match is found.
   * 
   */
  public RouteSensorInjectMatch getOneArbitraryMatch(final Route pRoute, final Sensor pSensor) {
    return rawGetOneArbitraryMatch(new Object[]{pRoute, pSensor});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Route pRoute, final Sensor pSensor) {
    return rawHasMatch(new Object[]{pRoute, pSensor});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Route pRoute, final Sensor pSensor) {
    return rawCountMatches(new Object[]{pRoute, pSensor});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Route pRoute, final Sensor pSensor, final IMatchProcessor<? super RouteSensorInjectMatch> processor) {
    rawForEachMatch(new Object[]{pRoute, pSensor}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Route pRoute, final Sensor pSensor, final IMatchProcessor<? super RouteSensorInjectMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pRoute, pSensor}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public RouteSensorInjectMatch newMatch(final Route pRoute, final Sensor pSensor) {
    return RouteSensorInjectMatch.newMatch(pRoute, pSensor);
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Route> rawAccumulateAllValuesOfroute(final Object[] parameters) {
    Set<Route> results = new HashSet<Route>();
    rawAccumulateAllValues(POSITION_ROUTE, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute() {
    return rawAccumulateAllValuesOfroute(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute(final RouteSensorInjectMatch partialMatch) {
    return rawAccumulateAllValuesOfroute(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute(final Sensor pSensor) {
    return rawAccumulateAllValuesOfroute(new Object[]{
    null, 
    pSensor
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Sensor> rawAccumulateAllValuesOfsensor(final Object[] parameters) {
    Set<Sensor> results = new HashSet<Sensor>();
    rawAccumulateAllValues(POSITION_SENSOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor() {
    return rawAccumulateAllValuesOfsensor(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor(final RouteSensorInjectMatch partialMatch) {
    return rawAccumulateAllValuesOfsensor(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor(final Route pRoute) {
    return rawAccumulateAllValuesOfsensor(new Object[]{
    pRoute, 
    null
    });
  }
  
  @Override
  protected RouteSensorInjectMatch tupleToMatch(final Tuple t) {
    try {
        return RouteSensorInjectMatch.newMatch((Route) t.get(POSITION_ROUTE), (Sensor) t.get(POSITION_SENSOR));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected RouteSensorInjectMatch arrayToMatch(final Object[] match) {
    try {
        return RouteSensorInjectMatch.newMatch((Route) match[POSITION_ROUTE], (Sensor) match[POSITION_SENSOR]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected RouteSensorInjectMatch arrayToMatchMutable(final Object[] match) {
    try {
        return RouteSensorInjectMatch.newMutableMatch((Route) match[POSITION_ROUTE], (Sensor) match[POSITION_SENSOR]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  /**
   * @return the singleton instance of the query specification of this pattern
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<RouteSensorInjectMatcher> querySpecification() throws ViatraQueryException {
    return RouteSensorInjectQuerySpecification.instance();
  }
}
