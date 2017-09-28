/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/SwitchMonitored.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchMonitoredMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.SwitchMonitoredMatcher;
import hu.bme.mit.trainbenchmark.benchmark.viatra.util.HasSensorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.exception.ViatraQueryException;
import org.eclipse.viatra.query.runtime.matchers.backend.IQueryBackendFactory;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.matchers.context.IInputKey;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.NegativePatternCall;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate SwitchMonitoredMatcher in a type-safe way.
 * 
 * @see SwitchMonitoredMatcher
 * @see SwitchMonitoredMatch
 * 
 */
@SuppressWarnings("all")
public final class SwitchMonitoredQuerySpecification extends BaseGeneratedEMFQuerySpecification<SwitchMonitoredMatcher> {
  private SwitchMonitoredQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static SwitchMonitoredQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected SwitchMonitoredMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return SwitchMonitoredMatcher.on(engine);
  }
  
  @Override
  public SwitchMonitoredMatcher instantiate() throws ViatraQueryException {
    return SwitchMonitoredMatcher.create();
  }
  
  @Override
  public SwitchMonitoredMatch newEmptyMatch() {
    return SwitchMonitoredMatch.newEmptyMatch();
  }
  
  @Override
  public SwitchMonitoredMatch newMatch(final Object... parameters) {
    return SwitchMonitoredMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Switch) parameters[0]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link SwitchMonitoredQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link SwitchMonitoredQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static SwitchMonitoredQuerySpecification INSTANCE = new SwitchMonitoredQuerySpecification();
    
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
    private final static SwitchMonitoredQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pSw = new PParameter("sw", "hu.bme.mit.trainbenchmark.railway.Switch", (IInputKey)null, PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pSw);
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.viatra.switchMonitored";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("sw");
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
              PVariable var_sw = body.getOrCreateVariableByName("sw");
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_sw, parameter_pSw)
              ));
              // 	Switch(sw)
              new TypeConstraint(body, new FlatTuple(var_sw), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Switch")));
              // 	neg find hasSensor(sw)
              new NegativePatternCall(body, new FlatTuple(var_sw), HasSensorQuerySpecification.instance().getInternalQueryRepresentation());
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
