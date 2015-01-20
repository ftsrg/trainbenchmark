package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Switch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SwitchSensorQuerySpecification;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.switchSensor pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link SwitchSensorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern switchSensor(Individual) =
 * {
 * 	Switch(Individual);
 * 	neg find hasSensor(Individual);	
 * }
 * </pre></code>
 * 
 * @see SwitchSensorMatch
 * @see SwitchSensorProcessor
 * @see SwitchSensorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class SwitchSensorMatcher extends BaseMatcher<SwitchSensorMatch> {
  /**
   * @return the singleton instance of the query specification of this pattern
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<SwitchSensorMatcher> querySpecification() throws IncQueryException {
    return SwitchSensorQuerySpecification.instance();
  }
  
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static SwitchSensorMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    SwitchSensorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new SwitchSensorMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_INDIVIDUAL = 0;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(SwitchSensorMatcher.class);
  
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
  public SwitchSensorMatcher(final Notifier emfRoot) throws IncQueryException {
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
  public SwitchSensorMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pIndividual the fixed value of pattern parameter Individual, or null if not bound.
   * @return matches represented as a SwitchSensorMatch object.
   * 
   */
  public Collection<SwitchSensorMatch> getAllMatches(final Switch pIndividual) {
    return rawGetAllMatches(new Object[]{pIndividual});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pIndividual the fixed value of pattern parameter Individual, or null if not bound.
   * @return a match represented as a SwitchSensorMatch object, or null if no match is found.
   * 
   */
  public SwitchSensorMatch getOneArbitraryMatch(final Switch pIndividual) {
    return rawGetOneArbitraryMatch(new Object[]{pIndividual});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pIndividual the fixed value of pattern parameter Individual, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Switch pIndividual) {
    return rawHasMatch(new Object[]{pIndividual});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pIndividual the fixed value of pattern parameter Individual, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Switch pIndividual) {
    return rawCountMatches(new Object[]{pIndividual});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pIndividual the fixed value of pattern parameter Individual, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Switch pIndividual, final IMatchProcessor<? super SwitchSensorMatch> processor) {
    rawForEachMatch(new Object[]{pIndividual}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pIndividual the fixed value of pattern parameter Individual, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Switch pIndividual, final IMatchProcessor<? super SwitchSensorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pIndividual}, processor);
  }
  
  /**
   * Registers a new filtered delta monitor on this pattern matcher.
   * The DeltaMonitor can be used to track changes (delta) in the set of filtered pattern matches from now on, considering those matches only that conform to the given fixed values of some parameters.
   * It can also be reset to track changes from a later point in time,
   * and changes can even be acknowledged on an individual basis.
   * See {@link DeltaMonitor} for details.
   * @param fillAtStart if true, all current matches are reported as new match events; if false, the delta monitor starts empty.
   * @param pIndividual the fixed value of pattern parameter Individual, or null if not bound.
   * @return the delta monitor.
   * @deprecated use the IncQuery Databinding API (IncQueryObservables) instead.
   * 
   */
  @Deprecated
  public DeltaMonitor<SwitchSensorMatch> newFilteredDeltaMonitor(final boolean fillAtStart, final Switch pIndividual) {
    return rawNewFilteredDeltaMonitor(fillAtStart, new Object[]{pIndividual});
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pIndividual the fixed value of pattern parameter Individual, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public SwitchSensorMatch newMatch(final Switch pIndividual) {
    return SwitchSensorMatch.newMatch(pIndividual);
    
  }
  
  /**
   * Retrieve the set of values that occur in matches for Individual.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Switch> rawAccumulateAllValuesOfIndividual(final Object[] parameters) {
    Set<Switch> results = new HashSet<Switch>();
    rawAccumulateAllValues(POSITION_INDIVIDUAL, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for Individual.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Switch> getAllValuesOfIndividual() {
    return rawAccumulateAllValuesOfIndividual(emptyArray());
  }
  
  @Override
  protected SwitchSensorMatch tupleToMatch(final Tuple t) {
    try {
      return SwitchSensorMatch.newMatch((Concept.Switch) t.get(POSITION_INDIVIDUAL));
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in tuple not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected SwitchSensorMatch arrayToMatch(final Object[] match) {
    try {
      return SwitchSensorMatch.newMatch((Concept.Switch) match[POSITION_INDIVIDUAL]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected SwitchSensorMatch arrayToMatchMutable(final Object[] match) {
    try {
      return SwitchSensorMatch.newMutableMatch((Concept.Switch) match[POSITION_INDIVIDUAL]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
}
