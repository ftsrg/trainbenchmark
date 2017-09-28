/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SemaphoreNeighbor.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.SemaphoreNeighborQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.viatra.semaphoreNeighbor pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link SemaphoreNeighborMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern semaphoreNeighbor(semaphore, route1, route2, sensor1, sensor2, te1, te2)
 * {
 * 	Route.exit(route1, semaphore);
 * 	Route.requires(route1, sensor1);
 * 	TrackElement.monitoredBy(te1, sensor1);
 * 	TrackElement.connectsTo(te1, te2);
 * 	TrackElement.monitoredBy(te2, sensor2);
 * 	Route.requires(route2, sensor2);
 * 	
 * 	neg find entrySemaphore(route2, semaphore);
 * 
 * 	route1 != route2;
 * }
 * </pre></code>
 * 
 * @see SemaphoreNeighborMatch
 * @see SemaphoreNeighborProcessor
 * @see SemaphoreNeighborQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class SemaphoreNeighborMatcher extends BaseMatcher<SemaphoreNeighborMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static SemaphoreNeighborMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    SemaphoreNeighborMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (SemaphoreNeighborMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static SemaphoreNeighborMatcher create() throws ViatraQueryException {
    return new SemaphoreNeighborMatcher();
  }
  
  private final static int POSITION_SEMAPHORE = 0;
  
  private final static int POSITION_ROUTE1 = 1;
  
  private final static int POSITION_ROUTE2 = 2;
  
  private final static int POSITION_SENSOR1 = 3;
  
  private final static int POSITION_SENSOR2 = 4;
  
  private final static int POSITION_TE1 = 5;
  
  private final static int POSITION_TE2 = 6;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(SemaphoreNeighborMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private SemaphoreNeighborMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @return matches represented as a SemaphoreNeighborMatch object.
   * 
   */
  public Collection<SemaphoreNeighborMatch> getAllMatches(final Semaphore pSemaphore, final Route pRoute1, final Route pRoute2, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe1, final TrackElement pTe2) {
    return rawGetAllMatches(new Object[]{pSemaphore, pRoute1, pRoute2, pSensor1, pSensor2, pTe1, pTe2});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @return a match represented as a SemaphoreNeighborMatch object, or null if no match is found.
   * 
   */
  public SemaphoreNeighborMatch getOneArbitraryMatch(final Semaphore pSemaphore, final Route pRoute1, final Route pRoute2, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe1, final TrackElement pTe2) {
    return rawGetOneArbitraryMatch(new Object[]{pSemaphore, pRoute1, pRoute2, pSensor1, pSensor2, pTe1, pTe2});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Semaphore pSemaphore, final Route pRoute1, final Route pRoute2, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe1, final TrackElement pTe2) {
    return rawHasMatch(new Object[]{pSemaphore, pRoute1, pRoute2, pSensor1, pSensor2, pTe1, pTe2});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Semaphore pSemaphore, final Route pRoute1, final Route pRoute2, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe1, final TrackElement pTe2) {
    return rawCountMatches(new Object[]{pSemaphore, pRoute1, pRoute2, pSensor1, pSensor2, pTe1, pTe2});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Semaphore pSemaphore, final Route pRoute1, final Route pRoute2, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe1, final TrackElement pTe2, final IMatchProcessor<? super SemaphoreNeighborMatch> processor) {
    rawForEachMatch(new Object[]{pSemaphore, pRoute1, pRoute2, pSensor1, pSensor2, pTe1, pTe2}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Semaphore pSemaphore, final Route pRoute1, final Route pRoute2, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe1, final TrackElement pTe2, final IMatchProcessor<? super SemaphoreNeighborMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSemaphore, pRoute1, pRoute2, pSensor1, pSensor2, pTe1, pTe2}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute1 the fixed value of pattern parameter route1, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public SemaphoreNeighborMatch newMatch(final Semaphore pSemaphore, final Route pRoute1, final Route pRoute2, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe1, final TrackElement pTe2) {
    return SemaphoreNeighborMatch.newMatch(pSemaphore, pRoute1, pRoute2, pSensor1, pSensor2, pTe1, pTe2);
  }
  
  /**
   * Retrieve the set of values that occur in matches for semaphore.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Semaphore> rawAccumulateAllValuesOfsemaphore(final Object[] parameters) {
    Set<Semaphore> results = new HashSet<Semaphore>();
    rawAccumulateAllValues(POSITION_SEMAPHORE, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for semaphore.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Semaphore> getAllValuesOfsemaphore() {
    return rawAccumulateAllValuesOfsemaphore(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for semaphore.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Semaphore> getAllValuesOfsemaphore(final SemaphoreNeighborMatch partialMatch) {
    return rawAccumulateAllValuesOfsemaphore(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for semaphore.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Semaphore> getAllValuesOfsemaphore(final Route pRoute1, final Route pRoute2, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe1, final TrackElement pTe2) {
    return rawAccumulateAllValuesOfsemaphore(new Object[]{
    null, 
    pRoute1, 
    pRoute2, 
    pSensor1, 
    pSensor2, 
    pTe1, 
    pTe2
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for route1.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Route> rawAccumulateAllValuesOfroute1(final Object[] parameters) {
    Set<Route> results = new HashSet<Route>();
    rawAccumulateAllValues(POSITION_ROUTE1, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for route1.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute1() {
    return rawAccumulateAllValuesOfroute1(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route1.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute1(final SemaphoreNeighborMatch partialMatch) {
    return rawAccumulateAllValuesOfroute1(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route1.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute1(final Semaphore pSemaphore, final Route pRoute2, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe1, final TrackElement pTe2) {
    return rawAccumulateAllValuesOfroute1(new Object[]{
    pSemaphore, 
    null, 
    pRoute2, 
    pSensor1, 
    pSensor2, 
    pTe1, 
    pTe2
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for route2.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Route> rawAccumulateAllValuesOfroute2(final Object[] parameters) {
    Set<Route> results = new HashSet<Route>();
    rawAccumulateAllValues(POSITION_ROUTE2, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for route2.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute2() {
    return rawAccumulateAllValuesOfroute2(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route2.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute2(final SemaphoreNeighborMatch partialMatch) {
    return rawAccumulateAllValuesOfroute2(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route2.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute2(final Semaphore pSemaphore, final Route pRoute1, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe1, final TrackElement pTe2) {
    return rawAccumulateAllValuesOfroute2(new Object[]{
    pSemaphore, 
    pRoute1, 
    null, 
    pSensor1, 
    pSensor2, 
    pTe1, 
    pTe2
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor1.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Sensor> rawAccumulateAllValuesOfsensor1(final Object[] parameters) {
    Set<Sensor> results = new HashSet<Sensor>();
    rawAccumulateAllValues(POSITION_SENSOR1, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor1.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor1() {
    return rawAccumulateAllValuesOfsensor1(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor1.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor1(final SemaphoreNeighborMatch partialMatch) {
    return rawAccumulateAllValuesOfsensor1(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor1.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor1(final Semaphore pSemaphore, final Route pRoute1, final Route pRoute2, final Sensor pSensor2, final TrackElement pTe1, final TrackElement pTe2) {
    return rawAccumulateAllValuesOfsensor1(new Object[]{
    pSemaphore, 
    pRoute1, 
    pRoute2, 
    null, 
    pSensor2, 
    pTe1, 
    pTe2
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor2.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Sensor> rawAccumulateAllValuesOfsensor2(final Object[] parameters) {
    Set<Sensor> results = new HashSet<Sensor>();
    rawAccumulateAllValues(POSITION_SENSOR2, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor2.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor2() {
    return rawAccumulateAllValuesOfsensor2(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor2.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor2(final SemaphoreNeighborMatch partialMatch) {
    return rawAccumulateAllValuesOfsensor2(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor2.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor2(final Semaphore pSemaphore, final Route pRoute1, final Route pRoute2, final Sensor pSensor1, final TrackElement pTe1, final TrackElement pTe2) {
    return rawAccumulateAllValuesOfsensor2(new Object[]{
    pSemaphore, 
    pRoute1, 
    pRoute2, 
    pSensor1, 
    null, 
    pTe1, 
    pTe2
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for te1.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<TrackElement> rawAccumulateAllValuesOfte1(final Object[] parameters) {
    Set<TrackElement> results = new HashSet<TrackElement>();
    rawAccumulateAllValues(POSITION_TE1, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for te1.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<TrackElement> getAllValuesOfte1() {
    return rawAccumulateAllValuesOfte1(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for te1.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<TrackElement> getAllValuesOfte1(final SemaphoreNeighborMatch partialMatch) {
    return rawAccumulateAllValuesOfte1(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for te1.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<TrackElement> getAllValuesOfte1(final Semaphore pSemaphore, final Route pRoute1, final Route pRoute2, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe2) {
    return rawAccumulateAllValuesOfte1(new Object[]{
    pSemaphore, 
    pRoute1, 
    pRoute2, 
    pSensor1, 
    pSensor2, 
    null, 
    pTe2
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for te2.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<TrackElement> rawAccumulateAllValuesOfte2(final Object[] parameters) {
    Set<TrackElement> results = new HashSet<TrackElement>();
    rawAccumulateAllValues(POSITION_TE2, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for te2.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<TrackElement> getAllValuesOfte2() {
    return rawAccumulateAllValuesOfte2(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for te2.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<TrackElement> getAllValuesOfte2(final SemaphoreNeighborMatch partialMatch) {
    return rawAccumulateAllValuesOfte2(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for te2.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<TrackElement> getAllValuesOfte2(final Semaphore pSemaphore, final Route pRoute1, final Route pRoute2, final Sensor pSensor1, final Sensor pSensor2, final TrackElement pTe1) {
    return rawAccumulateAllValuesOfte2(new Object[]{
    pSemaphore, 
    pRoute1, 
    pRoute2, 
    pSensor1, 
    pSensor2, 
    pTe1, 
    null
    });
  }
  
  @Override
  protected SemaphoreNeighborMatch tupleToMatch(final Tuple t) {
    try {
        return SemaphoreNeighborMatch.newMatch((Semaphore) t.get(POSITION_SEMAPHORE), (Route) t.get(POSITION_ROUTE1), (Route) t.get(POSITION_ROUTE2), (Sensor) t.get(POSITION_SENSOR1), (Sensor) t.get(POSITION_SENSOR2), (TrackElement) t.get(POSITION_TE1), (TrackElement) t.get(POSITION_TE2));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected SemaphoreNeighborMatch arrayToMatch(final Object[] match) {
    try {
        return SemaphoreNeighborMatch.newMatch((Semaphore) match[POSITION_SEMAPHORE], (Route) match[POSITION_ROUTE1], (Route) match[POSITION_ROUTE2], (Sensor) match[POSITION_SENSOR1], (Sensor) match[POSITION_SENSOR2], (TrackElement) match[POSITION_TE1], (TrackElement) match[POSITION_TE2]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected SemaphoreNeighborMatch arrayToMatchMutable(final Object[] match) {
    try {
        return SemaphoreNeighborMatch.newMutableMatch((Semaphore) match[POSITION_SEMAPHORE], (Route) match[POSITION_ROUTE1], (Route) match[POSITION_ROUTE2], (Sensor) match[POSITION_SENSOR1], (Sensor) match[POSITION_SENSOR2], (TrackElement) match[POSITION_TE1], (TrackElement) match[POSITION_TE2]);
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
  public static IQuerySpecification<SemaphoreNeighborMatcher> querySpecification() throws ViatraQueryException {
    return SemaphoreNeighborQuerySpecification.instance();
  }
}
