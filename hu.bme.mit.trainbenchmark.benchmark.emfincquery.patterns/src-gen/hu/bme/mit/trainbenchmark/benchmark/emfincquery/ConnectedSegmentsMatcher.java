/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ConnectedSegmentsQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Segment;
import hu.bme.mit.trainbenchmark.railway.Sensor;
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
 * Generated pattern matcher API of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegments pattern,
 * providing pattern-specific query methods.
 * 
 * <p>Use the pattern matcher on a given model via {@link #on(IncQueryEngine)},
 * e.g. in conjunction with {@link IncQueryEngine#on(Notifier)}.
 * 
 * <p>Matches of the pattern will be represented as {@link ConnectedSegmentsMatch}.
 * 
 * <p>Original source:
 * <code><pre>
 * pattern ConnectedSegments(sensor, segment1, segment2, segment3, segment4, segment5, segment6)
 * {
 * 	Segment.sensor(segment1, sensor);
 * 	Segment.connectsTo(segment1, segment2);
 * 	Segment.connectsTo(segment2, segment3);
 * 	Segment.connectsTo(segment3, segment4);
 * 	Segment.connectsTo(segment4, segment5);
 * 	Segment.connectsTo(segment5, segment6);
 * 	Segment.sensor(segment6, sensor);
 * }
 * </pre></code>
 * 
 * @see ConnectedSegmentsMatch
 * @see ConnectedSegmentsProcessor
 * @see ConnectedSegmentsQuerySpecification
 * 
 */
@SuppressWarnings("all")
public class ConnectedSegmentsMatcher extends BaseMatcher<ConnectedSegmentsMatch> {
  /**
   * Initializes the pattern matcher within an existing EMF-IncQuery engine.
   * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
   * The match set will be incrementally refreshed upon updates.
   * @param engine the existing EMF-IncQuery engine in which this matcher will be created.
   * @throws IncQueryException if an error occurs during pattern matcher creation
   * 
   */
  public static ConnectedSegmentsMatcher on(final IncQueryEngine engine) throws IncQueryException {
    // check if matcher already exists
    ConnectedSegmentsMatcher matcher = engine.getExistingMatcher(querySpecification());
    if (matcher == null) {
    	matcher = new ConnectedSegmentsMatcher(engine);
    	// do not have to "put" it into engine.matchers, reportMatcherInitialized() will take care of it
    }
    return matcher;
  }
  
  private final static int POSITION_SENSOR = 0;
  
  private final static int POSITION_SEGMENT1 = 1;
  
  private final static int POSITION_SEGMENT2 = 2;
  
  private final static int POSITION_SEGMENT3 = 3;
  
  private final static int POSITION_SEGMENT4 = 4;
  
  private final static int POSITION_SEGMENT5 = 5;
  
  private final static int POSITION_SEGMENT6 = 6;
  
  private final static Logger LOGGER = IncQueryLoggingUtil.getLogger(ConnectedSegmentsMatcher.class);
  
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
  public ConnectedSegmentsMatcher(final Notifier emfRoot) throws IncQueryException {
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
  public ConnectedSegmentsMatcher(final IncQueryEngine engine) throws IncQueryException {
    super(engine, querySpecification());
  }
  
  /**
   * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment2 the fixed value of pattern parameter segment2, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @param pSegment4 the fixed value of pattern parameter segment4, or null if not bound.
   * @param pSegment5 the fixed value of pattern parameter segment5, or null if not bound.
   * @param pSegment6 the fixed value of pattern parameter segment6, or null if not bound.
   * @return matches represented as a ConnectedSegmentsMatch object.
   * 
   */
  public Collection<ConnectedSegmentsMatch> getAllMatches(final Sensor pSensor, final Segment pSegment1, final Segment pSegment2, final Segment pSegment3, final Segment pSegment4, final Segment pSegment5, final Segment pSegment6) {
    return rawGetAllMatches(new Object[]{pSensor, pSegment1, pSegment2, pSegment3, pSegment4, pSegment5, pSegment6});
  }
  
  /**
   * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment2 the fixed value of pattern parameter segment2, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @param pSegment4 the fixed value of pattern parameter segment4, or null if not bound.
   * @param pSegment5 the fixed value of pattern parameter segment5, or null if not bound.
   * @param pSegment6 the fixed value of pattern parameter segment6, or null if not bound.
   * @return a match represented as a ConnectedSegmentsMatch object, or null if no match is found.
   * 
   */
  public ConnectedSegmentsMatch getOneArbitraryMatch(final Sensor pSensor, final Segment pSegment1, final Segment pSegment2, final Segment pSegment3, final Segment pSegment4, final Segment pSegment5, final Segment pSegment6) {
    return rawGetOneArbitraryMatch(new Object[]{pSensor, pSegment1, pSegment2, pSegment3, pSegment4, pSegment5, pSegment6});
  }
  
  /**
   * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
   * under any possible substitution of the unspecified parameters (if any).
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment2 the fixed value of pattern parameter segment2, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @param pSegment4 the fixed value of pattern parameter segment4, or null if not bound.
   * @param pSegment5 the fixed value of pattern parameter segment5, or null if not bound.
   * @param pSegment6 the fixed value of pattern parameter segment6, or null if not bound.
   * @return true if the input is a valid (partial) match of the pattern.
   * 
   */
  public boolean hasMatch(final Sensor pSensor, final Segment pSegment1, final Segment pSegment2, final Segment pSegment3, final Segment pSegment4, final Segment pSegment5, final Segment pSegment6) {
    return rawHasMatch(new Object[]{pSensor, pSegment1, pSegment2, pSegment3, pSegment4, pSegment5, pSegment6});
  }
  
  /**
   * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment2 the fixed value of pattern parameter segment2, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @param pSegment4 the fixed value of pattern parameter segment4, or null if not bound.
   * @param pSegment5 the fixed value of pattern parameter segment5, or null if not bound.
   * @param pSegment6 the fixed value of pattern parameter segment6, or null if not bound.
   * @return the number of pattern matches found.
   * 
   */
  public int countMatches(final Sensor pSensor, final Segment pSegment1, final Segment pSegment2, final Segment pSegment3, final Segment pSegment4, final Segment pSegment5, final Segment pSegment6) {
    return rawCountMatches(new Object[]{pSensor, pSegment1, pSegment2, pSegment3, pSegment4, pSegment5, pSegment6});
  }
  
  /**
   * Executes the given processor on each match of the pattern that conforms to the given fixed values of some parameters.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment2 the fixed value of pattern parameter segment2, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @param pSegment4 the fixed value of pattern parameter segment4, or null if not bound.
   * @param pSegment5 the fixed value of pattern parameter segment5, or null if not bound.
   * @param pSegment6 the fixed value of pattern parameter segment6, or null if not bound.
   * @param processor the action that will process each pattern match.
   * 
   */
  public void forEachMatch(final Sensor pSensor, final Segment pSegment1, final Segment pSegment2, final Segment pSegment3, final Segment pSegment4, final Segment pSegment5, final Segment pSegment6, final IMatchProcessor<? super ConnectedSegmentsMatch> processor) {
    rawForEachMatch(new Object[]{pSensor, pSegment1, pSegment2, pSegment3, pSegment4, pSegment5, pSegment6}, processor);
  }
  
  /**
   * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
   * Neither determinism nor randomness of selection is guaranteed.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment2 the fixed value of pattern parameter segment2, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @param pSegment4 the fixed value of pattern parameter segment4, or null if not bound.
   * @param pSegment5 the fixed value of pattern parameter segment5, or null if not bound.
   * @param pSegment6 the fixed value of pattern parameter segment6, or null if not bound.
   * @param processor the action that will process the selected match.
   * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
   * 
   */
  public boolean forOneArbitraryMatch(final Sensor pSensor, final Segment pSegment1, final Segment pSegment2, final Segment pSegment3, final Segment pSegment4, final Segment pSegment5, final Segment pSegment6, final IMatchProcessor<? super ConnectedSegmentsMatch> processor) {
    return rawForOneArbitraryMatch(new Object[]{pSensor, pSegment1, pSegment2, pSegment3, pSegment4, pSegment5, pSegment6}, processor);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSensor the fixed value of pattern parameter sensor, or null if not bound.
   * @param pSegment1 the fixed value of pattern parameter segment1, or null if not bound.
   * @param pSegment2 the fixed value of pattern parameter segment2, or null if not bound.
   * @param pSegment3 the fixed value of pattern parameter segment3, or null if not bound.
   * @param pSegment4 the fixed value of pattern parameter segment4, or null if not bound.
   * @param pSegment5 the fixed value of pattern parameter segment5, or null if not bound.
   * @param pSegment6 the fixed value of pattern parameter segment6, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public ConnectedSegmentsMatch newMatch(final Sensor pSensor, final Segment pSegment1, final Segment pSegment2, final Segment pSegment3, final Segment pSegment4, final Segment pSegment5, final Segment pSegment6) {
    return ConnectedSegmentsMatch.newMatch(pSensor, pSegment1, pSegment2, pSegment3, pSegment4, pSegment5, pSegment6);
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Sensor> rawAccumulateAllValuesOfsensor(final Object[] parameters) {
    Set<Sensor> results = new HashSet<Sensor>();
    rawAccumulateAllValues(POSITION_SENSOR, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor() {
    return rawAccumulateAllValuesOfsensor(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor(final ConnectedSegmentsMatch partialMatch) {
    return rawAccumulateAllValuesOfsensor(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for sensor.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Sensor> getAllValuesOfsensor(final Segment pSegment1, final Segment pSegment2, final Segment pSegment3, final Segment pSegment4, final Segment pSegment5, final Segment pSegment6) {
    return rawAccumulateAllValuesOfsensor(new Object[]{
    null, 
    pSegment1, 
    pSegment2, 
    pSegment3, 
    pSegment4, 
    pSegment5, 
    pSegment6
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Segment> rawAccumulateAllValuesOfsegment1(final Object[] parameters) {
    Set<Segment> results = new HashSet<Segment>();
    rawAccumulateAllValues(POSITION_SEGMENT1, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment1() {
    return rawAccumulateAllValuesOfsegment1(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment1(final ConnectedSegmentsMatch partialMatch) {
    return rawAccumulateAllValuesOfsegment1(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment1.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment1(final Sensor pSensor, final Segment pSegment2, final Segment pSegment3, final Segment pSegment4, final Segment pSegment5, final Segment pSegment6) {
    return rawAccumulateAllValuesOfsegment1(new Object[]{
    pSensor, 
    null, 
    pSegment2, 
    pSegment3, 
    pSegment4, 
    pSegment5, 
    pSegment6
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Segment> rawAccumulateAllValuesOfsegment2(final Object[] parameters) {
    Set<Segment> results = new HashSet<Segment>();
    rawAccumulateAllValues(POSITION_SEGMENT2, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment2() {
    return rawAccumulateAllValuesOfsegment2(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment2(final ConnectedSegmentsMatch partialMatch) {
    return rawAccumulateAllValuesOfsegment2(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment2.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment2(final Sensor pSensor, final Segment pSegment1, final Segment pSegment3, final Segment pSegment4, final Segment pSegment5, final Segment pSegment6) {
    return rawAccumulateAllValuesOfsegment2(new Object[]{
    pSensor, 
    pSegment1, 
    null, 
    pSegment3, 
    pSegment4, 
    pSegment5, 
    pSegment6
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment3.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Segment> rawAccumulateAllValuesOfsegment3(final Object[] parameters) {
    Set<Segment> results = new HashSet<Segment>();
    rawAccumulateAllValues(POSITION_SEGMENT3, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment3.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment3() {
    return rawAccumulateAllValuesOfsegment3(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment3.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment3(final ConnectedSegmentsMatch partialMatch) {
    return rawAccumulateAllValuesOfsegment3(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment3.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment3(final Sensor pSensor, final Segment pSegment1, final Segment pSegment2, final Segment pSegment4, final Segment pSegment5, final Segment pSegment6) {
    return rawAccumulateAllValuesOfsegment3(new Object[]{
    pSensor, 
    pSegment1, 
    pSegment2, 
    null, 
    pSegment4, 
    pSegment5, 
    pSegment6
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment4.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Segment> rawAccumulateAllValuesOfsegment4(final Object[] parameters) {
    Set<Segment> results = new HashSet<Segment>();
    rawAccumulateAllValues(POSITION_SEGMENT4, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment4.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment4() {
    return rawAccumulateAllValuesOfsegment4(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment4.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment4(final ConnectedSegmentsMatch partialMatch) {
    return rawAccumulateAllValuesOfsegment4(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment4.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment4(final Sensor pSensor, final Segment pSegment1, final Segment pSegment2, final Segment pSegment3, final Segment pSegment5, final Segment pSegment6) {
    return rawAccumulateAllValuesOfsegment4(new Object[]{
    pSensor, 
    pSegment1, 
    pSegment2, 
    pSegment3, 
    null, 
    pSegment5, 
    pSegment6
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment5.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Segment> rawAccumulateAllValuesOfsegment5(final Object[] parameters) {
    Set<Segment> results = new HashSet<Segment>();
    rawAccumulateAllValues(POSITION_SEGMENT5, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment5.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment5() {
    return rawAccumulateAllValuesOfsegment5(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment5.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment5(final ConnectedSegmentsMatch partialMatch) {
    return rawAccumulateAllValuesOfsegment5(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment5.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment5(final Sensor pSensor, final Segment pSegment1, final Segment pSegment2, final Segment pSegment3, final Segment pSegment4, final Segment pSegment6) {
    return rawAccumulateAllValuesOfsegment5(new Object[]{
    pSensor, 
    pSegment1, 
    pSegment2, 
    pSegment3, 
    pSegment4, 
    null, 
    pSegment6
    });
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment6.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  protected Set<Segment> rawAccumulateAllValuesOfsegment6(final Object[] parameters) {
    Set<Segment> results = new HashSet<Segment>();
    rawAccumulateAllValues(POSITION_SEGMENT6, parameters, results);
    return results;
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment6.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment6() {
    return rawAccumulateAllValuesOfsegment6(emptyArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment6.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment6(final ConnectedSegmentsMatch partialMatch) {
    return rawAccumulateAllValuesOfsegment6(partialMatch.toArray());
  }
  
  /**
   * Retrieve the set of values that occur in matches for segment6.
   * @return the Set of all values, null if no parameter with the given name exists, empty set if there are no matches
   * 
   */
  public Set<Segment> getAllValuesOfsegment6(final Sensor pSensor, final Segment pSegment1, final Segment pSegment2, final Segment pSegment3, final Segment pSegment4, final Segment pSegment5) {
    return rawAccumulateAllValuesOfsegment6(new Object[]{
    pSensor, 
    pSegment1, 
    pSegment2, 
    pSegment3, 
    pSegment4, 
    pSegment5, 
    null
    });
  }
  
  @Override
  protected ConnectedSegmentsMatch tupleToMatch(final Tuple t) {
    try {
    	return ConnectedSegmentsMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Sensor) t.get(POSITION_SENSOR), (hu.bme.mit.trainbenchmark.railway.Segment) t.get(POSITION_SEGMENT1), (hu.bme.mit.trainbenchmark.railway.Segment) t.get(POSITION_SEGMENT2), (hu.bme.mit.trainbenchmark.railway.Segment) t.get(POSITION_SEGMENT3), (hu.bme.mit.trainbenchmark.railway.Segment) t.get(POSITION_SEGMENT4), (hu.bme.mit.trainbenchmark.railway.Segment) t.get(POSITION_SEGMENT5), (hu.bme.mit.trainbenchmark.railway.Segment) t.get(POSITION_SEGMENT6));
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in tuple not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected ConnectedSegmentsMatch arrayToMatch(final Object[] match) {
    try {
    	return ConnectedSegmentsMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Sensor) match[POSITION_SENSOR], (hu.bme.mit.trainbenchmark.railway.Segment) match[POSITION_SEGMENT1], (hu.bme.mit.trainbenchmark.railway.Segment) match[POSITION_SEGMENT2], (hu.bme.mit.trainbenchmark.railway.Segment) match[POSITION_SEGMENT3], (hu.bme.mit.trainbenchmark.railway.Segment) match[POSITION_SEGMENT4], (hu.bme.mit.trainbenchmark.railway.Segment) match[POSITION_SEGMENT5], (hu.bme.mit.trainbenchmark.railway.Segment) match[POSITION_SEGMENT6]);
    } catch(ClassCastException e) {
    	LOGGER.error("Element(s) in array not properly typed!",e);
    	return null;
    }
  }
  
  @Override
  protected ConnectedSegmentsMatch arrayToMatchMutable(final Object[] match) {
    try {
    	return ConnectedSegmentsMatch.newMutableMatch((hu.bme.mit.trainbenchmark.railway.Sensor) match[POSITION_SENSOR], (hu.bme.mit.trainbenchmark.railway.Segment) match[POSITION_SEGMENT1], (hu.bme.mit.trainbenchmark.railway.Segment) match[POSITION_SEGMENT2], (hu.bme.mit.trainbenchmark.railway.Segment) match[POSITION_SEGMENT3], (hu.bme.mit.trainbenchmark.railway.Segment) match[POSITION_SEGMENT4], (hu.bme.mit.trainbenchmark.railway.Segment) match[POSITION_SEGMENT5], (hu.bme.mit.trainbenchmark.railway.Segment) match[POSITION_SEGMENT6]);
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
  public static IQuerySpecification<ConnectedSegmentsMatcher> querySpecification() throws IncQueryException {
    return ConnectedSegmentsQuerySpecification.instance();
  }
}
