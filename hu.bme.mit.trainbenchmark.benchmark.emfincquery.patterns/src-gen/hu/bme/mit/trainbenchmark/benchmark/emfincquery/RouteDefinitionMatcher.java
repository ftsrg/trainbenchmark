package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RouteDefinitionMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.RouteDefinitionQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.incquery.runtime.api.IMatchProcessor;
import org.eclipse.incquery.runtime.api.IQuerySpecification;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseMatcher;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.tuple.Tuple;
import org.eclipse.incquery.runtime.util.IncQueryLoggingUtil;

/**
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.routeDefinition pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link RouteDefinitionMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern routeDefinition(route, sensor)
 * {
 * 	Route(route);
 * 	Sensor(sensor);
 * 	Route.Route_routeDefinition(route, sensor);
 * }
 * </pre></code>
 * 
 * @see RouteDefinitionMatch
 * @see RouteDefinitionProcessor
 * @see RouteDefinitionQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class RouteDefinitionMatcher extends BaseMatcher<RouteDefinitionMatch> {
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static RouteDefinitionMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    RouteDefinitionMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new RouteDefinitionMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_ROUTE = 0;
  
  private final static int POSITION_SENSOR = 1;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(RouteDefinitionMatcher.class);
  
  /**
   * Initializes the pattern matcher over a given EMF model root (recommended: Resource or ResourceSet).
   * If a pattern matcher is already constructed with the same root, only a light-weight reference is returned.
   * The scope of pattern matching will be the given EMF model root and below (see FAQ for more precise definition).
   * The match set will be incrementally refreshed upon updates from this scope.
   * <p>The matcher will be created within the managed {@link IncQueryEngine} belonging to the EMF model root, so
   * multiple matchers will reuse the same engine and benefit from increased performance and reduced memory footprint.
   * @param emfRoot the root of the EMF containment hierarchy where the pattern matcher will operate. Recommended: Resource or ResourceSet.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * @deprecated use {@link #on(IncQueryEngine)} instead, e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}
   * 
   */
  @Deprecated
  public RouteDefinitionMatcher(final Notifier emfRoot) throws IncQueryException {
    this(IncQueryEngine.on(emfRoot));
  }
  
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * @deprecated use {@link #on(IncQueryEngine)} instead
   * 
   */
  @Deprecated
  public RouteDefinitionMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @return matches represented as a RouteDefinitionMatch object.
   * 
   */
  public Collection<RouteDefinitionMatch> getAllMatches(final Route pRoute, final Sensor pSensor) {
    return rawGetAllMatches(new Object[]{pRoute, pSensor});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @return a match represented as a RouteDefinitionMatch object, or null if no match is found.
   * 
   */
  public RouteDefinitionMatch getOneArbitraryMatch(final Route pRoute, final Sensor pSensor) {
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
  public void forEachMatch(final Route pRoute, final Sensor pSensor, final IMatchProcessor<? super RouteDefinitionMatch> processor) {
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
  public boolean forOneArbitraryMatch(final Route pRoute, final Sensor pSensor, final IMatchProcessor<? super RouteDefinitionMatch> processor) {
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
  public RouteDefinitionMatch newMatch(final Route pRoute, final Sensor pSensor) {
    return RouteDefinitionMatch.newMatch(pRoute, pSensor);
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Route> rawAccumulateAllValuesOfroute(final Object[] parameters) {
    Set<Route> results = new HashSet<Route>();
    rawAccumulateAllValues(POSITION_ROUTE, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute() {
    return rawAccumulateAllValuesOfroute(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute(final RouteDefinitionMatch partialMatch) {
    return rawAccumulateAllValuesOfroute(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
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
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Sensor> rawAccumulateAllValuesOfsensor(final Object[] parameters) {
    Set<Sensor> results = new HashSet<Sensor>();
    rawAccumulateAllValues(POSITION_SENSOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor() {
    return rawAccumulateAllValuesOfsensor(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor(final RouteDefinitionMatch partialMatch) {
    return rawAccumulateAllValuesOfsensor(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor(final Route pRoute) {
    return rawAccumulateAllValuesOfsensor(new Object[]{
    pRoute, 
    null
    });
  }
  
  @Override
  protected RouteDefinitionMatch tupleToMatch(final Tuple t) {
    try {
    	return RouteDefinitionMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Route) t.get(POSITION_ROUTE), (hu.bme.mit.trainbenchmark.railway.Sensor) t.get(POSITION_SENSOR));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected RouteDefinitionMatch arrayToMatch(final Object[] match) {
    try {
    	return RouteDefinitionMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Route) match[POSITION_ROUTE], (hu.bme.mit.trainbenchmark.railway.Sensor) match[POSITION_SENSOR]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected RouteDefinitionMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return RouteDefinitionMatch.newMutableMatch((hu.bme.mit.trainbenchmark.railway.Route) match[POSITION_ROUTE], (hu.bme.mit.trainbenchmark.railway.Sensor) match[POSITION_SENSOR]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  /**
   * @return the singleton instance of the query specification of this pattern
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<RouteDefinitionMatcher> querySpecification() throws IncQueryException {
    return RouteDefinitionQuerySpecification.instance();
  }
}
