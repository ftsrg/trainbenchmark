package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectingSensorsMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ConnectingSensorsMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.SensorTrackelementQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.TrackelementConnectedQuerySpecification;
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
 * A pattern-specific query specification that can instantiate ConnectingSensorsMatcher in a type-safe way.
 * 
 * @see ConnectingSensorsMatcher
 * @see ConnectingSensorsMatch
 * 
 */
@SuppressWarnings("all")
public final class ConnectingSensorsQuerySpecification extends BaseGeneratedQuerySpecification<ConnectingSensorsMatcher> {
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static ConnectingSensorsQuerySpecification instance() throws IncQueryException {
    return LazyHolder.INSTANCE;
    
  }
  
  @Override
  protected ConnectingSensorsMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return ConnectingSensorsMatcher.on(engine);
  }
  
  @Override
  public String getFullyQualifiedName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.connectingSensors";
    
  }
  
  @Override
  public List<String> getParameterNames() {
    return Arrays.asList("Sen1","Sen2");
  }
  
  @Override
  public List<PParameter> getParameters() {
    return Arrays.asList(new PParameter("Sen1", "Concept.Sensor"),new PParameter("Sen2", "Concept.Sensor"));
  }
  
  @Override
  public ConnectingSensorsMatch newEmptyMatch() {
    return ConnectingSensorsMatch.newEmptyMatch();
  }
  
  @Override
  public ConnectingSensorsMatch newMatch(final Object... parameters) {
    return ConnectingSensorsMatch.newMatch((Concept.Sensor) parameters[0], (Concept.Sensor) parameters[1]);
  }
  
  @Override
  public Set<PBody> doGetContainedBodies() throws IncQueryException {
    Set<PBody> bodies = Sets.newLinkedHashSet();
    {
      PBody body = new PBody(this);
      PVariable var_Sen1 = body.getOrCreateVariableByName("Sen1");
      PVariable var_Sen2 = body.getOrCreateVariableByName("Sen2");
      PVariable var_Te1 = body.getOrCreateVariableByName("Te1");
      PVariable var_Te2 = body.getOrCreateVariableByName("Te2");
      body.setExportedParameters(Arrays.<ExportedParameter>asList(
        new ExportedParameter(body, var_Sen1, "Sen1"), 
        new ExportedParameter(body, var_Sen2, "Sen2")
      ));
      
      
      new PositivePatternCall(body, new FlatTuple(var_Sen1, var_Te1), SensorTrackelementQuerySpecification.instance());
      new PositivePatternCall(body, new FlatTuple(var_Sen2, var_Te2), SensorTrackelementQuerySpecification.instance());
      new PositivePatternCall(body, new FlatTuple(var_Te1, var_Te2), TrackelementConnectedQuerySpecification.instance());
      bodies.add(body);
    }
    return bodies;
  }
  
  private static class LazyHolder {
    private final static ConnectingSensorsQuerySpecification INSTANCE = make();
    
    public static ConnectingSensorsQuerySpecification make() {
      return new ConnectingSensorsQuerySpecification();					
      
    }
  }
}
