package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SwitchSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Switch;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensor pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link SwitchSensorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern SwitchSensor(sw)
 * {
 * 	Switch(sw);
 * 	neg find hasSensor(sw);	
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
  
  private final static int POSITION_SW = 0;
  
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
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return matches represented as a SwitchSensorMatch object.
   * 
   */
  public Collection<SwitchSensorMatch> getAllMatches(final Switch pSw) {
    return rawGetAllMatches(new Object[]{pSw});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return a match represented as a SwitchSensorMatch object, or null if no match is found.
   * 
   */
  public SwitchSensorMatch getOneArbitraryMatch(final Switch pSw) {
    return rawGetOneArbitraryMatch(new Object[]{pSw});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Switch pSw) {
    return rawHasMatch(new Object[]{pSw});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Switch pSw) {
    return rawCountMatches(new Object[]{pSw});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Switch pSw, final IMatchProcessor<? super SwitchSensorMatch> processor) {
    rawForEachMatch(new Object[]{pSw}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Switch pSw, final IMatchProcessor<? super SwitchSensorMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSw}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public SwitchSensorMatch newMatch(final Switch pSw) {
    return SwitchSensorMatch.newMatch(pSw);
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
  
  @Override
  protected SwitchSensorMatch tupleToMatch(final Tuple t) {
    try {
    	return SwitchSensorMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Switch) t.get(POSITION_SW));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected SwitchSensorMatch arrayToMatch(final Object[] match) {
    try {
    	return SwitchSensorMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Switch) match[POSITION_SW]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected SwitchSensorMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return SwitchSensorMatch.newMutableMatch((hu.bme.mit.trainbenchmark.railway.Switch) match[POSITION_SW]);
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
  public static IQuerySpecification<SwitchSensorMatcher> querySpecification() throws IncQueryException {
    return SwitchSensorQuerySpecification.instance();
  }
}
