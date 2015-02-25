package hu.bme.mit.trainbenchmark.benchmark.emfincquery.util;

import com.google.common.collect.Sets;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySignalSensorMatch;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.EntrySignalSensorMatcher;
import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.EntrySignalQuerySpecification;
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
 * A pattern-specific query specification that can instantiate EntrySignalSensorMatcher in a type-safe way.
 * 
 * @see EntrySignalSensorMatcher
 * @see EntrySignalSensorMatch
 * 
 */
@SuppressWarnings("all")
public final class EntrySignalSensorQuerySpecification extends BaseGeneratedEMFQuerySpecification<EntrySignalSensorMatcher> {
  private EntrySignalSensorQuerySpecification() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws IncQueryException if the pattern definition could not be loaded
   * 
   */
  public static EntrySignalSensorQuerySpecification instance() throws IncQueryException {
    try{
    	return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
    	throw processInitializerError(err);
    }
  }
  
  @Override
  protected EntrySignalSensorMatcher instantiate(final IncQueryEngine engine) throws IncQueryException {
    return EntrySignalSensorMatcher.on(engine);
  }
  
  @Override
  public EntrySignalSensorMatch newEmptyMatch() {
    return EntrySignalSensorMatch.newEmptyMatch();
  }
  
  @Override
  public EntrySignalSensorMatch newMatch(final Object... parameters) {
    return EntrySignalSensorMatch.newMatch((hu.bme.mit.trainbenchmark.railway.Signal) parameters[0], (hu.bme.mit.trainbenchmark.railway.Route) parameters[1], (hu.bme.mit.trainbenchmark.railway.Sensor) parameters[2]);
  }
  
  private static class LazyHolder {
    private final static EntrySignalSensorQuerySpecification INSTANCE = make();
    
    public static EntrySignalSensorQuerySpecification make() {
      return new EntrySignalSensorQuerySpecification();					
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private final static EntrySignalSensorQuerySpecification.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    @Override
    public String getFullyQualifiedName() {
      return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySignalSensor";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("signal","route2","sensor2");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return Arrays.asList(new PParameter("signal", "hu.bme.mit.trainbenchmark.railway.Signal"),new PParameter("route2", "hu.bme.mit.trainbenchmark.railway.Route"),new PParameter("sensor2", "hu.bme.mit.trainbenchmark.railway.Sensor"));
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() throws QueryInitializationException {
      Set<PBody> bodies = Sets.newLinkedHashSet();
      try {
      {
      	PBody body = new PBody(this);
      	PVariable var_signal = body.getOrCreateVariableByName("signal");
      	PVariable var_route2 = body.getOrCreateVariableByName("route2");
      	PVariable var_sensor2 = body.getOrCreateVariableByName("sensor2");
      	body.setExportedParameters(Arrays.<ExportedParameter>asList(
      		new ExportedParameter(body, var_signal, "signal"),
      				
      		new ExportedParameter(body, var_route2, "route2"),
      				
      		new ExportedParameter(body, var_sensor2, "sensor2")
      	));
      	new PositivePatternCall(body, new FlatTuple(var_route2, var_signal), EntrySignalQuerySpecification.instance().getInternalQueryRepresentation());
      	new PositivePatternCall(body, new FlatTuple(var_route2, var_sensor2), RouteDefinitionQuerySpecification.instance().getInternalQueryRepresentation());
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
