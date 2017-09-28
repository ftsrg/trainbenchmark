/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SemaphoreNeighbor.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SemaphoreNeighborMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.EntrySemaphoreQuerySpecification;
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
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Inequality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.NegativePatternCall;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate SemaphoreNeighborMatcher in a type-safe way.
 * 
 * @see SemaphoreNeighborMatcher
 * @see SemaphoreNeighborMatch
 * 
 */
@SuppressWarnings("all")
public final class SemaphoreNeighborQuerySpecification extends BaseGeneratedEMFQuerySpecification<SemaphoreNeighborMatcher> {
  private SemaphoreNeighborQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static SemaphoreNeighborQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected SemaphoreNeighborMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SemaphoreNeighborMatcher.on(engine);
  }
  
  @Override
  public SemaphoreNeighborMatcher instantiate() throws ViatraQueryException {
    return SemaphoreNeighborMatcher.create();
  }
  
  @Override
  public SemaphoreNeighborMatch newEmptyMatch() {
    return SemaphoreNeighborMatch.newEmptyMatch();
  }
  
  @Override
  public SemaphoreNeighborMatch newMatch(final Object... parameters) {
    return SemaphoreNeighborMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Semaphore) parameters[0], (hu.bme.mit.trainbenchmark.railway.Route) parameters[1], (hu.bme.mit.trainbenchmark.railway.Route) parameters[2], (hu.bme.mit.trainbenchmark.railway.Sensor) parameters[3], (hu.bme.mit.trainbenchmark.railway.Sensor) parameters[4], (hu.bme.mit.trainbenchmark.railway.TrackElement) parameters[5], (hu.bme.mit.trainbenchmark.railway.TrackElement) parameters[6]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link SemaphoreNeighborQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link SemaphoreNeighborQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static SemaphoreNeighborQuerySpecification INSTANCE = new SemaphoreNeighborQuerySpecification();
    
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
    private final static SemaphoreNeighborQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pSemaphore = new PParameter("semaphore", "hu.bme.mit.trainbenchmark.railway.Semaphore", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pRoute1 = new PParameter("route1", "hu.bme.mit.trainbenchmark.railway.Route", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pRoute2 = new PParameter("route2", "hu.bme.mit.trainbenchmark.railway.Route", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pSensor1 = new PParameter("sensor1", "hu.bme.mit.trainbenchmark.railway.Sensor", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pSensor2 = new PParameter("sensor2", "hu.bme.mit.trainbenchmark.railway.Sensor", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pTe1 = new PParameter("te1", "hu.bme.mit.trainbenchmark.railway.TrackElement", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pTe2 = new PParameter("te2", "hu.bme.mit.trainbenchmark.railway.TrackElement", (IInputKey)null, PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pSemaphore, parameter_pRoute1, parameter_pRoute2, parameter_pSensor1, parameter_pSensor2, parameter_pTe1, parameter_pTe2);
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.viatra.semaphoreNeighbor";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("semaphore","route1","route2","sensor1","sensor2","te1","te2");
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
              PVariable var_semaphore = body.getOrCreateVariableByName("semaphore");
              PVariable var_route1 = body.getOrCreateVariableByName("route1");
              PVariable var_route2 = body.getOrCreateVariableByName("route2");
              PVariable var_sensor1 = body.getOrCreateVariableByName("sensor1");
              PVariable var_sensor2 = body.getOrCreateVariableByName("sensor2");
              PVariable var_te1 = body.getOrCreateVariableByName("te1");
              PVariable var_te2 = body.getOrCreateVariableByName("te2");
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_semaphore, parameter_pSemaphore),
                 new ExportedParameter(body, var_route1, parameter_pRoute1),
                 new ExportedParameter(body, var_route2, parameter_pRoute2),
                 new ExportedParameter(body, var_sensor1, parameter_pSensor1),
                 new ExportedParameter(body, var_sensor2, parameter_pSensor2),
                 new ExportedParameter(body, var_te1, parameter_pTe1),
                 new ExportedParameter(body, var_te2, parameter_pTe2)
              ));
              // 	Route.exit(route1, semaphore)
              new TypeConstraint(body, new FlatTuple(var_route1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_route1, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "exit")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Semaphore")));
              new Equality(body, var__virtual_0_, var_semaphore);
              // 	Route.requires(route1, sensor1)
              new TypeConstraint(body, new FlatTuple(var_route1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route")));
              PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
              new TypeConstraint(body, new FlatTuple(var_route1, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "requires")));
              new TypeConstraint(body, new FlatTuple(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Sensor")));
              new Equality(body, var__virtual_1_, var_sensor1);
              // 	TrackElement.monitoredBy(te1, sensor1)
              new TypeConstraint(body, new FlatTuple(var_te1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement")));
              PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
              new TypeConstraint(body, new FlatTuple(var_te1, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "monitoredBy")));
              new TypeConstraint(body, new FlatTuple(var__virtual_2_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Sensor")));
              new Equality(body, var__virtual_2_, var_sensor1);
              // 	TrackElement.connectsTo(te1, te2)
              new TypeConstraint(body, new FlatTuple(var_te1), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement")));
              PVariable var__virtual_3_ = body.getOrCreateVariableByName(".virtual{3}");
              new TypeConstraint(body, new FlatTuple(var_te1, var__virtual_3_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "connectsTo")));
              new TypeConstraint(body, new FlatTuple(var__virtual_3_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement")));
              new Equality(body, var__virtual_3_, var_te2);
              // 	TrackElement.monitoredBy(te2, sensor2)
              new TypeConstraint(body, new FlatTuple(var_te2), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement")));
              PVariable var__virtual_4_ = body.getOrCreateVariableByName(".virtual{4}");
              new TypeConstraint(body, new FlatTuple(var_te2, var__virtual_4_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "monitoredBy")));
              new TypeConstraint(body, new FlatTuple(var__virtual_4_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Sensor")));
              new Equality(body, var__virtual_4_, var_sensor2);
              // 	Route.requires(route2, sensor2)
              new TypeConstraint(body, new FlatTuple(var_route2), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route")));
              PVariable var__virtual_5_ = body.getOrCreateVariableByName(".virtual{5}");
              new TypeConstraint(body, new FlatTuple(var_route2, var__virtual_5_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "requires")));
              new TypeConstraint(body, new FlatTuple(var__virtual_5_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Sensor")));
              new Equality(body, var__virtual_5_, var_sensor2);
              // 		neg find entrySemaphore(route2, semaphore)
              new NegativePatternCall(body, new FlatTuple(var_route2, var_semaphore), EntrySemaphoreQuerySpecification.instance().getInternalQueryRepresentation());
              // 	route1 != route2
              new Inequality(body, var_route1, var_route2);
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
