package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import Concept.Route;
import Concept.Signal;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ExitSignalMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ExitSignalQuerySpecification;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.exitSignal pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link ExitSignalMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern exitSignal(R, Sig) =
 * {
 * 	Route(R);
 * 	Signal(Sig);
 * 	Route.Route_exit(R, Sig);
 * }
 * </pre></code>
 * 
 * @see ExitSignalMatch
 * @see ExitSignalProcessor
 * @see ExitSignalQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class ExitSignalMatcher extends BaseMatcher<ExitSignalMatch> {
  /**
   * @return the singleton instance of the query specification of this pattern
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static IQuerySpecification<ExitSignalMatcher> querySpecification() throws IncQueryException {
    return ExitSignalQuerySpecification.instance();
  }
  
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static ExitSignalMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    ExitSignalMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new ExitSignalMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_R = 0;
  
  private final static int POSITION_SIG = 1;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(ExitSignalMatcher.class);
  
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
  public ExitSignalMatcher(final Notifier emfRoot) throws IncQueryException {
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
  public ExitSignalMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @return matches represented as a ExitSignalMatch object.
   * 
   */
  public Collection<ExitSignalMatch> getAllMatches(final Route pR, final Signal pSig) {
    return rawGetAllMatches(new Object[]{pR, pSig});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @return a match represented as a ExitSignalMatch object, or null if no match is found.
   * 
   */
  public ExitSignalMatch getOneArbitraryMatch(final Route pR, final Signal pSig) {
    return rawGetOneArbitraryMatch(new Object[]{pR, pSig});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Route pR, final Signal pSig) {
    return rawHasMatch(new Object[]{pR, pSig});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Route pR, final Signal pSig) {
    return rawCountMatches(new Object[]{pR, pSig});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Route pR, final Signal pSig, final IMatchProcessor<? super ExitSignalMatch> processor) {
    rawForEachMatch(new Object[]{pR, pSig}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Route pR, final Signal pSig, final IMatchProcessor<? super ExitSignalMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pR, pSig}, processor);
  }
  
  /**
   * Registers a new filtered delta monitor on this pattern matcher.
   * The DeltaMonitor can be used to track changes (delta) in the set of filtered pattern matches from now on, considering those matches only that conform to the given fixed values of some parameters.
   * It can also be reset to track changes from a later point in time,
   * and changes can even be acknowledged on an individual basis.
   * See {@link DeltaMonitor} for details.
   * @param fillAtStart if true, all current matches are reported as new match events; if false, the delta monitor starts empty.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @return the delta monitor.
   * @deprecated use the IncQuery Databinding API (IncQueryObservables) instead.
   * 
   */
  @Deprecated
  public DeltaMonitor<ExitSignalMatch> newFilteredDeltaMonitor(final boolean fillAtStart, final Route pR, final Signal pSig) {
    return rawNewFilteredDeltaMonitor(fillAtStart, new Object[]{pR, pSig});
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pR the fixed value of pattern parameter R, or null if not bound.
   * @param pSig the fixed value of pattern parameter Sig, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public ExitSignalMatch newMatch(final Route pR, final Signal pSig) {
    return ExitSignalMatch.newMatch(pR, pSig);
    
  }
  
  /**
   * Retrieve the set of values that occur in matches for R.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Route> rawAccumulateAllValuesOfR(final Object[] parameters) {
    Set<Route> results = new HashSet<Route>();
    rawAccumulateAllValues(POSITION_R, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for R.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfR() {
    return rawAccumulateAllValuesOfR(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for R.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfR(final ExitSignalMatch partialMatch) {
    return rawAccumulateAllValuesOfR(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for R.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Route> getAllValuesOfR(final Signal pSig) {
    return rawAccumulateAllValuesOfR(new Object[]{null, pSig});
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sig.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Signal> rawAccumulateAllValuesOfSig(final Object[] parameters) {
    Set<Signal> results = new HashSet<Signal>();
    rawAccumulateAllValues(POSITION_SIG, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sig.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Signal> getAllValuesOfSig() {
    return rawAccumulateAllValuesOfSig(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sig.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Signal> getAllValuesOfSig(final ExitSignalMatch partialMatch) {
    return rawAccumulateAllValuesOfSig(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for Sig.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Signal> getAllValuesOfSig(final Route pR) {
    return rawAccumulateAllValuesOfSig(new Object[]{pR, null});
  }
  
  @Override
  protected ExitSignalMatch tupleToMatch(final Tuple t) {
    try {
      return ExitSignalMatch.newMatch((Concept.Route) t.get(POSITION_R), (Concept.Signal) t.get(POSITION_SIG));
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in tuple not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected ExitSignalMatch arrayToMatch(final Object[] match) {
    try {
      return ExitSignalMatch.newMatch((Concept.Route) match[POSITION_R], (Concept.Signal) match[POSITION_SIG]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
  
  @Override
  protected ExitSignalMatch arrayToMatchMutable(final Object[] match) {
    try {
      return ExitSignalMatch.newMutableMatch((Concept.Route) match[POSITION_R], (Concept.Signal) match[POSITION_SIG]);
    } catch(ClassCastException e) {
      LOGGER.error("Element(s) in array not properly typed!",e);
      return null;
    }
    
  }
}
