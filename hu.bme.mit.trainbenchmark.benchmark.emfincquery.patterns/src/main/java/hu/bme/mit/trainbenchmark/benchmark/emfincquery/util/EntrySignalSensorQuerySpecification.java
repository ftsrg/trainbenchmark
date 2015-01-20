package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySignalSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySignalSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.EntrySignalQuerySpecification;
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
 * A pattern-specific query specification that can instantiate EntrySignalSensorMatcher in a type-safe way.
 * 
 * @see EntrySignalSensorMatcher
 * @see EntrySignalSensorMatch
 * 
 */
@SuppressWarnings("all")
public final class EntrySignalSensorQuerySpecification extends BaseGeneratedQuerySpecification<EntrySignalSensorMatcher> {
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static EntrySignalSensorQuerySpecification instance() throws IncQueryException {
    return LazyHolder.INSTANCE;
    
  }
  
  @Override
  protected EntrySignalSensorMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return EntrySignalSensorMatcher.on(engine);
  }
  
  @Override
  public String getFullyQualifiedName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySignalSensor";
    
  }
  
  @Override
  public List<String> getParameterNames() {
    return Arrays.asList("Sig","R2","Sen2");
  }
  
  @Override
  public List<PParameter> getParameters() {
    return Arrays.asList(new PParameter("Sig", "Concept.Signal"),new PParameter("R2", "Concept.Route"),new PParameter("Sen2", "Concept.Sensor"));
  }
  
  @Override
  public EntrySignalSensorMatch newEmptyMatch() {
    return EntrySignalSensorMatch.newEmptyMatch();
  }
  
  @Override
  public EntrySignalSensorMatch newMatch(final Object... parameters) {
    return EntrySignalSensorMatch.newMatch((Concept.Signal) parameters[0], (Concept.Route) parameters[1], (Concept.Sensor) parameters[2]);
  }
  
  @Override
  public Set<PBody> doGetContainedBodies() throws IncQueryException {
    Set<PBody> bodies = Sets.newLinkedHashSet();
    {
      PBody body = new PBody(this);
      PVariable var_Sig = body.getOrCreateVariableByName("Sig");
      PVariable var_R2 = body.getOrCreateVariableByName("R2");
      PVariable var_Sen2 = body.getOrCreateVariableByName("Sen2");
      body.setExportedParameters(Arrays.<ExportedParameter>asList(
        new ExportedParameter(body, var_Sig, "Sig"), 
        new ExportedParameter(body, var_R2, "R2"), 
        new ExportedParameter(body, var_Sen2, "Sen2")
      ));
      
      
      
      new PositivePatternCall(body, new FlatTuple(var_R2, var_Sig), EntrySignalQuerySpecification.instance());
      new PositivePatternCall(body, new FlatTuple(var_R2, var_Sen2), RDefinitionQuerySpecification.instance());
      bodies.add(body);
    }
    return bodies;
  }
  
  private static class LazyHolder {
    private final static EntrySignalSensorQuerySpecification INSTANCE = make();
    
    public static EntrySignalSensorQuerySpecification make() {
      return new EntrySignalSensorQuerySpecification();					
      
    }
  }
}
