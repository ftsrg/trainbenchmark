/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatcher;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.psystem.PBody;
import org.eclipse.incquery.runtime.matchers.psystem.PVariable;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeBinary;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeUnary;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.psystem.queries.QueryInitializationException;

/**
 * A pattern-specific query specification that can instantiate ConnectedSegmentsMatcher in a type-safe way.
 * 
 * @see ConnectedSegmentsMatcher
 * @see ConnectedSegmentsMatch
 * 
 */
@SuppressWarnings("all")
public final class ConnectedSegmentsQuerySpecification extends BaseGeneratedEMFQuerySpecification<ConnectedSegmentsMatcher> {
  private ConnectedSegmentsQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static ConnectedSegmentsQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected ConnectedSegmentsMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return ConnectedSegmentsMatcher.on(engine);
  }
  
  @Override
  public ConnectedSegmentsMatch newEmptyMatch() {
    return ConnectedSegmentsMatch.newEmptyMatch();
  }
  
  @Override
  public ConnectedSegmentsMatch newMatch(final Object... parameters) {
    return ConnectedSegmentsMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Segment) parameters[0]);
  }
  
  private static class LazyHolder {
    private final static ConnectedSegmentsQuerySpecification INSTANCE = make();
    
    public static ConnectedSegmentsQuerySpecification make() {
      return new ConnectedSegmentsQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static ConnectedSegmentsQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegments";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("segment");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("segment", "hu.bme.mit.trainbenchmark.railway.Segment"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      {
      	PBody body = new PBody(this);
      	PVariable var_segment = body.getOrCreateVariableByName("segment");
      	PVariable var_seg2 = body.getOrCreateVariableByName("seg2");
      	PVariable var_seg3 = body.getOrCreateVariableByName("seg3");
      	PVariable var_seg4 = body.getOrCreateVariableByName("seg4");
      	PVariable var_seg5 = body.getOrCreateVariableByName("seg5");
      	PVariable var_seg6 = body.getOrCreateVariableByName("seg6");
      	PVariable var_sensor2 = body.getOrCreateVariableByName("sensor2");
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_segment, "segment")
      	));
      	new TypeUnary(body, var_segment, getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Segment");
      	new TypeBinary(body, CONTEXT, var_segment, var_sensor2, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "sensor"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/TrackElement.sensor");
      	new TypeBinary(body, CONTEXT, var_segment, var_seg2, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/TrackElement.connectsTo");
      	new TypeUnary(body, var_seg2, getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Segment");
      	new TypeBinary(body, CONTEXT, var_seg2, var_seg3, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/TrackElement.connectsTo");
      	new TypeUnary(body, var_seg3, getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Segment");
      	new TypeBinary(body, CONTEXT, var_seg3, var_seg4, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/TrackElement.connectsTo");
      	new TypeUnary(body, var_seg4, getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Segment");
      	new TypeBinary(body, CONTEXT, var_seg4, var_seg5, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/TrackElement.connectsTo");
      	new TypeUnary(body, var_seg5, getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Segment");
      	new TypeBinary(body, CONTEXT, var_seg5, var_seg6, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/TrackElement.connectsTo");
      	new TypeUnary(body, var_seg6, getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Segment");
      	new TypeBinary(body, CONTEXT, var_seg6, var_sensor2, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "sensor"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/TrackElement.sensor");
      	bodies.add(body);
      }
      	// to silence compiler error
      	if (false) throw new IncQueryException("Never", "happens");
      } catch (IncQueryException ex) {
      	throw processDependencyException(ex);
      }
      return bodies;
    }
  }
}
