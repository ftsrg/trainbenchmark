package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.MisalignedSwitchMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.MisalignedSwitchQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Switch;
import hu.bme.mit.trainbenchmark.railway.SwitchPosition;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.viatra.misalignedSwitch pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link MisalignedSwitchMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern misalignedSwitch(route, swP, sw) {
 * 	Route.follows(route, swP);
 * 	SwitchPosition.target(swP, sw);
 * 
 * 	SwitchPosition.position(swP, position);
 * 	Switch.currentPosition(sw, currentPosition);
 * 
 * 	position != currentPosition;
 * }
 * </pre></code>
 * 
 * @see MisalignedSwitchMatch
 * @see MisalignedSwitchProcessor
 * @see MisalignedSwitchQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class MisalignedSwitchMatcher extends BaseMatcher<MisalignedSwitchMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static MisalignedSwitchMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    MisalignedSwitchMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new MisalignedSwitchMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_ROUTE = 0;
  
  private final static int POSITION_SWP = 1;
  
  private final static int POSITION_SW = 2;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(MisalignedSwitchMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private MisalignedSwitchMatcher(final ViatraQueryEngine engine) throws ViatraQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSwP the fixed value of pattern parameter swP, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return matches represented as a MisalignedSwitchMatch object.
   * 
   */
  public Collection<MisalignedSwitchMatch> getAllMatches(final Route pRoute, final SwitchPosition pSwP, final Switch pSw) {
    return rawGetAllMatches(new Object[]{pRoute, pSwP, pSw});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSwP the fixed value of pattern parameter swP, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return a match represented as a MisalignedSwitchMatch object, or null if no match is found.
   * 
   */
  public MisalignedSwitchMatch getOneArbitraryMatch(final Route pRoute, final SwitchPosition pSwP, final Switch pSw) {
    return rawGetOneArbitraryMatch(new Object[]{pRoute, pSwP, pSw});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSwP the fixed value of pattern parameter swP, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Route pRoute, final SwitchPosition pSwP, final Switch pSw) {
    return rawHasMatch(new Object[]{pRoute, pSwP, pSw});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSwP the fixed value of pattern parameter swP, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Route pRoute, final SwitchPosition pSwP, final Switch pSw) {
    return rawCountMatches(new Object[]{pRoute, pSwP, pSw});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSwP the fixed value of pattern parameter swP, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Route pRoute, final SwitchPosition pSwP, final Switch pSw, final IMatchProcessor<? super MisalignedSwitchMatch> processor) {
    rawForEachMatch(new Object[]{pRoute, pSwP, pSw}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSwP the fixed value of pattern parameter swP, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Route pRoute, final SwitchPosition pSwP, final Switch pSw, final IMatchProcessor<? super MisalignedSwitchMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pRoute, pSwP, pSw}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pRoute the fixed value of pattern parameter route, or null if not bound.
   * @param pSwP the fixed value of pattern parameter swP, or null if not bound.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public MisalignedSwitchMatch newMatch(final Route pRoute, final SwitchPosition pSwP, final Switch pSw) {
    return MisalignedSwitchMatch.newMatch(pRoute, pSwP, pSw);
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
  public Set<Route> getAllValuesOfroute(final MisalignedSwitchMatch partialMatch) {
    return rawAccumulateAllValuesOfroute(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for route.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfroute(final SwitchPosition pSwP, final Switch pSw) {
    return rawAccumulateAllValuesOfroute(new Object[]{
    null, 
    pSwP, 
    pSw
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for swP.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<SwitchPosition> rawAccumulateAllValuesOfswP(final Object[] parameters) {
    Set<SwitchPosition> results = new HashSet<SwitchPosition>();
    rawAccumulateAllValues(POSITION_SWP, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for swP.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SwitchPosition> getAllValuesOfswP() {
    return rawAccumulateAllValuesOfswP(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for swP.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SwitchPosition> getAllValuesOfswP(final MisalignedSwitchMatch partialMatch) {
    return rawAccumulateAllValuesOfswP(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for swP.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<SwitchPosition> getAllValuesOfswP(final Route pRoute, final Switch pSw) {
    return rawAccumulateAllValuesOfswP(new Object[]{
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
  public Set<Switch> getAllValuesOfsw(final MisalignedSwitchMatch partialMatch) {
    return rawAccumulateAllValuesOfsw(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sw.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Switch> getAllValuesOfsw(final Route pRoute, final SwitchPosition pSwP) {
    return rawAccumulateAllValuesOfsw(new Object[]{
    pRoute, 
    pSwP, 
    null
    });
  }
  
  @Override
  protected MisalignedSwitchMatch tupleToMatch(final Tuple t) {
    try {
    	return MisalignedSwitchMatch.newMatch((Route) t.get(POSITION_ROUTE), (SwitchPosition) t.get(POSITION_SWP), (Switch) t.get(POSITION_SW));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected MisalignedSwitchMatch arrayToMatch(final Object[] match) {
    try {
    	return MisalignedSwitchMatch.newMatch((Route) match[POSITION_ROUTE], (SwitchPosition) match[POSITION_SWP], (Switch) match[POSITION_SW]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected MisalignedSwitchMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return MisalignedSwitchMatch.newMutableMatch((Route) match[POSITION_ROUTE], (SwitchPosition) match[POSITION_SWP], (Switch) match[POSITION_SW]);
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
  public static IQuerySpecification<MisalignedSwitchMatcher> querySpecification() throws ViatraQueryException {
    return MisalignedSwitchQuerySpecification.instance();
  }
}
