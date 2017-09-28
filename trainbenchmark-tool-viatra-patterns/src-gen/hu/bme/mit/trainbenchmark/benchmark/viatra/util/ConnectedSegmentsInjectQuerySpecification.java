/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/ConnectedSegmentsInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.viatra.ConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.ConnectedSegmentsInjectMatcher;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.backend.IQueryBackendFactory;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.matchers.context.IInputKey;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate ConnectedSegmentsInjectMatcher in a type-safe way.
 * 
 * @see ConnectedSegmentsInjectMatcher
 * @see ConnectedSegmentsInjectMatch
 * 
 */
@SuppressWarnings("all")
public final class ConnectedSegmentsInjectQuerySpecification extends BaseGeneratedEMFQuerySpecification<ConnectedSegmentsInjectMatcher> {
  private ConnectedSegmentsInjectQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static ConnectedSegmentsInjectQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected ConnectedSegmentsInjectMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return ConnectedSegmentsInjectMatcher.on(engine);
  }
  
  @Override
  public ConnectedSegmentsInjectMatcher instantiate() throws ViatraQueryException {
    return ConnectedSegmentsInjectMatcher.create();
  }
  
  @Override
  public ConnectedSegmentsInjectMatch newEmptyMatch() {
    return ConnectedSegmentsInjectMatch.newEmptyMatch();
  }
  
  @Override
  public ConnectedSegmentsInjectMatch newMatch(final Object... parameters) {
    return ConnectedSegmentsInjectMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Sensor) parameters[0], (hu.bme.mit.trainbenchmark.railway.Segment) parameters[1], (hu.bme.mit.trainbenchmark.railway.Segment) parameters[2]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link ConnectedSegmentsInjectQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link ConnectedSegmentsInjectQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static ConnectedSegmentsInjectQuerySpecification INSTANCE = new ConnectedSegmentsInjectQuerySpecification();
    
    /**
     * Statically initializes the query specification <b>after</b> the field {@link #INSTANCE} is assigned.
     * This initialization order is required to support indirect recursion.
     * 
     * <p> The static initializer is defined using a helper field to work around limitations of the code generator.
     * 
     */
    private final static Object STATIC_INITIALIZER = ensureInitialized();
    
    public static Object ensureInitialized() {
      INSTANCE.ensureInitializedInternalSneaky();
      return null;
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static ConnectedSegmentsInjectQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pSensor = new PParameter("sensor", "hu.bme.mit.trainbenchmark.railway.Sensor", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pSegment1 = new PParameter("segment1", "hu.bme.mit.trainbenchmark.railway.Segment", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pSegment3 = new PParameter("segment3", "hu.bme.mit.trainbenchmark.railway.Segment", (IInputKey)null, PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pSensor, parameter_pSegment1, parameter_pSegment3);
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.viatra.connectedSegmentsInject";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("sensor","segment1","segment3");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return parameters;
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      setEvaluationHints(new QueryEvaluationHint(null, (IQueryBackendFactory)null));
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
          {
              PBody body = new PBody(this);
              PVariable var_sensor = body.getOrCreateVariableByName("sensor");
              PVariable var_segment1 = body.getOrCreateVariableByName("segment1");
              PVariable var_segment3 = body.getOrCreateVariableByName("segment3");
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_sensor, parameter_pSensor),
                 new ExportedParameter(body, var_segment1, parameter_pSegment1),
                 new ExportedParameter(body, var_segment3, parameter_pSegment3)
              ));
              // 	Segment.connectsTo(segment1, segment3)
              new TypeConstraint(body, new FlatTuple(var_segment1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_segment1, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement")));
              new Equality(body, var__virtual_0_, var_segment3);
              // 	Segment.monitoredBy(segment1, sensor)
              new TypeConstraint(body, new FlatTuple(var_segment1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment")));
              PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
              new TypeConstraint(body, new FlatTuple(var_segment1, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "monitoredBy")));
              new TypeConstraint(body, new FlatTuple(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Sensor")));
              new Equality(body, var__virtual_1_, var_sensor);
              // 	Segment.monitoredBy(segment3, sensor)
              new TypeConstraint(body, new FlatTuple(var_segment3), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment")));
              PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
              new TypeConstraint(body, new FlatTuple(var_segment3, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "monitoredBy")));
              new TypeConstraint(body, new FlatTuple(var__virtual_2_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Sensor")));
              new Equality(body, var__virtual_2_, var_sensor);
              bodies.add(body);
          }
          // to silence compiler error
          if (false) throw new ViatraQueryException("Never", "happens");
      } catch (ViatraQueryException ex) {
          throw processDependencyException(ex);
      }
      return bodies;
    }
  }
}
