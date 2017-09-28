/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/RouteSensor.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.viatra.RouteSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.RouteSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.RequiredQuerySpecification;
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
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.NegativePatternCall;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate RouteSensorMatcher in a type-safe way.
 * 
 * @see RouteSensorMatcher
 * @see RouteSensorMatch
 * 
 */
@SuppressWarnings("all")
public final class RouteSensorQuerySpecification extends BaseGeneratedEMFQuerySpecification<RouteSensorMatcher> {
  private RouteSensorQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static RouteSensorQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected RouteSensorMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return RouteSensorMatcher.on(engine);
  }
  
  @Override
  public RouteSensorMatcher instantiate() throws ViatraQueryException {
    return RouteSensorMatcher.create();
  }
  
  @Override
  public RouteSensorMatch newEmptyMatch() {
    return RouteSensorMatch.newEmptyMatch();
  }
  
  @Override
  public RouteSensorMatch newMatch(final Object... parameters) {
    return RouteSensorMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Route) parameters[0], (hu.bme.mit.trainbenchmark.railway.Sensor) parameters[1], (hu.bme.mit.trainbenchmark.railway.SwitchPosition) parameters[2], (hu.bme.mit.trainbenchmark.railway.Switch) parameters[3]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link RouteSensorQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link RouteSensorQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static RouteSensorQuerySpecification INSTANCE = new RouteSensorQuerySpecification();
    
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
    private final static RouteSensorQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pRoute = new PParameter("route", "hu.bme.mit.trainbenchmark.railway.Route", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pSensor = new PParameter("sensor", "hu.bme.mit.trainbenchmark.railway.Sensor", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pSwP = new PParameter("swP", "hu.bme.mit.trainbenchmark.railway.SwitchPosition", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pSw = new PParameter("sw", "hu.bme.mit.trainbenchmark.railway.Switch", (IInputKey)null, PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pRoute, parameter_pSensor, parameter_pSwP, parameter_pSw);
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.viatra.routeSensor";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("route","sensor","swP","sw");
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
              PVariable var_swP = body.getOrCreateVariableByName("swP");
              PVariable var_sw = body.getOrCreateVariableByName("sw");
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_route, parameter_pRoute),
                 new ExportedParameter(body, var_sensor, parameter_pSensor),
                 new ExportedParameter(body, var_swP, parameter_pSwP),
                 new ExportedParameter(body, var_sw, parameter_pSw)
              ));
              // 	Route.follows(route, swP)
              new TypeConstraint(body, new FlatTuple(var_route), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_route, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "follows")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition")));
              new Equality(body, var__virtual_0_, var_swP);
              // 	SwitchPosition.target(swP, sw)
              new TypeConstraint(body, new FlatTuple(var_swP), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition")));
              PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
              new TypeConstraint(body, new FlatTuple(var_swP, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition", "target")));
              new TypeConstraint(body, new FlatTuple(var__virtual_1_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Switch")));
              new Equality(body, var__virtual_1_, var_sw);
              // 	TrackElement.monitoredBy(sw, sensor)
              new TypeConstraint(body, new FlatTuple(var_sw), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement")));
              PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
              new TypeConstraint(body, new FlatTuple(var_sw, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "TrackElement", "monitoredBy")));
              new TypeConstraint(body, new FlatTuple(var__virtual_2_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Sensor")));
              new Equality(body, var__virtual_2_, var_sensor);
              // 	neg find required(sensor, route)
              new NegativePatternCall(body, new FlatTuple(var_sensor, var_route), RequiredQuerySpecification.instance().getInternalQueryRepresentation());
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
