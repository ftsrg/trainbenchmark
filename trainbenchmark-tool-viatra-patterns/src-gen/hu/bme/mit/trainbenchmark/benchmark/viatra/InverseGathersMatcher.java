package hu.bme.mit.trainbenchmark.benchmark.viatra;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;

import hu.bme.mit.trainbenchmark.benchmark.viatra.util.InverseGathersProcessor;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.InverseGathersQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;

/**
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.viatra.inverseGathers pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link InverseGathersMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern inverseGathers(sensor, route)
 * {
 * 	Route.gathers(route, sensor);
 * }
 * </pre></code>
 * 
 * @see InverseGathersMatch
 * @see InverseGathersProcessor
 * @see InverseGathersQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class InverseGathersMatcher extends BaseMatcher<InverseGathersMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static InverseGathersMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    InverseGathersMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new InverseGathersMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_SENSOR = 0;
  
  private final static int POSITION_ROUTE = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(InverseGathersMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private InverseGathersMatcher(final ViatraQueryEngine engine) throws ViatraQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @return matches represented as a InverseGathersMatch object.
   * 
   */
  public Collection<InverseGathersMatch> getAllMatches(final Sensor pSensor, final Route pRoute) {
    return rawGetAllMatches(new Object[]{pSensor, pRoute});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @return a match represented as a InverseGathersMatch object, or null if no match is found.
   * 
   */
  public InverseGathersMatch getOneArbitraryMatch(final Sensor pSensor, final Route pRoute) {
    return rawGetOneArbitraryMatch(new Object[]{pSensor, pRoute});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Sensor pSensor, final Route pRoute) {
    return rawHasMatch(new Object[]{pSensor, pRoute});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Sensor pSensor, final Route pRoute) {
    return rawCountMatches(new Object[]{pSensor, pRoute});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Sensor pSensor, final Route pRoute, final IMatchProcessor<? super InverseGathersMatch> processor) {
    rawForEachMatch(new Object[]{pSensor, pRoute}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Sensor pSensor, final Route pRoute, final IMatchProcessor<? super InverseGathersMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSensor, pRoute}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public InverseGathersMatch newMatch(final Sensor pSensor, final Route pRoute) {
    return InverseGathersMatch.newMatch(pSensor, pRoute);
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
  public Set<Sensor> getAllValuesOfsensor(final InverseGathersMatch partialMatch) {
    return rawAccumulateAllValuesOfsensor(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor(final Route pRoute) {
    return rawAccumulateAllValuesOfsensor(new Object[]{
    null, 
    pRoute
    });
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
  public Set<Route> getAllValuesOfroute(final InverseGathersMatch partialMatch) {
    return rawAccumulateAllValuesOfroute(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute(final Sensor pSensor) {
    return rawAccumulateAllValuesOfroute(new Object[]{
    pSensor, 
    null
    });
  }
  
  @Override
  protected InverseGathersMatch tupleToMatch(final Tuple t) {
    try {
    	return InverseGathersMatch.newMatch((Sensor) t.get(POSITION_SENSOR), (Route) t.get(POSITION_ROUTE));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected InverseGathersMatch arrayToMatch(final Object[] match) {
    try {
    	return InverseGathersMatch.newMatch((Sensor) match[POSITION_SENSOR], (Route) match[POSITION_ROUTE]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected InverseGathersMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return InverseGathersMatch.newMutableMatch((Sensor) match[POSITION_SENSOR], (Route) match[POSITION_ROUTE]);
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
  public static IQuerySpecification<InverseGathersMatcher> querySpecification() throws ViatraQueryException {
    return InverseGathersQuerySpecification.instance();
  }
}
