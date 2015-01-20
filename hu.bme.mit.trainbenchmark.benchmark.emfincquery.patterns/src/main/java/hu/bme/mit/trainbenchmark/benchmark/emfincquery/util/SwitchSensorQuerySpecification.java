package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SwitchSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.HasSensorQuerySpecification;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedQuerySpecification;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.psystem.PBody;
import org.eclipse.incquery.runtime.matchers.psystem.PVariable;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.NegativePatternCall;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.TypeUnary;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate SwitchSensorMatcher in a type-safe way.
 * 
 * @see SwitchSensorMatcher
 * @see SwitchSensorMatch
 * 
 */
@SuppressWarnings("all")
public final class SwitchSensorQuerySpecification extends BaseGeneratedQuerySpecification<SwitchSensorMatcher> {
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static SwitchSensorQuerySpecification instance() throws IncQueryException {
    return LazyHolder.INSTANCE;
    
  }
  
  @Override
  protected SwitchSensorMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return SwitchSensorMatcher.on(engine);
  }
  
  @Override
  public String getFullyQualifiedName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.switchSensor";
    
  }
  
  @Override
  public List<String> getParameterNames() {
    return Arrays.asList("Individual");
  }
  
  @Override
  public List<PParameter> getParameters() {
    return Arrays.asList(new PParameter("Individual", "Concept.Switch"));
  }
  
  @Override
  public SwitchSensorMatch newEmptyMatch() {
    return SwitchSensorMatch.newEmptyMatch();
  }
  
  @Override
  public SwitchSensorMatch newMatch(final Object... parameters) {
    return SwitchSensorMatch.newMatch((Concept.Switch) parameters[0]);
  }
  
  @Override
  public Set<PBody> doGetContainedBodies() throws IncQueryException {
    Set<PBody> bodies = Sets.newLinkedHashSet();
    {
      PBody body = new PBody(this);
      PVariable var_Individual = body.getOrCreateVariableByName("Individual");
      body.setExportedParameters(Arrays.<ExportedParameter>asList(
        new ExportedParameter(body, var_Individual, "Individual")
      ));
      
      new TypeUnary(body, var_Individual, getClassifierLiteral("http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl", "Switch"), "http://www.semanticweb.org/ontologies/2011/1/TrainRequirementOntology.owl/Switch");
      new NegativePatternCall(body, new FlatTuple(var_Individual), HasSensorQuerySpecification.instance().instance());
      bodies.add(body);
    }
    return bodies;
  }
  
  private static class LazyHolder {
    private final static SwitchSensorQuerySpecification INSTANCE = make();
    
    public static SwitchSensorQuerySpecification make() {
      return new SwitchSensorQuerySpecification();					
      
    }
  }
}
