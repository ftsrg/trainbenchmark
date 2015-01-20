package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Route;
import Concept.Sensor;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.RDefinitionMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.RDefinitionQuerySpecification;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.rDefinition pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link RDefinitionMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern rDefinition(R, Sen) =
 * {
 * 	Route(R);
 * 	Sensor(Sen);
 * 	Route.Route_routeDefinition(R, Sen);
 * }
 * </pre></code>
 * 
 * @see RDefinitionMatch
 * @see RDefinitionProcessor
 * @see RDefinitionQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class RDefinitionMatcher extends BaseMatcher<RDefinitionMatch> {
  /**
   * @return the singleton instance of the query specification of this pattern
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<RDefinitionMatcher> querySpecification() throws IncQueryException {
    return RDefinitionQuerySpecification.instance();
  }
  
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static RDefinitionMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    RDefinitionMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new RDefinitionMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_R = 0;
  
  private final static int POSITION_SEN = 1;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(RDefinitionMatcher.class);
  
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
  public RDefinitionMatcher(final Notifier emfRoot) throws IncQueryException {
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
  public RDefinitionMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @return matches represented as a RDefinitionMatch object.
   * 
   */
  public Collection<RDefinitionMatch> getAllMatches(final Route pR, final Sensor pSen) {
    return rawGetAllMatches(new Object[]{pR, pSen});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @return a match represented as a RDefinitionMatch object, or null if no match is found.
   * 
   */
  public RDefinitionMatch getOneArbitraryMatch(final Route pR, final Sensor pSen) {
    return rawGetOneArbitraryMatch(new Object[]{pR, pSen});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Route pR, final Sensor pSen) {
    return rawHasMatch(new Object[]{pR, pSen});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Route pR, final Sensor pSen) {
    return rawCountMatches(new Object[]{pR, pSen});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Route pR, final Sensor pSen, final IMatchProcessor<? super RDefinitionMatch> processor) {
    rawForEachMatch(new Object[]{pR, pSen}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Route pR, final Sensor pSen, final IMatchProcessor<? super RDefinitionMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pR, pSen}, processor);
  }
  
  /**
   * Registers a new filtered delta monitor on this pattern matcher.
   * The DeltaMonitor can be used to track changes (delta) in the set of filtered pattern matches from now on, considering those matches only that conform to the given fixed values of some parameters.
   * It can also be reset to track changes from a later point in time,
   * and changes can even be acknowledged on an individual basis.
   * See {@link DeltaMonitor} for details.
   * @param fillAtStart if true, all current matches are reported as new match events; if false, the delta monitor starts empty.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @return the delta monitor.
   * @deprecated use the IncQuery Databinding API (IncQueryObservables) instead.
   * 
   */
  @Deprecated
  public DeltaMonitor<RDefinitionMatch> newFilteredDeltaMonitor(final boolean fillAtStart, final Route pR, final Sensor pSen) {
    return rawNewFilteredDeltaMonitor(fillAtStart, new Object[]{pR, pSen});
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSen the fixed value of pattern parameter Sen, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public RDefinitionMatch newMatch(final Route pR, final Sensor pSen) {
    return RDefinitionMatch.newMatch(pR, pSen);
    
  }
  
  /**
   * Retrieve the set of values that occur in matches for R.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Route> rawAccumulateAllValuesOfR(final Object[] parameters) {
    Set<Route> results = new HashSet<Route>();
    rawAccumulateAllValues(POSITION_R, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for R.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfR() {
    return rawAccumulateAllValuesOfR(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for R.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfR(final RDefinitionMatch partialMatch) {
    return rawAccumulateAllValuesOfR(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for R.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfR(final Sensor pSen) {
    return rawAccumulateAllValuesOfR(new Object[]{null, pSen});
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
  public Set<Sensor> getAllValuesOfSen(final RDefinitionMatch partialMatch) {
    return rawAccumulateAllValuesOfSen(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sen.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfSen(final Route pR) {
    return rawAccumulateAllValuesOfSen(new Object[]{pR, null});
  }
  
  @Override
  protected RDefinitionMatch tupleToMatch(final Tuple t) {
    try {
      return RDefinitionMatch.newMatch((Concept.Route) t.get(POSITION_R), (Concept.Sensor) t.get(POSITION_SEN));
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in tuple not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected RDefinitionMatch arrayToMatch(final Object[] match) {
    try {
      return RDefinitionMatch.newMatch((Concept.Route) match[POSITION_R], (Concept.Sensor) match[POSITION_SEN]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected RDefinitionMatch arrayToMatchMutable(final Object[] match) {
    try {
      return RDefinitionMatch.newMutableMatch((Concept.Route) match[POSITION_R], (Concept.Sensor) match[POSITION_SEN]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
}
