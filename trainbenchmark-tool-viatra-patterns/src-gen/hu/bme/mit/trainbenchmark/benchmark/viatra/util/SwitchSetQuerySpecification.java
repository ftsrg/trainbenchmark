/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SwitchSet.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchSetMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchSetMatcher;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EDataTypeInSlotsKey;
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
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.ConstantValue;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate SwitchSetMatcher in a type-safe way.
 * 
 * @see SwitchSetMatcher
 * @see SwitchSetMatch
 * 
 */
@SuppressWarnings("all")
public final class SwitchSetQuerySpecification extends BaseGeneratedEMFQuerySpecification<SwitchSetMatcher> {
  private SwitchSetQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static SwitchSetQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected SwitchSetMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SwitchSetMatcher.on(engine);
  }
  
  @Override
  public SwitchSetMatcher instantiate() throws ViatraQueryException {
    return SwitchSetMatcher.create();
  }
  
  @Override
  public SwitchSetMatch newEmptyMatch() {
    return SwitchSetMatch.newEmptyMatch();
  }
  
  @Override
  public SwitchSetMatch newMatch(final Object... parameters) {
    return SwitchSetMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Semaphore) parameters[0], (hu.bme.mit.trainbenchmark.railway.Route) parameters[1], (hu.bme.mit.trainbenchmark.railway.SwitchPosition) parameters[2], (hu.bme.mit.trainbenchmark.railway.Switch) parameters[3]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link SwitchSetQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link SwitchSetQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static SwitchSetQuerySpecification INSTANCE = new SwitchSetQuerySpecification();
    
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
    private final static SwitchSetQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pSemaphore = new PParameter("semaphore", "hu.bme.mit.trainbenchmark.railway.Semaphore", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pRoute = new PParameter("route", "hu.bme.mit.trainbenchmark.railway.Route", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pSwP = new PParameter("swP", "hu.bme.mit.trainbenchmark.railway.SwitchPosition", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pSw = new PParameter("sw", "hu.bme.mit.trainbenchmark.railway.Switch", (IInputKey)null, PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pSemaphore, parameter_pRoute, parameter_pSwP, parameter_pSw);
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.viatra.switchSet";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("semaphore","route","swP","sw");
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
              PVariable var_route = body.getOrCreateVariableByName("route");
              PVariable var_swP = body.getOrCreateVariableByName("swP");
              PVariable var_sw = body.getOrCreateVariableByName("sw");
              PVariable var_swpPosition = body.getOrCreateVariableByName("swpPosition");
              PVariable var_swCurrentPosition = body.getOrCreateVariableByName("swCurrentPosition");
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_semaphore, parameter_pSemaphore),
                 new ExportedParameter(body, var_route, parameter_pRoute),
                 new ExportedParameter(body, var_swP, parameter_pSwP),
                 new ExportedParameter(body, var_sw, parameter_pSw)
              ));
              // 	Route.active(route, true)
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new ConstantValue(body, var__virtual_0_, true);
              new TypeConstraint(body, new FlatTuple(var_route), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route")));
              PVariable var__virtual_1_ = body.getOrCreateVariableByName(".virtual{1}");
              new TypeConstraint(body, new FlatTuple(var_route, var__virtual_1_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "active")));
              new TypeConstraint(body, new FlatTuple(var__virtual_1_), new EDataTypeInSlotsKey((EDataType)getClassifierLiteral("http://www.eclipse.org/emf/2002/Ecore", "EBoolean")));
              new Equality(body, var__virtual_1_, var__virtual_0_);
              // 	Route.entry(route, semaphore)
              new TypeConstraint(body, new FlatTuple(var_route), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route")));
              PVariable var__virtual_2_ = body.getOrCreateVariableByName(".virtual{2}");
              new TypeConstraint(body, new FlatTuple(var_route, var__virtual_2_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "entry")));
              new TypeConstraint(body, new FlatTuple(var__virtual_2_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Semaphore")));
              new Equality(body, var__virtual_2_, var_semaphore);
              // 	Route.follows(route, swP)
              new TypeConstraint(body, new FlatTuple(var_route), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route")));
              PVariable var__virtual_3_ = body.getOrCreateVariableByName(".virtual{3}");
              new TypeConstraint(body, new FlatTuple(var_route, var__virtual_3_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "follows")));
              new TypeConstraint(body, new FlatTuple(var__virtual_3_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition")));
              new Equality(body, var__virtual_3_, var_swP);
              // 	SwitchPosition.target(swP, sw)
              new TypeConstraint(body, new FlatTuple(var_swP), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition")));
              PVariable var__virtual_4_ = body.getOrCreateVariableByName(".virtual{4}");
              new TypeConstraint(body, new FlatTuple(var_swP, var__virtual_4_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition", "target")));
              new TypeConstraint(body, new FlatTuple(var__virtual_4_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Switch")));
              new Equality(body, var__virtual_4_, var_sw);
              // 		Semaphore.signal(semaphore, ::GO)
              PVariable var__virtual_5_ = body.getOrCreateVariableByName(".virtual{5}");
              new ConstantValue(body, var__virtual_5_, getEnumLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Signal", "GO").getInstance());
              new TypeConstraint(body, new FlatTuple(var_semaphore), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Semaphore")));
              PVariable var__virtual_6_ = body.getOrCreateVariableByName(".virtual{6}");
              new TypeConstraint(body, new FlatTuple(var_semaphore, var__virtual_6_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Semaphore", "signal")));
              new TypeConstraint(body, new FlatTuple(var__virtual_6_), new EDataTypeInSlotsKey((EDataType)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Signal")));
              new Equality(body, var__virtual_6_, var__virtual_5_);
              // 	SwitchPosition.position(swP, swpPosition)
              new TypeConstraint(body, new FlatTuple(var_swP), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition")));
              PVariable var__virtual_7_ = body.getOrCreateVariableByName(".virtual{7}");
              new TypeConstraint(body, new FlatTuple(var_swP, var__virtual_7_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "SwitchPosition", "position")));
              new TypeConstraint(body, new FlatTuple(var__virtual_7_), new EDataTypeInSlotsKey((EDataType)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Position")));
              new Equality(body, var__virtual_7_, var_swpPosition);
              // 	Switch.currentPosition(sw, swCurrentPosition)
              new TypeConstraint(body, new FlatTuple(var_sw), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Switch")));
              PVariable var__virtual_8_ = body.getOrCreateVariableByName(".virtual{8}");
              new TypeConstraint(body, new FlatTuple(var_sw, var__virtual_8_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Switch", "currentPosition")));
              new TypeConstraint(body, new FlatTuple(var__virtual_8_), new EDataTypeInSlotsKey((EDataType)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Position")));
              new Equality(body, var__virtual_8_, var_swCurrentPosition);
              // 	swpPosition != swCurrentPosition
              new Inequality(body, var_swpPosition, var_swCurrentPosition);
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
  
  private static boolean evaluateExpression_1_1() {
    return true;
  }
}
