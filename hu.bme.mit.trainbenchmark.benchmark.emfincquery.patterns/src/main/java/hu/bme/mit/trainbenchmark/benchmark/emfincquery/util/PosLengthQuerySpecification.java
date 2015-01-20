package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.PosLengthMatcher;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedQuerySpecification;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.psystem.IExpressionEvaluator;
import org.eclipse.incquery.runtime.matchers.psystem.IValueProvider;
import org.eclipse.incquery.runtime.matchers.psystem.PBody;
import org.eclipse.incquery.runtime.matchers.psystem.PVariable;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExpressionEvaluation;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeBinary;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;

/**
 * A pattern-specific query specification that can instantiate PosLengthMatcher in a type-safe way.
 * 
 * @see PosLengthMatcher
 * @see PosLengthMatch
 * 
 */
@SuppressWarnings("all")
public final class PosLengthQuerySpecification extends BaseGeneratedQuerySpecification<PosLengthMatcher> {
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static PosLengthQuerySpecification instance() throws IncQueryException {
    return LazyHolder.INSTANCE;
    
  }
  
  @Override
  protected PosLengthMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return PosLengthMatcher.on(engine);
  }
  
  @Override
  public String getFullyQualifiedName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.posLength";
    
  }
  
  @Override
  public List<String> getParameterNames() {
    return Arrays.asList("Source","Target");
  }
  
  @Override
  public List<PParameter> getParameters() {
    return Arrays.asList(new PParameter("Source", "Concept.Segment"),new PParameter("Target", "java.lang.Integer"));
  }
  
  @Override
  public PosLengthMatch newEmptyMatch() {
    return PosLengthMatch.newEmptyMatch();
  }
  
  @Override
  public PosLengthMatch newMatch(final Object... parameters) {
    return PosLengthMatch.newMatch((Concept.Segment) parameters[0], (java.lang.Integer) parameters[1]);
  }
  
  @Override
  public Set<PBody> doGetContainedBodies() throws IncQueryException {
    Set<PBody> bodies = Sets.newLinkedHashSet();
    {
      PBody body = new PBody(this);
      PVariable var_Source = body.getOrCreateVariableByName("Source");
      PVariable var_Target = body.getOrCreateVariableByName("Target");
      body.setExportedParameters(Arrays.<ExportedParameter>asList(
        new ExportedParameter(body, var_Source, "Source"), 
        new ExportedParameter(body, var_Target, "Target")
      ));
      
      
      new TypeBinary(body, CONTEXT, var_Source, var_Target, getFeatureLiteral("http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl", "Segment", "Segment_length"), "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl/Segment.Segment_length");
      new ExpressionEvaluation(body, new IExpressionEvaluator() {
        @Override
        public String getShortDescription() {
        	return "Expression evaluation from pattern posLength";
        }
        
        @Override
        public Iterable<String> getInputParameterNames() {
        	return Arrays.asList("Target");
        }
        
        @Override
        public Object evaluateExpression(IValueProvider provider) throws Exception {
        	java.lang.Integer Target = (java.lang.Integer) provider.getValue("Target");
        	return evaluateExpression_1_1(Target);
        }
        
        },  null); 
      bodies.add(body);
    }
    return bodies;
  }
  
  private static class LazyHolder {
    private final static PosLengthQuerySpecification INSTANCE = make();
    
    public static PosLengthQuerySpecification make() {
      return new PosLengthQuerySpecification();					
      
    }
  }
  
  private boolean evaluateExpression_1_1(final Integer Target) {
    return ((Target).intValue() <= 0);
  }
}
