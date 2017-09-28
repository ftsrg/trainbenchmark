/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SemaphoreNeighbor.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.EntrySemaphoreMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.EntrySemaphoreQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;

/**
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.viatra.entrySemaphore pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link EntrySemaphoreMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern entrySemaphore(route, semaphore)
 * {
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
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static EntrySemaphoreMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    EntrySemaphoreMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (EntrySemaphoreMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static EntrySemaphoreMatcher create() throws ViatraQueryException {
    return new EntrySemaphoreMatcher();
  }
  
  private final static int POSITION_ROUTE = 0;
  
  private final static int POSITION_SEMAPHORE = 1;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(EntrySemaphoreMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private EntrySemaphoreMatcher() throws ViatraQueryException {
    super(querySpecification());
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
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Route> rawAccumulateAllValuesOfroute(final Object[] parameters) {
    Set<Route> results = new HashSet<Route>();
    rawAccumulateAllValues(POSITION_ROUTE, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute() {
    return rawAccumulateAllValuesOfroute(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute(final EntrySemaphoreMatch partialMatch) {
    return rawAccumulateAllValuesOfroute(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values or empty set if there are no matches
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
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Semaphore> rawAccumulateAllValuesOfsemaphore(final Object[] parameters) {
    Set<Semaphore> results = new HashSet<Semaphore>();
    rawAccumulateAllValues(POSITION_SEMAPHORE, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for semaphore.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Semaphore> getAllValuesOfsemaphore() {
    return rawAccumulateAllValuesOfsemaphore(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for semaphore.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Semaphore> getAllValuesOfsemaphore(final EntrySemaphoreMatch partialMatch) {
    return rawAccumulateAllValuesOfsemaphore(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for semaphore.
   * @return the Set of all values or empty set if there are no matches
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
        return EntrySemaphoreMatch.newMatch((Route) t.get(POSITION_ROUTE), (Semaphore) t.get(POSITION_SEMAPHORE));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected EntrySemaphoreMatch arrayToMatch(final Object[] match) {
    try {
        return EntrySemaphoreMatch.newMatch((Route) match[POSITION_ROUTE], (Semaphore) match[POSITION_SEMAPHORE]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected EntrySemaphoreMatch arrayToMatchMutable(final Object[] match) {
    try {
        return EntrySemaphoreMatch.newMutableMatch((Route) match[POSITION_ROUTE], (Semaphore) match[POSITION_SEMAPHORE]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  /**
   * @return the singleton instance of the query specification of this pattern
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<EntrySemaphoreMatcher> querySpecification() throws ViatraQueryException {
    return EntrySemaphoreQuerySpecification.instance();
  }
}
