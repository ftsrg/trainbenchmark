/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SemaphoreNeighborInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SemaphoreNeighborInjectMatcher;
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
 * A pattern-specific query specification that can instantiate SemaphoreNeighborInjectMatcher in a type-safe way.
 * 
 * @see SemaphoreNeighborInjectMatcher
 * @see SemaphoreNeighborInjectMatch
 * 
 */
@SuppressWarnings("all")
public final class SemaphoreNeighborInjectQuerySpecification extends BaseGeneratedEMFQuerySpecification<SemaphoreNeighborInjectMatcher> {
  private SemaphoreNeighborInjectQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static SemaphoreNeighborInjectQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected SemaphoreNeighborInjectMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SemaphoreNeighborInjectMatcher.on(engine);
  }
  
  @Override
  public SemaphoreNeighborInjectMatcher instantiate() throws ViatraQueryException {
    return SemaphoreNeighborInjectMatcher.create();
  }
  
  @Override
  public SemaphoreNeighborInjectMatch newEmptyMatch() {
    return SemaphoreNeighborInjectMatch.newEmptyMatch();
  }
  
  @Override
  public SemaphoreNeighborInjectMatch newMatch(final Object... parameters) {
    return SemaphoreNeighborInjectMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Route) parameters[0], (hu.bme.mit.trainbenchmark.railway.Semaphore) parameters[1]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link SemaphoreNeighborInjectQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link SemaphoreNeighborInjectQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static SemaphoreNeighborInjectQuerySpecification INSTANCE = new SemaphoreNeighborInjectQuerySpecification();
    
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
    private final static SemaphoreNeighborInjectQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pRoute = new PParameter("route", "hu.bme.mit.trainbenchmark.railway.Route", (IInputKey)null, PParameterDirection.INOUT);
    
    private final PParameter parameter_pSemaphore = new PParameter("semaphore", "hu.bme.mit.trainbenchmark.railway.Semaphore", (IInputKey)null, PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pRoute, parameter_pSemaphore);
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.viatra.semaphoreNeighborInject";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("route","semaphore");
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
              PVariable var_semaphore = body.getOrCreateVariableByName("semaphore");
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_route, parameter_pRoute),
                 new ExportedParameter(body, var_semaphore, parameter_pSemaphore)
              ));
              // 	Route.entry(route, semaphore)
              new TypeConstraint(body, new FlatTuple(var_route), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route")));
              PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
              new TypeConstraint(body, new FlatTuple(var_route, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Route", "entry")));
              new TypeConstraint(body, new FlatTuple(var__virtual_0_), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Semaphore")));
              new Equality(body, var__virtual_0_, var_semaphore);
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
