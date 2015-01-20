package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Route;
import Concept.Sensor;
import Concept.Signal;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySignalSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.EntrySignalSensorQuerySpecification;
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
 * pattern entrySignalSensor(Sig, R2, Sen2) =
 * {
 * 	find entrySignal(R2, Sig);
 * 	find rDefinition(R2, Sen2);
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
   * @return the singleton instance of the query specification of this pattern
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<EntrySignalSensorMatcher> querySpecification() throws IncQueryException {
    return EntrySignalSensorQuerySpecification.instance();
  }
  
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
  
  private final static int POSITION_SIG = 0;
  
  private final static int POSITION_R2 = 1;
  
  private final static int POSITION_SEN2 = 2;
  
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
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @param pR2 the fixed value of pattern parameter R2, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @return matches represented as a EntrySignalSensorMatch object.
   * 
   */
  public Collection<EntrySignalSensorMatch> getAllMatches(final Signal pSig, final Route pR2, final Sensor pSen2) {
    return rawGetAllMatches(new Object[]{pSig, pR2, pSen2});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @param pR2 the fixed value of pattern parameter R2, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @return a match represented as a EntrySignalSensorMatch object, or null if no match is found.
   * 
   */
  public EntrySignalSensorMatch getOneArbitraryMatch(final Signal pSig, final Route pR2, final Sensor pSen2) {
    return rawGetOneArbitraryMatch(new Object[]{pSig, pR2, pSen2});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @param pR2 the fixed value of pattern parameter R2, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Signal pSig, final Route pR2, final Sensor pSen2) {
    return rawHasMatch(new Object[]{pSig, pR2, pSen2});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @param pR2 the fixed value of pattern parameter R2, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Signal pSig, final Route pR2, final Sensor pSen2) {
    return rawCountMatches(new Object[]{pSig, pR2, pSen2});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @param pR2 the fixed value of pattern parameter R2, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Signal pSig, final Route pR2, final Sensor pSen2, final IMatchProcessor<? super EntrySignalSensorMatch> processor) {
    rawForEachMatch(new Object[]{pSig, pR2, pSen2}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @param pR2 the fixed value of pattern parameter R2, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Signal pSig, final Route pR2, final Sensor pSen2, final IMatchProcessor<? super EntrySignalSensorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSig, pR2, pSen2}, processor);
  }
  
  /**
   * Registers a new filtered delta monitor on this pattern matcher.
   * The DeltaMonitor can be used to track changes (delta) in the set of filtered pattern matches from now on, considering those matches only that conform to the given fixed values of some parameters.
   * It can also be reset to track changes from a later point in time,
   * and changes can even be acknowledged on an individual basis.
   * See {@link DeltaMonitor} for details.
   * @param fillAtStart if true, all current matches are reported as new match events; if false, the delta monitor starts empty.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @param pR2 the fixed value of pattern parameter R2, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @return the delta monitor.
   * @deprecated use the IncQuery Databinding API (IncQueryObservables) instead.
   * 
   */
  @Deprecated
  public DeltaMonitor<EntrySignalSensorMatch> newFilteredDeltaMonitor(final boolean fillAtStart, final Signal pSig, final Route pR2, final Sensor pSen2) {
    return rawNewFilteredDeltaMonitor(fillAtStart, new Object[]{pSig, pR2, pSen2});
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @param pR2 the fixed value of pattern parameter R2, or null if not bound.
   * @param pSen2 the fixed value of pattern parameter Sen2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public EntrySignalSensorMatch newMatch(final Signal pSig, final Route pR2, final Sensor pSen2) {
    return EntrySignalSensorMatch.newMatch(pSig, pR2, pSen2);
    
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sig.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Signal> rawAccumulateAllValuesOfSig(final Object[] parameters) {
    Set<Signal> results = new HashSet<Signal>();
    rawAccumulateAllValues(POSITION_SIG, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sig.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Signal> getAllValuesOfSig() {
    return rawAccumulateAllValuesOfSig(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sig.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Signal> getAllValuesOfSig(final EntrySignalSensorMatch partialMatch) {
    return rawAccumulateAllValuesOfSig(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sig.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Signal> getAllValuesOfSig(final Route pR2, final Sensor pSen2) {
    return rawAccumulateAllValuesOfSig(new Object[]{null, pR2, pSen2});
  }
  
  /**
   * Retrieve the set of values that occur in matches for R2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Route> rawAccumulateAllValuesOfR2(final Object[] parameters) {
    Set<Route> results = new HashSet<Route>();
    rawAccumulateAllValues(POSITION_R2, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for R2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfR2() {
    return rawAccumulateAllValuesOfR2(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for R2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfR2(final EntrySignalSensorMatch partialMatch) {
    return rawAccumulateAllValuesOfR2(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for R2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfR2(final Signal pSig, final Sensor pSen2) {
    return rawAccumulateAllValuesOfR2(new Object[]{pSig, null, pSen2});
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
  public Set<Sensor> getAllValuesOfSen2(final EntrySignalSensorMatch partialMatch) {
    return rawAccumulateAllValuesOfSen2(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sen2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfSen2(final Signal pSig, final Route pR2) {
    return rawAccumulateAllValuesOfSen2(new Object[]{pSig, pR2, null});
  }
  
  @Override
  protected EntrySignalSensorMatch tupleToMatch(final Tuple t) {
    try {
      return EntrySignalSensorMatch.newMatch((Concept.Signal) t.get(POSITION_SIG), (Concept.Route) t.get(POSITION_R2), (Concept.Sensor) t.get(POSITION_SEN2));
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in tuple not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected EntrySignalSensorMatch arrayToMatch(final Object[] match) {
    try {
      return EntrySignalSensorMatch.newMatch((Concept.Signal) match[POSITION_SIG], (Concept.Route) match[POSITION_R2], (Concept.Sensor) match[POSITION_SEN2]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected EntrySignalSensorMatch arrayToMatchMutable(final Object[] match) {
    try {
      return EntrySignalSensorMatch.newMutableMatch((Concept.Signal) match[POSITION_SIG], (Concept.Route) match[POSITION_R2], (Concept.Sensor) match[POSITION_SEN2]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
}
