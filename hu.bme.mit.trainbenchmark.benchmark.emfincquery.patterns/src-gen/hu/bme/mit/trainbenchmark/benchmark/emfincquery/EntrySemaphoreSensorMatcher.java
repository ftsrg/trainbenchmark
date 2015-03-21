package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySemaphoreSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.EntrySemaphoreSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySemaphoreSensor pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link EntrySemaphoreSensorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern entrySemaphoreSensor(semaphore, sensor2) {
 * 	Route.entry(route2, semaphore);
 * 	Route.definedBy(route2, sensor2);
 * }
 * </pre></code>
 * 
 * @see EntrySemaphoreSensorMatch
 * @see EntrySemaphoreSensorProcessor
 * @see EntrySemaphoreSensorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class EntrySemaphoreSensorMatcher extends BaseMatcher<EntrySemaphoreSensorMatch> {
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static EntrySemaphoreSensorMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    EntrySemaphoreSensorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new EntrySemaphoreSensorMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_SEMAPHORE = 0;
  
  private final static int POSITION_SENSOR2 = 1;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(EntrySemaphoreSensorMatcher.class);
  
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
  public EntrySemaphoreSensorMatcher(final Notifier emfRoot) throws IncQueryException {
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
  public EntrySemaphoreSensorMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return matches represented as a EntrySemaphoreSensorMatch object.
   * 
   */
  public Collection<EntrySemaphoreSensorMatch> getAllMatches(final Semaphore pSemaphore, final Sensor pSensor2) {
    return rawGetAllMatches(new Object[]{pSemaphore, pSensor2});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return a match represented as a EntrySemaphoreSensorMatch object, or null if no match is found.
   * 
   */
  public EntrySemaphoreSensorMatch getOneArbitraryMatch(final Semaphore pSemaphore, final Sensor pSensor2) {
    return rawGetOneArbitraryMatch(new Object[]{pSemaphore, pSensor2});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Semaphore pSemaphore, final Sensor pSensor2) {
    return rawHasMatch(new Object[]{pSemaphore, pSensor2});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Semaphore pSemaphore, final Sensor pSensor2) {
    return rawCountMatches(new Object[]{pSemaphore, pSensor2});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Semaphore pSemaphore, final Sensor pSensor2, final IMatchProcessor<? super EntrySemaphoreSensorMatch> processor) {
    rawForEachMatch(new Object[]{pSemaphore, pSensor2}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Semaphore pSemaphore, final Sensor pSensor2, final IMatchProcessor<? super EntrySemaphoreSensorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSemaphore, pSensor2}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public EntrySemaphoreSensorMatch newMatch(final Semaphore pSemaphore, final Sensor pSensor2) {
    return EntrySemaphoreSensorMatch.newMatch(pSemaphore, pSensor2);
  }
  
  /**
   * Retrieve the set of values that occur in matches for semaphore.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Semaphore> rawAccumulateAllValuesOfsemaphore(final Object[] parameters) {
    Set<Semaphore> results = new HashSet<Semaphore>();
    rawAccumulateAllValues(POSITION_SEMAPHORE, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for semaphore.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Semaphore> getAllValuesOfsemaphore() {
    return rawAccumulateAllValuesOfsemaphore(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for semaphore.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Semaphore> getAllValuesOfsemaphore(final EntrySemaphoreSensorMatch partialMatch) {
    return rawAccumulateAllValuesOfsemaphore(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for semaphore.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Semaphore> getAllValuesOfsemaphore(final Sensor pSensor2) {
    return rawAccumulateAllValuesOfsemaphore(new Object[]{
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
  public Set<Sensor> getAllValuesOfsensor2(final EntrySemaphoreSensorMatch partialMatch) {
    return rawAccumulateAllValuesOfsensor2(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor2(final Semaphore pSemaphore) {
    return rawAccumulateAllValuesOfsensor2(new Object[]{
    pSemaphore, 
    null
    });
  }
  
  @Override
  protected EntrySemaphoreSensorMatch tupleToMatch(final Tuple t) {
    try {
    	return EntrySemaphoreSensorMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Semaphore) t.get(POSITION_SEMAPHORE), (hu.bme.mit.trainbenchmark.railway.Sensor) t.get(POSITION_SENSOR2));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected EntrySemaphoreSensorMatch arrayToMatch(final Object[] match) {
    try {
    	return EntrySemaphoreSensorMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Semaphore) match[POSITION_SEMAPHORE], (hu.bme.mit.trainbenchmark.railway.Sensor) match[POSITION_SENSOR2]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected EntrySemaphoreSensorMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return EntrySemaphoreSensorMatch.newMutableMatch((hu.bme.mit.trainbenchmark.railway.Semaphore) match[POSITION_SEMAPHORE], (hu.bme.mit.trainbenchmark.railway.Sensor) match[POSITION_SENSOR2]);
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
  public static IQuerySpecification<EntrySemaphoreSensorMatcher> querySpecification() throws IncQueryException {
    return EntrySemaphoreSensorQuerySpecification.instance();
  }
}
