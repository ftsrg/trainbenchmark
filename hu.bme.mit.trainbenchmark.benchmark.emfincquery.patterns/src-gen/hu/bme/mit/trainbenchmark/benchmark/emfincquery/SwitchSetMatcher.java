package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SwitchSetQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.switchSet pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link SwitchSetMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern switchSet(semaphore, route, switchPosition, sw)
 * {
 * 	Route.entry(route, semaphore);
 * 	Route.follows(route, switchPosition);
 * 	SwitchPosition.^switch(switchPosition, sw);
 * 	
 * 	Semaphore.signal(semaphore, ::GO);
 * 	SwitchPosition.position(switchPosition, swPP);
 * 	Switch.currentPosition(sw, swCP);
 * 
 * 	swPP != swCP;
 * }
 * </pre></code>
 * 
 * @see SwitchSetMatch
 * @see SwitchSetProcessor
 * @see SwitchSetQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class SwitchSetMatcher extends BaseMatcher<SwitchSetMatch> {
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static SwitchSetMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    SwitchSetMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new SwitchSetMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_SEMAPHORE = 0;
  
  private final static int POSITION_ROUTE = 1;
  
  private final static int POSITION_SWITCHPOSITION = 2;
  
  private final static int POSITION_SW = 3;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(SwitchSetMatcher.class);
  
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
  public SwitchSetMatcher(final Notifier emfRoot) throws IncQueryException {
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
  public SwitchSetMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSwitchPosition the fixed value of pattern parameter switchPosition, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return matches represented as a SwitchSetMatch object.
   * 
   */
  public Collection<SwitchSetMatch> getAllMatches(final Semaphore pSemaphore, final Route pRoute, final SwitchPosition pSwitchPosition, final Switch pSw) {
    return rawGetAllMatches(new Object[]{pSemaphore, pRoute, pSwitchPosition, pSw});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSwitchPosition the fixed value of pattern parameter switchPosition, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return a match represented as a SwitchSetMatch object, or null if no match is found.
   * 
   */
  public SwitchSetMatch getOneArbitraryMatch(final Semaphore pSemaphore, final Route pRoute, final SwitchPosition pSwitchPosition, final Switch pSw) {
    return rawGetOneArbitraryMatch(new Object[]{pSemaphore, pRoute, pSwitchPosition, pSw});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSwitchPosition the fixed value of pattern parameter switchPosition, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Semaphore pSemaphore, final Route pRoute, final SwitchPosition pSwitchPosition, final Switch pSw) {
    return rawHasMatch(new Object[]{pSemaphore, pRoute, pSwitchPosition, pSw});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSwitchPosition the fixed value of pattern parameter switchPosition, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Semaphore pSemaphore, final Route pRoute, final SwitchPosition pSwitchPosition, final Switch pSw) {
    return rawCountMatches(new Object[]{pSemaphore, pRoute, pSwitchPosition, pSw});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSwitchPosition the fixed value of pattern parameter switchPosition, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Semaphore pSemaphore, final Route pRoute, final SwitchPosition pSwitchPosition, final Switch pSw, final IMatchProcessor<? super SwitchSetMatch> processor) {
    rawForEachMatch(new Object[]{pSemaphore, pRoute, pSwitchPosition, pSw}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSwitchPosition the fixed value of pattern parameter switchPosition, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Semaphore pSemaphore, final Route pRoute, final SwitchPosition pSwitchPosition, final Switch pSw, final IMatchProcessor<? super SwitchSetMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSemaphore, pRoute, pSwitchPosition, pSw}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSwitchPosition the fixed value of pattern parameter switchPosition, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public SwitchSetMatch newMatch(final Semaphore pSemaphore, final Route pRoute, final SwitchPosition pSwitchPosition, final Switch pSw) {
    return SwitchSetMatch.newMatch(pSemaphore, pRoute, pSwitchPosition, pSw);
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
  public Set<Semaphore> getAllValuesOfsemaphore(final SwitchSetMatch partialMatch) {
    return rawAccumulateAllValuesOfsemaphore(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for semaphore.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Semaphore> getAllValuesOfsemaphore(final Route pRoute, final SwitchPosition pSwitchPosition, final Switch pSw) {
    return rawAccumulateAllValuesOfsemaphore(new Object[]{
    null, 
    pRoute, 
    pSwitchPosition, 
    pSw
    });
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
  public Set<Route> getAllValuesOfroute(final SwitchSetMatch partialMatch) {
    return rawAccumulateAllValuesOfroute(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute(final Semaphore pSemaphore, final SwitchPosition pSwitchPosition, final Switch pSw) {
    return rawAccumulateAllValuesOfroute(new Object[]{
    pSemaphore, 
    null, 
    pSwitchPosition, 
    pSw
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for switchPosition.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<SwitchPosition> rawAccumulateAllValuesOfswitchPosition(final Object[] parameters) {
    Set<SwitchPosition> results = new HashSet<SwitchPosition>();
    rawAccumulateAllValues(POSITION_SWITCHPOSITION, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for switchPosition.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SwitchPosition> getAllValuesOfswitchPosition() {
    return rawAccumulateAllValuesOfswitchPosition(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for switchPosition.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SwitchPosition> getAllValuesOfswitchPosition(final SwitchSetMatch partialMatch) {
    return rawAccumulateAllValuesOfswitchPosition(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for switchPosition.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SwitchPosition> getAllValuesOfswitchPosition(final Semaphore pSemaphore, final Route pRoute, final Switch pSw) {
    return rawAccumulateAllValuesOfswitchPosition(new Object[]{
    pSemaphore, 
    pRoute, 
    null, 
    pSw
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for sw.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Switch> rawAccumulateAllValuesOfsw(final Object[] parameters) {
    Set<Switch> results = new HashSet<Switch>();
    rawAccumulateAllValues(POSITION_SW, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for sw.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Switch> getAllValuesOfsw() {
    return rawAccumulateAllValuesOfsw(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sw.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Switch> getAllValuesOfsw(final SwitchSetMatch partialMatch) {
    return rawAccumulateAllValuesOfsw(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sw.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Switch> getAllValuesOfsw(final Semaphore pSemaphore, final Route pRoute, final SwitchPosition pSwitchPosition) {
    return rawAccumulateAllValuesOfsw(new Object[]{
    pSemaphore, 
    pRoute, 
    pSwitchPosition, 
    null
    });
  }
  
  @Override
  protected SwitchSetMatch tupleToMatch(final Tuple t) {
    try {
    	return SwitchSetMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Semaphore) t.get(POSITION_SEMAPHORE), (hu.bme.mit.trainbenchmark.railway.Route) t.get(POSITION_ROUTE), (hu.bme.mit.trainbenchmark.railway.SwitchPosition) t.get(POSITION_SWITCHPOSITION), (hu.bme.mit.trainbenchmark.railway.Switch) t.get(POSITION_SW));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected SwitchSetMatch arrayToMatch(final Object[] match) {
    try {
    	return SwitchSetMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Semaphore) match[POSITION_SEMAPHORE], (hu.bme.mit.trainbenchmark.railway.Route) match[POSITION_ROUTE], (hu.bme.mit.trainbenchmark.railway.SwitchPosition) match[POSITION_SWITCHPOSITION], (hu.bme.mit.trainbenchmark.railway.Switch) match[POSITION_SW]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected SwitchSetMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return SwitchSetMatch.newMutableMatch((hu.bme.mit.trainbenchmark.railway.Semaphore) match[POSITION_SEMAPHORE], (hu.bme.mit.trainbenchmark.railway.Route) match[POSITION_ROUTE], (hu.bme.mit.trainbenchmark.railway.SwitchPosition) match[POSITION_SWITCHPOSITION], (hu.bme.mit.trainbenchmark.railway.Switch) match[POSITION_SW]);
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
  public static IQuerySpecification<SwitchSetMatcher> querySpecification() throws IncQueryException {
    return SwitchSetQuerySpecification.instance();
  }
}
