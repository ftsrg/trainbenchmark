/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SwitchMonitored.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.HasSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.HasSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.TrackElement;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.viatra.hasSensor pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link HasSensorMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern hasSensor(sw)
 * {
 * 	TrackElement.monitoredBy(sw, _);
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
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static HasSensorMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    HasSensorMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (HasSensorMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static HasSensorMatcher create() throws ViatraQueryException {
    return new HasSensorMatcher();
  }
  
  private final static int POSITION_SW = 0;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(HasSensorMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private HasSensorMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return matches represented as a HasSensorMatch object.
   * 
   */
  public Collection<HasSensorMatch> getAllMatches(final TrackElement pSw) {
    return rawGetAllMatches(new Object[]{pSw});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return a match represented as a HasSensorMatch object, or null if no match is found.
   * 
   */
  public HasSensorMatch getOneArbitraryMatch(final TrackElement pSw) {
    return rawGetOneArbitraryMatch(new Object[]{pSw});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final TrackElement pSw) {
    return rawHasMatch(new Object[]{pSw});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final TrackElement pSw) {
    return rawCountMatches(new Object[]{pSw});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final TrackElement pSw, final IMatchProcessor<? super HasSensorMatch> processor) {
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
  public boolean forOneArbitraryMatch(final TrackElement pSw, final IMatchProcessor<? super HasSensorMatch> processor) {
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
  public HasSensorMatch newMatch(final TrackElement pSw) {
    return HasSensorMatch.newMatch(pSw);
  }
  
  /**
   * Retrieve the set of values that occur in matches for sw.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<TrackElement> rawAccumulateAllValuesOfsw(final Object[] parameters) {
    Set<TrackElement> results = new HashSet<TrackElement>();
    rawAccumulateAllValues(POSITION_SW, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for sw.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<TrackElement> getAllValuesOfsw() {
    return rawAccumulateAllValuesOfsw(emptyArray());
  }
  
  @Override
  protected HasSensorMatch tupleToMatch(final Tuple t) {
    try {
        return HasSensorMatch.newMatch((TrackElement) t.get(POSITION_SW));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected HasSensorMatch arrayToMatch(final Object[] match) {
    try {
        return HasSensorMatch.newMatch((TrackElement) match[POSITION_SW]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected HasSensorMatch arrayToMatchMutable(final Object[] match) {
    try {
        return HasSensorMatch.newMutableMatch((TrackElement) match[POSITION_SW]);
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
  public static IQuerySpecification<HasSensorMatcher> querySpecification() throws ViatraQueryException {
    return HasSensorQuerySpecification.instance();
  }
}
