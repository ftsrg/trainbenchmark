package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectedSegmentsMatcher;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.incquery.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.incquery.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.psystem.PBody;
import org.eclipse.incquery.runtime.matchers.psystem.PVariable;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.incquery.runtime.matchers.tuple.FlatTuple;

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
    return ConnectedSegmentsMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Sensor) parameters[0], (hu.bme.mit.trainbenchmark.railway.Segment) parameters[1], (hu.bme.mit.trainbenchmark.railway.Segment) parameters[2], (hu.bme.mit.trainbenchmark.railway.Segment) parameters[3], (hu.bme.mit.trainbenchmark.railway.Segment) parameters[4], (hu.bme.mit.trainbenchmark.railway.Segment) parameters[5], (hu.bme.mit.trainbenchmark.railway.Segment) parameters[6]);
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
      return Arrays.asList("sensor","segment1","segment2","segment3","segment4","segment5","segment6");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("sensor", "hu.bme.mit.trainbenchmark.railway.Sensor"),new PParameter("segment1", "hu.bme.mit.trainbenchmark.railway.Segment"),new PParameter("segment2", "hu.bme.mit.trainbenchmark.railway.Segment"),new PParameter("segment3", "hu.bme.mit.trainbenchmark.railway.Segment"),new PParameter("segment4", "hu.bme.mit.trainbenchmark.railway.Segment"),new PParameter("segment5", "hu.bme.mit.trainbenchmark.railway.Segment"),new PParameter("segment6", "hu.bme.mit.trainbenchmark.railway.Segment"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      	{
      		PBody body = new PBody(this);
      		PVariable var_sensor = body.getOrCreateVariableByName("sensor");
      		PVariable var_segment1 = body.getOrCreateVariableByName("segment1");
      		PVariable var_segment2 = body.getOrCreateVariableByName("segment2");
      		PVariable var_segment3 = body.getOrCreateVariableByName("segment3");
      		PVariable var_segment4 = body.getOrCreateVariableByName("segment4");
      		PVariable var_segment5 = body.getOrCreateVariableByName("segment5");
      		PVariable var_segment6 = body.getOrCreateVariableByName("segment6");
      		body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
      		   new ExportedParameter(body, var_sensor, "sensor"),
      		   new ExportedParameter(body, var_segment1, "segment1"),
      		   new ExportedParameter(body, var_segment2, "segment2"),
      		   new ExportedParameter(body, var_segment3, "segment3"),
      		   new ExportedParameter(body, var_segment4, "segment4"),
      		   new ExportedParameter(body, var_segment5, "segment5"),
      		   new ExportedParameter(body, var_segment6, "segment6")
      		));
      		// 	Segment.connectsTo(segment1, segment2)
      		new TypeConstraint(body, new FlatTuple(var_segment1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment")));
      		PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
      		new TypeConstraint(body, new FlatTuple(var_segment1, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo")));
      		new Equality(body, var__virtual_0_, var_segment2);
      		// 	Segment.connectsTo(segment2, segment3)
      		new TypeConstraint(body, new FlatTuple(var_segment2), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment")));
      		PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
      		new TypeConstraint(body, new FlatTuple(var_segment2, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo")));
      		new Equality(body, var__virtual_1_, var_segment3);
      		// 	Segment.connectsTo(segment3, segment4)
      		new TypeConstraint(body, new FlatTuple(var_segment3), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment")));
      		PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
      		new TypeConstraint(body, new FlatTuple(var_segment3, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo")));
      		new Equality(body, var__virtual_2_, var_segment4);
      		// 	Segment.connectsTo(segment4, segment5)
      		new TypeConstraint(body, new FlatTuple(var_segment4), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment")));
      		PVariable var__virtual_3_ = body.getOrCreateVariableByName(".virtual{3}");
      		new TypeConstraint(body, new FlatTuple(var_segment4, var__virtual_3_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo")));
      		new Equality(body, var__virtual_3_, var_segment5);
      		// 	Segment.connectsTo(segment5, segment6)
      		new TypeConstraint(body, new FlatTuple(var_segment5), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment")));
      		PVariable var__virtual_4_ = body.getOrCreateVariableByName(".virtual{4}");
      		new TypeConstraint(body, new FlatTuple(var_segment5, var__virtual_4_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo")));
      		new Equality(body, var__virtual_4_, var_segment6);
      		// 	Segment.monitoredBy(segment1, sensor)
      		new TypeConstraint(body, new FlatTuple(var_segment1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment")));
      		PVariable var__virtual_5_ = body.getOrCreateVariableByName(".virtual{5}");
      		new TypeConstraint(body, new FlatTuple(var_segment1, var__virtual_5_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "monitoredBy")));
      		new Equality(body, var__virtual_5_, var_sensor);
      		// 	Segment.monitoredBy(segment2, sensor)
      		new TypeConstraint(body, new FlatTuple(var_segment2), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment")));
      		PVariable var__virtual_6_ = body.getOrCreateVariableByName(".virtual{6}");
      		new TypeConstraint(body, new FlatTuple(var_segment2, var__virtual_6_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "monitoredBy")));
      		new Equality(body, var__virtual_6_, var_sensor);
      		// 	Segment.monitoredBy(segment3, sensor)
      		new TypeConstraint(body, new FlatTuple(var_segment3), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment")));
      		PVariable var__virtual_7_ = body.getOrCreateVariableByName(".virtual{7}");
      		new TypeConstraint(body, new FlatTuple(var_segment3, var__virtual_7_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "monitoredBy")));
      		new Equality(body, var__virtual_7_, var_sensor);
      		// 	Segment.monitoredBy(segment4, sensor)
      		new TypeConstraint(body, new FlatTuple(var_segment4), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment")));
      		PVariable var__virtual_8_ = body.getOrCreateVariableByName(".virtual{8}");
      		new TypeConstraint(body, new FlatTuple(var_segment4, var__virtual_8_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "monitoredBy")));
      		new Equality(body, var__virtual_8_, var_sensor);
      		// 	Segment.monitoredBy(segment5, sensor)
      		new TypeConstraint(body, new FlatTuple(var_segment5), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment")));
      		PVariable var__virtual_9_ = body.getOrCreateVariableByName(".virtual{9}");
      		new TypeConstraint(body, new FlatTuple(var_segment5, var__virtual_9_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "monitoredBy")));
      		new Equality(body, var__virtual_9_, var_sensor);
      		// 	Segment.monitoredBy(segment6, sensor)
      		new TypeConstraint(body, new FlatTuple(var_segment6), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment")));
      		PVariable var__virtual_10_ = body.getOrCreateVariableByName(".virtual{10}");
      		new TypeConstraint(body, new FlatTuple(var_segment6, var__virtual_10_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "monitoredBy")));
      		new Equality(body, var__virtual_10_, var_sensor);
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
