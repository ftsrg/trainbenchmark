package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySignalSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.EntrySignalSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.Signal;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySignalSensor pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link EntrySignalSensorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern entrySignalSensor(signal, route2, sensor2)
 * {
 * 	find entrySignal(route2, signal);
 * 	find routeDefinition(route2, sensor2);
 * }
 * </pre></code>
 * 
 * @see EntrySignalSensorMatch
 * @see EntrySignalSensorProcessor
 * @see EntrySignalSensorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class EntrySignalSensorMatcher extends BaseMatcher<EntrySignalSensorMatch> {
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static EntrySignalSensorMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    EntrySignalSensorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new EntrySignalSensorMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_SIGNAL = 0;
  
  private final static int POSITION_ROUTE2 = 1;
  
  private final static int POSITION_SENSOR2 = 2;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(EntrySignalSensorMatcher.class);
  
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
  public EntrySignalSensorMatcher(final Notifier emfRoot) throws IncQueryException {
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
  public EntrySignalSensorMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSignal the fixed value of pattern parameter signal, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return matches represented as a EntrySignalSensorMatch object.
   * 
   */
  public Collection<EntrySignalSensorMatch> getAllMatches(final Signal pSignal, final Route pRoute2, final Sensor pSensor2) {
    return rawGetAllMatches(new Object[]{pSignal, pRoute2, pSensor2});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSignal the fixed value of pattern parameter signal, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return a match represented as a EntrySignalSensorMatch object, or null if no match is found.
   * 
   */
  public EntrySignalSensorMatch getOneArbitraryMatch(final Signal pSignal, final Route pRoute2, final Sensor pSensor2) {
    return rawGetOneArbitraryMatch(new Object[]{pSignal, pRoute2, pSensor2});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSignal the fixed value of pattern parameter signal, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Signal pSignal, final Route pRoute2, final Sensor pSensor2) {
    return rawHasMatch(new Object[]{pSignal, pRoute2, pSensor2});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSignal the fixed value of pattern parameter signal, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Signal pSignal, final Route pRoute2, final Sensor pSensor2) {
    return rawCountMatches(new Object[]{pSignal, pRoute2, pSensor2});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSignal the fixed value of pattern parameter signal, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Signal pSignal, final Route pRoute2, final Sensor pSensor2, final IMatchProcessor<? super EntrySignalSensorMatch> processor) {
    rawForEachMatch(new Object[]{pSignal, pRoute2, pSensor2}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSignal the fixed value of pattern parameter signal, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Signal pSignal, final Route pRoute2, final Sensor pSensor2, final IMatchProcessor<? super EntrySignalSensorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSignal, pRoute2, pSensor2}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSignal the fixed value of pattern parameter signal, or null if not bound.
   * @param pRoute2 the fixed value of pattern parameter route2, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public EntrySignalSensorMatch newMatch(final Signal pSignal, final Route pRoute2, final Sensor pSensor2) {
    return EntrySignalSensorMatch.newMatch(pSignal, pRoute2, pSensor2);
  }
  
  /**
   * Retrieve the set of values that occur in matches for signal.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Signal> rawAccumulateAllValuesOfsignal(final Object[] parameters) {
    Set<Signal> results = new HashSet<Signal>();
    rawAccumulateAllValues(POSITION_SIGNAL, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for signal.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Signal> getAllValuesOfsignal() {
    return rawAccumulateAllValuesOfsignal(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for signal.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Signal> getAllValuesOfsignal(final EntrySignalSensorMatch partialMatch) {
    return rawAccumulateAllValuesOfsignal(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for signal.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Signal> getAllValuesOfsignal(final Route pRoute2, final Sensor pSensor2) {
    return rawAccumulateAllValuesOfsignal(new Object[]{
    null, 
    pRoute2, 
    pSensor2
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for route2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Route> rawAccumulateAllValuesOfroute2(final Object[] parameters) {
    Set<Route> results = new HashSet<Route>();
    rawAccumulateAllValues(POSITION_ROUTE2, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for route2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute2() {
    return rawAccumulateAllValuesOfroute2(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute2(final EntrySignalSensorMatch partialMatch) {
    return rawAccumulateAllValuesOfroute2(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute2(final Signal pSignal, final Sensor pSensor2) {
    return rawAccumulateAllValuesOfroute2(new Object[]{
    pSignal, 
    null, 
    pSensor2
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Sensor> rawAccumulateAllValuesOfsensor2(final Object[] parameters) {
    Set<Sensor> results = new HashSet<Sensor>();
    rawAccumulateAllValues(POSITION_SENSOR2, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor2() {
    return rawAccumulateAllValuesOfsensor2(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor2(final EntrySignalSensorMatch partialMatch) {
    return rawAccumulateAllValuesOfsensor2(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor2(final Signal pSignal, final Route pRoute2) {
    return rawAccumulateAllValuesOfsensor2(new Object[]{
    pSignal, 
    pRoute2, 
    null
    });
  }
  
  @Override
  protected EntrySignalSensorMatch tupleToMatch(final Tuple t) {
    try {
    	return EntrySignalSensorMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Signal) t.get(POSITION_SIGNAL), (hu.bme.mit.trainbenchmark.railway.Route) t.get(POSITION_ROUTE2), (hu.bme.mit.trainbenchmark.railway.Sensor) t.get(POSITION_SENSOR2));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected EntrySignalSensorMatch arrayToMatch(final Object[] match) {
    try {
    	return EntrySignalSensorMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Signal) match[POSITION_SIGNAL], (hu.bme.mit.trainbenchmark.railway.Route) match[POSITION_ROUTE2], (hu.bme.mit.trainbenchmark.railway.Sensor) match[POSITION_SENSOR2]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected EntrySignalSensorMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return EntrySignalSensorMatch.newMutableMatch((hu.bme.mit.trainbenchmark.railway.Signal) match[POSITION_SIGNAL], (hu.bme.mit.trainbenchmark.railway.Route) match[POSITION_ROUTE2], (hu.bme.mit.trainbenchmark.railway.Sensor) match[POSITION_SENSOR2]);
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
  public static IQuerySpecification<EntrySignalSensorMatcher> querySpecification() throws IncQueryException {
    return EntrySignalSensorQuerySpecification.instance();
  }
}
