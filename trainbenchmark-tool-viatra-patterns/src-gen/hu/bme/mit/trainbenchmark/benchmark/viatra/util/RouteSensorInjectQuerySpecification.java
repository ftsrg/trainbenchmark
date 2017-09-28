/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/RouteSensorInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.viatra.RouteSensorInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.RouteSensorInjectMatcher;
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
 * A pattern-specific query specification that can instantiate RouteSensorInjectMatcher in a type-safe way.
 * 
 * @see RouteSensorInjectMatcher
 * @see RouteSensorInjectMatch
 * 
 */
@SuppressWarnings("all")
public final class RouteSensorInjectQuerySpecification extends BaseGeneratedEMFQuerySpecification<RouteSensorInjectMatcher> {
  private RouteSensorInjectQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static RouteSensorInjectQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected RouteSensorInjectMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return RouteSensorInjectMatcher.on(engine);
  }
  
  @Override
  public RouteSensorInjectMatcher instantiate() throws ViatraQueryException {
    return RouteSensorInjectMatcher.create();
  }
  
  @Override
  public RouteSensorInjectMatch newEmptyMatch() {
    return RouteSensorInjectMatch.newEmptyMatch();
  }
  
  @Override
  public RouteSensorInjectMatch newMatch(final Object... parameters) {
    return RouteSensorInjectMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Route) parameters[0], (hu.bme.mit.trainbenchmark.railway.Sensor) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link RouteSensorInjectQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link RouteSensorInjectQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static RouteSensorInjectQuerySpecification INSTANCE = new RouteSensorInjectQuerySpecification();
    
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
    private final static RouteSensorInjectQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pRoute = new PParameter("route", "hu.bme.mit.trainbenchmark.railway.Route", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pSensor = new PParameter("sensor", "hu.bme.mit.trainbenchmark.railway.Sensor", (IInputKey)null, PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pRoute, parameter_pSensor);
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.viatra.routeSensorInject";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("route","sensor");
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
              PVariable var_route = body.getOrCreateVariableByName("route");
              PVariable var_sensor = body.getOrCreateVariableByName("sensor");
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_route, parameter_pRoute),
                 new ExportedParameter(body, var_sensor, parameter_pSensor)
              ));
              // 	Route.requires(route, sensor)
              new TypeConstraint(body, new FlatTuple(var_route), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_route, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "requires")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Sensor")));
              new Equality(body, var__virtual_0_, var_sensor);
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
