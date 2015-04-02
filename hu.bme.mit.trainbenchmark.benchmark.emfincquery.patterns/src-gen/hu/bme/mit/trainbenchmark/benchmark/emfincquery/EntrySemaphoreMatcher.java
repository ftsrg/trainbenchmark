package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySemaphoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.EntrySemaphoreQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySemaphore pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link EntrySemaphoreMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern entrySemaphore(route, semaphore) {
 * 	Route.entry(route, semaphore);
 * }
 * </pre></code>
 * 
 * @see EntrySemaphoreMatch
 * @see EntrySemaphoreProcessor
 * @see EntrySemaphoreQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class EntrySemaphoreMatcher extends BaseMatcher<EntrySemaphoreMatch> {
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static EntrySemaphoreMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    EntrySemaphoreMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new EntrySemaphoreMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_ROUTE = 0;
  
  private final static int POSITION_SEMAPHORE = 1;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(EntrySemaphoreMatcher.class);
  
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
  public EntrySemaphoreMatcher(final Notifier emfRoot) throws IncQueryException {
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
  public EntrySemaphoreMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @return matches represented as a EntrySemaphoreMatch object.
   * 
   */
  public Collection<EntrySemaphoreMatch> getAllMatches(final Route pRoute, final Semaphore pSemaphore) {
    return rawGetAllMatches(new Object[]{pRoute, pSemaphore});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @return a match represented as a EntrySemaphoreMatch object, or null if no match is found.
   * 
   */
  public EntrySemaphoreMatch getOneArbitraryMatch(final Route pRoute, final Semaphore pSemaphore) {
    return rawGetOneArbitraryMatch(new Object[]{pRoute, pSemaphore});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Route pRoute, final Semaphore pSemaphore) {
    return rawHasMatch(new Object[]{pRoute, pSemaphore});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Route pRoute, final Semaphore pSemaphore) {
    return rawCountMatches(new Object[]{pRoute, pSemaphore});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Route pRoute, final Semaphore pSemaphore, final IMatchProcessor<? super EntrySemaphoreMatch> processor) {
    rawForEachMatch(new Object[]{pRoute, pSemaphore}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Route pRoute, final Semaphore pSemaphore, final IMatchProcessor<? super EntrySemaphoreMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pRoute, pSemaphore}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public EntrySemaphoreMatch newMatch(final Route pRoute, final Semaphore pSemaphore) {
    return EntrySemaphoreMatch.newMatch(pRoute, pSemaphore);
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Route> rawAccumulateAllValuesOfroute(final Object[] parameters) {
    Set<Route> results = new HashSet<Route>();
    rawAccumulateAllValues(POSITION_ROUTE, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute() {
    return rawAccumulateAllValuesOfroute(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute(final EntrySemaphoreMatch partialMatch) {
    return rawAccumulateAllValuesOfroute(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute(final Semaphore pSemaphore) {
    return rawAccumulateAllValuesOfroute(new Object[]{
    null, 
    pSemaphore
    });
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
  public Set<Semaphore> getAllValuesOfsemaphore(final EntrySemaphoreMatch partialMatch) {
    return rawAccumulateAllValuesOfsemaphore(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for semaphore.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Semaphore> getAllValuesOfsemaphore(final Route pRoute) {
    return rawAccumulateAllValuesOfsemaphore(new Object[]{
    pRoute, 
    null
    });
  }
  
  @Override
  protected EntrySemaphoreMatch tupleToMatch(final Tuple t) {
    try {
    	return EntrySemaphoreMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Route) t.get(POSITION_ROUTE), (hu.bme.mit.trainbenchmark.railway.Semaphore) t.get(POSITION_SEMAPHORE));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected EntrySemaphoreMatch arrayToMatch(final Object[] match) {
    try {
    	return EntrySemaphoreMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Route) match[POSITION_ROUTE], (hu.bme.mit.trainbenchmark.railway.Semaphore) match[POSITION_SEMAPHORE]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected EntrySemaphoreMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return EntrySemaphoreMatch.newMutableMatch((hu.bme.mit.trainbenchmark.railway.Route) match[POSITION_ROUTE], (hu.bme.mit.trainbenchmark.railway.Semaphore) match[POSITION_SEMAPHORE]);
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
  public static IQuerySpecification<EntrySemaphoreMatcher> querySpecification() throws IncQueryException {
    return EntrySemaphoreQuerySpecification.instance();
  }
}
