package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SignalNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.SignalNeighborMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ConnectingSensorsQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.EntrySignalSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ExitSignalSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.RouteDefinitionQuerySpecification;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.incquery.runtime.api.IncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.incquery.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.incquery.runtime.exception.IncQueryException;
import org.eclipse.incquery.runtime.matchers.psystem.PBody;
import org.eclipse.incquery.runtime.matchers.psystem.PVariable;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.Inequality;
import org.eclipse.incquery.runtime.matchers.psystem.basicdeferred.NegativePatternCall;
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.PositivePatternCall;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.incquery.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate SignalNeighborMatcher in a type-safe way.
 * 
 * @see SignalNeighborMatcher
 * @see SignalNeighborMatch
 * 
 */
@SuppressWarnings("all")
public final class SignalNeighborQuerySpecification extends BaseGeneratedEMFQuerySpecification<SignalNeighborMatcher> {
  private SignalNeighborQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static SignalNeighborQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected SignalNeighborMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return SignalNeighborMatcher.on(engine);
  }
  
  @Override
  public SignalNeighborMatch newEmptyMatch() {
    return SignalNeighborMatch.newEmptyMatch();
  }
  
  @Override
  public SignalNeighborMatch newMatch(final Object... parameters) {
    return SignalNeighborMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Signal) parameters[0]);
  }
  
  private static class LazyHolder {
    private final static SignalNeighborQuerySpecification INSTANCE = make();
    
    public static SignalNeighborQuerySpecification make() {
      return new SignalNeighborQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static SignalNeighborQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.SignalNeighbor";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("signal");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("signal", "hu.bme.mit.trainbenchmark.railway.Signal"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      {
      	PBody body = new PBody(this);
      	PVariable var_signal = body.getOrCreateVariableByName("signal");
      	PVariable var_route1 = body.getOrCreateVariableByName("route1");
      	PVariable var_sensor1 = body.getOrCreateVariableByName("sensor1");
      	PVariable var_sensor2 = body.getOrCreateVariableByName("sensor2");
      	PVariable var_route3 = body.getOrCreateVariableByName("route3");
      	PVariable var__route2 = body.getOrCreateVariableByName("_route2");
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_signal, "signal")
      	));
      	new PositivePatternCall(body, new FlatTuple(var_signal, var_route1, var_sensor1), ExitSignalSensorQuerySpecification.instance().getInternalQueryRepresentation());
      	new PositivePatternCall(body, new FlatTuple(var_sensor1, var_sensor2), ConnectingSensorsQuerySpecification.instance().getInternalQueryRepresentation());
      	new PositivePatternCall(body, new FlatTuple(var_route3, var_sensor2), RouteDefinitionQuerySpecification.instance().getInternalQueryRepresentation());
      	new Inequality(body, var_route3, var_route1);
      	new NegativePatternCall(body, new FlatTuple(var_signal, var__route2, var_sensor2), EntrySignalSensorQuerySpecification.instance().getInternalQueryRepresentation());
      	bodies.add(body);
      }
      	// to silence compiler error
      	if (false) throw new IncQueryException("Never", "happens");
      } catch (IncQueryException ex) {
      	throw processDependencyException(ex);
      }
      return bodies;
    }
  }
}
