/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/ConnectedSegmentsInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra;

import hu.bme.mit.trainbenchmark.benchmark.viatra.ConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.ConnectedSegmentsInjectQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.viatra.connectedSegmentsInject pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
 * e.g. in conjunction with {@link ViatraQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link ConnectedSegmentsInjectMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern connectedSegmentsInject(sensor, segment1, segment3)
 * {
 * 	Segment.connectsTo(segment1, segment3);
 * 	Segment.monitoredBy(segment1, sensor);
 * 	Segment.monitoredBy(segment3, sensor);
 * }
 * </pre></code>
 * 
 * @see ConnectedSegmentsInjectMatch
 * @see ConnectedSegmentsInjectProcessor
 * @see ConnectedSegmentsInjectQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class ConnectedSegmentsInjectMatcher extends BaseMatcher<ConnectedSegmentsInjectMatch> {
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static ConnectedSegmentsInjectMatcher on(final ViatraQueryEngine engine) throws ViatraQueryException {
    // check if matcher already exists
    ConnectedSegmentsInjectMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
        matcher = (ConnectedSegmentsInjectMatcher)engine.getMatcher(querySpecification());
    }
    return matcher;
  }
  
  /**
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * @return an initialized matcher
   * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
   * 
   */
  public static ConnectedSegmentsInjectMatcher create() throws ViatraQueryException {
    return new ConnectedSegmentsInjectMatcher();
  }
  
  private final static int POSITION_SENSOR = 0;
  
  private final static int POSITION_SEGMENT1 = 1;
  
  private final static int POSITION_SEGMENT3 = 2;
  
  private final static Logger LOGGER = ViatraQueryLoggingUtil.getLogger(ConnectedSegmentsInjectMatcher.class);
  
  /**
   * Initializes the pattern matcher within an existing VIATRA Query engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing VIATRA Query engine in which this matcher will be created.
   * @throws ViatraQueryException if an error occurs during pattern matcher creation
   * 
   */
  private ConnectedSegmentsInjectMatcher() throws ViatraQueryException {
    super(querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @return matches represented as a ConnectedSegmentsInjectMatch object.
   * 
   */
  public Collection<ConnectedSegmentsInjectMatch> getAllMatches(final Sensor pSensor, final Segment pSegment1, final Segment pSegment3) {
    return rawGetAllMatches(new Object[]{pSensor, pSegment1, pSegment3});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @return a match represented as a ConnectedSegmentsInjectMatch object, or null if no match is found.
   * 
   */
  public ConnectedSegmentsInjectMatch getOneArbitraryMatch(final Sensor pSensor, final Segment pSegment1, final Segment pSegment3) {
    return rawGetOneArbitraryMatch(new Object[]{pSensor, pSegment1, pSegment3});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Sensor pSensor, final Segment pSegment1, final Segment pSegment3) {
    return rawHasMatch(new Object[]{pSensor, pSegment1, pSegment3});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Sensor pSensor, final Segment pSegment1, final Segment pSegment3) {
    return rawCountMatches(new Object[]{pSensor, pSegment1, pSegment3});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Sensor pSensor, final Segment pSegment1, final Segment pSegment3, final IMatchProcessor<? super ConnectedSegmentsInjectMatch> processor) {
    rawForEachMatch(new Object[]{pSensor, pSegment1, pSegment3}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Sensor pSensor, final Segment pSegment1, final Segment pSegment3, final IMatchProcessor<? super ConnectedSegmentsInjectMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSensor, pSegment1, pSegment3}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public ConnectedSegmentsInjectMatch newMatch(final Sensor pSensor, final Segment pSegment1, final Segment pSegment3) {
    return ConnectedSegmentsInjectMatch.newMatch(pSensor, pSegment1, pSegment3);
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Sensor> rawAccumulateAllValuesOfsensor(final Object[] parameters) {
    Set<Sensor> results = new HashSet<Sensor>();
    rawAccumulateAllValues(POSITION_SENSOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor() {
    return rawAccumulateAllValuesOfsensor(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor(final ConnectedSegmentsInjectMatch partialMatch) {
    return rawAccumulateAllValuesOfsensor(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor(final Segment pSegment1, final Segment pSegment3) {
    return rawAccumulateAllValuesOfsensor(new Object[]{
    null, 
    pSegment1, 
    pSegment3
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment1.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Segment> rawAccumulateAllValuesOfsegment1(final Object[] parameters) {
    Set<Segment> results = new HashSet<Segment>();
    rawAccumulateAllValues(POSITION_SEGMENT1, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment1.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment1() {
    return rawAccumulateAllValuesOfsegment1(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment1.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment1(final ConnectedSegmentsInjectMatch partialMatch) {
    return rawAccumulateAllValuesOfsegment1(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment1.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment1(final Sensor pSensor, final Segment pSegment3) {
    return rawAccumulateAllValuesOfsegment1(new Object[]{
    pSensor, 
    null, 
    pSegment3
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment3.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  protected Set<Segment> rawAccumulateAllValuesOfsegment3(final Object[] parameters) {
    Set<Segment> results = new HashSet<Segment>();
    rawAccumulateAllValues(POSITION_SEGMENT3, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment3.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment3() {
    return rawAccumulateAllValuesOfsegment3(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment3.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment3(final ConnectedSegmentsInjectMatch partialMatch) {
    return rawAccumulateAllValuesOfsegment3(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment3.
   * @return the Set of all values or empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment3(final Sensor pSensor, final Segment pSegment1) {
    return rawAccumulateAllValuesOfsegment3(new Object[]{
    pSensor, 
    pSegment1, 
    null
    });
  }
  
  @Override
  protected ConnectedSegmentsInjectMatch tupleToMatch(final Tuple t) {
    try {
        return ConnectedSegmentsInjectMatch.newMatch((Sensor) t.get(POSITION_SENSOR), (Segment) t.get(POSITION_SEGMENT1), (Segment) t.get(POSITION_SEGMENT3));
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in tuple not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected ConnectedSegmentsInjectMatch arrayToMatch(final Object[] match) {
    try {
        return ConnectedSegmentsInjectMatch.newMatch((Sensor) match[POSITION_SENSOR], (Segment) match[POSITION_SEGMENT1], (Segment) match[POSITION_SEGMENT3]);
    } catch(ClassCastException e) {
        LOGGER.error("Element(s) in array not properly typed!",e);
        return null;
    }
  }
  
  @Override
  protected ConnectedSegmentsInjectMatch arrayToMatchMutable(final Object[] match) {
    try {
        return ConnectedSegmentsInjectMatch.newMutableMatch((Sensor) match[POSITION_SENSOR], (Segment) match[POSITION_SEGMENT1], (Segment) match[POSITION_SEGMENT3]);
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
  public static IQuerySpecification<ConnectedSegmentsInjectMatcher> querySpecification() throws ViatraQueryException {
    return ConnectedSegmentsInjectQuerySpecification.instance();
  }
}
