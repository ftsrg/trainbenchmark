package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SignalNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SignalNeighborMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ConnectingSensorsQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.EntrySignalSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ExitSignalSensorQuerySpecification;
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
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.Inequality;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.NegativePatternCall;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.PositivePatternCall;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate SignalNeighborMatcher in a type-safe way.
 * 
 * @see SignalNeighborMatcher
 * @see SignalNeighborMatch
 * 
 */
@SuppressWarnings("all")
public final class SignalNeighborQuerySpecification extends BaseGeneratedQuerySpecification<SignalNeighborMatcher> {
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static SignalNeighborQuerySpecification instance() throws IncQueryException {
    return LazyHolder.INSTANCE;
    
  }
  
  @Override
  protected SignalNeighborMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return SignalNeighborMatcher.on(engine);
  }
  
  @Override
  public String getFullyQualifiedName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.signalNeighbor";
    
  }
  
  @Override
  public List<String> getParameterNames() {
    return Arrays.asList("R1");
  }
  
  @Override
  public List<PParameter> getParameters() {
    return Arrays.asList(new PParameter("R1", "Concept.Route"));
  }
  
  @Override
  public SignalNeighborMatch newEmptyMatch() {
    return SignalNeighborMatch.newEmptyMatch();
  }
  
  @Override
  public SignalNeighborMatch newMatch(final Object... parameters) {
    return SignalNeighborMatch.newMatch((Concept.Route) parameters[0]);
  }
  
  @Override
  public Set<PBody> doGetContainedBodies() throws IncQueryException {
    Set<PBody> bodies = Sets.newLinkedHashSet();
    {
      PBody body = new PBody(this);
      PVariable var_R1 = body.getOrCreateVariableByName("R1");
      PVariable var_SigA = body.getOrCreateVariableByName("SigA");
      PVariable var_Sen1A = body.getOrCreateVariableByName("Sen1A");
      PVariable var_Sen2A = body.getOrCreateVariableByName("Sen2A");
      PVariable var_R3A = body.getOrCreateVariableByName("R3A");
      PVariable var__R2A = body.getOrCreateVariableByName("_R2A");
      body.setExportedParameters(Arrays.<ExportedParameter>asList(
        new ExportedParameter(body, var_R1, "R1")
      ));
      
      new PositivePatternCall(body, new FlatTuple(var_SigA, var_R1, var_Sen1A), ExitSignalSensorQuerySpecification.instance());
      new PositivePatternCall(body, new FlatTuple(var_Sen1A, var_Sen2A), ConnectingSensorsQuerySpecification.instance());
      new PositivePatternCall(body, new FlatTuple(var_R3A, var_Sen2A), RDefinitionQuerySpecification.instance());
      new Inequality(body, var_R3A, var_R1);
      new NegativePatternCall(body, new FlatTuple(var_SigA, var__R2A, var_Sen2A), EntrySignalSensorQuerySpecification.instance().instance());
      bodies.add(body);
    }
    return bodies;
  }
  
  private static class LazyHolder {
    private final static SignalNeighborQuerySpecification INSTANCE = make();
    
    public static SignalNeighborQuerySpecification make() {
      return new SignalNeighborQuerySpecification();					
      
    }
  }
}
