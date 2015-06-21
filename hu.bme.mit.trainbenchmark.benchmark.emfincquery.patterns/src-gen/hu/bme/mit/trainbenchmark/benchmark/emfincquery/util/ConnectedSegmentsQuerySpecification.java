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
    return ConnectedSegmentsMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Sensor) parameters[0], (hu.bme.mit.trainbenchmark.railway.Sensor) parameters[1], (hu.bme.mit.trainbenchmark.railway.Segment) parameters[2], (hu.bme.mit.trainbenchmark.railway.Segment) parameters[3], (hu.bme.mit.trainbenchmark.railway.Segment) parameters[4], (hu.bme.mit.trainbenchmark.railway.Segment) parameters[5], (hu.bme.mit.trainbenchmark.railway.Segment) parameters[6], (hu.bme.mit.trainbenchmark.railway.Segment) parameters[7]);
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
      return Arrays.asList("sensor1","sensor2","segment1","segment2","segment3","segment4","segment5","segment6");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("sensor1", "hu.bme.mit.trainbenchmark.railway.Sensor"),new PParameter("sensor2", "hu.bme.mit.trainbenchmark.railway.Sensor"),new PParameter("segment1", "hu.bme.mit.trainbenchmark.railway.Segment"),new PParameter("segment2", "hu.bme.mit.trainbenchmark.railway.Segment"),new PParameter("segment3", "hu.bme.mit.trainbenchmark.railway.Segment"),new PParameter("segment4", "hu.bme.mit.trainbenchmark.railway.Segment"),new PParameter("segment5", "hu.bme.mit.trainbenchmark.railway.Segment"),new PParameter("segment6", "hu.bme.mit.trainbenchmark.railway.Segment"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      {
      	PBody body = new PBody(this);
      	PVariable var_sensor2 = body.getOrCreateVariableByName("sensor2");
      	PVariable var_segment1 = body.getOrCreateVariableByName("segment1");
      	PVariable var_segment2 = body.getOrCreateVariableByName("segment2");
      	PVariable var_segment3 = body.getOrCreateVariableByName("segment3");
      	PVariable var_segment4 = body.getOrCreateVariableByName("segment4");
      	PVariable var_segment5 = body.getOrCreateVariableByName("segment5");
      	PVariable var_segment6 = body.getOrCreateVariableByName("segment6");
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_sensor2, "sensor1"),
      				
      		new ExportedParameter(body, var_sensor2, "sensor2"),
      				
      		new ExportedParameter(body, var_segment1, "segment1"),
      				
      		new ExportedParameter(body, var_segment2, "segment2"),
      				
      		new ExportedParameter(body, var_segment3, "segment3"),
      				
      		new ExportedParameter(body, var_segment4, "segment4"),
      				
      		new ExportedParameter(body, var_segment5, "segment5"),
      				
      		new ExportedParameter(body, var_segment6, "segment6")
      	));
      	new TypeUnary(body, var_segment1, getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Segment");
      	new TypeBinary(body, CONTEXT, var_segment1, var_sensor2, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "sensor"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/TrackElement.sensor");
      	new TypeBinary(body, CONTEXT, var_segment1, var_segment2, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/TrackElement.connectsTo");
      	new TypeUnary(body, var_segment2, getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Segment");
      	new TypeBinary(body, CONTEXT, var_segment2, var_segment3, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/TrackElement.connectsTo");
      	new TypeUnary(body, var_segment3, getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Segment");
      	new TypeBinary(body, CONTEXT, var_segment3, var_segment4, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/TrackElement.connectsTo");
      	new TypeUnary(body, var_segment4, getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Segment");
      	new TypeBinary(body, CONTEXT, var_segment4, var_segment5, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/TrackElement.connectsTo");
      	new TypeUnary(body, var_segment5, getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Segment");
      	new TypeBinary(body, CONTEXT, var_segment5, var_segment6, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/TrackElement.connectsTo");
      	new TypeUnary(body, var_segment6, getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/Segment");
      	new TypeBinary(body, CONTEXT, var_segment6, var_sensor2, getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "sensor"), "http://www.semanticweb.org/ontologies/2015/trainbenchmark/TrackElement.sensor");
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
