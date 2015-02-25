package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ExitSignalSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.ExitSignalSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ExitSignalQuerySpecification;
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
import org.eclipse.incquery.runtime.matchers.psystem.basicenumerables.PositivePatternCall;
import org.eclipse.incquery.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.incquery.runtime.matchers.psystem.queries.QueryInitializationException;
import org.eclipse.incquery.runtime.matchers.tuple.FlatTuple;

/**
 * A pattern-specific query specification that can instantiate ExitSignalSensorMatcher in a type-safe way.
 * 
 * @see ExitSignalSensorMatcher
 * @see ExitSignalSensorMatch
 * 
 */
@SuppressWarnings("all")
public final class ExitSignalSensorQuerySpecification extends BaseGeneratedEMFQuerySpecification<ExitSignalSensorMatcher> {
  private ExitSignalSensorQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static ExitSignalSensorQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected ExitSignalSensorMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return ExitSignalSensorMatcher.on(engine);
  }
  
  @Override
  public ExitSignalSensorMatch newEmptyMatch() {
    return ExitSignalSensorMatch.newEmptyMatch();
  }
  
  @Override
  public ExitSignalSensorMatch newMatch(final Object... parameters) {
    return ExitSignalSensorMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Signal) parameters[0], (hu.bme.mit.trainbenchmark.railway.Route) parameters[1], (hu.bme.mit.trainbenchmark.railway.Sensor) parameters[2]);
  }
  
  private static class LazyHolder {
    private final static ExitSignalSensorQuerySpecification INSTANCE = make();
    
    public static ExitSignalSensorQuerySpecification make() {
      return new ExitSignalSensorQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static ExitSignalSensorQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.exitSignalSensor";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("signal","route1","sensor1");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("signal", "hu.bme.mit.trainbenchmark.railway.Signal"),new PParameter("route1", "hu.bme.mit.trainbenchmark.railway.Route"),new PParameter("sensor1", "hu.bme.mit.trainbenchmark.railway.Sensor"));
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
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_signal, "signal"),
      				
      		new ExportedParameter(body, var_route1, "route1"),
      				
      		new ExportedParameter(body, var_sensor1, "sensor1")
      	));
      	new PositivePatternCall(body, new FlatTuple(var_route1, var_signal), ExitSignalQuerySpecification.instance().getInternalQueryRepresentation());
      	new PositivePatternCall(body, new FlatTuple(var_route1, var_sensor1), RouteDefinitionQuerySpecification.instance().getInternalQueryRepresentation());
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
