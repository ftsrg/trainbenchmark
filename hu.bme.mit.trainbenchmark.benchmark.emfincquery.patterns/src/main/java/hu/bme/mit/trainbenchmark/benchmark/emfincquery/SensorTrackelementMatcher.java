package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Sensor;
import Concept.Trackelement;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SensorTrackelementMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SensorTrackelementQuerySpecification;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.sensorTrackelement pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link SensorTrackelementMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern sensorTrackelement(Sen, Te) =
 * {
 * 	Sensor(Sen);
 * 	Trackelement(Te);
 * 	Sensor.Sensor_trackElement(Sen, Te);
 * }
 * </pre></code>
 * 
 * @see SensorTrackelementMatch
 * @see SensorTrackelementProcessor
 * @see SensorTrackelementQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class SensorTrackelementMatcher extends BaseMatcher<SensorTrackelementMatch> {
  /**
   * @return the singleton instance of the query specification of this pattern
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<SensorTrackelementMatcher> querySpecification() throws IncQueryException {
    return SensorTrackelementQuerySpecification.instance();
  }
  
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static SensorTrackelementMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    SensorTrackelementMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new SensorTrackelementMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_SEN = 0;
  
  private final static int POSITION_TE = 1;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(SensorTrackelementMatcher.class);
  
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
  public SensorTrackelementMatcher(final Notifier emfRoot) throws IncQueryException {
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
  public SensorTrackelementMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter Te, or null if not bound.
   * @return matches represented as a SensorTrackelementMatch object.
   * 
   */
  public Collection<SensorTrackelementMatch> getAllMatches(final Sensor pSen, final Trackelement pTe) {
    return rawGetAllMatches(new Object[]{pSen, pTe});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter Te, or null if not bound.
   * @return a match represented as a SensorTrackelementMatch object, or null if no match is found.
   * 
   */
  public SensorTrackelementMatch getOneArbitraryMatch(final Sensor pSen, final Trackelement pTe) {
    return rawGetOneArbitraryMatch(new Object[]{pSen, pTe});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter Te, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Sensor pSen, final Trackelement pTe) {
    return rawHasMatch(new Object[]{pSen, pTe});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter Te, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Sensor pSen, final Trackelement pTe) {
    return rawCountMatches(new Object[]{pSen, pTe});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter Te, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Sensor pSen, final Trackelement pTe, final IMatchProcessor<? super SensorTrackelementMatch> processor) {
    rawForEachMatch(new Object[]{pSen, pTe}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter Te, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Sensor pSen, final Trackelement pTe, final IMatchProcessor<? super SensorTrackelementMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSen, pTe}, processor);
  }
  
  /**
   * Registers a new filtered delta monitor on this pattern matcher.
   * The DeltaMonitor can be used to track changes (delta) in the set of filtered pattern matches from now on, considering those matches only that conform to the given fixed values of some parameters.
   * It can also be reset to track changes from a later point in time,
   * and changes can even be acknowledged on an individual basis.
   * See {@link DeltaMonitor} for details.
   * @param fillAtStart if true, all current matches are reported as new match events; if false, the delta monitor starts empty.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter Te, or null if not bound.
   * @return the delta monitor.
   * @deprecated use the IncQuery Databinding API (IncQueryObservables) instead.
   * 
   */
  @Deprecated
  public DeltaMonitor<SensorTrackelementMatch> newFilteredDeltaMonitor(final boolean fillAtStart, final Sensor pSen, final Trackelement pTe) {
    return rawNewFilteredDeltaMonitor(fillAtStart, new Object[]{pSen, pTe});
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter Te, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public SensorTrackelementMatch newMatch(final Sensor pSen, final Trackelement pTe) {
    return SensorTrackelementMatch.newMatch(pSen, pTe);
    
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sen.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Sensor> rawAccumulateAllValuesOfSen(final Object[] parameters) {
    Set<Sensor> results = new HashSet<Sensor>();
    rawAccumulateAllValues(POSITION_SEN, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sen.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfSen() {
    return rawAccumulateAllValuesOfSen(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sen.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfSen(final SensorTrackelementMatch partialMatch) {
    return rawAccumulateAllValuesOfSen(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sen.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfSen(final Trackelement pTe) {
    return rawAccumulateAllValuesOfSen(new Object[]{null, pTe});
  }
  
  /**
   * Retrieve the set of values that occur in matches for Te.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Trackelement> rawAccumulateAllValuesOfTe(final Object[] parameters) {
    Set<Trackelement> results = new HashSet<Trackelement>();
    rawAccumulateAllValues(POSITION_TE, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for Te.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Trackelement> getAllValuesOfTe() {
    return rawAccumulateAllValuesOfTe(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Te.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Trackelement> getAllValuesOfTe(final SensorTrackelementMatch partialMatch) {
    return rawAccumulateAllValuesOfTe(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Te.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Trackelement> getAllValuesOfTe(final Sensor pSen) {
    return rawAccumulateAllValuesOfTe(new Object[]{pSen, null});
  }
  
  @Override
  protected SensorTrackelementMatch tupleToMatch(final Tuple t) {
    try {
      return SensorTrackelementMatch.newMatch((Concept.Sensor) t.get(POSITION_SEN), (Concept.Trackelement) t.get(POSITION_TE));
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in tuple not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected SensorTrackelementMatch arrayToMatch(final Object[] match) {
    try {
      return SensorTrackelementMatch.newMatch((Concept.Sensor) match[POSITION_SEN], (Concept.Trackelement) match[POSITION_TE]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected SensorTrackelementMatch arrayToMatchMutable(final Object[] match) {
    try {
      return SensorTrackelementMatch.newMutableMatch((Concept.Sensor) match[POSITION_SEN], (Concept.Trackelement) match[POSITION_TE]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
}
