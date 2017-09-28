/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SwitchSetInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchSetInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.SwitchSetInjectQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Switch;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.viatra.switchSetInject pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link SwitchSetInjectMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern switchSetInject(sw)
 * {
 * 	Switch(sw);
 * }
 * </pre></code>
 * 
 * @see SwitchSetInjectMatch
 * @see SwitchSetInjectProcessor
 * @see SwitchSetInjectQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class SwitchSetInjectMatcher extends BaseMatcher<SwitchSetInjectMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static SwitchSetInjectMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    SwitchSetInjectMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (SwitchSetInjectMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static SwitchSetInjectMatcher create() throws ViatraQueryException {
    return new SwitchSetInjectMatcher();
  }
  
  private final static int POSITION_SW = 0;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(SwitchSetInjectMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private SwitchSetInjectMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return matches represented as a SwitchSetInjectMatch object.
   * 
   */
  public Collection<SwitchSetInjectMatch> getAllMatches(final Switch pSw) {
    return rawGetAllMatches(new Object[]{pSw});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSw the fixed value of pattern parameter sw, or null if not bound.
   * @return a match represented as a SwitchSetInjectMatch object, or null if no match is found.
   * 
   */
  public SwitchSetInjectMatch getOneArbitraryMatch(final Switch pSw) {
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
  public void forEachMatch(final Switch pSw, final IMatchProcessor<? super SwitchSetInjectMatch> processor) {
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
  public boolean forOneArbitraryMatch(final Switch pSw, final IMatchProcessor<? super SwitchSetInjectMatch> processor) {
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
  public SwitchSetInjectMatch newMatch(final Switch pSw) {
    return SwitchSetInjectMatch.newMatch(pSw);
  }
  
  /**
   * Retrieve the set of values that occur in matches for sw.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Switch> rawAccumulateAllValuesOfsw(final Object[] parameters) {
    Set<Switch> results = new HashSet<Switch>();
    rawAccumulateAllValues(POSITION_SW, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for sw.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Switch> getAllValuesOfsw() {
    return rawAccumulateAllValuesOfsw(emptyArray());
  }
  
  @Override
  protected SwitchSetInjectMatch tupleToMatch(final Tuple t) {
    try {
        return SwitchSetInjectMatch.newMatch((Switch) t.get(POSITION_SW));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected SwitchSetInjectMatch arrayToMatch(final Object[] match) {
    try {
        return SwitchSetInjectMatch.newMatch((Switch) match[POSITION_SW]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected SwitchSetInjectMatch arrayToMatchMutable(final Object[] match) {
    try {
        return SwitchSetInjectMatch.newMutableMatch((Switch) match[POSITION_SW]);
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
  public static IQuerySpecification<SwitchSetInjectMatcher> querySpecification() throws ViatraQueryException {
    return SwitchSetInjectQuerySpecification.instance();
  }
}
