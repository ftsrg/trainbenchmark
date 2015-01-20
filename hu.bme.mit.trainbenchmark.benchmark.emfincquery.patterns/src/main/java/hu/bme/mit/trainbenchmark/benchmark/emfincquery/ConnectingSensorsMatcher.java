package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Sensor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectingSensorsMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ConnectingSensorsQuerySpecification;
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
import org.eclipse.incquery.runtime.rete.misc.DeltaMonitor;
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
 * pattern connectingSensors(Sen1, Sen2) =
 * {
 * 	find sensorTrackelement(Sen1, Te1);
 * 	find sensorTrackelement(Sen2, Te2);
 * 	find trackelementConnected(Te1, Te2);
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
   * @return the singleton instance of the query specification of this pattern
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<ConnectingSensorsMatcher> querySpecification() throws IncQueryException {
    return ConnectingSensorsQuerySpecification.instance();
  }
  
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
  
  private final static int POSITION_SEN1 = 0;
  
  private final static int POSITION_SEN2 = 1;
  
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
   * @param pSen1 the fixed value of pattern parameter Sen1, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @return matches represented as a ConnectingSensorsMatch object.
   * 
   */
  public Collection<ConnectingSensorsMatch> getAllMatches(final Sensor pSen1, final Sensor pSen2) {
    return rawGetAllMatches(new Object[]{pSen1, pSen2});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSen1 the fixed value of pattern parameter Sen1, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @return a match represented as a ConnectingSensorsMatch object, or null if no match is found.
   * 
   */
  public ConnectingSensorsMatch getOneArbitraryMatch(final Sensor pSen1, final Sensor pSen2) {
    return rawGetOneArbitraryMatch(new Object[]{pSen1, pSen2});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSen1 the fixed value of pattern parameter Sen1, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Sensor pSen1, final Sensor pSen2) {
    return rawHasMatch(new Object[]{pSen1, pSen2});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSen1 the fixed value of pattern parameter Sen1, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Sensor pSen1, final Sensor pSen2) {
    return rawCountMatches(new Object[]{pSen1, pSen2});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSen1 the fixed value of pattern parameter Sen1, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Sensor pSen1, final Sensor pSen2, final IMatchProcessor<? super ConnectingSensorsMatch> processor) {
    rawForEachMatch(new Object[]{pSen1, pSen2}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSen1 the fixed value of pattern parameter Sen1, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Sensor pSen1, final Sensor pSen2, final IMatchProcessor<? super ConnectingSensorsMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSen1, pSen2}, processor);
  }
  
  /**
   * Registers a new filtered delta monitor on this pattern matcher.
   * The DeltaMonitor can be used to track changes (delta) in the set of filtered pattern matches from now on, considering those matches only that conform to the given fixed values of some parameters.
   * It can also be reset to track changes from a later point in time,
   * and changes can even be acknowledged on an individual basis.
   * See {@link DeltaMonitor} for details.
   * @param fillAtStart if true, all current matches are reported as new match events; if false, the delta monitor starts empty.
   * @param pSen1 the fixed value of pattern parameter Sen1, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @return the delta monitor.
   * @deprecated use the IncQuery Databinding API (IncQueryObservables) instead.
   * 
   */
  @Deprecated
  public DeltaMonitor<ConnectingSensorsMatch> newFilteredDeltaMonitor(final boolean fillAtStart, final Sensor pSen1, final Sensor pSen2) {
    return rawNewFilteredDeltaMonitor(fillAtStart, new Object[]{pSen1, pSen2});
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSen1 the fixed value of pattern parameter Sen1, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public ConnectingSensorsMatch newMatch(final Sensor pSen1, final Sensor pSen2) {
    return ConnectingSensorsMatch.newMatch(pSen1, pSen2);
    
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sen1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Sensor> rawAccumulateAllValuesOfSen1(final Object[] parameters) {
    Set<Sensor> results = new HashSet<Sensor>();
    rawAccumulateAllValues(POSITION_SEN1, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sen1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfSen1() {
    return rawAccumulateAllValuesOfSen1(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sen1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfSen1(final ConnectingSensorsMatch partialMatch) {
    return rawAccumulateAllValuesOfSen1(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sen1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfSen1(final Sensor pSen2) {
    return rawAccumulateAllValuesOfSen1(new Object[]{null, pSen2});
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sen2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Sensor> rawAccumulateAllValuesOfSen2(final Object[] parameters) {
    Set<Sensor> results = new HashSet<Sensor>();
    rawAccumulateAllValues(POSITION_SEN2, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sen2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfSen2() {
    return rawAccumulateAllValuesOfSen2(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sen2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfSen2(final ConnectingSensorsMatch partialMatch) {
    return rawAccumulateAllValuesOfSen2(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sen2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfSen2(final Sensor pSen1) {
    return rawAccumulateAllValuesOfSen2(new Object[]{pSen1, null});
  }
  
  @Override
  protected ConnectingSensorsMatch tupleToMatch(final Tuple t) {
    try {
      return ConnectingSensorsMatch.newMatch((Concept.Sensor) t.get(POSITION_SEN1), (Concept.Sensor) t.get(POSITION_SEN2));
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in tuple not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected ConnectingSensorsMatch arrayToMatch(final Object[] match) {
    try {
      return ConnectingSensorsMatch.newMatch((Concept.Sensor) match[POSITION_SEN1], (Concept.Sensor) match[POSITION_SEN2]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected ConnectingSensorsMatch arrayToMatchMutable(final Object[] match) {
    try {
      return ConnectingSensorsMatch.newMutableMatch((Concept.Sensor) match[POSITION_SEN1], (Concept.Sensor) match[POSITION_SEN2]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
}
