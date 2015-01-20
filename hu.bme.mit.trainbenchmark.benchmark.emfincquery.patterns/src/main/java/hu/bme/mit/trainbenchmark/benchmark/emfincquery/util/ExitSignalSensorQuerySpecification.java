package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ExitSignalSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ExitSignalSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ExitSignalQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.RDefinitionQuerySpecification;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedQuerySpecification;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.psystem.PBody;
import org.eclipse.incquery.runtime.matchers.psystem.PVariable;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.PositivePatternCall;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate ExitSignalSensorMatcher in a type-safe way.
 * 
 * @see ExitSignalSensorMatcher
 * @see ExitSignalSensorMatch
 * 
 */
@SuppressWarnings("all")
public final class ExitSignalSensorQuerySpecification extends BaseGeneratedQuerySpecification<ExitSignalSensorMatcher> {
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static ExitSignalSensorQuerySpecification instance() throws IncQueryException {
    return LazyHolder.INSTANCE;
    
  }
  
  @Override
  protected ExitSignalSensorMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return ExitSignalSensorMatcher.on(engine);
  }
  
  @Override
  public String getFullyQualifiedName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.exitSignalSensor";
    
  }
  
  @Override
  public List<String> getParameterNames() {
    return Arrays.asList("Sig","R1","Sen1");
  }
  
  @Override
  public List<PParameter> getParameters() {
    return Arrays.asList(new PParameter("Sig", "Concept.Signal"),new PParameter("R1", "Concept.Route"),new PParameter("Sen1", "Concept.Sensor"));
  }
  
  @Override
  public ExitSignalSensorMatch newEmptyMatch() {
    return ExitSignalSensorMatch.newEmptyMatch();
  }
  
  @Override
  public ExitSignalSensorMatch newMatch(final Object... parameters) {
    return ExitSignalSensorMatch.newMatch((Concept.Signal) parameters[0], (Concept.Route) parameters[1], (Concept.Sensor) parameters[2]);
  }
  
  @Override
  public Set<PBody> doGetContainedBodies() throws IncQueryException {
    Set<PBody> bodies = Sets.newLinkedHashSet();
    {
      PBody body = new PBody(this);
      PVariable var_Sig = body.getOrCreateVariableByName("Sig");
      PVariable var_R1 = body.getOrCreateVariableByName("R1");
      PVariable var_Sen1 = body.getOrCreateVariableByName("Sen1");
      body.setExportedParameters(Arrays.<ExportedParameter>asList(
        new ExportedParameter(body, var_Sig, "Sig"), 
        new ExportedParameter(body, var_R1, "R1"), 
        new ExportedParameter(body, var_Sen1, "Sen1")
      ));
      
      
      
      new PositivePatternCall(body, new FlatTuple(var_R1, var_Sig), ExitSignalQuerySpecification.instance());
      new PositivePatternCall(body, new FlatTuple(var_R1, var_Sen1), RDefinitionQuerySpecification.instance());
      bodies.add(body);
    }
    return bodies;
  }
  
  private static class LazyHolder {
    private final static ExitSignalSensorQuerySpecification INSTANCE = make();
    
    public static ExitSignalSensorQuerySpecification make() {
      return new ExitSignalSensorQuerySpecification();					
      
    }
  }
}
