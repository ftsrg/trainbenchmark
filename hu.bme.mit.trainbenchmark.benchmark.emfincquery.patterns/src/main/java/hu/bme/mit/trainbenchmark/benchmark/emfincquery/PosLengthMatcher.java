package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Segment;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.PosLengthQuerySpecification;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.posLength pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link PosLengthMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern posLength(Source, Target) =
 * {
 * 	Segment(Source);
 * 	Segment.Segment_length(Source, Target);
 * 	check(Target {@literal <}= 0);
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
   * @return the singleton instance of the query specification of this pattern
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<PosLengthMatcher> querySpecification() throws IncQueryException {
    return PosLengthQuerySpecification.instance();
  }
  
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
  
  private final static int POSITION_SOURCE = 0;
  
  private final static int POSITION_TARGET = 1;
  
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
   * @param pSource the fixed value of pattern parameter Source, or null if not bound.
   * @param pTarget the fixed value of pattern parameter Target, or null if not bound.
   * @return matches represented as a PosLengthMatch object.
   * 
   */
  public Collection<PosLengthMatch> getAllMatches(final Segment pSource, final Integer pTarget) {
    return rawGetAllMatches(new Object[]{pSource, pTarget});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSource the fixed value of pattern parameter Source, or null if not bound.
   * @param pTarget the fixed value of pattern parameter Target, or null if not bound.
   * @return a match represented as a PosLengthMatch object, or null if no match is found.
   * 
   */
  public PosLengthMatch getOneArbitraryMatch(final Segment pSource, final Integer pTarget) {
    return rawGetOneArbitraryMatch(new Object[]{pSource, pTarget});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSource the fixed value of pattern parameter Source, or null if not bound.
   * @param pTarget the fixed value of pattern parameter Target, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Segment pSource, final Integer pTarget) {
    return rawHasMatch(new Object[]{pSource, pTarget});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSource the fixed value of pattern parameter Source, or null if not bound.
   * @param pTarget the fixed value of pattern parameter Target, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Segment pSource, final Integer pTarget) {
    return rawCountMatches(new Object[]{pSource, pTarget});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSource the fixed value of pattern parameter Source, or null if not bound.
   * @param pTarget the fixed value of pattern parameter Target, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Segment pSource, final Integer pTarget, final IMatchProcessor<? super PosLengthMatch> processor) {
    rawForEachMatch(new Object[]{pSource, pTarget}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSource the fixed value of pattern parameter Source, or null if not bound.
   * @param pTarget the fixed value of pattern parameter Target, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Segment pSource, final Integer pTarget, final IMatchProcessor<? super PosLengthMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSource, pTarget}, processor);
  }
  
  /**
   * Registers a new filtered delta monitor on this pattern matcher.
   * The DeltaMonitor can be used to track changes (delta) in the set of filtered pattern matches from now on, considering those matches only that conform to the given fixed values of some parameters.
   * It can also be reset to track changes from a later point in time,
   * and changes can even be acknowledged on an individual basis.
   * See {@link DeltaMonitor} for details.
   * @param fillAtStart if true, all current matches are reported as new match events; if false, the delta monitor starts empty.
   * @param pSource the fixed value of pattern parameter Source, or null if not bound.
   * @param pTarget the fixed value of pattern parameter Target, or null if not bound.
   * @return the delta monitor.
   * @deprecated use the IncQuery Databinding API (IncQueryObservables) instead.
   * 
   */
  @Deprecated
  public DeltaMonitor<PosLengthMatch> newFilteredDeltaMonitor(final boolean fillAtStart, final Segment pSource, final Integer pTarget) {
    return rawNewFilteredDeltaMonitor(fillAtStart, new Object[]{pSource, pTarget});
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSource the fixed value of pattern parameter Source, or null if not bound.
   * @param pTarget the fixed value of pattern parameter Target, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public PosLengthMatch newMatch(final Segment pSource, final Integer pTarget) {
    return PosLengthMatch.newMatch(pSource, pTarget);
    
  }
  
  /**
   * Retrieve the set of values that occur in matches for Source.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Segment> rawAccumulateAllValuesOfSource(final Object[] parameters) {
    Set<Segment> results = new HashSet<Segment>();
    rawAccumulateAllValues(POSITION_SOURCE, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for Source.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfSource() {
    return rawAccumulateAllValuesOfSource(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Source.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfSource(final PosLengthMatch partialMatch) {
    return rawAccumulateAllValuesOfSource(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Source.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfSource(final Integer pTarget) {
    return rawAccumulateAllValuesOfSource(new Object[]{null, pTarget});
  }
  
  /**
   * Retrieve the set of values that occur in matches for Target.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Integer> rawAccumulateAllValuesOfTarget(final Object[] parameters) {
    Set<Integer> results = new HashSet<Integer>();
    rawAccumulateAllValues(POSITION_TARGET, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for Target.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Integer> getAllValuesOfTarget() {
    return rawAccumulateAllValuesOfTarget(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Target.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Integer> getAllValuesOfTarget(final PosLengthMatch partialMatch) {
    return rawAccumulateAllValuesOfTarget(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Target.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Integer> getAllValuesOfTarget(final Segment pSource) {
    return rawAccumulateAllValuesOfTarget(new Object[]{pSource, null});
  }
  
  @Override
  protected PosLengthMatch tupleToMatch(final Tuple t) {
    try {
      return PosLengthMatch.newMatch((Concept.Segment) t.get(POSITION_SOURCE), (java.lang.Integer) t.get(POSITION_TARGET));
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in tuple not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected PosLengthMatch arrayToMatch(final Object[] match) {
    try {
      return PosLengthMatch.newMatch((Concept.Segment) match[POSITION_SOURCE], (java.lang.Integer) match[POSITION_TARGET]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected PosLengthMatch arrayToMatchMutable(final Object[] match) {
    try {
      return PosLengthMatch.newMutableMatch((Concept.Segment) match[POSITION_SOURCE], (java.lang.Integer) match[POSITION_TARGET]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
}
