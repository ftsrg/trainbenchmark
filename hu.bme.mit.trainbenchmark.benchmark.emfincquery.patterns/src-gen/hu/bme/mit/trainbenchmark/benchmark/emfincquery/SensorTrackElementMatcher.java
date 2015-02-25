package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SensorTrackElementMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SensorTrackElementQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Sensor;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.sensorTrackElement pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link SensorTrackElementMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern sensorTrackElement(sen, te)
 * {
 * 	Sensor(sen);
 * 	TrackElement(te);
 * 	Sensor.Sensor_trackElement(sen, te);
 * }
 * </pre></code>
 * 
 * @see SensorTrackElementMatch
 * @see SensorTrackElementProcessor
 * @see SensorTrackElementQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class SensorTrackElementMatcher extends BaseMatcher<SensorTrackElementMatch> {
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static SensorTrackElementMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    SensorTrackElementMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new SensorTrackElementMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_SEN = 0;
  
  private final static int POSITION_TE = 1;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(SensorTrackElementMatcher.class);
  
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
  public SensorTrackElementMatcher(final Notifier emfRoot) throws IncQueryException {
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
  public SensorTrackElementMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSen the fixed value of pattern parameter sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter te, or null if not bound.
   * @return matches represented as a SensorTrackElementMatch object.
   * 
   */
  public Collection<SensorTrackElementMatch> getAllMatches(final Sensor pSen, final TrackElement pTe) {
    return rawGetAllMatches(new Object[]{pSen, pTe});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSen the fixed value of pattern parameter sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter te, or null if not bound.
   * @return a match represented as a SensorTrackElementMatch object, or null if no match is found.
   * 
   */
  public SensorTrackElementMatch getOneArbitraryMatch(final Sensor pSen, final TrackElement pTe) {
    return rawGetOneArbitraryMatch(new Object[]{pSen, pTe});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSen the fixed value of pattern parameter sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter te, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Sensor pSen, final TrackElement pTe) {
    return rawHasMatch(new Object[]{pSen, pTe});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSen the fixed value of pattern parameter sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter te, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Sensor pSen, final TrackElement pTe) {
    return rawCountMatches(new Object[]{pSen, pTe});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSen the fixed value of pattern parameter sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter te, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Sensor pSen, final TrackElement pTe, final IMatchProcessor<? super SensorTrackElementMatch> processor) {
    rawForEachMatch(new Object[]{pSen, pTe}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSen the fixed value of pattern parameter sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter te, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Sensor pSen, final TrackElement pTe, final IMatchProcessor<? super SensorTrackElementMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSen, pTe}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSen the fixed value of pattern parameter sen, or null if not bound.
   * @param pTe the fixed value of pattern parameter te, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public SensorTrackElementMatch newMatch(final Sensor pSen, final TrackElement pTe) {
    return SensorTrackElementMatch.newMatch(pSen, pTe);
  }
  
  /**
   * Retrieve the set of values that occur in matches for sen.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Sensor> rawAccumulateAllValuesOfsen(final Object[] parameters) {
    Set<Sensor> results = new HashSet<Sensor>();
    rawAccumulateAllValues(POSITION_SEN, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for sen.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsen() {
    return rawAccumulateAllValuesOfsen(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sen.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsen(final SensorTrackElementMatch partialMatch) {
    return rawAccumulateAllValuesOfsen(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sen.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsen(final TrackElement pTe) {
    return rawAccumulateAllValuesOfsen(new Object[]{
    null, 
    pTe
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for te.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<TrackElement> rawAccumulateAllValuesOfte(final Object[] parameters) {
    Set<TrackElement> results = new HashSet<TrackElement>();
    rawAccumulateAllValues(POSITION_TE, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for te.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<TrackElement> getAllValuesOfte() {
    return rawAccumulateAllValuesOfte(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for te.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<TrackElement> getAllValuesOfte(final SensorTrackElementMatch partialMatch) {
    return rawAccumulateAllValuesOfte(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for te.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<TrackElement> getAllValuesOfte(final Sensor pSen) {
    return rawAccumulateAllValuesOfte(new Object[]{
    pSen, 
    null
    });
  }
  
  @Override
  protected SensorTrackElementMatch tupleToMatch(final Tuple t) {
    try {
    	return SensorTrackElementMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Sensor) t.get(POSITION_SEN), (hu.bme.mit.trainbenchmark.railway.TrackElement) t.get(POSITION_TE));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected SensorTrackElementMatch arrayToMatch(final Object[] match) {
    try {
    	return SensorTrackElementMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Sensor) match[POSITION_SEN], (hu.bme.mit.trainbenchmark.railway.TrackElement) match[POSITION_TE]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected SensorTrackElementMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return SensorTrackElementMatch.newMutableMatch((hu.bme.mit.trainbenchmark.railway.Sensor) match[POSITION_SEN], (hu.bme.mit.trainbenchmark.railway.TrackElement) match[POSITION_TE]);
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
  public static IQuerySpecification<SensorTrackElementMatcher> querySpecification() throws IncQueryException {
    return SensorTrackElementQuerySpecification.instance();
  }
}
