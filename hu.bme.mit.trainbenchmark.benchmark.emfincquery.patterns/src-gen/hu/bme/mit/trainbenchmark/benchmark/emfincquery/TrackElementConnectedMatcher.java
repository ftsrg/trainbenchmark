package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.TrackElementConnectedMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.TrackElementConnectedQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.TrackElement;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.trackElementConnected pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link TrackElementConnectedMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern trackElementConnected(te1, te2)
 * {
 * 	TrackElement(te1);
 * 	TrackElement(te2);
 * 	TrackElement.TrackElement_connectsTo(te1, te2);
 * }
 * </pre></code>
 * 
 * @see TrackElementConnectedMatch
 * @see TrackElementConnectedProcessor
 * @see TrackElementConnectedQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class TrackElementConnectedMatcher extends BaseMatcher<TrackElementConnectedMatch> {
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static TrackElementConnectedMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    TrackElementConnectedMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new TrackElementConnectedMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_TE1 = 0;
  
  private final static int POSITION_TE2 = 1;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(TrackElementConnectedMatcher.class);
  
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
  public TrackElementConnectedMatcher(final Notifier emfRoot) throws IncQueryException {
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
  public TrackElementConnectedMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @return matches represented as a TrackElementConnectedMatch object.
   * 
   */
  public Collection<TrackElementConnectedMatch> getAllMatches(final TrackElement pTe1, final TrackElement pTe2) {
    return rawGetAllMatches(new Object[]{pTe1, pTe2});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @return a match represented as a TrackElementConnectedMatch object, or null if no match is found.
   * 
   */
  public TrackElementConnectedMatch getOneArbitraryMatch(final TrackElement pTe1, final TrackElement pTe2) {
    return rawGetOneArbitraryMatch(new Object[]{pTe1, pTe2});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final TrackElement pTe1, final TrackElement pTe2) {
    return rawHasMatch(new Object[]{pTe1, pTe2});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final TrackElement pTe1, final TrackElement pTe2) {
    return rawCountMatches(new Object[]{pTe1, pTe2});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final TrackElement pTe1, final TrackElement pTe2, final IMatchProcessor<? super TrackElementConnectedMatch> processor) {
    rawForEachMatch(new Object[]{pTe1, pTe2}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final TrackElement pTe1, final TrackElement pTe2, final IMatchProcessor<? super TrackElementConnectedMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pTe1, pTe2}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pTe1 the fixed value of pattern parameter te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter te2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public TrackElementConnectedMatch newMatch(final TrackElement pTe1, final TrackElement pTe2) {
    return TrackElementConnectedMatch.newMatch(pTe1, pTe2);
  }
  
  /**
   * Retrieve the set of values that occur in matches for te1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<TrackElement> rawAccumulateAllValuesOfte1(final Object[] parameters) {
    Set<TrackElement> results = new HashSet<TrackElement>();
    rawAccumulateAllValues(POSITION_TE1, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for te1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<TrackElement> getAllValuesOfte1() {
    return rawAccumulateAllValuesOfte1(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for te1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<TrackElement> getAllValuesOfte1(final TrackElementConnectedMatch partialMatch) {
    return rawAccumulateAllValuesOfte1(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for te1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<TrackElement> getAllValuesOfte1(final TrackElement pTe2) {
    return rawAccumulateAllValuesOfte1(new Object[]{
    null, 
    pTe2
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for te2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<TrackElement> rawAccumulateAllValuesOfte2(final Object[] parameters) {
    Set<TrackElement> results = new HashSet<TrackElement>();
    rawAccumulateAllValues(POSITION_TE2, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for te2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<TrackElement> getAllValuesOfte2() {
    return rawAccumulateAllValuesOfte2(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for te2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<TrackElement> getAllValuesOfte2(final TrackElementConnectedMatch partialMatch) {
    return rawAccumulateAllValuesOfte2(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for te2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<TrackElement> getAllValuesOfte2(final TrackElement pTe1) {
    return rawAccumulateAllValuesOfte2(new Object[]{
    pTe1, 
    null
    });
  }
  
  @Override
  protected TrackElementConnectedMatch tupleToMatch(final Tuple t) {
    try {
    	return TrackElementConnectedMatch.newMatch((hu.bme.mit.trainbenchmark.railway.TrackElement) t.get(POSITION_TE1), (hu.bme.mit.trainbenchmark.railway.TrackElement) t.get(POSITION_TE2));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected TrackElementConnectedMatch arrayToMatch(final Object[] match) {
    try {
    	return TrackElementConnectedMatch.newMatch((hu.bme.mit.trainbenchmark.railway.TrackElement) match[POSITION_TE1], (hu.bme.mit.trainbenchmark.railway.TrackElement) match[POSITION_TE2]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected TrackElementConnectedMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return TrackElementConnectedMatch.newMutableMatch((hu.bme.mit.trainbenchmark.railway.TrackElement) match[POSITION_TE1], (hu.bme.mit.trainbenchmark.railway.TrackElement) match[POSITION_TE2]);
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
  public static IQuerySpecification<TrackElementConnectedMatcher> querySpecification() throws IncQueryException {
    return TrackElementConnectedQuerySpecification.instance();
  }
}
