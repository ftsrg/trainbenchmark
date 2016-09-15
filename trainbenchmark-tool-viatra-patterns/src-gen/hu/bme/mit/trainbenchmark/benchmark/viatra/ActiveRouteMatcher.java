/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/ActiveRoute.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.ActiveRouteMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.ActiveRouteQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.viatra.ActiveRoute pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link ActiveRouteMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern ActiveRoute(route) {
 * 	Route(route);
 * 	neg find misalignedSwitch(route, _, _);
 * }
 * </pre></code>
 * 
 * @see ActiveRouteMatch
 * @see ActiveRouteProcessor
 * @see ActiveRouteQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class ActiveRouteMatcher extends BaseMatcher<ActiveRouteMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static ActiveRouteMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    ActiveRouteMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new ActiveRouteMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_ROUTE = 0;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(ActiveRouteMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private ActiveRouteMatcher(final ViatraQueryEngine engine) throws ViatraQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @return matches represented as a ActiveRouteMatch object.
   * 
   */
  public Collection<ActiveRouteMatch> getAllMatches(final Route pRoute) {
    return rawGetAllMatches(new Object[]{pRoute});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @return a match represented as a ActiveRouteMatch object, or null if no match is found.
   * 
   */
  public ActiveRouteMatch getOneArbitraryMatch(final Route pRoute) {
    return rawGetOneArbitraryMatch(new Object[]{pRoute});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Route pRoute) {
    return rawHasMatch(new Object[]{pRoute});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Route pRoute) {
    return rawCountMatches(new Object[]{pRoute});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Route pRoute, final IMatchProcessor<? super ActiveRouteMatch> processor) {
    rawForEachMatch(new Object[]{pRoute}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Route pRoute, final IMatchProcessor<? super ActiveRouteMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pRoute}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public ActiveRouteMatch newMatch(final Route pRoute) {
    return ActiveRouteMatch.newMatch(pRoute);
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
  
  @Override
  protected ActiveRouteMatch tupleToMatch(final Tuple t) {
    try {
    	return ActiveRouteMatch.newMatch((Route) t.get(POSITION_ROUTE));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected ActiveRouteMatch arrayToMatch(final Object[] match) {
    try {
    	return ActiveRouteMatch.newMatch((Route) match[POSITION_ROUTE]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected ActiveRouteMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return ActiveRouteMatch.newMutableMatch((Route) match[POSITION_ROUTE]);
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
  public static IQuerySpecification<ActiveRouteMatcher> querySpecification() throws ViatraQueryException {
    return ActiveRouteQuerySpecification.instance();
  }
}
