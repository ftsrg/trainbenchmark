package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Trackelement;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.TrackelementConnectedMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.TrackelementConnectedQuerySpecification;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.trackelementConnected pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link TrackelementConnectedMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern trackelementConnected(Te1, Te2) =
 * {
 * 	Trackelement(Te1);
 * 	Trackelement(Te2);
 * 	Trackelement.TrackElement_connectsTo(Te1, Te2);
 * }
 * </pre></code>
 * 
 * @see TrackelementConnectedMatch
 * @see TrackelementConnectedProcessor
 * @see TrackelementConnectedQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class TrackelementConnectedMatcher extends BaseMatcher<TrackelementConnectedMatch> {
  /**
   * @return the singleton instance of the query specification of this pattern
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<TrackelementConnectedMatcher> querySpecification() throws IncQueryException {
    return TrackelementConnectedQuerySpecification.instance();
  }
  
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static TrackelementConnectedMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    TrackelementConnectedMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new TrackelementConnectedMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_TE1 = 0;
  
  private final static int POSITION_TE2 = 1;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(TrackelementConnectedMatcher.class);
  
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
  public TrackelementConnectedMatcher(final Notifier emfRoot) throws IncQueryException {
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
  public TrackelementConnectedMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pTe1 the fixed value of pattern parameter Te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter Te2, or null if not bound.
   * @return matches represented as a TrackelementConnectedMatch object.
   * 
   */
  public Collection<TrackelementConnectedMatch> getAllMatches(final Trackelement pTe1, final Trackelement pTe2) {
    return rawGetAllMatches(new Object[]{pTe1, pTe2});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pTe1 the fixed value of pattern parameter Te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter Te2, or null if not bound.
   * @return a match represented as a TrackelementConnectedMatch object, or null if no match is found.
   * 
   */
  public TrackelementConnectedMatch getOneArbitraryMatch(final Trackelement pTe1, final Trackelement pTe2) {
    return rawGetOneArbitraryMatch(new Object[]{pTe1, pTe2});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pTe1 the fixed value of pattern parameter Te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter Te2, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Trackelement pTe1, final Trackelement pTe2) {
    return rawHasMatch(new Object[]{pTe1, pTe2});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pTe1 the fixed value of pattern parameter Te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter Te2, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Trackelement pTe1, final Trackelement pTe2) {
    return rawCountMatches(new Object[]{pTe1, pTe2});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pTe1 the fixed value of pattern parameter Te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter Te2, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Trackelement pTe1, final Trackelement pTe2, final IMatchProcessor<? super TrackelementConnectedMatch> processor) {
    rawForEachMatch(new Object[]{pTe1, pTe2}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pTe1 the fixed value of pattern parameter Te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter Te2, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Trackelement pTe1, final Trackelement pTe2, final IMatchProcessor<? super TrackelementConnectedMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pTe1, pTe2}, processor);
  }
  
  /**
   * Registers a new filtered delta monitor on this pattern matcher.
   * The DeltaMonitor can be used to track changes (delta) in the set of filtered pattern matches from now on, considering those matches only that conform to the given fixed values of some parameters.
   * It can also be reset to track changes from a later point in time,
   * and changes can even be acknowledged on an individual basis.
   * See {@link DeltaMonitor} for details.
   * @param fillAtStart if true, all current matches are reported as new match events; if false, the delta monitor starts empty.
   * @param pTe1 the fixed value of pattern parameter Te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter Te2, or null if not bound.
   * @return the delta monitor.
   * @deprecated use the IncQuery Databinding API (IncQueryObservables) instead.
   * 
   */
  @Deprecated
  public DeltaMonitor<TrackelementConnectedMatch> newFilteredDeltaMonitor(final boolean fillAtStart, final Trackelement pTe1, final Trackelement pTe2) {
    return rawNewFilteredDeltaMonitor(fillAtStart, new Object[]{pTe1, pTe2});
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pTe1 the fixed value of pattern parameter Te1, or null if not bound.
   * @param pTe2 the fixed value of pattern parameter Te2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public TrackelementConnectedMatch newMatch(final Trackelement pTe1, final Trackelement pTe2) {
    return TrackelementConnectedMatch.newMatch(pTe1, pTe2);
    
  }
  
  /**
   * Retrieve the set of values that occur in matches for Te1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Trackelement> rawAccumulateAllValuesOfTe1(final Object[] parameters) {
    Set<Trackelement> results = new HashSet<Trackelement>();
    rawAccumulateAllValues(POSITION_TE1, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for Te1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Trackelement> getAllValuesOfTe1() {
    return rawAccumulateAllValuesOfTe1(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Te1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Trackelement> getAllValuesOfTe1(final TrackelementConnectedMatch partialMatch) {
    return rawAccumulateAllValuesOfTe1(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Te1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Trackelement> getAllValuesOfTe1(final Trackelement pTe2) {
    return rawAccumulateAllValuesOfTe1(new Object[]{null, pTe2});
  }
  
  /**
   * Retrieve the set of values that occur in matches for Te2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Trackelement> rawAccumulateAllValuesOfTe2(final Object[] parameters) {
    Set<Trackelement> results = new HashSet<Trackelement>();
    rawAccumulateAllValues(POSITION_TE2, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for Te2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Trackelement> getAllValuesOfTe2() {
    return rawAccumulateAllValuesOfTe2(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Te2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Trackelement> getAllValuesOfTe2(final TrackelementConnectedMatch partialMatch) {
    return rawAccumulateAllValuesOfTe2(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Te2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Trackelement> getAllValuesOfTe2(final Trackelement pTe1) {
    return rawAccumulateAllValuesOfTe2(new Object[]{pTe1, null});
  }
  
  @Override
  protected TrackelementConnectedMatch tupleToMatch(final Tuple t) {
    try {
      return TrackelementConnectedMatch.newMatch((Concept.Trackelement) t.get(POSITION_TE1), (Concept.Trackelement) t.get(POSITION_TE2));
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in tuple not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected TrackelementConnectedMatch arrayToMatch(final Object[] match) {
    try {
      return TrackelementConnectedMatch.newMatch((Concept.Trackelement) match[POSITION_TE1], (Concept.Trackelement) match[POSITION_TE2]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected TrackelementConnectedMatch arrayToMatchMutable(final Object[] match) {
    try {
      return TrackelementConnectedMatch.newMutableMatch((Concept.Trackelement) match[POSITION_TE1], (Concept.Trackelement) match[POSITION_TE2]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
}
