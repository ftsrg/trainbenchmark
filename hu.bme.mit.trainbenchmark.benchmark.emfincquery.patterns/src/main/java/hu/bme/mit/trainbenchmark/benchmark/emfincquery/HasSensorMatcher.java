package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Trackelement;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.HasSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.HasSensorQuerySpecification;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.hasSensor pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link HasSensorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern hasSensor(Trackelement) =
 * {
 * 	Trackelement(Trackelement);
 * 	Sensor(Target);
 * 	Trackelement.TrackElement_sensor(Trackelement, Target);
 * }
 * </pre></code>
 * 
 * @see HasSensorMatch
 * @see HasSensorProcessor
 * @see HasSensorQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class HasSensorMatcher extends BaseMatcher<HasSensorMatch> {
  /**
   * @return the singleton instance of the query specification of this pattern
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<HasSensorMatcher> querySpecification() throws IncQueryException {
    return HasSensorQuerySpecification.instance();
  }
  
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static HasSensorMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    HasSensorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new HasSensorMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_TRACKELEMENT = 0;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(HasSensorMatcher.class);
  
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
  public HasSensorMatcher(final Notifier emfRoot) throws IncQueryException {
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
  public HasSensorMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pTrackelement the fixed value of pattern parameter Trackelement, or null if not bound.
   * @return matches represented as a HasSensorMatch object.
   * 
   */
  public Collection<HasSensorMatch> getAllMatches(final Trackelement pTrackelement) {
    return rawGetAllMatches(new Object[]{pTrackelement});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pTrackelement the fixed value of pattern parameter Trackelement, or null if not bound.
   * @return a match represented as a HasSensorMatch object, or null if no match is found.
   * 
   */
  public HasSensorMatch getOneArbitraryMatch(final Trackelement pTrackelement) {
    return rawGetOneArbitraryMatch(new Object[]{pTrackelement});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pTrackelement the fixed value of pattern parameter Trackelement, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Trackelement pTrackelement) {
    return rawHasMatch(new Object[]{pTrackelement});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pTrackelement the fixed value of pattern parameter Trackelement, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Trackelement pTrackelement) {
    return rawCountMatches(new Object[]{pTrackelement});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pTrackelement the fixed value of pattern parameter Trackelement, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Trackelement pTrackelement, final IMatchProcessor<? super HasSensorMatch> processor) {
    rawForEachMatch(new Object[]{pTrackelement}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pTrackelement the fixed value of pattern parameter Trackelement, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Trackelement pTrackelement, final IMatchProcessor<? super HasSensorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pTrackelement}, processor);
  }
  
  /**
   * Registers a new filtered delta monitor on this pattern matcher.
   * The DeltaMonitor can be used to track changes (delta) in the set of filtered pattern matches from now on, considering those matches only that conform to the given fixed values of some parameters.
   * It can also be reset to track changes from a later point in time,
   * and changes can even be acknowledged on an individual basis.
   * See {@link DeltaMonitor} for details.
   * @param fillAtStart if true, all current matches are reported as new match events; if false, the delta monitor starts empty.
   * @param pTrackelement the fixed value of pattern parameter Trackelement, or null if not bound.
   * @return the delta monitor.
   * @deprecated use the IncQuery Databinding API (IncQueryObservables) instead.
   * 
   */
  @Deprecated
  public DeltaMonitor<HasSensorMatch> newFilteredDeltaMonitor(final boolean fillAtStart, final Trackelement pTrackelement) {
    return rawNewFilteredDeltaMonitor(fillAtStart, new Object[]{pTrackelement});
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pTrackelement the fixed value of pattern parameter Trackelement, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public HasSensorMatch newMatch(final Trackelement pTrackelement) {
    return HasSensorMatch.newMatch(pTrackelement);
    
  }
  
  /**
   * Retrieve the set of values that occur in matches for Trackelement.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Trackelement> rawAccumulateAllValuesOfTrackelement(final Object[] parameters) {
    Set<Trackelement> results = new HashSet<Trackelement>();
    rawAccumulateAllValues(POSITION_TRACKELEMENT, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for Trackelement.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Trackelement> getAllValuesOfTrackelement() {
    return rawAccumulateAllValuesOfTrackelement(emptyArray());
  }
  
  @Override
  protected HasSensorMatch tupleToMatch(final Tuple t) {
    try {
      return HasSensorMatch.newMatch((Concept.Trackelement) t.get(POSITION_TRACKELEMENT));
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in tuple not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected HasSensorMatch arrayToMatch(final Object[] match) {
    try {
      return HasSensorMatch.newMatch((Concept.Trackelement) match[POSITION_TRACKELEMENT]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected HasSensorMatch arrayToMatchMutable(final Object[] match) {
    try {
      return HasSensorMatch.newMutableMatch((Concept.Trackelement) match[POSITION_TRACKELEMENT]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
}
