/**
 * Generated from platform:/resource/trainbenchmark-tool-viatra-patterns/src/hu/bme/mit/trainbenchmark/benchmark/viatra/PosLengthInject.vql
 */
package hu.bme.mit.trainbenchmark.benchmark.viatra.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.viatra.PosLengthInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.PosLengthInjectMatcher;
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
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.viatra.query.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate PosLengthInjectMatcher in a type-safe way.
 * 
 * @see PosLengthInjectMatcher
 * @see PosLengthInjectMatch
 * 
 */
@SuppressWarnings("all")
public final class PosLengthInjectQuerySpecification extends BaseGeneratedEMFQuerySpecification<PosLengthInjectMatcher> {
  private PosLengthInjectQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryException if the pattern definition could not be loaded
   * 
   */
  public static PosLengthInjectQuerySpecification instance() throws ViatraQueryException {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected PosLengthInjectMatcher instantiate(final ViatraQueryEngine engine) throws ViatraQueryException {
    return PosLengthInjectMatcher.on(engine);
  }
  
  @Override
  public PosLengthInjectMatcher instantiate() throws ViatraQueryException {
    return PosLengthInjectMatcher.create();
  }
  
  @Override
  public PosLengthInjectMatch newEmptyMatch() {
    return PosLengthInjectMatch.newEmptyMatch();
  }
  
  @Override
  public PosLengthInjectMatch newMatch(final Object... parameters) {
    return PosLengthInjectMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Segment) parameters[0]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link PosLengthInjectQuerySpecification} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link PosLengthInjectQuerySpecification#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private final static PosLengthInjectQuerySpecification INSTANCE = new PosLengthInjectQuerySpecification();
    
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
    private final static PosLengthInjectQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_pSegment = new PParameter("segment", "hu.bme.mit.trainbenchmark.railway.Segment", (IInputKey)null, PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_pSegment);
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.viatra.posLengthInject";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("segment");
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
              PVariable var_segment = body.getOrCreateVariableByName("segment");
              body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
                 new ExportedParameter(body, var_segment, parameter_pSegment)
              ));
              // 	Segment(segment)
              new TypeConstraint(body, new FlatTuple(var_segment), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://www.semanticweb.org/ontologies/2015/trainbenchmark", "Segment")));
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
