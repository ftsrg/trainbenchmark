package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.PosLengthQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Segment;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLength pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link PosLengthMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern PosLength(segment)
 * {
 * 	Segment.length(segment, length);
 * 	check(length {@literal <}= 0);
 * }
 * </pre></code>
 * 
 * @see PosLengthMatch
 * @see PosLengthProcessor
 * @see PosLengthQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class PosLengthMatcher extends BaseMatcher<PosLengthMatch> {
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static PosLengthMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    PosLengthMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new PosLengthMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_SEGMENT = 0;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(PosLengthMatcher.class);
  
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
  public PosLengthMatcher(final Notifier emfRoot) throws IncQueryException {
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
  public PosLengthMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSegment the fixed value of pattern parameter segment, or null if not bound.
   * @return matches represented as a PosLengthMatch object.
   * 
   */
  public Collection<PosLengthMatch> getAllMatches(final Segment pSegment) {
    return rawGetAllMatches(new Object[]{pSegment});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSegment the fixed value of pattern parameter segment, or null if not bound.
   * @return a match represented as a PosLengthMatch object, or null if no match is found.
   * 
   */
  public PosLengthMatch getOneArbitraryMatch(final Segment pSegment) {
    return rawGetOneArbitraryMatch(new Object[]{pSegment});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSegment the fixed value of pattern parameter segment, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Segment pSegment) {
    return rawHasMatch(new Object[]{pSegment});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSegment the fixed value of pattern parameter segment, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Segment pSegment) {
    return rawCountMatches(new Object[]{pSegment});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSegment the fixed value of pattern parameter segment, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Segment pSegment, final IMatchProcessor<? super PosLengthMatch> processor) {
    rawForEachMatch(new Object[]{pSegment}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSegment the fixed value of pattern parameter segment, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Segment pSegment, final IMatchProcessor<? super PosLengthMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSegment}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSegment the fixed value of pattern parameter segment, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public PosLengthMatch newMatch(final Segment pSegment) {
    return PosLengthMatch.newMatch(pSegment);
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Segment> rawAccumulateAllValuesOfsegment(final Object[] parameters) {
    Set<Segment> results = new HashSet<Segment>();
    rawAccumulateAllValues(POSITION_SEGMENT, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment() {
    return rawAccumulateAllValuesOfsegment(emptyArray());
  }
  
  @Override
  protected PosLengthMatch tupleToMatch(final Tuple t) {
    try {
    	return PosLengthMatch.newMatch((Segment) t.get(POSITION_SEGMENT));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected PosLengthMatch arrayToMatch(final Object[] match) {
    try {
    	return PosLengthMatch.newMatch((Segment) match[POSITION_SEGMENT]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected PosLengthMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return PosLengthMatch.newMutableMatch((Segment) match[POSITION_SEGMENT]);
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
  public static IQuerySpecification<PosLengthMatcher> querySpecification() throws IncQueryException {
    return PosLengthQuerySpecification.instance();
  }
}
