package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.InverseDefinedByMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.InverseDefinedByQuerySpecification;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.inverseDefinedBy pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link InverseDefinedByMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern inverseDefinedBy(sensor, route)
 * {
 * 	Route.definedBy(route, sensor);
 * }
 * </pre></code>
 * 
 * @see InverseDefinedByMatch
 * @see InverseDefinedByProcessor
 * @see InverseDefinedByQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class InverseDefinedByMatcher extends BaseMatcher<InverseDefinedByMatch> {
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static InverseDefinedByMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    InverseDefinedByMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new InverseDefinedByMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_SENSOR = 0;
  
  private final static int POSITION_ROUTE = 1;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(InverseDefinedByMatcher.class);
  
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
  public InverseDefinedByMatcher(final Notifier emfRoot) throws IncQueryException {
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
  public InverseDefinedByMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @return matches represented as a InverseDefinedByMatch object.
   * 
   */
  public Collection<InverseDefinedByMatch> getAllMatches(final Sensor pSensor, final Route pRoute) {
    return rawGetAllMatches(new Object[]{pSensor, pRoute});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @return a match represented as a InverseDefinedByMatch object, or null if no match is found.
   * 
   */
  public InverseDefinedByMatch getOneArbitraryMatch(final Sensor pSensor, final Route pRoute) {
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
  public void forEachMatch(final Sensor pSensor, final Route pRoute, final IMatchProcessor<? super InverseDefinedByMatch> processor) {
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
  public boolean forOneArbitraryMatch(final Sensor pSensor, final Route pRoute, final IMatchProcessor<? super InverseDefinedByMatch> processor) {
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
  public InverseDefinedByMatch newMatch(final Sensor pSensor, final Route pRoute) {
    return InverseDefinedByMatch.newMatch(pSensor, pRoute);
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
  public Set<Sensor> getAllValuesOfsensor(final InverseDefinedByMatch partialMatch) {
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
  public Set<Route> getAllValuesOfroute(final InverseDefinedByMatch partialMatch) {
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
  protected InverseDefinedByMatch tupleToMatch(final Tuple t) {
    try {
    	return InverseDefinedByMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Sensor) t.get(POSITION_SENSOR), (hu.bme.mit.trainbenchmark.railway.Route) t.get(POSITION_ROUTE));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected InverseDefinedByMatch arrayToMatch(final Object[] match) {
    try {
    	return InverseDefinedByMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Sensor) match[POSITION_SENSOR], (hu.bme.mit.trainbenchmark.railway.Route) match[POSITION_ROUTE]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected InverseDefinedByMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return InverseDefinedByMatch.newMutableMatch((hu.bme.mit.trainbenchmark.railway.Sensor) match[POSITION_SENSOR], (hu.bme.mit.trainbenchmark.railway.Route) match[POSITION_ROUTE]);
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
  public static IQuerySpecification<InverseDefinedByMatcher> querySpecification() throws IncQueryException {
    return InverseDefinedByQuerySpecification.instance();
  }
}
