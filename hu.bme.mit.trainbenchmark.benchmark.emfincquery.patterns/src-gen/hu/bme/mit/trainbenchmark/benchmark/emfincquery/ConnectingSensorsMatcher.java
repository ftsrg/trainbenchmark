package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectingSensorsMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ConnectingSensorsQuerySpecification;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.connectingSensors pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link ConnectingSensorsMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern connectingSensors(sensor1, sensor2)
 * {
 * 	find sensorTrackElement(sensor1, te1);
 * 	find sensorTrackElement(sensor2, te2);
 * 	find trackElementConnected(te1, te2);
 * }
 * </pre></code>
 * 
 * @see ConnectingSensorsMatch
 * @see ConnectingSensorsProcessor
 * @see ConnectingSensorsQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class ConnectingSensorsMatcher extends BaseMatcher<ConnectingSensorsMatch> {
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static ConnectingSensorsMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    ConnectingSensorsMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new ConnectingSensorsMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_SENSOR1 = 0;
  
  private final static int POSITION_SENSOR2 = 1;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(ConnectingSensorsMatcher.class);
  
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
  public ConnectingSensorsMatcher(final Notifier emfRoot) throws IncQueryException {
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
  public ConnectingSensorsMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return matches represented as a ConnectingSensorsMatch object.
   * 
   */
  public Collection<ConnectingSensorsMatch> getAllMatches(final Sensor pSensor1, final Sensor pSensor2) {
    return rawGetAllMatches(new Object[]{pSensor1, pSensor2});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return a match represented as a ConnectingSensorsMatch object, or null if no match is found.
   * 
   */
  public ConnectingSensorsMatch getOneArbitraryMatch(final Sensor pSensor1, final Sensor pSensor2) {
    return rawGetOneArbitraryMatch(new Object[]{pSensor1, pSensor2});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Sensor pSensor1, final Sensor pSensor2) {
    return rawHasMatch(new Object[]{pSensor1, pSensor2});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Sensor pSensor1, final Sensor pSensor2) {
    return rawCountMatches(new Object[]{pSensor1, pSensor2});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Sensor pSensor1, final Sensor pSensor2, final IMatchProcessor<? super ConnectingSensorsMatch> processor) {
    rawForEachMatch(new Object[]{pSensor1, pSensor2}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Sensor pSensor1, final Sensor pSensor2, final IMatchProcessor<? super ConnectingSensorsMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSensor1, pSensor2}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public ConnectingSensorsMatch newMatch(final Sensor pSensor1, final Sensor pSensor2) {
    return ConnectingSensorsMatch.newMatch(pSensor1, pSensor2);
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Sensor> rawAccumulateAllValuesOfsensor1(final Object[] parameters) {
    Set<Sensor> results = new HashSet<Sensor>();
    rawAccumulateAllValues(POSITION_SENSOR1, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor1() {
    return rawAccumulateAllValuesOfsensor1(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor1(final ConnectingSensorsMatch partialMatch) {
    return rawAccumulateAllValuesOfsensor1(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor1(final Sensor pSensor2) {
    return rawAccumulateAllValuesOfsensor1(new Object[]{
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
  public Set<Sensor> getAllValuesOfsensor2(final ConnectingSensorsMatch partialMatch) {
    return rawAccumulateAllValuesOfsensor2(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor2(final Sensor pSensor1) {
    return rawAccumulateAllValuesOfsensor2(new Object[]{
    pSensor1, 
    null
    });
  }
  
  @Override
  protected ConnectingSensorsMatch tupleToMatch(final Tuple t) {
    try {
    	return ConnectingSensorsMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Sensor) t.get(POSITION_SENSOR1), (hu.bme.mit.trainbenchmark.railway.Sensor) t.get(POSITION_SENSOR2));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected ConnectingSensorsMatch arrayToMatch(final Object[] match) {
    try {
    	return ConnectingSensorsMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Sensor) match[POSITION_SENSOR1], (hu.bme.mit.trainbenchmark.railway.Sensor) match[POSITION_SENSOR2]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected ConnectingSensorsMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return ConnectingSensorsMatch.newMutableMatch((hu.bme.mit.trainbenchmark.railway.Sensor) match[POSITION_SENSOR1], (hu.bme.mit.trainbenchmark.railway.Sensor) match[POSITION_SENSOR2]);
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
  public static IQuerySpecification<ConnectingSensorsMatcher> querySpecification() throws IncQueryException {
    return ConnectingSensorsQuerySpecification.instance();
  }
}
