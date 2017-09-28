/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/RouteSensor.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.RouteSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.viatra.routeSensor pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link RouteSensorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern routeSensor(route, sensor, swP, sw)
 * {
 * 	Route.follows(route, swP);
 * 	SwitchPosition.target(swP, sw);
 * 	TrackElement.monitoredBy(sw, sensor);
 * 
 * 	neg find required(sensor, route);
 * }
 * </pre></code>
 * 
 * @see RouteSensorMatch
 * @see RouteSensorProcessor
 * @see RouteSensorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class RouteSensorMatcher extends BaseMatcher<RouteSensorMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static RouteSensorMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    RouteSensorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (RouteSensorMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static RouteSensorMatcher create() throws ViatraQueryException {
    return new RouteSensorMatcher();
  }
  
  private final static int POSITION_ROUTE = 0;
  
  private final static int POSITION_SENSOR = 1;
  
  private final static int POSITION_SWP = 2;
  
  private final static int POSITION_SW = 3;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(RouteSensorMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private RouteSensorMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSwP the fixed value of pattern parameter swP, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return matches represented as a RouteSensorMatch object.
   * 
   */
  public Collection<RouteSensorMatch> getAllMatches(final Route pRoute, final Sensor pSensor, final SwitchPosition pSwP, final Switch pSw) {
    return rawGetAllMatches(new Object[]{pRoute, pSensor, pSwP, pSw});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSwP the fixed value of pattern parameter swP, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return a match represented as a RouteSensorMatch object, or null if no match is found.
   * 
   */
  public RouteSensorMatch getOneArbitraryMatch(final Route pRoute, final Sensor pSensor, final SwitchPosition pSwP, final Switch pSw) {
    return rawGetOneArbitraryMatch(new Object[]{pRoute, pSensor, pSwP, pSw});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSwP the fixed value of pattern parameter swP, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Route pRoute, final Sensor pSensor, final SwitchPosition pSwP, final Switch pSw) {
    return rawHasMatch(new Object[]{pRoute, pSensor, pSwP, pSw});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSwP the fixed value of pattern parameter swP, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Route pRoute, final Sensor pSensor, final SwitchPosition pSwP, final Switch pSw) {
    return rawCountMatches(new Object[]{pRoute, pSensor, pSwP, pSw});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSwP the fixed value of pattern parameter swP, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Route pRoute, final Sensor pSensor, final SwitchPosition pSwP, final Switch pSw, final IMatchProcessor<? super RouteSensorMatch> processor) {
    rawForEachMatch(new Object[]{pRoute, pSensor, pSwP, pSw}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSwP the fixed value of pattern parameter swP, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Route pRoute, final Sensor pSensor, final SwitchPosition pSwP, final Switch pSw, final IMatchProcessor<? super RouteSensorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pRoute, pSensor, pSwP, pSw}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSwP the fixed value of pattern parameter swP, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public RouteSensorMatch newMatch(final Route pRoute, final Sensor pSensor, final SwitchPosition pSwP, final Switch pSw) {
    return RouteSensorMatch.newMatch(pRoute, pSensor, pSwP, pSw);
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
  public Set<Route> getAllValuesOfroute(final RouteSensorMatch partialMatch) {
    return rawAccumulateAllValuesOfroute(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute(final Sensor pSensor, final SwitchPosition pSwP, final Switch pSw) {
    return rawAccumulateAllValuesOfroute(new Object[]{
    null, 
    pSensor, 
    pSwP, 
    pSw
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
  public Set<Sensor> getAllValuesOfsensor(final RouteSensorMatch partialMatch) {
    return rawAccumulateAllValuesOfsensor(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor(final Route pRoute, final SwitchPosition pSwP, final Switch pSw) {
    return rawAccumulateAllValuesOfsensor(new Object[]{
    pRoute, 
    null, 
    pSwP, 
    pSw
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for swP.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<SwitchPosition> rawAccumulateAllValuesOfswP(final Object[] parameters) {
    Set<SwitchPosition> results = new HashSet<SwitchPosition>();
    rawAccumulateAllValues(POSITION_SWP, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for swP.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<SwitchPosition> getAllValuesOfswP() {
    return rawAccumulateAllValuesOfswP(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for swP.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<SwitchPosition> getAllValuesOfswP(final RouteSensorMatch partialMatch) {
    return rawAccumulateAllValuesOfswP(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for swP.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<SwitchPosition> getAllValuesOfswP(final Route pRoute, final Sensor pSensor, final Switch pSw) {
    return rawAccumulateAllValuesOfswP(new Object[]{
    pRoute, 
    pSensor, 
    null, 
    pSw
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for sw.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Switch> rawAccumulateAllValuesOfsw(final Object[] parameters) {
    Set<Switch> results = new HashSet<Switch>();
    rawAccumulateAllValues(POSITION_SW, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for sw.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Switch> getAllValuesOfsw() {
    return rawAccumulateAllValuesOfsw(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sw.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Switch> getAllValuesOfsw(final RouteSensorMatch partialMatch) {
    return rawAccumulateAllValuesOfsw(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sw.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Switch> getAllValuesOfsw(final Route pRoute, final Sensor pSensor, final SwitchPosition pSwP) {
    return rawAccumulateAllValuesOfsw(new Object[]{
    pRoute, 
    pSensor, 
    pSwP, 
    null
    });
  }
  
  @Override
  protected RouteSensorMatch tupleToMatch(final Tuple t) {
    try {
        return RouteSensorMatch.newMatch((Route) t.get(POSITION_ROUTE), (Sensor) t.get(POSITION_SENSOR), (SwitchPosition) t.get(POSITION_SWP), (Switch) t.get(POSITION_SW));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected RouteSensorMatch arrayToMatch(final Object[] match) {
    try {
        return RouteSensorMatch.newMatch((Route) match[POSITION_ROUTE], (Sensor) match[POSITION_SENSOR], (SwitchPosition) match[POSITION_SWP], (Switch) match[POSITION_SW]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected RouteSensorMatch arrayToMatchMutable(final Object[] match) {
    try {
        return RouteSensorMatch.newMutableMatch((Route) match[POSITION_ROUTE], (Sensor) match[POSITION_SENSOR], (SwitchPosition) match[POSITION_SWP], (Switch) match[POSITION_SW]);
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
  public static IQuerySpecification<RouteSensorMatcher> querySpecification() throws ViatraQueryException {
    return RouteSensorQuerySpecification.instance();
  }
}
